package arcworld.level3;

import arcworld.level3.Shooter._Point;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Level 3 for ArcWorld Game<.*.>
 * @author Dev Parzival
 * @date   31-Mar-2020
 * @time   17:24:54
 */
public class Level3 extends JPanel implements Runnable,KeyListener{

    static Random rnd;
    static{
        rnd=new Random();
    }
    JFrame frame;                                                               //JFrame on which this panel will be added.
    BufferedImage img;                                                          //Artwork is done on this Image.
    Graphics g;                                                                 //With this Graphics object above img is accessed.
    
    Shooter shooter;
    
    ArrayList<Astroid>listAstroid;
    ArrayList<Bullet>listBullet;
    /**********GAME STATUS*******/
    int level;
    int wave;
    int score;
    int astroidsDestroyed;
    int enduranceLimit=50;
    /****************************/
    public Level3(JFrame frame) {
        this.frame=frame;
        setSize(frame.size());
        img=new BufferedImage(getWidth(),getHeight(),BufferedImage.TYPE_4BYTE_ABGR);
        g=img.createGraphics();
        shooter=new Shooter(new _Point(400,600),-100,getWidth()+80,this);
        shooter.setPower(30);
        listAstroid=new ArrayList<>();
        listBullet=new ArrayList<>();
        
        level=3;
        wave=1;
        score=0;
    }
    
    @Override
    public void paint(Graphics g){
        g.drawImage(img,0,0, this);
    }
    
    public void clearImage(){
        g.setColor(Color.black);
        g.fillRect(0,0,getWidth(),getHeight());
    }
    
    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode()==KeyEvent.VK_LEFT){
            shooter.setLeftAccel(true);
        }
        if(e.getKeyCode()==KeyEvent.VK_RIGHT){
            shooter.setRightAccel(true);
        }
        if(e.getKeyCode()==KeyEvent.VK_F){
            Bullet bullet=new Bullet((int)((shooter.head.x+shooter.xLen/2)),(int)shooter.head.y-10+40,10, this);
            synchronized(listBullet){
                if(listBullet.size()!=0){
                    if(!bullet.equalsConsideringSep(listBullet.get(listBullet.size()-1))){
                        bullet.setPower(shooter.getPower());
                        listBullet.add(bullet);
                    }
                }
                else{
                    bullet.setPower(shooter.getPower());
                    listBullet.add(bullet);
                }
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode()==KeyEvent.VK_LEFT){
            shooter.setLeftAccel(false);
        }
        if(e.getKeyCode()==KeyEvent.VK_RIGHT){
            shooter.setRightAccel(false);
        }
    }
    public boolean wayClear(){
        int limit=50;
        synchronized(listAstroid){
            for(Astroid ast : listAstroid){
                if(ast.gettopMostY()<limit)
                    return false;
            }
        }
        return true;
    }
    public void createAstroids(){
        int num=rnd.nextInt(6);
        synchronized(listAstroid){
            for(int i=0;i<num;i++){
            int sepX=rnd.nextInt(300)+10;
            int sepY=rnd.nextInt(130)+20;
            if(i==0){
                Astroid ast=Astroid.getRandomAstroid(sepX, -sepY-100);
                ast.setColor(Color.white);
                ast.enduranceLimit=enduranceLimit;
                listAstroid.add(ast);
            }
            else{
                sepX=rnd.nextInt(70)+30;
                Astroid prev=listAstroid.get(listAstroid.size()-1);
                Astroid ast=Astroid.getRandomAstroid(prev.getrightMostX()+sepX, -sepY-100);
                ast.setColor(Color.white);
                listAstroid.add(ast);
               }
            }
        }
    }
    public void moveAstroids(){
        synchronized(listAstroid){
            for(int i=0;i<listAstroid.size();i++){
                Astroid ast=listAstroid.get(i);
                if(checkAstroidOutOfBond(ast)){
                    ast.dispose();
                    listAstroid.remove(i);
                    i--;
                    continue;
                }
                ast.fall();
            }
        }
    }
    public void drawAstroids(){
        for(Astroid ast: listAstroid){
            ast.drawAstroid(g);
        }
    }
    public void fillAstroids(){
        for(Astroid ast: listAstroid){
            ast.fillAstroid(g);
        }
    }
    public boolean checkAstroidOutOfBond(Astroid ast){
        
        if(ast.gettopMostY()>=getWidth())
            return true;
        return false;
    }
    public void moveBullets(){
        
        synchronized(listBullet){
            synchronized(listAstroid){
                for(int i=0;i<listBullet.size();i++){
                    Bullet bullet=listBullet.get(i);
                    if(bullet.bulletOutOfBound()){
                        listBullet.remove(i);
                        i--;
                        continue;
                    }
                    boolean collision=false;
                    for(int j=0;j<listAstroid.size();j++){
                        Astroid ast=listAstroid.get(j);
                        if(bulletCollisionWithAstroid(ast, bullet)){
                            collision=true;
                            ast.enduranceLimit-=bullet.power;
                            if(ast.enduranceLimit>0){
                                if(ast.enduranceLimit<=20 ){
                                    ast.setColor(new Color(227, 216, 216));
                                    break;
                                }
                                else
                                    break;
                            }
                            astroidsDestroyed++;
                            if(astroidsDestroyed%10==0){
                                wave++;
                                enduranceLimit+=10;
                            }
                            score+=ast.point;
                            listAstroid.remove(j);
                            ast.dispose();
                            break;
                        }
                    }

                    if(collision){
                        listBullet.remove(i);
                        i--;
                        continue;
                    }
                    bullet.move();
                }
            }
        }
    }
    /**
     * The calling method must call this method inside synchronized block.
     * @param ast       Astroid
     * @param bullet    Bullet
     * @return          If collision returns true else false
     */
    private static boolean bulletCollisionWithAstroid(Astroid ast,Bullet bullet){
        if(bullet.startx >= ast.getLeftMostX() && bullet.startx <= ast.getrightMostX())
            if(bullet.startx >= ast.getLeftMostX() && bullet.startx <= ast.getrightMostX())
                if(Astroid.isInside(ast.points,ast.points.size(),new Point(bullet.startx,bullet.starty)))
                    return true;
                
            
        return false;
    }
    public void drawBullets(){
        synchronized(listBullet){
            for(Bullet bullet: listBullet){
                bullet.drawBullet(g);
            }
        }
    }
    @Override
    public void run() {
        for(;;){
            clearImage();
            
            if(wayClear()){
                createAstroids();
            }
            shooter.move();
            shooter.drawShooter(g);
            
            moveAstroids();
            drawAstroids();
            
            moveBullets();
            drawBullets();
            
            repaint();
            
            try {
                Thread.sleep(15);
            } catch (InterruptedException ex) {
                Logger.getLogger(Level3.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    public static void main(String[] args) {
        JFrame frame=new JFrame();
        frame.setSize(1000,700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Level3 level3=new Level3(frame);
        frame.add(level3);
        frame.addKeyListener(level3);
        frame.setVisible(true);
        Thread t=new Thread(level3);
        t.start();
    }

}

package arcworld.level3;

import arcworld.level3.Shooter._Point;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
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
    public int score;
    int astroidsDestroyed;
    int enduranceLimit;
    int originalLife;
    int currentLife;
    int astroidFall;
    /****************************/
    
    Font pixelMplus_20;
    Font pixelMplus_25;
    Font pixelMplus_15;
    
    GameSound sound;
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
        astroidsDestroyed=0;
        enduranceLimit=0;
        currentLife=originalLife=120;
        astroidFall=1;
        try {
            pixelMplus_20=Font.createFont(Font.TRUETYPE_FONT,new File("src\\arcworld\\PixelMplus10-Regular.ttf")).deriveFont(20f);
            pixelMplus_25=Font.createFont(Font.TRUETYPE_FONT,new File("src\\arcworld\\PixelMplus10-Regular.ttf")).deriveFont(25f);
            pixelMplus_15=Font.createFont(Font.TRUETYPE_FONT,new File("src\\arcworld\\PixelMplus10-Regular.ttf")).deriveFont(15f);
        } catch (FontFormatException | IOException e) {
            JOptionPane.showInputDialog(frame,e);
        }
        sound=new GameSound();
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
                        listBullet.add(bullet);
                    }
                }
                else{
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
        int limit=5;
        synchronized(listAstroid){
            for(Astroid ast : listAstroid){
                if(ast.gettopMostY()<limit)
                    return false;
            }
        }
        return true;
    }
    public void createAstroids(){
        int limit=920;
        int num=rnd.nextInt(6);
        synchronized(listAstroid){
            for(int i=0;i<num;i++){
                int sepX=rnd.nextInt(100)+10;
                int sepY=rnd.nextInt(150)+20;
                astroidFall=rnd.nextInt(Math.min(5,wave))+1;
                if(i==0){
                    sepX+=rnd.nextInt(200)+20;
                    Astroid ast=Astroid.getRandomAstroid(sepX, -sepY-100);
                    ast.setColor(Color.white);
                    ast.setFall(astroidFall);
                    listAstroid.add(ast);
                }
                else{
                    sepX=rnd.nextInt(70)+30;
                    Astroid prev=listAstroid.get(listAstroid.size()-1);
                    int leftMost=prev.getrightMostX()+sepX;
                    if(leftMost>limit)return;
                    int topMost= -sepY-100-rnd.nextInt(40);
                    Astroid ast=Astroid.getRandomAstroid(leftMost,topMost);
                    ast.setColor(Color.white);
                    ast.setFall(astroidFall);
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
                    currentLife-=10;
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
        
        if(ast.gettopMostY()>=getHeight())
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
                            Thread t=new Thread(sound);
                            t.start();
                            astroidsDestroyed++;
                            if(astroidsDestroyed%13==0){
                                wave++;
//                                enduranceLimit+=10;
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
    public void drawScore(){
        //LEVEL
        g.setColor(Color.lightGray);
        g.setFont(pixelMplus_25);
        g.drawString("LEVEL",800, 50);
        
        g.setColor(Color.white);
        g.setFont(pixelMplus_25);
        g.drawString(Integer.toString(level),900, 50);
        
        //SCORE
        g.setColor(Color.lightGray);
        g.setFont(pixelMplus_25);
        g.drawString("SCORE",800, 100);
        
        g.setColor(Color.white);
        g.setFont(pixelMplus_25);
        g.drawString(Integer.toString(score),900, 100);
        
        //LIFE
        g.setColor(Color.lightGray);
        g.setFont(pixelMplus_25);
        g.drawString("LIFE",800, 150);
        
        if(currentLife<=40)
            g.setColor(Color.red);
        else
        g.setColor(Color.yellow);
        g.fillRect(900,132,(int)(100*(currentLife/((float)originalLife))),20);
    }
    public void dispose(){
        listAstroid.clear();
        listBullet.clear();
        g.dispose();
        
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

            drawScore();
            
            moveBullets();
            drawBullets();
            
            repaint();
            
            try {
                Thread.sleep(10);
            } catch (InterruptedException ex) {
                Logger.getLogger(Level3.class.getName()).log(Level.SEVERE, null, ex);
            }
            if(currentLife<=0){
                return;
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

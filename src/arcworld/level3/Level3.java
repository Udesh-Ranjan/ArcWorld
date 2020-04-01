package arcworld.level3;

import arcworld.level3.Shooter._Point;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
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
    
    public Level3(JFrame frame) {
        this.frame=frame;
        setSize(frame.size());
        img=new BufferedImage(getWidth(),getHeight(),BufferedImage.TYPE_4BYTE_ABGR);
        g=img.createGraphics();
        shooter=new Shooter(new _Point(400,600),-100,getWidth()-65,this);
        listAstroid=new ArrayList<>();
        
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
        for(Astroid ast : listAstroid){
            if(ast.gettopMostY()<limit)
                return false;
        }
        return true;
    }
    public void createAstroids(){
        int num=rnd.nextInt(6);
        for(int i=0;i<num;i++){
            int sepX=rnd.nextInt(300)+10;
            int sepY=rnd.nextInt(130)+20;
            if(i==0){
                Astroid ast=Astroid.getRandomAstroid(sepX, -sepY-100);
                ast.setColor(Color.white);
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
    public void moveAstroids(){
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

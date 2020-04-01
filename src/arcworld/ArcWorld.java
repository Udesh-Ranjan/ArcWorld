package arcworld;

import arcworld.level1.Level1;
import arcworld.level1.Level1Sound;
import arcworld.level1.WelcomeToLevel1;
import arcworld.level2.Level2;
import arcworld.level2.WelcomeToLevel2;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Dev Parzival
 */
public class ArcWorld extends JFrame implements Runnable{

    Dimension size;
    Level1 level1;
    
    public ArcWorld(){
        size=new Dimension(1000,700);
        this.setLayout(null);
        this.setSize(size);
        this.setResizable(false);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
    }
    public void paint(Graphics g){
        g.setColor(Color.black);
        g.fillRect(0,0,getWidth(),getHeight());
    }
    @Override
    public void run() {
        //////////Level1/////////
        setBackground(Color.black);
        setForeground(Color.cyan);
        repaint();
        
        Level1Sound sound=null;
        try {
            sound=new Level1Sound("src\\arcworld\\Level1.wav");
            sound.playSound();
        } catch (Exception ex) {
            JOptionPane.showInputDialog(this,ex.toString());
        }
        
        WelcomeToLevel1 welcome1=new WelcomeToLevel1(this);
        this.add(welcome1);
        welcome1.display("Level1",5000);
        
        try {
            Thread.sleep(5000);
            sound.disposeSound();
        } catch (Exception e) {
        }
        
        this.remove(welcome1);
        
        level1=new Level1(getWidth(),getHeight(),this,getForeground(),getBackground(),700);
        addKeyListener(level1);
        requestFocus();
        addWindowListener(level1);
        level1.setBounds(0, 0,getWidth(), getHeight());
        add(level1);
        Thread t=new Thread(level1);
        t.start();
        try {
            t.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(ArcWorld.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.remove(level1);
        try {
            sound=new Level1Sound("src\\arcworld\\Level2.wav");
            sound.playSound();
        } catch (Exception ex) {
            JOptionPane.showInputDialog(this,ex.toString());
        }
        WelcomeToLevel2 welcome2=new WelcomeToLevel2(this);
        this.add(welcome2);
        welcome2.display("Level2",5000);
        this.remove(welcome2);
        try {
            Thread.sleep(5000);
            sound.disposeSound();
        } catch (Exception e) {
        }
        Level2 level2;
        level2 = new Level2(this) {};
        add(level2);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        t=new Thread(level2);
        t.start();
        
        try {
            t.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(Level2.class.getName()).log(Level.SEVERE, null, ex);
        }
        remove(level2);
        level2.dispose();
        
        System.out.println("Exiting The thread");
        System.exit(0);
        ////////////Level2///////////
    }
    
    public static void main(String[] args) {
        ArcWorld arcadeWorld=new ArcWorld();
        Thread game=new Thread(arcadeWorld);
        game.start();
    }
}

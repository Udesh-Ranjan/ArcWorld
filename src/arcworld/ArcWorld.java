package arcworld;

import arcworld.level1.Level1;
import arcworld.level1.Level1Sound;
import arcworld.level1.WelcomeToLevel1;
import arcworld.level2.Level2;
import arcworld.level2.Level2Sound;
import arcworld.level2.WelcomeToLevel2;
import arcworld.level3.Level3;
import arcworld.level3.Level3Sound;
import arcworld.level3.WelcomeToLevel3;
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
    /**
     * GAME FINISHED.
     * @param score Score to be Displayed
     */
    public void gameEnd(int score){
        GameEnd gameEnd=new GameEnd(this);
        this.add(gameEnd);
        gameEnd.gameEnd(score);
    }
    @Override
    public void run() {
        int score=0;
        
        setBackground(Color.black);
        setForeground(Color.cyan);
        repaint();
        
        Level1Sound soundLevel1=null;
        try {
            soundLevel1=new Level1Sound("src\\arcworld\\Level1.wav");
            soundLevel1.playSound();
        } catch (Exception ex) {
            JOptionPane.showInputDialog(this,ex.toString());
        }
        //////////Welcome to Level1/////////
        WelcomeToLevel1 welcome1=new WelcomeToLevel1(this);
        this.add(welcome1);
        welcome1.display("Level1",5000);
        
        try {
            Thread.sleep(5000);
            soundLevel1.disposeSound();
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
        
        score+=level1.astroids_destroyed;
        
        this.remove(level1);
        if(!level1.finished){
            gameEnd(score);
            return;
        }
        Level2Sound soundLevel2=null;
        try {
            soundLevel2=new Level2Sound("src\\arcworld\\Level2.wav");
            soundLevel2.playSound();
        } catch (Exception ex) {
            JOptionPane.showInputDialog(this,ex.toString());
        }
        //////////Welcome to Level2/////////
        WelcomeToLevel2 welcome2=new WelcomeToLevel2(this);
        this.add(welcome2);
        welcome2.display("Level2",5000);
        this.remove(welcome2);
        try {
            Thread.sleep(5000);
            soundLevel2.disposeSound();
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
        score+=level2.score;
        remove(level2);
        level2.dispose();
        
        if(!level2.finished){
            gameEnd(score);
            return;
        }
        /////////Welcome To Level3//////////
        Level3Sound soundLevel3=null;
        try {
            soundLevel3=new Level3Sound("src\\arcworld\\Level3.wav");
            soundLevel3.playSound();
        } catch (Exception ex) {
            JOptionPane.showInputDialog(this,ex.toString());
        }
        WelcomeToLevel3 welcome3=new WelcomeToLevel3(this);
        this.add(welcome3);
        welcome3.display("Level3",5000);
        try{
            Thread.sleep(5000);
            soundLevel3.disposeSound();
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        remove(welcome3);
        
        Level3 level3=new Level3(this);
        add(level3);
        this.addKeyListener(level3);
        t=new Thread(level3);
        t.start();
        try {
            t.join();
        } catch (Exception e) {
            e.printStackTrace();
        }
        score+=level3.score;
        level3.dispose();
        gameEnd(score);
    }
    /**
     * Main method
     * @param args 
     */
    public static void main(String[] args) {
        ArcWorld arcadeWorld=new ArcWorld();
        Thread game=new Thread(arcadeWorld);
        game.start();
    }
}

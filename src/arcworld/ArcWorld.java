package arcworld;

import arcworld.level1.Level1;
import arcworld.level1.WelcomeUser;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;

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
        
        WelcomeUser wel=new WelcomeUser(this);
        add(wel);
        wel.repaint();
        Thread t=new Thread(wel);
        t.start();
        try {
            t.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(ArcWorld.class.getName()).log(Level.SEVERE, null, ex);
        }
        wel.clear();
        
        level1=new Level1(getWidth(),getHeight(),this,getForeground(),getBackground(),700);
        addKeyListener(level1);
        requestFocus();
        addWindowListener(level1);
        level1.setBounds(0, 0,getWidth(), getHeight());
        add(level1);
        t=new Thread(level1);
        t.start();
        try {
            t.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(ArcWorld.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Exiting The thread");
        ////////////Level2///////////
    }
    
    public static void main(String[] args) {
        ArcWorld arcadeWorld=new ArcWorld();
        Thread game=new Thread(arcadeWorld);
        game.start();
    }
}

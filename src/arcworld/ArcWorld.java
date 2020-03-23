package arcworld;

import arcworld.level1.Level1;
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
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        
        
    }
    public void paint(Graphics g){
        g.setColor(Color.black);
        g.fillRect(0,0,700,700);
    }
    @Override
    public void run() {
        //////////Level1/////////
        setBackground(Color.black);
        setForeground(Color.cyan);
        level1=new Level1(700,700,this,getForeground(),getBackground());
        addKeyListener(level1);
        addWindowListener(level1);
        level1.setBounds(0, 0, 700, 700);
        add(level1);
        Thread t=new Thread(level1);
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

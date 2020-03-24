package level2;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Dev Parzival
 */
public abstract class Level2 extends JPanel implements Runnable,KeyListener{

    BufferedImage img;
    Graphics g;
    Astroid ast;
    public Level2(JFrame frame) {
        this.setSize(frame.getSize());
        frame.addKeyListener(this);
        img=new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_4BYTE_ABGR);
        g=img.getGraphics();
        ast=new Astroid(Astroid.map.get(1), (Graphics2D) g, this, img,this.getWidth()/2,this.getHeight()/2);
    }

    @Override
    public void run() {
        for(;;){
            g.setColor(Color.black);
            g.fillRect(0,0,getWidth(), getHeight());
            g.setColor(Color.red);
            ast.drawAstroid();
            ast.move();
            ///////Game Refresh Rate///////
            repaint();
            try {
                Thread.sleep(10);
            } catch (InterruptedException ex) {
                Logger.getLogger(Level2.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    @Override
    public void paint(Graphics g){
        g.drawImage(img, 0, 0, this);
    }
    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
    }
    //////////Main Method////////////
    public static void main(String[] args) {
        
        JFrame frame=new JFrame();
        frame.setSize(1000, 700);
        Level2 level2;
        level2 = new Level2(frame) {};
        frame.add(level2);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Thread t=new Thread(level2);
        t.start();
        
        try {
            t.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(Level2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


}

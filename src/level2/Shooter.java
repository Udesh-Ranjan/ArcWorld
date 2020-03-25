package level2;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Dev Parzival
 */
public class Shooter extends JPanel implements KeyListener{

    static class _Point{
        double x;
        double y;

        public _Point(double x,double y) {
            this.x=x;
            this.y=y;
        }
        public _Point(_Point p){
            this.x=p.x;
            this.y=p.y;
        }
        @Override
        public String toString(){
            return "x= "+x+" "+"y= "+y;
        }
    }
    
    BufferedImage img;
    Graphics g;
    _Point corkpit;
//    Point wing1;
//    Point wing2;
    _Point tail;
    
    int wingDistance;
    int length;
    
    /**
     *
     * @param corkpit
     * @param length
     */
    public Shooter(_Point corkpit,int length){
        this.corkpit=corkpit;
        this.length=length;
        tail=new _Point(corkpit.x,corkpit.y+length);
        setSize(1000, 700);
        img=new BufferedImage(1000, 700, BufferedImage.TYPE_4BYTE_ABGR);
        g=img.getGraphics();
        drawShooter(g);
    }
    
    public void drawShooter(Graphics g){
        g.setColor(Color.black);
        g.fillRect(0, 0,img.getWidth(), img.getHeight());
        
        g.setColor(Color.white);
        g.drawLine((int)corkpit.x,(int)corkpit.y,(int)tail.x, (int)tail.y);
        repaint();
    }
    
    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        
        if(e.getKeyCode()==KeyEvent.VK_RIGHT){
            
            double step=2.5;
            
            //In First and Fourth quadrant so Y we are increasing.
            if ( ((int)corkpit.x==(int)tail.x &&  tail.y>corkpit.y)  ||  corkpit.x>tail.x){
                System.out.println("First & Fourth Quad");
                corkpit.y+=step;
                double val=length*length-(corkpit.y-tail.y)*(corkpit.y-tail.y);
                boolean sign = val>=0;
                val=val*(sign?1:-1);
                corkpit.x=Math.pow(val,0.5)*(sign?1:-1)+tail.x;
                System.out.println(corkpit+"\n"+tail);
            }
            //In Third and Second quadrant so Y we are decreasing.
            else{
                System.out.println("In second and third Quad");
                corkpit.y-=step;
                double val=length*length-(corkpit.y-tail.y)*(corkpit.y-tail.y);
                boolean sign = val>=0;
                val=val*(sign?1:-1);
                corkpit.x=tail.x-Math.pow(val,0.5);
                System.out.println(corkpit+"\n"+tail);
            }
            drawShooter(g);
        }
        
        if(e.getKeyCode()==KeyEvent.VK_LEFT){
            
            double step=2.5;
            
            //In Second and Third quadrant so Y is Increasing.
            if (((int)corkpit.x==(int)tail.x &&  tail.y>corkpit.y) || corkpit.x<tail.x) {
                
                corkpit.y+=step;
                double val=length*length-(corkpit.y-tail.y)*(corkpit.y-tail.y);
                boolean sign = val>=0;
                val=val*(sign?1:-1);
                corkpit.x=tail.x-Math.pow(val,0.5);
                System.out.println(corkpit+"\n"+tail);
            }
            else{
                
                //In Fourth and First quadrant so Y is Increasing.
                corkpit.y-=step;
                double val=length*length-(corkpit.y-tail.y)*(corkpit.y-tail.y);
                boolean sign = val>=0;
                val=val*(sign?1:-1);
                corkpit.x=Math.pow(val,0.5)*(sign?1:-1)+tail.x;
                System.out.println(corkpit+"\n"+tail);
                
            }
            drawShooter(g);
        }
    }
    public double getDistance(_Point p1,_Point p2){
        return Math.pow((p1.y-p2.y)*(p1.y-p2.y)+(p1.x-p2.x)*(p1.x-p2.x),0.5);
    }
    @Override
    public void keyReleased(KeyEvent e) {
        
    }
    @Override
    public void paint(Graphics g){
        g.drawImage(img,0,0,this);
    }
    ////////Main Method/////////
    public static void main(String[] args) {
        
        
        System.out.println(Math.pow(-216,0.5));
        
        
        JFrame frame=new JFrame();
        frame.setSize(1000,700);
        frame.setLayout(null);
        Shooter panel=new Shooter(new _Point(500,350),50);
        frame.addKeyListener(panel);
        frame.requestFocus();
        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

}

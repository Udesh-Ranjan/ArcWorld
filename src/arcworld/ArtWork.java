package arcworld;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Dev Parzival
 */
public class ArtWork extends JPanel implements KeyListener,MouseListener,MouseMotionListener{

    JFrame frame;
    ArrayList<Point>list;                                                       //List of points in a polygon
    BufferedImage img;                                                          //Image
    Graphics2D g;                                                               //Graphics object of img.
    Color back;
    Color front;
    ArtWork(int w,int h,JFrame frm){
        setSize(w,h);
        list=new ArrayList<>();
        img=new BufferedImage(w,h,BufferedImage.TYPE_4BYTE_ABGR);
        g=img.createGraphics();
        frame=frm;
        front=frame.getForeground();
        back=frame.getBackground();
    }
    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        //////////Displaying The Corrdinates////////////
        if(e.getKeyCode()==KeyEvent.VK_ESCAPE){
            System.out.println("Displaying List : ");
            for(int i=0;i<list.size();i++){
                 Point pnt=list.get(i);
                System.out.print(pnt.x+" ,"+pnt.y);
                if(i+1!=list.size())
                    System.out.print(" ,");
            }
            System.out.println();
        }
        if(e.getKeyCode()==KeyEvent.VK_LEFT){
            for(int i=0;i<list.size();i++)
                list.get(i).x--;
            clear();
            drawObject();
            repaint();

        }
        if(e.getKeyCode()==KeyEvent.VK_RIGHT){
            for(int i=0;i<list.size();i++)
                list.get(i).x++;
            clear();
            drawObject();
            repaint();


        }
        if(e.getKeyCode()==KeyEvent.VK_UP){
            for(int i=0;i<list.size();i++)
                list.get(i).y--;
            clear();
            drawObject();
            repaint();

        }
        if(e.getKeyCode()==KeyEvent.VK_DOWN){
            for(int i=0;i<list.size();i++)
                list.get(i).y++;
            clear();
            drawObject();
            repaint();

        }
        if(e.getKeyCode()==KeyEvent.VK_DELETE){
            if(list.size()!=0)
                list.remove(list.size()-1);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Point pnt=e.getPoint();
        if(list.size()==0)
            list.add(pnt);
        else{
            if(list.get(list.size()-1).equals(pnt)==false)
                list.add(pnt);
        }
        //System.out.println(list);
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
    
    @Override
    public void mouseDragged(MouseEvent e) {
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        Point pnt=e.getLocationOnScreen();
        frame.setTitle("ArtWork Mouse Position : "+"("+pnt.x+","+pnt.y+")");
        g.setColor(back);
        g.fillRect(0,0,this.getWidth(),this.getHeight());
        g.setColor(Color.black);
        g.drawOval(pnt.x-1, pnt.y-1, 2, 2);
        int count_points=list.size();
        for(int i=1;i<count_points;i++){
            Point pnt1=list.get(i-1);
            Point pnt2=list.get(i);
            g.drawLine(pnt1.x, pnt1.y, pnt2.x, pnt2.y);
        }
        if(count_points!=0){
            Point last_click=list.get(count_points-1);
            Point curr_mouse=e.getPoint();
            g.drawLine(last_click.x,last_click.y,curr_mouse.x,curr_mouse.y);
        }
        repaint();
    }
    public void clear(){
        g.setColor(back);
        g.fillRect(0,0,this.getWidth(),this.getHeight());
        g.setColor(Color.black);
        
    }
    public void drawObject(){
        int count_points=list.size();
        for(int i=1;i<count_points;i++){
            Point pnt1=list.get(i-1);
            Point pnt2=list.get(i);
            g.drawLine(pnt1.x, pnt1.y, pnt2.x, pnt2.y);
        }
        
    }
    @Override
    public void paint(Graphics g){
        this.g.drawOval(getWidth()/2-5,getHeight()/2-5,10,10);
        g.drawImage(img,0,0,this);
    }
    
    public static void main(String S[]) {
        
        JFrame frame=new JFrame();
        ArtWork panel=new ArtWork(1000,700,frame);
        frame.setSize(panel.getSize());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        frame.addKeyListener(panel);
        frame.requestFocus();
        frame.addMouseListener(panel);
        frame.addMouseMotionListener(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        
    }

}

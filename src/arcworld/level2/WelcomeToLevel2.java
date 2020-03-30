package arcworld.level2;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Dev Parzival
 * @date   30-Mar-2020
 * @time   10:47:15
 */
public class WelcomeToLevel2 extends JPanel{

    BufferedImage  img;
    Graphics g;
    Font pixelMplus;
    ArrayList<Point>color;
    public WelcomeToLevel2(JFrame frame){
        
        try{
            pixelMplus=Font.createFont(Font.TRUETYPE_FONT,new File("src\\arcworld\\PixelMplus12-Bold.ttf")).deriveFont(100f);
            GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(pixelMplus);
        }
        catch(IOException | FontFormatException ex){
            ex.printStackTrace();
        }
        
        this.setSize(frame.getSize());
        color=new ArrayList<Point>();
        double step=242/255.00;                                               //for each r step g valis present.
        double r=0,g=0;
        for(r=0;r<=255;r++){
            color.add(new Point((int)r,(int)g));
            g+=step;
        }
        
    }
    public void display(String msg,long time){
        createImage();
        
        double delay=((double)time)/color.size();
        
        for (Point color1 : color) {
            clearImage();
            g.setFont(pixelMplus);
            g.setColor(new Color(color1.x, color1.y, 0));
            int length=getLength(msg);
            g.drawString(msg,getWidth()/2-length/2,250);
            repaint();
            try{
                Thread.sleep((long) delay);
            }
            catch(InterruptedException ex){
                ex.printStackTrace();
            }
        }
        dispose();
    }
    public void createImage(){
        this.img=new BufferedImage(getWidth(),getHeight(), BufferedImage.TYPE_4BYTE_ABGR);
        this.g=img.createGraphics();
    }
    public void dispose(){
        g.dispose();
        img=null;
    }
    public int getLength(String str){
        return g.getFontMetrics().stringWidth(str);
    }
    @Override
    public void paint(Graphics g){
        if(img!=null)
            g.drawImage(img,0,0, this);
    }
    public void clearImage(){
        g.setColor(Color.BLACK);
        g.fillRect(0,0,getWidth(),getHeight());
    }
    public static void main(String[] args) {
        JFrame frame=new JFrame();
        frame.setSize(1000,700);
        WelcomeToLevel2 wel=new WelcomeToLevel2(frame);
        frame.add(wel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        wel.display("Level1",5000);

    }

}

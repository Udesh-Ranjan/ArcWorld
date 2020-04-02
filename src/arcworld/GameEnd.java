package arcworld;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Dev Parzival
 * @date   02-Apr-2020
 * @time   17:57:43
 */
public class GameEnd extends JPanel{
    
    BufferedImage  img;
    Graphics g;
    Font pixelMplus;
    
    public GameEnd(JFrame frame){
        
        this.setSize(frame.getSize());
        img=new BufferedImage(getWidth(),getHeight(),BufferedImage.TYPE_4BYTE_ABGR);
        g=img.createGraphics();
        
        try{
            pixelMplus=Font.createFont(Font.TRUETYPE_FONT,new File("src\\arcworld\\PixelMplus12-Bold.ttf")).deriveFont(200f);
            GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(pixelMplus);
        }
        catch(IOException | FontFormatException ex){
            ex.printStackTrace();
        }
    }
    public void clearImage(){
        g.setColor(Color.black);
        g.fillRect(0,0,getWidth(),getHeight());
    }
    public void gameEnd(int score){
        clearImage();
        Color c1=Color.gray;
        Color c2=Color.lightGray;
        Color c3=Color.white;
        Color c4=Color.darkGray;
        
        Random rnd=new Random();
        for(int i=0;i<300;i++){
            Color col;
            int val=rnd.nextInt();
            if(val%3==0)
                col=c3;
            else
                if(val%4==0)
                    col=c2;
            else
                    if(val%7==0)
                        col=c4;
            else
                        col=c1;
            
            int x=rnd.nextInt(getWidth())+1;
            int y=rnd.nextInt(getHeight())+1;
            
            g.setColor(col);
            g.drawLine(x,y,x+1,y);
        }
        g.setFont(pixelMplus);
        g.setColor(Color.gray);
        g.drawString("GAME",310,150);
        g.drawString("OVER",310,330);
        
        String str="SCORE : "+score;
        g.setColor(Color.orange);
        boolean flag=true;
        float size=200f;
        while(flag){
            pixelMplus=pixelMplus.deriveFont(size);
            g.setFont(pixelMplus);
            if(g.getFontMetrics().stringWidth(str)>getWidth()-100){
                size-=20;
            }
            else
                break;
        }
        g.drawString(str,getWidth()/2-g.getFontMetrics().stringWidth(str)/2,500);
        repaint();
    }
    @Override
    public void paint(Graphics g){
        g.drawImage(img,0,0,this);
    }
    /////////Main///////////
    public static void main(String[] args) {
        JFrame frame=new JFrame();
        frame.setSize(1000,700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        GameEnd end=new GameEnd(frame);
        end.gameEnd(2000);
        frame.add(end);
        frame.setVisible(true);
    }

}

package arcworld.level1;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Dev Parzival
 */
public class WelcomeUser extends JPanel implements Runnable{

    @Override
    public void run() {
        for(;flag;){
            
        }
    }
    static class Quote{
        String quote;
        String name;

        public Quote(String quote,String name) {
            this.quote=quote;
            this.name=name; 
            
        }
        
    }
    JButton agree;
    Random rand;
    static Quote welcome[]={new Quote("We come spinning out of nothingnes scattering stars like dust","Rumi"),
    new Quote("My goal is simple.It is a complete understanding of the universe,why it is as it is and why it exits at all","Stephen Hawking"),
    new Quote("", "")
    };
    JFrame frame;
    BufferedImage img;
    Graphics g;
    volatile boolean flag;
    public WelcomeUser(JFrame frame){
        flag=true;
        rand=new Random();
        this.frame=frame;
        this.setLayout(null);
        this.setSize(frame.getSize());
        img=new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_4BYTE_ABGR);
        g=img.getGraphics();
        drawQuote(welcome[0], g);
        agree=new JButton("I Agree :)");
        agree.setBounds(getWidth()/2,getHeight(g, 100,welcome[0])+250, 140, 40);
        agree.setBackground(Color.darkGray);
        agree.setForeground(Color.WHITE);
        agree.setFont(new Font("monospaced",Font.PLAIN,15));
        agree.setFocusPainted(false);
        agree.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                flag=false;
            }
        });
        repaint();
        add(agree);
    }
    
    public void drawQuote(Quote q,Graphics g){
        g.setColor(Color.black);
        g.fillRect(0, 0, getWidth(), getHeight());
        g.setFont(new Font("Impact", Font.PLAIN, 40));
        int sep=200;
        int x=sep;
        int y=getHeight()/2-100;
        
        drawStars(getWidth(),getHeight(), g);
        
        g.setColor(Color.LIGHT_GRAY);
        g.fillRoundRect(sep-50, y-100,getWidth()-sep*2+50, getHeight(g, sep, q), 50, 50);
        g.setColor(Color.black);
        String str[]=("\""+q.quote+"\"").split(" ");
        FontMetrics fm=g.getFontMetrics();
        for (String str1 : str) {
            str1+=" ";
            int width=fm.stringWidth(str1);
            if(width+x>=getWidth()-sep){
                x=sep;
                y+=fm.getHeight()+10;
            }
            g.drawString(str1, x, y);
            x+=width;
        }
        drawStringOnCenter(g, sep,getWidth()-sep,getHeight(g, sep, q)+120, "-"+q.name);
    }
    @Override
    public void paint(Graphics g){
        g.drawImage(img, 0, 0, this);
        agree.repaint();
        
    }
    public boolean contains(Component comp){
        return true;
    }
    public int getHeight(Graphics g,int sep,Quote q){
        String str[]=q.quote.split(" ");
        int x=sep;
        int y=getHeight()/2-100;
        FontMetrics fm=g.getFontMetrics();
        for (String str1 : str) {
            str1+=" ";
            int width=fm.stringWidth(str1);
            if(width+x>=getWidth()-sep){
                x=sep;
                y+=fm.getHeight()+10;
            }
//            g.drawString(str1, x, y);
            x+=width;
        
        }
        return y;
    }
    static void drawStringOnCenter(Graphics g,int left,int right,int height,String str){
        FontMetrics fm=g.getFontMetrics();
        int size=fm.stringWidth(str);
        int center =(right+left)/2;
        g.drawString(str,center-size/2, height);
    }
    public void drawStars(int width,int height,Graphics g){
        
        for(int i=0;i<500;i++){
            int col=rand.nextInt(3);
            if(col==0){
                g.setColor(Color.DARK_GRAY);
            }
            else
                if (col==1) {
                g.setColor(Color.LIGHT_GRAY);
            }
            else
                g.setColor(Color.white);
            
            int x=rand.nextInt(width);
            int y=rand.nextInt(height);

            g.drawLine(x, y, x, y+1);
        }
    }
    public void clear(){
        agree.removeActionListener(null);
        img=null;
        g.dispose();
        agree=null;
        
    }
    public static void main(String[] args) {
        JFrame frame=new JFrame();
        frame.setSize(1000, 700);
        WelcomeUser wel=new WelcomeUser(frame);
        frame.add(wel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
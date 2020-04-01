package arcworld.level3;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.JPanel;

/**
 *
 * @author Dev Parzival
 * @date   31-Mar-2020
 * @time   17:38:00
 */
public class Shooter {
    
    static final double GRAVITY=0.97;
    
    public static class _Point{
        
        double x,y;
        public _Point() {
            x=y=0;
        }
        public _Point(double x,double y){
            this.x=x;
            this.y=y;
        }
        public _Point(_Point p){
            x=p.x;
            y=p.y;
        }
        
    }
    _Point head;
    _Point nossel;
    int xLen;
    int yLen;
    double xVel;
    boolean leftAccel;
    boolean rightAccel;
    int leftLimit;
    int rightLimit;
    
    Image shooterRest;
    Image shooterInMotion;
    JPanel panel;
    
    
    public Shooter(_Point head,int leftLimit,int rightLimit,JPanel panel){
        this.panel=panel;
        this.head=head;
        this.head.y=400;
        xLen=250;
        yLen=250;
        leftAccel=rightAccel=false;
        xVel=0;
        this.leftLimit=leftLimit;
        this.rightLimit=rightLimit;
        shooterRest=Toolkit.getDefaultToolkit().getImage("C:\\Users\\Dev Parzival\\Pictures\\Shooter.jpg");
        
        shooterInMotion=Toolkit.getDefaultToolkit().getImage("C:\\Users\\Dev Parzival\\Pictures\\ShooterMotion.jpg");
    }
        
    public void drawShooter(Graphics g){
        if(!leftAccel && !rightAccel)
            g.drawImage(shooterRest,(int)head.x,400,250,250,Color.black,panel);
        else
            g.drawImage(shooterInMotion,(int)head.x,400,250,250,Color.black,panel);
    }
    public void setLeftAccel(boolean value){
        leftAccel=value;
    }
    public void setRightAccel(boolean value){
        rightAccel=value;
    }
    public void move(){
        
        if(leftAccel){
            xVel-=2.5;
        }
        else
            if(rightAccel){
                xVel+=2.5;
            }
        else
                if(!leftAccel && !rightAccel){
                    xVel*=GRAVITY;
                }
        if(xVel>7)
            xVel=7;
        if(xVel<-7){
            xVel=-7;
        }
        head.x+=xVel;
        if(head.x<leftLimit){
            head.x=leftLimit;
        }
        if(head.x+xLen>rightLimit){
            head.x=rightLimit-xLen;
        }
           
    }
}

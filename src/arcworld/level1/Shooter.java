package arcworld.level1;

import java.awt.Graphics;
import java.awt.Point;
import java.util.Random;

/**
 *
 * @author Dev Parzival
 */
public class Shooter{
    Point top;
    Point bottom_left;
    Point bottom_right;
    Random rnd=new Random();
    int height;
    int base;
    
    double xVel;
    
    boolean rightAccel;
    boolean leftAccel;
    
    static final double GRAVITY=0;
    
    int tail_length;
    
    int border=700;
    
    public Shooter(){
       top=new Point(0,0);
       bottom_left=new Point(0,0);
       bottom_right=new Point(0,0);
    }
    public Shooter(Point top,Point bottom_left,Point bottom_right){
        this.top=top;
        this.bottom_left=bottom_left;
        this.bottom_right=bottom_right;
        
        height=Math.abs(bottom_left.y-top.y);
        base=Math.abs(bottom_left.x-bottom_right.x);
       
        xVel=0;
        rightAccel=leftAccel=false;
    }
    public void move(){
        
        if(rightAccel){
            xVel+=1.7;
        }
        else
            if(leftAccel){
                xVel-=1.7;
            }
            else{
                xVel*=GRAVITY;
            }
        
        if(xVel<-10){
            xVel=-10;
        }
        if(xVel>10){
            xVel=10;
        }
        
        top.x+=xVel;
        bottom_right.x+=xVel;
        bottom_left.x+=xVel;
        
        
        if(bottom_left.x<=0){
            bottom_left.x=0;
            top.x=base/2;
            bottom_right.x=base;
        }
        
        if(bottom_right.x>=700){
            bottom_right.x=700;
            top.x=bottom_right.x-base/2;
            bottom_left.x=bottom_right.x-base;
        }
    }
    public void setRightAccel(boolean input){
        rightAccel = input;
    }
    public void setLeftAccel(boolean input){
        leftAccel=input;
    }
    public void drawShooter(Graphics g){
        
        g.drawLine(top.x, top.y, bottom_left.x, bottom_left.y);
        g.drawLine(bottom_left.x, bottom_left.y,bottom_right.x, bottom_right.y);
        g.drawLine(bottom_right.x, bottom_right.y, top.x, top.y);
        
//        g.drawLine(top.x,bottom_right.y,top.x,bottom_right.y+tail_length);
    }
    public void initTail(){
        tail_length=Math.abs(rnd.nextInt())%16;
    }
}

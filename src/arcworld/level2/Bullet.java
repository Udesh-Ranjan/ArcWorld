package arcworld.level2;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author Dev Parzival
 * @date   28-Mar-2020
 * @time   11:26:13
 */
public class Bullet {

    static class _Point{
        double x;
        double y;

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
    static final int LENGTH=10;
    
    Graphics g;
    _Point head;
    _Point tail;

    /**
     *
     * @param head Denotes the head of the bullet.
     * @param tail Denotes the tail of the bullet.
     * @param g    Graphics object belongs to an Image where the bullet will be drawn when drawBullet method is called.
     */
    public Bullet(_Point head,_Point tail,Graphics g) {
        this.head=new _Point(head);
        this.tail=new _Point(tail);
        this.g=g;
    }
    public void move(){
        double step=2.5;
        
        //Straight
        if(((int)head.x==(int)tail.x&&head.y<tail.y)){
            
            System.out.println("#1");
            
            head.y-=step;
            tail.y-=step;
        }
        else
            if(head.x>tail.x&&head.y<tail.y){
                double delta_y=Math.abs(head.y-tail.y);
                double delta_x=Math.abs(head.x-tail.x);
                
                if(delta_x<=delta_y){
            
                    System.out.println("#2");

//                    if(head.x-tail.x<=4){
//                        step*=0.7;
//                    }

                    head.x+=step;
                    head.y-=(delta_y/delta_x)*step;
                    
                    tail.x+=step;
                    tail.y-=(delta_y/delta_x)*step;
                }
                else{
                    
                    System.out.println("#3");
                    
//                    if(tail.y-head.y<=7){
//                        step*=0.8;
//                    }

                    head.y-=step;
                    head.x+=(delta_x/delta_y)*step;

                    tail.y-=step;
                    tail.x+=(delta_x/delta_y)*step;

                }
            }
        else
            if (head.x>tail.x && (int)head.y==(int)tail.y) {
                
                System.out.println("#4");
            
                head.x+=step;
                tail.x+=step;
            }
            else
                if(head.y>tail.y && head.x>tail.x){
                    double delta_y=Math.abs(head.y-tail.y);
                    double delta_x=Math.abs(head.x-tail.x);

                    if(delta_x<=delta_y){
                       System.out.println("#5");
                       
//                       if(head.x-tail.x<=7){
//                           step*=0.8;
//                       }
                       
                       head.x+=step;
                       head.y+=(delta_y/delta_x)*step;
                       
                        tail.x+=step;
                        tail.y+=(delta_y/delta_x)*step;

                    }
                    else{
                        
                        System.out.println("#6");
                        
//                        if(head.y-tail.y<=9){
//                            step*=0.8;
//                        }
                
                        head.y+=step;
                        head.x+=(delta_x/delta_y)*step;

                        tail.y+=step;
                        tail.x+=(delta_x/delta_y)*step;

                    }
                }
                else
                    if((int)head.x==(int)tail.x && head.y>tail.y){
                        
                        System.out.println("#7");
            
                        head.y+=step;
                        tail.y+=step;
                    }
                    else
                        if(head.x<tail.x && head.y>tail.y){
                            double delta_y=Math.abs(head.y-tail.y);
                            double delta_x=Math.abs(head.x-tail.x);
                            
                            if(delta_x<=delta_y){
                                
                                System.out.println("#8");
            
//                                if(tail.x-head.x<=6){
//                                    step*=0.8;
//                                }

                                head.x-=step;
                                head.y+=(delta_y/delta_x)*step;

                                 tail.x-=step;
                                 tail.y+=(delta_y/delta_x)*step;

                            }
                        
                            else{
                                
                                System.out.println("#9");
            
//                                if(head.y-tail.y<=4){
//                                    step*=0.8;
//                                }

                                head.y+=step;
                                head.x-=(delta_x/delta_y)*step;

                                tail.y+=step;
                                tail.x-=(delta_x/delta_y)*step;

                            }
                        }
                        else               
                            if (head.x<tail.x && (int)head.y==(int)tail.y) {
                                
                                System.out.println("#10");
            
                                head.x-=step;
                                tail.x-=step;
                            }
                            else{
                                double delta_y=Math.abs(head.y-tail.y);
                                double delta_x=Math.abs(head.x-tail.x);

                                if(delta_x<=delta_y){
                                    
                                    System.out.println("#11");
            
//                                    if(tail.x-head.x<=7){
//                                        step*=0.8;
//                                    }
                                    
                                    head.x-=step;
                                    head.y-=(delta_y/delta_x)*step;

                                     tail.x-=step;
                                     tail.y-=(delta_y/delta_x)*step;

                                }
                                else{
                                    
                                    System.out.println("#12");
            
//                                    if(tail.y-head.y<=7){
//                                        step*=0.8;
//                                    }
                                    
                                    head.y-=step;
                                    head.x-=(delta_x/delta_y)*step;

                                    tail.y-=step;
                                    tail.x-=(delta_x/delta_y)*step;

                                }
                            }
    }
    public void drawBullet(){
        g.setColor(Color.white);
        drawLine(head,tail);
    }
    public void drawLine(_Point p1,_Point p2){
        g.drawLine((int)p1.x,(int)p1.y,(int)p2.x,(int)p2.y);
    }
    //////////Main Method///////////
    public static void main(String[] args) {
        
    }
    
}



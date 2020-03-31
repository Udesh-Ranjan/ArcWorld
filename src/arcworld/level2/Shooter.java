package arcworld.level2;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 *
 * @author Dev Parzival
 */
public class Shooter{

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
            return "x= "+x+" "+"y= "+y+"\n";
        }
    }
    
    static final double LENGTH=50;
    static final double GRAVITY=0.98;
    boolean upKey;
    BufferedImage img;
    Graphics g;
    _Point cockpit;
    _Point tail;
    _Point wing1;
    _Point wing2;
    
    _Point initialCokpit;                                                       //initialCockpit is used to rset the cockpit.
    
    static int sleep=10;
    volatile double velocityStep;
    
    float length;
    float wingSeperation;
    
    boolean enabled=false;
    
    @Override
    public String toString(){
        
        String str="";
        
        str+="cockpit "+cockpit;
        str+="tail "+tail;
        str+="wing1 "+wing1;
        str+="wing2 "+wing2;
        str+="velocityChange "+velocityStep;
        str+="\n-------------------------------\n";
        return str;
    }
    
    /**
     *
     * @param cockpit front portion of the Jet.
     * @param length  Vertical separation b/w tail.y & cockpit.y.
     */
    public Shooter(_Point cockpit,float length){
        this.cockpit=cockpit;
        this.length=length;
        this.wingSeperation=length/2;
        tail=new _Point(cockpit.x,cockpit.y+length);
        
        wing1=new _Point(tail.x-wingSeperation*2,tail.y);
        wing2=new _Point(tail.x+wingSeperation*2,tail.y);
        
        initialCokpit=new _Point(cockpit);
        
        velocityStep=0;
        upKey=false;
        img=new BufferedImage(1000, 700, BufferedImage.TYPE_4BYTE_ABGR);
        g=img.getGraphics();
        drawShooter(g);
        
    }

    /**
     *
     * @param cockpit   front portion of the Jet.
     * @param length    Vertical separation b/w tail.y & cockpit.y. 
     * @param img       BufferedImage.
     * @param g         Graphics object for drawing on image.
     */
    public Shooter(_Point cockpit,float length,BufferedImage img,Graphics g){
        this.cockpit=cockpit;
        this.length=length;
        this.wingSeperation=length/2;
        tail=new _Point(cockpit.x,cockpit.y+length);
        this.img=img;
        this.g=g;
        wing1=new _Point(tail.x-wingSeperation*2,tail.y);
        wing2=new _Point(tail.x+wingSeperation*2,tail.y);
        
        initialCokpit=new _Point(cockpit);
        
        velocityStep=0;
        upKey=false;
        drawShooter(g);
    }
    
    public void drawShooter(Graphics g){
        
        g.setColor(Color.red);
        g.drawLine((int)cockpit.x,(int)cockpit.y,(int)tail.x, (int)tail.y);
        
        if(enabled)
            g.setColor(Color.white);
        else
            g.setColor(Color.green);
        
        g.drawLine((int)wing1.x,(int)wing1.y,(int)tail.x, (int)tail.y);
        g.drawLine((int)wing1.x,(int)wing1.y,(int)cockpit.x, (int)cockpit.y);

        g.drawLine((int)wing2.x,(int)wing2.y,(int)tail.x, (int)tail.y);
        g.drawLine((int)wing2.x,(int)wing2.y,(int)cockpit.x, (int)cockpit.y);
                
    }
    /////////Reset the Cockpit///////
    public void resetShooter(){
        cockpit=new _Point(initialCokpit);
        tail=new _Point(cockpit.x,cockpit.y+length);
        wing1=new _Point(tail.x-wingSeperation*2,tail.y);
        wing2=new _Point(tail.x+wingSeperation*2,tail.y);
    }
    /////////Moves Shooter/////////
    public void move(){
        
        if(!enabled)
            return;
        
        if(upKey){
            velocityStep+=0.1;
        }
        else{
            velocityStep*=GRAVITY;
        }
        
        if(velocityStep>2.5){
            velocityStep=2.5;
        }
        double changeLimit=velocityStep+1.5;
        double cons=0.9999;
        
        _Point curr_cockpit=new _Point(cockpit);
        //Straight
        if(((int)cockpit.x==(int)tail.x&&cockpit.y<tail.y)){
            
            System.out.println("#1");
            
            cockpit.y-=velocityStep;
            tail.y-=velocityStep;
            wing1.y-=velocityStep;
            wing2.y-=velocityStep;
        }
        else
            if(cockpit.x>tail.x&&cockpit.y<tail.y){
                double delta_y=Math.abs(cockpit.y-tail.y);
                double delta_x=Math.abs(cockpit.x-tail.x);
                
                if(delta_x<=delta_y){
            
                    System.out.println("#2");
                    
                    curr_cockpit.x+=velocityStep;
                    curr_cockpit.y-=(delta_y/delta_x)*velocityStep;
                    
                    while(Math.abs(curr_cockpit.y-cockpit.y)>changeLimit){
                        velocityStep*=cons;
                        
                        curr_cockpit.x=cockpit.x;
                        curr_cockpit.y=cockpit.y;
                        
                        curr_cockpit.x+=velocityStep;
                        curr_cockpit.y-=(delta_y/delta_x)*velocityStep;
                    
                    }
                    cockpit.x+=velocityStep;
                    cockpit.y-=(delta_y/delta_x)*velocityStep;
                    
                    tail.x+=velocityStep;
                    tail.y-=(delta_y/delta_x)*velocityStep;
                    
                    wing1.x+=velocityStep;
                    wing1.y-=(delta_y/delta_x)*velocityStep;
                    
                    wing2.x+=velocityStep;
                    wing2.y-=(delta_y/delta_x)*velocityStep;
                    
                }
                else{
                    
                    System.out.println("#3");
            
                    curr_cockpit.y-=velocityStep;
                    curr_cockpit.x+=(delta_x/delta_y)*velocityStep;
                    
                    while(Math.abs(curr_cockpit.x-cockpit.x)>changeLimit){
                        velocityStep*=cons;
                        
                        curr_cockpit.x=cockpit.x;
                        curr_cockpit.y=cockpit.y;
                        
                        curr_cockpit.y-=velocityStep;
                        curr_cockpit.x+=(delta_x/delta_y)*velocityStep;
                    
                    }
                    
                    cockpit.y-=velocityStep;
                    cockpit.x+=(delta_x/delta_y)*velocityStep;

                    tail.y-=velocityStep;
                    tail.x+=(delta_x/delta_y)*velocityStep;

                    wing1.y-=velocityStep;
                    wing1.x+=(delta_x/delta_y)*velocityStep;

                    wing2.y-=velocityStep;
                    wing2.x+=(delta_x/delta_y)*velocityStep;
                    
                }

            }
        else
            if (cockpit.x>tail.x && (int)cockpit.y==(int)tail.y) {
                
                System.out.println("#4");
            
                cockpit.x+=velocityStep;
                tail.x+=velocityStep;
                wing1.x+=velocityStep;
                wing2.x+=velocityStep;
            }
            else
                if(cockpit.y>tail.y && cockpit.x>tail.x){
                    double delta_y=Math.abs(cockpit.y-tail.y);
                    double delta_x=Math.abs(cockpit.x-tail.x);

                    if(delta_x<=delta_y){
                        
                        
                       System.out.println("#5");
                       
                       curr_cockpit.x+=velocityStep;
                       curr_cockpit.y+=(delta_y/delta_x)*velocityStep;
                       
                       while(Math.abs(curr_cockpit.y-cockpit.y)>changeLimit){
                           velocityStep*=cons;
                           
                           curr_cockpit.x=cockpit.x;
                           curr_cockpit.y=cockpit.y;
                           
                           curr_cockpit.x+=velocityStep;
                           curr_cockpit.y+=(delta_y/delta_x)*velocityStep;

                       }
                       
                       cockpit.x+=velocityStep;
                       cockpit.y+=(delta_y/delta_x)*velocityStep;
                       
                       tail.x+=velocityStep;
                       tail.y+=(delta_y/delta_x)*velocityStep;

                       wing1.x+=velocityStep;
                       wing1.y+=(delta_y/delta_x)*velocityStep;

                       wing2.x+=velocityStep;
                       wing2.y+=(delta_y/delta_x)*velocityStep;

                    }
                    else{
                        
                        System.out.println("#6");
                        
                        curr_cockpit.y+=velocityStep;
                        curr_cockpit.x+=(delta_x/delta_y)*velocityStep;
                        
                        while(Math.abs(curr_cockpit.x-cockpit.x)>changeLimit){
                            velocityStep*=cons;
                            
                            curr_cockpit.x=cockpit.x;
                            curr_cockpit.y=cockpit.y;
                            
                            curr_cockpit.y+=velocityStep;
                            curr_cockpit.x+=(delta_x/delta_y)*velocityStep;
                        
                        }
                        cockpit.y+=velocityStep;
                        cockpit.x+=(delta_x/delta_y)*velocityStep;

                        tail.y+=velocityStep;
                        tail.x+=(delta_x/delta_y)*velocityStep;

                        wing1.y+=velocityStep;
                        wing1.x+=(delta_x/delta_y)*velocityStep;

                        wing2.y+=velocityStep;
                        wing2.x+=(delta_x/delta_y)*velocityStep;
                        
                    }
                }
                else
                    if((int)cockpit.x==(int)tail.x && cockpit.y>tail.y){
                        
                        System.out.println("#7");
            
                        cockpit.y+=velocityStep;
                        tail.y+=velocityStep;
                        wing1.y+=velocityStep;
                        wing2.y+=velocityStep;     
                    }
                    else
                        if(cockpit.x<tail.x && cockpit.y>tail.y){
                            double delta_y=Math.abs(cockpit.y-tail.y);
                            double delta_x=Math.abs(cockpit.x-tail.x);
                            
                            if(delta_x<=delta_y){
                                
                                System.out.println("#8");
                                
                                curr_cockpit.x-=velocityStep;
                                curr_cockpit.y+=(delta_y/delta_x)*velocityStep;
                                
                                while(Math.abs(curr_cockpit.y-cockpit.y)>changeLimit){
                                    velocityStep*=cons;
                                    
                                    curr_cockpit.x=cockpit.x;
                                    curr_cockpit.y=cockpit.y;
                                    
                                    curr_cockpit.x-=velocityStep;
                                    curr_cockpit.y+=(delta_y/delta_x)*velocityStep;
                                
                                }
                                
                                cockpit.x-=velocityStep;
                                cockpit.y+=(delta_y/delta_x)*velocityStep;

                                tail.x-=velocityStep;
                                tail.y+=(delta_y/delta_x)*velocityStep;

                                wing1.x-=velocityStep;
                                wing1.y+=(delta_y/delta_x)*velocityStep;

                                wing2.x-=velocityStep;
                                wing2.y+=(delta_y/delta_x)*velocityStep;
                                
                            }
                            else{
                                
                                System.out.println("#9");
                                
                                curr_cockpit.y+=velocityStep;
                                curr_cockpit.x-=(delta_x/delta_y)*velocityStep;

                                while(Math.abs(curr_cockpit.x-cockpit.x)>changeLimit){
                                    velocityStep*=cons;
                                    
                                    curr_cockpit.x=cockpit.x;
                                    curr_cockpit.y=cockpit.y;
                                    
                                    curr_cockpit.y+=velocityStep;
                                    curr_cockpit.x-=(delta_x/delta_y)*velocityStep;
                                
                                }
                                cockpit.y+=velocityStep;
                                cockpit.x-=(delta_x/delta_y)*velocityStep;

                                tail.y+=velocityStep;
                                tail.x-=(delta_x/delta_y)*velocityStep;

                                wing1.y+=velocityStep;
                                wing1.x-=(delta_x/delta_y)*velocityStep;

                                wing2.y+=velocityStep;
                                wing2.x-=(delta_x/delta_y)*velocityStep;
                                
                            }
                        }
                        else               
                            if (cockpit.x<tail.x && (int)cockpit.y==(int)tail.y) {
                                
                                System.out.println("#10");
            
                                cockpit.x-=velocityStep;
                                tail.x-=velocityStep;
                                wing1.x-=velocityStep;
                                wing2.x-=velocityStep;
                            }
                            else{
                                double delta_y=Math.abs(cockpit.y-tail.y);
                                double delta_x=Math.abs(cockpit.x-tail.x);

                                if(delta_x<=delta_y){
                                    
                                    System.out.println("#11");
            
                                    curr_cockpit.x-=velocityStep;
                                    curr_cockpit.y-=(delta_y/delta_x)*velocityStep;
                                    
                                    while(Math.abs(curr_cockpit.y-cockpit.y)>changeLimit){
                                        velocityStep*=cons;
                                        
                                        curr_cockpit.x=cockpit.x;
                                        curr_cockpit.y=cockpit.y;
                                        
                                        curr_cockpit.x-=velocityStep;
                                        curr_cockpit.y-=(delta_y/delta_x)*velocityStep;
                                    
                                    }
                                    cockpit.x-=velocityStep;
                                    cockpit.y-=(delta_y/delta_x)*velocityStep;

                                     tail.x-=velocityStep;
                                     tail.y-=(delta_y/delta_x)*velocityStep;

                                     wing1.x-=velocityStep;
                                     wing1.y-=(delta_y/delta_x)*velocityStep;

                                     wing2.x-=velocityStep;
                                     wing2.y-=(delta_y/delta_x)*velocityStep;
                                     
                                }
                                else{
                                    
                                    System.out.println("#12");
                                    
                                    curr_cockpit.y-=velocityStep;
                                    curr_cockpit.x-=(delta_x/delta_y)*velocityStep;

                                    while(Math.abs(curr_cockpit.x-cockpit.x)>changeLimit){
                                        velocityStep*=cons;
                                        
                                        curr_cockpit.x=cockpit.x;
                                        curr_cockpit.y=cockpit.y;
                                        
                                        curr_cockpit.y-=velocityStep;
                                        curr_cockpit.x-=(delta_x/delta_y)*velocityStep;
                                    
                                    }
                                    
                                    cockpit.y-=velocityStep;
                                    cockpit.x-=(delta_x/delta_y)*velocityStep;

                                    tail.y-=velocityStep;
                                    tail.x-=(delta_x/delta_y)*velocityStep;

                                    wing1.y-=velocityStep;
                                    wing1.x-=(delta_x/delta_y)*velocityStep;

                                    wing2.y-=velocityStep;
                                    wing2.x-=(delta_x/delta_y)*velocityStep;
                                    
                                }
                            }
        resetOrientaionOnOutofBound();
    }
    /**
     * reset the Shooter position if the Shooter has gone out of screen.
    */
    public void resetOrientaionOnOutofBound(){
        int width=img.getWidth();
        int height=img.getHeight();
        
        /////////////Top/////////////
        if(cockpit.y<=0 && tail.y<=0){
            double increaseHeight=height-tail.y;
            cockpit.y+=increaseHeight;
            tail.y+=increaseHeight;
            wing1.y+=increaseHeight;
            wing2.y+=increaseHeight;
            
        }
        /////////////Bottom/////////////
        if(cockpit.y>=height && tail.y>=height){
            double decreaseHeight=cockpit.y-4;
            cockpit.y-=decreaseHeight;
            tail.y-=decreaseHeight;
            wing1.y-=decreaseHeight;
            wing2.y-=decreaseHeight;
            
        }
        /////////////Right/////////////
        if(cockpit.x>=width && tail.x>=width){
            double decreaseWidth=cockpit.x-5;
            cockpit.x-=decreaseWidth;
            tail.x-=decreaseWidth;
            wing1.x-=decreaseWidth;
            wing2.x-=decreaseWidth;
        }
        /////////////Left/////////////
        if(cockpit.x<=0 && tail.x<=0){
            double increaseWidth=width-cockpit.x;
            cockpit.x+=increaseWidth;
            tail.x+=increaseWidth;
            wing1.x+=increaseWidth;
            wing2.x+=increaseWidth;
        }
    }

    /**
     *
     * @param   p1 of type _Point
     * @param   p2 of type _Point
     * @return  returns distance b/w them.
     */
    public double getDistance(_Point p1,_Point p2){
        return Math.pow((p1.y-p2.y)*(p1.y-p2.y)+(p1.x-p2.x)*(p1.x-p2.x),0.5);
    }
    /////////Dispose////////
    public void dispose(){
        img=null;
        g=null;
        cockpit=null;
        tail=null;
        wing1=wing2=null;
    }
}

package level2;

import java.awt.Color;
import java.awt.Graphics;
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
            return "x= "+x+" "+"y= "+y+"\n";
        }
    }
    
    static final double GRAVITY=0.5;
    volatile boolean upKey;
    BufferedImage img;
    Graphics g;
    volatile _Point cockpit;
    volatile _Point tail;
    volatile _Point wing1;
    volatile _Point wing2;
    static int sleep=10;
    volatile double velocityStep;
    
    float length;
    float wingSeperation;
    
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
     * @param length  distance b/w the cockpit and centre of the wing.
     */
    public Shooter(_Point cockpit,float length){
        this.cockpit=cockpit;
        this.length=length;
        this.wingSeperation=length/2;
        tail=new _Point(cockpit.x,cockpit.y+length);
        
        wing1=new _Point(tail.x-wingSeperation*2,tail.y);
        wing2=new _Point(tail.x+wingSeperation*2,tail.y);
        
        velocityStep=0;
        upKey=false;
        setSize(1000, 700);
        img=new BufferedImage(1000, 700, BufferedImage.TYPE_4BYTE_ABGR);
        g=img.getGraphics();
        drawShooter(g);
        
    }
    
    public void drawShooter(Graphics g){
        g.setColor(Color.red);
        g.drawLine((int)cockpit.x,(int)cockpit.y,(int)tail.x, (int)tail.y);
        
        g.setColor(Color.white);
        
        g.drawLine((int)wing1.x,(int)wing1.y,(int)tail.x, (int)tail.y);
        g.drawLine((int)wing1.x,(int)wing1.y,(int)cockpit.x, (int)cockpit.y);

        g.drawLine((int)wing2.x,(int)wing2.y,(int)tail.x, (int)tail.y);
        g.drawLine((int)wing2.x,(int)wing2.y,(int)cockpit.x, (int)cockpit.y);
        
        repaint();
    }
    
    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        
        if(e.getKeyCode()==KeyEvent.VK_ESCAPE){
            System.out.println(this);
        }
        
        
        if(e.getKeyCode()==KeyEvent.VK_RIGHT){
            int step=1;
            //      |
            if((int)cockpit.x==(int)tail.x && cockpit.y<tail.y){
                System.out.println("Changing X");
                //Changing X
                cockpit.x+=step;
                double val=length*length-(cockpit.x-tail.x)*(cockpit.x-tail.x);
                int sign=(val>=0?1:-1);
                val*=sign;
                cockpit.y=tail.y-(Math.pow(val, 0.5)*sign);
            }
            else//      /
                if(cockpit.x>tail.x && cockpit.y < tail.y){
                    double delta_x=Math.abs(cockpit.x-tail.x);
                    double delta_y=Math.abs(cockpit.y-tail.y);
                    
                    if(delta_x<=delta_y){
                        System.out.println("Changing X");
                        //Changing X
                        cockpit.x+=step;
                        double val=length*length-(cockpit.x-tail.x)*(cockpit.x-tail.x);
                        int sign=(val>=0?1:-1);
                        val*=sign;
                        cockpit.y=tail.y-(Math.pow(val, 0.5)*sign);
                    }
                    else{
                        //Changing Y
                        System.out.println("Changing Y");
                        cockpit.y+=step;
                        double val=length*length-(cockpit.y-tail.y)*(cockpit.y-tail.y);
                        int sign=(val>=0?1:-1);
                        val*=sign;
                        cockpit.x=Math.pow(val,0.5)*sign+tail.x;
                    }
                }
            else
                    if((int)cockpit.y==(int)tail.y && cockpit.x>tail.x){
                        //Changing Y
                        System.out.println("Changing Y");
                        cockpit.y+=step;
                        double val=length*length-(cockpit.y-tail.y)*(cockpit.y-tail.y);
                        int sign=(val>=0?1:-1);
                        val*=sign;
                        cockpit.x=Math.pow(val,0.5)*sign+tail.x;
                    }
            else
                        if(cockpit.x>tail.x && cockpit.y>tail.y){
                            double delta_x=Math.abs(cockpit.x-tail.x);
                            double delta_y=Math.abs(cockpit.y-tail.y);
                            
                            if(delta_x<=delta_y){
                                //Changing X
                                System.out.println("Changing X");
                                cockpit.x-=step;
                                double val=length*length-(cockpit.x-tail.x)*(cockpit.x-tail.x);
                                int sign=(val>=0?1:-1);
                                val*=sign;
                                cockpit.y=Math.pow(val,0.5)*sign+tail.y;
                            }
                            else{
                                //Changing Y
                                System.out.println("Changing Y");
                                cockpit.y+=step;
                                double val=length*length-(cockpit.y-tail.y)*(cockpit.y-tail.y);
                                int sign=(val>=0?1:-1);
                                val*=sign;
                                cockpit.x=Math.pow(val,0.5)*sign+tail.x;
                            }
                            
                        }
            else
                            if((int)cockpit.x==(int)tail.x && cockpit.y>tail.y){
                                //Changing X
                                System.out.println("Changing X");
                                cockpit.x-=step;
                                double val=length*length-(cockpit.x-tail.x)*(cockpit.x-tail.x);
                                int sign=(val>=0?1:-1);
                                val*=sign;
                                cockpit.y=Math.pow(val,0.5)*sign+tail.y;
                            }
            else
                                if(tail.x>cockpit.x && cockpit.y>tail.y){
                                    double delta_x=Math.abs(cockpit.x-tail.x);
                                    double delta_y=Math.abs(cockpit.y-tail.y);
                                    
                                    if(delta_x <=delta_y){
                                        //Changing X
                                        System.out.println("Changing X");
                                        cockpit.x-=step;
                                        double val=length*length-(cockpit.x-tail.x)*(cockpit.x-tail.x);
                                        int sign=(val>=0?1:-1);
                                        val*=sign;
                                        cockpit.y=Math.pow(val,0.5)*sign+tail.y;
                                    }
                                    else{
                                        //Changing Y
                                        System.out.println("Changing Y");
                                        cockpit.y-=step;
                                        double val=length*length-(cockpit.y-tail.y)*(cockpit.y-tail.y);
                                        int sign=(val>=0?1:-1);
                                        val*=sign;
                                        cockpit.x=tail.x-Math.pow(val,0.5)*sign;
                                    }
                                }
            else
                                    if(cockpit.x<tail.x && (int)cockpit.y==(int)tail.y){
                                        //Changing Y
                                        System.out.println("Changing Y");
                                        cockpit.y-=step;
                                        double val=length*length-(cockpit.y-tail.y)*(cockpit.y-tail.y);
                                        int sign=(val>=0?1:-1);
                                        val*=sign;
                                        cockpit.x=tail.x-Math.pow(val,0.5)*sign;
                                    }
                                    else{
                                        double delta_x=Math.abs(cockpit.x-tail.x);
                                        double delta_y=Math.abs(cockpit.y-tail.y);
                                        
                                        if(delta_x<=delta_y){
                                            //Changing X
                                            cockpit.x+=step;
                                            double val=length*length-(cockpit.x-tail.x)*(cockpit.x-tail.x);
                                            int sign=(val>=0?1:-1);
                                            val*=sign;
                                            cockpit.y=tail.y-(Math.pow(val, 0.5)*sign);
                                        }
                                        else{
                                            //Changing Y
                                            System.out.println("Changing Y");
                                            cockpit.y-=step;
                                            double val=length*length-(cockpit.y-tail.y)*(cockpit.y-tail.y);
                                            int sign=(val>=0?1:-1);
                                            val*=sign;
                                            cockpit.x=tail.x-Math.pow(val,0.5)*sign;
                                        }
                                    }
            //xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx//
            
            if((int)wing1.x==(int)tail.x && wing1.y<tail.y){
                System.out.println("Changing X");
                //Changing X
                wing1.x+=step;
                double val=length*length-(wing1.x-tail.x)*(wing1.x-tail.x);
                int sign=(val>=0?1:-1);
                val*=sign;
                wing1.y=tail.y-(Math.pow(val, 0.5)*sign);
            }
            else//      /
                if(wing1.x>tail.x && wing1.y < tail.y){
                    double delta_x=Math.abs(wing1.x-tail.x);
                    double delta_y=Math.abs(wing1.y-tail.y);
                    
                    if(delta_x<=delta_y){
                        System.out.println("Changing X");
                        //Changing X
                        wing1.x+=step;
                        double val=length*length-(wing1.x-tail.x)*(wing1.x-tail.x);
                        int sign=(val>=0?1:-1);
                        val*=sign;
                        wing1.y=tail.y-(Math.pow(val, 0.5)*sign);
                    }
                    else{
                        //Changing Y
                        System.out.println("Changing Y");
                        wing1.y+=step;
                        double val=length*length-(wing1.y-tail.y)*(wing1.y-tail.y);
                        int sign=(val>=0?1:-1);
                        val*=sign;
                        wing1.x=Math.pow(val,0.5)*sign+tail.x;
                    }
                }
            else
                    if((int)wing1.y==(int)tail.y && wing1.x>tail.x){
                        //Changing Y
                        System.out.println("Changing Y");
                        wing1.y+=step;
                        double val=length*length-(wing1.y-tail.y)*(wing1.y-tail.y);
                        int sign=(val>=0?1:-1);
                        val*=sign;
                        wing1.x=Math.pow(val,0.5)*sign+tail.x;
                    }
            else
                        if(wing1.x>tail.x && wing1.y>tail.y){
                            double delta_x=Math.abs(wing1.x-tail.x);
                            double delta_y=Math.abs(wing1.y-tail.y);
                            
                            if(delta_x<=delta_y){
                                //Changing X
                                System.out.println("Changing X");
                                wing1.x-=step;
                                double val=length*length-(wing1.x-tail.x)*(wing1.x-tail.x);
                                int sign=(val>=0?1:-1);
                                val*=sign;
                                wing1.y=Math.pow(val,0.5)*sign+tail.y;
                            }
                            else{
                                //Changing Y
                                System.out.println("Changing Y");
                                wing1.y+=step;
                                double val=length*length-(wing1.y-tail.y)*(wing1.y-tail.y);
                                int sign=(val>=0?1:-1);
                                val*=sign;
                                wing1.x=Math.pow(val,0.5)*sign+tail.x;
                            }
                            
                        }
            else
                            if((int)wing1.x==(int)tail.x && wing1.y>tail.y){
                                //Changing X
                                System.out.println("Changing X");
                                wing1.x-=step;
                                double val=length*length-(wing1.x-tail.x)*(wing1.x-tail.x);
                                int sign=(val>=0?1:-1);
                                val*=sign;
                                wing1.y=Math.pow(val,0.5)*sign+tail.y;
                            }
            else
                                if(tail.x>wing1.x && wing1.y>tail.y){
                                    double delta_x=Math.abs(wing1.x-tail.x);
                                    double delta_y=Math.abs(wing1.y-tail.y);
                                    
                                    if(delta_x <=delta_y){
                                        //Changing X
                                        System.out.println("Changing X");
                                        wing1.x-=step;
                                        double val=length*length-(wing1.x-tail.x)*(wing1.x-tail.x);
                                        int sign=(val>=0?1:-1);
                                        val*=sign;
                                        wing1.y=Math.pow(val,0.5)*sign+tail.y;
                                    }
                                    else{
                                        //Changing Y
                                        System.out.println("Changing Y");
                                        wing1.y-=step;
                                        double val=length*length-(wing1.y-tail.y)*(wing1.y-tail.y);
                                        int sign=(val>=0?1:-1);
                                        val*=sign;
                                        wing1.x=tail.x-Math.pow(val,0.5)*sign;
                                    }
                                }
            else
                                    if(wing1.x<tail.x && (int)wing1.y==(int)tail.y){
                                        //Changing Y
                                        System.out.println("Changing Y");
                                        wing1.y-=step;
                                        double val=length*length-(wing1.y-tail.y)*(wing1.y-tail.y);
                                        int sign=(val>=0?1:-1);
                                        val*=sign;
                                        wing1.x=tail.x-Math.pow(val,0.5)*sign;
                                    }
                                    else{
                                        double delta_x=Math.abs(wing1.x-tail.x);
                                        double delta_y=Math.abs(wing1.y-tail.y);
                                        
                                        if(delta_x<=delta_y){
                                            //Changing X
                                            wing1.x+=step;
                                            double val=length*length-(wing1.x-tail.x)*(wing1.x-tail.x);
                                            int sign=(val>=0?1:-1);
                                            val*=sign;
                                            wing1.y=tail.y-(Math.pow(val, 0.5)*sign);
                                        }
                                        else{
                                            //Changing Y
                                            System.out.println("Changing Y");
                                            wing1.y-=step;
                                            double val=length*length-(wing1.y-tail.y)*(wing1.y-tail.y);
                                            int sign=(val>=0?1:-1);
                                            val*=sign;
                                            wing1.x=tail.x-Math.pow(val,0.5)*sign;
                                        }
                                    }
            //xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx//
            //xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx//
            
            if((int)wing2.x==(int)tail.x && wing2.y<tail.y){
                System.out.println("Changing X");
                //Changing X
                wing2.x+=step;
                double val=length*length-(wing2.x-tail.x)*(wing2.x-tail.x);
                int sign=(val>=0?1:-1);
                val*=sign;
                wing2.y=tail.y-(Math.pow(val, 0.5)*sign);
            }
            else//      /
                if(wing2.x>tail.x && wing2.y < tail.y){
                    double delta_x=Math.abs(wing2.x-tail.x);
                    double delta_y=Math.abs(wing2.y-tail.y);
                    
                    if(delta_x<=delta_y){
                        System.out.println("Changing X");
                        //Changing X
                        wing2.x+=step;
                        double val=length*length-(wing2.x-tail.x)*(wing2.x-tail.x);
                        int sign=(val>=0?1:-1);
                        val*=sign;
                        wing2.y=tail.y-(Math.pow(val, 0.5)*sign);
                    }
                    else{
                        //Changing Y
                        System.out.println("Changing Y");
                        wing2.y+=step;
                        double val=length*length-(wing2.y-tail.y)*(wing2.y-tail.y);
                        int sign=(val>=0?1:-1);
                        val*=sign;
                        wing2.x=Math.pow(val,0.5)*sign+tail.x;
                    }
                }
            else
                    if((int)wing2.y==(int)tail.y && wing2.x>tail.x){
                        //Changing Y
                        System.out.println("Changing Y");
                        wing2.y+=step;
                        double val=length*length-(wing2.y-tail.y)*(wing2.y-tail.y);
                        int sign=(val>=0?1:-1);
                        val*=sign;
                        wing2.x=Math.pow(val,0.5)*sign+tail.x;
                    }
            else
                        if(wing2.x>tail.x && wing2.y>tail.y){
                            double delta_x=Math.abs(wing2.x-tail.x);
                            double delta_y=Math.abs(wing2.y-tail.y);
                            
                            if(delta_x<=delta_y){
                                //Changing X
                                System.out.println("Changing X");
                                wing2.x-=step;
                                double val=length*length-(wing2.x-tail.x)*(wing2.x-tail.x);
                                int sign=(val>=0?1:-1);
                                val*=sign;
                                wing2.y=Math.pow(val,0.5)*sign+tail.y;
                            }
                            else{
                                //Changing Y
                                System.out.println("Changing Y");
                                wing2.y+=step;
                                double val=length*length-(wing2.y-tail.y)*(wing2.y-tail.y);
                                int sign=(val>=0?1:-1);
                                val*=sign;
                                wing2.x=Math.pow(val,0.5)*sign+tail.x;
                            }
                            
                        }
            else
                            if((int)wing2.x==(int)tail.x && wing2.y>tail.y){
                                //Changing X
                                System.out.println("Changing X");
                                wing2.x-=step;
                                double val=length*length-(wing2.x-tail.x)*(wing2.x-tail.x);
                                int sign=(val>=0?1:-1);
                                val*=sign;
                                wing2.y=Math.pow(val,0.5)*sign+tail.y;
                            }
            else
                                if(tail.x>wing2.x && wing2.y>tail.y){
                                    double delta_x=Math.abs(wing2.x-tail.x);
                                    double delta_y=Math.abs(wing2.y-tail.y);
                                    
                                    if(delta_x <=delta_y){
                                        //Changing X
                                        System.out.println("Changing X");
                                        wing2.x-=step;
                                        double val=length*length-(wing2.x-tail.x)*(wing2.x-tail.x);
                                        int sign=(val>=0?1:-1);
                                        val*=sign;
                                        wing2.y=Math.pow(val,0.5)*sign+tail.y;
                                    }
                                    else{
                                        //Changing Y
                                        System.out.println("Changing Y");
                                        wing2.y-=step;
                                        double val=length*length-(wing2.y-tail.y)*(wing2.y-tail.y);
                                        int sign=(val>=0?1:-1);
                                        val*=sign;
                                        wing2.x=tail.x-Math.pow(val,0.5)*sign;
                                    }
                                }
            else
                                    if(wing2.x<tail.x && (int)wing2.y==(int)tail.y){
                                        //Changing Y
                                        System.out.println("Changing Y");
                                        wing2.y-=step;
                                        double val=length*length-(wing2.y-tail.y)*(wing2.y-tail.y);
                                        int sign=(val>=0?1:-1);
                                        val*=sign;
                                        wing2.x=tail.x-Math.pow(val,0.5)*sign;
                                    }
                                    else{
                                        double delta_x=Math.abs(wing2.x-tail.x);
                                        double delta_y=Math.abs(wing2.y-tail.y);
                                        
                                        if(delta_x<=delta_y){
                                            //Changing X
                                            wing2.x+=step;
                                            double val=length*length-(wing2.x-tail.x)*(wing2.x-tail.x);
                                            int sign=(val>=0?1:-1);
                                            val*=sign;
                                            wing2.y=tail.y-(Math.pow(val, 0.5)*sign);
                                        }
                                        else{
                                            //Changing Y
                                            System.out.println("Changing Y");
                                            wing2.y-=step;
                                            double val=length*length-(wing2.y-tail.y)*(wing2.y-tail.y);
                                            int sign=(val>=0?1:-1);
                                            val*=sign;
                                            wing2.x=tail.x-Math.pow(val,0.5)*sign;
                                        }
                                    }
            //xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx//
                                        
        }
        
        if(e.getKeyCode()==KeyEvent.VK_UP){
            
            upKey=true;
        }
        
        System.out.println(this);
    }
    @Override
    public void keyReleased(KeyEvent e) {
        
        if(e.getKeyCode()==KeyEvent.VK_UP){
            upKey=false;
        }
    }
    /**
     *
     * @param p1    First _Point
     * @param p2    Second _Point
     * @return      Returns distance between p1 and p2
     */
    public double getDistance(_Point p1,_Point p2){
        return Math.pow((p1.y-p2.y)*(p1.y-p2.y)+(p1.x-p2.x)*(p1.x-p2.x),0.5);
    }
    public void move(){
        
        if(upKey){
            velocityStep+=0.01;
        }
        else{
            velocityStep*=GRAVITY;
//            velocityStep=0;
        }
        
        if(velocityStep>1){
            velocityStep=1;
        }
        
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

                    if(cockpit.x-tail.x<=4){
                        velocityStep*=0.7;
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
            
                    
                    if(tail.y-cockpit.y<=7){
                        velocityStep*=0.8;
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
                       
                       if(cockpit.x-tail.x<=7){
                           velocityStep*=0.8;
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
                        
                        if(cockpit.y-tail.y<=6){
                            velocityStep*=0.8;
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
            
                                
                                if(tail.x-cockpit.x<=6){
                                    velocityStep*=0.8;
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
            
                                if(cockpit.y-tail.y<=7){
                                    velocityStep*=0.8;
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
            
                                    if(tail.x-cockpit.x<=7){
                                        velocityStep*=0.8;
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
            
                                    if(tail.y-cockpit.y<=7){
                                        velocityStep*=0.8;
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
    }
    @Override
    public void paint(Graphics g){
        g.drawImage(img,0,0,this);
    }
    public void clearImage(){
        g.setColor(Color.black);
        g.fillRect(0, 0, getWidth(), getHeight());
    }
    static class Run implements Runnable{

        Shooter shooter;
        
        public Run(Shooter s) {
            shooter=s;
        }

        @Override
        public void run() {
            
            for(;;){
                
                shooter.clearImage();
                shooter.move();
                shooter.drawShooter(shooter.g);
                try{
                    Thread.sleep(sleep);
                }
                catch(InterruptedException ex){
                    ex.printStackTrace();
                }               
            }
        }
        
    }
    ////////Main Method/////////
    public static void main(String[] args) {        
        
        JFrame frame=new JFrame();
        frame.setSize(1000,700);
        frame.setLayout(null);
        Shooter panel=new Shooter(new _Point(500,350),50);
        frame.addKeyListener(panel);
        frame.requestFocus();
        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        
        Run run=new Run(panel);
        
        Thread t=new Thread(run);
        
        frame.setVisible(true);
        
         t.start();
    }

}

package arcworld.level2;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JPanel;
import static arcworld.level2.Shooter.GRAVITY;

/**
 *
 * @author Dev Parzival
 */
public abstract class Level2 extends JPanel implements Runnable,KeyListener{

    BufferedImage img;
    Graphics g;
    Astroid ast1,ast2,ast3;
    Shooter shooter;
    
    public Level2(JFrame frame) {
        this.setSize(frame.getSize());
        frame.addKeyListener(this);
        img=new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_4BYTE_ABGR);
        g=img.getGraphics();
        ast1=new Astroid(Astroid.map.get(1), (Graphics2D) g, this, img,this.getWidth()/2,this.getHeight()/2);
        ast2=new Astroid(Astroid.map.get(1), (Graphics2D) g, this, img,this.getWidth()/2,this.getHeight()/2);
        ast3=new Astroid(Astroid.map.get(1), (Graphics2D) g, this, img,this.getWidth()/2,this.getHeight()/2);
        
        shooter=new Shooter(new Shooter._Point(500,350),50, img, g);
    }

    
    @Override
    public void paint(Graphics g){
        g.drawImage(img, 0, 0, this);
    }
    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
            
        if(e.getKeyCode()==KeyEvent.VK_RIGHT){
            double step=4.5;
            //      |
            if((int)shooter.cockpit.x==(int)shooter.tail.x && shooter.cockpit.y<shooter.tail.y){
                System.out.println("Changing X");
                //Changing X
                shooter.cockpit.x+=step;
                double val=shooter.length*shooter.length-(shooter.cockpit.x-shooter.tail.x)*(shooter.cockpit.x-shooter.tail.x);
                int sign=(val>=0?1:-1);
                val*=sign;
                shooter.cockpit.y=shooter.tail.y-(Math.pow(val, 0.5)*sign);
            }
            else//      /
                if(shooter.cockpit.x>shooter.tail.x && shooter.cockpit.y < shooter.tail.y){
                    double delta_x=Math.abs(shooter.cockpit.x-shooter.tail.x);
                    double delta_y=Math.abs(shooter.cockpit.y-shooter.tail.y);
                    
                    if(delta_x<=delta_y){
                        System.out.println("Changing X");
                        //Changing X
                        shooter.cockpit.x+=step;
                        double val=shooter.length*shooter.length-(shooter.cockpit.x-shooter.tail.x)*(shooter.cockpit.x-shooter.tail.x);
                        int sign=(val>=0?1:-1);
                        val*=sign;
                        shooter.cockpit.y=shooter.tail.y-(Math.pow(val, 0.5)*sign);
                    }
                    else{
                        //Changing Y
                        System.out.println("Changing Y");
                        shooter.cockpit.y+=step;
                        double val=shooter.length*shooter.length-(shooter.cockpit.y-shooter.tail.y)*(shooter.cockpit.y-shooter.tail.y);
                        int sign=(val>=0?1:-1);
                        val*=sign;
                        shooter.cockpit.x=Math.pow(val,0.5)*sign+shooter.tail.x;
                    }
                }
            else
                    if((int)shooter.cockpit.y==(int)shooter.tail.y && shooter.cockpit.x>shooter.tail.x){
                        //Changing Y
                        System.out.println("Changing Y");
                        shooter.cockpit.y+=step;
                        double val=shooter.length*shooter.length-(shooter.cockpit.y-shooter.tail.y)*(shooter.cockpit.y-shooter.tail.y);
                        int sign=(val>=0?1:-1);
                        val*=sign;
                        shooter.cockpit.x=Math.pow(val,0.5)*sign+shooter.tail.x;
                    }
            else
                        if(shooter.cockpit.x>shooter.tail.x && shooter.cockpit.y>shooter.tail.y){
                            double delta_x=Math.abs(shooter.cockpit.x-shooter.tail.x);
                            double delta_y=Math.abs(shooter.cockpit.y-shooter.tail.y);
                            
                            if(delta_x<=delta_y){
                                //Changing X
                                System.out.println("Changing X");
                                shooter.cockpit.x-=step;
                                double val=shooter.length*shooter.length-(shooter.cockpit.x-shooter.tail.x)*(shooter.cockpit.x-shooter.tail.x);
                                int sign=(val>=0?1:-1);
                                val*=sign;
                                shooter.cockpit.y=Math.pow(val,0.5)*sign+shooter.tail.y;
                            }
                            else{
                                //Changing Y
                                System.out.println("Changing Y");
                                shooter.cockpit.y+=step;
                                double val=shooter.length*shooter.length-(shooter.cockpit.y-shooter.tail.y)*(shooter.cockpit.y-shooter.tail.y);
                                int sign=(val>=0?1:-1);
                                val*=sign;
                                shooter.cockpit.x=Math.pow(val,0.5)*sign+shooter.tail.x;
                            }
                            
                        }
            else
                            if((int)shooter.cockpit.x==(int)shooter.tail.x && shooter.cockpit.y>shooter.tail.y){
                                //Changing X
                                System.out.println("Changing X");
                                shooter.cockpit.x-=step;
                                double val=shooter.length*shooter.length-(shooter.cockpit.x-shooter.tail.x)*(shooter.cockpit.x-shooter.tail.x);
                                int sign=(val>=0?1:-1);
                                val*=sign;
                                shooter.cockpit.y=Math.pow(val,0.5)*sign+shooter.tail.y;
                            }
            else
                                if(shooter.tail.x>shooter.cockpit.x && shooter.cockpit.y>shooter.tail.y){
                                    double delta_x=Math.abs(shooter.cockpit.x-shooter.tail.x);
                                    double delta_y=Math.abs(shooter.cockpit.y-shooter.tail.y);
                                    
                                    if(delta_x <=delta_y){
                                        //Changing X
                                        System.out.println("Changing X");
                                        shooter.cockpit.x-=step;
                                        double val=shooter.length*shooter.length-(shooter.cockpit.x-shooter.tail.x)*(shooter.cockpit.x-shooter.tail.x);
                                        int sign=(val>=0?1:-1);
                                        val*=sign;
                                        shooter.cockpit.y=Math.pow(val,0.5)*sign+shooter.tail.y;
                                    }
                                    else{
                                        //Changing Y
                                        System.out.println("Changing Y");
                                        shooter.cockpit.y-=step;
                                        double val=shooter.length*shooter.length-(shooter.cockpit.y-shooter.tail.y)*(shooter.cockpit.y-shooter.tail.y);
                                        int sign=(val>=0?1:-1);
                                        val*=sign;
                                        shooter.cockpit.x=shooter.tail.x-Math.pow(val,0.5)*sign;
                                    }
                                }
            else
                                    if(shooter.cockpit.x<shooter.tail.x && (int)shooter.cockpit.y==(int)shooter.tail.y){
                                        //Changing Y
                                        System.out.println("Changing Y");
                                        shooter.cockpit.y-=step;
                                        double val=shooter.length*shooter.length-(shooter.cockpit.y-shooter.tail.y)*(shooter.cockpit.y-shooter.tail.y);
                                        int sign=(val>=0?1:-1);
                                        val*=sign;
                                        shooter.cockpit.x=shooter.tail.x-Math.pow(val,0.5)*sign;
                                    }
                                    else{
                                        double delta_x=Math.abs(shooter.cockpit.x-shooter.tail.x);
                                        double delta_y=Math.abs(shooter.cockpit.y-shooter.tail.y);
                                        
                                        if(delta_x<=delta_y){
                                            //Changing X
                                            shooter.cockpit.x+=step;
                                            double val=shooter.length*shooter.length-(shooter.cockpit.x-shooter.tail.x)*(shooter.cockpit.x-shooter.tail.x);
                                            int sign=(val>=0?1:-1);
                                            val*=sign;
                                            shooter.cockpit.y=shooter.tail.y-(Math.pow(val, 0.5)*sign);
                                        }
                                        else{
                                            //Changing Y
                                            System.out.println("Changing Y");
                                            shooter.cockpit.y-=step;
                                            double val=shooter.length*shooter.length-(shooter.cockpit.y-shooter.tail.y)*(shooter.cockpit.y-shooter.tail.y);
                                            int sign=(val>=0?1:-1);
                                            val*=sign;
                                            shooter.cockpit.x=shooter.tail.x-Math.pow(val,0.5)*sign;
                                        }
                                    }
            //xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx//
            
            if((int)shooter.wing1.x==(int)shooter.tail.x && shooter.wing1.y<shooter.tail.y){
                System.out.println("Changing X");
                //Changing X
                shooter.wing1.x+=step;
                double val=shooter.length*shooter.length-(shooter.wing1.x-shooter.tail.x)*(shooter.wing1.x-shooter.tail.x);
                int sign=(val>=0?1:-1);
                val*=sign;
                shooter.wing1.y=shooter.tail.y-(Math.pow(val, 0.5)*sign);
            }
            else//      /
                if(shooter.wing1.x>shooter.tail.x && shooter.wing1.y < shooter.tail.y){
                    double delta_x=Math.abs(shooter.wing1.x-shooter.tail.x);
                    double delta_y=Math.abs(shooter.wing1.y-shooter.tail.y);
                    
                    if(delta_x<=delta_y){
                        System.out.println("Changing X");
                        //Changing X
                        shooter.wing1.x+=step;
                        double val=shooter.length*shooter.length-(shooter.wing1.x-shooter.tail.x)*(shooter.wing1.x-shooter.tail.x);
                        int sign=(val>=0?1:-1);
                        val*=sign;
                        shooter.wing1.y=shooter.tail.y-(Math.pow(val, 0.5)*sign);
                    }
                    else{
                        //Changing Y
                        System.out.println("Changing Y");
                        shooter.wing1.y+=step;
                        double val=shooter.length*shooter.length-(shooter.wing1.y-shooter.tail.y)*(shooter.wing1.y-shooter.tail.y);
                        int sign=(val>=0?1:-1);
                        val*=sign;
                        shooter.wing1.x=Math.pow(val,0.5)*sign+shooter.tail.x;
                    }
                }
            else
                    if((int)shooter.wing1.y==(int)shooter.tail.y && shooter.wing1.x>shooter.tail.x){
                        //Changing Y
                        System.out.println("Changing Y");
                        shooter.wing1.y+=step;
                        double val=shooter.length*shooter.length-(shooter.wing1.y-shooter.tail.y)*(shooter.wing1.y-shooter.tail.y);
                        int sign=(val>=0?1:-1);
                        val*=sign;
                        shooter.wing1.x=Math.pow(val,0.5)*sign+shooter.tail.x;
                    }
            else
                        if(shooter.wing1.x>shooter.tail.x && shooter.wing1.y>shooter.tail.y){
                            double delta_x=Math.abs(shooter.wing1.x-shooter.tail.x);
                            double delta_y=Math.abs(shooter.wing1.y-shooter.tail.y);
                            
                            if(delta_x<=delta_y){
                                //Changing X
                                System.out.println("Changing X");
                                shooter.wing1.x-=step;
                                double val=shooter.length*shooter.length-(shooter.wing1.x-shooter.tail.x)*(shooter.wing1.x-shooter.tail.x);
                                int sign=(val>=0?1:-1);
                                val*=sign;
                                shooter.wing1.y=Math.pow(val,0.5)*sign+shooter.tail.y;
                            }
                            else{
                                //Changing Y
                                System.out.println("Changing Y");
                                shooter.wing1.y+=step;
                                double val=shooter.length*shooter.length-(shooter.wing1.y-shooter.tail.y)*(shooter.wing1.y-shooter.tail.y);
                                int sign=(val>=0?1:-1);
                                val*=sign;
                                shooter.wing1.x=Math.pow(val,0.5)*sign+shooter.tail.x;
                            }
                            
                        }
            else
                            if((int)shooter.wing1.x==(int)shooter.tail.x && shooter.wing1.y>shooter.tail.y){
                                //Changing X
                                System.out.println("Changing X");
                                shooter.wing1.x-=step;
                                double val=shooter.length*shooter.length-(shooter.wing1.x-shooter.tail.x)*(shooter.wing1.x-shooter.tail.x);
                                int sign=(val>=0?1:-1);
                                val*=sign;
                                shooter.wing1.y=Math.pow(val,0.5)*sign+shooter.tail.y;
                            }
            else
                                if(shooter.tail.x>shooter.wing1.x && shooter.wing1.y>shooter.tail.y){
                                    double delta_x=Math.abs(shooter.wing1.x-shooter.tail.x);
                                    double delta_y=Math.abs(shooter.wing1.y-shooter.tail.y);
                                    
                                    if(delta_x <=delta_y){
                                        //Changing X
                                        System.out.println("Changing X");
                                        shooter.wing1.x-=step;
                                        double val=shooter.length*shooter.length-(shooter.wing1.x-shooter.tail.x)*(shooter.wing1.x-shooter.tail.x);
                                        int sign=(val>=0?1:-1);
                                        val*=sign;
                                        shooter.wing1.y=Math.pow(val,0.5)*sign+shooter.tail.y;
                                    }
                                    else{
                                        //Changing Y
                                        System.out.println("Changing Y");
                                        shooter.wing1.y-=step;
                                        double val=shooter.length*shooter.length-(shooter.wing1.y-shooter.tail.y)*(shooter.wing1.y-shooter.tail.y);
                                        int sign=(val>=0?1:-1);
                                        val*=sign;
                                        shooter.wing1.x=shooter.tail.x-Math.pow(val,0.5)*sign;
                                    }
                                }
            else
                                    if(shooter.wing1.x<shooter.tail.x && (int)shooter.wing1.y==(int)shooter.tail.y){
                                        //Changing Y
                                        System.out.println("Changing Y");
                                        shooter.wing1.y-=step;
                                        double val=shooter.length*shooter.length-(shooter.wing1.y-shooter.tail.y)*(shooter.wing1.y-shooter.tail.y);
                                        int sign=(val>=0?1:-1);
                                        val*=sign;
                                        shooter.wing1.x=shooter.tail.x-Math.pow(val,0.5)*sign;
                                    }
                                    else{
                                        double delta_x=Math.abs(shooter.wing1.x-shooter.tail.x);
                                        double delta_y=Math.abs(shooter.wing1.y-shooter.tail.y);
                                        
                                        if(delta_x<=delta_y){
                                            //Changing X
                                            shooter.wing1.x+=step;
                                            double val=shooter.length*shooter.length-(shooter.wing1.x-shooter.tail.x)*(shooter.wing1.x-shooter.tail.x);
                                            int sign=(val>=0?1:-1);
                                            val*=sign;
                                            shooter.wing1.y=shooter.tail.y-(Math.pow(val, 0.5)*sign);
                                        }
                                        else{
                                            //Changing Y
                                            System.out.println("Changing Y");
                                            shooter.wing1.y-=step;
                                            double val=shooter.length*shooter.length-(shooter.wing1.y-shooter.tail.y)*(shooter.wing1.y-shooter.tail.y);
                                            int sign=(val>=0?1:-1);
                                            val*=sign;
                                            shooter.wing1.x=shooter.tail.x-Math.pow(val,0.5)*sign;
                                        }
                                    }
            //xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx//
            //xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx//
            
            if((int)shooter.wing2.x==(int)shooter.tail.x && shooter.wing2.y<shooter.tail.y){
                System.out.println("Changing X");
                //Changing X
                shooter.wing2.x+=step;
                double val=shooter.length*shooter.length-(shooter.wing2.x-shooter.tail.x)*(shooter.wing2.x-shooter.tail.x);
                int sign=(val>=0?1:-1);
                val*=sign;
                shooter.wing2.y=shooter.tail.y-(Math.pow(val, 0.5)*sign);
            }
            else//      /
                if(shooter.wing2.x>shooter.tail.x && shooter.wing2.y < shooter.tail.y){
                    double delta_x=Math.abs(shooter.wing2.x-shooter.tail.x);
                    double delta_y=Math.abs(shooter.wing2.y-shooter.tail.y);
                    
                    if(delta_x<=delta_y){
                        System.out.println("Changing X");
                        //Changing X
                        shooter.wing2.x+=step;
                        double val=shooter.length*shooter.length-(shooter.wing2.x-shooter.tail.x)*(shooter.wing2.x-shooter.tail.x);
                        int sign=(val>=0?1:-1);
                        val*=sign;
                        shooter.wing2.y=shooter.tail.y-(Math.pow(val, 0.5)*sign);
                    }
                    else{
                        //Changing Y
                        System.out.println("Changing Y");
                        shooter.wing2.y+=step;
                        double val=shooter.length*shooter.length-(shooter.wing2.y-shooter.tail.y)*(shooter.wing2.y-shooter.tail.y);
                        int sign=(val>=0?1:-1);
                        val*=sign;
                        shooter.wing2.x=Math.pow(val,0.5)*sign+shooter.tail.x;
                    }
                }
            else
                    if((int)shooter.wing2.y==(int)shooter.tail.y && shooter.wing2.x>shooter.tail.x){
                        //Changing Y
                        System.out.println("Changing Y");
                        shooter.wing2.y+=step;
                        double val=shooter.length*shooter.length-(shooter.wing2.y-shooter.tail.y)*(shooter.wing2.y-shooter.tail.y);
                        int sign=(val>=0?1:-1);
                        val*=sign;
                        shooter.wing2.x=Math.pow(val,0.5)*sign+shooter.tail.x;
                    }
            else
                        if(shooter.wing2.x>shooter.tail.x && shooter.wing2.y>shooter.tail.y){
                            double delta_x=Math.abs(shooter.wing2.x-shooter.tail.x);
                            double delta_y=Math.abs(shooter.wing2.y-shooter.tail.y);
                            
                            if(delta_x<=delta_y){
                                //Changing X
                                System.out.println("Changing X");
                                shooter.wing2.x-=step;
                                double val=shooter.length*shooter.length-(shooter.wing2.x-shooter.tail.x)*(shooter.wing2.x-shooter.tail.x);
                                int sign=(val>=0?1:-1);
                                val*=sign;
                                shooter.wing2.y=Math.pow(val,0.5)*sign+shooter.tail.y;
                            }
                            else{
                                //Changing Y
                                System.out.println("Changing Y");
                                shooter.wing2.y+=step;
                                double val=shooter.length*shooter.length-(shooter.wing2.y-shooter.tail.y)*(shooter.wing2.y-shooter.tail.y);
                                int sign=(val>=0?1:-1);
                                val*=sign;
                                shooter.wing2.x=Math.pow(val,0.5)*sign+shooter.tail.x;
                            }
                            
                        }
            else
                            if((int)shooter.wing2.x==(int)shooter.tail.x && shooter.wing2.y>shooter.tail.y){
                                //Changing X
                                System.out.println("Changing X");
                                shooter.wing2.x-=step;
                                double val=shooter.length*shooter.length-(shooter.wing2.x-shooter.tail.x)*(shooter.wing2.x-shooter.tail.x);
                                int sign=(val>=0?1:-1);
                                val*=sign;
                                shooter.wing2.y=Math.pow(val,0.5)*sign+shooter.tail.y;
                            }
            else
                                if(shooter.tail.x>shooter.wing2.x && shooter.wing2.y>shooter.tail.y){
                                    double delta_x=Math.abs(shooter.wing2.x-shooter.tail.x);
                                    double delta_y=Math.abs(shooter.wing2.y-shooter.tail.y);
                                    
                                    if(delta_x <=delta_y){
                                        //Changing X
                                        System.out.println("Changing X");
                                        shooter.wing2.x-=step;
                                        double val=shooter.length*shooter.length-(shooter.wing2.x-shooter.tail.x)*(shooter.wing2.x-shooter.tail.x);
                                        int sign=(val>=0?1:-1);
                                        val*=sign;
                                        shooter.wing2.y=Math.pow(val,0.5)*sign+shooter.tail.y;
                                    }
                                    else{
                                        //Changing Y
                                        System.out.println("Changing Y");
                                        shooter.wing2.y-=step;
                                        double val=shooter.length*shooter.length-(shooter.wing2.y-shooter.tail.y)*(shooter.wing2.y-shooter.tail.y);
                                        int sign=(val>=0?1:-1);
                                        val*=sign;
                                        shooter.wing2.x=shooter.tail.x-Math.pow(val,0.5)*sign;
                                    }
                                }
            else
                                    if(shooter.wing2.x<shooter.tail.x && (int)shooter.wing2.y==(int)shooter.tail.y){
                                        //Changing Y
                                        System.out.println("Changing Y");
                                        shooter.wing2.y-=step;
                                        double val=shooter.length*shooter.length-(shooter.wing2.y-shooter.tail.y)*(shooter.wing2.y-shooter.tail.y);
                                        int sign=(val>=0?1:-1);
                                        val*=sign;
                                        shooter.wing2.x=shooter.tail.x-Math.pow(val,0.5)*sign;
                                    }
                                    else{
                                        double delta_x=Math.abs(shooter.wing2.x-shooter.tail.x);
                                        double delta_y=Math.abs(shooter.wing2.y-shooter.tail.y);
                                        
                                        if(delta_x<=delta_y){
                                            //Changing X
                                            shooter.wing2.x+=step;
                                            double val=shooter.length*shooter.length-(shooter.wing2.x-shooter.tail.x)*(shooter.wing2.x-shooter.tail.x);
                                            int sign=(val>=0?1:-1);
                                            val*=sign;
                                            shooter.wing2.y=shooter.tail.y-(Math.pow(val, 0.5)*sign);
                                        }
                                        else{
                                            //Changing Y
                                            System.out.println("Changing Y");
                                            shooter.wing2.y-=step;
                                            double val=shooter.length*shooter.length-(shooter.wing2.y-shooter.tail.y)*(shooter.wing2.y-shooter.tail.y);
                                            int sign=(val>=0?1:-1);
                                            val*=sign;
                                            shooter.wing2.x=shooter.tail.x-Math.pow(val,0.5)*sign;
                                        }
                                    }
            //xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx//
                                        
        }
        
        if(e.getKeyCode()==KeyEvent.VK_UP){
            
            shooter.upKey=true;
        }
        
        if(e.getKeyCode()==KeyEvent.VK_F){
            //Create the Bullet
        }
    }   
    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode()==KeyEvent.VK_UP)
            shooter.upKey=false;
    }
    public void move(){
        
        if(shooter.upKey){
            shooter.velocityStep+=0.025;
        }
        else{
            shooter.velocityStep*=GRAVITY;
//            shooter.velocityStep=0;
        }
        
        if(shooter.velocityStep>2.5){
            shooter.velocityStep=2.5;
        }
        
        //Straight
        if(((int)shooter.cockpit.x==(int)shooter.tail.x&&shooter.cockpit.y<shooter.tail.y)){
            
            System.out.println("#1");
            
            shooter.cockpit.y-=shooter.velocityStep;
            shooter.tail.y-=shooter.velocityStep;
            shooter.wing1.y-=shooter.velocityStep;
            shooter.wing2.y-=shooter.velocityStep;
        }
        else
            if(shooter.cockpit.x>shooter.tail.x&&shooter.cockpit.y<shooter.tail.y){
                double delta_y=Math.abs(shooter.cockpit.y-shooter.tail.y);
                double delta_x=Math.abs(shooter.cockpit.x-shooter.tail.x);
                
                if(delta_x<=delta_y){
            
                    System.out.println("#2");

                    if(shooter.cockpit.x-shooter.tail.x<=4){
                        shooter.velocityStep*=0.7;
                    }

                    
                    shooter.cockpit.x+=shooter.velocityStep;
                    shooter.cockpit.y-=(delta_y/delta_x)*shooter.velocityStep;
                    
                    shooter.tail.x+=shooter.velocityStep;
                    shooter.tail.y-=(delta_y/delta_x)*shooter.velocityStep;
                    
                    shooter.wing1.x+=shooter.velocityStep;
                    shooter.wing1.y-=(delta_y/delta_x)*shooter.velocityStep;
                    
                    shooter.wing2.x+=shooter.velocityStep;
                    shooter.wing2.y-=(delta_y/delta_x)*shooter.velocityStep;
                    
                }
                else{
                    
                    System.out.println("#3");
            
                    
                    if(shooter.tail.y-shooter.cockpit.y<=7){
                        shooter.velocityStep*=0.8;
                    }

                    shooter.cockpit.y-=shooter.velocityStep;
                    shooter.cockpit.x+=(delta_x/delta_y)*shooter.velocityStep;

                    shooter.tail.y-=shooter.velocityStep;
                    shooter.tail.x+=(delta_x/delta_y)*shooter.velocityStep;

                    shooter.wing1.y-=shooter.velocityStep;
                    shooter.wing1.x+=(delta_x/delta_y)*shooter.velocityStep;

                    shooter.wing2.y-=shooter.velocityStep;
                    shooter.wing2.x+=(delta_x/delta_y)*shooter.velocityStep;
                }

            }
        else
            if (shooter.cockpit.x>shooter.tail.x && (int)shooter.cockpit.y==(int)shooter.tail.y) {
                
                System.out.println("#4");
            
                shooter.cockpit.x+=shooter.velocityStep;
                shooter.tail.x+=shooter.velocityStep;
                shooter.wing1.x+=shooter.velocityStep;
                shooter.wing2.x+=shooter.velocityStep;
            }
            else
                if(shooter.cockpit.y>shooter.tail.y && shooter.cockpit.x>shooter.tail.x){
                    double delta_y=Math.abs(shooter.cockpit.y-shooter.tail.y);
                    double delta_x=Math.abs(shooter.cockpit.x-shooter.tail.x);

                    if(delta_x<=delta_y){
                        
                        
                       System.out.println("#5");
                       
                       if(shooter.cockpit.x-shooter.tail.x<=7){
                           shooter.velocityStep*=0.8;
                       }
                       
                       
                       
                       shooter.cockpit.x+=shooter.velocityStep;
                       shooter.cockpit.y+=(delta_y/delta_x)*shooter.velocityStep;
                       
                        shooter.tail.x+=shooter.velocityStep;
                        shooter.tail.y+=(delta_y/delta_x)*shooter.velocityStep;

                        shooter.wing1.x+=shooter.velocityStep;
                        shooter.wing1.y+=(delta_y/delta_x)*shooter.velocityStep;

                        shooter.wing2.x+=shooter.velocityStep;
                        shooter.wing2.y+=(delta_y/delta_x)*shooter.velocityStep;
                    }
                    else{
                        
                        System.out.println("#6");
                        
                        if(shooter.cockpit.y-shooter.tail.y<=9){
                            shooter.velocityStep*=0.8;
                        }

                    
                        shooter.cockpit.y+=shooter.velocityStep;
                        shooter.cockpit.x+=(delta_x/delta_y)*shooter.velocityStep;

                        shooter.tail.y+=shooter.velocityStep;
                        shooter.tail.x+=(delta_x/delta_y)*shooter.velocityStep;

                        shooter.wing1.y+=shooter.velocityStep;
                        shooter.wing1.x+=(delta_x/delta_y)*shooter.velocityStep;

                        shooter.wing2.y+=shooter.velocityStep;
                        shooter.wing2.x+=(delta_x/delta_y)*shooter.velocityStep;
                    }
                }
                else
                    if((int)shooter.cockpit.x==(int)shooter.tail.x && shooter.cockpit.y>shooter.tail.y){
                        
                        System.out.println("#7");
            
                        shooter.cockpit.y+=shooter.velocityStep;
                        shooter.tail.y+=shooter.velocityStep;
                        shooter.wing1.y+=shooter.velocityStep;
                        shooter.wing2.y+=shooter.velocityStep;     
                    }
                    else
                        if(shooter.cockpit.x<shooter.tail.x && shooter.cockpit.y>shooter.tail.y){
                            double delta_y=Math.abs(shooter.cockpit.y-shooter.tail.y);
                            double delta_x=Math.abs(shooter.cockpit.x-shooter.tail.x);
                            
                            if(delta_x<=delta_y){
                                
                                System.out.println("#8");
            
                                
                                if(shooter.tail.x-shooter.cockpit.x<=6){
                                    shooter.velocityStep*=0.8;
                                }

                                shooter.cockpit.x-=shooter.velocityStep;
                                shooter.cockpit.y+=(delta_y/delta_x)*shooter.velocityStep;

                                 shooter.tail.x-=shooter.velocityStep;
                                 shooter.tail.y+=(delta_y/delta_x)*shooter.velocityStep;

                                 shooter.wing1.x-=shooter.velocityStep;
                                 shooter.wing1.y+=(delta_y/delta_x)*shooter.velocityStep;

                                 shooter.wing2.x-=shooter.velocityStep;
                                 shooter.wing2.y+=(delta_y/delta_x)*shooter.velocityStep;
                            }
                            else{
                                
                                System.out.println("#9");
            
                                if(shooter.cockpit.y-shooter.tail.y<=4){
                                    shooter.velocityStep*=0.8;
                                }

                                shooter.cockpit.y+=shooter.velocityStep;
                                shooter.cockpit.x-=(delta_x/delta_y)*shooter.velocityStep;

                                shooter.tail.y+=shooter.velocityStep;
                                shooter.tail.x-=(delta_x/delta_y)*shooter.velocityStep;

                                shooter.wing1.y+=shooter.velocityStep;
                                shooter.wing1.x-=(delta_x/delta_y)*shooter.velocityStep;

                                shooter.wing2.y+=shooter.velocityStep;
                                shooter.wing2.x-=(delta_x/delta_y)*shooter.velocityStep;
                            }
                        }
                        else               
                            if (shooter.cockpit.x<shooter.tail.x && (int)shooter.cockpit.y==(int)shooter.tail.y) {
                                
                                System.out.println("#10");
            
                                shooter.cockpit.x-=shooter.velocityStep;
                                shooter.tail.x-=shooter.velocityStep;
                                shooter.wing1.x-=shooter.velocityStep;
                                shooter.wing2.x-=shooter.velocityStep;
                            }
                            else{
                                double delta_y=Math.abs(shooter.cockpit.y-shooter.tail.y);
                                double delta_x=Math.abs(shooter.cockpit.x-shooter.tail.x);

                                if(delta_x<=delta_y){
                                    
                                    System.out.println("#11");
            
                                    if(shooter.tail.x-shooter.cockpit.x<=7){
                                        shooter.velocityStep*=0.8;
                                    }
                                    
                                    shooter.cockpit.x-=shooter.velocityStep;
                                    shooter.cockpit.y-=(delta_y/delta_x)*shooter.velocityStep;

                                     shooter.tail.x-=shooter.velocityStep;
                                     shooter.tail.y-=(delta_y/delta_x)*shooter.velocityStep;

                                     shooter.wing1.x-=shooter.velocityStep;
                                     shooter.wing1.y-=(delta_y/delta_x)*shooter.velocityStep;

                                     shooter.wing2.x-=shooter.velocityStep;
                                     shooter.wing2.y-=(delta_y/delta_x)*shooter.velocityStep;
                                }
                                else{
                                    
                                    System.out.println("#12");
            
                                    if(shooter.tail.y-shooter.cockpit.y<=7){
                                        shooter.velocityStep*=0.8;
                                    }
                                    
                                    shooter.cockpit.y-=shooter.velocityStep;
                                    shooter.cockpit.x-=(delta_x/delta_y)*shooter.velocityStep;

                                    shooter.tail.y-=shooter.velocityStep;
                                    shooter.tail.x-=(delta_x/delta_y)*shooter.velocityStep;

                                    shooter.wing1.y-=shooter.velocityStep;
                                    shooter.wing1.x-=(delta_x/delta_y)*shooter.velocityStep;

                                    shooter.wing2.y-=shooter.velocityStep;
                                    shooter.wing2.x-=(delta_x/delta_y)*shooter.velocityStep;
                                }
                            }
    }
    @Override
    public void run() {
        for(;;){
            g.setColor(Color.black);
            g.fillRect(0,0,getWidth(), getHeight());
            g.setColor(Color.red);
            
            ast1.move();
            ast1.drawAstroid();
            ast2.move();
            ast2.drawAstroid();
            ast3.move();
            ast3.drawAstroid();
            
            shooter.move();
            shooter.drawShooter(g);
            
            ///////Game Refresh Rate///////
            repaint();
            try {
                Thread.sleep(15);
            }
            catch (InterruptedException ex) {
                Logger.getLogger(Level2.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    //////////Main Method////////////
    public static void main(String[] args) {
        
        JFrame frame=new JFrame();
        frame.setSize(1000, 700);
        Level2 level2;
        level2 = new Level2(frame) {};
        frame.add(level2);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Thread t=new Thread(level2);
        t.start();
        
        try {
            t.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(Level2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

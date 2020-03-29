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
import arcworld.level2.Bullet._Point;;
import static arcworld.level2.Shooter.GRAVITY;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Dev Parzival
 */
public abstract class Level2 extends JPanel implements Runnable,KeyListener{

    static final int BIG_ASTROID=1;
    static final int SMALL_ASTROID=2;
    
    BufferedImage img;
    Graphics g;
    Astroid ast1,ast2,ast3;
    Shooter shooter;
    
    ArrayList<Bullet>listBullet;
    ArrayList<Astroid>listAstroid;
    
    HashMap<Astroid,Integer>map;
    
    long firedTime;
    
    public Level2(JFrame frame) {
        firedTime=0;
        this.setSize(frame.getSize());
        frame.addKeyListener(this);
        img=new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_4BYTE_ABGR);
        g=img.getGraphics();
        map=new HashMap<>();
        ast1=new Astroid(Astroid.map.get(1), (Graphics2D) g, this, img,this.getWidth()/2,this.getHeight()/2);
        ast2=new Astroid(Astroid.map.get(1), (Graphics2D) g, this, img,this.getWidth()/2,this.getHeight()/2);
        ast3=new Astroid(Astroid.map.get(1), (Graphics2D) g, this, img,this.getWidth()/2,this.getHeight()/2);
        ast1.enabled=true;
        ast2.enabled=true;
        ast3.enabled=true;
        
        map.put(ast1,BIG_ASTROID);
        map.put(ast2,BIG_ASTROID);
        map.put(ast3,BIG_ASTROID);
        
        shooter=new Shooter(new Shooter._Point(500,350),50, img, g);
        
        listBullet=new ArrayList<>();
        listAstroid=new ArrayList<>();
        
        listAstroid.add(ast1);
        listAstroid.add(ast2);
        listAstroid.add(ast3);
        
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
            
            boolean flag=true;
            if(firedTime!=0){
                long currTime=System.currentTimeMillis();
                if(currTime-firedTime<200){
                    flag=false;
                }
            }
            if(flag){
                firedTime=System.currentTimeMillis();
                Bullet bullet=null;

                double x=(shooter.cockpit.x+shooter.tail.x)/2;
                double y=(shooter.cockpit.y+shooter.tail.y)/2;

                bullet=new Bullet(new Bullet._Point(shooter.cockpit.x,shooter.cockpit.y),new Bullet._Point(x,y),g);

                synchronized(listBullet){
                    listBullet.add(bullet);
                }
            }
        }
    }   
    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode()==KeyEvent.VK_UP)
            shooter.upKey=false;
    }
    
    public void moveBullets(){
        
        synchronized(listBullet){
            
            for(int i=0;i<listBullet.size();i++){
                Bullet bullet=listBullet.get(i);
                
                if(bulletOutOfBound(bullet)){
                    listBullet.remove(i);
                    i--;
                    continue;
                }
                bullet.move();
            }
        }
        
    }
    public void drawBullets(){
        
        synchronized(listBullet){
            for(Bullet bullet:listBullet){
                bullet.drawBullet();
            }
        }
    }
    public boolean bulletOutOfBound(Bullet bullet){
        int height=getHeight();
        int width=getWidth();
        
        if(bullet.head.y<0 && bullet.tail.y<0){
            return true;
        }
        if(bullet.head.x<0 && bullet.tail.x<0){
            return true;
        }
        if(bullet.head.y>height && bullet.tail.y>height){
            return true;
        }
        if(bullet.head.x>width && bullet.tail.x>width){
            return true;
        }
        
        return false;
    }
    public void checkBulletsHitAstroids(){
        
        synchronized(listBullet){
            for(int i=0;i<listBullet.size();i++){
                Bullet bullet=listBullet.get(i);
                boolean collision=false;
                for(int j=0;j<listAstroid.size();j++){
                    Astroid ast=listAstroid.get(j);
                    if(Astroid.isInside(ast.points,ast.points.size(),new Astroid._Point((int)bullet.head.x,(int)bullet.head.y)) || Astroid.isInside(ast.points,ast.points.size(),new Astroid._Point((int)bullet.tail.x,(int)bullet.tail.y))){
                        if(map.get(ast)!=null){
                            for(int k=0;k<2;k++){
                            Astroid small=new Astroid(Astroid.map.get(2), (Graphics2D) g, this, img,this.getWidth()/2,this.getHeight()/2);
                            small.enabled=true;
                            listAstroid.add(small);
                            }
                        }
                        listAstroid.remove(j);
                        collision=true;
                        break;
                    }
                }
                if(collision){
                    listBullet.remove(i);
                    i--;
                }
            }
        }
    }
    public void moveAstroids(){
        synchronized(listAstroid){
            for(Astroid ast: listAstroid){
                ast.move();
            }
        }
    }
    public void drawAstroids(){
        synchronized(listAstroid){
            for(Astroid ast: listAstroid){
                ast.drawAstroid();
            }
        }
    }
    @Override
    public void run() {
        for(;;){
            g.setColor(Color.black);
            g.fillRect(0,0,getWidth(), getHeight());
            g.setColor(Color.red);
            
            moveAstroids();
            drawAstroids();
            
            shooter.move();
            shooter.drawShooter(g);
            
            moveBullets();
            drawBullets();
            
            checkBulletsHitAstroids();

            ///////Game Refresh Rate///////
            repaint();
            try {
                Thread.sleep(8);
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

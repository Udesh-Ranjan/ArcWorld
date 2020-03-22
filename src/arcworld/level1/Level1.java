package arcworld.level1;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyListener;
import java.awt.event.WindowListener;
import java.awt.event.WindowEvent;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Dev Parzival
 * 6th March 2020  09:54 am
 * Astroid Shooter
 * MAY THESE COMMANDS LIVE LONG.
 */
public class Level1 extends JPanel implements KeyListener,Runnable,WindowListener{
    final static byte RESET=0;
    final static byte LEFT=1;
    final static byte RIGHT=2;
    boolean game_status;
    
    @Override
    public void windowOpened(WindowEvent e) {
        game_status=true;
    }

    @Override
    public void windowClosing(WindowEvent e) {
        game_status=false;
        System.exit(0);
    }

    @Override
    public void windowClosed(WindowEvent e) {
        game_status=false;
    }

    @Override
    public void windowIconified(WindowEvent e) {
        game_status=true;
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
        game_status=false;
    }

    @Override
    public void windowActivated(WindowEvent e) {
        game_status=true;
    }
    @Override
    public void windowDeactivated(WindowEvent e) {
        game_status=false;
    }
    
    PrintStream debug;                                         //For getting error msg.
    Graphics2D g;                                              //Reference to the below img.
    BufferedImage img;                                         //Art work done on this.
    Shooter shoot;                                             //It fires.
    int count=20;                                              //# of pixels to slide on x axis on a single left or right key stroke.
    Byte val[];                                                //Length is count 1 represents Left-Key stroke & 2 represents Right-Key stroke.
    ArrayList<Bullet>list_bullet=new ArrayList<Bullet>();             //Stores the bullets.
    JFrame frame;
    ArrayList<Astroid>list_astroid;
    Color front;
    Color back;
    Random rnd;
    GameSound game_sound;
    
    int astroids_destroyed=0;
    
    public Level1(int width,int height,JFrame frm,Color frnt,Color bck){
        game_sound=new GameSound();
        frame=frm;
        front=Color.LIGHT_GRAY;
        back=bck;
//        System.out.println(front);
//        System.out.println(back);
        val=new Byte[count];
        list_bullet=new ArrayList<>();
        list_astroid=new ArrayList<>();
        for(int i=0;i<val.length;i++)
            val[i]=RESET;
        game_status=true;
        rnd=new Random();
        debug=System.out;
        setSize(width,height);
        img=new BufferedImage(this.getWidth(),this.getHeight(),BufferedImage.TYPE_4BYTE_ABGR);
        g=img.createGraphics();
        g.setColor(Color.black);                                                //Making sure that screen is loaded with proper color
        g.fillRect(0,0,width,height);                                           //Making sure that screen is loaded with proper color
        g.setColor(front);
        shoot=new Shooter(new Point(300,500),new Point(290,530),new Point(310,530));
        shoot.drawShooter(g);
        repaint();
    }
    ///////////Creates Astroids/////////////
    public synchronized void createAstroid(){
        
        int number_of_astroids=rnd.nextInt(65326821)%6+1;
        boolean screen_available=true;
        //debug.println(number_of_astroids);
        for(int i=0;screen_available&&i<number_of_astroids;i++){
            Astroid ast=null;
            int astroid_number=rnd.nextInt(35357154)%10+1;
            ast=new Astroid(Astroid.map.get(astroid_number),g,this,img);
            if(i==0){
                int sep_x=Math.abs(rnd.nextInt()%200);
                int sep_y=Math.abs(rnd.nextInt()%50);
                int max_y=ast.maxY();
                for (Point point : ast.points) {
                    point.x += sep_x;
                    point.y -= (sep_y+max_y);
                }
                //debug.println(sep_x+" "+sep_y+" "+max_y);
            }
            else{
                Astroid prev=list_astroid.get(i-1);
                int sep_x=Math.abs(rnd.nextInt()%150)+50;
                int sep_y=Math.abs(rnd.nextInt()%50);
                int prev_max_x=prev.maxX();
                int max_y=ast.maxY();
                
                if(prev_max_x+sep_x>=frame.getWidth()-200){
                    screen_available=false;break;
                }
                for(int j=0;j<ast.points.size();j++){
                    ast.points.get(j).x+=(sep_x+prev_max_x);
                    ast.points.get(j).y-=(sep_y+max_y);
                }
                //debug.println(sep_x+" "+sep_y+" "+max_y);
            }
            list_astroid.add(ast);
        }
    }
    public boolean wayClear(){                                                  //Defines when to create new astroids.
        boolean flag=true;
        for (Astroid ast : list_astroid) {
            if(ast.minY()<=50){
                flag=false;
                break;
            }
        }
        
        return flag;
    }
    @Override
    public void keyTyped(KeyEvent e) {
        if(!game_status)
            return;
        ////////////Firing//////////////
        if(e.getKeyChar()=='f'){
            int len=10;
            Bullet bullet=new Bullet(shoot.top.x,shoot.top.y-len,len,g,this,System.currentTimeMillis());
            boolean found=false;
            /////////////Removing Bullets Of Similar Kind Considering Sepration(sep)///////////////
            for(int i=list_bullet.size()-1;i>=0;i--){
                Bullet _bullet=list_bullet.get(i);
                if(_bullet.equalsConsideringSep(bullet)){
                    found=true;
                    break;
                }
            }
            if(!found){
                //Thread t=new Thread(game_sound);
                //t.start();
                list_bullet.add(bullet);
            }
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(!game_status)
            return;
        /////////////Move Shooter Right Side/////////////
        if(e.getKeyCode()==KeyEvent.VK_RIGHT){
            for(int i=0;i<val.length;i++)
                val[i]=RIGHT;
        }
        /////////////Move Shooter Left Side/////////////
        if(e.getKeyCode()==KeyEvent.VK_LEFT){
            for(int i=0;i<val.length;i++)
                val[i]=LEFT;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
    @Override
    public void paint(Graphics g){
        /////////////Drawing img On the Panel//////////////
        g.drawImage(img,0,0, this);
    }
    @Override
    public void run(){
        //////////Game Starts//////////
        for(;;){
            if(true){             
                if(wayClear()){
                    createAstroid();
                    //debug.println(list_astroid.size());
                }
                //////////////Drawing Astroids////////////////
                for (Astroid ast : list_astroid) {
                    ast.panel.g.setColor(ast.front);
                    ast.drawAstroid();
                    ast.panel.g.setColor(this.front);
                    ast.curr_time=System.currentTimeMillis();
                }
                /////////Checking Weather Bullet Crossed The Top Of The Panel///////
                for(int i=0;i<list_bullet.size();i++){
                    Bullet bullet=list_bullet.get(i);
                    if(bullet.starty+bullet.length<=0){
                        list_bullet.remove(i);
                        continue;
                    }
                    bullet.drawBullet();
                    bullet.curr_time=System.currentTimeMillis();
                }
                /////////////Moves Shooter On The Axis/////////////
                for(int i=0;i<val.length;i++){
                    if(val[i]==LEFT){
    //                    synchronized(g){
    //                        Color back=this.getBackground();
    //                        Color front=g.getColor();
                            g.setColor(back);
    //                        g.fillRect(shoot.startx, shoot.starty, shoot.width, shoot.height);
                            shoot.drawShooter(g);
                            g.setColor(front);
                            shoot.top.x--;
                            shoot.bottom_right.x--;
                            shoot.bottom_left.x--;
                            
                            shoot.initTail();
                            shoot.drawShooter(g);
    //                        g.fillRect(shoot.startx, shoot.starty,shoot.width , shoot.height);
                            //g.drawImage(img, 0,0, this);
                            repaint();
                            val[i]=RESET;
    //                    }
                    }
                    else
                        if(val[i]==RIGHT){
    //                        synchronized(g){
    //                            Color back=this.getBackground();
    //                            Color front=g.getColor();
                                g.setColor(back);
    //                            g.fillRect(shoot.startx, shoot.starty, shoot.width, shoot.height);
                                shoot.drawShooter(g);
                                g.setColor(front);
                                
                                shoot.top.x++;
                                shoot.bottom_right.x++;
                                shoot.bottom_left.x++;
                                
                                shoot.initTail();
                                shoot.drawShooter(g);
    //                            g.fillRect(shoot.startx, shoot.starty,shoot.width , shoot.height);
                                //g.drawImage(img, 0,0, this);
                                repaint();
                                val[i]=RESET;
    //                        }
                        }
                }
                for(int i=0;i<list_bullet.size();i++){
                    Bullet bullet=list_bullet.get(i);
                    
                    //Checking weather the bullet hitted the astroid
                    for(int j=0;j<list_astroid.size();j++){
                        Astroid ast=list_astroid.get(j);
                        if(ast.minY()<=bullet.starty&&ast.maxY()>=bullet.starty)
                            if(ast.minX()<=bullet.startx&&ast.maxX()>=bullet.startx)
                                if(Astroid.isInside(ast.points,ast.points.size(),new Point(bullet.startx,bullet.starty))){
                                    Thread t=new Thread(game_sound);
                                    t.start();
                                    
                                    ast.clearAstroid();
                                    list_astroid.remove(j);
                                    j--;
                                    bullet.clearBullet();
                                    if(list_bullet.size()>i){
                                        try {
                                            list_bullet.remove(i);
                                            i--;
                                        } catch (Exception e) {
                                            debug.println("Array out of bounds");
                                        }
                                    }
                                    astroids_destroyed++;
                                    
                                    if(astroids_destroyed%10==0){
                                        Astroid.elapsedtime=Math.max(Astroid.elapsedtime-1,1);
                                    }
                                }
                    }
                    
                    
                    /////////Refreshing Bullets/////////
                    if(bullet.curr_time-bullet.start_time>=bullet.elapsedLimit){
                        //System.out.println(bullet.start_time+" "+bullet.curr_time+" "+(bullet.curr_time-bullet.start_time));
                        bullet.clearBullet();
                        bullet.starty--;
                        bullet.start_time=System.currentTimeMillis();
                    }
                }
                
                /////////Refreshing Astroids//////////
                for(int i=0;i<list_astroid.size();i++){
                    Astroid ast=list_astroid.get(i);
                    if(ast.minY()>=this.getHeight()){
                        list_astroid.remove(i);
                        i--;
                        continue;
                    }
                    if(Math.abs(ast.curr_time-ast.start_time)>=Astroid.elapsedtime){
                        ast.clearAstroid();
                        ast.fall();
                        ast.drawAstroid();
                        ast.start_time=ast.curr_time=System.currentTimeMillis();
                    }
                    else
                        ast.curr_time=System.currentTimeMillis();
                }
                
                repaint();
                /////////Game Refresh Rate/////////
                try {
                    Thread.sleep(1);
                } catch (InterruptedException ex) {
                }
            }
        }
    }
}

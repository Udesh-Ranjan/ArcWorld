package level2;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Astroid{
    
    static class _Point{
        static final byte LEFT=-1;
        static final byte RIGHT=1;
        
        static final byte UP=-1;
        static final byte DOWN=1;
        
        int x,y;
        byte xOrientation;
        byte yOrientation;
        
        int xDistance;
        int yDistance;
        
        public _Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
        
        public void initializeOrientation(int centerX,int centerY){
            if(x<centerX)
                xOrientation=LEFT;
            else
                xOrientation=RIGHT;
            
            if(y<centerY)
                yOrientation=UP;
            else
                yOrientation=DOWN;
            
            xDistance=Math.abs(x-centerX);
            yDistance=Math.abs(y-centerY);
            
        }
        public void adjustOrientation(int centerX,int centerY){
            if(xOrientation==LEFT){
                x=centerX-xDistance;
            }
            else{
                x=centerX+xDistance;
            }
            
            if(yOrientation==UP){
                y=centerY-yDistance;
            }
            else{
                y=centerY+yDistance;
            }    
        }
    }
    
    static final Integer big[]={423 ,320 ,428 ,316 ,437 ,308 ,445 ,311 ,450 ,308 ,458 ,309 ,472 ,294 ,483 ,297 ,509 ,286 ,553 ,311 ,550 ,355 ,523 ,376 ,509 ,370 ,474 ,379 ,456 ,375 ,451 ,362 ,431 ,358 ,422 ,338 ,418 ,331 ,426 ,327 ,424 ,321};
    static final Integer small[]={465 ,325 ,469 ,315 ,473 ,316 ,480 ,310 ,491 ,315 ,502 ,309 ,514 ,318 ,520 ,328 ,521 ,336 ,515 ,343 ,508 ,349 ,500 ,349 ,487 ,349 ,484 ,345 ,475 ,339 ,469 ,338 ,469 ,329 ,465 ,325};

    static long elapsedtime=20; 
    static final int INF=2000;
    static HashMap<Integer,Integer[]>map;
    
    static Random rnd;
    Level2 panel;
    BufferedImage img;
    Graphics2D g;
    long start_time;
    long curr_time;
    ArrayList<_Point>points;
    Color front;
    boolean status;
    boolean changeX;
    static{
        map=new HashMap<>();
        map.put(1,big);
        map.put(2,small);
        rnd=new Random();
    }
    double centerX;
    double centerY;
    
    double nextX;
    double nextY;
    
    double slope;
    
    double distanceX;
    double distanceY;
    
    int leftRadius;
    int rightRadius;
    
    int upperRadius;
    int lowerRadius;
    
    Astroid(Integer list[],Graphics2D g,Level2 pnl,BufferedImage img,double centerX,double centerY ){
        this.centerX=centerX;
        this.centerY=centerY;
        nextX=nextY=slope=0;
        
        points=new ArrayList();
        for(int i=0;i<list.length;i+=2){
            points.add(new _Point(list[i],list[i+1]));
        }
        this.g=g;
        panel=pnl;
        this.img=img;
        front=Color.cyan;
        start_time=curr_time=System.currentTimeMillis();
        status=true;
        
        int sign=((rnd.nextInt())%2)==0?1:-1;
        nextX=centerX+(rnd.nextInt(51)+10)*sign;
        sign=((rnd.nextInt())%2)==0?1:-1;
        nextY=centerY+(rnd.nextInt(51)+10)*sign;
        
        slope=(centerY-nextY)/(centerX-nextX);
        distanceX=Math.abs(centerX-nextX);
        distanceY=Math.abs(centerY-nextY);
        changeX=(Math.abs(centerX-nextX)>=Math.abs(centerY-nextY));
        
        initializeOrientation();
        
        leftRadius=(int)(centerX-minX());
        rightRadius=(int)(maxX()-centerX);
        upperRadius=(int)(centerY-minY());
        lowerRadius=(int)(maxY()-centerY);
        
    }
    public void drawAstroid(){
        int count_points=points.size();
        for(int i=1;i<count_points;i++){
            _Point pnt1=points.get(i-1);
            _Point pnt2=points.get(i);
            g.drawLine(pnt1.x, pnt1.y, pnt2.x, pnt2.y);
        }
    }
    public void initializeOrientation(){
        for (_Point point : points) {
            point.initializeOrientation((int)centerX, (int)centerY);
        }
    }
    public void adjustOrientation(){
        for (_Point point : points) {
            point.adjustOrientation((int)centerX, (int)centerY);
        }
    }
    public void move() {
        float step=2f;
        int width=panel.getWidth();
        int height=panel.getHeight();
        
        if(changeX){
            ////////Moving towards left///////////
            if(centerX<nextX){
                centerX-=step;
                centerY=slope*(centerX-nextX)+nextY;
                
                if(centerX+rightRadius<0){
                    centerX=width+leftRadius;
                    centerY=slope*(centerX-width/2)+height/2;
                }
                
                nextX=centerX+distanceX;
                nextY=(centerY>nextY)?centerY-distanceY:centerY+distanceY;
                
            }
            ////////Moving towards right///////////
            else{
                centerX+=step;
                centerY=slope*(centerX-nextX)+nextY;
                
                if(centerX-leftRadius>width){
                    centerX=-rightRadius;
                    centerY=slope*(centerX-width/2)+height/2;
                }
                
                nextX=centerX-distanceX;
                nextY=(centerY>nextY)?centerY-distanceY:centerY+distanceY;
            }
        }
        else{
            ////////Moving towards top///////////
            if(centerY<nextY){
                centerY-=step;
                centerX=(centerY-nextY)/slope+nextX;
                
                if(centerY+lowerRadius<0){
                    centerY=height+upperRadius;
                    centerX=(centerY-height/2)/slope+width/2;
                }
                
                nextY=centerY+distanceY;
                nextX=(centerX<nextX)?centerX+distanceX:centerX-distanceX;
                
            }
            else{
            ////////Moving towards bottom///////////
                centerY+=step;
                centerX=(centerY-nextY)/slope+nextX;
                
                if(centerY-upperRadius>height){
                    centerY=-lowerRadius;
                    centerX=(centerY-height/2)/slope+width/2;
                }
                
                nextY=centerY-distanceY;
                nextX=(centerX<nextX)?centerX+distanceX:centerX-distanceX;
                
            }
        }
        adjustOrientation();
    }

    public int minY() {                                                     //Returns the minimun Y coordinate
        int min=Integer.MAX_VALUE;
        for (_Point point : points) {
            int val = point.y;
            if(val<min)
                min=val;
        }
        return min;
    }
    public int maxY() {                                                     //Returns the maximum Y coordinate
        int max=Integer.MIN_VALUE;
        for (_Point point : points) {
            int val = point.y;
            if(val>max)
                max=val;
        }
        return max;
    }
    public int minX() {                                                     //Returns the minimun X coordinate
        int min=Integer.MAX_VALUE;
        for (_Point point : points) {
            int val = point.x;
            if(val<min)
                min=val;
        }
        return min;
    }
    public int maxX() {                                                     //Returns the maximum X coordinate
        int max=Integer.MIN_VALUE;
        for (_Point point : points) {
            int val = point.x;
            if(val>max)
                max=val;
        }
        return max;
    }
    static boolean onSegment(_Point p, _Point q, _Point r){ 
            if (q.x <= Math.max(p.x, r.x) && 
                    q.x >= Math.min(p.x, r.x) && 
                    q.y <= Math.max(p.y, r.y) && 
                    q.y >= Math.min(p.y, r.y)){ 
                    return true; 
            } 
            return false; 
    } 

    static int orientation(_Point p, _Point q, _Point r){ 
            int val = (q.y - p.y) * (r.x - q.x) 
                            - (q.x - p.x) * (r.y - q.y); 

            if (val == 0){ 
                    return 0; // colinear 
            } 
            return (val > 0) ? 1 : 2; // clock or counterclock wise 
    } 

    static boolean doIntersect(_Point p1, _Point q1,_Point p2, _Point q2) { 
            int o1 = orientation(p1, q1, p2); 
            int o2 = orientation(p1, q1, q2); 
            int o3 = orientation(p2, q2, p1); 
            int o4 = orientation(p2, q2, q1); 

            if (o1 != o2 && o3 != o4) { 
                    return true; 
            } 

            if (o1 == 0 && onSegment(p1, p2, q1)) { 
                    return true; 
            } 

            if (o2 == 0 && onSegment(p1, q2, q1)) { 
                    return true; 
            } 

            if (o3 == 0 && onSegment(p2, p1, q2)) { 
                    return true; 
            } 

            if (o4 == 0 && onSegment(p2, q1, q2)) { 
                    return true; 
            } 

            return false; 
    } 

    static boolean isInside(ArrayList<_Point>polygon, int n, _Point p){ 
            if (n < 3){ 
                    return false; 
            } 

            _Point extreme = new _Point(INF, p.y); 

            int count = 0, i = 0; 
            do{ 
                    int next = (i + 1) % n; 

                    if (doIntersect(polygon.get(i), polygon.get(next), p, extreme)) 
                    { 
                            if (orientation(polygon.get(i), p, polygon.get(next)) == 0) 
                            { 
                                    return onSegment(polygon.get(i), p, 
                                                                    polygon.get(next)); 
                            } 

                            count++; 
                    } 
                    i = next; 
            } while (i != 0); 

            return (count % 2 == 1); 
    }
    ///////Main Method///////
    public static void main(String $[]){
        
    }
}
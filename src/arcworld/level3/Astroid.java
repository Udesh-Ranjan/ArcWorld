package arcworld.level3;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 *An Astroid.
 * 
 * @author Dev Parzival
 * @date   01-Apr-2020
 * @time   10:25:08
 */
public class Astroid {

    public static final Integer astroid1_coordinates[]={7 ,48 ,12 ,42 ,18 ,30 ,36 ,13 ,53 ,18 ,59 ,9 ,68 ,1 ,82 ,20 ,80 ,11 ,89 ,8 ,102 ,16 ,111 ,17 ,128 ,41 ,139 ,54 ,135 ,55 ,134 ,64 ,141 ,74 ,133 ,84 ,117 ,89 ,106 ,93 ,80 ,104 ,66 ,95 ,48 ,93 ,26 ,101 ,15 ,94 ,1 ,72 ,10 ,65 ,6 ,49};
    public static final Integer astroid2_coordinates[]={31 ,1 ,47 ,2 ,98 ,36 ,91 ,67 ,75 ,93 ,57 ,101 ,11 ,87 ,1 ,64 ,1 ,39 ,14 ,11 ,33 ,1};
    public static final Integer astroid3_coordinates[]={13 ,36 ,10 ,31 ,12 ,22 ,20 ,22 ,28 ,19 ,28 ,11 ,36 ,2 ,40 ,2 ,50 ,1 ,54 ,3 ,62 ,8 ,71 ,8 ,78 ,22 ,103 ,27 ,114 ,42 ,115 ,42 ,125 ,49 ,124 ,79 ,114 ,89 ,102 ,95 ,94 ,111 ,86 ,120 ,69 ,122 ,60 ,117 ,57 ,106 ,56 ,100 ,59 ,95 ,40 ,75 ,36 ,88 ,26 ,89 ,22 ,82 ,18 ,74 ,16 ,58 ,6 ,51 ,2 ,46 ,2 ,41 ,10 ,29};
    public static final Integer astroid4_coordinates[]={13 ,31 ,14 ,26 ,16 ,24 ,19 ,14 ,24 ,9 ,39 ,0 ,58 ,0 ,75 ,8 ,92 ,29 ,98 ,33 ,116 ,35 ,130 ,36 ,139 ,48 ,151 ,59 ,157 ,75 ,157 ,88 ,136 ,111 ,112 ,125 ,98 ,125 ,80 ,121 ,78 ,116 ,78 ,111 ,77 ,107 ,73 ,103 ,65 ,102 ,56 ,103 ,51 ,112 ,40 ,114 ,26 ,107 ,22 ,101 ,17 ,95 ,12 ,76 ,1 ,66 ,1 ,60 ,2 ,52 ,8 ,44 ,8 ,43 ,14 ,37 ,13 ,28};
    public static final Integer astroid5_coordinates[]={7 ,19 ,9 ,8 ,25 ,2 ,41 ,6 ,55 ,7 ,62 ,3 ,74 ,3 ,92 ,9 ,101 ,23 ,103 ,42 ,99 ,57 ,87 ,73 ,80 ,81 ,68 ,84 ,61 ,83 ,53 ,77 ,47 ,70 ,46 ,65 ,36 ,62 ,21 ,64 ,10 ,59 ,2 ,51 ,1 ,41 ,5 ,32 ,7 ,16};

    public static HashMap<Integer,Integer[]>map;
    private static Random rnd;
    static{
        rnd=new Random();
        map=new HashMap();
        map.put(1, astroid1_coordinates);
        map.put(2, astroid2_coordinates);
        map.put(3, astroid3_coordinates);
        map.put(4, astroid4_coordinates);
        map.put(5, astroid5_coordinates);
    }
    private Color color;
    public ArrayList<Point>points;
    
    private int astType;
    private Point leftMost;
    private Point rightMost;
    private Point topMost;
    private Point bottomMost;
    /**
     * Create an Astroid with list of Integer coordinates.
     * @param arr list of integer points where consecutive elements represents x & y respectively.
     */
    public Astroid(Integer arr[]) {
        points=new ArrayList<>(arr.length/2);
        for(int i=0;i<arr.length;i+=2){
            points.add(new Point(arr[i],arr[i+1]));
        }
        color=Color.lightGray;
        
        int minX=Integer.MAX_VALUE;
        int maxX=Integer.MIN_VALUE;
        
        int minY=Integer.MAX_VALUE;
        int maxY=Integer.MIN_VALUE;
        
        for (Point point : points) {
            if(point.x<minX){
                minX=point.x;
                leftMost=point;
            }
            if(point.x>maxX){
                maxX=point.x;
                rightMost=point;
            }
            if(point.y<minY){
                minY=point.y;
                topMost=point;
            }
            if(point.y>maxY){
                maxY=point.y;
                bottomMost=point;
            }
        }
    }
    /**
     * Returns an Astroid.
     * @return Astroid
     */
    public static Astroid getRandomAstroid(){
        
        int val=rnd.nextInt(5)+1;
        Astroid ast=new Astroid(map.get(val));
        ast.astType=val;
        return ast;
    }
    /**
     * Returns an Astroid with the left most x coordinate is leftX &
     * top most y coordinate is topY.
     * @param leftX             New leftMost x
     * @param topY              New topMost y
     * @return Astroid
     */
    public static Astroid getRandomAstroid(int leftX,int topY){
        
        int val=rnd.nextInt(5)+1;
        Astroid ast=new Astroid(map.get(val));
        
        int x=ast.leftMost.x;
        int distance=leftX-x;
        for(int i=0;i<ast.points.size();i++){
            ast.points.get(i).x+=distance;
        }
        int y=ast.topMost.y;
        distance=topY-y;
        for(int i=0;i<ast.points.size();i++){
            ast.points.get(i).y+=distance;
        }
        
        int minX=Integer.MAX_VALUE;
        int maxX=Integer.MIN_VALUE;
        
        int minY=Integer.MAX_VALUE;
        int maxY=Integer.MIN_VALUE;
        
        for (Point point : ast.points) {
            if(point.x<minX){
                minX=point.x;
                ast.leftMost=point;
            }
            if(point.x>maxX){
                maxX=point.x;
                ast.rightMost=point;
            }
            if(point.y<minY){
                minY=point.y;
                ast.topMost=point;
            }
            if(point.y>maxY){
                maxY=point.y;
                ast.bottomMost=point;
            }
        
        }
        return ast;
    }
    int getLeftMostX(){
        return leftMost.x;
    }
    int getrightMostX(){
        return rightMost.x;
    }
    int gettopMostY(){
        return topMost.y;
    }
    int getBottomMostY(){
        return bottomMost.y;
    }
    public void setColor(Color col){
        this.color=col;
    }
    /**
     * Draws an Astroid.
     * @param g Graphics object.
     */
    public void drawAstroid(Graphics g){
        g.setColor(color);
        Point p1,p2;
        p1=p2=null;
        p1=points.get(0);
        for(int i=1;i<points.size();i++){
            p2=points.get(i);
            
            g.drawLine(p1.x,p1.y,p2.x,p2.y);
            p1=p2;
        }
    }
    /**
     * Astroid falls.
     */
    public void fall(){
        for(Point point : points){
            point.y++;
        }
    }
    public void fillAstroid(Graphics g){
        
        g.setColor(Color.DARK_GRAY);
        int x[]=new int[points.size()];
        int y[]=new int[x.length];
        for(int i=0;i<x.length;i++){
            Point p=points.get(i);
            x[i]=p.x;
            y[i]=p.y;
        }
        g.fillPolygon(x, y,x.length);
    }
    /**
     * When dispose is called the Astroid cannot be used again.
     * It will remove all the elements(Point) from points(ArrayList).
     */
    public void dispose(){
        points.removeAll(points);
    }
}

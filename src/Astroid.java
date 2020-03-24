import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import level2.Level2;

public class Astroid{
    
        static final Integer big[]={423 ,320 ,428 ,316 ,437 ,308 ,445 ,311 ,450 ,308 ,458 ,309 ,472 ,294 ,483 ,297 ,509 ,286 ,553 ,311 ,550 ,355 ,523 ,376 ,509 ,370 ,474 ,379 ,456 ,375 ,451 ,362 ,431 ,358 ,422 ,338 ,418 ,331 ,426 ,327 ,424 ,321};
        static long elapsedtime=20; 
        static final int INF=2000;
        static HashMap<Integer,Integer[]>map;
        Level2 panel;
        BufferedImage img;
        Graphics2D g;
        long start_time;
        long curr_time;
        ArrayList<Point>points;
        Color front;
        boolean status;
        static{
            map=new HashMap<>();
            map.put(1,big);
        }
        Astroid(Integer list[],Graphics2D g,Level2 pnl,BufferedImage img){
            points=new ArrayList();
            for(int i=0;i<list.length;i+=2){
                points.add(new Point(list[i],list[i+1]));
            }
            this.g=g;
            panel=pnl;
            this.img=img;
            front=Color.cyan;
            start_time=curr_time=System.currentTimeMillis();
            status=true;
        }
        public void drawAstroid(){
            int count_points=points.size();
            for(int i=1;i<count_points;i++){
                Point pnt1=points.get(i-1);
                Point pnt2=points.get(i);
                g.drawLine(pnt1.x, pnt1.y, pnt2.x, pnt2.y);
            }
        
        }
        
        public void fall() {
            for (Point point : points) {
                point.y+=2;                                                      //Incrementing the y position
            }
        }

        public int minY() {                                                     //Returns the minimun Y coordinate
            int min=Integer.MAX_VALUE;
            for (Point point : points) {
                int val = point.y;
                if(val<min)
                    min=val;
            }
            return min;
        }
        public int maxY() {                                                     //Returns the maximum Y coordinate
            int max=Integer.MIN_VALUE;
            for (Point point : points) {
                int val = point.y;
                if(val>max)
                    max=val;
            }
            return max;
        }
        public int minX() {                                                     //Returns the minimun X coordinate
            int min=Integer.MAX_VALUE;
            for (Point point : points) {
                int val = point.x;
                if(val<min)
                    min=val;
            }
            return min;
        }
        public int maxX() {                                                     //Returns the maximum X coordinate
            int max=Integer.MIN_VALUE;
            for (Point point : points) {
                int val = point.x;
                if(val>max)
                    max=val;
            }
            return max;
        }
        static boolean onSegment(Point p, Point q, Point r){ 
		if (q.x <= Math.max(p.x, r.x) && 
			q.x >= Math.min(p.x, r.x) && 
			q.y <= Math.max(p.y, r.y) && 
			q.y >= Math.min(p.y, r.y)){ 
			return true; 
		} 
		return false; 
	} 

	static int orientation(Point p, Point q, Point r){ 
		int val = (q.y - p.y) * (r.x - q.x) 
				- (q.x - p.x) * (r.y - q.y); 

		if (val == 0){ 
			return 0; // colinear 
		} 
		return (val > 0) ? 1 : 2; // clock or counterclock wise 
	} 

	static boolean doIntersect(Point p1, Point q1,Point p2, Point q2) { 
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

	static boolean isInside(ArrayList<Point>polygon, int n, Point p){ 
		if (n < 3){ 
			return false; 
		} 

		Point extreme = new Point(INF, p.y); 

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
    }
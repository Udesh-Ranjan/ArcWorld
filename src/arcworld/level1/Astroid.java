package arcworld.level1;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Astroid{
    
        static final Integer astroid1_coordinates[]={54 ,3,68, 5 ,81 ,16,83 ,26 ,79 ,39 ,87 ,54, 81 ,63 ,76 ,70 ,61 ,80 ,49 ,79 ,34 ,81 ,20 ,70 ,5 ,56 ,2 ,36 ,8 ,34 ,9 ,27 ,18 ,31 ,21 ,16 ,24 ,9 ,28 ,6 ,32 ,1 ,54 ,3};
        static final Integer astroid2_coordinates[]={5 ,14 ,7 ,12 ,7 ,9 ,10 ,8 ,11 ,7 ,12 ,4 ,13 ,3 ,14 ,2 ,16 ,3 ,17 ,2 ,18 ,1 ,22 ,2 ,23 ,5 ,26 ,4 ,28 ,6 ,31 ,7 ,35 ,7 ,36 ,6 ,37 ,4 ,39 ,4 ,41 ,8 ,46 ,8 ,46 ,7 ,48 ,9 ,51 ,12 ,48 ,18 ,46 ,19 ,44 ,17 ,41 ,22 ,40 ,27 ,38 ,28 ,35 ,29 ,33 ,33 ,32 ,37 ,28 ,36 ,23 ,35 ,19 ,36 ,18 ,40 ,17 ,42 ,15 ,42 ,13 ,44 ,7 ,42 ,4 ,37 ,2 ,34 ,1 ,30 ,2 ,28 ,3 ,24 ,3 ,22 ,3 ,21 ,3 ,17 ,6 ,12};
        static final Integer astroid3_coordinates[]={37 ,4 ,32 ,10 ,24 ,14 ,17 ,14 ,9 ,18 ,7 ,26 ,5 ,35 ,3 ,39 ,3 ,47 ,1 ,53 ,4 ,60 ,9 ,62 ,15 ,67 ,18 ,76 ,20 ,77 ,26 ,77 ,34 ,81 ,37 ,85 ,45 ,86 ,53 ,77 ,60 ,78 ,70 ,73 ,74 ,69 ,76 ,66 ,79 ,59 ,81 ,53 ,83 ,46 ,84 ,38 ,89 ,21 ,87 ,18 ,77 ,15 ,72 ,3 ,64 ,4 ,58 ,3 ,45 ,5 ,40 ,1 ,36 ,7};
        static final Integer astroid4_coordinates[]={85 ,5 ,70 ,5 ,65 ,12 ,53 ,15 ,36 ,12 ,32 ,1 ,17 ,1 ,11 ,5 ,1 ,16 ,1 ,29 ,6 ,34 ,9 ,38 ,31 ,53 ,37 ,48 ,52 ,47 ,79 ,58 ,84 ,50 ,87 ,55 ,96 ,52 ,105 ,42 ,114 ,25 ,107 ,18 ,107 ,12 ,106 ,0 ,100 ,9 ,84 ,5};
        static final Integer astroid5_coordinates[]={28 ,8 ,37 ,4 ,51 ,1 ,66 ,2 ,76 ,7 ,78 ,8 ,88 ,14 ,93 ,26 ,95 ,39 ,94 ,49 ,90 ,57 ,78 ,64 ,61 ,66 ,47 ,67 ,27 ,68 ,11 ,66 ,4 ,50 ,1 ,28 ,6 ,22 ,12 ,17 ,15 ,17 ,21 ,15 ,28 ,8};
        static final Integer astroid6_coordinates[]={42 ,11 ,6 ,25 ,1 ,37 ,7 ,57 ,4 ,73 ,19 ,87 ,38 ,82 ,58 ,87 ,78 ,90 ,60 ,70 ,78 ,78 ,84 ,73 ,96 ,73 ,105 ,61 ,102 ,39 ,105 ,25 ,75 ,0 ,58 ,10 ,40 ,11};
        static final Integer astroid7_coordinates[]={22 ,1 ,30 ,0 ,35 ,0 ,40 ,2 ,43 ,2 ,51 ,2 ,56 ,2 ,62 ,2 ,70 ,0 ,75 ,0 ,78 ,3 ,85 ,3 ,89 ,1 ,95 ,0 ,97 ,3 ,103 ,7 ,116 ,32 ,114 ,38 ,115 ,48 ,118 ,53 ,117 ,57 ,115 ,55 ,115 ,59 ,111 ,62 ,113 ,63 ,113 ,66 ,112 ,68 ,113 ,72 ,112 ,77 ,106 ,80 ,103 ,83 ,98 ,90 ,87 ,94 ,84 ,93 ,72 ,97 ,71 ,93 ,63 ,92 ,56 ,96 ,50 ,96 ,46 ,93 ,44 ,86 ,37 ,86 ,28 ,90 ,9 ,84 ,2 ,67 ,6 ,59 ,2 ,53 ,2 ,44 ,4 ,35 ,7 ,30 ,0 ,17 ,2 ,13 ,16 ,8 ,16 ,4 ,22 ,2};
        static final Integer astroid8_coordinates[]={22 ,18 ,9 ,23 ,6 ,26 ,1 ,50 ,7 ,63 ,6 ,73 ,3 ,88 ,7 ,105 ,19 ,128 ,23 ,139 ,17 ,145 ,33 ,159 ,42 ,138 ,53 ,113 ,48 ,108 ,66 ,88 ,57 ,82 ,54 ,77 ,64 ,71 ,65 ,65 ,68 ,58 ,69 ,52 ,70 ,46 ,72 ,38 ,75 ,17 ,76 ,5 ,73 ,4 ,66 ,1 ,57 ,1 ,48 ,11 ,43 ,8 ,28 ,7 ,20 ,9 ,12 ,21};
        static final Integer astroid9_coordinates[]={22 ,4 ,29 ,10 ,42 ,1 ,50 ,3 ,57 ,13 ,64 ,26 ,68 ,26 ,76 ,26 ,75 ,36 ,69 ,39 ,58 ,49 ,48 ,44 ,32 ,50 ,23 ,41 ,16 ,38 ,4 ,31 ,3 ,28 ,0 ,27 ,4 ,23 ,1 ,17 ,2 ,14 ,3 ,13 ,6 ,7 ,7 ,6 ,12 ,9 ,11 ,7 ,16 ,2 ,24 ,5};
        static final Integer astroid10_coordinates[]={48 ,22 ,64 ,22 ,78 ,19 ,78 ,1 ,86 ,9 ,95 ,17 ,103 ,12 ,107 ,18 ,103 ,30 ,116 ,54 ,106 ,55 ,97 ,68 ,89 ,73 ,79 ,64 ,72 ,72 ,68 ,69 ,70 ,56 ,62 ,53 ,56 ,59 ,48 ,65 ,46 ,82 ,29 ,89 ,1 ,62 ,3 ,53 ,7 ,41 ,10 ,18 ,13 ,24 ,24 ,17 ,29 ,23 ,36 ,26 ,48 ,22};
        static long elapsedtime=20; 
        static final int INF=2000;
        static HashMap<Integer,Integer[]>map;
        Level1 panel;
        BufferedImage img;
        Graphics2D g;
        long start_time;
        long curr_time;
        ArrayList<Point>points;
        Color front;
        boolean status;
        int fall;
        static Random rnd;
        static{
            map=new HashMap<>();
            map.put(1,astroid1_coordinates);
            map.put(2,astroid2_coordinates);
            map.put(3,astroid3_coordinates);
            map.put(4,astroid4_coordinates);
            map.put(5,astroid5_coordinates);
            map.put(6,astroid6_coordinates);
            map.put(7,astroid7_coordinates);
            map.put(8,astroid8_coordinates);
            map.put(9,astroid9_coordinates);
            map.put(10,astroid10_coordinates);
            rnd=new Random();
        }
        Astroid(Integer list[],Graphics2D g,Level1 pnl,BufferedImage img){
            points=new ArrayList();
            for(int i=0;i<list.length;i+=2){
                points.add(new Point(list[i],list[i+1]));
            }
            fall=rnd.nextInt(3)+3;
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
        public void clearAstroid(){
            g.setColor(panel.back);
            drawAstroid();
            g.setColor(panel.front);
        }

        public void fall() {
            for (Point point : points) {
                point.y+=fall;                                                      //Incrementing the y position
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
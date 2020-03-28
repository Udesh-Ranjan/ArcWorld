package arcworld.level2;

import java.io.PrintStream;
import java.util.Scanner;

/**
 *
 * @author Dev Parzival
 */
public class Compute {

    static class Point{
        double x;
        double y;

        public Point(double x,double y){
            this.x=x;
            this.y=y;
        }
        public Point(Point p){
            x=p.x;
            y=p.y;
        }
        public Point(){
            x=y=0;
        }
    }
    public static void main(String[] args) {
        double x,y;
        Point p1,p2;
        
        PrintStream out=System.out;
        Scanner scan=new Scanner(System.in);
        out.println("Enter point Point 1(CenterX,CenterY)");
        out.print("Enter x : ");
        x=scan.nextDouble();
        out.print("Enter y : ");
        y=scan.nextDouble();
        
        p1=new Point(x,y);
        
        out.println("Enter point Point 2");
        out.print("Enter x : ");
        x=scan.nextDouble();
        out.print("Enter y : ");
        y=scan.nextDouble();
        
        p2=new Point(x,y);
        
        double length=getDistance(p1,p2);
        
        System.out.println("Enter 1 to change X or 2 to change Y");
        int choice=scan.nextInt();
        if(choice==1){
            System.out.print("Enter the step value : ");
            double step=scan.nextDouble();
            double res=Math.pow(length*length-(p2.x+step-p1.x)*(p2.x+step-p1.x),0.5);
            System.out.println(res+"="+"y±"+p1.y);
        }
        else{
            System.out.print("Enter the step value : ");
            double step=scan.nextDouble();
            double res=Math.pow(length*length-(p2.y+step-p1.y)*(p2.y+step-p1.y),0.5);
            System.out.println(res+"="+"x±"+p1.x);
        }
        
    }
    static double getDistance(Point p1,Point p2){
        return Math.pow((p1.x-p2.x)*(p1.x-p2.x)+(p1.y-p2.y)*(p1.y-p2.y),0.5);
    }
        
}

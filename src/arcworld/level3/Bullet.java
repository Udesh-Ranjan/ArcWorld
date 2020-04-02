
package arcworld.level3;

/**
 *
 * @author Dev Parzival
 * @date   01-Apr-2020
 * @time   20:22:59
 */
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

public class Bullet{
    
    static final int ySep=20;                                                    //Seperation b/w two bullets on same axis in pixels.
    static final int xSep=5;
    static final int step=3;                                                    //Speed at which the bullet will move.
    
    int startx;
    int starty;
    int length;                         
    JPanel panel;
    int power;
    Bullet(int x,int y,int len,JPanel panel){
        power=25;
        startx=x;
        starty=y;
        length=len;
        this.panel=panel;
    }
    void drawBullet(Graphics g){
        g.setColor(Color.white);
            g.drawLine(startx,starty,startx,starty+length);
    }

    public void move(){
        starty-=step;
    }
    public boolean bulletOutOfBound(){
        return starty+length<=0?true:false;
    }
    public boolean equals(Bullet bullet){
        if(bullet==null)return false;
        return bullet.startx==startx&&bullet.starty==starty;
    }
    public void setPower(int pow){
        power=pow;
    }
    public int getPower(){
        return power;
    }
    /**
     * Returns boolean value based on the ySeparation b/w the bullets.
     * @param bullet Bullet 
     * @return boolean
     */
    public boolean equalsConsideringSep(Bullet bullet){
        if(bullet==null)return false;
        if(bullet.startx-startx<=xSep)
        if(Math.abs(bullet.starty-starty)<ySep){
            return true;
        }
        return false;
    }
}
    
package arcworld.level2;

import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JOptionPane;

/**
 *
 * @author Dev Parzival
 * @date   30-Mar-2020
 * @time   12:35:46
 */
public class Level2Sound implements Runnable{
    
    String filePath;
    Clip clip;
    AudioInputStream audioInputStream;
    public Level2Sound(String filePath) throws Exception{
            audioInputStream=AudioSystem.getAudioInputStream(new File(filePath).getAbsoluteFile());
            clip=AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
    
    @Override
    public void run() {
        if(clip!=null){
            clip.start();
        }
    }
    public void playSound(){
        Thread t=null;
        try{
            Level2Sound sound=new Level2Sound(filePath);
           t=new Thread(sound);
           t.setPriority(Thread.MIN_PRIORITY);
            t.run();
        }
        catch(Exception e){
        }
    }
    public void disposeSound(){
        try {
            clip.close();
            audioInputStream.close();
        } catch (IOException ex) {
            JOptionPane.showInputDialog(this, ex);
        }
    }
    public static void main(String[] args) throws Exception{
        long time=10000;
        
        Level2Sound sound=new Level2Sound("C:\\Users\\Dev Parzival\\Desktop\\Level1.wav");
        sound.playSound();
        try {
            Thread.sleep(time);
            
        } catch (Exception e) {
        }
        sound.disposeSound();
    }
    
}

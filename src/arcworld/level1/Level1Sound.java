package arcworld.level1;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.UnsupportedAudioFileException;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

/**
 *
 * @author Dev Parzival
 * @date   30-Mar-2020
 * @time   12:35:46
 */
public class Level1Sound implements Runnable{
    
    String filePath;
    Clip clip;
    AudioInputStream audioInputStream;

    public Level1Sound(String filePath) throws Exception{
            audioInputStream=AudioSystem.getAudioInputStream(new File(filePath).getAbsoluteFile());
            clip=AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
            
    }
    
    @Override
    public void run() {
        if(clip!=null){
            
            clip.start();
            try {
                Thread.sleep(2000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    public static void main(String[] args) throws Exception{
        Thread t=new Thread(new Level1Sound("C:\\Users\\Dev Parzival\\Music\\Broke.wav"));
        t.start();
        t.join();
    }
    
}

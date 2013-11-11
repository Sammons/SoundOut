/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package soundout;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;

/**
 *
 * @author Ben Sammens
 */
public class WriteSound implements Runnable {

    
    private AudioInputStream dataStream;
    private AudioFileFormat.Type fileFormat;
    /**
     *
     * @param dataStream
     * @param fileFormat
     */
    public WriteSound(AudioInputStream dataStream, AudioFileFormat.Type fileFormat) {
        this.dataStream = dataStream;
        this.fileFormat = fileFormat;
    }
    
    //begin writing the audio to a file
    //AudioSystem does not need to be told to stop explicitly    
    @Override
    public void run() {
        File storageFile = new File("recording." + fileFormat.getExtension());
        try {
            //write the audio!        
            AudioSystem.write(dataStream, fileFormat, storageFile);
        } catch (IOException ex) {
            Logger.getLogger(WriteSound.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

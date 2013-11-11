package soundout;

import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Date;
import java.util.Timer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 *
 * @author Ben Sammons
 */
public class Main {

    /**
     * @param args the command line arguments
     *
     */
    public static void main(String[] args) {
        boolean started = false;
        int i = 0;
        Microphone mic = new Microphone();
        while (i < 250000000) {
            if (!started) {
                mic.Open();
                started = true;
            }

            i++;

        }
        mic.Close();
        File f = new File("recording.wav");
        AudioInputStream stream = null;
        short data[] = null;
        try {
            stream = AudioSystem.getAudioInputStream(f);
            byte bytes[] = new byte[stream.getFormat().getSampleSizeInBits() / 8];
            data = new short[(int) stream.getFrameLength()];
            for (int j = 0; j < (int) stream.getFrameLength(); j++) {
                stream.read(bytes, 0, stream.getFormat().getSampleSizeInBits() / 8);
                data[j] = ByteBuffer.wrap(bytes).order(ByteOrder.BIG_ENDIAN).getShort();
            }
        } catch (UnsupportedAudioFileException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        short dataAvg[] = new short[data.length/64];
        int j =0;
        int l =0;
        long next = 0;
        for (short k : data) {
        next += k;
        j++;
        if (j==64) {
            j=0;
            dataAvg[l] = (short) (next/64);
            next = 0;
            System.out.println(dataAvg[l]);
            l++;

        }
        }

    }
}

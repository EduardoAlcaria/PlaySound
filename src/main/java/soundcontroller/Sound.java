package soundcontroller;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class Sound{

    public static void playSound(String path) {
        try {

            File soundFile = new File(path);

            AudioInputStream audioStream = AudioSystem.getAudioInputStream(soundFile);

            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();

        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }

    }
}

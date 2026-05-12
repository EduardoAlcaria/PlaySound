

import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;
import com.github.kwhat.jnativehook.keyboard.NativeKeyListener;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main implements NativeKeyListener {

    private boolean shiftPressed = false;

    public static void main(String[] args) {

        Logger logger = Logger.getLogger(GlobalScreen.class.getPackage().getName());
        logger.setLevel(Level.OFF);

        try {
            GlobalScreen.registerNativeHook();
        } catch (Exception e) {
            System.err.println("Failed to register native hook.");
            e.printStackTrace();
            System.exit(1);
        }

        GlobalScreen.addNativeKeyListener(new Main());

        System.out.println("Listening for ALT + ,");
    }

    @Override
    public void nativeKeyPressed(NativeKeyEvent e) {

        if (e.getKeyCode() == NativeKeyEvent.VC_ALT){
            shiftPressed = true;
        }


        if (shiftPressed && e.getKeyCode() == NativeKeyEvent.VC_COMMA) {
            playSound("/home/kaiser/Downloads/sound.wav");
        }
    }

    @Override
    public void nativeKeyReleased(NativeKeyEvent e) {
        if (e.getKeyCode() == NativeKeyEvent.VC_ALT) {
            shiftPressed = false;
        }
    }

    @Override
    public void nativeKeyTyped(NativeKeyEvent e) {
    }

    private static void playSound(String path) {
        try {
            File soundFile = new File(path);

            AudioInputStream audioStream =
                    AudioSystem.getAudioInputStream(soundFile);

            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();

        } catch (UnsupportedAudioFileException |
                 IOException |
                 LineUnavailableException e) {
            e.printStackTrace();
        }
    }
}
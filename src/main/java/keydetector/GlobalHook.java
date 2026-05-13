package keydetector;

import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.NativeHookException;
import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;
import com.github.kwhat.jnativehook.keyboard.NativeKeyListener;
import soundcontroller.Sound;

import java.util.logging.Level;
import java.util.logging.Logger;

public class GlobalHook  implements NativeKeyListener {

    private boolean shiftPressed = false;


    public void addGlobalLister(){

        Logger logger = Logger.getLogger(GlobalScreen.class.getPackage().getName());
        logger.setLevel(Level.OFF);

        try {
            GlobalScreen.registerNativeHook();
        } catch (Exception e) {
            System.err.println("Failed to register native hook.");
            e.printStackTrace();
            System.exit(1);
        }

        GlobalScreen.addNativeKeyListener(new GlobalHook());

        System.out.println("Listening for ALT + ,");
    }

    @Override
    public void nativeKeyPressed(NativeKeyEvent e) {

        if (e.getKeyCode() == NativeKeyEvent.VC_ALT){
            shiftPressed = true;
        }

        if (shiftPressed && e.getKeyCode() == NativeKeyEvent.VC_COMMA) {
            Sound.playSound("/home/kaiser/Downloads/sound.wav");
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

}





import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;
import com.github.kwhat.jnativehook.keyboard.NativeKeyListener;
import keydetector.GlobalHook;

import javax.sound.sampled.*;


public class Main implements NativeKeyListener {

    public static void main(String[] args) {

       GlobalHook hook = new GlobalHook();

       hook.addGlobalLister();
    }
}
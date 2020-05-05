package dev.federicocapece.jdaze;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashSet;


/**
 * <pre>
 * Input manager for the game engine
 * </pre>
 */
public final class Input {
    /**
     * The set of keys that are currently pressed,
     * do not touch this directly
     */
    private static HashSet<Integer> pressed = new HashSet<>();

    /**
     * The keyListener that will send the Key data to this Input manager.
     * It is automatically registered on the Renderer
     */
    protected static final KeyListener keyListener = new KeyListener() {
        @Override
        public void keyTyped(KeyEvent e) {

        }

        @Override
        public void keyPressed(KeyEvent e) {
            pressed.add((int)e.getKeyCode());
        }

        @Override
        public void keyReleased(KeyEvent e) {
            pressed.remove((int)e.getKeyCode());
        }
    };


    /**
     * Use this method inside the update() to check if a key is pressed and react to it.
     * @param keyCode The keycode as integer, use the constants in: java.awt.KeyEvent
     * @return true/false
     */
    public static boolean isKeyDown(int keyCode){
        return pressed.contains(keyCode);
    }

    /**
     * Use this method inside the update() to check if a key is NOT pressed and react to it.
     * NOTE: this actually does !isKeyDown, so it is not recommended, just use !isKeyDown
     * @param keyCode The keycode as integer, use the constants in: java.awt.KeyEvent
     * @return true/false
     */
    public static boolean isKeyUp(int keyCode){
        return !isKeyDown(keyCode);
    }


}

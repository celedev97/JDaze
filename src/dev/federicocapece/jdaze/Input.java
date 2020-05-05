package dev.federicocapece.jdaze;

import javax.swing.event.MouseInputListener;
import java.awt.event.*;
import java.util.HashSet;


/**
 * <pre>
 * Input manager for the game engine
 * </pre>
 */
public final class Input {
    //#region Internal data

    /**
     * The set of keys that are currently pressed,
     * do not touch this directly
     */
    private static HashSet<Integer> pressed = new HashSet<>();

    private static float mouseWheelRotation = 0f;

    //#endregion

    //#region Listeners

    /**
     * The KeyListener that will send the Key data to the Input manager.
     * It is automatically registered on the renderer canvas
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
     * The MouseWheelListener that will send the mouse move and click data to the Input manager.
     * It is automatically registered on the renderer canvas
     */
    protected static final MouseInputListener mouseInputListener = new MouseInputListener() {
        @Override
        public void mouseClicked(MouseEvent e) {

        }

        @Override
        public void mousePressed(MouseEvent e) {
            System.out.println("DOWNMOUSE");
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            System.out.println("UPMOUSE");
        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }

        @Override
        public void mouseDragged(MouseEvent e) {
            System.out.println("DRAGMOUSE");
        }

        @Override
        public void mouseMoved(MouseEvent e) {
            System.out.println("MOVEMOUSE");
        }
    };

    /**
     * The MouseWheelListener that will send the mouse wheel data to the Input manager.
     * It is automatically registered on the renderer canvas
     */
    protected static final MouseWheelListener mouseWheelListener = new MouseWheelListener() {
        @Override
        public void mouseWheelMoved(MouseWheelEvent e) {
            mouseWheelRotation -= e.getPreciseWheelRotation();
        }
    };

    //#endregion

    //#region static methods for external use

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

    /**
     * Use this method inside the update() to check if the mouse wheel was rotated.
     * This method will return a float:
     * <pre>
     * positive = up
     * negative = down
     * zero     = no rotation
     * </pre>
     * NOTE: the rotation of the mouse wheel get reset after the update,
     * so you shouldn't use it in combination with deltaTime.
     * @return a float representing the mouseWheel rotation
     */
    public static float getMouseWheelRotation(){
        return mouseWheelRotation;
    }

    protected static void mouseWheelReset() {
        mouseWheelRotation = 0;
    }
    //#endregion

}

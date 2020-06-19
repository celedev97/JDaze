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
    //#region Private status data

    /**
     * The set of keys that are currently pressed,
     * do not touch this directly
     */
    private static HashSet<Integer> keysDown = new HashSet<>();
    /**
     * The set of mouse buttons that are currently pressed,
     * do not touch this directly
     */
    private static HashSet<Integer> mouseButtonsDown = new HashSet<>();
    /**
     * The float representing the mouse wheel rotation in the last frame.
     */
    private static float mouseWheelRotation = 0f;

    private static final Vector mousePosition = new Vector(0,0);

    //#endregion

    //#region Engine methods/listener

    /**
     * Internal method for resetting the mouse wheel status, called after each update
     */
    protected static void mouseWheelReset() {
        mouseWheelRotation = 0;
    }

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
            keysDown.add((int)e.getKeyCode());
        }

        @Override
        public void keyReleased(KeyEvent e) {
            keysDown.remove((int)e.getKeyCode());
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
            mouseButtonsDown.add(e.getButton());
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            mouseButtonsDown.remove(e.getButton());
        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }

        @Override
        public void mouseDragged(MouseEvent e) {
            mousePosition.set(e.getX(), e.getY());
        }

        @Override
        public void mouseMoved(MouseEvent e) {
            mousePosition.set(e.getX(), e.getY());
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
        return keysDown.contains(keyCode);
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

    /**
     * Use this method inside the update() to check if a mouse button is pressed and react to it.
     * @param buttonCode the MouseButton code as integer, use constants in: java.awt.MouseEvent
     * @return true/false according to the mouse button status
     */
    public static boolean isMouseDown(int buttonCode) {
        return mouseButtonsDown.contains(buttonCode);
    }

    /**
     * Get the mouse position as a Vector.
     * The mouse position is referred to the canvas, not to the world position.
     * NOTE: You could theoretically store it inside your class
     * and reuse it as it's directly updated by the Input Manager.
     * @return the Vector representing the mouse position
     */
    public static Vector getMousePosition(){
        return mousePosition;
    }


    /**
     * Get the mouse position inside the world coordinate system as a Vector.
     * This is calculated every time you call it by using the Camera position,
     * so it's recommended to store it and not to call this multiple time for every update.
     * @return the Vector representing the mouse position
     */
    public static Vector getMouseWorldPosition(){
        return Engine.camera.canvasToWorldPoint(mousePosition);
    }

    public static Vector getWASDVector(){
        Vector movement = Vector.ZERO();

        if(isKeyDown(KeyEvent.VK_S)){
            movement.sumUpdate(Vector.DOWN());
        }else if(isKeyDown(KeyEvent.VK_W)) {
            movement.sumUpdate(Vector.UP());
        }

        if(isKeyDown(KeyEvent.VK_A)){
            movement.sumUpdate(Vector.LEFT());
        }else if(isKeyDown(KeyEvent.VK_D)) {
            movement.sumUpdate(Vector.RIGHT());
        }

        return movement.normalize();
    }

    public static Vector getArrowsVector(){
        Vector movement = Vector.ZERO();

        if(isKeyDown(KeyEvent.VK_UP)){
            movement.sumUpdate(Vector.DOWN());
        }else if(isKeyDown(KeyEvent.VK_DOWN)) {
            movement.sumUpdate(Vector.UP());
        }

        if(isKeyDown(KeyEvent.VK_LEFT)){
            movement.sumUpdate(Vector.LEFT());
        }else if(isKeyDown(KeyEvent.VK_RIGHT)) {
            movement.sumUpdate(Vector.RIGHT());
        }

        return movement.normalize();
    }

    //#endregion

}

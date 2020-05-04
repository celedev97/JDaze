package dev.federicocapece.jdaze;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashSet;

public class Input {
    HashSet<Integer> pressed = new HashSet<>();

    //since it's protected it cannot be instantiated outside this package
    protected Input(){

    }

    protected KeyListener keyListener = new KeyListener() {
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

    public boolean isKeyDown(int keyCode){
        return pressed.contains(keyCode);
    }

    public boolean isKeyUp(int keyCode){
        return !isKeyDown(keyCode);
    }


}

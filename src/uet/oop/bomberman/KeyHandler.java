package uet.oop.bomberman;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
public class KeyHandler implements KeyListener {
    public boolean upPressed, downPressed, leftPressed, rightPressed;
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if(code == KeyEvent.VK_UP) {
            upPressed = true;
        }
        if(code == KeyEvent.VK_UP) {
            downPressed = true;
        }
        if(code == KeyEvent.VK_UP) {
            leftPressed = true;
        }
        if(code == KeyEvent.VK_UP) {
            rightPressed = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        if(code == KeyEvent.VK_UP) {
            upPressed = false;
        }
        if(code == KeyEvent.VK_UP) {
            downPressed = false;
        }
        if(code == KeyEvent.VK_UP) {
            leftPressed = false;
        }
        if(code == KeyEvent.VK_UP) {
            rightPressed = false;
        }
    }
}

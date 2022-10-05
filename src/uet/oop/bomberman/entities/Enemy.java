package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.Map;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Enemy extends Entity{
    protected STATE state = null;
    protected int num = 1;

    public enum STATE {
        UP,
        DOWN,
        LEFT,
        RIGHT,
        DEAD,;

        private static final List<STATE> VALUES =
                Collections.unmodifiableList(Arrays.asList(values()));
        private static final int SIZE = VALUES.size() - 1;
        private static final Random RANDOM = new Random();

        public static STATE randomLetter()  {
            return VALUES.get(RANDOM.nextInt(SIZE));
        }
    }
    public STATE getState() {
        return state;
    }

    public void setState(STATE state) {
        this.state = state;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public Enemy(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
        velocity = 10;
    }

    public void moveUp() {
        this.y -= velocity;
    }

    public void moveDown() {
        this.y += velocity;
    }

    public void moveLeft() {
        this.x -= velocity;
    }
    public void moveRight() {
        this.x += velocity;
    }

    @Override
    public void update(Map m) {

    }
}

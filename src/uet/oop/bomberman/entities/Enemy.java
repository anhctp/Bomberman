package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import static uet.oop.bomberman.BombermanGame.entities;
import static uet.oop.bomberman.BombermanGame.m;

public class Enemy extends Entity {
    protected STATE state = null;
    protected int num = 1;
    protected int time = 10;
    protected int timeDead = 100;
    protected boolean appear = false;
    protected boolean isDead = false;


    public enum STATE {
        UP,
        DOWN,
        LEFT,
        RIGHT,
        DEAD,
        ;

        private static final List<STATE> VALUES =
                Collections.unmodifiableList(Arrays.asList(values()));
        private static final int SIZE = VALUES.size() - 1;
        private static final Random RANDOM = new Random();

        public static STATE randomLetter() {
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
        velocity = 8;
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


    public void countDown() {
        if (time == 0) {
            appear = true;
            time = 10;
        } else {
            time--;
            appear = false;
        }
    }

    public void setImg() {

    }

    public void dead(Enemy enemy) {
        Grass grass = new Grass(enemy.x, enemy.y, Sprite.grass.getFxImage());
        int i = entities.indexOf(enemy);
        entities.set(i, grass);
        System.out.println(entities.get(i).getClass());
//        m.getMap().set(i, grass);
    }

    @Override
    public void update() {

    }
}

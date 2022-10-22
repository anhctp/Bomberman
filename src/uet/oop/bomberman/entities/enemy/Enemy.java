package uet.oop.bomberman.entities.enemy;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.Grass;
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


    public boolean checkUp(Enemy enemy) {
//        if (getEntity((entity.getX() + Sprite.DEFAULT_SIZE) / 32, (entity.getY() - entity.getVelocity()) / 32).goThrough) {
        if (m.getEntity((enemy.getX()) / 32, (enemy.getY() - enemy.getVelocity()) / 32).goThrough) {
            return true;
        }
        return false;
    }

    public boolean checkDown(Enemy enemy) {
//        if (getEntity((entity.getX() + Sprite.DEFAULT_SIZE) / 32, (entity.getY() + entity.getVelocity() + Sprite.SCALED_SIZE) / 32).goThrough) {
        if (m.getEntity((enemy.getX() + Sprite.DEFAULT_SIZE) / 32, (enemy.getY()  + 2 * Sprite.DEFAULT_SIZE) / 32).goThrough) {
            return true;
        }
        return false;
    }

    public boolean checkLeft(Enemy enemy) {
        //if (getEntity((entity.getX() - entity.getVelocity()) / 32, entity.getY() / 32).goThrough) {
        if (m.getEntity((enemy.getX() - enemy.getVelocity()) / 32, enemy.getY() / 32).goThrough) {
            return true;
        }
        return false;
    }

    public boolean checkRight(Enemy enemy) {
        //if (getEntity((entity.getX() + entity.getVelocity() + Sprite.SCALED_SIZE) / 32, (entity.getY() + Sprite.DEFAULT_SIZE) / 32).goThrough) {
        if (m.getEntity((enemy.getX()  + 2 * Sprite.DEFAULT_SIZE) / 32, (enemy.getY() + Sprite.DEFAULT_SIZE) / 32).goThrough) {
            return true;
        }
        return false;
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

    public void logicEnemy(Enemy enemy) {
        if (state != null) {
            switch (state) {
                case RIGHT:
                    if (checkRight(enemy)) {
                        this.setState(Enemy.STATE.RIGHT);
                        moveRight();
                        setImg();
                    } else state = null;
                    break;
                case LEFT:
                    if (checkLeft(enemy)) {
                        this.setState(Enemy.STATE.LEFT);
                        moveLeft();
                        setImg();
                    } else state = null;
                    break;
                case UP:
                    if (checkUp(enemy)) {
                        this.setState(Enemy.STATE.UP);
                        moveUp();
                        setImg();
                    } else state = null;
                    break;
                case DOWN:
                    if (checkDown(enemy)) {
                        this.setState(Enemy.STATE.DOWN);
                        moveDown();
                        setImg();
                    } else state = null;
                    break;
                case DEAD:
                    setImg();
                    isDead = true;
                    break;
            }
        } else state = Enemy.STATE.randomLetter();
    }

    public void dead(Enemy enemy) {
        Grass grass = new Grass(enemy.x, enemy.y, Sprite.grass.getFxImage());
        int i = entities.indexOf(enemy);
        entities.set(i, grass);
    }

    @Override
    public void update() {

    }
}

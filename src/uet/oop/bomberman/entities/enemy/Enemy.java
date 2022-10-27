package uet.oop.bomberman.entities.enemy;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Character;
import uet.oop.bomberman.entities.Grass;
import uet.oop.bomberman.graphics.Sprite;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import static uet.oop.bomberman.BombermanGame.entities;
import static uet.oop.bomberman.BombermanGame.soundEffect;

public class Enemy extends Character {
    public static int countEnemy = 0;
    protected STATE state = null;
    protected int num = 1;
    protected int time = 10;
    protected int timeDead = 100;
    protected boolean appear = false;
    protected boolean isDead = false;
    protected boolean isPlaySoundDead = false;

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

    public void setState(STATE state) {
        this.state = state;
    }

    public Enemy(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
        countEnemy++;
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
        countEnemy--;
        Grass grass = new Grass(enemy.x, enemy.y, Sprite.grass.getFxImage());
        int i = entities.indexOf(enemy);
        entities.set(i, grass);
    }

    @Override
    public void update() {
        if (isDead) {
            if (!isPlaySoundDead) {
                soundEffect("res/Sound/kill.wav");
                isPlaySoundDead = true;
            }
            if (timeDead == 0) {
                dead(this);
                appear = false;
            } else timeDead--;
        }
        countDown();
        if (appear) {
            logicEnemy(this);
        }
    }
}
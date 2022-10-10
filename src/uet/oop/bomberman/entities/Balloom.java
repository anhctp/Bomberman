package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.Map;
import uet.oop.bomberman.graphics.Sprite;

import static uet.oop.bomberman.BombermanGame.m;

public class Balloom extends Enemy {

    public Balloom(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    @Override
    public void setImg() {
        switch (state) {
            case LEFT:
                num++;
                if (num > 3) {
                    num = 1;
                }
                this.img = Sprite.movingSprite(Sprite.balloom_left1, Sprite.balloom_left2, Sprite.balloom_left3, num, 4).getFxImage();
                break;
            case RIGHT:
                num++;
                if (num > 3) {
                    num = 1;
                }
                this.img = Sprite.movingSprite(Sprite.balloom_right1, Sprite.balloom_right2, Sprite.balloom_right3, num, 4).getFxImage();
                break;
            case DEAD:
                this.img = Sprite.balloom_dead.getFxImage();
                break;
        }
    }

    public void logicBalloom(Map m) {
        if (state != null) {
            switch (state) {
                case RIGHT:
                    if (m.checkRight(this)) {
                        this.setState(Enemy.STATE.RIGHT);
                        moveRight();
                        setImg();
                    } else state = null;
                    break;
                case LEFT:
                    if (m.checkLeft(this)) {
                        this.setState(Enemy.STATE.LEFT);
                        moveLeft();
                        setImg();
                    } else state = null;
                    break;
                case UP:
                    if (m.checkUp(this)) {
                        this.setState(Enemy.STATE.UP);
                        moveUp();
                        setImg();
                    } else state = null;
                    break;
                case DOWN:
                    if (m.checkDown(this)) {
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
    @Override
    public void update() {
        if (isDead) {
            if (timeDead == 0) {
                dead(this);
                appear = false;
            }
            else timeDead--;
        }
        countDown();
        if (appear) {
            logicBalloom(m);
        }
    }
}

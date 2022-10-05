package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.Map;
import uet.oop.bomberman.graphics.Sprite;

public class Balloom extends Enemy {
    public Balloom(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    public void setImg() {
        switch (state) {
            case LEFT:
                if (num == 1) {
                    this.img = Sprite.balloom_left1.getFxImage();
                    num = 2;
                } else if (num == 2) {
                    this.img = Sprite.balloom_left2.getFxImage();
                    num = 3;
                } else if (num == 3) {
                    this.img = Sprite.balloom_left3.getFxImage();
                    num = 1;
                }
                break;
            case RIGHT:
                if (num == 1) {
                    this.img = Sprite.balloom_right1.getFxImage();
                    num = 2;
                } else if (num == 2) {
                    this.img = Sprite.balloom_right2.getFxImage();
                    num = 3;
                } else if (num == 3) {
                    this.img = Sprite.balloom_right3.getFxImage();
                    num = 1;
                }
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
                        System.out.println("rightBallom");
                        this.setState(Enemy.STATE.RIGHT);
                        moveRight();
                        setImg();
                        System.out.println(x + "Bl");
                    } else state = null;
                    break;
                case LEFT:
                    if (m.checkLeft(this)) {
                        System.out.println("leftBallom");
                        this.setState(Enemy.STATE.LEFT);
                        moveLeft();
                        setImg();
                        System.out.println(x + "Bl");
                    } else state = null;
                    break;
                case UP:
                    if (m.checkUp(this)) {
                        System.out.println("upBallom");
                        this.setState(Enemy.STATE.UP);
                        moveUp();
                        setImg();
                        System.out.println(y+ "Bl");
                    } else state = null;
                    break;
                case DOWN:
                    if (m.checkDown(this)) {
                        System.out.println("downBallom");
                        this.setState(Enemy.STATE.DOWN);
                        moveDown();
                        setImg();
                        System.out.println(y+ "Bl");
                    } else state = null;
                    break;
                case DEAD:
                    System.out.println("dead");
                    setImg();
                    break;
            }
        } else state = Enemy.STATE.randomLetter();
    }


    @Override
    public void update(Map m) {
        try {
            Thread.sleep(270);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
        logicBalloom(m);
    }
}

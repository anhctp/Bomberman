package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.Map;
import uet.oop.bomberman.graphics.Sprite;

public class Balloom extends Enemy{
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
                    super.img = Sprite.balloom_dead.getFxImage();
                    break;
            }
        }

        public void logicBalloom(Map m) {
            if (state != null) {
                switch (state) {
                    case RIGHT:
                        System.out.println("right");
                        if (m.checkRight(this)) {
                            this.setState(Enemy.STATE.RIGHT);
                            moveRight();
                            setImg();
                        }
                        else state = null;
                        break;
                    case LEFT:
                        System.out.println("left");
                        if (m.checkLeft(this)) {
                            this.setState(Enemy.STATE.LEFT);
                            moveLeft();
                            setImg();
                        }
                        else state = null;
                        break;
                    case UP:
                        System.out.println("up");
                        if (m.checkUp(this)) {
                            this.setState(Enemy.STATE.UP);
                            moveUp();
                            setImg();
                        }
                        else state = null;
                        break;
                    case DOWN:
                        System.out.println("down");
                        if (m.checkDown(this)) {
                            this.setState(Enemy.STATE.DOWN);
                            moveDown();
                            setImg();
                        }
                        else state = null;
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
                Thread.sleep(90);
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
            logicBalloom(m);
        }


}

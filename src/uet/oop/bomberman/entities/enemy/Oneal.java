package uet.oop.bomberman.entities.enemy;

import javafx.scene.image.Image;
import uet.oop.bomberman.AI.AStar;
import uet.oop.bomberman.entities.*;
import uet.oop.bomberman.graphics.Sprite;

import java.util.ArrayList;
import java.util.List;

import static uet.oop.bomberman.BombermanGame.*;
import static uet.oop.bomberman.Map.width;
import static uet.oop.bomberman.Map.height;

public class Oneal extends Enemy {

    private AStar aStar;

    private int countToChangeVelocity = 150;

    public Oneal(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
        velocity = 4;
    }

    public void applyAStar() {
        if (this.x % 32 == 0 && this.y % 32 == 0) {
            List<List<Integer>> blocks = new ArrayList<>();
            blocks.add(new ArrayList<>());
            blocks.add(new ArrayList<>());
            for (Entity entity : m.map) {
                if ((entity instanceof Bomb) || (entity instanceof Wall) || (entity instanceof Brick)) {
                    blocks.get(0).add(entity.getX() / 32);
                    blocks.get(1).add(entity.getY() / 32);
                }
            }
            AStar aStarInit = new AStar(width, height, this.x / 32, this.y / 32, (bomberman.getX() + 8) / 32, (bomberman.getY() + 8) / 32, blocks);
            aStar = aStarInit;
            aStar.process();
            if (aStar.getPath() != null) {
                if (aStar.getPath().get(0).size() > 2) {
                    int nextX = aStar.getPath().get(0).get(aStar.getPath().get(0).size() - 1);
                    int nextY = aStar.getPath().get(1).get(aStar.getPath().get(1).size() - 1);
                    if (this.y / 32 > nextY) {
                        this.state = STATE.UP;
                    }
                    if (this.y / 32 < nextY) {
                        this.state = STATE.DOWN;
                    }
                    if (this.x / 32 > nextX) {
                        this.state = STATE.LEFT;
                    }
                    if (this.x / 32 < nextX) {
                        this.state = STATE.RIGHT;
                    }
                } else {
                    if (this.y / 32 > bomberman.getY() / 32) {
                        this.state = STATE.UP;
                    }
                    if (this.y / 32 < bomberman.getY() / 32) {
                        this.state = STATE.DOWN;
                    }
                    if (this.x / 32 > bomberman.getX() / 32) {
                        this.state = STATE.LEFT;
                    }
                    if (this.x / 32 < bomberman.getX() / 32) {
                        this.state = STATE.RIGHT;
                    }
                }
            }
        }
    }

    @Override
    public void setImg() {
        switch (state) {
            case LEFT:
                num++;
                if (num > 3) {
                    num = 1;
                }
                this.img = Sprite.movingSprite(Sprite.oneal_left1, Sprite.oneal_left2, Sprite.oneal_left3, num, 4).getFxImage();
                break;
            case DEAD:
                this.img = Sprite.oneal_dead.getFxImage();
                break;
            default:
                num++;
                if (num > 3) {
                    num = 1;
                }
                this.img = Sprite.movingSprite(Sprite.oneal_right1, Sprite.oneal_right2, Sprite.oneal_right3, num, 4).getFxImage();
                break;
        }
    }

    private int randomVelocity() {
        int num = (int) (Math.random() * 2 + 1);
        switch (num) {
            case 1:
                countToChangeVelocity = 320;
                return 1;
            case 2:
                countToChangeVelocity = 160;
                return 2;
            case 3:
                countToChangeVelocity = 80;
                return 4;
            default:
                countToChangeVelocity = 40;
                return 8;
        }
    }

    @Override
    public void update() {
        applyAStar();
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

        if (countToChangeVelocity == 0) {
            velocity = randomVelocity();
        } else {
            countToChangeVelocity -= velocity;
        }
        countDown();
        if (appear) {
            logicEnemy(this);
        }
    }
}
package uet.oop.bomberman.entities.enemy;

import javafx.scene.image.Image;
import uet.oop.bomberman.AI.AStar;
import uet.oop.bomberman.entities.Bomber;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;

import static uet.oop.bomberman.Map.width;
import static uet.oop.bomberman.Map.height;
import static uet.oop.bomberman.Map.getBlocks;
import static uet.oop.bomberman.BombermanGame.entities;

public class Oneal extends Enemy {

    private AStar aStar;

    public AStar getAStar() {
        return aStar;
    }

    public void setAStar(AStar aStar) {
        this.aStar = aStar;
    }

    public Oneal(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    public void applyAStar() {
        if (this.x % 32 == 0 && this.y % 32 == 0) {
            Bomber bomberman = new Bomber(0, 0, Sprite.player_down.getFxImage());
            for (Entity e : entities) {
                if (e instanceof Bomber) {
                    bomberman = (Bomber) e;
                }
            }
            AStar aStarInit = new AStar(width, height, this.x / 32, this.y / 32, bomberman.getX() / 32, bomberman.getY() / 32, getBlocks);
            aStar = aStarInit;
            aStar.process();
            if (aStar.getPath() != null) {

                if (aStar.getPath().size() > 2) {
                    int nextX = aStar.getPath().get(0).get(aStar.getPath().get(0).size() - 2);
                    int nextY = aStar.getPath().get(1).get(aStar.getPath().get(1).size() - 2);
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
            case RIGHT:
                num++;
                if (num > 3) {
                    num = 1;
                }
                this.img = Sprite.movingSprite(Sprite.oneal_right1, Sprite.oneal_right2, Sprite.oneal_right3, num, 4).getFxImage();
                break;
            case DEAD:
                this.img = Sprite.oneal_dead.getFxImage();
                break;
        }
    }

    @Override
    public void update() {
        applyAStar();
        if (isDead) {
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

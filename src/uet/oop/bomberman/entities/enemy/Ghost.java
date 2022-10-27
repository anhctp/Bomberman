package uet.oop.bomberman.entities.enemy;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Wall;
import uet.oop.bomberman.graphics.Sprite;

import static uet.oop.bomberman.BombermanGame.m;
import static uet.oop.bomberman.BombermanGame.soundEffect;

public class Ghost extends Enemy {

    public Ghost(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
        velocity = 8;
    }

    @Override
    public void setImg() {
        switch (state) {
            case LEFT:
                num++;
                if (num > 3) {
                    num = 1;
                }
                this.img = Sprite.movingSprite(Sprite.doll_left1, Sprite.doll_left2, Sprite.doll_left3, num, 4).getFxImage();
                break;
            case DEAD:
                this.img = Sprite.doll_dead.getFxImage();
                break;
            default:
                num++;
                if (num > 3) {
                    num = 1;
                }
                this.img = Sprite.movingSprite(Sprite.doll_right1, Sprite.doll_right2, Sprite.doll_right3, num, 4).getFxImage();
                break;
        }
    }

    @Override
    public boolean checkUp(Enemy enemy) {
        return !m.getEntity((enemy.getX()) / 32, (enemy.getY() - enemy.getVelocity()) / 32).getClass().equals(Wall.class);
    }

    @Override
    public boolean checkDown(Enemy enemy) {
        return !m.getEntity((enemy.getX() + Sprite.DEFAULT_SIZE) / 32, (enemy.getY() + 2 * Sprite.DEFAULT_SIZE) / 32).getClass().equals(Wall.class);
    }

    @Override
    public boolean checkLeft(Enemy enemy) {
        return m.getEntity((enemy.getX() - enemy.getVelocity()) / 32, enemy.getY() / 32).getClass().equals(Wall.class);
    }

    @Override
    public boolean checkRight(Enemy enemy) {
        return m.getEntity((enemy.getX() + 2 * Sprite.DEFAULT_SIZE) / 32, (enemy.getY() + Sprite.DEFAULT_SIZE) / 32).getClass().equals(Wall.class);
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

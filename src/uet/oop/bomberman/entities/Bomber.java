package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.Map;
import uet.oop.bomberman.graphics.Sprite;

import java.util.List;

public class Bomber extends Entity {
    private static final int STEP = 32;
    private int numOfBomb = 1;
    public Bomber(int x, int y, Image img) {
        super( x, y, img);
        this.velocity = 16;
    }

    @Override
    public void update(Map m) {

    }

    public void moveUp() {
        if(y > 0) {
            y -= STEP;
        }
        this.img = Sprite.player_up.getFxImage();
    }
    public void moveDown() {
        if(y < Map.height * 28) {
            y += STEP;
        }
        this.img = Sprite.player_down.getFxImage();
    }
    public void moveLeft() {
        if(x > 0) {
            x -= STEP;
        }
        this.img = Sprite.player_left.getFxImage();
    }
    public void moveRight() {
        if(x < Map.width * 30) {
            x += STEP;
        }
        this.img = Sprite.player_right.getFxImage();
    }

    public void setBomb(int x, int y, Map m, List<Entity> stillObjects) {
        if(numOfBomb > 0) {
            Entity bomb = new Bomb(x, y, Sprite.bomb.getFxImage());
            m.changeEntity(x, y, bomb, stillObjects);
            numOfBomb--;
        }
//        wait(2);
    }
}

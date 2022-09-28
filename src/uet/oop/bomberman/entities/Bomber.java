package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.Map;
import uet.oop.bomberman.graphics.Sprite;

public class Bomber extends Entity {
    private static final int STEP = 32;
    public Bomber(int x, int y, Image img) {
        super( x, y, img);
    }

    @Override
    public void update() {

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

}

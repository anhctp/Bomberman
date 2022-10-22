package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.Map;
import uet.oop.bomberman.graphics.Sprite;

public class Wall extends Entity {

    public Wall(int x, int y, Image img) {
        super(x, y, img);
        goThrough = false;
        left = x;
        right = x + Sprite.wall.get_realWidth();
        top = y;
        bottom = y + Sprite.wall.get_realHeight();
    }
    @Override
    public void update() {

    }
}

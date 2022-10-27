package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.Map;
import uet.oop.bomberman.graphics.Sprite;

import static uet.oop.bomberman.BombermanGame.m;

public class Flame extends Entity {
    public Flame(int x, int y, Image img) {
        super(x, y, img);
        left = x;
        right = x + 32;
        top = y;
        bottom = y + 32;
    }

    @Override
    public void update() {
    }
}

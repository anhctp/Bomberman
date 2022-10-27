package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;

public class Brick extends Entity {
    private boolean exploded = false;
    protected boolean hasItem = false;
    protected Entity item;

    public Brick(int x, int y, Image img) {
        super(x, y, img);
        goThrough = false;
        left = x;
        right = x + Sprite.brick.get_realWidth();
        top = y;
        bottom = y + Sprite.brick.get_realHeight();
    }
    public Brick(int x, int y, Image img, boolean hasItem, Entity item) {
        super(x, y, img);
        goThrough = false;
        left = x;
        right = x + Sprite.brick.get_realWidth();
        top = y;
        bottom = y + Sprite.brick.get_realHeight();
        this.hasItem = hasItem;
        this.item = item;
    }

    @Override
    public void update() {
        if (exploded) {
            appear = false;
        }
    }
}


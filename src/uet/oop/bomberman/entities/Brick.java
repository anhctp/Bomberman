package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.Map;

public class Brick extends Entity {
    private boolean exploded = false;

    public Brick(int x, int y, Image img) {
        super(x, y, img);
        goThrough = false;
    }

    public void setExploded(boolean exploded) {
        this.exploded = exploded;
    }

    public boolean isExploded() {
        return exploded;
    }

    @Override
    public void update() {
        if (exploded) {
            appear = false;
        }
    }
}


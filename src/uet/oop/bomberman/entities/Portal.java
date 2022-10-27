package uet.oop.bomberman.entities;

import javafx.scene.image.Image;

public class Portal extends StaticEntity {
    public Portal(int x, int y, Image img) {
        super(x, y, img);
        goThrough = false;
    }

    @Override
    public void update() {

    }
}

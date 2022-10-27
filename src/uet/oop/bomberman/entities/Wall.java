package uet.oop.bomberman.entities;

import javafx.scene.image.Image;

public class Wall extends StaticEntity {

    public Wall(int x, int y, Image img) {
        super(x, y, img);
        goThrough = false;
    }
    @Override
    public void update() {

    }
}

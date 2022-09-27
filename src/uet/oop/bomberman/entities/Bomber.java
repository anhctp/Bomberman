package uet.oop.bomberman.entities;

import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import uet.oop.bomberman.graphics.Sprite;

public class Bomber extends Entity {

    public Bomber(int x, int y, Image img) {
        super(x, y, img);
    }

    public void moveUp() {
        img = Sprite.player_up.getFxImage();
        y -= 32;
    }

    public void moveDown() {
        img = Sprite.player_down.getFxImage();
        y += 32;
    }

    public void moveLeft() {
        img = Sprite.player_left.getFxImage();
        x -= 32;
    }

    public void moveRight() {
        img = Sprite.player_right.getFxImage();
        x += 32;
    }

    @Override
    public void update() {

    }
}

package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.Map;

public class Bomb extends Entity{
    public Bomb(int x, int y, Image img) {
        super(x, y, img);
        goThrough = false;
    }

    @Override
    public void update() {
    public void update(Map m) {

    }
}

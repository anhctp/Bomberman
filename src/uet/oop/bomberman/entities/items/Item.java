package uet.oop.bomberman.entities.items;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Entity;


public abstract class Item extends Entity {
    public Item(int x, int y, Image img) {
        super(x, y, img);
    }
}

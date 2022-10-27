package uet.oop.bomberman.entities.enemy;

import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;

public class Balloom extends Enemy {

    public Balloom(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
        velocity = 4;
    }

    @Override
    public void setImg() {
        switch (state) {
            case LEFT:
                num++;
                if (num > 3) {
                    num = 1;
                }
                this.img = Sprite.movingSprite(Sprite.balloom_left1, Sprite.balloom_left2, Sprite.balloom_left3, num, 4).getFxImage();
                break;
            case DEAD:
                this.img = Sprite.balloom_dead.getFxImage();
                break;
            default:
                num++;
                if (num > 3) {
                    num = 1;
                }
                this.img = Sprite.movingSprite(Sprite.balloom_right1, Sprite.balloom_right2, Sprite.balloom_right3, num, 4).getFxImage();
                break;
        }
    }

}

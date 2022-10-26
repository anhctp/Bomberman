package uet.oop.bomberman.entities;

import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import uet.oop.bomberman.Map;
import uet.oop.bomberman.graphics.Sprite;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public abstract class Entity {
    //Tọa độ X tính từ góc trái trên trong Canvas
    protected int left;
    protected int right;
    protected int top;
    protected int bottom;

    protected int velocity = 0;
    public boolean goThrough = true;
    protected int x;
    //Tọa độ Y tính từ góc trái trên trong Canvas
    protected int y;
    public int getLeft() {
        return left;
    }

    public int getRight() {
        return right;
    }

    public int getTop() {
        return top;
    }

    public int getBottom() {
        return bottom;
    }

    protected boolean appear = true;



    protected Image img;

    //Khởi tạo đối tượng, chuyển từ tọa độ đơn vị sang tọa độ trong canvas
    public Entity( int xUnit, int yUnit, Image img) {
        this.x = xUnit * Sprite.SCALED_SIZE;
        this.y = yUnit * Sprite.SCALED_SIZE;
        this.img = img;
    }

    public int getVelocity() {
        return velocity;
    }

    public void setVelocity(int velocity) {
        this.velocity = velocity;
    }
    //public abstract void update(Map m);

    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean getAppear() {
        return appear;
    }

    public void render(GraphicsContext gc) {
        if (appear) {
            gc.drawImage(img, x, y);
        }
    }

    public abstract void update();
}
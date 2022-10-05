package uet.oop.bomberman.entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.Map;
import uet.oop.bomberman.graphics.Sprite;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Bomber extends Entity {
    private static final int STEP = 32;
    private int maxBomb = 1; //
    public List<Bomb> bombs = new ArrayList<>();
    private int timeBetween2Bomb = 240;
    public Bomber(int x, int y, Image img) {
        super( x, y, img);
        this.velocity = 16;
    }

    @Override
    public void update(Map m) {

    public void update() {
        for(int i = 0; i < bombs.size(); i++) {
            if(bombs.get(i).getIsExplode()) {
                bombs.remove(bombs.get(i));
            }
        }
    }

    public void moveUp() {
        if(y > 0) {
            y -= STEP;
        }
        this.img = Sprite.player_up.getFxImage();
    }
    public void moveDown() {
        if(y < Map.height * 28) {
            y += STEP;
        }
        this.img = Sprite.player_down.getFxImage();
    }
    public void moveLeft() {
        if(x > 0) {
            x -= STEP;
        }
        this.img = Sprite.player_left.getFxImage();
    }
    public void moveRight() {
        if(x < Map.width * 30) {
            x += STEP;
        }
        this.img = Sprite.player_right.getFxImage();
    }

    public void handleEvent(KeyEvent keyEvent, Map m, List<Entity> entities, List<Entity> stillObjects, GraphicsContext gc) {
        switch (keyEvent.getCode()) {
            case UP: case W:
                if(m.getEntity(this.getX() / 32, this.getY() / 32 - 1).goThrough) {
                    this.moveUp();
                }
                break;
            case DOWN: case S:
                if(m.getEntity(this.getX() / 32, this.getY() / 32 + 1).goThrough) {
                    this.moveDown();
                }
                break;
            case LEFT: case A:
                if(m.getEntity(this.getX() / 32 - 1, this.getY() / 32).goThrough) {
                    this.moveLeft();
                }
                break;
            case RIGHT: case D:
                if(m.getEntity(this.getX() / 32 + 1, this.getY() / 32).goThrough) {
                    this.moveRight();
                }
                break;
            case SPACE:
                if(bombs.size() < maxBomb) {
                    Bomb bomb = new Bomb(x / 32, y / 32, Sprite.bomb.getFxImage());
                    bomb.setBomb(entities, bomb);
                    bombs.add(bomb);
//                    if(m.getEntity(x / 32, y / 32 - 1) instanceof Brick) {
//                        ((Brick) m.getEntity(x / 32, y / 32 - 1)).setExploded();
//                    }
//                    if(bomb.getIsExplode()) {
//                        System.out.println("exploded!");
//                        m.updateAfterExplode(x, y, stillObjects);
//                    }
                }
                break;
            default:
                break;
        }
    }
}

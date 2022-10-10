package uet.oop.bomberman.entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.Map;
import uet.oop.bomberman.Map;
import uet.oop.bomberman.graphics.Sprite;

import java.util.ArrayList;
import java.util.List;

public class Bomb extends Entity {
    private int beforeExplodeTime;
    private int explodeTime;
    protected List<Flame> flames = new ArrayList<>();
    private boolean isAcitve;
    private boolean isExplode;
    private static int timeBetween2Bomb = 240;
    public Bomb(int x, int y, Image img) {
        super(x, y, img);
        goThrough = false;
        beforeExplodeTime = 120;
        explodeTime = 120;
        isAcitve = false;
        isExplode = false;
//        flames.clear();
    }

    public boolean getIsExplode() {
        return isExplode;
    }

    public void setBomb(List<Entity> entities, Bomb bomb) {
        entities.add(bomb);
    }
    @Override
    public void update() {
        updateBomb();
        if(beforeExplodeTime == 0) {
            explode(x, y);
        }
        if(isAcitve) {
            updateFlames();
        }
    }


    public void updateBomb() {
        if(beforeExplodeTime == 0) {
            appear = false;
        } else beforeExplodeTime--;
    }
    public void updateFlames() {
        if(explodeTime == 0) {
            for(Flame flame : flames) {
                flame.appear = false;
                isExplode = true;
            }
        } else explodeTime--;
    }

    public void explode(int x, int y) {
        flames.clear();
        Flame flameCenter = new Flame(x / 32, y / 32, Sprite.bomb_exploded.getFxImage());
        Flame flameAbove = new Flame(x / 32, y / 32 - 1, Sprite.explosion_vertical_top_last.getFxImage());
        Flame flameUnder = new Flame(x / 32, y  / 32 + 1, Sprite.explosion_vertical_down_last.getFxImage());
        Flame flameLeft = new Flame(x / 32 - 1, y / 32, Sprite.explosion_horizontal_left_last.getFxImage());
        Flame flameRight = new Flame(x / 32 + 1, y / 32, Sprite.explosion_horizontal_right_last.getFxImage());
        flames.add(flameCenter);
        flames.add(flameAbove);
        flames.add(flameUnder);
        flames.add(flameLeft);
        flames.add(flameRight);
        isAcitve = true;
    }
    @Override
    public void render(GraphicsContext gc) {
        if(beforeExplodeTime == 0) {
            renderFlame(gc);
        } else {
            super.render(gc);
        }
    }
    public void renderFlame(GraphicsContext gc) {
        for(Flame flame : flames) {
            flame.render(gc);
        }
    }
}

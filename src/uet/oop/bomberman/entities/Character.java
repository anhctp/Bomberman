package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.enemy.Enemy;
import uet.oop.bomberman.graphics.Sprite;

import static uet.oop.bomberman.BombermanGame.m;

public class Character extends Entity {

    public Character(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    public void moveUp() {
        this.y -= velocity;
    }

    public void moveDown() {
        this.y += velocity;
    }

    public void moveLeft() {
        this.x -= velocity;
    }

    public void moveRight() {
        this.x += velocity;
    }


    public boolean checkUp(Enemy enemy) {
        return m.getEntity((enemy.getX()) / 32, (enemy.getY() - enemy.getVelocity()) / 32).goThrough;
    }

    public boolean checkDown(Enemy enemy) {
        return m.getEntity((enemy.getX() + Sprite.DEFAULT_SIZE) / 32, (enemy.getY() + 2 * Sprite.DEFAULT_SIZE) / 32).goThrough;
    }

    public boolean checkLeft(Enemy enemy) {
        return m.getEntity((enemy.getX() - enemy.getVelocity()) / 32, enemy.getY() / 32).goThrough;
    }

    public boolean checkRight(Enemy enemy) {
        return m.getEntity((enemy.getX() + 2 * Sprite.DEFAULT_SIZE) / 32, (enemy.getY() + Sprite.DEFAULT_SIZE) / 32).goThrough;
    }

    @Override
    public void update() {

    }
}

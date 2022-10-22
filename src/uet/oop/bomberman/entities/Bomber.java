package uet.oop.bomberman.entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import uet.oop.bomberman.KeyHandler;
import uet.oop.bomberman.Map;
import uet.oop.bomberman.entities.enemy.Enemy;
import uet.oop.bomberman.entities.items.BombItem;
import uet.oop.bomberman.entities.items.FlameItem;
import uet.oop.bomberman.entities.items.SpeedItem;
import uet.oop.bomberman.graphics.Sprite;

import java.util.ArrayList;
import java.util.List;

import static uet.oop.bomberman.BombermanGame.*;

public class Bomber extends Entity {
    private static int STEP = 4;
    //    private int velocity = STEP / 8;
    private int maxBomb = 1; //
    protected int num = 1;
    public KeyHandler keyHandler;
    //    private boolean alive = true;
    public List<Bomb> bombs = new ArrayList<>();
    private int flameLvOfbbm = 1;
    private int timeBetween2Bomb = 240;
    private boolean upPressed = false;
    private boolean downPressed = false;
    private boolean leftPressed = false;
    private boolean rightPressed = false;
    private boolean spacePressed = false;
    private int deadTime = 120;

    private enum State {
        UP, DOWN, LEFT, RIGHT
    }

    boolean dead = false;
    private State state = State.RIGHT;

    public Bomber(int x, int y, Image img) {

        super(x, y, img);
//        this.keyHandler = keyHandler;
        this.velocity = 16;
        left = x;
        right = x + Sprite.player_up.get_realWidth() * 2;
        top = y;
        bottom = y + Sprite.player_up.get_realHeight() * 2;
    }

    @Override
    public void update() {
        for (Entity e : m.getObjects()) {
            if (e instanceof Portal) {
                if (m.checkCollision(this, e)) {
                    m.createMap();
                    for (Entity entity : m.getObjects()) {
                        if (entity instanceof Bomber) {
                            this.x = entity.getX();
                            this.y = entity.getY();
                            break;
                        }
                    }
                    addEntities();
                    entities.add(this);
                    break;
                }
            }
        }
        for (int i = 0; i < bombs.size(); i++) {
            if (bombs.get(i).getIsExplode()) {
                bombs.remove(bombs.get(i));
            }
        }

        if (dead(m)) {
            if (deadTime == 0) {
                appear = false;
            } else {
                deadTime--;
            }
        }
    }

    public void moveUp(GraphicsContext gc) {
        Entity aboveLeft = m.getEntity(this.getX() / 32, (this.getY() - 1) / 32);
        Entity aboveRight = m.getEntity((this.getX() + Sprite.DEFAULT_SIZE) / 32, (this.getY() - 1) / 32);
        int count = 0;
        while (aboveLeft.goThrough && aboveRight.goThrough && (count < STEP)) {
//            state = State.UP;
            y--;
            count++;
            aboveLeft = m.getEntity(this.getX() / 32, (this.getY() - 1) / 32);
            aboveRight = m.getEntity((this.getX() + Sprite.DEFAULT_SIZE) / 32, (this.getY() - 1) / 32);
            render(gc);
        }
        top = y;
        bottom = y + Sprite.player_up.get_realHeight() * 2;
        left = x;
        right = x + Sprite.player_up.get_realWidth() * 2;
        setImg();
//        System.out.println(x + " " + y);

    }

    public void moveDown(GraphicsContext gc) {
        Entity underLeft = m.getEntity(this.getX() / 32, (this.getY() + Sprite.player_down.get_realHeight() * 2 + 1) / 32);
        Entity underRight = m.getEntity((this.getX() + Sprite.DEFAULT_SIZE) / 32, (this.getY() + Sprite.player_down.get_realHeight() * 2 + 1) / 32);
        int count = 0;
        while (underLeft.goThrough && underRight.goThrough && (count < STEP)) {
//            state = State.DOWN;
            y++;
            count++;
            underLeft = m.getEntity(this.getX() / 32, (this.getY() + Sprite.SCALED_SIZE + 1) / 32);
            underRight = m.getEntity((this.getX() + Sprite.DEFAULT_SIZE) / 32, (this.getY() + Sprite.player_down.get_realHeight() * 2 + 1) / 32);

        }
        top = y;
        bottom = y + Sprite.player_down.get_realHeight() * 2;
        left = x;
        right = x + Sprite.player_down.get_realWidth() * 2;
        setImg();
//        System.out.println(x + " " + y);

    }

    public void moveLeft() {

        Entity above = m.getEntity((this.getX() - 1) / 32, this.getY() / 32);
        Entity under = m.getEntity((this.getX() - 1) / 32, (this.getY() + Sprite.player_left.get_realHeight() * 2 - STEP) / 32);
        int count = 0;

        while (above.goThrough && under.goThrough && (count < STEP)) {
//            state = State.LEFT;
            x--;
            count++;
            above = m.getEntity((this.getX() - 1) / 32, this.getY() / 32);
            under = m.getEntity((this.getX() - 1) / 32, (this.getY() + Sprite.player_left.get_realHeight() * 2 - STEP) / 32);
            update();
        }
        top = y;
        bottom = y + Sprite.player_left.get_realHeight() * 2;
        left = x;
        right = x + Sprite.player_left.get_realWidth() * 2;
        setImg();
//        System.out.println(x + " " + y);
    }

    public void moveRight() {
        Entity above = m.getEntity((this.getX() + Sprite.player_right.get_realWidth() * 2 + 1) / 32, this.getY() / 32);
        Entity under = m.getEntity((this.getX() + Sprite.player_right.get_realWidth() * 2 + 1) / 32, (this.getY() + Sprite.player_right.get_realHeight() * 2 - (STEP)) / 32);
        int count = 0;
        while (above.goThrough && under.goThrough && (count < STEP)) {
//            state = State.RIGHT;
            x++;
            count++;
            above = m.getEntity((this.getX() + Sprite.player_right.get_realWidth() * 2 + 1) / 32, this.getY() / 32);
            under = m.getEntity((this.getX() + Sprite.player_right.get_realWidth() * 2 + 1) / 32, (this.getY() + Sprite.player_right.get_realHeight() * 2 - (STEP)) / 32);
            update();
        }
        top = y;
        bottom = y + Sprite.player_right.get_realHeight() * 2;
        left = x;
        right = x + Sprite.player_right.get_realWidth() * 2;
        setImg();
//        System.out.println(x + " " + y);

    }

    public boolean checkItem(Entity entity, Entity grass) {
        if (entity instanceof SpeedItem) {
            STEP += 4;
            return true;
        }
        if (entity instanceof BombItem) {
            maxBomb += 1;
            return true;
        }
        if (entity instanceof FlameItem) {
            flameLvOfbbm++;
            for (Bomb b : bombs) {
                if (b.isAcitve() == false) {
                    b.setFlameLv(flameLvOfbbm);
                }
            }
            return true;
        }
        return false;
    }

    public void handleEvent(Map m, List<Entity> entities, GraphicsContext gc) {
        if (upPressed) {
            moveUp(gc);
            Entity grass1 = new Grass(this.getX() / 32, (this.getY()) / 32, Sprite.grass.getFxImage());
            if (checkItem(m.getEntity(this.getX() / 32, this.getY() / 32), grass1)) {
                m.changeEntity(this.getX() / 32, this.getY() / 32, grass1);
            }
        } else if (downPressed) {
            moveDown(gc);
            Entity grass2 = new Grass(this.getX() / 32, (this.getY() + Sprite.player_down.get_realHeight() * 2) / 32, Sprite.grass.getFxImage());
            if (checkItem(m.getEntity(this.getX() / 32, (this.getY() + Sprite.player_down.get_realHeight() * 2) / 32), grass2)) {
                m.changeEntity(this.getX() / 32, (this.getY() + Sprite.player_down.get_realHeight() * 2) / 32, grass2);
            }
        } else if (leftPressed) {
            moveLeft();
            Entity grass3 = new Grass(this.getX() / 32, (this.getY()) / 32, Sprite.grass.getFxImage());
            if (checkItem(m.getEntity(this.getX() / 32, this.getY() / 32), grass3)) {
                m.changeEntity(this.getX() / 32, this.getY() / 32, grass3);
            }
        } else if (rightPressed) {
            moveRight();
            Entity grass4 = new Grass((this.getX() + Sprite.player_right.get_realWidth() * 2) / 32, (this.getY()) / 32, Sprite.grass.getFxImage());
            if (checkItem(m.getEntity((this.getX() + Sprite.player_right.get_realWidth() * 2) / 32, this.getY() / 32), grass4)) {
                m.changeEntity((this.getX() + Sprite.player_right.get_realWidth() * 2) / 32, this.getY() / 32, grass4);
            }
        } else if (spacePressed) {
            if (bombs.size() < maxBomb) {
                Bomb bomb = new Bomb(x / 32, (y + 16) / 32, Sprite.bomb.getFxImage(), flameLvOfbbm); // 16 = height of bomber / 2
                bomb.setBomb(entities, bomb);
                bombs.add(bomb);
            }
        }

    }

    public void handleKeyPressed(javafx.scene.input.KeyEvent e) {
        KeyCode code = e.getCode();
        if (code == KeyCode.UP) {
            upPressed = true;
        }
        if (code == KeyCode.DOWN) {
            downPressed = true;
        }
        if (code == KeyCode.LEFT) {
            leftPressed = true;
        }
        if (code == KeyCode.RIGHT) {
            rightPressed = true;
        }
        if (code == KeyCode.SPACE) {
            spacePressed = true;
        }
    }

    public void handleKeyReleased(javafx.scene.input.KeyEvent e) {
        KeyCode code = e.getCode();
        if (code == KeyCode.UP) {
            upPressed = false;
        }
        if (code == KeyCode.DOWN) {
            downPressed = false;
        }
        if (code == KeyCode.LEFT) {
            leftPressed = false;
        }
        if (code == KeyCode.RIGHT) {
            rightPressed = false;
        }
        if (code == KeyCode.SPACE) {
            spacePressed = false;
        }
    }

    public boolean dead(Map m) {
        for (int i = 0; i < entities.size(); i++) {
            if (m.checkCollision(entities.get(i), this)) {
                if (entities.get(i) instanceof Enemy) {
                    dead = true;
                    setImg();
                }
            }
        }
        for (int i = 0; i < bombs.size(); i++) {
            for (int j = 0; j < bombs.get(i).flames.size(); j++) {
                if (m.checkCollision(bombs.get(i).flames.get(j), this)) {
                    dead = true;
                    setImg();
                }
                for (int k = 0; k < m.getObjects().size(); k++) {
                    if (m.getObjects().get(k) instanceof Enemy) {
                        if (m.checkCollision(bombs.get(i).flames.get(j), m.getObjects().get(k))) {
                            ((Enemy) m.getObjects().get(k)).setState(Enemy.STATE.DEAD);
                        }
                    }
                }
            }
        }
        return dead;
    }

    public boolean coolisionWith(Entity e) {
        int left = e.getX();
        int right = e.getX() + Sprite.SCALED_SIZE;
        int top = e.getY();
        int bottom = e.getY() + Sprite.SCALED_SIZE;

        if (this.getBottom() < top) {
            return false;
        }

        if (this.getTop() > bottom) {
            return false;
        }

        if (this.getRight() < left) {
            return false;
        }

        if (this.getLeft() > right) {
            return false;
        }
        return true;
    }

    public void setImg() {
        if (dead) {
            this.img = Sprite.movingSprite(Sprite.player_dead1, Sprite.player_dead2, Sprite.player_dead3, num, 5).getFxImage();
        } else {
            if (upPressed) {
                num++;
                if (num > 20) {
                    num = 1;
                }
                this.img = Sprite.movingSprite(Sprite.player_up, Sprite.player_up_1, Sprite.player_up_2, num, 5).getFxImage();
            }
            if (downPressed) {
                num++;
                if (num > 20) {
                    num = 1;
                }
                this.img = Sprite.movingSprite(Sprite.player_down, Sprite.player_down_1, Sprite.player_down_2, num, 5).getFxImage();
            }
            if (leftPressed) {
                num++;
                if (num > 20) {
                    num = 1;
                }
                this.img = Sprite.movingSprite(Sprite.player_left, Sprite.player_left_1, Sprite.player_left_2, num, 5).getFxImage();
            }
            if (rightPressed) {
                num++;
                if (num > 20) {
                    num = 1;
                }
                this.img = Sprite.movingSprite(Sprite.player_right, Sprite.player_right_1, Sprite.player_right_2, num, 5).getFxImage();
            }
        }
    }
}

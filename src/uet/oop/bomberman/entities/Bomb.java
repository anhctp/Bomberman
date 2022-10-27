package uet.oop.bomberman.entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.Map;
import uet.oop.bomberman.graphics.Sprite;

import java.util.ArrayList;
import java.util.List;

import static uet.oop.bomberman.BombermanGame.m;
import static uet.oop.bomberman.BombermanGame.soundEffect;

public class Bomb extends Entity {
    private int beforeExplodeTime;
    private int explodeTime;
    public boolean isPlaySoundExplosion = false;
    int num = 0;
    int numFlame = 0;
    private boolean isActive;
    private boolean isExplode;
    private int flameLv;

    public void setBeforeExplodeTime(int beforeExplodeTime) {
        this.beforeExplodeTime = beforeExplodeTime;
    }

    public int getBeforeExplodeTime() {
        return beforeExplodeTime;
    }

    public void setFlameLv(int flameLv) {
        this.flameLv = flameLv;
    }

    protected List<Flame> flames = new ArrayList<>();

    public boolean isActive() {
        return isActive;
    }

    public Bomb(int x, int y, Image img, int flameLv) {
        super(x, y, img);
        goThrough = false;
        beforeExplodeTime = 240;
        explodeTime = 100;
        isActive = false;
        isExplode = false;
        this.flameLv = flameLv;
    }

    public boolean getIsExplode() {
        return isExplode;
    }

    public void setBomb(List<Entity> entities, Bomb bomb) {
        entities.add(bomb);
        // Them bomb vao map (do check di chuyen cua entity o m.map)
        m.map.add(bomb);
    }

    @Override
    public void update() {
        setBombImg();
        updateBomb();
        if (beforeExplodeTime == 0) {
            explode(x, y, m);
        }
        if (isActive) {
            updateFlames();
        }
        if (isExplode) {
            if (m.getEntity(x / 32, y / 32 - 1) instanceof Brick) {
                if (((Brick) m.getEntity(x / 32, y / 32 - 1)).hasItem) {
                    m.changeEntity(x / 32, y / 32 - 1, ((Brick) m.getEntity(x / 32, y / 32 - 1)).item);
                } else {
                    Grass grass = new Grass(x / 32, y / 32 - 1, Sprite.grass.getFxImage());
                    m.changeEntity(x / 32, y / 32 - 1, grass);
                }
            }
            if (m.getEntity(x / 32, y / 32 + 1) instanceof Brick) {
                if (((Brick) m.getEntity(x / 32, y / 32 + 1)).hasItem) {
                    m.changeEntity(x / 32, y / 32 + 1, ((Brick) m.getEntity(x / 32, y / 32 + 1)).item);
                } else {
                    Grass grass = new Grass(x / 32, y / 32 + 1, Sprite.grass.getFxImage());
                    m.changeEntity(x / 32, y / 32 + 1, grass);
                }
            }
            if (m.getEntity(x / 32 - 1, y / 32) instanceof Brick) {
                if (((Brick) m.getEntity(x / 32 - 1, y / 32)).hasItem) {
                    m.changeEntity(x / 32 - 1, y / 32, ((Brick) m.getEntity(x / 32 - 1, y / 32)).item);
                } else {
                    Grass grass = new Grass(x / 32 - 1, y / 32, Sprite.grass.getFxImage());
                    m.changeEntity(x / 32 - 1, y / 32, grass);
                }
            }
            if (m.getEntity(x / 32 + 1, y / 32) instanceof Brick) {
                if (((Brick) m.getEntity(x / 32 + 1, y / 32)).hasItem) {
                    m.changeEntity(x / 32 + 1, y / 32, ((Brick) m.getEntity(x / 32 + 1, y / 32)).item);
                } else {
                    Grass grass = new Grass(x / 32 + 1, y / 32, Sprite.grass.getFxImage());
                    m.changeEntity(x / 32 + 1, y / 32, grass);
                }
            }
            // Remove bomb
            m.map.remove(this);
        }
    }


    public void updateBomb() {
        if (beforeExplodeTime == 0) {
            appear = false;
        } else beforeExplodeTime--;
    }

    public void updateFlames() {
        if (explodeTime == 0) {
            for (Flame flame : flames) {
                flame.appear = false;
                isExplode = true;
            }
        } else explodeTime--;
    }

    public void explode(int x, int y, Map m) {
        flames.clear();
        if (!isPlaySoundExplosion) {
            soundEffect("res/Sound/bomb.wav");
            isPlaySoundExplosion = true;
        }
        Flame flameCenter;
        Flame flameAbove1;
        Flame flameUnder1;
        Flame flameLeft1;
        Flame flameRight1;

        Flame flameAbove2;
        Flame flameUnder2;
        Flame flameLeft2;
        Flame flameRight2;

        switch (flameLv) {
            case 1:
                if (!(m.getEntity(x / 32, y / 32) instanceof Wall)) {
                    flameCenter = new Flame(x / 32, y / 32, Sprite.bomb_exploded.getFxImage());
                    flames.add(flameCenter);
                    setFlameImg(flameCenter, 0);
                }
                if (!(m.getEntity(x / 32, y / 32 - 1) instanceof Wall)) {
                    flameAbove1 = new Flame(x / 32, y / 32 - 1, Sprite.explosion_vertical_top_last.getFxImage());
                    flames.add(flameAbove1);
                    setFlameImg(flameAbove1, 1);
                }
                if (!((m.getEntity(x / 32, y / 32 + 1)) instanceof Wall)) {
                    flameUnder1 = new Flame(x / 32, y / 32 + 1, Sprite.explosion_vertical_down_last.getFxImage());
                    flames.add(flameUnder1);
                    setFlameImg(flameUnder1, 3);
                }
                if (!(m.getEntity(x / 32 - 1, y / 32) instanceof Wall)) {
                    flameLeft1 = new Flame(x / 32 - 1, y / 32, Sprite.explosion_horizontal_left_last.getFxImage());
                    flames.add(flameLeft1);
                    setFlameImg(flameLeft1, 4);
                }
                if (!(m.getEntity(x / 32 + 1, y / 32) instanceof Wall)) {
                    flameRight1 = new Flame(x / 32 + 1, y / 32, Sprite.explosion_horizontal_right_last.getFxImage());
                    flames.add(flameRight1);
                    setFlameImg(flameRight1, 2);
                }


                break;
            case 2:
                flameCenter = new Flame(x / 32, y / 32, Sprite.bomb_exploded1.getFxImage());
                flames.add(flameCenter);

                if (!(m.getEntity(x / 32, y / 32 - 1) instanceof Wall)) {
                    if (m.getEntity(x / 32, y / 32 - 1) instanceof Brick) {
                        flameAbove1 = new Flame(x / 32, y / 32 - 1, Sprite.explosion_vertical_top_last1.getFxImage());
                        flames.add(flameAbove1);
                    } else {
                        if (m.getEntity(x / 32, y / 32 - 2) instanceof Wall) {
                            flameAbove1 = new Flame(x / 32, y / 32 - 1, Sprite.explosion_vertical_top_last1.getFxImage());
                            flames.add(flameAbove1);
                        } else {
                            flameAbove1 = new Flame(x / 32, y / 32 - 1, Sprite.explosion_vertical1.getFxImage());
                            flameAbove2 = new Flame(x / 32, y / 32 - 2, Sprite.explosion_vertical_top_last1.getFxImage());
                            flames.add(flameAbove1);
                            flames.add(flameAbove2);
                        }
                    }
                }
                if (!(m.getEntity(x / 32, y / 32 + 1) instanceof Wall)) {
                    if (m.getEntity(x / 32, y / 32 + 1) instanceof Brick) {
                        flameUnder1 = new Flame(x / 32, y / 32 + 1, Sprite.explosion_vertical_down_last1.getFxImage());
                        flames.add(flameUnder1);
                    } else {
                        if (m.getEntity(x / 32, y / 32 + 2) instanceof Wall) {
                            flameUnder1 = new Flame(x / 32, y / 32 + 1, Sprite.explosion_vertical_down_last1.getFxImage());
                            flames.add(flameUnder1);
                        } else {
                            flameUnder1 = new Flame(x / 32, y / 32 + 1, Sprite.explosion_vertical1.getFxImage());
                            flameUnder2 = new Flame(x / 32, y / 32 + 2, Sprite.explosion_vertical_down_last1.getFxImage());
                            flames.add(flameUnder1);
                            flames.add(flameUnder2);
                        }
                    }
                }
                if (!(m.getEntity(x / 32 - 1, y / 32) instanceof Wall)) {
                    if (m.getEntity(x / 32 - 1, y / 32) instanceof Brick) {
                        flameLeft1 = new Flame(x / 32 - 1, y / 32, Sprite.explosion_horizontal_left_last1.getFxImage());
                        flames.add(flameLeft1);
                    } else {
                        if (m.getEntity(x / 32 - 2, y / 32) instanceof Wall) {
                            flameLeft1 = new Flame(x / 32 - 1, y / 32, Sprite.explosion_horizontal_left_last1.getFxImage());
                            flames.add(flameLeft1);
                        } else {
                            flameLeft1 = new Flame(x / 32 - 1, y / 32, Sprite.explosion_horizontal1.getFxImage());
                            flameLeft2 = new Flame(x / 32 - 2, y / 32, Sprite.explosion_horizontal_left_last1.getFxImage());
                            flames.add(flameLeft1);
                            flames.add(flameLeft2);
                        }
                    }
                }
                if (!(m.getEntity(x / 32 + 1, y / 32) instanceof Wall)) {
                    if (m.getEntity(x / 32 + 1, y / 32) instanceof Brick) {
                        flameRight1 = new Flame(x / 32 + 1, y / 32, Sprite.explosion_horizontal_right_last1.getFxImage());
                        flames.add(flameRight1);
                    } else {
                        if (m.getEntity(x / 32 + 2, y / 32) instanceof Wall) {
                            flameRight1 = new Flame(x / 32 + 1, y / 32, Sprite.explosion_horizontal_right_last1.getFxImage());
                            flames.add(flameRight1);
                        } else {
                            flameRight1 = new Flame(x / 32 + 1, y / 32, Sprite.explosion_horizontal1.getFxImage());
                            flameRight2 = new Flame(x / 32 + 2, y / 32, Sprite.explosion_horizontal_right_last1.getFxImage());
                            flames.add(flameRight1);
                            flames.add(flameRight2);
                        }
                    }
                }
                break;
            default:
                break;
        }
        isActive = true;
    }

    @Override
    public void render(GraphicsContext gc) {
        if (beforeExplodeTime == 0) {
            renderFlame(gc);
        } else {
            super.render(gc);
        }
    }

    public void renderFlame(GraphicsContext gc) {
        for (Flame flame : flames) {
            flame.render(gc);
        }
    }

    public void setBombImg() {
        if (num < 7500) {
            num++;
        } else num = 0;
        this.img = Sprite.movingSprite(Sprite.bomb, Sprite.bomb_1, Sprite.bomb_2, num, 30).getFxImage();
    }

    public void setFlameImg(Flame flame, int direction) {
        if (numFlame < 360) {
            numFlame++;
        } else numFlame = 0;
        switch (direction) {
            case 0:
                flame.img = Sprite.movingSprite(Sprite.bomb_exploded, Sprite.bomb_exploded1, Sprite.bomb_exploded2, numFlame, 120).getFxImage();
                break;
            case 1:
                flame.img = Sprite.movingSprite(Sprite.explosion_vertical_top_last, Sprite.explosion_vertical_top_last1, Sprite.explosion_vertical_top_last2, numFlame, 120).getFxImage();
                break;
            case 2:
                flame.img = Sprite.movingSprite(Sprite.explosion_horizontal_right_last, Sprite.explosion_horizontal_right_last1, Sprite.explosion_horizontal_right_last2, numFlame, 120).getFxImage();
                break;
            case 3:
                flame.img = Sprite.movingSprite(Sprite.explosion_vertical_down_last, Sprite.explosion_vertical_down_last1, Sprite.explosion_vertical_down_last2, numFlame, 120).getFxImage();
                break;
            case 4:
                flame.img = Sprite.movingSprite(Sprite.explosion_horizontal_left_last, Sprite.explosion_horizontal_left_last1, Sprite.explosion_horizontal_left_last2, numFlame, 120).getFxImage();
                break;
        }
    }
}
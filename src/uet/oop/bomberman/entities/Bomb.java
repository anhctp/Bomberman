package uet.oop.bomberman.entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.Map;
import uet.oop.bomberman.Map;
import uet.oop.bomberman.graphics.Sprite;

import javax.swing.plaf.synth.SynthRootPaneUI;
import java.util.ArrayList;
import java.util.List;

import static uet.oop.bomberman.BombermanGame.entities;
import static uet.oop.bomberman.BombermanGame.m;
import static uet.oop.bomberman.Map.map;

public class Bomb extends Entity {
    private int beforeExplodeTime;
    private int explodeTime;

    public void setFlameLv(int flameLv) {
        this.flameLv = flameLv;
    }

    public int getFlameLv() {
        return flameLv;
    }

    protected List<Flame> flames = new ArrayList<>();

    public boolean isAcitve() {
        return isAcitve;
    }

    private boolean isAcitve;
    private boolean isExplode;
    private static int timeBetween2Bomb = 240;
    private int flameLv = 1;
    public Bomb(int x, int y, Image img, int flameLv) {
        super(x, y, img);
        goThrough = false;
        beforeExplodeTime = 120;
        explodeTime = 120;
        isAcitve = false;
        isExplode = false;
        this.flameLv = flameLv;
    }


    public boolean getIsExplode() {
        return isExplode;
    }

    public void setBomb(List<Entity> entities, Bomb bomb) {
        entities.add(bomb);

        // Them bomb vao map (do check di chuyen cua entity o m.map)
//        m.map.set(m.fromPosToIndex(bomb.getX() / 32, bomb.getY() / 32), bomb);
        m.map.add(bomb);
//        entities.set(m.fromPosToIndex(bomb.getX() / 32, bomb.getY() / 32), bomb);
    }
    @Override
    public void update() {
        updateBomb();
        if(beforeExplodeTime == 0) {
            explode(x, y, m);
        }
        if(isAcitve) {
            updateFlames();
        }
        if(isExplode) {
            if(m.getEntity(x / 32, y / 32 - 1) instanceof Brick) {
                if(((Brick) m.getEntity(x / 32, y / 32 - 1)).hasItem == true) {
                    m.changeEntity(x / 32, y /32 - 1, ((Brick) m.getEntity(x / 32, y / 32 - 1)).item);
//                    entities.remove(m.getEntity(x / 32, y / 32 - 1));
                } else {
                    Grass grass = new Grass(x / 32, y / 32 - 1, Sprite.grass.getFxImage());
                    m.changeEntity(x / 32, y / 32 - 1, grass);
                }
            }
            if(m.getEntity(x / 32, y / 32 + 1) instanceof Brick) {
                if(((Brick) m.getEntity(x / 32, y / 32 + 1)).hasItem == true) {
                    m.changeEntity(x / 32, y /32 + 1, ((Brick) m.getEntity(x / 32, y / 32 + 1)).item);
//                    entities.remove(m.getEntity(x / 32, y / 32 + 1));
//                    entities.remove(m.getEntity(x / 32, y / 32 + 1));
                } else {
                    Grass grass = new Grass(x / 32, y / 32 + 1, Sprite.grass.getFxImage());
                    m.changeEntity(x / 32, y / 32 + 1, grass);
                }
            }
            if(m.getEntity(x / 32 - 1, y / 32) instanceof Brick) {
                if(((Brick) m.getEntity(x / 32 - 1, y / 32 )).hasItem == true) {
                    m.changeEntity(x / 32 - 1, y /32, ((Brick) m.getEntity(x / 32 - 1, y / 32)).item);
//                    entities.remove(m.getEntity(x / 32 - 1, y / 32));
//                    entities.remove(m.getEntity(x / 32 - 1, y / 32));
                } else {
                    Grass grass = new Grass(x / 32 - 1, y / 32, Sprite.grass.getFxImage());
                    m.changeEntity(x / 32 - 1, y / 32, grass);
                }
            }
            if(m.getEntity(x / 32 + 1, y / 32) instanceof Brick) {
                if(((Brick) m.getEntity(x / 32 + 1, y / 32)).hasItem == true) {
                    m.changeEntity(x / 32 + 1, y / 32, ((Brick) m.getEntity(x / 32 + 1, y / 32)).item);
//                    entities.remove(m.getEntity(x / 32 + 1, y / 32));
                } else {
                    Grass grass = new Grass(x / 32 + 1, y / 32, Sprite.grass.getFxImage());
                    m.changeEntity(x / 32 + 1, y / 32, grass);
                }
            }
            // Set lai bomb la grass
            Grass grass = new Grass(this.getX() / 32, this.getY() / 32, Sprite.grass.getFxImage());
            m.map.set(m.fromPosToIndex(this.getX() / 32, this.getY() / 32), grass);

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

    public void explode(int x, int y, Map m) {
        flames.clear();
        List<Flame> f = new ArrayList<>();
        Flame flameCenter;
        Flame flameAbove1;
        Flame flameUnder1;
        Flame flameLeft1;
        Flame flameRight1;

        Flame flameAbove2;
        Flame flameUnder2;
        Flame flameLeft2;
        Flame flameRight2;

        Flame flameAbove3;
        Flame flameUnder3;
        Flame flameLeft3;
        Flame flameRight3;

        switch (flameLv) {
            case 1:
                System.out.println(1);
                flameCenter = new Flame(x / 32, y / 32, Sprite.bomb_exploded.getFxImage());
                flameAbove1 = new Flame(x / 32, y / 32 - 1, Sprite.explosion_vertical_top_last.getFxImage());
                flameUnder1 = new Flame(x / 32, y  / 32 + 1, Sprite.explosion_vertical_down_last.getFxImage());
                flameLeft1 = new Flame(x / 32 - 1, y / 32, Sprite.explosion_horizontal_left_last.getFxImage());
                flameRight1 = new Flame(x / 32 + 1, y / 32, Sprite.explosion_horizontal_right_last.getFxImage());
                flames.add(flameCenter);
                flames.add(flameAbove1);
                flames.add(flameUnder1);
                flames.add(flameLeft1);
                flames.add(flameRight1);
                if(m.getEntity(x / 32, y / 32 - 1) instanceof Bomb) {
                    explode(x / 32, y / 32 - 1, m);
                }
                if (m.getEntity(x / 32, y / 32 + 1) instanceof Bomb) {
                    explode(x / 32 - 1, y / 32 + 1, m);
                }
                if(m.getEntity(x / 32 - 1, y / 32) instanceof Bomb) {
                    explode(x / 32 - 1, y / 32, m);
                }
                if(m.getEntity(x / 32 + 1, y / 32) instanceof Bomb) {
                    explode(x / 32 + 1, y / 32, m);
                }
                break;
            case 2:
                System.out.println(2);
                flameCenter = new Flame(x / 32, y / 32, Sprite.bomb_exploded1.getFxImage());
                flameAbove1 = new Flame(x / 32, y / 32 - 1, Sprite.explosion_vertical1.getFxImage());
                flameUnder1 = new Flame(x / 32, y  / 32 + 1, Sprite.explosion_vertical1.getFxImage());
                flameLeft1 = new Flame(x / 32 - 1, y / 32, Sprite.explosion_horizontal1.getFxImage());
                flameRight1 = new Flame(x / 32 + 1, y / 32, Sprite.explosion_horizontal1.getFxImage());

                if(!(m.getEntity(x / 32, y / 32 - 1) instanceof Brick)
                        && !(m.getEntity(x / 32, y / 32 - 1) instanceof Wall))
                {
                    flameAbove2 = new Flame(x / 32, y / 32 - 2, Sprite.explosion_vertical_top_last1.getFxImage());
                    flames.add(flameAbove2);
                    flames.add(flameAbove1);
                } else {
                    flameAbove1 = new Flame(x / 32, y / 32 - 1, Sprite.explosion_vertical_top_last1.getFxImage());
                    flames.add(flameAbove1);
                }
                if(!(m.getEntity(x / 32, y / 32 + 1) instanceof Brick)
                        && !(m.getEntity(x / 32, y / 32 + 1) instanceof Wall)) {
                    flameUnder2 = new Flame(x / 32, y  / 32 + 2, Sprite.explosion_vertical_down_last1.getFxImage());
                    flames.add(flameUnder2);
                    flames.add(flameUnder1);

                } else {
                    flameUnder1 = new Flame(x / 32, y / 32 + 1, Sprite.explosion_vertical_down_last1.getFxImage());
                    flames.add(flameUnder1);
                }

                if(!(m.getEntity(x / 32 - 1, y / 32) instanceof Brick)
                        && !(m.getEntity(x / 32 - 1, y / 32) instanceof Wall)) {
                    flameLeft2 = new Flame(x / 32 - 2, y / 32, Sprite.explosion_horizontal_left_last1.getFxImage());
                    flames.add(flameLeft2);
                    flames.add(flameLeft1);
                }
                else {
                    flameLeft1 = new Flame(x / 32 - 1, y / 32, Sprite.explosion_horizontal_left_last1.getFxImage());
                    flames.add(flameLeft1);
                }

                if(!(m.getEntity(x / 32 + 1, y / 32 ) instanceof Brick)
                        && !(m.getEntity(x / 32 + 1, y / 32) instanceof Wall))
                {
                    flameRight2 = new Flame(x / 32 + 2, y / 32, Sprite.explosion_horizontal_right_last1.getFxImage());
                    flames.add(flameRight2);
                    flames.add(flameRight1);
                } else {
                    flameRight1 = new Flame(x / 32 + 1, y / 32, Sprite.explosion_horizontal_right_last1.getFxImage());
                    flames.add(flameRight1);
                }

                flames.add(flameCenter);

                if(m.getEntity(x / 32, y / 32 - 1) instanceof Bomb) {
                    explode(x / 32, y / 32 - 1, m);
                }
                if (m.getEntity(x / 32, y / 32 + 1) instanceof Bomb) {
                    explode(x / 32 - 1, y / 32 + 1, m);
                }
                if(m.getEntity(x / 32 - 1, y / 32) instanceof Bomb) {
                    explode(x / 32 - 1, y / 32, m);
                }
                if(m.getEntity(x / 32 + 1, y / 32) instanceof Bomb) {
                    explode(x / 32 + 1, y / 32, m);
                }

                if(m.getEntity(x / 32, y / 32 - 2) instanceof Bomb) {
                    explode(x / 32, y / 32 - 2, m);
                }
                if (m.getEntity(x / 32, y / 32 + 2) instanceof Bomb) {
                    explode(x / 32 - 1, y / 32 + 12, m);
                }
                if(m.getEntity(x / 32 - 2, y / 32) instanceof Bomb) {
                    explode(x / 32 - 2, y / 32, m);
                }
                if(m.getEntity(x / 32 + 2, y / 32) instanceof Bomb) {
                    explode(x / 32 + 2, y / 32, m);
                }
                break;

            case 3:
                flameCenter = new Flame(x / 32, y / 32, Sprite.bomb_exploded2.getFxImage());
                flameAbove1 = new Flame(x / 32, y / 32 - 1, Sprite.explosion_vertical2.getFxImage());
                flameUnder1 = new Flame(x / 32, y  / 32 + 1, Sprite.explosion_vertical2.getFxImage());
                flameLeft1 = new Flame(x / 32 - 1, y / 32, Sprite.explosion_horizontal2.getFxImage());
                flameRight1 = new Flame(x / 32 + 1, y / 32, Sprite.explosion_horizontal2.getFxImage());

                if(!(m.getEntity(x / 32, y / 32 - 1) instanceof Brick)
                        && !(m.getEntity(x / 32, y / 32 - 1) instanceof Wall))
                {
                    flameAbove2 = new Flame(x / 32, y / 32 - 2, Sprite.explosion_vertical_top_last1.getFxImage());
                    flames.add(flameAbove2);
                }
                if(!(m.getEntity(x / 32, y / 32 + 1) instanceof Brick)
                        && !(m.getEntity(x / 32, y / 32 + 1) instanceof Wall)) {
                    flameUnder2 = new Flame(x / 32, y  / 32 + 2, Sprite.explosion_vertical_down_last1.getFxImage());
                    flames.add(flameUnder2);
                }
                if(!(m.getEntity(x / 32 - 1, y / 32) instanceof Brick)
                        && !(m.getEntity(x / 32 - 1, y / 32) instanceof Wall)) {
                    flameLeft2 = new Flame(x / 32 - 2, y / 32, Sprite.explosion_horizontal_left_last1.getFxImage());
                    flames.add(flameLeft2);
                }
                if(!(m.getEntity(x / 32 + 1, y / 32 ) instanceof Brick)
                        && !(m.getEntity(x / 32 + 1, y / 32) instanceof Wall))
                {
                    flameRight2 = new Flame(x / 32 + 2, y / 32, Sprite.explosion_horizontal_right_last1.getFxImage());
                    flames.add(flameRight2);
                }

                if((m.getEntity(x/32, y/32 - 1) instanceof Grass)
                        && (m.getEntity(x/32, y/32 - 2)instanceof Grass)) {
                    flameAbove3 = new Flame(x / 32, y / 32 - 3, Sprite.explosion_vertical_top_last2.getFxImage());
                    flames.add(flameAbove3);
                }
                if((m.getEntity(x/32, y/32 + 1) instanceof Grass)
                        && (m.getEntity(x/32, y/32 + 2)instanceof Grass)) {
                    flameUnder3 = new Flame(x / 32, y  / 32 + 3, Sprite.explosion_vertical_down_last2.getFxImage());
                    flames.add(flameUnder3);
                }
                if((m.getEntity(x/32 - 1, y/32) instanceof Grass)
                        && (m.getEntity(x/32 - 2, y/32 )instanceof Grass)) {
                    flameLeft3 = new Flame(x / 32 - 3, y / 32, Sprite.explosion_horizontal_left_last2.getFxImage());
                    flames.add(flameLeft3);
                }
                if((m.getEntity(x/32, y/32 + 1) instanceof Grass)
                        && (m.getEntity(x/32, y/32 + 2)instanceof Grass)) {
                    flameRight3 = new Flame(x / 32 + 3, y / 32, Sprite.explosion_horizontal_right_last2.getFxImage());
                    flames.add(flameRight3);
                }

                flames.add(flameCenter);
                flames.add(flameAbove1);
                flames.add(flameUnder1);
                flames.add(flameLeft1);
                flames.add(flameRight1);
                break;
            default:
                break;
        }

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

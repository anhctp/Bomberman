package uet.oop.bomberman;

import javafx.scene.canvas.GraphicsContext;
import uet.oop.bomberman.entities.*;
import uet.oop.bomberman.entities.enemy.Balloom;
import uet.oop.bomberman.entities.enemy.Oneal;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.level.Level;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static uet.oop.bomberman.BombermanGame.stillObjects;

public class Map {
    public static int width; // theo o
    public static int height; // theo o

    public static List<Entity> map = new ArrayList<>();
    public static List<Entity> bombs = new ArrayList<>();
    public static List<List<Integer>> getBlocks = new ArrayList<List<Integer>>();
    public static int nextLevel = 1;
    private List<Entity> objects = new ArrayList<>();

    public void setObjects(List<Entity> objects) {
        this.objects = objects;
    }


    public List<Entity> getObjects() {
        return objects;
    }

    public void createMap() throws IOException {
        Level level = new Level();
        level.initMapLevel();
        int endX = 0;
        int endY = 0;
        Bomber bomber = new Bomber(0, 0, Sprite.grass.getFxImage());
        if (objects != null) {
            int count = 0;
            for (Entity entity : objects) {
                if (entity instanceof Bomber) {
                    bomber = (Bomber) entity;
                    count++;
                }
                if (entity instanceof Portal) {
                    endX = entity.getX();
                    endY = entity.getY();
                    count++;
                }
                if (count == 2) {
                    break;
                }
            }
        }
//        while (bomber != null) {
//            System.out.println(endX + ", " + endY);
//            System.out.println("bomber: " + bomber.getX() + ", " + bomber.getY());
//            if (((bomber.getX() == endX) && (bomber.getY() == endY)) || objects == null) {
//                break;
//            }
//        }
    }

    public Entity getEntity(int x, int y) {
        return map.get(fromPosToIndex(x, y));
    }

    public void changeEntity(int x, int y, Entity entity) {
        map.set(fromPosToIndex(x, y), entity);
    }

    public int fromPosToIndex(int x, int y) {
        return height * x + y;
    }

    public void printMap(List<Entity> stillObjects, GraphicsContext gc) {
        for (int i = 0; i < map.size(); i++) {
            map.get(i).render(gc);
        }
    }

    public void updateAfterExplode(int x, int y, List<Entity> stillObjects) {
        if (getEntity(x / 32, y / 32 - 1) instanceof Brick) {
            Grass grass = new Grass(x / 32, y / 32 - 1, Sprite.grass.getFxImage());
            changeEntity(x / 32, y / 32 - 1, grass);
        }
        if (getEntity(x / 32, y / 32 + 1) instanceof Brick) {
            Grass grass = new Grass(x / 32, y / 32 + 1, Sprite.grass.getFxImage());
            changeEntity(x / 32, y / 32 + 1, grass);
        }
        if (getEntity(x / 32 - 1, y / 32) instanceof Brick) {
            Grass grass = new Grass(x / 32 - 1, y / 32, Sprite.grass.getFxImage());
            changeEntity(x / 32 - 1, y / 32, grass);
        }
        if (getEntity(x / 32 + 1, y / 32) instanceof Brick) {
            Grass grass = new Grass(x / 32 + 1, y / 32, Sprite.grass.getFxImage());
            changeEntity(x / 32 + 1, y / 32, grass);
        }
    }


    public boolean checkCollision(Entity e1, Entity e2) {
        int leftE1 = e1.getX();
        int rightE1 = e1.getX() + 28;
        int topE1 = e1.getY();
        int bottomE1 = e1.getY() + 28;

        int leftE2 = e2.getX();
        int rightE2 = e2.getX() + 28;
        int topE2 = e2.getY();
        int bottomE2 = e2.getY() + 28;

        if (bottomE1 <= topE2) {
            return false;
        }

        if (topE1 >= bottomE2) {
            return false;
        }

        if (rightE1 <= leftE2) {
            return false;
        }

        if (leftE1 >= rightE2) {
            return false;
        }
        return true;
    }


}


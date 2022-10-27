package uet.oop.bomberman;

import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.level.Level;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Map {
    public static int width; // theo o
    public static int height; // theo o

    public static List<Entity> map = new ArrayList<>();

    public static int level;
    public static int nextLevel = 1;
    private List<Entity> objects = new ArrayList<>();

    public void setObjects(List<Entity> objects) {
        this.objects = objects;
    }


    public List<Entity> getObjects() {
        return objects;
    }

    public void createMap() {
        try {
            Level level = new Level();
            level.initMapLevel();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void getSize() {
        try {
            FileInputStream file = new FileInputStream("res/levels/Level" + nextLevel + ".txt");
            Scanner scanner = new Scanner(file);
            level = scanner.nextInt();
            int row = scanner.nextInt();
            int col = scanner.nextInt();
            width = col;
            height = row;
        } catch (FileNotFoundException e) {
            e.getStackTrace();
        }
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

    public boolean checkCollision(Entity e1, Entity e2) {
        int leftE1 = e1.getX();
        int rightE1 = e1.getX() + 10;
        int topE1 = e1.getY();
        int bottomE1 = e1.getY() + 15;

        int leftE2 = e2.getX();
        int rightE2 = e2.getX() + 16;
        int topE2 = e2.getY();
        int bottomE2 = e2.getY() + 16;

        if (bottomE1 < topE2) {
            return false;
        }

        if (topE1 > bottomE2) {
            return false;
        }

        if (rightE1 < leftE2) {
            return false;
        }

        if (leftE1 > rightE2) {
            return false;
        }
        return true;
    }
}


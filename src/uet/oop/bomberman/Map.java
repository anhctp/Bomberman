package uet.oop.bomberman;

import javafx.scene.canvas.GraphicsContext;
import uet.oop.bomberman.entities.*;
import uet.oop.bomberman.entities.enemy.Balloom;
import uet.oop.bomberman.entities.enemy.Oneal;
import uet.oop.bomberman.graphics.Sprite;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Map {
    private static int width; // theo o
    private static int height; // theo o

    public static List<Entity> map = new ArrayList<>();

    public static List<List<Integer>> getBlocks = new ArrayList<List<Integer>>();

    public static int getWidth() {
        return width;
    }

    public static int getHeight() {
        return height;
    }

    public static List<Entity> items = new ArrayList<>();
    private List<Entity> objects = new ArrayList<>();

    public List<Entity> getObjects() {
        return objects;
    }

    public void setObjects(List<Entity> objects) {
        this.objects = objects;
    }

    public List<Entity> getMap() {
        return map;
    }

    public void setMap(List<Entity> map) {
        this.map = map;
    }

    public boolean checkUp(Entity entity) {
//        if (getEntity((entity.getX() + Sprite.DEFAULT_SIZE) / 32, (entity.getY() - entity.getVelocity()) / 32).goThrough) {
        if (getEntity((entity.getX()) / 32, (entity.getY() - entity.getVelocity()) / 32).goThrough) {
            return true;
        }
        return false;
    }

    public boolean checkDown(Entity entity) {
//        if (getEntity((entity.getX() + Sprite.DEFAULT_SIZE) / 32, (entity.getY() + entity.getVelocity() + Sprite.SCALED_SIZE) / 32).goThrough) {
        if (getEntity((entity.getX() + Sprite.DEFAULT_SIZE) / 32, (entity.getY()  + 2 * Sprite.DEFAULT_SIZE) / 32).goThrough) {
            return true;
        }
        return false;
    }

    public boolean checkLeft(Entity entity) {
        //if (getEntity((entity.getX() - entity.getVelocity()) / 32, entity.getY() / 32).goThrough) {
        if (getEntity((entity.getX() - entity.getVelocity()) / 32, entity.getY() / 32).goThrough) {
            return true;
        }
        return false;
    }

    public boolean checkRight(Entity entity) {
        //if (getEntity((entity.getX() + entity.getVelocity() + Sprite.SCALED_SIZE) / 32, (entity.getY() + Sprite.DEFAULT_SIZE) / 32).goThrough) {
        if (getEntity((entity.getX()  + 2 * Sprite.DEFAULT_SIZE) / 32, (entity.getY() + Sprite.DEFAULT_SIZE) / 32).goThrough) {
            return true;
        }
        return false;
    }


    public void createMap(List<Entity> stillObjects) throws IOException {

//        FileInputStream file = new FileInputStream("res\\levels\\Level2.txt");
        FileInputStream file = new FileInputStream("/Users/admin/Downloads/Work/OOP/Bomberman/res/levels/Level1.txt");
        Scanner scanner = new Scanner(file);
        int level = scanner.nextInt();
        int row = scanner.nextInt();
        int col = scanner.nextInt();

        width = col;
        height = row;

//        FileReader fr = new FileReader("res\\levels\\Level2.txt");   //Creation of File Reader object
        FileReader fr = new FileReader("/Users/admin/Downloads/Work/OOP/Bomberman/res/levels/Level1.txt");   //Creation of File Reader object
        BufferedReader br = new BufferedReader(fr);  //Creation of BufferedReader object
        String s = br.readLine();
        char[][] cMap = new char[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                int c = 0;
                c = br.read();
                char ch = (char) c;
                cMap[i][j] = ch;
            }
            String ss = br.readLine();
        }

        //create getBlocks list;
        getBlocks.add(new ArrayList<Integer>());
        getBlocks.add(new ArrayList<Integer>());

        for (int i = 0; i < col; i++) {
            for (int j = 0; j < row; j++) {
                Entity object;
                Entity item = null;
                Entity objectName = null;
                switch (cMap[j][i]) {
                    case '#':
                        object = new Wall(i, j, Sprite.wall.getFxImage());
                        getBlocks.get(0).add(i);
                        getBlocks.get(1).add(j);
                        break;
                    case '*':
                        object = new Brick(i, j, Sprite.brick.getFxImage());
                        getBlocks.get(0).add(i);
                        getBlocks.get(1).add(j);
                        break;
                    case 'x':
                        object = new Grass(i, j, Sprite.grass.getFxImage());
                        objectName = new Portal(i, j, Sprite.portal.getFxImage());
                        getBlocks.get(0).add(i);
                        getBlocks.get(1).add(j);
                        break;
                    case 'p':
                        object = new Grass(i, j, Sprite.grass.getFxImage());
                        objectName = new Bomber(i, j, Sprite.player_down.getFxImage());
                        break;
                    case '1':
                        object = new Grass(i, j, Sprite.grass.getFxImage());
                        objectName = new Balloom(i, j, Sprite.balloom_left1.getFxImage());
                        break;
                    case '2':
                        object = new Grass(i, j, Sprite.grass.getFxImage());
                        objectName = new Oneal(i, j, Sprite.oneal_left1.getFxImage());
                        break;
//                    case 'b':
//                        break;
//                    case 'f':
//                        break;
//                    case 's':
//                        break;
//                    case ' ':
//                        object = new Grass(i, j, Sprite.grass.getFxImage());
//                        break;
//                    case '2':
//                        object = new Oneal();
//                        break;
                    case 'b':
                        item = new BombItem(i, j, Sprite.powerup_bombs.getFxImage());
                        object = new Brick(i, j, Sprite.brick.getFxImage(), true, item);
                        break;
                    case 'f':
                        item = new FlameItem(i, j, Sprite.powerup_flames.getFxImage());
                        object = new Brick(i, j, Sprite.brick.getFxImage(), true, item);

                        break;
                    case 's':
                        item = new SpeedItem(i, j, Sprite.powerup_speed.getFxImage());
                        object = new Brick(i, j, Sprite.brick.getFxImage(), true, item);
                        break;
                    case ' ':
                        object = new Grass(i, j, Sprite.grass.getFxImage());
                        break;
                    default:
                        object = new Grass(i, j, Sprite.grass.getFxImage());
                }
                stillObjects.add(object);
                if (objectName != null) {
                    objects.add(objectName);
                }
                map.add(object);

            }
            String ss = br.readLine();
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

    public void printMap(List<Entity> stillObjects, GraphicsContext gc) {
        for(int i = 0; i < map.size(); i++) {
            map.get(i).render(gc);
        for (int i = 0; i < stillObjects.size(); i++) {
            stillObjects.get(i).render(gc);
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
        int rightE1 = e1.getX() + 10;
        int topE1 = e1.getY();
        int bottomE1 = e1.getY() + 15;

        int leftE2 = e2.getX();
        int rightE2 = e2.getX() + 16;
        int topE2 = e2.getY();
        int bottomE2 = e2.getY() + 16;

        if(bottomE1 < topE2 ) {
            return false;
        }

        if(topE1 > bottomE2) {
            return false;
        }

        if(rightE1 < leftE2 ) {
            return false;
        }

        if( leftE1 > rightE2 ) {
            return false;
        }
        return true;

    }
}

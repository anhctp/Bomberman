package uet.oop.bomberman;

import uet.oop.bomberman.entities.*;
import uet.oop.bomberman.graphics.Sprite;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Map {
    public static int width; // theo o
    public static int height; // theo o

    List<Entity> map = new ArrayList<>();

    public static int getWidth() {
        return width;
    }

    public static int getHeight() {
        return height;
    }

    public void createMap(List<Entity> stillObjects) throws IOException {

        FileInputStream file = new FileInputStream("/Users/admin/Downloads/Work/OOP/Game/res/levels/Level1.txt");
        Scanner scanner = new Scanner(file);
        int level = scanner.nextInt();
        int row = scanner.nextInt();
        int col = scanner.nextInt();

        width = col;
        height = row;

        FileReader fr = new FileReader("/Users/admin/Downloads/Work/OOP/Game/res/levels/Level1.txt");   //Creation of File Reader object
        BufferedReader br = new BufferedReader(fr);  //Creation of BufferedReader object
        String s = br.readLine();
        char[][] cMap = new char[row][col];
        for(int i = 0; i < row; i++) {
            for(int j = 0; j < col; j++) {
                int c = 0;
                c = br.read();
                char ch = (char) c;
                cMap[i][j] = ch;
            }
            String ss = br.readLine();
        }

        for (int i = 0; i < col; i++) {
            for (int j = 0; j < row; j++) {
                Entity object;
                switch (cMap[j][i]) {
                    case '#':
                        object = new Wall(i, j, Sprite.wall.getFxImage());
                        break;
                    case '*':
                        object = new Brick(i, j, Sprite.brick.getFxImage());
                        break;
                    case 'x':
                        object = new Portal(i, j, Sprite.wall.getFxImage());
                        break;
//                    case 'p':
//                       object = new Bomber(i, j, Sprite.bomber)
//                        break;
//                    case '1':
//                        object = new Balloon();
//                        break;
//                    case '2':
//                        object = new Oneal();
//                        break;
//                    case 'b':
//                        break;
//                    case 'f':
//                        break;
//                    case 's':
//                        break;
                    case ' ':
                        object = new Grass(i, j, Sprite.grass.getFxImage());
                        break;
                    default:
                        throw new IllegalStateException("Unexpected value: " + cMap[j][i]);
                }
                stillObjects.add(object);
                map.add(object);
            }
            String ss = br.readLine();
        }
    }

    public Entity getEntity(int x, int y) {
        return map.get(fromPosToIndex(x, y));
    }

    public void changeEntity(int x, int y, Entity entity, List<Entity> stillObjects) {
        stillObjects.set(fromPosToIndex(x, y), entity);
    }

    int fromPosToIndex(int x, int y) {
        return height * x + y;
    }
}

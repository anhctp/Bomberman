package uet.oop.bomberman.level;

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

import static uet.oop.bomberman.BombermanGame.*;
import static uet.oop.bomberman.Map.*;

public class Level {
    private List<Entity> objects = new ArrayList<>();

    public void initMapLevel() throws IOException {
        entities.clear();
        stillObjects.clear();
        FileInputStream file = new FileInputStream("res/levels/Level" + nextLevel +".txt");
        Scanner scanner = new Scanner(file);
        int level = scanner.nextInt();
        int row = scanner.nextInt();
        int col = scanner.nextInt();
        width = col;
        height = row;

        FileReader fr = new FileReader("res/levels/Level" + nextLevel +".txt");   //Creation of File Reader object
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

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                Entity object;
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
        m.setObjects(objects);
        nextLevel = level + 1;
    }
}

package uet.oop.bomberman;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import uet.oop.bomberman.entities.*;
import uet.oop.bomberman.graphics.Sprite;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BombermanGame extends Application {

    public static final int WIDTH = 40;
    public static final int HEIGHT = 20;

    private GraphicsContext gc;
    private Canvas canvas;
    private List<Entity> entities = new ArrayList<>();
    private List<Entity> stillObjects = new ArrayList<>();
    private List<Bomber> bomberList = new ArrayList<>();


    @Override
    public void start(Stage stage) throws IOException {
        // Tao Canvas
        canvas = new Canvas(Sprite.SCALED_SIZE * WIDTH, Sprite.SCALED_SIZE * HEIGHT);
        gc = canvas.getGraphicsContext2D();

        // Tao root container
        Group root = new Group();
        root.getChildren().add(canvas);


        // Tao scene
        Scene scene = new Scene(root);

        // Them scene vao stage
        stage.setScene(scene);
        stage.show();

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                render();
                update();
            }
        };
        timer.start();

        createMap();
//        Entity bomberman = new Bomber(1, 1, Sprite.player_right.getFxImage());
//        entities.add(bomberman);
        Bomber bomberman = new Bomber(1, 1, Sprite.player_right.getFxImage());
        bomberList.add(bomberman);

        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                switch (keyEvent.getCode()) {
                    case W:
                        bomberman.moveUp();
                        break;
                    case S:
                        bomberman.moveDown();
                        break;
                    case A:
                        bomberman.moveLeft();
                        break;
                    case D:
                        bomberman.moveRight();
                        break;
                }
            }
        });
    }

    public void createMap() throws IOException {
        File file = new File("res\\levels\\Level2.txt");
        if (file.exists()) {
            FileInputStream fileInputStream = new FileInputStream(file);
            Scanner scanner = new Scanner(file);
            int level = scanner.nextInt();
            int row = scanner.nextInt();
            int col = scanner.nextInt();
            FileReader fr = new FileReader(file);   //Creation of File Reader object
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

            for (int i = 0; i < col; i++) {
                for (int j = 0; j < row; j++) {
                    Entity object;
//                int c = 0;
//                c = br.read();
//                char ch = (char) c;
                    switch (cMap[j][i]) {
                        case '#':
                            object = new Wall(i, j, Sprite.wall.getFxImage());
                            break;
                        case '*':
                            object = new Brick(i, j, Sprite.brick.getFxImage());
                            break;
                        case 'x':
                            object = new Portal(i, j, Sprite.portal.getFxImage());
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
                }
                String ss = br.readLine();
            }
        }
    }

    public void update() {
        entities.forEach(Entity::update);
    }

    public void render() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        stillObjects.forEach(g -> g.render(gc));
//        entities.forEach(g -> g.render(gc));
        bomberList.forEach(g -> g.render(gc));
    }
}

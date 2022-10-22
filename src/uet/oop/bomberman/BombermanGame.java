package uet.oop.bomberman;

//import com.sun.webkit.dom.EntityImpl;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import uet.oop.bomberman.entities.*;
import uet.oop.bomberman.graphics.Sprite;

import javax.swing.plaf.basic.BasicTreeUI;
import java.awt.event.MouseEvent;
import java.io.*;
import java.security.Key;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class BombermanGame extends Application {

    public static final int WIDTH = 40;
    public static final int HEIGHT = 30;

    private GraphicsContext gc;
    private Canvas canvas;
    public static List<Entity> entities = new ArrayList<>();
    public static Map m = new Map();
    private List<Entity> stillObjects = new ArrayList<>();
    KeyHandler keyHandler = new KeyHandler();

//    private long lastTime;
//    private static final int FPS = 30;
//    private static final long TIME_PER_FRAME = 1000000000 / FPS;
    @Override
    public void start(Stage stage) throws IOException {
        // Tao Canvas
        m.createMap(stillObjects);
        canvas = new Canvas(Sprite.SCALED_SIZE * m.getWidth(), Sprite.SCALED_SIZE * m.getHeight());
        gc = canvas.getGraphicsContext2D();

        // Tao root container
        Group root = new Group();
        root.getChildren().add(canvas);
        // Tao scene
        Scene scene = new Scene(root);

        // Them scene vao stage
        stage.setTitle("Bomberman Game");
        stage.setScene(scene);
        stage.show();
        Bomber bomberman = new Bomber(1, 1, Sprite.player_right.getFxImage());

        scene.setOnKeyPressed(e -> {
            bomberman.handleKeyPressed(e);
            bomberman.handleEvent(m, entities, gc);
        });
        scene.setOnKeyReleased(e -> {
            bomberman.handleKeyReleased(e);
            bomberman.handleEvent(m, entities, gc);
        });
        entities.add(bomberman);
        addBalloom();

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                render();
                update();
//                try {
//                    TimeUnit.NANOSECONDS.sleep(100);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
            }

        };
        timer.start();
    }

//    public long delay() {
//        long endTime = System.nanoTime();
//        long delayTime = endTime - lastTime;
//        lastTime = endTime;
//        if (delayTime < TIME_PER_FRAME) {
//
//            return TIME_PER_FRAME - delayTime;
//        }
//        return 0;
//    }
    public void addBalloom() {
        for(Entity entity : m.getObjects()) {
            if (entity instanceof Balloom) {
                entities.add(entity);
            }
        }
    }

    public void update() {
        entities.forEach(Entity::update);
    }

    public void render() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
//        stillObjects.forEach(g -> g.render(gc));
        m.getMap().forEach(g->g.render(gc));
        entities.forEach(g -> g.render(gc));
    }
}

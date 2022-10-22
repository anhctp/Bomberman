package uet.oop.bomberman;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;
import uet.oop.bomberman.entities.*;
import uet.oop.bomberman.graphics.Sprite;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static uet.oop.bomberman.Map.map;
import static uet.oop.bomberman.entities.enemy.Enemy.countEnemy;

public class BombermanGame extends Application {

    public static final int WIDTH = 40;
    public static final int HEIGHT = 30;
    public static List<Entity> entities = new ArrayList<>();
    public static List<Entity> stillObjects = new ArrayList<>();
    public static Map m = new Map();

    private GraphicsContext gc;
    private Canvas canvas;

    @Override
    public void start(Stage stage) throws IOException {
        // Tao Canvas
        m.createMap();
        canvas = new Canvas(Sprite.SCALED_SIZE * m.width, Sprite.SCALED_SIZE * m.height);
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

        addEntities();
        Bomber bomberman = new Bomber(1, 1, Sprite.player_right.getFxImage());

        for (Entity e : m.getObjects()) {
            if (e instanceof Bomber) {
                bomberman = (Bomber) e;
                break;
            }
        }

        Bomber finalBomberman = bomberman;
        scene.setOnKeyPressed(e -> {
            finalBomberman.handleKeyPressed(e);
            finalBomberman.handleEvent(m, entities, gc);
        });
        scene.setOnKeyReleased(e -> {
            finalBomberman.handleKeyReleased(e);
            finalBomberman.handleEvent(m, entities, gc);
        });
        entities.add(finalBomberman);



        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                render();
                update();
            }
        };
        timer.start();
    }
    public static void addEntities() {
        for (Entity entity : m.getObjects()) {
            if ((!(entity instanceof Bomber)) && !(entity instanceof Portal))
                entities.add(entity);
        }
    }

    public void update() {
        entities.forEach(Entity::update);
        if (countEnemy == 0) {
            for (Entity entity : m.getObjects()) {
                if (entity instanceof Portal) {
                    entities.add(entity);
                }
            }
        }
    }

    public void render() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        map.forEach(g -> g.render(gc));
        //stillObjects.forEach(g -> g.render(gc));
        entities.forEach(g -> g.render(gc));
    }
}

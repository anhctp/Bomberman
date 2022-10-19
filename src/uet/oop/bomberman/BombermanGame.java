package uet.oop.bomberman;

//import com.sun.webkit.dom.EntityImpl;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;
import uet.oop.bomberman.AI.AStar;
import uet.oop.bomberman.entities.Bomber;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BombermanGame extends Application {

    public static final int WIDTH = 40;
    public static final int HEIGHT = 30;

    private GraphicsContext gc;
    private Canvas canvas;
    public static List<Entity> entities = new ArrayList<>();
    public static Map m = new Map();
    private List<Entity> stillObjects = new ArrayList<>();

    @Override
    public void start(Stage stage) throws IOException {
        // Tao Canvas
        m.createMap(stillObjects);
        canvas = new Canvas(Sprite.SCALED_SIZE * m.width, Sprite.SCALED_SIZE * m.height);
        gc = canvas.getGraphicsContext2D();

        // Tao root container
        Group root = new Group();
        root.getChildren().add(canvas);
        // Tao scene
        Scene scene = new Scene(root);

//        scene.addEventFilter(KeyEvent.KEY_PRESSED, key);
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
        scene.setOnKeyPressed(keyEvent -> finalBomberman.handleEvent(keyEvent, m, entities, stillObjects, gc));
        entities.add(bomberman);

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                render();
                update();
            }
        };
        timer.start();
    }

    public void addEntities() {
        for (Entity entity : m.getObjects()) {
            entities.add(entity);
        }
    }

    public void update() {
        entities.forEach(Entity::update);
//        stillObjects.forEach(Entity::update);
//        entities.forEach(entity -> entity.update(m));
    }

    public void render() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        stillObjects.forEach(g -> g.render(gc));
        entities.forEach(g -> g.render(gc));
    }
}

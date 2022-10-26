package uet.oop.bomberman;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import uet.oop.bomberman.entities.Bomber;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.Portal;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.level.Level;
import uet.oop.bomberman.sound.Sound;
import uet.oop.bomberman.menu.Menu;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import static uet.oop.bomberman.Map.map;
import static uet.oop.bomberman.entities.enemy.Enemy.countEnemy;
import static uet.oop.bomberman.level.Level.isLevelUp;
import static uet.oop.bomberman.menu.Menu.*;

public class BombermanGame extends Application {

    public static final int WIDTH = 40;
    public static final int HEIGHT = 30;
    public static List<Entity> entities = new ArrayList<>();
    public static List<Entity> stillObjects = new ArrayList<>();
    public static Map m = new Map();
    public static Bomber bomberman = new Bomber(0, 0, Sprite.player_right.getFxImage());
    public static boolean isPause = false;
    public static boolean running;
    public static ImageView author_view;

    private GraphicsContext gc;
    private Canvas canvas;
    static Sound sound = new Sound("res/Sound/music.wav");

    @Override
    public void start(Stage stage) throws FileNotFoundException {
        // Sound
        sound.play();
        sound.loop();

        // Tao Canvas
        m.getSize();
        canvas = new Canvas(Sprite.SCALED_SIZE * m.width, Sprite.SCALED_SIZE * m.height);
        canvas.setTranslateY(32);
        gc = canvas.getGraphicsContext2D();
        Image author = new Image(new FileInputStream("res/img/author.png"), Sprite.SCALED_SIZE * m.width, Sprite.SCALED_SIZE * m.height, false, false);
        author_view = new ImageView(author);
        author_view.setX(0);
        author_view.setY(32);
        author_view.setScaleX(1);
        author_view.setScaleY(1);
        // Tao root container
        Group root = new Group();
        root.getChildren().add(author_view);
        createMenu(root);
        root.getChildren().add(canvas);
        // Tao scene
        Scene scene = new Scene(root);

        // Them scene vao stage
        stage.setTitle("Bomberman Game");
        stage.setScene(scene);
        stage.show();

        AnimationTimer timer = new AnimationTimer() {
            private long lastTime = 0;

            public void handle(long now) {
                if (now - lastTime > 1000000000 / 60) {
                    lastTime = now;
                    try {
                        if (running) {
                            render();
                            if (!isPause) {
                                update();
                                //time();
                            }
                            updateMenu();
                        } else {
                            countEnemy = 0;
                            m.getObjects().clear();
                            map.clear();
                            entities.clear();
                            stillObjects.clear();
                            bomberman.bombs.clear();
                            updateMenu();
                            render();
                        }
                    } catch (Exception e) {
                    }
                }
            }
        };
        timer.start();

        scene.setOnKeyPressed(e -> {
            bomberman.handleKeyPressed(e);
            bomberman.handleEvent(m, entities, gc);
        });
        scene.setOnKeyReleased(e -> {
            bomberman.handleKeyReleased(e);
            bomberman.handleEvent(m, entities, gc);
        });

    }

    public static void addEntities() {
        for (Entity entity : m.getObjects()) {
            if (entity instanceof Bomber) {
                bomberman = (Bomber) entity;
                entities.add(bomberman);
            }
            if (!(entity instanceof Bomber) && !(entity instanceof Portal))
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

        for (Entity e : entities) {
            if (e instanceof Portal) {
                if (m.checkCollision(bomberman, e)) {
                    isLevelUp = true;
                    break;
                }
            }
        }

        if (isLevelUp) {
            bomberman.setDead(true);
            running = false;
            Menu.levelUp();
            Level.loadNextLevel();
            isLevelUp = false;
        }
    }

    public void render() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        map.forEach(g -> g.render(gc));
//        stillObjects.forEach(g -> g.render(gc));
        entities.forEach(g -> g.render(gc));
    }

    public static void soundEffect(String s) {
        sound = new Sound(s);
        sound.play();
    }
}

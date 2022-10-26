package uet.oop.bomberman.menu;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import uet.oop.bomberman.Map;
import uet.oop.bomberman.graphics.Sprite;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import static uet.oop.bomberman.BombermanGame.*;
import static uet.oop.bomberman.Map.*;


public class Menu {
    private static ImageView statusGame;
    public static Text level, hp, time;
    public static int hpCount = 3, time_number = 120;
    public static Image pauseGame, playGame;

    public static void createMenu(Group root) { //Create a menu
        try {
            level = new Text("Level: 1");
            level.setFont(Font.font("Arial", FontWeight.BOLD, 14));
            level.setFill(Color.WHITE);
            level.setX(416);
            level.setY(20);
            hp = new Text("HP: 3");
            hp.setFont(Font.font("Arial", FontWeight.BOLD, 14));
            hp.setFill(Color.WHITE);
            hp.setX(512);
            hp.setY(20);
            time = new Text("Times: 120");
            time.setFont(Font.font("Arial", FontWeight.BOLD, 14));
            time.setFill(Color.WHITE);
            time.setX(608);
            time.setY(20);

            Image newGame = new Image(new FileInputStream("res/img/play.png"));
            statusGame = new ImageView(newGame);
            statusGame.setX(0);
            statusGame.setY(-8);
            statusGame.setScaleX(1);
            statusGame.setScaleY(0.7);

            Pane pane = new Pane();
            pane.getChildren().addAll(level, hp, time, statusGame);
            pane.setMinSize(Sprite.SCALED_SIZE * m.width, 16);
            pane.setMaxSize(Sprite.SCALED_SIZE * m.width, 32);
            pane.setStyle("-fx-background-color: #353535");

            root.getChildren().add(pane);

            playGame = new Image(new FileInputStream("res/img/pause.png"));
            pauseGame = new Image(new FileInputStream("res/img/resume.png"));

            statusGame.setOnMouseClicked(event -> {

                if (!bomberman.isDead()) {
                    isPause = !isPause;
                } else {
                    if (hpCount == 0 || nextLevel == 4) {
                        hpCount = 3;
                        nextLevel = 1;
                    }
//                        if (nextLevel == 4) {
//                            Image image = new Image(new FileInputStream("res/img/win.png"), Sprite.SCALED_SIZE * m.width, Sprite.SCALED_SIZE * m.height, false, false);
//                            author_view.setImage(image);
//                            nextLevel = 1;
//                            hpCount = 3;
//                        }
                    m.createMap();
                    addEntities();
                    bomberman.setDead(false);
                    running = true;
                }
                updateMenu();

            });
        } catch (FileNotFoundException e) {
            e.getStackTrace();
        }
    }

    public static void updateMenu() { //Update menu
        try {
            level.setText("Level: " + Map.level);
            hp.setText("HP: " + hpCount);

            if (!bomberman.isDead())
                if (isPause) {
                    statusGame.setImage(pauseGame);
                } else {
                    statusGame.setImage(playGame);
                }
            else {
                if (nextLevel == 4 || hpCount == 0) {
                    Image newGame = new Image(new FileInputStream("res/img/playAgain.png"));
                    statusGame.setImage(newGame);
                } else {
                    Image newGame = new Image(new FileInputStream("res/img/play.png"));
                    statusGame.setImage(newGame);
                }
            }
        } catch (FileNotFoundException e) {
            e.getStackTrace();
        }
    }


    public static void levelUp() {
        try {
            if (bomberman.isDead()) {
                if (nextLevel < 4) {
                    Image image = new Image(new FileInputStream("res/img/levelUp.png"), Sprite.SCALED_SIZE * m.width, Sprite.SCALED_SIZE * m.height, false, false);
                    author_view.setImage(image);
                } else {
                    Image image = new Image(new FileInputStream("res/img/win.png"), Sprite.SCALED_SIZE * m.width, Sprite.SCALED_SIZE * m.height, false, false);
                    author_view.setImage(image);
                }
            }
        } catch (FileNotFoundException e) {
            e.getStackTrace();
        }
    }
}


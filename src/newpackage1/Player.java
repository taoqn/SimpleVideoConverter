/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newpackage1;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javax.swing.Timer;
import javax.swing.event.MouseInputListener;

/**
 *
 * @author DinhTao
 */
public class Player extends BorderPane {

    int width;
    int height;
    Media media;
    MediaPlayer player;
    MediaView view;
    Pane mpane;
    MediaBar bar;
    Timer t;
    int count;

    public Player(String file) {
        media = new Media(file);
        player = new MediaPlayer(media);
        view = new MediaView(player);

        view.setFitWidth(720);
        view.setFitHeight(405);
        view.setPreserveRatio(false);

        mpane = new Pane();
        mpane.getChildren().add(view);
        mpane.setCenterShape(true);
        setCenter(mpane);

        bar = new MediaBar(player);
        setBottom(bar);

        mpane.setOnMouseEntered(new EventHandler<javafx.scene.input.MouseEvent>() {
            @Override
            public void handle(javafx.scene.input.MouseEvent event) {
                bar.setVisible(true);
            }
        });
        mpane.setOnMouseExited(new EventHandler<javafx.scene.input.MouseEvent>() {
            @Override
            public void handle(javafx.scene.input.MouseEvent event) {
                bar.setVisible(false);
            }
        });
        player.play();
    }

    public int getWidthView() {
        return width;
    }

    public int getHeightView() {
        return height;
    }

    public void setSizeView(int w, int h) {
        if (((w * 9) / 16) <= h) {
            width = w - 16;
            height = (width * 9) / 16;
//            view.setTranslateX(0);
//            view.setTranslateY(height/4);
        } else {
            width = (height * 16) / 9;
            height = h - 39;
//            view.setTranslateX(width/4);
//            view.setTranslateY(0);
        }
        view.setFitWidth(width);
        view.setFitHeight(height);
    }

}

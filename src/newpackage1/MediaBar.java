/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newpackage1;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javafx.animation.Animation;
import javafx.application.Platform;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.effect.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaPlayer.Status;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/**
 *
 * @author DinhTao
 */
public class MediaBar extends HBox {
    
    MediaPlayer player;
    NumberFormat formatter = new DecimalFormat("#00");
    Slider time = new Slider();
    Slider vol = new Slider();
    Button playButton = new Button("||");
    Label volume = new Label("Volume: ");
    Label viewTimeCur = new Label("");
    
    public MediaBar(MediaPlayer play) {
        player = play;
        setAlignment(Pos.CENTER);
        setPadding(new Insets(12, 10, 12, 10));
        vol.setPrefWidth(70);
        vol.setMinWidth(30);
        vol.setValue(100);
        
        HBox.setHgrow(time, Priority.ALWAYS);
        playButton.setPrefWidth(30);
        viewTimeCur.setPadding(new Insets(5, 5, 5, 5));
        viewTimeCur.setBackground(new Background(new BackgroundFill(Color.DARKSEAGREEN, CornerRadii.EMPTY , new Insets(1, 1, 1, 1))));
        viewTimeCur.setEffect(new InnerShadow(10, Color.BLACK));
        volume.setPadding(new Insets(5, 10, 5, 10));
        volume.setBackground(new Background(new BackgroundFill(Color.DARKSEAGREEN, CornerRadii.EMPTY , new Insets(1, 1, 1, 1))));
        volume.setEffect(new InnerShadow());
        getChildren().add(playButton);
        getChildren().add(viewTimeCur);
        getChildren().add(time);
        getChildren().add(volume);
        getChildren().add(vol);
        
        playButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Status status = player.getStatus();
                if (status == Status.PLAYING) {
                    if (player.getCurrentTime().greaterThanOrEqualTo(player.getTotalDuration())) {
                        player.seek(player.getStartTime());
                        player.play();
                    } else {
                        player.pause();
                        playButton.setText(">");
                    }
                }
                if (status == Status.PAUSED || status == Status.HALTED || status == Status.STOPPED) {
                    player.play();
                    playButton.setText("||");
                }
            }
        });
        
        player.currentTimeProperty().addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable observable) {
                updateValue();
            }
        });
        
        time.valueProperty().addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable observable) {
                if (time.isPressed()) {
                    player.seek(player.getMedia().getDuration().multiply(time.getValue() / 100));
                }
            }
        });
        
        vol.valueProperty().addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable observable) {
                if (vol.isPressed()) {
                    player.setVolume(vol.getValue() / 100);
                }
            }
        });
        
        this.setOnMouseEntered(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                setVisible(true);
            }
        });
        
    }
    
    protected void updateValue() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                double sec = player.getCurrentTime().toSeconds();
                double min = player.getCurrentTime().toMinutes();
                double hour = player.getCurrentTime().toHours();
                if (sec > 59) {
                    sec = sec%60;
                }
                if (min > 59) {
                    min = min%60;
                }
                String cur = formatter.format(hour) + ":" + formatter.format(min) + ":" + formatter.format(sec);
                double h = player.getTotalDuration().toHours();
                double m = player.getTotalDuration().toMinutes() % 60;
                double s = player.getTotalDuration().toSeconds() % 60;
                String tol = formatter.format(h) + ":" + formatter.format(m) + ":" + formatter.format(s);
                viewTimeCur.setText(cur + "/" + tol);
                time.setValue(player.getCurrentTime().toMillis() / player.getTotalDuration().toMillis() * 100);
            }
        });
    }
}

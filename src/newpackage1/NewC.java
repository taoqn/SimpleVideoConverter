/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newpackage1;

import static com.sun.java.accessibility.util.AWTEventMonitor.addComponentListener;
import java.io.File;
import java.net.URL;
import java.nio.file.Paths;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.swing.JFileChooser;
import java.awt.event.ComponentEvent;
import javafx.embed.swing.JFXPanel;


/**
 *
 * @author DinhTao
 */
public class NewC extends Application {

    private static JFXPanel javafxPanel;
    
    public NewC(){
//        javafxPanel.setScene(createScene());
    }
    
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FileChooser ch = new FileChooser();
        File f = ch.showOpenDialog(primaryStage);
        System.out.println(f.toURI().toURL().toExternalForm());
        Player player = new Player(f.toURI().toURL().toExternalForm());
        Scene sc = new Scene(player, player.getWidthView(), player.getHeightView(), Color.BLACK);
        //primaryStage.setResizable(false);
        primaryStage.setScene(sc);
        primaryStage.show();
//        primaryStage
        
        
        
        
        
//        JFileChooser ch = new JFileChooser();
//        ch.showOpenDialog(ch);
//        System.out.println(ch.getSelectedFile().toURI().toURL().toExternalForm());
//        Player player = new Player(ch.getSelectedFile().toURI().toURL().toExternalForm());
//        Scene sc = new Scene(player, player.getPlayerWidth(), player.getPlayerHeight(), Color.BLACK);
//        primaryStage.setScene(sc);
//        primaryStage.show();
    }
}

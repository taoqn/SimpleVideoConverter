/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newpackage1;

import java.awt.BorderLayout;
import javafx.application.Application;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.effect.Reflection;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Demo extends Application{
    private static JFXPanel javafxPanel;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                initAndShowGUI();
            }
        });
    }

    public static void initAndShowGUI() {
        JFrame frame = new JFrame("Swing application");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create JavaFX panel.
        javafxPanel = new JFXPanel();
        frame.getContentPane().add(javafxPanel, BorderLayout.CENTER);

        // Create JavaFX scene.
        Application.launch (Demo.class, null);

        // Show frame.
        frame.pack();
        frame.setVisible(true);
    }

//    @Override
//    public void start (Stage mainStage) {
//        // Add scene to panel
//        
//    }

    private static Scene createScene() {
        Text text = new Text("Hello World");
        text.setFont(new Font(24));
        text.setEffect(new Reflection());

        BorderPane pane = new BorderPane();
        pane.setCenter(text);
        Scene scene = new Scene(pane);

        return scene;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        javafxPanel.setScene(createScene());
    }
}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newpackage1;

import java.awt.event.ComponentAdapter;
import java.io.File;
import java.net.MalformedURLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.effect.Reflection;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class FXInSwing extends JFrame {

    JFXPanel panel;
    Player player;
    Scene sc;

    public FXInSwing() {
        panel = new JFXPanel();
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                FileChooser ch = new FileChooser();
                File f = ch.showOpenDialog(null);
                try {
                    System.out.println(f.toURI().toURL().toExternalForm());
                } catch (MalformedURLException ex) {
                    Logger.getLogger(FXInSwing.class.getName()).log(Level.SEVERE, null, ex);
                }
                try {
                    player = new Player(f.toURI().toURL().toExternalForm());
                    sc = new Scene(player, player.getWidthView(), player.getHeightView(), Color.BLACK);
                    panel.setScene(sc);
                } catch (MalformedURLException ex) {
                    Logger.getLogger(FXInSwing.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        //this.setBackground(java.awt.Color.yellow);
        this.getContentPane().add(panel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(726, 430);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setResizable(false);

        this.addComponentListener(new ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                if (player != null) {
                    formComponentResized(evt);
                }
            }
        });
    }

    private void formComponentResized(java.awt.event.ComponentEvent evt) {
        // TODO add your handling code here:
        int width = this.getWidth();
        int height = this.getHeight();
        player.setSizeView(width, height);
        System.out.println(this.getWidth() + "/" + this.getHeight());
        System.out.println(player.getWidthView() + "/" + player.getHeightView());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new FXInSwing();
            }
        });
    }
}

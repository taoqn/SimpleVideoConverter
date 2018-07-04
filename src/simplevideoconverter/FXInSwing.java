/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simplevideoconverter;

import java.awt.event.ComponentAdapter;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
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

    File file;
    JFXPanel panel;
    Player player;
    Scene sc;

    public FXInSwing(File f) {
        file = f;
        panel = new JFXPanel();
        updatePane();
        this.getContentPane().add(panel);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        //this.setType(java.awt.Window.Type.UTILITY);
        this.setSize(726, 430);
        this.setVisible(true);
        this.setTitle(f.getName());
        this.setLocationRelativeTo(null);
        this.setResizable(false);

        this.addComponentListener(new ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                if (player != null) {
                    formComponentResized(evt);
                }
            }
        });

        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                if (player != null) {
                    formWindowClosing(evt);
                }
            }
        });
    }

    protected void updatePane() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                try {
                    player = new Player(file.toURI().toURL().toExternalForm());
                    sc = new Scene(player, player.getWidthView(), player.getHeightView(), Color.BLACK);
                    panel.setScene(sc);
                } catch (MalformedURLException ex) {
                    Logger.getLogger(FXInSwing.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    private void formWindowClosing(WindowEvent evt) {
        synchronized (evt) {
            Platform.setImplicitExit(false);
            player.stopPlayer();
            this.getContentPane().remove(panel);
            panel = null;
            player = null;
        }
    }

    private void formComponentResized(java.awt.event.ComponentEvent evt) {
        // TODO add your handling code here:
        int width = this.getWidth();
        int height = this.getHeight();
        player.setSizeView(width, height);
    }

}

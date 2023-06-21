package uca.pages;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Scene_SplashController implements Initializable {
    
    @FXML 
    private Pane pane;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        new Splash().start();
    }    
    
    class Splash extends Thread {

            @Override
            public void run(){
                    try{
                        Thread.sleep(5000);
                        Platform.runLater(() -> {
                            Parent root = null;
                            try {
                                root = FXMLLoader.load(getClass().getResource("Scene_Login.fxml"));
                            } catch (IOException ex) {
                                Logger.getLogger(Scene_SplashController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            Scene scene = new Scene(root);
                            Stage stage = new Stage();
                            stage.setScene(scene);
                            String css = this.getClass().getResource("style_login.css").toExternalForm(); 
                            scene.getStylesheets().add(css);
                            stage.centerOnScreen();
                            stage.show();
                            pane.getScene().getWindow().hide();
                        });
                        
                }catch(InterruptedException e){
                        System.out.println(e);
                }
           }
    }
}

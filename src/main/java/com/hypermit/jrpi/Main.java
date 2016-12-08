package com.hypermit.jrpi;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * @author: hypermit
 * Date: 2016
 * hypermit@gmail.com
 */
public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/main_window.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 750, 400));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}

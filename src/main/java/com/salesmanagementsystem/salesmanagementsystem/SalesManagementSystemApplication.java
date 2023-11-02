package com.salesmanagementsystem.salesmanagementsystem;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SalesManagementSystemApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(SalesManagementSystemApplication.class.getResource("View/Login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 672, 404);
//        stage.setTitle("");
        stage.setScene(scene);
        stage.setTitle("Books & Records Shop Management System");
        stage.setMinHeight(450);
        stage.setMinWidth(680);
        stage.setMaxHeight(450);
        stage.setMaxWidth(680);
        stage.centerOnScreen();
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
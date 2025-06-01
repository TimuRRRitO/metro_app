package com.example.metroappv2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Database db = new Database();
        MainPane mainPane = new MainPane(db.getStations(), db.getLinks());
        FXMLLoader loader = new FXMLLoader(getClass().getResource("metro_map.fxml"));

        Scene scene = new Scene(mainPane, 1500, 600);
        primaryStage.setTitle("Московское метро - Поиск маршрутов");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
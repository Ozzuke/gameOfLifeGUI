package org.example.gameoflifegui;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


public class GameOfLifeApp extends Application {
    private int lauaLaius = 50;
    private int lauaPikkus = 50;
    private int ruuduSuurus = 10;
    private boolean heleResiim = true;

    @Override
    public void start(Stage primaryStage) {
        Mangulaud laud = new Mangulaud(lauaLaius, lauaPikkus, ruuduSuurus);
        Juhtimine juhtimine = new Juhtimine(laud);

        BorderPane root = new BorderPane();
        root.setCenter(laud);
        root.setBottom(juhtimine);
        root.setPadding(new Insets(10));

        // Load the CSS file
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles.css");
        primaryStage.setScene(scene);
        primaryStage.setTitle("Game of Life");
        primaryStage.show();
    }
}
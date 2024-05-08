package org.example.gameoflifegui;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.util.Objects;


public class GameOfLifeApp extends Application {
    private int lauaLaius = 50;
    private int lauaPikkus = 30;
    private int CELL_SIZE = 10;
    private boolean heleResiim = true;

    @Override
    public void start(Stage primaryStage) {
        Mangulaud laud = new Mangulaud(lauaLaius, lauaPikkus, CELL_SIZE);
        Juhtimine juhtimine = new Juhtimine(laud);

        BorderPane root = new BorderPane();
        root.setCenter(laud);
        root.setBottom(juhtimine);
        root.setPadding(new Insets(10));

        // Load the CSS file
        Scene scene = new Scene(root);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/styles.css")).toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.setTitle("Game of Life");
        primaryStage.show();
    }
}
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
    public void start(Stage peaLava) {
        Mangulaud laud = new Mangulaud(lauaLaius, lauaPikkus, ruuduSuurus);
        Juhtimine juhtimine = new Juhtimine(laud);

        BorderPane juur = new BorderPane();
        juur.setCenter(laud);
        juur.setBottom(juhtimine);
        juur.setPadding(new Insets(10));

        // Load the CSS file
        Scene stseen = new Scene(juur);
        stseen.getStylesheets().add("/styles.css");
        peaLava.setScene(stseen);
        peaLava.setTitle("Game of Life");
        peaLava.show();
    }
}
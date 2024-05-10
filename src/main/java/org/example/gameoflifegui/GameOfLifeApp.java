package org.example.gameoflifegui;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class GameOfLifeApp extends Application {
    private int lauaLaius = 100;
    private int lauaPikkus = 100;
    private int ruuduSuurus = 5;
    private boolean heleResiim = true;

    @Override
    public void start(Stage peaLava) throws FileNotFoundException {
        PildiImport pilt = new PildiImport("glass.jpg", lauaPikkus, lauaLaius);
        Mangulaud laud = new Mangulaud(lauaLaius, lauaPikkus, ruuduSuurus);
        laud.setRuudud(pilt.piltToBitSet());
        laud.uuendaDisplay();
        Juhtimine juhtimine = new Juhtimine(laud, pilt.piltToBitSet());
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
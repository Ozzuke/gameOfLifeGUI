package org.example.gameoflifegui;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class GameOfLifeApp extends Application {
    @Override
    public void start(Stage peaLava) {
        Seaded seaded = Seaded.getInstance();
        Laud laud = new Laud(seaded.getLauaLaius(), seaded.getLauaPikkus(), seaded.getRuuduSuurus());
        Juhtpaneel juhtpaneel = new Juhtpaneel();
        Juhtimine juhtimine = new Juhtimine(laud, juhtpaneel);

        BorderPane juur = new BorderPane();
        juur.setCenter(laud);
        juur.setBottom(juhtpaneel);
        juur.setPadding(new Insets(10));

        Scene stseen = new Scene(juur);
        stseen.getStylesheets().add("/styles.css");

        peaLava.setScene(stseen);
        peaLava.setTitle("Game of Life");
        peaLava.show();
    }
}

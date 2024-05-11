package org.example.gameoflifegui;

import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;

public class Seaded {
    private CheckBox režiimiNupp = new CheckBox();
    private boolean heleRežiim = true;
    private Mangulaud laud;
    private FileChooser pildiValija = new FileChooser();
    private Scene seadeStseen;
    private Stage seadeLava;
    public Seaded(Mangulaud laud){
        this.laud = laud;
        seadeStseen = new Scene(looSeadeKast());
        seadeStseen.getStylesheets().add("/styles.css");
        seadeLava = new Stage();
        seadeLava.setMinWidth(200);
        seadeLava.setMaxWidth(200);
        seadeLava.setMinHeight(200);
        seadeLava.setTitle("Seaded");
        seadeLava.setScene(seadeStseen);
    }
    public void avaSeaded(){
        seadeLava.show();
    }
    private GridPane looSeadeKast() {
    Label darkModeLabel = new Label("Tume režiim");
    Label selectBackgroundLabel = new Label("Vali taustapilt");
    Button selectImageButton = new Button("Vali");

    // Seome nupud vastavate meetoditega
    selectImageButton.setOnAction(e -> {
        try {
            avaPilt();
        } catch (FileNotFoundException ex) {
            throw new RuntimeException(ex);
        }
    });
    režiimiNupp.setOnAction(e -> muudaRežiimi());

    // Paigutame nupud ja sildid GridPane'i
    GridPane settingsPane = new GridPane();
    settingsPane.setHgap(10);
    settingsPane.setVgap(10);
    settingsPane.add(darkModeLabel, 0, 0);
    settingsPane.add(režiimiNupp, 1, 0);
    settingsPane.add(new Separator(), 0, 1, 2, 1);
    settingsPane.add(selectBackgroundLabel, 0, 2);
    settingsPane.add(selectImageButton, 1, 2);
    settingsPane.setAlignment(Pos.CENTER_RIGHT);
    return settingsPane;
}
    private void muudaRežiimi() {
        heleRežiim = !heleRežiim;
        Color taust = heleRežiim ? Color.WHITE : Color.valueOf("#111");
        Color ruuduServad = heleRežiim ? Color.GRAY : Color.GRAY.darker();
        Color elusadRuudud = heleRežiim ? Color.BLACK : Color.WHITE;
        režiimiNupp.setText(heleRežiim ? "Tume režiim" : "Hele režiim");
        laud.setTaustaVärv(taust);
        laud.setRuuduServadeVärv(ruuduServad);
        laud.setElusRuuduVärv(elusadRuudud);
        laud.uuendaDisplay();

        // Muuda CSS-i klassi
        Scene stseen = laud.getScene();
        Parent juur = stseen.getRoot();
        juur.getStyleClass().remove("dark-mode");
        if (!heleRežiim) {
            juur.getStyleClass().add("dark-mode");
        }

        // Muuda seadete akent
        Parent seadedJuur = seadeStseen.getRoot();
        seadedJuur.getStyleClass().remove("dark-mode");
        if (!heleRežiim){
            seadedJuur.getStyleClass().add("dark-mode");
        }
    }
    private void avaPilt() throws FileNotFoundException {
        pildiValija.setTitle("Vali taustapilt");
        File valitudPilt = pildiValija.showOpenDialog(seadeLava);
        if (valitudPilt != null){
            laud.setTaustapilt(valitudPilt);
        }
    }
}

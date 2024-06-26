package org.example.gameoflifegui;

import javafx.geometry.Pos;
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

public class SeadedAken {
    private CheckBox režiimiNupp = new CheckBox();
    private boolean heleRežiim = true;
    private Laud laud;
    private FileChooser pildiValija = new FileChooser();
    private Scene seadeStseen;
    private Stage seadeLava;

    public SeadedAken(Laud laud) {
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

    public void avaSeaded() {
        seadeLava.show();
    }

    private GridPane looSeadeKast() {
        Label tumeRežiimiSilt = new Label("Tume režiim");
        Label valiTaustapiltSilt = new Label("Vali taustapilt");
        Label salvestaSeadeidSilt = new Label("Salvesta seadeid");
        Label slavestaPiltSilt = new Label("Salvesta Pilt");
        Button valiPiltNupp = new Button("Vali");
        Button salvestaSeadedNupp = new Button("Salvesta");
        Button salvestaPiltNupp = new Button("Salvesta");

        valiPiltNupp.setOnAction(e -> {
            try {
                avaPilt();
            } catch (FileNotFoundException ex) {
                throw new RuntimeException(ex);
            }
        });
        režiimiNupp.setOnAction(e -> muudaRežiimi());
        salvestaSeadedNupp.setOnAction(e -> Seaded.getInstance().salvestaSeaded());
        salvestaPiltNupp.setOnAction(e -> {laud.salvestaPilt("pilt.png");});

        GridPane settingsPane = new GridPane();
        settingsPane.setHgap(10);
        settingsPane.setVgap(10);
        settingsPane.add(tumeRežiimiSilt, 0, 0);
        settingsPane.add(režiimiNupp, 1, 0);
        settingsPane.add(new Separator(), 0, 1, 2, 1);
        settingsPane.add(valiTaustapiltSilt, 0, 2);
        settingsPane.add(valiPiltNupp, 1, 2);
        settingsPane.add(salvestaSeadeidSilt, 0, 3);
        settingsPane.add(salvestaSeadedNupp, 1, 3);
        settingsPane.add(salvestaPiltNupp, 1, 4);
        settingsPane.add(slavestaPiltSilt, 0, 4);
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

        Scene stseen = laud.getScene();
        stseen.getRoot().getStyleClass().remove("dark-mode");
        if (!heleRežiim) {
            stseen.getRoot().getStyleClass().add("dark-mode");
        }

        seadeStseen.getRoot().getStyleClass().remove("dark-mode");
        if (!heleRežiim) {
            seadeStseen.getRoot().getStyleClass().add("dark-mode");
        }
    }

    private void avaPilt() throws FileNotFoundException {
        pildiValija.setTitle("Vali taustapilt");
        File valitudPilt = pildiValija.showOpenDialog(seadeLava);
        if (valitudPilt != null) {
            laud.setTaustapilt(valitudPilt);
        }
    }
}

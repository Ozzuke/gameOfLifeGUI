package org.example.gameoflifegui;

import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class Seaded {
    private Button režiimiNupp = new Button("Tume režiim");
    private boolean heleRežiim = true;
    private Mangulaud laud;
    private FileChooser pildiValija = new FileChooser();
    private Scene seaded;
    private Stage seadeLava;
    public Seaded(Mangulaud laud){
        this.laud = laud;
        seaded = new Scene(looSeadeKast());
        seaded.getStylesheets().add("/styles.css");
        seadeLava = new Stage();
        seadeLava.setTitle("Seaded");
        seadeLava.setScene(seaded);
    }
    public void avaSeaded(){
        seadeLava.show();
    }
    private VBox looSeadeKast() {
        Button valiPiltNupp = new Button("Vali Pilt");

        // Seome nupud vastavate meetoditega
        valiPiltNupp.setOnAction(e -> avaPilt());
        režiimiNupp.setOnAction(e -> muudaRežiimi());

        // Paigutame nupud horisontaalselt
        VBox nupuKast = new VBox(2, režiimiNupp, valiPiltNupp);
        nupuKast.setAlignment(Pos.CENTER);
        return nupuKast;
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
        Parent seadedJuur = seaded.getRoot();
        seadedJuur.getStyleClass().remove("dark-mode");
        if (!heleRežiim){
            seadedJuur.getStyleClass().add("dark-mode");
        }
    }
    private void avaPilt(){
        pildiValija.setTitle("vali oma pilt");
        pildiValija.showOpenDialog(null);
    }
}

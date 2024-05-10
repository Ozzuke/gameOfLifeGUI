package org.example.gameoflifegui;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.BitSet;

public class Mangulaud extends Pane {
    private int lauaLaius;
    private int lauaPikkus;
    private int ruuduSuurus;

    public void setRuudud(BitSet ruudud) {
        this.ruudud = ruudud;
    }

    private BitSet ruudud;
    private Color taustaVärv;
    private Color ruuduServadeVärv;
    private Color elusRuuduVärv;

    public Mangulaud(int lauaLaius, int lauaPikkus, int ruuduSuurus) {
        this.lauaLaius = lauaLaius;
        this.lauaPikkus = lauaPikkus;
        this.ruuduSuurus = ruuduSuurus;
        this.ruudud = new BitSet(lauaPikkus * lauaLaius);
        this.taustaVärv = Color.WHITE;
        this.ruuduServadeVärv = Color.GRAY;
        this.elusRuuduVärv = Color.BLACK;

        initializeBoard();
    }

    // Meetod mängulaua loomiseks
    private void initializeBoard() {
        for (int row = 0; row < lauaPikkus; row++) {
            for (int col = 0; col < lauaLaius; col++) {
                Rectangle cell = new Rectangle(col * ruuduSuurus, row * ruuduSuurus, ruuduSuurus, ruuduSuurus);
                cell.setFill(taustaVärv);
                cell.setStroke(ruuduServadeVärv);
                getChildren().add(cell);
            }
        }
    }

    // Meetod mängulaua uuendamiseks
    public void uuendaLauda(BitSet uuedRuudud) {
        this.ruudud = uuedRuudud;
        uuendaDisplay();
    }

    // Meetod mängulaua kuvamise uuendamiseks
    public void uuendaDisplay() {
        for (int rida = 0; rida < lauaPikkus; rida++) {
            for (int veerg = 0; veerg < lauaLaius; veerg++) {
                int index = rida * lauaLaius + veerg;
                Rectangle ruut = (Rectangle) getChildren().get(index);
                ruut.setFill(ruudud.get(index) ? elusRuuduVärv : taustaVärv);
            }
        }
    }

    // Getterid ja setterid
    public BitSet getRuudud() {
        return ruudud;
    }

    public int getLauaLaius() {
        return lauaLaius;
    }

    public int getLauaPikkus() {
        return lauaPikkus;
    }

    public int getRuuduSuurus() {
        return ruuduSuurus;
    }

    public void setTaustaVärv(Color taustaVärv) {
        this.taustaVärv = taustaVärv;
    }

    public void setRuuduServadeVärv(Color ruuduServadeVärv) {
        this.ruuduServadeVärv = ruuduServadeVärv;
    }

    public void setElusRuuduVärv(Color elusRuuduVärv) {
        this.elusRuuduVärv = elusRuuduVärv;
    }
}
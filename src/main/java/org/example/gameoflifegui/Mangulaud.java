package org.example.gameoflifegui;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.BitSet;
public class Mangulaud extends Canvas {
    private int lauaLaius;
    private int lauaPikkus;
    private int ruuduSuurus;
    private GraphicsContext gc;
    private BitSet ruudud;
    private BitSet pildiRuudud;
    private Color taustaVärv;
    private Color ruuduServadeVärv;
    private Color elusRuuduVärv;
    private boolean kasBorder = false;

    public Mangulaud(int lauaLaius, int lauaPikkus, int ruuduSuurus){
        this.lauaLaius = lauaLaius;
        this.lauaPikkus = lauaPikkus;
        this.ruuduSuurus = ruuduSuurus;
        this.ruudud = new BitSet(lauaPikkus * lauaLaius);
        this.taustaVärv = Color.WHITE;
        this.ruuduServadeVärv = Color.GRAY;
        this.elusRuuduVärv = Color.BLACK;
        if (ruuduSuurus > 4){
            kasBorder = true;
        }

        initializeBoard();
    }

    // Meetod mängulaua loomiseks
    private void initializeBoard() {
        // Hangime Canvas'ist GraphicsContext
        this.gc = this.getGraphicsContext2D();

        // Määrame Canvase suurust
        this.setWidth(lauaLaius * ruuduSuurus);
        this.setHeight(lauaPikkus * ruuduSuurus);

        // Joonistame laua algseis
        uuendaDisplay();
    }


    // Meetod mängulaua uuendamiseks
    public void uuendaLauda(BitSet uuedRuudud) {
        this.ruudud = uuedRuudud;
        uuendaDisplay();
    }


    // Meetod mängulaua kuvamise uuendamiseks ühe bachina
    public void uuendaDisplay() {
        // kui soovime teha pildi piiriga, siis teeme seda
        if (kasBorder){
            uuendaDisplayBorderiga();
            return;
        }

        // teeme pildi, mis hakkab olema mängulaua pilt
        WritableImage boardImage = new WritableImage(lauaLaius * ruuduSuurus, lauaPikkus * ruuduSuurus);
        PixelWriter pixelWriter = boardImage.getPixelWriter();

        // Joonista kõiki ruute pildile
        for (int row = 0; row < lauaPikkus; row++) {
            for (int col = 0; col < lauaLaius; col++) {
                int index = row * lauaLaius + col;
                Color color = ruudud.get(index) ? elusRuuduVärv : taustaVärv;

                // Joonista aktiivse ruudu
                for (int y = 0; y < ruuduSuurus; y++) {
                    for (int x = 0; x < ruuduSuurus; x++) {
                        pixelWriter.setColor(col * ruuduSuurus + x, row * ruuduSuurus + y, color);
                    }
                }
            }
        }

        // Joonistame saadud pildi lauale
        gc.drawImage(boardImage, 0, 0);
    }

    // Meetod mängulaua kuvamise uuendamiseks ühe bachina, kus ruutudel on piirjoon
    public void uuendaDisplayBorderiga() {
        // teeme pildi, mis hakkab olema mängulaua pilt
        WritableImage lauaPilt = new WritableImage(lauaLaius * ruuduSuurus, lauaPikkus * ruuduSuurus);
        PixelWriter pixelWriter = lauaPilt.getPixelWriter();

        // Joonista kõiki ruute pildile
        for (int rida = 0; rida < lauaPikkus; rida++) {
            for (int veerg = 0; veerg < lauaLaius; veerg++) {
                int index = rida * lauaLaius + veerg;
                // Määrab ära, kas joonistada elusa või surnud ruudu
                Color praeguseRuuduVärv = ruudud.get(index) ? elusRuuduVärv : taustaVärv;

                // Joonista piiri ruut
                for (int y = 0; y < ruuduSuurus; y++) {
                    for (int x = 0; x < ruuduSuurus; x++) {
                        pixelWriter.setColor(veerg * ruuduSuurus + x, rida * ruuduSuurus + y, ruuduServadeVärv);
                    }
                }

                // Joonista väiksem aktiivne ruut
                for (int y = 1; y < ruuduSuurus - 1; y++) {
                    for (int x = 1; x < ruuduSuurus - 1; x++) {
                        pixelWriter.setColor(veerg * ruuduSuurus + x, rida * ruuduSuurus + y, praeguseRuuduVärv);
                    }
                }
            }
        }

        // Joonistame saadud pildi lauale
        gc.drawImage(lauaPilt, 0, 0);
    }
    public void taastaPilt(){
        if (pildiRuudud != null){
            this.setRuudud((BitSet) pildiRuudud.clone());
            uuendaDisplay();
        }
    }

    public void setTaustapilt(File valitudPilt) throws FileNotFoundException {
        PildiImport pildiImport = new PildiImport(valitudPilt, lauaPikkus * ruuduSuurus, lauaLaius*ruuduSuurus);
        this.setPildiRuudud(pildiImport.piltToBitSet());
        this.setRuudud(pildiImport.piltToBitSet());
        this.uuendaDisplay();
    }

    // Getterid ja setterid

    public void setRuudud(BitSet ruudud) {
        this.ruudud = ruudud;
    }
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
    public void setPildiRuudud(BitSet pildiRuudud) {
        this.pildiRuudud = pildiRuudud;
    }

}
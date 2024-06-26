package org.example.gameoflifegui;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.BitSet;

public class Laud extends Canvas {
    private int lauaLaius;
    private int lauaPikkus;
    private int ruuduSuurus;
    private GraphicsContext gc;
    private BitSet ruudud;
    private BitSet pildiRuudud;
    private Color taustaVärv;
    private Color ruuduServadeVärv;
    private Color elusRuuduVärv;
    private boolean kasBorder;

    public Laud(int lauaLaius, int lauaPikkus, int ruuduSuurus) {
        this.lauaLaius = lauaLaius;
        this.lauaPikkus = lauaPikkus;
        this.ruuduSuurus = ruuduSuurus;
        this.ruudud = new BitSet(lauaPikkus * lauaLaius);
        this.taustaVärv = Color.WHITE;
        this.ruuduServadeVärv = Color.GRAY;
        this.elusRuuduVärv = Color.BLACK;
        this.kasBorder = ruuduSuurus > 4;

        initsialiseeriLaud();
    }

    private void initsialiseeriLaud() {
        this.gc = this.getGraphicsContext2D();
        this.setWidth(lauaLaius * ruuduSuurus);
        this.setHeight(lauaPikkus * ruuduSuurus);
        uuendaDisplay();
    }

    public void uuendaLauda(BitSet uuedRuudud) {
        this.ruudud = uuedRuudud;
        uuendaDisplay();
    }

    public void uuendaDisplay() {
        if (kasBorder) {
            uuendaDisplayBorderiga();
            return;
        }

        WritableImage boardImage = new WritableImage(lauaLaius * ruuduSuurus, lauaPikkus * ruuduSuurus);
        PixelWriter pixelWriter = boardImage.getPixelWriter();

        // joonistab kõik ruudud
        for (int rida = 0; rida < lauaPikkus; rida++) {
            for (int veerg = 0; veerg < lauaLaius; veerg++) {
                int indeks = rida * lauaLaius + veerg;
                Color color = ruudud.get(indeks) ? elusRuuduVärv : taustaVärv;

                // joonistab ruudu
                for (int y = 0; y < ruuduSuurus; y++) {
                    for (int x = 0; x < ruuduSuurus; x++) {
                        pixelWriter.setColor(veerg * ruuduSuurus + x, rida * ruuduSuurus + y, color);
                    }
                }
            }
        }

        // joonistab ühe pariina ruudud lauale
        gc.drawImage(boardImage, 0, 0);
    }

    public void uuendaDisplayBorderiga() {
        WritableImage lauaPilt = new WritableImage(lauaLaius * ruuduSuurus, lauaPikkus * ruuduSuurus);
        PixelWriter pixelWriter = lauaPilt.getPixelWriter();

        // joonistab kõik ruudud
        for (int rida = 0; rida < lauaPikkus; rida++) {
            for (int veerg = 0; veerg < lauaLaius; veerg++) {
                int index = rida * lauaLaius + veerg;
                Color praeguseRuuduVärv = ruudud.get(index) ? elusRuuduVärv : taustaVärv;

                // joonistab ääre ruudu
                for (int y = 0; y < ruuduSuurus; y++) {
                    for (int x = 0; x < ruuduSuurus; x++) {
                        pixelWriter.setColor(veerg * ruuduSuurus + x, rida * ruuduSuurus + y, ruuduServadeVärv);
                    }
                }

                // joonistab väiksema aktiivse ruudu
                for (int y = 1; y < ruuduSuurus - 1; y++) {
                    for (int x = 1; x < ruuduSuurus - 1; x++) {
                        pixelWriter.setColor(veerg * ruuduSuurus + x, rida * ruuduSuurus + y, praeguseRuuduVärv);
                    }
                }
            }
        }

        // joonistab ühe pariina ruudud lauale
        gc.drawImage(lauaPilt, 0, 0);
    }

    public void taastaPilt() {
        if (pildiRuudud != null) {
            this.setRuudud((BitSet) pildiRuudud.clone());
            uuendaDisplay();
        }
    }

    public void setTaustapilt(File valitudPilt) throws FileNotFoundException {
        PildiImport pildiImport = new PildiImport(valitudPilt, lauaPikkus, lauaLaius);
        this.setPildiRuudud(pildiImport.piltToBitSet());
        this.setRuudud(pildiImport.piltToBitSet());
        this.uuendaDisplay();
    }
    public void salvestaPilt(String failiNimi) {
        BufferedImage pilt = new BufferedImage(getLauaLaius(), getLauaPikkus(), BufferedImage.TYPE_INT_RGB);

        for (int y = 0; y < getLauaPikkus(); y++) {
            for (int x = 0; x < getLauaLaius(); x++) {
                int indeks = y * getLauaLaius() + x;
                java.awt.Color värv = getRuudud().get(indeks) ? java.awt.Color.BLACK : java.awt.Color.WHITE;
                pilt.setRGB(x, y, värv.getRGB());
            }
        }

        try {
            ImageIO.write(pilt, "png", new File(failiNimi));
        } catch (IOException e) {
            e.printStackTrace();
        }
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

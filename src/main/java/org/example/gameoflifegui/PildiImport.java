package org.example.gameoflifegui;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.paint.Color;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.BitSet;

public class PildiImport {
    public ImageView getVaataPilti() {
        return vaataPilti;
    }

    public Image getPilt() {
        return pilt;
    }

    private ImageView vaataPilti;
    private Image pilt;

    public PildiImport(String failiNimi, int pikkus, int laius) throws FileNotFoundException {
        FileInputStream fis = new FileInputStream("src/main/resources/pildid/" + failiNimi);
        this.pilt = new Image(fis, pikkus, laius, false, false);
        this.vaataPilti = new ImageView(pilt);
    }

    public BitSet piltToBitSet(){
        PixelReader piksliLuger = pilt.getPixelReader();
        int pikkus = (int) pilt.getHeight();
        int laius = (int) pilt.getWidth();
        BitSet laud = new BitSet(pikkus*laius);
        double keskmineHeledus = keskmineHeledus();
        for (int y = 0; y < pikkus; y++) {
            for (int x = 0; x < laius; x++) {
                int indeks = y * laius + x;
                Color värv = piksliLuger.getColor(x,y);
                double heledus = värv.getBrightness();
                if (heledus < keskmineHeledus){
                    laud.set(indeks);
                }
            }

        }
        return laud;
    }

    public double keskmineHeledus(){
        PixelReader piksliLuger = pilt.getPixelReader();
        int pikkus = (int) pilt.getHeight();
        int laius = (int) pilt.getWidth();
        double heleduseSumma = 0;
        int piksleid = 0;
        for (int y = 0; y < pikkus; y++) {
            for (int x = 0; x < laius; x++) {
                Color värv = piksliLuger.getColor(x, y);
                double heledus = värv.getBrightness();
                heleduseSumma += heledus;
                piksleid++;
            }
        }
        double keskmineHeledus = heleduseSumma / piksleid;
        return keskmineHeledus;
    }
}

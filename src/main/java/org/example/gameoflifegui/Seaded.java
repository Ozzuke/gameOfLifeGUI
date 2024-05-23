package org.example.gameoflifegui;

import javafx.scene.paint.Color;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class Seaded {
    private static Seaded instance;
    private int lauaLaius = 80;
    private int lauaPikkus = 80;
    private int ruuduSuurus = 10;
    private boolean heleRežiim = true;
    private double mänguKiirus = 0.1;
    private boolean lubaAinultKuiSeisab = false;
    private boolean jookseb = false;

    private Seaded() {}

    public static Seaded getInstance() {
        if (instance == null) {
            instance = new Seaded();
        }
        return instance;
    }

    public void salvestaSeaded() {
        try (PrintWriter kirjutaja = new PrintWriter(new File("seaded.txt"))) {
            kirjutaja.println("lauaLaius=" + lauaLaius);
            kirjutaja.println("lauaPikkus=" + lauaPikkus);
            kirjutaja.println("ruuduSuurus=" + ruuduSuurus);
            kirjutaja.println("heleRežiim=" + heleRežiim);
            kirjutaja.println("mänguKiirus=" + mänguKiirus);
            kirjutaja.println("lubaAinultKuiSeisab=" + lubaAinultKuiSeisab);
            kirjutaja.println("jookseb=" + jookseb);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
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

    public boolean isHeleRežiim() {
        return heleRežiim;
    }

    public void setHeleRežiim(boolean heleRežiim) {
        this.heleRežiim = heleRežiim;
    }

    public double getMänguKiirus() {
        return mänguKiirus;
    }

    public void setMänguKiirus(double mänguKiirus) {
        this.mänguKiirus = mänguKiirus;
    }

    public boolean isLubaAinultKuiSeisab() {
        return lubaAinultKuiSeisab;
    }

    public void setLubaAinultKuiSeisab(boolean lubaAinultKuiSeisab) {
        this.lubaAinultKuiSeisab = lubaAinultKuiSeisab;
    }

    public boolean isJookseb() {
        return jookseb;
    }

    public void setJookseb(boolean jookseb) {
        this.jookseb = jookseb;
    }

    public Color getTaustaVärv() {
        return heleRežiim ? Color.WHITE : Color.valueOf("#111");
    }

    public Color getRuuduServadeVärv() {
        return heleRežiim ? Color.GRAY : Color.GRAY.darker();
    }

    public Color getElusRuuduVärv() {
        return heleRežiim ? Color.BLACK : Color.WHITE;
    }
}

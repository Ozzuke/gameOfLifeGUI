package org.example.gameoflifegui;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Alert;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;

import java.util.BitSet;

public class Juhtimine {
    private Laud laud;
    private Juhtpaneel juhtpaneel;
    private Timeline mänguTsükkel;
    private boolean jookseb = false;
    private Seaded seaded;

    public Juhtimine(Laud laud, Juhtpaneel juhtpaneel) {
        this.laud = laud;
        this.juhtpaneel = juhtpaneel;
        this.seaded = Seaded.getInstance();
        looNupuKuulajad();
        looHiireKuulajad();
    }

    private void looNupuKuulajad() {
        juhtpaneel.getKäivitaNupp().setOnAction(e -> {
            try {
                käivitaMäng();
            } catch (ViganeStartVajutus ex) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText(ex.getMessage());
                alert.showAndWait();
            }
        });
        juhtpaneel.getPeataNupp().setOnAction(e -> peataMäng());
        juhtpaneel.getLähtestaNupp().setOnAction(e -> lähtesta());
        juhtpaneel.getTaastaPilt().setOnAction(e -> taastaPilt());
        juhtpaneel.getSeaded().setOnAction(e -> avaSeaded());
    }

    private void looHiireKuulajad() {
        laud.addEventHandler(MouseEvent.MOUSE_CLICKED, this::tegeleRuudulVajutusega);
        laud.addEventHandler(MouseEvent.MOUSE_DRAGGED, this::tegeleRuudulVajutusega);
    }

    public void looKlaviatuuriKuulajad() {
        laud.getScene().addEventHandler(KeyEvent.KEY_PRESSED, event -> {
            if (event.getCode() == KeyCode.P) {
                if (jookseb) {
                    peataMäng();
                } else {
                    try {
                        käivitaMäng();
                    } catch (ViganeStartVajutus ex) {
                        System.out.println(ex.getMessage());
                    }
                }
            }
        });
    }

    private void käivitaMäng() throws ViganeStartVajutus {
        if (jookseb){
            throw new ViganeStartVajutus();
        } else {
            mänguTsükkel = new Timeline(new KeyFrame(Duration.seconds(seaded.getMänguKiirus()), e -> {
                BitSet järgmineOlek = ManguOlek.järgmineOlek(laud.getRuudud(), laud.getLauaLaius(), laud.getLauaPikkus());
                laud.uuendaLauda(järgmineOlek);

                if (järgmineOlek.cardinality() == 0) {
                    peataMäng();
                }
            }));
            mänguTsükkel.setCycleCount(Animation.INDEFINITE);
            mänguTsükkel.play();
            jookseb = true;
            juhtpaneel.uuendaTulukest(jookseb);
        }
    }

    private void peataMäng() {
        if (jookseb) {
            mänguTsükkel.stop();
            jookseb = false;
            juhtpaneel.uuendaTulukest(jookseb);
        }
    }

    private void lähtesta() {
        peataMäng();
        laud.getRuudud().clear();
        laud.uuendaDisplay();
    }

    private void taastaPilt() {
        peataMäng();
        laud.taastaPilt();
    }

    private void avaSeaded() {
        SeadedAken seadedAken = new SeadedAken(laud);
        seadedAken.avaSeaded();
    }

    private void tegeleRuudulVajutusega(MouseEvent sündmus) {
        if (!jookseb || !seaded.isLubaAinultKuiSeisab()) {
            double ruuduSuurus = laud.getRuuduSuurus();
            int veerg = (int) (sündmus.getX() / ruuduSuurus);
            int rida = (int) (sündmus.getY() / ruuduSuurus);

            if (veerg >= 0 && veerg < laud.getLauaLaius() && rida >= 0 && rida < laud.getLauaPikkus()) {
                int index = rida * laud.getLauaLaius() + veerg;
                if (sündmus.getButton() == MouseButton.SECONDARY) {
                    laud.getRuudud().clear(index);
                } else {
                    laud.getRuudud().set(index);
                }
                laud.uuendaDisplay();
            }
        }
    }

    public boolean isJookseb() {
        return jookseb;
    }
}

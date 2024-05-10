package org.example.gameoflifegui;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

import java.util.BitSet;

public class Juhtimine extends VBox {
    private Mangulaud laud;
    private BitSet pildiBitSet;
    private Timeline mänguTsükkel;
    private boolean jookseb = false;
    private Label olekuSilt;
    private Circle tuluke;
    private DropShadow tulukeseGlow;
    private boolean lubaAinultKuiSeisab = false;
    private Seaded seaded;


    public Juhtimine(Mangulaud laud) {
        this(laud, new BitSet());
    }

    public Juhtimine(Mangulaud laud, BitSet pildiBitSet){
        this.laud = laud;
        this.pildiBitSet = (BitSet) pildiBitSet.clone();
        this.seaded = new Seaded(laud);
        // Loome nupud mängu juhtimiseks
        HBox nupuKast = looNupuKast();

        // Loome olekusildi ja tulukese
        olekuSilt = new Label("Jookseb:");
        tuluke = new Circle(5);
        tuluke.setFill(Color.RED);
        tulukeseGlow = new DropShadow(5, Color.RED);
        tuluke.setEffect(tulukeseGlow);

        // Paigutame olekusildi ja tulukese horisontaalselt
        HBox olekuKast = new HBox(5, olekuSilt, tuluke);
        olekuKast.setAlignment(Pos.CENTER_LEFT);

        // Loome tühiku olekusildi ja nuppude vahele
        Region tühik = new Region();
        HBox.setHgrow(tühik, Priority.ALWAYS);

        // Paigutame olekusildi, tühiku ja nupud horisontaalselt
        HBox juhtpaneeliKast = new HBox(10, olekuKast, tühik, nupuKast);
        juhtpaneeliKast.setAlignment(Pos.CENTER);
        VBox.setMargin(juhtpaneeliKast, new Insets(10, 0, 0, 0));

        // Seadistame vahemikud ja joonduse
        setSpacing(10);
        setAlignment(Pos.CENTER);
        getChildren().add(juhtpaneeliKast);

        // Lisame hiirekliki kuulaja mängulauale
        laud.addEventHandler(MouseEvent.MOUSE_CLICKED, this::tegeleRuudulVajutusega);
        laud.addEventHandler(MouseEvent.MOUSE_DRAGGED, this::tegeleRuudulVajutusega);
    }

    private HBox looNupuKast() {
        Button käivitaNupp = new Button("Käivita");
        Button peataNupp = new Button("Peata");
        Button lähtestaNupp = new Button("Lähtesta");
        Button taastaPilt = new Button("Taasta Pilt");
        Button avaSeaded = new Button("Seaded");

        // Seome nupud vastavate meetoditega
        käivitaNupp.setOnAction(e -> käivitaMäng());
        peataNupp.setOnAction(e -> peataMäng());
        lähtestaNupp.setOnAction(e -> lähtesta());
        taastaPilt.setOnAction(e -> taastaPilt());
        avaSeaded.setOnAction(e -> avaSeaded());

        // Paigutame nupud horisontaalselt
        HBox nupuKast = new HBox(10, käivitaNupp, peataNupp, lähtestaNupp, taastaPilt, avaSeaded);
        nupuKast.setAlignment(Pos.CENTER_RIGHT);
        return nupuKast;
    }

    // Meetod mängu käivitamiseks
    private void käivitaMäng() {
        if (!jookseb) {
            mänguTsükkel = new Timeline(new KeyFrame(Duration.seconds(0.1), e -> {
                BitSet järgmineOlek = ManguOlek.järgmineOlek(laud.getRuudud(), laud.getLauaLaius(), laud.getLauaPikkus());
                laud.uuendaLauda(järgmineOlek);

                // Kontrollime, kas mõni ruut veel elab
                if (järgmineOlek.cardinality() == 0) {
                    peataMäng();
                }
            }));
            mänguTsükkel.setCycleCount(Animation.INDEFINITE);
            mänguTsükkel.play();
            jookseb = true;
            uuendaTulukest();
        }
    }

    private void avaSeaded(){
        seaded.avaSeaded();
    }

    // Meetod mängu peatamiseks
    private void peataMäng() {
        if (jookseb) {
            mänguTsükkel.stop();
            jookseb = false;
            uuendaTulukest();
        }
    }

    // Meetod pildi taastamiseks, kui seda on
    private void taastaPilt(){
        laud.getRuudud().clear();
        laud.setRuudud((BitSet) pildiBitSet.clone());
        laud.uuendaDisplay();
    }

    // Meetod mängu lähtestamiseks
    private void lähtesta() {
        peataMäng();
        laud.getRuudud().clear();
        laud.uuendaDisplay();
    }

    // Meetod hiirekliki käsitlemiseks mängulaual
    private void tegeleRuudulVajutusega(MouseEvent event) {
        if (!lubaAinultKuiSeisab || !jookseb) {
            double ruuduSuurus = laud.getRuuduSuurus();

            int col = (int) (event.getX() / ruuduSuurus);
            int row = (int) (event.getY() / ruuduSuurus);

            if (col >= 0 && col < laud.getLauaLaius() && row >= 0 && row < laud.getLauaPikkus()) {
                int index = row * laud.getLauaLaius() + col;
                if (event.getButton() == MouseButton.SECONDARY){
                    laud.getRuudud().clear(index);
                } else {
                    laud.getRuudud().set(index);
                }
                laud.uuendaDisplay();
            }
        }
    }

    // Meetod tulukese värvi uuendamiseks
    private void uuendaTulukest() {
        Color värv = jookseb ? Color.LIME : Color.RED;
        tuluke.setFill(värv);
        tulukeseGlow.setColor(värv);
    }
}
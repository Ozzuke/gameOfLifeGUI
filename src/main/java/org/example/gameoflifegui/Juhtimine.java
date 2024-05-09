package org.example.gameoflifegui;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
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
    private Timeline mänguTsükkel;
    private boolean jookseb = false;
    private Label olekuSilt;
    private Circle tuluke;
    private DropShadow tulukeseGlow;
    private boolean heleRežiim = true;
    private Button režiimiNupp = new Button("Tume režiim");

    public Juhtimine(Mangulaud laud) {
        this.laud = laud;

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
    }

    private HBox looNupuKast() {
        Button käivitaNupp = new Button("Käivita");
        Button peataNupp = new Button("Peata");
        Button lähtestaNupp = new Button("Lähtesta");

        // Seome nupud vastavate meetoditega
        käivitaNupp.setOnAction(e -> käivitaMäng());
        peataNupp.setOnAction(e -> peataMäng());
        lähtestaNupp.setOnAction(e -> lähtesta());
        režiimiNupp.setOnAction(e -> muudaRežiimi());

        // Paigutame nupud horisontaalselt
        HBox nupuKast = new HBox(10, käivitaNupp, peataNupp, lähtestaNupp, režiimiNupp);
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

    // Meetod mängu peatamiseks
    private void peataMäng() {
        if (jookseb) {
            mänguTsükkel.stop();
            jookseb = false;
            uuendaTulukest();
        }
    }

    // Meetod mängu lähtestamiseks
    private void lähtesta() {
        peataMäng();
        laud.getRuudud().clear();
        laud.uuendaDisplay();
    }

    // Meetod režiimi muutmiseks
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
    }

    // Meetod hiirekliki käsitlemiseks mängulaual
    private void tegeleRuudulVajutusega(MouseEvent event) {
        if (!jookseb) {
            double ruuduSuurus = laud.getRuuduSuurus();

            int col = (int) (event.getX() / ruuduSuurus);
            int row = (int) (event.getY() / ruuduSuurus);

            if (col >= 0 && col < laud.getLauaLaius() && row >= 0 && row < laud.getLauaPikkus()) {
                int index = row * laud.getLauaLaius() + col;
                laud.getRuudud().flip(index);
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
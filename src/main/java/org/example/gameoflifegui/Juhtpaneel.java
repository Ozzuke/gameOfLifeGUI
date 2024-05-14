package org.example.gameoflifegui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Juhtpaneel extends VBox {
    private Label olekuSilt;
    private Circle tuluke;
    private Button käivitaNupp, peataNupp, lähtestaNupp, taastaPilt, seaded;

    public Juhtpaneel() {
        olekuSilt = new Label("Jookseb:");
        tuluke = new Circle(5);
        tuluke.setFill(Color.RED);

        käivitaNupp = new Button("Käivita");
        peataNupp = new Button("Peata");
        lähtestaNupp = new Button("Lähtesta");
        taastaPilt = new Button("Taasta Pilt");
        seaded = new Button("Seaded");

        HBox olekuKast = new HBox(5, olekuSilt, tuluke);
        olekuKast.setAlignment(Pos.CENTER_LEFT);

        Region tühik = new Region();
        HBox.setHgrow(tühik, Priority.ALWAYS);

        HBox nupuKast = new HBox(10, käivitaNupp, peataNupp, lähtestaNupp, taastaPilt, seaded);
        nupuKast.setAlignment(Pos.CENTER_RIGHT);

        HBox juhtpaneeliKast = new HBox(10, olekuKast, tühik, nupuKast);
        juhtpaneeliKast.setAlignment(Pos.CENTER);
        VBox.setMargin(juhtpaneeliKast, new Insets(10, 0, 0, 0));

        setSpacing(10);
        setAlignment(Pos.CENTER);
        getChildren().add(juhtpaneeliKast);
    }

    public Button getKäivitaNupp() {
        return käivitaNupp;
    }

    public Button getPeataNupp() {
        return peataNupp;
    }

    public Button getLähtestaNupp() {
        return lähtestaNupp;
    }

    public Button getTaastaPilt() {
        return taastaPilt;
    }

    public Button getSeaded() {
        return seaded;
    }

    public Circle getTuluke() {
        return tuluke;
    }

    public void uuendaTulukest(boolean jookseb) {
        tuluke.setFill(jookseb ? Color.LIME : Color.RED);
    }
}

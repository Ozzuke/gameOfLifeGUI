module org.example.gameoflifegui {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens org.example.gameoflifegui to javafx.fxml;
    exports org.example.gameoflifegui;
}
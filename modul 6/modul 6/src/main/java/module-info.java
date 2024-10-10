module org.example.modul6 {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.modul6 to javafx.fxml;
    exports org.example.modul6;
}
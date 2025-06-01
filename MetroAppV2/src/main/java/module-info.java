module com.example.metroappv2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.metroappv2 to javafx.fxml;
    exports com.example.metroappv2;
}
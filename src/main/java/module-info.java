module org.example.aircraftsystem {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.ikonli.javafx;
    requires java.sql;

    opens org.example.aircraftsystem to javafx.fxml;
    exports org.example.aircraftsystem;
    exports org.example.aircraftsystem.data;
    opens org.example.aircraftsystem.data to javafx.fxml;
}
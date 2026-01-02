module com.tech.med {
    requires javafx.controls;
    requires javafx.fxml;
    opens com.tech.med to javafx.fxml;
    exports com.tech.med;
}
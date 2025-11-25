module com.trabalhofinal {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.trabalhofinal to javafx.fxml;
    exports com.trabalhofinal;
}
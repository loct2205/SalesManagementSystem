module com.salesmanagementsystem.salesmanagementsystem {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.salesmanagementsystem.salesmanagementsystem to javafx.fxml;
    exports com.salesmanagementsystem.salesmanagementsystem;
    exports com.salesmanagementsystem.salesmanagementsystem.Controller;
    opens com.salesmanagementsystem.salesmanagementsystem.Controller to javafx.fxml;
}
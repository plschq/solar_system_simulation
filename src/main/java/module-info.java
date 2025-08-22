module sss {
    
    requires javafx.controls;
    requires javafx.fxml;
    
    opens sss to javafx.fxml;
    exports sss;
    
}

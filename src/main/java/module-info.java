module sss {
    
    requires javafx.controls;
    requires javafx.fxml;
    
    requires org.kordamp.bootstrapfx.core;
    
    opens sss to javafx.fxml;
    exports sss;
    
}

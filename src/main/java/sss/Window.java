package sss;


import javafx.application.Application;
import javafx.stage.Stage;


public final class Window extends Application {
    
    public static Stage stage;
    
    
    public static void init(Stage stage) {
        Window.stage = stage;
        Window.stage.setTitle("Solar System Simulation [v1.0.0]");
        // Window.stage.setScene();
        Window.stage.setFullScreen(false);
        Window.stage.setAlwaysOnTop(false);
        Window.stage.setResizable(true);
        Window.stage.setMinWidth(500);
        Window.stage.setMinHeight(300);
    }
    
    public static void show() {Window.stage.show();}
    
    @Override public void start(Stage stage) {Launcher.start(stage);}
    @Override public void stop() {Launcher.stop();}
    
    public static void main(String[] args) {Window.launch(args);}
    
}

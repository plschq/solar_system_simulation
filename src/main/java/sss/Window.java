package sss;


import sss.scenes.solarSystemScene.SolarSystemScene;

import javafx.application.Application;
import javafx.stage.Stage;


public final class Window extends Application {
    
    public static Stage stage;
    
    
    public static void init(Stage stage) {
        Window.stage = stage;
        Window.stage.setTitle("Solar System Simulation [v1.0.2]");
        // Window.stage.setScene();
        Window.stage.setFullScreen(false);
        Window.stage.setAlwaysOnTop(false);
        Window.stage.setResizable(true);
        Window.stage.setMinWidth(500);
        Window.stage.setMinHeight(300);
        
        Window.stage.widthProperty().addListener((observable, oldValue, newValue) -> {
            if (Double.isNaN(oldValue.doubleValue())) {
                SolarSystemScene.root.setTranslateX(0.5 * newValue.doubleValue());
            } else {
                SolarSystemScene.root.setTranslateX(SolarSystemScene.root.getTranslateX() + 0.5 * (newValue.doubleValue() - oldValue.doubleValue()));
            } SolarSystemScene.updateLabels();
        }); Window.stage.heightProperty().addListener((observable, oldValue, newValue) -> {
            if (Double.isNaN(oldValue.doubleValue())) {
                SolarSystemScene.root.setTranslateY(0.5 * newValue.doubleValue());
            } else {
                SolarSystemScene.root.setTranslateY(SolarSystemScene.root.getTranslateY() + 0.5 * (newValue.doubleValue() - oldValue.doubleValue()));
            } SolarSystemScene.updateLabels();
        });
    }
    
    public static void show() {Window.stage.show();}
    
    @Override public void start(Stage stage) {Launcher.start(stage);}
    @Override public void stop() {Launcher.stop();}
    
    public static void main(String[] args) {Window.launch(args);}
    
}

package sss;


import sss.scenes.solarSystemScene.SolarSystemScene;

import javafx.application.Application;
import javafx.stage.Stage;

import java.io.FileNotFoundException;


public final class Window extends Application {
    
    public static Stage stage;
    
    
    public static void init(Stage stage) {
        Window.stage = stage;
        Window.stage.setTitle("Solar system simulation [v1.2.0]");
        // Window.stage.setScene();
        Window.stage.setFullScreen(false);
        Window.stage.setAlwaysOnTop(false);
        Window.stage.setResizable(true);
        Window.stage.setMinWidth(500);
        Window.stage.setMinHeight(300);
        
        Window.stage.widthProperty().addListener((observable, oldValue, newValue) -> {
            if (Double.isNaN(oldValue.doubleValue())) {
                SolarSystemScene.planetsAnchor.setTranslateX(0.5 * newValue.doubleValue());
            } else {
                SolarSystemScene.planetsAnchor.setTranslateX(SolarSystemScene.planetsAnchor.getTranslateX() +
                        0.5 * (newValue.doubleValue() - oldValue.doubleValue()));
            }
        }); Window.stage.heightProperty().addListener((observable, oldValue, newValue) -> {
            if (Double.isNaN(oldValue.doubleValue())) {
                SolarSystemScene.planetsAnchor.setTranslateY(0.5 * newValue.doubleValue());
            } else {
                SolarSystemScene.planetsAnchor.setTranslateY(SolarSystemScene.planetsAnchor.getTranslateY() +
                        0.5 * (newValue.doubleValue() - oldValue.doubleValue()));
            }
        });
    }
    
    public static void show() {Window.stage.show();}
    
    @Override public void start(Stage stage) throws FileNotFoundException {Launcher.start(stage);}
    @Override public void stop() {Launcher.stop();}
    
    public static void main(String[] args) {Window.launch(args);}
    
}

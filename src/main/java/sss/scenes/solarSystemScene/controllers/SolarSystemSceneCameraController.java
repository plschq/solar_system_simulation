package sss.scenes.solarSystemScene.controllers;


import sss.dataclasses.XY;
import sss.scenes.solarSystemScene.SolarSystemScene;

import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;


public final class SolarSystemSceneCameraController {
    
    private static XY lastMousePressed;
    private static XY lastCameraTranslate;
    
    
    public static void init() {
        
        SolarSystemScene.scene.addEventHandler(MouseEvent.MOUSE_PRESSED, e -> {
            if (!e.getButton().equals(MouseButton.SECONDARY)) {return;}
            
            SolarSystemSceneCameraController.lastMousePressed = new XY(
                    e.getSceneX(),
                    e.getSceneY());
            SolarSystemSceneCameraController.lastCameraTranslate = new XY(
                    SolarSystemScene.scene.getCamera().getTranslateX(),
                    SolarSystemScene.scene.getCamera().getTranslateY());
        });
        
        SolarSystemScene.scene.addEventHandler(MouseEvent.MOUSE_DRAGGED, e -> {
            if (!e.getButton().equals(MouseButton.SECONDARY)) {return;}
    
            SolarSystemScene.scene.getCamera().setTranslateX(SolarSystemSceneCameraController.lastCameraTranslate.x.getMeters() +
                    SolarSystemSceneCameraController.lastMousePressed.x.getMeters() - e.getSceneX());
            SolarSystemScene.scene.getCamera().setTranslateY(SolarSystemSceneCameraController.lastCameraTranslate.y.getMeters() +
                    SolarSystemSceneCameraController.lastMousePressed.y.getMeters() - e.getSceneY());
        });
        
        SolarSystemScene.scene.addEventHandler(ScrollEvent.SCROLL, e -> {
            double step = 1.3;
            if (e.getDeltaY() > 0) {
                SolarSystemScene.ZOOM *= step;
            } else if (e.getDeltaY() < 0) {
                SolarSystemScene.ZOOM /= step;
            }
        });
        
    }
    
}

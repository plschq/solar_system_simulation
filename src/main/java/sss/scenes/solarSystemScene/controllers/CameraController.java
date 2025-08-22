package sss.scenes.solarSystemScene.controllers;


import sss.Window;
import sss.dataclasses.XY;
import sss.scenes.solarSystemScene.SolarSystemScene;

import javafx.scene.Cursor;
import javafx.scene.input.*;


public final class CameraController {
    
    private static XY lastMousePressed;
    private static XY lastTranslate;
    
    
    public static void init() {
        
        SolarSystemScene.scene.addEventHandler(MouseEvent.MOUSE_PRESSED, e -> {
            if (!e.getButton().equals(MouseButton.PRIMARY)) {return;}
    
            CameraController.lastMousePressed = new XY(
                    e.getSceneX(),
                    e.getSceneY());
            CameraController.lastTranslate = new XY(
                    SolarSystemScene.root.getTranslateX(),
                    SolarSystemScene.root.getTranslateY());
            
            SolarSystemScene.scene.setCursor(Cursor.MOVE);
        });
        
        SolarSystemScene.scene.addEventHandler(MouseEvent.MOUSE_DRAGGED, e -> {
            if (!e.getButton().equals(MouseButton.PRIMARY)) {return;}
            
            SolarSystemScene.root.setTranslateX(CameraController.lastTranslate.x.getMeters() -
                    CameraController.lastMousePressed.x.getMeters() + e.getSceneX());
            SolarSystemScene.root.setTranslateY(CameraController.lastTranslate.y.getMeters() -
                    CameraController.lastMousePressed.y.getMeters() + e.getSceneY());
            
            SolarSystemScene.updateLabels();
        });
    
        SolarSystemScene.scene.addEventHandler(MouseEvent.MOUSE_RELEASED, e -> {
            if (!e.getButton().equals(MouseButton.PRIMARY)) {return;}
            
            SolarSystemScene.scene.setCursor(Cursor.DEFAULT);
        });
        
        SolarSystemScene.scene.addEventHandler(ScrollEvent.SCROLL, e -> {
            double step = 1.075;
            if (e.getDeltaY() > 0) {
                SolarSystemScene.root.setTranslateX((SolarSystemScene.root.getTranslateX() - e.getSceneX()) * step + e.getSceneX());
                SolarSystemScene.root.setTranslateY((SolarSystemScene.root.getTranslateY() - e.getSceneY()) * step + e.getSceneY());
                SolarSystemScene.ZOOM *= step;
                SolarSystemScene.update();
            } else if (e.getDeltaY() < 0) {
                SolarSystemScene.root.setTranslateX((SolarSystemScene.root.getTranslateX() - e.getSceneX()) / step + e.getSceneX());
                SolarSystemScene.root.setTranslateY((SolarSystemScene.root.getTranslateY() - e.getSceneY()) / step + e.getSceneY());
                SolarSystemScene.ZOOM /= step;
                SolarSystemScene.update();
            } SolarSystemScene.updateLabels();
        });
    
        SolarSystemScene.scene.addEventHandler(KeyEvent.KEY_PRESSED, e -> {
            double step = 1.075;
            if (e.getCode().equals(KeyCode.UP) || e.getCode().equals(KeyCode.W)) {
                SolarSystemScene.root.setTranslateX((SolarSystemScene.root.getTranslateX() -
                        Window.stage.getWidth() * 0.5) * step + Window.stage.getWidth() * 0.5);
                SolarSystemScene.root.setTranslateY((SolarSystemScene.root.getTranslateY() -
                        Window.stage.getHeight() * .5) * step + Window.stage.getHeight() * .5);
                SolarSystemScene.ZOOM *= step;
                SolarSystemScene.update();
            } else if (e.getCode().equals(KeyCode.DOWN) || e.getCode().equals(KeyCode.S)) {
                SolarSystemScene.root.setTranslateX((SolarSystemScene.root.getTranslateX() -
                        Window.stage.getWidth() * 0.5) / step + Window.stage.getWidth() * 0.5);
                SolarSystemScene.root.setTranslateY((SolarSystemScene.root.getTranslateY() -
                        Window.stage.getHeight() * .5) / step + Window.stage.getHeight() * .5);
                SolarSystemScene.ZOOM /= step;
                SolarSystemScene.update();
            } SolarSystemScene.updateLabels();
        });
        
    }
    
}

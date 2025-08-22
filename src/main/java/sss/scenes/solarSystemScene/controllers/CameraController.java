package sss.scenes.solarSystemScene.controllers;


import sss.Window;
import sss.dataclasses.XY;
import sss.scenes.solarSystemScene.SolarSystemScene;

import javafx.scene.Cursor;
import javafx.scene.input.*;


public final class CameraController {
    
    private static XY lastMousePressed = null;
    private static XY lastTranslate = null;
    
    
    public static void init() {
        
        SolarSystemScene.scene.addEventHandler(MouseEvent.MOUSE_PRESSED, e -> {
            if (!e.getButton().equals(MouseButton.PRIMARY)) {return;}
    
            lastMousePressed = new XY(
                    e.getSceneX(),
                    e.getSceneY());
            lastTranslate = new XY(
                    SolarSystemScene.root.getTranslateX(),
                    SolarSystemScene.root.getTranslateY());
            
            SolarSystemScene.scene.setCursor(Cursor.MOVE);
        });
        
        SolarSystemScene.scene.addEventHandler(MouseEvent.MOUSE_DRAGGED, e -> {
            if (!e.getButton().equals(MouseButton.PRIMARY)) {return;}
            
            SolarSystemScene.root.setTranslateX(lastTranslate.x.getMeters() -
                    lastMousePressed.x.getMeters() + e.getSceneX());
            SolarSystemScene.root.setTranslateY(lastTranslate.y.getMeters() -
                    lastMousePressed.y.getMeters() + e.getSceneY());
            
            SolarSystemScene.updateLabels();
        });
    
        SolarSystemScene.scene.addEventHandler(MouseEvent.MOUSE_RELEASED, e -> {
            if (!e.getButton().equals(MouseButton.PRIMARY)) {return;}
    
            lastMousePressed = null;
            lastTranslate = null;
            
            SolarSystemScene.scene.setCursor(Cursor.DEFAULT);
        });
        
        SolarSystemScene.scene.addEventHandler(ScrollEvent.SCROLL, e -> {
            if (lastMousePressed != null || lastTranslate != null) {return;}
            
            double step = 1.075;
            
            if (e.getDeltaY() > 0) {
                SolarSystemScene.root.setTranslateX((SolarSystemScene.root.getTranslateX() -
                        e.getSceneX()) * step + e.getSceneX());
                SolarSystemScene.root.setTranslateY((SolarSystemScene.root.getTranslateY() -
                        e.getSceneY()) * step + e.getSceneY());
                SolarSystemScene.ZOOM *= step;
                SolarSystemScene.update();
            } else if (e.getDeltaY() < 0 && SolarSystemScene.ZOOM / step > 1.7e-4) {
                SolarSystemScene.root.setTranslateX((SolarSystemScene.root.getTranslateX() -
                        e.getSceneX()) / step + e.getSceneX());
                SolarSystemScene.root.setTranslateY((SolarSystemScene.root.getTranslateY() -
                        e.getSceneY()) / step + e.getSceneY());
                SolarSystemScene.ZOOM /= step;
                SolarSystemScene.update();
            } SolarSystemScene.updateLabels();
        });
    
        SolarSystemScene.scene.addEventHandler(KeyEvent.KEY_PRESSED, e -> {
            if (lastMousePressed != null || lastTranslate != null) {return;}
            
            double step = 1.075;
            
            if (e.getCode().equals(KeyCode.UP) || e.getCode().equals(KeyCode.W)) {
                SolarSystemScene.root.setTranslateX((SolarSystemScene.root.getTranslateX() -
                        Window.stage.getWidth() * 0.5) * step + Window.stage.getWidth() * 0.5);
                SolarSystemScene.root.setTranslateY((SolarSystemScene.root.getTranslateY() -
                        Window.stage.getHeight() * .5) * step + Window.stage.getHeight() * .5);
                SolarSystemScene.ZOOM *= step;
                SolarSystemScene.update();
            } else if (SolarSystemScene.ZOOM / step > 1.7e-4 && (e.getCode().equals(KeyCode.DOWN) || e.getCode().equals(KeyCode.S))) {
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

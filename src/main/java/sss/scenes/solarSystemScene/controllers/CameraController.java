package sss.scenes.solarSystemScene.controllers;


import sss.Planets;
import sss.Window;
import sss.dataclasses.XY;
import sss.scenes.solarSystemScene.SolarSystemScene;

import javafx.scene.Cursor;
import javafx.scene.input.*;
import sss.scenes.solarSystemScene.components.Planet;


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
                    SolarSystemScene.planetsAnchor.getTranslateX(),
                    SolarSystemScene.planetsAnchor.getTranslateY());
            
            SolarSystemScene.scene.setCursor(Cursor.MOVE);
        });
        
        SolarSystemScene.scene.addEventHandler(MouseEvent.MOUSE_DRAGGED, e -> {
            if (!e.getButton().equals(MouseButton.PRIMARY)) {return;}
            
            SolarSystemScene.planetsAnchor.setTranslateX(lastTranslate.x.getMeters() -
                    lastMousePressed.x.getMeters() + e.getSceneX() + FocusController.getDiff().x.getMeters());
            SolarSystemScene.planetsAnchor.setTranslateY(lastTranslate.y.getMeters() -
                    lastMousePressed.y.getMeters() + e.getSceneY() + FocusController.getDiff().y.getMeters());
    
            FocusController.updateLastPlanetPos();
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
                SolarSystemScene.planetsAnchor.setTranslateX((SolarSystemScene.planetsAnchor.getTranslateX() -
                        e.getSceneX()) * step + e.getSceneX());
                SolarSystemScene.planetsAnchor.setTranslateY((SolarSystemScene.planetsAnchor.getTranslateY() -
                        e.getSceneY()) * step + e.getSceneY());
                SolarSystemScene.ZOOM *= step;
                SolarSystemScene.update();
            } else if (e.getDeltaY() < 0 && SolarSystemScene.ZOOM / step > 1.7e-4) {
                SolarSystemScene.planetsAnchor.setTranslateX((SolarSystemScene.planetsAnchor.getTranslateX() -
                        e.getSceneX()) / step + e.getSceneX());
                SolarSystemScene.planetsAnchor.setTranslateY((SolarSystemScene.planetsAnchor.getTranslateY() -
                        e.getSceneY()) / step + e.getSceneY());
                SolarSystemScene.ZOOM /= step;
                SolarSystemScene.update();
            } SolarSystemScene.updateLabels();
        });
    
        SolarSystemScene.scene.addEventHandler(KeyEvent.KEY_PRESSED, e -> {
            if (lastMousePressed != null || lastTranslate != null) {return;}
            
            double step = 1.075;
            
            if (e.getCode().equals(KeyCode.UP) || e.getCode().equals(KeyCode.W)) {
                SolarSystemScene.planetsAnchor.setTranslateX((SolarSystemScene.planetsAnchor.getTranslateX() -
                        Window.stage.getWidth() * 0.5) * step + Window.stage.getWidth() * 0.5);
                SolarSystemScene.planetsAnchor.setTranslateY((SolarSystemScene.planetsAnchor.getTranslateY() -
                        Window.stage.getHeight() * .5) * step + Window.stage.getHeight() * .5);
                SolarSystemScene.ZOOM *= step;
                SolarSystemScene.update();
            } else if (SolarSystemScene.ZOOM / step > 1.7e-4 && (e.getCode().equals(KeyCode.DOWN) || e.getCode().equals(KeyCode.S))) {
                SolarSystemScene.planetsAnchor.setTranslateX((SolarSystemScene.planetsAnchor.getTranslateX() -
                        Window.stage.getWidth() * 0.5) / step + Window.stage.getWidth() * 0.5);
                SolarSystemScene.planetsAnchor.setTranslateY((SolarSystemScene.planetsAnchor.getTranslateY() -
                        Window.stage.getHeight() * .5) / step + Window.stage.getHeight() * .5);
                SolarSystemScene.ZOOM /= step;
                SolarSystemScene.update();
            } // else {FocusController.setFocus(Planets.all.get(2));} SolarSystemScene.updateLabels();
        });
        
    }
    
}

package sss.scenes.solarSystemScene.controllers;


import sss.Window;
import sss.dataclasses.XY;
import sss.scenes.solarSystemScene.SolarSystemScene;
import sss.scenes.solarSystemScene.components.Planet;


public final class FocusController {
    
    public static Planet focusedOn;
    
    public static XY lastPlanetPos;
    
    
    public static void init() {
        
        // SolarSystemScene.scene.addEventHandler(MouseEvent.MOUSE_PRESSED, e -> {
        //     System.out.println(e.getTarget());
        // });
        
    }
    
    public static void setFocus(Planet planet) {
        focusedOn = planet;
        
        SolarSystemScene.planetsAnchor.setTranslateX(
                Window.stage.getWidth() * 0.5 - focusedOn.planetAnchor.getTranslateX());
        SolarSystemScene.planetsAnchor.setTranslateY(
                Window.stage.getHeight() * .5 - focusedOn.planetAnchor.getTranslateY());
    
        updateLastPlanetPos();
    }
    
    public static void updateFocus() {
        SolarSystemScene.planetsAnchor.setTranslateX(
                SolarSystemScene.planetsAnchor.getTranslateX() + getDiff().x.getMeters());
        SolarSystemScene.planetsAnchor.setTranslateY(
                SolarSystemScene.planetsAnchor.getTranslateY() + getDiff().y.getMeters());
    
        updateLastPlanetPos();
    }
    
    public static void updateLastPlanetPos() {
        lastPlanetPos = new XY(
                focusedOn.planetAnchor.getTranslateX(),
                focusedOn.planetAnchor.getTranslateY());
    }
    
    public static XY getDiff() {
        return new XY(
                lastPlanetPos.x.getMeters() - focusedOn.planetAnchor.getTranslateX(),
                lastPlanetPos.y.getMeters() - focusedOn.planetAnchor.getTranslateY());
    }
    
}

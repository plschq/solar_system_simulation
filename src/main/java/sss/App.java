package sss;


import sss.dataclasses.Distance;
import sss.dataclasses.XY;
import sss.scenes.solarSystemScene.SolarSystemScene;

import java.io.FileNotFoundException;


public final class App {
    
    public static void init() throws FileNotFoundException {
        SolarSystemScene.init();
        Window.stage.setScene(SolarSystemScene.scene);
    }
    
    public static double getAngleBetween(XY point1, XY point2) {
        return App.simplifyAngle(
                Math.atan2(point2.x.getMeters() - point1.x.getMeters(),
                        point2.y.getMeters() - point1.y.getMeters()) * 180 / Math.PI);
    }
    
    public static Distance getDistanceBetween(XY point1, XY point2) {
        return new Distance(1 + Math.sqrt(
                Math.pow(point2.x.getMeters() - point1.x.getMeters(), 2) +
                        Math.pow(point2.y.getMeters() - point1.y.getMeters(), 2)));
    }
    
    public static double simplifyAngle(double angle) {
        return ((angle % 360) + 360) % 360;
    }
    
}

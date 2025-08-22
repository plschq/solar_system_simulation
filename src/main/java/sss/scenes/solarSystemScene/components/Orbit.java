package sss.scenes.solarSystemScene.components;


import sss.dataclasses.Distance;
import sss.dataclasses.XY;
import sss.scenes.solarSystemScene.SolarSystemScene;

import javafx.scene.shape.Ellipse;
import javafx.scene.paint.Color;


public class Orbit {
    
    public final Ellipse orbitNode = new Ellipse();
    
    public final Planet parent;
    
    public final XY center;
    public final XY semiAxes;
    
    public final Distance aphelion;
    public final Distance perihelion;
    
    public final double eccentricity;
    public final double treatmentPeriod;
    
    
    public Orbit(Planet parent, Distance largeSemiAxis, double eccentricity, double treatmentPeriod) {
        this.parent = parent;
        this.semiAxes = new XY(largeSemiAxis.getMeters() *
                Math.sqrt(1 - Math.pow(eccentricity, 2)), largeSemiAxis.getMeters());
        this.eccentricity = eccentricity;
        this.aphelion = new Distance(getLargeSemiAxis().getMeters() * (1 + eccentricity));
        this.perihelion = new Distance(getLargeSemiAxis().getMeters() * (1 - eccentricity));
        this.treatmentPeriod = treatmentPeriod;
    
        this.center = new XY(0, 0.5 * (this.aphelion.getMeters() - this.perihelion.getMeters()));
        
        this.orbitNode.setFill(Color.TRANSPARENT);
        this.orbitNode.setStrokeWidth(2);
        this.orbitNode.setViewOrder(1);
        
        this.parent.anchor.getChildren().add(this.orbitNode);
    }
    
    
    public void showOrbitDim() {
        this.orbitNode.setStroke(Color.web("#fff1"));
    } public void showOrbitBright() {
        this.orbitNode.setStroke(Color.web("#fff3"));
    } public void hideOrbit() {
        this.orbitNode.setStroke(Color.TRANSPARENT);
    }
    
    public void update() {
        this.orbitNode.setRadiusX(this.semiAxes.x.getPixels() * SolarSystemScene.ZOOM);
        this.orbitNode.setRadiusY(this.semiAxes.y.getPixels() * SolarSystemScene.ZOOM);
        this.orbitNode.setCenterX(this.center.x.getPixels() * SolarSystemScene.ZOOM);
        this.orbitNode.setCenterY(this.center.y.getPixels() * SolarSystemScene.ZOOM);
    }
    
    public Distance getLargeSemiAxis() {
        // return new Distance(Math.max(this.semiAxes.x.getMeters(), this.semiAxes.y.getMeters()));
        return this.semiAxes.y;
    }
    
}

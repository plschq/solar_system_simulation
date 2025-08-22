package sss.scenes.solarSystemScene.components;


import sss.dataclasses.Distance;
import sss.dataclasses.XY;
import sss.scenes.solarSystemScene.SolarSystemScene;

import javafx.scene.shape.Ellipse;
import javafx.scene.paint.Color;


public class Orbit {
    
    public final Ellipse orbitNode;
    
    public Planet parent;
    
    public XY center;
    public XY semiAxes;
    
    public final Distance aphelion;
    public final Distance perihelion;
    
    public final double treatmentPeriod;
    // public final double slant;
    
    
    public Orbit(Planet parent, XY semiAxes, Distance aphelion, Distance perihelion, double treatmentPeriod) {
        this.parent = parent;
        
        this.center = new XY(
                Distance.byPixels(parent.planetNode.getCenterX()).getMeters(),
                Distance.byPixels(parent.planetNode.getCenterY()).getMeters() +
                        Math.abs(aphelion.getMeters()-perihelion.getMeters()));
        this.semiAxes = semiAxes;
        this.aphelion = aphelion;
        this.perihelion = perihelion;
        // this.slant = slant;
        
        this.treatmentPeriod = treatmentPeriod;
        
        this.orbitNode = new Ellipse(
                this.center.x.getPixels(),
                this.center.y.getPixels(),
                semiAxes.x.getPixels(),
                semiAxes.y.getPixels());
        this.orbitNode.setFill(Color.TRANSPARENT);
        this.orbitNode.setStroke(Color.web("#888"));
        this.orbitNode.setStrokeWidth(2);
        // this.orbitNode.setRotate(Math.toDegrees(slant));
        
        parent.anchor.getChildren().add(this.orbitNode);
    }
    
    
    public void update() {
        this.orbitNode.setRadiusX(this.semiAxes.x.getPixels() * SolarSystemScene.ZOOM);
        this.orbitNode.setRadiusY(this.semiAxes.y.getPixels() * SolarSystemScene.ZOOM);
        this.orbitNode.setCenterX(this.center.x.getPixels() * SolarSystemScene.ZOOM);
        this.orbitNode.setCenterY(this.center.y.getPixels() * SolarSystemScene.ZOOM);
    }
    
    public Distance getLargeSemiAxis() {
        return (this.semiAxes.x.getMeters() > this.semiAxes.y.getMeters()) ?
                this.semiAxes.x : this.semiAxes.y;
    }
    
}

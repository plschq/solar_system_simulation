package sss.scenes.solarSystemScene.components;


import sss.App;
import sss.dataclasses.Distance;
import sss.dataclasses.Vector;
import sss.dataclasses.XY;
import sss.scenes.solarSystemScene.SolarSystemScene;

import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.control.Label;


public class Planet {
    
    public AnchorPane anchor;
    public Circle planetNode;
    
    public final double mass;
    public XY position;
    public final Distance radius;
    public Vector velocity;
    
    public Orbit orbit;
    public Label label;
    
    
    public Planet(XY position, double mass, Distance radius, Color color) {
        this.anchor = new AnchorPane();
        this.anchor.setTranslateX(position.x.getPixels());
        this.anchor.setTranslateY(position.y.getPixels());
        this.planetNode = new Circle(0, 0, radius.getPixels() * SolarSystemScene.ZOOM, color);
        
        this.position = position;
        this.mass = mass;
        this.radius = radius;
        this.velocity = new Vector(new Distance(0), 0);
        
        this.anchor.getChildren().addAll(this.planetNode);
    }
    
    
    public void setOrbit(Orbit orbit) {
        this.orbit = orbit;
    }
    
    public void setLabel(String text) {
        if (this.label == null) {
            this.label = new Label(text);
            this.label.setTextFill(Color.web("#ccc"));
            
            this.anchor.getChildren().add(this.label);
        } else {
            this.label.setText(text);
        }
    }
    
    public void update() {
        this.planetNode.setRadius(this.radius.getPixels() * SolarSystemScene.ZOOM);
        this.anchor.setTranslateX(this.position.x.getPixels() * SolarSystemScene.ZOOM);
        this.anchor.setTranslateY(this.position.y.getPixels() * SolarSystemScene.ZOOM);
        
        if (this.orbit != null) {
            
            this.orbit.update();
            
            double maxVelocity = Math.sqrt(
                    SolarSystemScene.G * this.orbit.parent.mass *
                            (2 / this.orbit.perihelion.getMeters() -
                                    1 / this.orbit.getLargeSemiAxis().getMeters()));
            double minVelocity = Math.sqrt(
                    SolarSystemScene.G * this.orbit.parent.mass *
                            (2 / this.orbit.aphelion.getMeters() -
                                    1 / this.orbit.getLargeSemiAxis().getMeters()));
            double averageVelocity = 0.5 * (minVelocity + maxVelocity);
            double currentVelocity = Math.sqrt(
                    SolarSystemScene.G * this.orbit.parent.mass *
                            (2 / App.getDistanceBetween(this.position, this.orbit.parent.position).getMeters() -
                                    1 / this.orbit.getLargeSemiAxis().getMeters()));
            
            this.velocity.magnitude = new Distance(currentVelocity);
            this.velocity.angle = App.simplifyAngle(
                    this.velocity.angle + (
                            360.0 / 60 / 60 / 24 / this.orbit.treatmentPeriod
                    ) / SolarSystemScene.FPS / averageVelocity *
                            SolarSystemScene.SPEED * currentVelocity);
            
            this.position = new XY(
                    this.orbit.center.x.getMeters() + this.orbit.semiAxes.x.getMeters() * Math.sin(Math.toRadians(this.velocity.angle)),
                    this.orbit.center.y.getMeters() - this.orbit.semiAxes.y.getMeters() * Math.cos(Math.toRadians(this.velocity.angle))
            );
            
        }
        
        if (this.label != null) {
            this.label.setTranslateX(-this.label.getWidth() * 0.5);
            this.label.setTranslateY(this.radius.getPixels() * SolarSystemScene.ZOOM);
        }
    }
    
}

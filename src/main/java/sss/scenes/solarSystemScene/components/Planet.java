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

import java.util.ArrayList;


public class Planet {
    
    public final AnchorPane anchor = new AnchorPane();
    public Circle planetNode;
    
    public final double mass;
    public XY position = new XY(0, 0);
    public final Distance radius;
    public final Vector velocity = new Vector(new Distance(0), 0);
    
    public final String name;
    
    public final Orbit orbit;
    public final Label label;
    
    public final double minZoom;
    public boolean isHidden = false;
    
    public final ArrayList<Planet> moons = new ArrayList<>();
    
    
    public Planet(XY position, double mass, Distance radius, Color color, String name, double minZoom) {
        this.planetNode = new Circle(0, 0, radius.getPixels() * SolarSystemScene.ZOOM, color);
        
        this.position = position;
        this.orbit = null;
        this.mass = mass;
        this.radius = radius;
        this.name = name;
        this.minZoom = minZoom;
    
        this.label = new Label(this.name);
        this.label.setTextFill(Color.web("#ccc"));
        
        this.anchor.getChildren().addAll(this.planetNode, this.label);
    }
    
    public Planet(Orbit orbit, double mass, Distance radius, Color color, String name, double minZoom) {
        this.planetNode = new Circle(0, 0, radius.getPixels() * SolarSystemScene.ZOOM, color);
    
        this.orbit = orbit;
        this.mass = mass;
        this.radius = radius;
        this.name = name;
        this.minZoom = minZoom;
        
        this.label = new Label(this.name);
        this.label.setTextFill(Color.web("#ccc"));
        
        this.anchor.getChildren().addAll(this.planetNode, this.label);
    }
    
    
    public void addMoon(Planet planet) {
        this.moons.add(planet);
        this.anchor.getChildren().addAll(planet.anchor);
    }
    
    public void update() {
        
        if (this.orbit != null) {
            
            this.orbit.update(this.isHidden);
            
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
                            Math.abs(2 / App.getDistanceBetween(this.position, this.orbit.parent.position).getMeters() -
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
        
        if ((SolarSystemScene.ZOOM <= this.minZoom && !this.isHidden) ||
                (SolarSystemScene.ZOOM > this.minZoom && this.isHidden)) {
            this.isHidden = !this.isHidden;}
        
        if (this.label != null) {
            this.label.setTranslateX(-this.label.getWidth() * 0.5);
            this.label.setTranslateY(this.radius.getPixels() * SolarSystemScene.ZOOM);
    
            if (this.isHidden) {
                this.anchor.getChildren().remove(this.label);
            } else if (!this.anchor.getChildren().contains(this.label)) {
                this.anchor.getChildren().add(this.label);
            }
        }
    
        this.planetNode.setRadius(this.radius.getPixels() * SolarSystemScene.ZOOM);
        this.anchor.setTranslateX(this.position.x.getPixels() * SolarSystemScene.ZOOM);
        this.anchor.setTranslateY(this.position.y.getPixels() * SolarSystemScene.ZOOM);
        
        this.moons.forEach(Planet::update);
        
    }
    
}

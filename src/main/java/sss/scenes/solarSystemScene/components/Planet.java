package sss.scenes.solarSystemScene.components;


import javafx.scene.input.MouseEvent;
import sss.App;
import sss.dataclasses.Distance;
import sss.dataclasses.Vector;
import sss.dataclasses.XY;
import sss.scenes.solarSystemScene.SolarSystemScene;

import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;


public class Planet {
    
    public final AnchorPane anchor = new AnchorPane();
    public final AnchorPane moonsAnchor = new AnchorPane();
    public ImageView planetImage;
    
    public double mass;
    public XY position = new XY(0, 0);
    public Distance radius;
    public Vector velocity = new Vector(new Distance(0), 0);
    
    public String name;
    
    public Orbit orbit;
    public final Label label = new Label();
    
    public double minZoom;
    public double priority;
    
    public boolean isHovered = false;
    
    public final ArrayList<Planet> moons = new ArrayList<>();
    
    
    public Planet(
            XY position,
            double mass,
            Distance radius,
            String imagePath,
            String name,
            double minZoom,
            double priority
    ) throws FileNotFoundException {
        this.init(
                position,
                null,
                mass,
                radius,
                imagePath,
                name,
                minZoom,
                priority
        );
    }
    
    public Planet(
            Orbit orbit,
            double mass,
            Distance radius,
            String imagePath,
            String name,
            double minZoom,
            double priority
    ) throws FileNotFoundException {
        this.init(
                new XY(0, 0),
                orbit,
                mass,
                radius,
                imagePath,
                name,
                minZoom,
                priority
        );
    }
    
    
    public void init(
            XY position,
            Orbit orbit,
            double mass,
            Distance radius,
            String imagePath,
            String name,
            double minZoom,
            double priority
    ) throws FileNotFoundException {
        this.position = position;
        this.orbit = orbit;
        this.mass = mass;
        this.radius = radius;
        this.name = name;
        this.minZoom = minZoom;
        this.priority = priority;
    
        this.label.setText(this.name);
        this.label.setFont(new Font("Arial", 12));
        this.label.addEventHandler(MouseEvent.MOUSE_ENTERED, e -> {
            this.isHovered = true;
            this.showOrbitBright();
            this.showLabelBright();
        });
        this.label.addEventHandler(MouseEvent.MOUSE_EXITED, e -> {
            this.isHovered = false;
            if (this.priority != 0) {
                this.showOrbitDim();
                this.showLabelDim();
            }
        });
    
        this.planetImage = new ImageView(new Image(new FileInputStream(
                "src\\main\\resources\\sss\\images\\" + imagePath)));
        this.planetImage.setViewOrder(-1);
        
        this.anchor.getChildren().add(this.moonsAnchor);
    }
    
    public void addMoon(Planet planet) {
        this.moons.add(planet);
        this.moonsAnchor.getChildren().add(planet.anchor);
    }
    
    public void showPlanetImage() {
        if (!this.anchor.getChildren().contains(this.planetImage)) {
            this.anchor.getChildren().add(this.planetImage);
        }
    } public void hidePlanetImage() {
        this.anchor.getChildren().remove(this.planetImage);
    }
    
    public void showLabelDim() {
        if (!this.anchor.getChildren().contains(this.label)) {
            this.anchor.getChildren().add(this.label);
        } this.label.setTextFill(Color.web("#ccc4"));
    } public void showLabelBright() {
        if (!this.anchor.getChildren().contains(this.label)) {
            this.anchor.getChildren().add(this.label);
        } this.label.setTextFill(Color.web("#ccc"));
    } public void hideLabel() {
        this.anchor.getChildren().remove(this.label);
    }
    
    public void showOrbitDim() {
        if (this.orbit != null) {
            this.orbit.showOrbitDim();
        }
    } public void showOrbitBright() {
        if (this.orbit != null) {
            this.orbit.showOrbitBright();
        }
    } public void hideOrbit() {
        if (this.orbit != null) {
            this.orbit.hideOrbit();
        }
    }
    
    public void showMoons() {
        if (!this.anchor.getChildren().contains(this.moonsAnchor)) {
            this.anchor.getChildren().add(this.moonsAnchor);
        }
    } public void hideMoons() {
        this.anchor.getChildren().remove(this.moonsAnchor);
    }
    
    public void updatePlanetImage() {
        this.planetImage.setFitWidth(this.radius.getPixels() * 2 * SolarSystemScene.ZOOM);
        this.planetImage.setFitHeight(this.radius.getPixels() * 2 * SolarSystemScene.ZOOM);
        this.planetImage.setTranslateX(-this.planetImage.getFitWidth() * 0.5);
        this.planetImage.setTranslateY(-this.planetImage.getFitHeight() * .5);
    }
    
    public void updatePosition() {
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
                this.velocity.angle + (360.0 / 60 / 60 / 24 / this.orbit.treatmentPeriod)
                        / SolarSystemScene.FPS / averageVelocity * SolarSystemScene.SPEED * currentVelocity);
    
        this.position = new XY(
                this.orbit.center.x.getMeters() + this.orbit.semiAxes.x.getMeters() * Math.sin(Math.toRadians(this.velocity.angle)),
                this.orbit.center.y.getMeters() - this.orbit.semiAxes.y.getMeters() * Math.cos(Math.toRadians(this.velocity.angle))
        );
    
        this.anchor.setTranslateX(this.position.x.getPixels() * SolarSystemScene.ZOOM);
        this.anchor.setTranslateY(this.position.y.getPixels() * SolarSystemScene.ZOOM);
    }
    
    public void updateLabelPosition() {
        this.label.setTranslateX(-this.label.getWidth() * 0.5);
        this.label.setTranslateY(this.radius.getPixels() * SolarSystemScene.ZOOM);
    }
    
    public void update() {
        
        if (this.orbit != null) {
            this.orbit.update();
            this.updatePosition();
        }
    
        this.updateLabelPosition();
        this.updatePlanetImage();
        
        this.moons.forEach(Planet::update);
        
        // Planet image optimization (hiding when too small to display)
        if (this.radius.getPixels() * 2 * SolarSystemScene.ZOOM <= 1) {
            this.hidePlanetImage();
        } else {this.showPlanetImage();}
    
        // Orbit optimization (hiding when too small to display)
        if (this.orbit != null && this.orbit.getLargeSemiAxis().getPixels() * SolarSystemScene.ZOOM <= 3) {
            this.hideOrbit();
        } else {
            if (this.priority == 0 || this.isHovered) {
                this.showOrbitBright();
            } else {this.showOrbitDim();}
        }
        
        // Moons optimization (hiding when too small to display)
        boolean hide = true;
        for (Planet moon : this.moons) {
            if (!moon.orbit.orbitNode.getStroke().equals(Color.TRANSPARENT)) {
                hide = false; break;}
        } if (hide) {
            this.hideMoons();
        } else {this.showMoons();}
        
        // Label optimization
        if (SolarSystemScene.ZOOM < this.minZoom) {
            this.hideLabel();
        } else {
            if (this.priority == 0 || this.radius.getPixels()
                    * 2 * SolarSystemScene.ZOOM > 0.5 || this.isHovered) {
                this.showLabelBright();
            } else {this.showLabelDim();}
        }
        
    }
    
    public static XY getAbsPos(Planet planet) {
        return (planet.orbit != null) ? new XY(
                planet.position.x.getMeters() + Planet.getAbsPos(planet.orbit.parent).x.getMeters(),
                planet.position.y.getMeters() + Planet.getAbsPos(planet.orbit.parent).y.getMeters()
        ) : planet.position;
    }
    
}

package sss.scenes.solarSystemScene.components;


import sss.App;
import sss.Window;
import sss.dataclasses.Distance;
import sss.scenes.solarSystemScene.controllers.FocusController;
import sss.scenes.solarSystemScene.dataclasses.Orbit;
import sss.dataclasses.Vector;
import sss.dataclasses.XY;
import sss.scenes.solarSystemScene.SolarSystemScene;

import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Objects;


public class Planet {
    
    public double mass;
    public XY position = new XY(0, 0);
    public Distance radius;
    public Vector velocity = new Vector(new Distance(0), 0);
    
    public String name;
    public Orbit orbit;

    public double minZoom;
    public double priority;
    
    public final ArrayList<Planet> moons = new ArrayList<>();
    
    public final Arc trailPart1 = new Arc();
    public final Arc trailPart2 = new Arc();
    public final Arc trailPart3 = new Arc();
    public final Arc trailPart4 = new Arc();
    
    public final ImageView planetImage = new ImageView();
    public final Label label = new Label();
    
    public final AnchorPane moonsAnchor = new AnchorPane();
    public final AnchorPane orbitAnchor = new AnchorPane(this.trailPart1,
            this.trailPart2, this.trailPart3, this.trailPart4);
    public final AnchorPane planetAnchor = new AnchorPane();
    public final AnchorPane anchor = new AnchorPane(this.orbitAnchor, this.planetAnchor);
    
    
    public Planet(double mass, Distance radius, String imagePath, String name, double minZoom, double priority)
            throws FileNotFoundException {
        this.init(new XY(0, 0), null, mass, radius, imagePath, name, minZoom, priority);
    }
    
    public Planet(Orbit orbit, double mass, Distance radius, String imagePath, String name, double minZoom, double priority)
            throws FileNotFoundException {
        this.init(new XY(0, 0), orbit, mass, radius, imagePath, name, minZoom, priority);
    }
    
    
    // ------------------------------------------------------------------------------------------
    // --- Init methods -------------------------------------------------------------------------
    // ------------------------------------------------------------------------------------------
    
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
        
        this.velocity.angle = Math.random() * 360;
        
        this.label.setText(this.name);
        this.label.setFont(new Font("Arial", 11));
        
        this.planetImage.setImage(new Image(new FileInputStream(
                "src\\main\\resources\\sss\\planets\\" + imagePath)));
        this.planetImage.setViewOrder(-1);
        
        if (this.orbit != null) {
            this.orbit.parent.addMoon(this);
            
            this.initTrail(this.trailPart1);
            this.initTrail(this.trailPart2);
            this.initTrail(this.trailPart3);
            this.initTrail(this.trailPart4);
        }
    }
    
    public void initTrail(Arc trail) {
        trail.setType(ArcType.ROUND);
        trail.setFill(null);
        trail.setStrokeWidth(2);
        trail.setType(ArcType.OPEN);
        trail.setLength(90);
    }
    
    // ------------------------------------------------------------------------------------------
    // --- Show and hide methods ----------------------------------------------------------------
    // ------------------------------------------------------------------------------------------
    
    public void showPlanetImage() {
        if (!this.planetAnchor.getChildren().contains(this.planetImage)) {
            this.planetAnchor.getChildren().add(this.planetImage);}
    } public void hidePlanetImage() {
        this.planetAnchor.getChildren().remove(this.planetImage);
    }
    
    public void showLabelDim() {
        if (!this.planetAnchor.getChildren().contains(this.label)) {
            this.planetAnchor.getChildren().add(this.label);
        } this.label.setTextFill(Color.web("#ccc8"));
    } public void showLabelBright() {
        if (!this.planetAnchor.getChildren().contains(this.label)) {
            this.planetAnchor.getChildren().add(this.label);
        } this.label.setTextFill(Color.web("#ccc"));
    } public void hideLabel() {
        this.planetAnchor.getChildren().remove(this.label);
    }
    
    public void showMoons() {
        if (!this.planetAnchor.getChildren().contains(this.moonsAnchor)) {
            this.planetAnchor.getChildren().add(this.moonsAnchor);}
    } public void hideMoons() {
        this.planetAnchor.getChildren().remove(this.moonsAnchor);
    }
    
    public void showOrbit() {
        if (!this.anchor.getChildren().contains(this.orbitAnchor)) {
            this.anchor.getChildren().add(this.orbitAnchor);}
    } public void hideOrbit() {
        this.anchor.getChildren().remove(this.orbitAnchor);
    }
    
    // ------------------------------------------------------------------------------------------
    // --- Update methods -----------------------------------------------------------------------
    // ------------------------------------------------------------------------------------------
    
    public void updatePlanetImage() {
        this.planetImage.setFitWidth(this.radius.getPixels() * 2 * SolarSystemScene.ZOOM);
        this.planetImage.setFitHeight(this.radius.getPixels() * 2 * SolarSystemScene.ZOOM);
        this.planetImage.setTranslateX(-this.planetImage.getFitWidth() * 0.5);
        this.planetImage.setTranslateY(-this.planetImage.getFitHeight() * .5);
    
        if (this.radius.getPixels() * 2 * SolarSystemScene.ZOOM <= 1 || this.isPlanetImageOutOfBounds()) {
            this.hidePlanetImage();
        } else {this.showPlanetImage();}
    }
    
    public void updateLabel() {
        if (SolarSystemScene.ZOOM < this.minZoom || this.isLabelOutOfBounds()) {
            this.hideLabel();
        } else {
            if (this.priority == 0 || this.radius.getPixels()
                    * 2 * SolarSystemScene.ZOOM > 0.1) {
                this.showLabelBright();
            } else {this.showLabelDim();}
        }
    }
    
    public void updateMoonsAnchor() {
        boolean hide = true;
        for (Planet moon : this.moons) {
            if (moon.anchor.getChildren().contains(moon.orbitAnchor)) {
                hide = false; break;}
        } if (hide) {
            this.hideMoons();
        } else {this.showMoons();}
    }
    
    public void updateOrbit() {
        if (orbit.getSemimajorAxis().getPixels() * SolarSystemScene.ZOOM < 3 || this.isOrbitOutOfBounds()) {
            this.hideOrbit();
        } else {
            this.updateTrail();
            this.showOrbit();
        }
    }
    
    public void updateVelocity() {
        double maxVelocity = this.getOrbitalVelocityByDistance(this.orbit.pericenter);
        double minVelocity = this.getOrbitalVelocityByDistance(this.orbit.apocenter);
        double averageVelocity = 0.5 * (minVelocity + maxVelocity);
        double currentVelocity = this.getOrbitalVelocityByDistance(
                App.getDistanceBetween(this.position, this.orbit.parent.position));
    
        this.velocity.magnitude = new Distance(currentVelocity);
        this.velocity.angle = App.simplifyAngle(
                this.velocity.angle - (360.0 / 60 / 60 / 24 / this.orbit.getTreatmentPeriod(this.mass))
                        / SolarSystemScene.FPS / averageVelocity * SolarSystemScene.SPEED * currentVelocity);
    }
    
    public void updatePosition() {
        this.updateVelocity();
    
        this.position = this.getPlanetPosByAngle(this.velocity.angle);
    
        this.planetAnchor.setTranslateX(this.position.x.getPixels() * SolarSystemScene.ZOOM);
        this.planetAnchor.setTranslateY(this.position.y.getPixels() * SolarSystemScene.ZOOM);
    }
    
    public void updateLabelPosition() {
        this.label.setTranslateX(-this.label.getWidth() * 0.5);
        this.label.setTranslateY(this.radius.getPixels() * SolarSystemScene.ZOOM);
    }
    
    public void updateTrail(Arc trail, int n) {
        Color[] trailColors = new Color[] {
                Color.web("#888888"),
                Color.web("#686868"),
                Color.web("#555555"),
                Color.web("#383838"),
                Color.web("#222222")};
        
        trail.setCenterX(this.orbit.center.x.getPixels() * SolarSystemScene.ZOOM);
        trail.setCenterY(this.orbit.center.y.getPixels() * SolarSystemScene.ZOOM);
        trail.setRadiusX(this.orbit.semiAxes.x.getPixels() * SolarSystemScene.ZOOM);
        trail.setRadiusY(this.orbit.semiAxes.y.getPixels() * SolarSystemScene.ZOOM);
        trail.setStartAngle(-(this.velocity.angle + 90*(n-1)));
        trail.setStroke(new LinearGradient(
                1 - 0.5 * (1 + Math.cos(Math.toRadians(this.velocity.angle + 90*(n-1)))),
                1 - 0.5 * (1 + Math.sin(Math.toRadians(this.velocity.angle + 90*(n-1)))),
                    0.5 * (1 + Math.cos(Math.toRadians(this.velocity.angle + 90*(n-1)))),
                    0.5 * (1 + Math.sin(Math.toRadians(this.velocity.angle + 90*(n-1)))),
                true, CycleMethod.NO_CYCLE, new Stop(0, trailColors[n-1]), new Stop(1, trailColors[n])));
        
        
    } public void updateTrail() {
        this.updateTrail(this.trailPart1, 1);
        this.updateTrail(this.trailPart2, 2);
        this.updateTrail(this.trailPart3, 3);
        this.updateTrail(this.trailPart4, 4);
    }
    
    public void update() {
        if (this.orbit != null) {
            this.updatePosition();
            this.updateOrbit();
        }
        
        this.updatePlanetImage();
        
        this.updateLabelPosition();
        this.updateLabel();
        
        this.moons.forEach(Planet::update);
        this.updateMoonsAnchor();
    
        this.planetAnchor.toFront();
        this.orbitAnchor.toBack();
        this.label.toFront();
        
        if (Objects.equals(this, FocusController.focusedOn)) {
            FocusController.updateFocus();
        }
    }
    
    // ------------------------------------------------------------------------------------------
    // --- Other methods ------------------------------------------------------------------------
    // ------------------------------------------------------------------------------------------
    
    public void addMoon(Planet planet) {
        this.moons.add(planet);
        this.moonsAnchor.getChildren().add(planet.anchor);
    }
    
    public double getOrbitalVelocityByDistance(Distance distance) {
        return Math.sqrt(Math.abs(
                SolarSystemScene.G * this.orbit.parent.mass *
                        (2 / distance.getMeters() -
                                1 / this.orbit.getSemimajorAxis().getMeters())));
    }
    
    public XY getPlanetPosByAngle(double angle) {
        return new XY(
                this.orbit.center.x.getMeters() + this.orbit.semiAxes.x.getMeters() * Math.sin(Math.toRadians(angle)),
                this.orbit.center.y.getMeters() - this.orbit.semiAxes.y.getMeters() * Math.cos(Math.toRadians(angle))
        );
    }
    
    public XY getAbsPos() {
        return (this.orbit != null) ? new XY(
                this.planetAnchor.getTranslateX() + this.orbit.parent.getAbsPos().x.getMeters(),
                this.planetAnchor.getTranslateY() + this.orbit.parent.getAbsPos().y.getMeters()
        ) : new XY(this.planetAnchor.getTranslateX(), this.planetAnchor.getTranslateY());
    }
    
    public XY getScreenPos() {
        return new XY(
                this.getAbsPos().x.getMeters() + SolarSystemScene.planetsAnchor.getTranslateX(),
                this.getAbsPos().y.getMeters() + SolarSystemScene.planetsAnchor.getTranslateY());
    }
    
    public boolean isPlanetImageOutOfBounds() {
        final XY screenPos = this.getScreenPos();
        final double n = 20;  // pixels out of bounds allowed
        
        return !(0 < screenPos.x.getMeters() + this.planetImage.getTranslateX() + this.planetImage.getFitWidth() + n &&
                screenPos.x.getMeters() + 20 - this.planetImage.getFitWidth() * 0.5 - n < Window.stage.getWidth() &&
                0 < screenPos.y.getMeters() + this.planetImage.getTranslateY() + this.planetImage.getFitHeight() + n &&
                screenPos.y.getMeters() + 40 - this.planetImage.getFitHeight() * 0.5 - n < Window.stage.getHeight());
    }
    
    public boolean isLabelOutOfBounds() {
        final XY screenPos = this.getScreenPos();
        final double n = 20;  // pixels out of bounds allowed
    
        return !(0 < screenPos.x.getMeters() + this.label.getTranslateX() + this.label.getWidth() + n &&
                screenPos.x.getMeters() + 20 - this.label.getWidth() * 0.5 - n < Window.stage.getWidth() &&
                0 < screenPos.y.getMeters() + this.label.getTranslateY() + this.label.getHeight() + n &&
                screenPos.y.getMeters() + 40 - this.label.getHeight() * 0.5 - n < Window.stage.getHeight());
    }
    
    public boolean isOrbitOutOfBounds() {
        // ...
        return false;
    }
    
}

package sss.scenes.solarSystemScene;


import javafx.scene.shape.Circle;
import sss.Window;
import sss.controllers.FullscreenController;
import sss.dataclasses.Distance;
import sss.dataclasses.XY;
import sss.scenes.solarSystemScene.components.Orbit;
import sss.scenes.solarSystemScene.components.Planet;
import sss.scenes.solarSystemScene.controllers.CameraController;
import sss.scenes.solarSystemScene.controllers.TimeController;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.scene.*;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.text.Font;

import java.util.ArrayList;


public final class SolarSystemScene {
    
    public static double FPS = 100;  // frames per second
    public static double SPEED = 1;  // time acceleration
    public static double AU = 1.495978707e11;  // meters in 1 AU
    public static double G = 6.6743e-11;
    public static double SCALE = 250 / SolarSystemScene.AU;  // 1 AU in 250 pixels
    public static double ZOOM = 1;
    
    public static final Group root = new Group();
    public static final Scene scene = new Scene(SolarSystemScene.root, 600, 400, true, SceneAntialiasing.BALANCED);
    
    public static final ArrayList<Planet> planets = new ArrayList<>();
    
    public static final Label timeAccelerationLabel = new Label("Time acceleration: " + SPEED + "x");
    public static final Label zoomLabel = new Label("Zoom: " + ZOOM + "x");
    public static final AnchorPane labels = new AnchorPane(timeAccelerationLabel, zoomLabel);
    
    public static final Circle center = new Circle(0, 0, 1, Color.web("#f00"));
    
    public static Timeline timeline;
    
    
    public static void init() {
        scene.setCursor(Cursor.DEFAULT);
        scene.setFill(Color.web("#050010"));
        scene.setCamera(new PerspectiveCamera(false));
        scene.getCamera().setTranslateX(0);
        scene.getCamera().setTranslateY(0);
        scene.getCamera().setTranslateZ(0);
        scene.getCamera().setNearClip(0);
        scene.getCamera().setFarClip(1e20);
        
        timeAccelerationLabel.setTextFill(Color.web("#fff"));
        timeAccelerationLabel.setBackground(Background.fill(Color.web("#050010")));
        timeAccelerationLabel.setFont(new Font("Arial", 15));
    
        zoomLabel.setTextFill(Color.web("#fff"));
        zoomLabel.setBackground(Background.fill(Color.web("#050010")));
        zoomLabel.setFont(new Font("Arial", 15));
        
        labels.setViewOrder(-1);
        center.setViewOrder(-1);
        
        root.getChildren().addAll(labels, center);
        
        FullscreenController.init(scene);
        CameraController.init();
        TimeController.init();
        
        double planetsRadiusMultiplier = 1;
        
        // Sun
        Planet sun = new Planet(new XY(0, 0), 1.98892e30, new Distance(6.957e8), Color.web("#fdc96e")); addPlanet(sun);
        sun.setLabel("Sun");
        
        // Mercury
        Planet mercury = new Planet(new XY(0, -Distance.byAU(0.30749951).getMeters()), 3.33022e23,
                new Distance(2.4397e6 * planetsRadiusMultiplier), Color.web("#d6d6d6")); addPlanet(mercury);
        mercury.setOrbit(new Orbit(sun, new XY(Distance.byAU(0.38709927).getMeters(),
                Distance.byAU(0.37870).getMeters()), Distance.byAU(0.30749951), Distance.byAU(0.46670079), 87.969));
        mercury.setLabel("Mercury");
        
        // Venus
        Planet venus = new Planet(new XY(0, -Distance.byAU(0.71843270).getMeters()), 4.8675e24,
                new Distance(6.0518e6 * planetsRadiusMultiplier), Color.web("#a6b000")); addPlanet(venus);
        venus.setOrbit(new Orbit(sun, new XY(Distance.byAU(0.723332).getMeters(),
                Distance.byAU(0.72298).getMeters()), Distance.byAU(0.71843270), Distance.byAU(0.72823128), 224.701));
        venus.setLabel("Venus");
        
        // Earth
        Planet earth = new Planet(new XY(0, -Distance.byAU(0.98329134).getMeters()), 5.9742e24,
                new Distance(6.371e6 * planetsRadiusMultiplier), Color.web("#0dc865")); addPlanet(earth);
        earth.setOrbit(new Orbit(sun, new XY(Distance.byAU(0.99986).getMeters(),
                Distance.byAU(1).getMeters()), Distance.byAU(0.98329134), Distance.byAU(1.01671388), 365.25));
        earth.setLabel("Earth");
        
        // Mars
        Planet mars = new Planet(new XY(0, -Distance.byAU(1.381).getMeters()), 6.4171e23,
                new Distance(3.3895e6 * planetsRadiusMultiplier), Color.web("#e53100")); addPlanet(mars);
        mars.setOrbit(new Orbit(sun, new XY(Distance.byAU(1.523662).getMeters(),
                Distance.byAU(1.51740).getMeters()), Distance.byAU(1.381), Distance.byAU(1.666), 686.98));
        mars.setLabel("Mars");
    
        // Jupiter
        Planet jupiter = new Planet(new XY(0, -Distance.byAU(4.950429).getMeters()), 1.8987e27,
                new Distance(6.9911e7 * planetsRadiusMultiplier), Color.web("#d29223")); addPlanet(jupiter);
        jupiter.setOrbit(new Orbit(sun, new XY(Distance.byAU(5.204267).getMeters(),
                Distance.byAU(5.19820).getMeters()), Distance.byAU(4.950429), Distance.byAU(5.458104), 4332.589));
        jupiter.setLabel("Jupiter");
    
        // Saturn
        Planet saturn = new Planet(new XY(0, -Distance.byAU(9.048).getMeters()), 5.6846e26,
                new Distance(5.8232e7 * planetsRadiusMultiplier), Color.web("#d29223")); addPlanet(saturn);
        saturn.setOrbit(new Orbit(sun, new XY(Distance.byAU(9.58260).getMeters(),
                Distance.byAU(9.56730).getMeters()), Distance.byAU(9.048), Distance.byAU(10.116), 10759.22));
        saturn.setLabel("Saturn");
    
        // Uranus
        Planet uranus = new Planet(new XY(0, -Distance.byAU(18.37551863).getMeters()), 8.6813e25,
                new Distance(2.5362e7 * planetsRadiusMultiplier), Color.web("#69b8d8")); addPlanet(uranus);
        uranus.setOrbit(new Orbit(sun, new XY(Distance.byAU(19.22941195).getMeters(),
                Distance.byAU(19.19770).getMeters()), Distance.byAU(18.37551863), Distance.byAU(18.37551863), 30685.4));
        uranus.setLabel("Uranus");
    
        // Neptune
        Planet neptune = new Planet(new XY(0, -Distance.byAU(29.76607095).getMeters()), 1.0243e26,
                new Distance(2.4622e7 * planetsRadiusMultiplier), Color.web("#104ca7")); addPlanet(neptune);
        neptune.setOrbit(new Orbit(sun, new XY(Distance.byAU(30.10366151).getMeters(),
                Distance.byAU(30.10870).getMeters()), Distance.byAU(29.76607095), Distance.byAU(30.44125206), 60190.03));
        neptune.setLabel("Neptune");
        
        timeline = new Timeline(new KeyFrame(Duration.millis(1000 / SolarSystemScene.FPS), e ->
                Platform.runLater(SolarSystemScene::update)));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }
    
    public static void addPlanet(Planet planet) {
        planets.add(planet);
        root.getChildren().add(planet.anchor);
    }
    
    public static void updateLabels() {
        timeAccelerationLabel.setText("Time acceleration: " + TimeController.getSpeedString());
        zoomLabel.setText("Zoom: " + ZOOM + "x");
        
        labels.setTranslateX(-root.getTranslateX() + 10);
        labels.setTranslateY(-root.getTranslateY() + 10);
        
        zoomLabel.setTranslateY(timeAccelerationLabel.getHeight());
    }
    
    public static void updateCenter() {
        center.setCenterX(Window.stage.getWidth() * 0.5 - root.getTranslateX());
        center.setCenterY(Window.stage.getHeight() * .5 - root.getTranslateY());
    }
    
    public static void update() {
        for (Planet planet : planets) {
            planet.update();
        }
    }
    
}

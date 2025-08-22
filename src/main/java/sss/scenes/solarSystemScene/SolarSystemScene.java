package sss.scenes.solarSystemScene;


import sss.Window;
import sss.controllers.FullscreenController;
import sss.dataclasses.Distance;
import sss.dataclasses.XY;
import sss.scenes.solarSystemScene.components.Orbit;
import sss.scenes.solarSystemScene.components.Planet;
import sss.scenes.solarSystemScene.controllers.SolarSystemSceneCameraController;
import sss.scenes.solarSystemScene.controllers.TimeController;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.scene.*;
import javafx.scene.paint.Color;
import javafx.util.Duration;

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
    
    public static Timeline timeline;
    
    
    public static void init() {
        SolarSystemScene.scene.setCursor(Cursor.DEFAULT);
        SolarSystemScene.scene.setFill(Color.web("#050010"));
        SolarSystemScene.scene.setCamera(new PerspectiveCamera(false));
        SolarSystemScene.scene.getCamera().setTranslateX(0);
        SolarSystemScene.scene.getCamera().setTranslateY(0);
        SolarSystemScene.scene.getCamera().setTranslateZ(0);
        
        FullscreenController.init(SolarSystemScene.scene);
        SolarSystemSceneCameraController.init();
        TimeController.init();
        
        // Sun
        Planet sun = new Planet(new XY(0, 0), 1.98892e30, new Distance(6.957e8), Color.web("fdc96e")); addPlanet(sun);
        sun.setLabel("Sun");
        
        // Mercury
        Planet mercury = new Planet(new XY(0, -Distance.byAU(0.30749951).getMeters()), 3.33022e23,
                new Distance(2.4397e6), Color.web("d6d6d6")); addPlanet(mercury);
        mercury.setOrbit(new Orbit(sun, new XY(Distance.byAU(0.38709927).getMeters(),
                Distance.byAU(0.37870).getMeters()), Distance.byAU(0.30749951), Distance.byAU(0.46670079), 87.969));
        mercury.setLabel("Mercury");
    
        // Venus
        Planet venus = new Planet(new XY(0, -Distance.byAU(0.71843270).getMeters()), 4.8675e24,
                new Distance(6.0518e6), Color.web("a6b000")); addPlanet(venus);
        venus.setOrbit(new Orbit(sun, new XY(Distance.byAU(0.723332).getMeters(),
                Distance.byAU(0.72298).getMeters()), Distance.byAU(0.71843270), Distance.byAU(0.72823128), 224.701));
        venus.setLabel("Venus");
    
        // Earth
        Planet earth = new Planet(new XY(0, -Distance.byAU(0.98329134).getMeters()), 5.9742e24,
                new Distance(6.371e6), Color.web("0dc865")); addPlanet(earth);
        earth.setOrbit(new Orbit(sun, new XY(Distance.byAU(0.99986).getMeters(),
                Distance.byAU(1).getMeters()), Distance.byAU(0.98329134), Distance.byAU(1.01671388), 365.25));
        earth.setLabel("Earth");
    
        // Mars
        Planet mars = new Planet(new XY(0, -Distance.byAU(1.381).getMeters()), 6.4171e23,
                new Distance(3.3895e6), Color.web("e53100")); addPlanet(mars);
        mars.setOrbit(new Orbit(sun, new XY(Distance.byAU(1.523662).getMeters(),
                Distance.byAU(1.51740).getMeters()), Distance.byAU(1.381), Distance.byAU(1.666), 686.98));
        mars.setLabel("Mars");
        
        SolarSystemScene.timeline = new Timeline(new KeyFrame(Duration.millis(1000 / SolarSystemScene.FPS), e ->
                Platform.runLater(SolarSystemScene::update)));
        SolarSystemScene.timeline.setCycleCount(Timeline.INDEFINITE);
        SolarSystemScene.timeline.play();
    }
    
    public static void addPlanet(Planet planet) {
        SolarSystemScene.planets.add(planet);
        SolarSystemScene.root.getChildren().add(planet.anchor);
    }
    
    public static void update() {
        SolarSystemScene.root.setTranslateX(Window.stage.getWidth() / 2);
        SolarSystemScene.root.setTranslateY(Window.stage.getHeight() / 2);
        
        for (Planet planet : planets) {
            planet.update();
        }
    }
    
}

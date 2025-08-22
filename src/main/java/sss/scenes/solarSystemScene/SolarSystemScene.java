package sss.scenes.solarSystemScene;


import sss.Planets;
import sss.controllers.FullscreenController;
import sss.scenes.solarSystemScene.components.Planet;
import sss.scenes.solarSystemScene.controllers.CameraController;
import sss.scenes.solarSystemScene.controllers.FocusController;
import sss.scenes.solarSystemScene.controllers.TimeController;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.scene.*;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.text.Font;
import javafx.geometry.Insets;
import javafx.scene.layout.VBox;

import java.io.FileNotFoundException;


public final class SolarSystemScene {
    
    public static double FPS = 100;  // frames per second
    public static double SPEED = 1;  // time acceleration
    public static double AU = 1.495978707e11;  // meters in 1 AU
    public static double G = 6.6743e-11;
    public static double SCALE = 250 / SolarSystemScene.AU;  // 1 AU in 250 pixels
    public static double ZOOM = 1;
    
    public static final Group planetsAnchor = new Group();
    public static final Group root = new Group(planetsAnchor);
    public static final Scene scene = new Scene(SolarSystemScene.root, 600, 400, true, SceneAntialiasing.BALANCED);
    
    public static final Label timeAccelerationLabel = new Label("Time acceleration: " + SPEED + "x");
    public static final Label zoomLabel = new Label("Zoom: " + ZOOM + "x");
    public static final VBox labels = new VBox(timeAccelerationLabel, zoomLabel);
    
    public static Planet rootPlanet;
    
    public static Timeline timeline;
    
    
    public static void init() throws FileNotFoundException {
        scene.setCursor(Cursor.DEFAULT);
        scene.setFill(Color.web("#050010"));
        scene.setCamera(new PerspectiveCamera(false));
        scene.getCamera().setTranslateX(0);
        scene.getCamera().setTranslateY(0);
        scene.getCamera().setTranslateZ(0);
        scene.getCamera().setNearClip(0);
        scene.getCamera().setFarClip(1e20);
    
        initLabels();
        
        FullscreenController.init(scene);
        Planets.init();
        FocusController.init();
        CameraController.init();
        TimeController.init();
        
        startTimeline();
    }
    
    public static void initLabels() {
        timeAccelerationLabel.setTextFill(Color.web("#fff"));
        timeAccelerationLabel.setBackground(Background.fill(Color.web("#fff2")));
        timeAccelerationLabel.setPadding(new Insets(5));
        timeAccelerationLabel.setFont(new Font("Arial", 15));
    
        zoomLabel.setTextFill(Color.web("#fff"));
        zoomLabel.setBackground(Background.fill(Color.web("#fff2")));
        zoomLabel.setPadding(new Insets(5));
        zoomLabel.setFont(new Font("Arial", 15));
    
        labels.setViewOrder(-1);
        labels.setTranslateX(10);
        labels.setTranslateY(10);
        
        root.getChildren().add(labels);
    }
    
    public static void startTimeline() {
        timeline = new Timeline(new KeyFrame(Duration.millis(1000 / SolarSystemScene.FPS), e ->
                Platform.runLater(SolarSystemScene::update)));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }
    
    public static void setRootPlanet(Planet planet) {
        if (rootPlanet != null) {
            planetsAnchor.getChildren().remove(rootPlanet.anchor);}
        rootPlanet = planet;
        planetsAnchor.getChildren().add(planet.anchor);
        FocusController.setFocus(rootPlanet);
    }
    
    public static void updateLabels() {
        timeAccelerationLabel.setText("Time acceleration: " + TimeController.getSpeedString());
        zoomLabel.setText("Zoom: " + ZOOM + "x");
    }
    
    public static void update() {
        rootPlanet.update();
        
        for (Planet planet : Planets.all) {
            if (planet.label.getText().equals("Sun") &&
                    !planet.planetAnchor.getChildren().contains(planet.moonsAnchor)) {
                planet.label.setText("Solar system"); break;
            } else if (rootPlanet.label.getText().equals("Solar system") &&
                    planet.planetAnchor.getChildren().contains(planet.moonsAnchor)) {
                planet.label.setText("Sun"); break;
            }
        }
    }
    
}

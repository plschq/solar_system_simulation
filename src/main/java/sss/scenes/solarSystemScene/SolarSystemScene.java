package sss.scenes.solarSystemScene;


import sss.Planets;
import sss.controllers.FullscreenController;
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


public final class SolarSystemScene {
    
    public static double FPS = 100;  // frames per second
    public static double SPEED = 1;  // time acceleration
    public static double AU = 1.495978707e11;  // meters in 1 AU
    public static double G = 6.6743e-11;
    public static double SCALE = 250 / SolarSystemScene.AU;  // 1 AU in 250 pixels
    public static double ZOOM = 1;
    
    public static final Group root = new Group();
    public static final Scene scene = new Scene(SolarSystemScene.root, 600, 400, true, SceneAntialiasing.BALANCED);
    
    public static final Label timeAccelerationLabel = new Label("Time acceleration: " + SPEED + "x");
    public static final Label zoomLabel = new Label("Zoom: " + ZOOM + "x");
    public static final AnchorPane labels = new AnchorPane(timeAccelerationLabel, zoomLabel);
    
    public static Planet rootPlanet;
    
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
    
        initLabels();
        
        FullscreenController.init(scene);
        CameraController.init();
        TimeController.init();
        Planets.init();
        
        startTimeline();
    }
    
    public static void initLabels() {
        timeAccelerationLabel.setTextFill(Color.web("#fff"));
        timeAccelerationLabel.setBackground(Background.fill(Color.web("#050010")));
        timeAccelerationLabel.setFont(new Font("Arial", 15));
    
        zoomLabel.setTextFill(Color.web("#fff"));
        zoomLabel.setBackground(Background.fill(Color.web("#050010")));
        zoomLabel.setFont(new Font("Arial", 15));
    
        labels.setViewOrder(-1);
        
        root.getChildren().add(labels);
    }
    
    public static void startTimeline() {
        timeline = new Timeline(new KeyFrame(Duration.millis(1000 / SolarSystemScene.FPS), e ->
                Platform.runLater(SolarSystemScene::update)));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }
    
    public static void setRootPlanet(Planet planet) {
        rootPlanet = planet;
        root.getChildren().add(planet.anchor);
    }
    
    public static void updateLabels() {
        timeAccelerationLabel.setText("Time acceleration: " + TimeController.getSpeedString());
        zoomLabel.setText("Zoom: " + ZOOM + "x");
        
        labels.setTranslateX(-root.getTranslateX() + 10);
        labels.setTranslateY(-root.getTranslateY() + 10);
        
        zoomLabel.setTranslateY(timeAccelerationLabel.getHeight());
    }
    
    public static void update() {
        rootPlanet.update();
        
        if (rootPlanet.label.getText().equals("Sun") && ZOOM <= 0.002) {
            rootPlanet.label.setText("Solar system");
        } else if (rootPlanet.label.getText().equals("Solar system") && ZOOM > 0.002) {
            rootPlanet.label.setText("Sun");
        }
    }
    
}

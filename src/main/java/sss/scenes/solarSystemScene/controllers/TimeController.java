package sss.scenes.solarSystemScene.controllers;


import sss.scenes.solarSystemScene.SolarSystemScene;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;


public final class TimeController {
    
    public static final double STEP = 2;
    
    
    public static void init() {
        SolarSystemScene.scene.addEventHandler(KeyEvent.KEY_PRESSED, e -> {
            if (e.getCode().equals(KeyCode.RIGHT)) {
                SolarSystemScene.SPEED *= TimeController.STEP;
            } else if (e.getCode().equals(KeyCode.LEFT)) {
                SolarSystemScene.SPEED /= TimeController.STEP;
            }
        });
    }
    
}

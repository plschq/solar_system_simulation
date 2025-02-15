package sss.scenes.solarSystemScene.controllers;


import sss.scenes.solarSystemScene.SolarSystemScene;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;


public final class TimeController {
    
    public static double VALUE = 1;
    public static long POWER = 0;
    
    
    public static void init() {
        SolarSystemScene.scene.addEventHandler(KeyEvent.KEY_PRESSED, e -> {
            if (e.getCode().equals(KeyCode.RIGHT) || e.getCode().equals(KeyCode.D)) {
                
                if (VALUE == 1) {VALUE = 2.5;}
                else if (VALUE == 2.5) {VALUE = 5;}
                else if (VALUE == 5) {VALUE = 7.5;}
                else if (VALUE == 7.5) {VALUE = 1; POWER += 1;}
                
                SolarSystemScene.SPEED = VALUE * Math.pow(10, POWER);
                        
            } else if (e.getCode().equals(KeyCode.LEFT) || e.getCode().equals(KeyCode.A)) {
                
                if (VALUE == 1) {VALUE = 7.5; POWER -= 1;}
                else if (VALUE == 2.5) {VALUE = 1;}
                else if (VALUE == 5) {VALUE = 2.5;}
                else if (VALUE == 7.5) {VALUE = 5;}
                
                SolarSystemScene.SPEED = VALUE * Math.pow(10, POWER);
                
            } SolarSystemScene.updateLabels();
        });
    }
    
    
    public static String getSpeedString() {
        if (VALUE == 1 || VALUE == 5) {
            int v = (int) VALUE;
            if (Math.abs(POWER) >= 4) {
                return "" + v + "e" + POWER;
            } else if (POWER == 0) {
                return "" + v;
            } else if (POWER > 0) {
                return "" + (long) (v * Math.pow(10, POWER));
            } else {
                return "" + (v * Math.pow(10, POWER));
            }
        } else {
            double v = VALUE;
            if (Math.abs(POWER) >= 4) {
                return "" + v + "e" + POWER;
            } else if (POWER == 0) {
                return "" + v;
            } else if (POWER > 0) {
                return "" + (long) (v * Math.pow(10, POWER));
            } else {
                return "" + (v * Math.pow(10, POWER));
            }
        }
    }
    
}

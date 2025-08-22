package sss.controllers;


import sss.Window;

import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;


public final class FullscreenController {
    
    public static void init(Scene scene) {
        scene.addEventHandler(KeyEvent.KEY_PRESSED, e -> {
            if (e.getCode().equals(KeyCode.F11)) {
                Window.stage.setFullScreen(!Window.stage.fullScreenProperty().get());
            }
        });
    }
    
}

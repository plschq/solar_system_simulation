package sss;


import sss.dataclasses.Distance;
import sss.dataclasses.XY;
import sss.scenes.solarSystemScene.SolarSystemScene;
import sss.scenes.solarSystemScene.components.Orbit;
import sss.scenes.solarSystemScene.components.Planet;

import javafx.scene.paint.Color;

import java.util.ArrayList;


public final class Planets {
    
    public static final ArrayList<Planet> all = new ArrayList<>();
    
    
    public static void init() {
        
        // Sun
        Planet sun = new Planet(
                new XY(0, 0),
                1.98892e30,
                new Distance(6.957e8),
                Color.web("fdc96e"),
                "Sun",
                -1);
        Planets.all.add(sun);
        SolarSystemScene.setRootPlanet(sun);
        
    
        // Mercury
        Planet mercury = new Planet(  // planet
                new Orbit(  // orbit
                        sun,  // orbit center
                        Distance.byAU(0.38709927),  // large semi-axis
                        0.20563593,  // eccentricity
                        87.969),  // rotation period (days)
                3.33022e23,  // mass
                new Distance(2.4397e6),  // radius
                Color.web("#d6d6d6"),  // color
                "Mercury",  // name (label text)
                0.25);  // min zoom
        sun.addMoon(mercury);
        Planets.all.add(mercury);
    
        // Venus
        Planet venus = new Planet(
                new Orbit(
                        sun,
                        Distance.byAU(0.723332),
                        0.0068,
                        224.701),
                4.8675e24,
                new Distance(6.0518e6),
                Color.web("#a6b000"),
                "Venus",
                0.25);
        sun.addMoon(venus);
        Planets.all.add(venus);
    
        // Earth
        Planet earth = new Planet(
                new Orbit(
                        sun,
                        Distance.byAU(1.00000261),
                        0.01671123,
                        365.25),
                5.9742e24,
                new Distance(6.371e6),
                Color.web("#0dc865"),
                "Earth",
                0.1);
        sun.addMoon(earth);
        Planets.all.add(earth);
    
        // Moon
        Planet moon = new Planet(
                new Orbit(
                        earth,
                        Distance.byAU(0.00257),
                        0.0549,
                        27.321661),
                7.3477e22,
                new Distance(1.7371e6),
                Color.web("#fff"),
                "Moon",
                55);
        earth.addMoon(moon);
        Planets.all.add(moon);
    
        // Mars
        Planet mars = new Planet(
                new Orbit(
                        sun,
                        Distance.byAU(1.523662),
                        0.0933941,
                        686.98),
                6.4171e23,
                new Distance(3.3895e6),
                Color.web("#e53100"),
                "Mars",
                0.25);
        sun.addMoon(mars);
        Planets.all.add(mars);
    
        // Phobos
        Planet phobos = new Planet(
                new Orbit(
                        mars,
                        new Distance(9377200),
                        0.0151,
                        0.31875),
                1.072e16,
                new Distance(1.11e4),
                Color.web("#8a7062"),
                "Phobos",
                2000);
        mars.addMoon(phobos);
        Planets.all.add(phobos);
    
        // Deimos
        Planet deimos = new Planet(
                new Orbit(
                        mars,
                        new Distance(23458000),
                        0.0002,
                        1.26244213),
                1.48e15,
                new Distance(6.2e3),
                Color.web("#a98e75"),
                "Deimos",
                750);
        mars.addMoon(deimos);
        Planets.all.add(deimos);
    
        // Jupiter
        Planet jupiter = new Planet(
                new Orbit(
                        sun,
                        Distance.byAU(5.204267),
                        0.048775,
                        4332.589),
                1.8987e27,
                new Distance(6.9911e7),
                Color.web("#d29223"),
                "Jupiter",
                0.015);
        sun.addMoon(jupiter);
        Planets.all.add(jupiter);
    
        // Saturn
        Planet saturn = new Planet(
                new Orbit(
                        sun,
                        Distance.byAU(9.536676),
                        0.055723219,
                        10759.22),
                5.6846e26,
                new Distance(5.8232e7),
                Color.web("#d29223"),
                "Saturn",
                0.01);
        sun.addMoon(saturn);
        Planets.all.add(saturn);
    
        // Uranus
        Planet uranus = new Planet(
                new Orbit(
                        sun,
                        Distance.byAU(19.22941195),
                        0.044405586,
                        30685.4),
                8.6813e25,
                new Distance(2.5362e7),
                Color.web("#69b8d8"),
                "Uranus",
                0.005);
        sun.addMoon(uranus);
        Planets.all.add(uranus);
    
        // Neptune
        Planet neptune = new Planet(
                new Orbit(
                        sun,
                        Distance.byAU(30.10366151),
                        0.011214269,
                        60190.03),
                1.0243e26,
                new Distance(2.4622e7),
                Color.web("#104ca7"),
                "Neptune",
                0.005);
        sun.addMoon(neptune);
        Planets.all.add(neptune);
        
    }
    
}

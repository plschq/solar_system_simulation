package sss;


import sss.dataclasses.Distance;
import sss.dataclasses.XY;
import sss.scenes.solarSystemScene.SolarSystemScene;
import sss.scenes.solarSystemScene.components.Orbit;
import sss.scenes.solarSystemScene.components.Planet;

import java.io.FileNotFoundException;
import java.util.ArrayList;


public final class Planets {
    
    public static final ArrayList<Planet> all = new ArrayList<>();
    
    
    public static void init() throws FileNotFoundException {
        
        // Sun
        Planet sun = new Planet(
                new XY(0, 0),
                1.98892e30,
                new Distance(6.957e8),
                "sun.png",
                "Sun",
                -1,
                0);
    
        SolarSystemScene.setRootPlanet(sun); Planets.all.add(sun);
    
        
        // Mercury
        final Planet mercury = new Planet(  // planet
                new Orbit(  // orbit
                        sun,  // orbit center
                        Distance.byAU(0.38709927),  // large semi-axis
                        0.20563593,  // eccentricity
                        87.969),  // rotation period (days)
                3.33022e23,  // mass
                new Distance(2.4397e6),  // radius
                "mercury.png",  // image path
                "Mercury",  // name (label text)
                0.2,  // min zoom
                0);
    
        // Venus
        final Planet venus = new Planet(
                new Orbit(
                        sun,
                        Distance.byAU(0.723332),
                        0.0068,
                        224.701),
                4.8675e24,
                new Distance(6.0518e6),
                "venus.png",
                "Venus",
                0.15,
                0);
    
        // Earth
        final Planet earth = new Planet(
                new Orbit(
                        sun,
                        Distance.byAU(1.00000261),
                        0.01671123,
                        365.25),
                5.9742e24,
                new Distance(6.371e6),
                "earth.png",
                "Earth",
                0.035,
                0);
    
        // Moon
        final Planet moon = new Planet(
                new Orbit(
                        earth,
                        Distance.byAU(0.00257),
                        0.0549,
                        27.321661),
                7.3477e22,
                new Distance(1.7371e6),
                "moon.png",
                "Moon",
                45,
                0);
    
        // Mars
        final Planet mars = new Planet(
                new Orbit(
                        sun,
                        Distance.byAU(1.523662),
                        0.0933941,
                        686.98),
                6.4171e23,
                new Distance(3.3895e6),
                "mars.png",
                "Mars",
                0.08,
                0);
    
        // Phobos
        final Planet phobos = new Planet(
                new Orbit(
                        mars,
                        new Distance(9377200),
                        0.0151,
                        0.31875),
                1.072e16,
                new Distance(1.11e4),
                "phobos.png",
                "Phobos",
                2500,
                0);
    
        // Deimos
        final Planet deimos = new Planet(
                new Orbit(
                        mars,
                        new Distance(23458000),
                        0.0002,
                        1.26244213),
                1.48e15,
                new Distance(6.2e3),
                "deimos.png",
                "Deimos",
                800,
                0);
    
        // Jupiter
        final Planet jupiter = new Planet(
                new Orbit(
                        sun,
                        Distance.byAU(5.204267),
                        0.048775,
                        4332.589),
                1.8987e27,
                new Distance(6.9911e7),
                "jupiter.png",
                "Jupiter",
                0.012,
                0);
    
        // Saturn
        final Planet saturn = new Planet(
                new Orbit(
                        sun,
                        Distance.byAU(9.536676),
                        0.055723219,
                        10759.22),
                5.6846e26,
                new Distance(5.8232e7),
                "saturn.png",
                "Saturn",
                0.006,
                0);
    
        // Uranus
        final Planet uranus = new Planet(
                new Orbit(
                        sun,
                        Distance.byAU(19.22941195),
                        0.044405586,
                        30685.4),
                8.6813e25,
                new Distance(2.5362e7),
                "uranus.png",
                "Uranus",
                0.0035,
                0);
    
        // Neptune
        final Planet neptune = new Planet(
                new Orbit(
                        sun,
                        Distance.byAU(30.10366151),
                        0.011214269,
                        60190.03),
                1.0243e26,
                new Distance(2.4622e7),
                "neptune.png",
                "Neptune",
                0.002,
                0);
    
        // Pluto
        final Planet pluto = new Planet(
                new Orbit(
                        sun,
                        Distance.byAU(39.482117),
                        0.2488273,
                        90553.02),
                1.303e22,
                new Distance(1.1883e6),
                "pluto.png",
                "Pluto",
                0.0035,
                -1);
    
        // 1 Ceres
        final Planet ceres = new Planet(
                new Orbit(
                        sun,
                        Distance.byAU(2.7653),
                        0.07934,
                        1680.5),
                9.393e20,
                new Distance(4.635e5),
                "ceres.png",
                "1 Ceres",
                0.026,
                -1);
    
        // Eris
        final Planet eris = new Planet(
                new Orbit(
                        sun,
                        Distance.byAU(67.781),
                        0.44068,
                        203830),
                1.67e22,
                new Distance(1.163e6),
                "eris.png",
                "Eris",
                0.0035,
                -1);
        
        // Makemake
        final Planet makemake = new Planet(
                new Orbit(
                        sun,
                        Distance.byAU(45.436301),
                        0.16254481,
                        111867),
                3e21,
                new Distance(7.39e5),
                "makemake.png",
                "Makemake",
                0.0035,
                -1);
    
        // Haumea
        final Planet haumea = new Planet(
                new Orbit(
                        sun,
                        Distance.byAU(43.28708),
                        0.1920504,
                        104025),
                4.006e21,
                new Distance(8.16e5),
                "haumea.png",
                "Haumea",
                0.0035,
                -1);
        
    
        Planets.all.add(mercury); sun.addMoon(mercury);
        Planets.all.add(venus); sun.addMoon(venus);
        Planets.all.add(earth); sun.addMoon(earth);
        Planets.all.add(moon); earth.addMoon(moon);
        Planets.all.add(mars); sun.addMoon(mars);
        Planets.all.add(phobos); mars.addMoon(phobos);
        Planets.all.add(deimos); mars.addMoon(deimos);
        Planets.all.add(jupiter); sun.addMoon(jupiter);
        Planets.all.add(saturn); sun.addMoon(saturn);
        Planets.all.add(uranus); sun.addMoon(uranus);
        Planets.all.add(neptune); sun.addMoon(neptune);
        Planets.all.add(pluto); sun.addMoon(pluto);
        Planets.all.add(ceres); sun.addMoon(ceres);
        Planets.all.add(eris); sun.addMoon(eris);
        Planets.all.add(makemake); sun.addMoon(makemake);
        Planets.all.add(haumea); sun.addMoon(haumea);
        
    }
    
}

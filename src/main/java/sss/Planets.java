package sss;


import sss.dataclasses.Distance;
import sss.scenes.solarSystemScene.SolarSystemScene;
import sss.scenes.solarSystemScene.controllers.FocusController;
import sss.scenes.solarSystemScene.dataclasses.Orbit;
import sss.scenes.solarSystemScene.components.Planet;

import java.io.FileNotFoundException;
import java.util.ArrayList;


public final class Planets {
    
    public static final ArrayList<Planet> all = new ArrayList<>();
    
    
    public static void init() throws FileNotFoundException {
        
        // Sun
        Planet sun = new Planet(1.98892e30, new Distance(6.957e8), "sun.png", "Sun", -1, 0);
    
        SolarSystemScene.setRootPlanet(sun); Planets.all.add(sun);
    
        
        final Planet mercury = new Planet(  // planet
                new Orbit(  // orbit
                        sun,  // orbit center
                        Distance.byAU(0.38709927),  // large semi-axis
                        0.20563593),  // eccentricity
                3.33022e23,  // mass
                new Distance(2.4397e6),  // radius
                "mercury.png",  // image path
                "Mercury",  // name (label text)
                0.2,  // min zoom
                0);  // priority
        Planets.all.add(mercury);  // adding planet
        
        final Planet venus = new Planet(new Orbit(sun, Distance.byAU(0.723332), 0.0068), 4.8675e24, new Distance(6.0518e6), "venus.png", "Venus", 0.15, 0); Planets.all.add(venus);
        
        final Planet earth = new Planet(new Orbit(sun, Distance.byAU(1.00000261), 0.01671123), 5.9742e24, new Distance(6.371e6), "earth.png", "Earth", 0.035, 0); Planets.all.add(earth);
        final Planet moon = new Planet(new Orbit(earth, Distance.byAU(0.00257), 0.0549), 7.3477e22, new Distance(1.7371e6), "moon.png", "Moon", 45, 0); Planets.all.add(moon);
        
        final Planet mars = new Planet(new Orbit(sun, Distance.byAU(1.523662), 0.0933941), 6.4171e23, new Distance(3.3895e6), "mars.png", "Mars", 0.08, 0); Planets.all.add(mars);
        final Planet phobos = new Planet(new Orbit(mars, new Distance(9377200), 0.0151), 1.072e16, new Distance(1.11e4), "phobos.png", "Phobos", 2500, 0); Planets.all.add(phobos);
        final Planet deimos = new Planet(new Orbit(mars, new Distance(23458000), 0.0002), 1.48e15, new Distance(6.2e3), "deimos.png", "Deimos", 800, 0); Planets.all.add(deimos);
        
        final Planet jupiter = new Planet(new Orbit(sun, Distance.byAU(5.204267), 0.048775), 1.8987e27, new Distance(6.9911e7), "jupiter.png", "Jupiter", 0.012, 0); Planets.all.add(jupiter);
        final Planet io = new Planet(new Orbit(jupiter, new Distance(4.218e8), 0.0041), 8.9319e22, new Distance(1.8213e6), "io.png", "Io", 60, 0); Planets.all.add(io);
        final Planet europa = new Planet(new Orbit(jupiter, new Distance(6.711e8), 0.0094), 4.8017e22, new Distance(1.5608e6), "europa.png", "Europa", 40, 0); Planets.all.add(europa);
        final Planet ganymede = new Planet(new Orbit(jupiter, new Distance(1.0704e9), 0.0013), 4.8017e22, new Distance(2.6341e6), "ganymede.png", "Ganymede", 15, 0); Planets.all.add(ganymede);
        final Planet callisto = new Planet(new Orbit(jupiter, new Distance(1.8827e9), 0.0074), 1.075e23, new Distance(2.4103e6), "callisto.png", "Callisto", 8, 0); Planets.all.add(callisto);
        
        final Planet saturn = new Planet(new Orbit(sun, Distance.byAU(9.536676), 0.055723219), 5.6846e26, new Distance(5.8232e7), "saturn.png", "Saturn", 0.006, 0); Planets.all.add(saturn);
        final Planet mimas = new Planet(new Orbit(saturn, new Distance(1.85539e8), 0.0196), 3.7493e19, new Distance(1.982e5), "mimas.png", "Mimas", 185, 0); Planets.all.add(mimas);
        final Planet enceladus = new Planet(new Orbit(saturn, new Distance(2.37948e8), 0.0047), 1.08022e20, new Distance(2.521e5), "enceladus.png", "Enceladus", 120, 0); Planets.all.add(enceladus);
        final Planet tethys = new Planet(new Orbit(saturn, new Distance(2.94672e8), 0.0001), 6.17449e20, new Distance(5.311e5), "tethys.png", "Tethys", 85, 0); Planets.all.add(tethys);
        final Planet dione = new Planet(new Orbit(saturn, new Distance(3.774e8), 0.0022), 1.095452e21, new Distance(5.617e5), "dione.png", "Dione", 50, 0); Planets.all.add(dione);
        final Planet rhea = new Planet(new Orbit(saturn, new Distance(5.271e8), 0.0013), 2.306518e21, new Distance(7.635e5), "rhea.png", "Rhea", 20, 0); Planets.all.add(rhea);
        final Planet titan = new Planet(new Orbit(saturn, new Distance(1.22187e9), 0.0288), 1.3452e23, new Distance(2.576e6), "titan.png", "Titan", 10, 0); Planets.all.add(titan);
        final Planet iapetus = new Planet(new Orbit(saturn, new Distance(3.56082e9), 0.0286125), 1.805635e21, new Distance(7.343e5), "iapetus.png", "Iapetus", 4, 0); Planets.all.add(iapetus);
        
        final Planet uranus = new Planet(new Orbit(sun, Distance.byAU(19.22941195), 0.044405586), 8.6813e25, new Distance(2.5362e7), "uranus.png", "Uranus", 0.0035, 0); Planets.all.add(uranus);
        final Planet puck = new Planet(new Orbit(uranus, new Distance(86004444), 0.00012), 2.9e18, new Distance(8.1e4), "unset.png", "Puck", 120, 0); Planets.all.add(puck);
        final Planet miranda = new Planet(new Orbit(uranus, new Distance(1.299e8), 0.0013), 6.59e19, new Distance(2.358e5), "unset.png", "Miranda", 100, 0); Planets.all.add(miranda);
        final Planet ariel = new Planet(new Orbit(uranus, new Distance(1.9102e8), 0.0012), 1.353e21, new Distance(5.789e5), "unset.png", "Ariel", 75, 0); Planets.all.add(ariel);
        final Planet umbriel = new Planet(new Orbit(uranus, new Distance(2.66e8), 0.0039), 1.172e21, new Distance(5.847e5), "unset.png", "Umbriel", 40, 0); Planets.all.add(umbriel);
        final Planet titania = new Planet(new Orbit(uranus, new Distance(4.363e8), 0.0011), 3.527e21, new Distance(7.884e5), "unset.png", "Titania", 30, 0); Planets.all.add(titania);
        final Planet oberon = new Planet(new Orbit(uranus, new Distance(5.8352e8), 0.0014), 3.014e21, new Distance(7.614e5), "unset.png", "Oberon", 20, 0); Planets.all.add(oberon);
        
        final Planet neptune = new Planet(new Orbit(sun, Distance.byAU(30.10366151), 0.011214269), 1.0243e26, new Distance(2.4622e7), "neptune.png", "Neptune", 0.002, 0); Planets.all.add(neptune);
        final Planet triton = new Planet(new Orbit(neptune, new Distance(3.54759e8), 0.000016), 2.14e22, new Distance(1.3534e6), "unset.png", "Triton", 30, 0); Planets.all.add(triton);
        final Planet proteus = new Planet(new Orbit(neptune, new Distance(1.17647e8), 0.0004), 5e19, new Distance(2.1e5), "unset.png", "Proteus", 110, 0); Planets.all.add(proteus);
        final Planet nereid = new Planet(new Orbit(neptune, new Distance(5.5134e9), 0.7512), 3.1e19, new Distance(1.7e5), "unset.png", "Nereid", 5, 0); Planets.all.add(nereid);
        final Planet larissa = new Planet(new Orbit(neptune, new Distance(7.3548e7), 0.001393), 4.9e18, new Distance(9.7e4), "unset.png", "Larissa", 170, 0); Planets.all.add(larissa);
        final Planet despina = new Planet(new Orbit(neptune, new Distance(5.2526e7), 0.000224), 2.3e18, new Distance(7.5e4), "unset.png", "Despina", 700, 0); Planets.all.add(despina);
        final Planet galatea = new Planet(new Orbit(neptune, new Distance(6.1953e7), 0.000037), 2.12e18, new Distance(8.8e4), "unset.png", "Galatea", 550, 0); Planets.all.add(galatea);
        
        final Planet pluto = new Planet(new Orbit(sun, Distance.byAU(39.482117), 0.2488273), 1.303e22, new Distance(1.1883e6), "pluto.png", "Pluto", 0.0035, -1); Planets.all.add(pluto);
        final Planet charon = new Planet(new Orbit(pluto, new Distance(1.95914e7), 0.0002), 1.586e21, new Distance(6.06e5), "unset.png", "Charon", 300, 0); Planets.all.add(charon);
        final Planet styx = new Planet(new Orbit(pluto, new Distance(4.2656e7), 0.005787), 7.5e15, new Distance(1.1e4), "unset.png", "Styx", 850, 0); Planets.all.add(styx);
        final Planet nix = new Planet(new Orbit(pluto, new Distance(4.8694e7), 0.002036), 4.5e16, new Distance(3.8e4), "unset.png", "Nix", 300, 0); Planets.all.add(nix);
        final Planet kerberos = new Planet(new Orbit(pluto, new Distance(5.7783e7), 0.00328), 1.65e16, new Distance(1.27e4), "unset.png", "Kerberos", 650, 0); Planets.all.add(kerberos);
        final Planet hydra = new Planet(new Orbit(pluto, new Distance(6.4738e7), 0.005862), 4.8e16, new Distance(3.93e4), "unset.png", "Hydra", 185, 0); Planets.all.add(hydra);
        
        final Planet ceres = new Planet(new Orbit(sun, Distance.byAU(2.7653), 0.07934), 9.393e20, new Distance(4.635e5), "ceres.png", "Ceres", 0.026, -1); Planets.all.add(ceres);
        
        final Planet eris = new Planet(new Orbit(sun, Distance.byAU(67.781), 0.44068), 1.67e22, new Distance(1.163e6), "eris.png", "Eris", 0.0035, -1); Planets.all.add(eris);
        final Planet dysnomia = new Planet(new Orbit(eris, new Distance(3.7273e7), 0.0062), 4e20, new Distance(3.16e5), "unset.png", "Dysnomia", 650, 0); Planets.all.add(dysnomia);
        
        final Planet makemake = new Planet(new Orbit(sun, Distance.byAU(45.436301), 0.16254481), 3e21, new Distance(7.39e5), "makemake.png", "Makemake", 0.0035, -1); Planets.all.add(makemake);
        final Planet S2015_136472_1 = new Planet(new Orbit(makemake, new Distance(2.11e7), 0), 1e15, new Distance(8.75e4), "unset.png", "S/2015 (136472) 1", 2200, 0); Planets.all.add(S2015_136472_1);
        
        final Planet haumea = new Planet(new Orbit(sun, Distance.byAU(43.28708), 0.1920504), 4.006e21, new Distance(8.16e5), "haumea.png", "Haumea", 0.0035, -1); Planets.all.add(haumea);
        final Planet hi_iaka = new Planet(new Orbit(haumea, new Distance(4.988e7), 0.0513), 1.79e19, new Distance(1.6e5), "unset.png", "Hi'iaka", 240, 0); Planets.all.add(hi_iaka);
        final Planet namaka = new Planet(new Orbit(haumea, new Distance(2.5657e7), 0.249), 1.79e18, new Distance(8.5e4), "unset.png", "Namaka", 775, 0); Planets.all.add(namaka);
    }
    
}

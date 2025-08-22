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
    
        // Io
        final Planet io = new Planet(
                new Orbit(
                        jupiter,
                        new Distance(4.218e8),
                        0.0041,
                        1.769137786),
                8.9319e22,
                new Distance(1.8213e6),
                "io.png",
                "Io",
                60,
                0);
    
        // Europa
        final Planet europa = new Planet(
                new Orbit(
                        jupiter,
                        new Distance(6.711e8),
                        0.0094,
                        3.551),
                4.8017e22,
                new Distance(1.5608e6),
                "europa.png",
                "Europa",
                40,
                0);
    
        // Ganymede
        final Planet ganymede = new Planet(
                new Orbit(
                        jupiter,
                        new Distance(1.0704e9),
                        0.0013,
                        7.15455296),
                4.8017e22,
                new Distance(2.6341e6),
                "ganymede.png",
                "Ganymede",
                15,
                0);
    
        // Callisto
        final Planet callisto = new Planet(
                new Orbit(
                        jupiter,
                        new Distance(1.8827e9),
                        0.0074,
                        16.6890184),
                1.075e23,
                new Distance(2.4103e6),
                "callisto.png",
                "Callisto",
                8,
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
    
        // Mimas
        final Planet mimas = new Planet(
                new Orbit(
                        saturn,
                        new Distance(1.85539e8),
                        0.0196,
                        0.942),
                3.7493e19,
                new Distance(1.982e5),
                "mimas.png",
                "Mimas",
                185,
                0);
    
        // Enceladus
        final Planet enceladus = new Planet(
                new Orbit(
                        saturn,
                        new Distance(2.37948e8),
                        0.0047,
                        1.370218),
                1.08022e20,
                new Distance(2.521e5),
                "enceladus.png",
                "Enceladus",
                120,
                0);
    
        // Tethys
        final Planet tethys = new Planet(
                new Orbit(
                        saturn,
                        new Distance(2.94672e8),
                        0.0001,
                        1.887802),
                6.17449e20,
                new Distance(5.311e5),
                "tethys.png",
                "Tethys",
                85,
                0);
    
        // Dione
        final Planet dione = new Planet(
                new Orbit(
                        saturn,
                        new Distance(3.774e8),
                        0.0022,
                        2.77),
                1.095452e21,
                new Distance(5.617e5),
                "dione.png",
                "Dione",
                50,
                0);
    
        // Rhea
        final Planet rhea = new Planet(
                new Orbit(
                        saturn,
                        new Distance(5.271e8),
                        0.0013,
                        4.518),
                2.306518e21,
                new Distance(7.635e5),
                "rhea.png",
                "Rhea",
                20,
                0);
    
        // Titan
        final Planet titan = new Planet(
                new Orbit(
                        saturn,
                        new Distance(1.22187e9),
                        0.0288,
                        15.945),
                1.3452e23,
                new Distance(2.576e6),
                "titan.png",
                "Titan",
                10,
                0);
    
        // Iapetus
        final Planet iapetus = new Planet(
                new Orbit(
                        saturn,
                        new Distance(3.56082e9),
                        0.0286125,
                        79.3215),
                1.805635e21,
                new Distance(7.343e5),
                "iapetus.png",
                "Iapetus",
                4,
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
    
        // Puck
        final Planet puck = new Planet(
                new Orbit(
                        uranus,
                        new Distance(86004444),
                        0.00012,
                        0.76183287),
                2.9e18,
                new Distance(8.1e4),
                "unset.png",
                "Puck",
                120,
                0);
    
        // Miranda
        final Planet miranda = new Planet(
                new Orbit(
                        uranus,
                        new Distance(1.299e8),
                        0.0013,
                        1.413),
                6.59e19,
                new Distance(2.358e5),
                "unset.png",
                "Miranda",
                100,
                0);
    
        // Ariel
        final Planet ariel = new Planet(
                new Orbit(
                        uranus,
                        new Distance(1.9102e8),
                        0.0012,
                        2.52),
                1.353e21,
                new Distance(5.789e5),
                "unset.png",
                "Ariel",
                75,
                0);
    
        // Umbriel
        final Planet umbriel = new Planet(
                new Orbit(
                        uranus,
                        new Distance(2.66e8),
                        0.0039,
                        4.144),
                1.172e21,
                new Distance(5.847e5),
                "unset.png",
                "Umbriel",
                40,
                0);
    
        // Titania
        final Planet titania = new Planet(
                new Orbit(
                        uranus,
                        new Distance(4.363e8),
                        0.0011,
                        8.706),
                3.527e21,
                new Distance(7.884e5),
                "unset.png",
                "Titania",
                30,
                0);
    
        // Oberon
        final Planet oberon = new Planet(
                new Orbit(
                        uranus,
                        new Distance(5.8352e8),
                        0.0014,
                        13.463),
                3.014e21,
                new Distance(7.614e5),
                "unset.png",
                "Oberon",
                20,
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
    
        // Triton
        final Planet triton = new Planet(
                new Orbit(
                        neptune,
                        new Distance(3.54759e8),
                        0.000016,
                        -5.88),
                2.14e22,
                new Distance(1.3534e6),
                "unset.png",
                "Triton",
                30,
                0);
    
        // Proteus
        final Planet proteus = new Planet(
                new Orbit(
                        neptune,
                        new Distance(1.17647e8),
                        0.0004,
                        1.1223),
                5e19,
                new Distance(2.1e5),
                "unset.png",
                "Proteus",
                110,
                0);
    
        // Nereid
        final Planet nereid = new Planet(
                new Orbit(
                        neptune,
                        new Distance(5.5134e9),
                        0.7512,
                        360.1362),
                3.1e19,
                new Distance(1.7e5),
                "unset.png",
                "Nereid",
                5,
                0);
    
        // Larissa
        final Planet larissa = new Planet(
                new Orbit(
                        neptune,
                        new Distance(7.3548e7),
                        0.001393,
                        0.554653),
                4.9e18,
                new Distance(9.7e4),
                "unset.png",
                "Larissa",
                170,
                0);
    
        // Despina
        final Planet despina = new Planet(
                new Orbit(
                        neptune,
                        new Distance(5.2526e7),
                        0.000224,
                        0.334655),
                2.3e18,
                new Distance(7.5e4),
                "unset.png",
                "Despina",
                700,
                0);
    
        // Galatea
        final Planet galatea = new Planet(
                new Orbit(
                        neptune,
                        new Distance(6.1953e7),
                        0.000037,
                        0.428744),
                2.12e18,
                new Distance(8.8e4),
                "unset.png",
                "Galatea",
                550,
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
    
        // Charon
        final Planet charon = new Planet(
                new Orbit(
                        pluto,
                        new Distance(1.95914e7),
                        0.0002,
                        6.3872304),
                1.586e21,
                new Distance(6.06e5),
                "unset.png",
                "Charon",
                300,
                0);
    
        // Styx
        final Planet styx = new Planet(
                new Orbit(
                        pluto,
                        new Distance(4.2656e7),
                        0.005787,
                        20.16155),
                7.5e15,
                new Distance(1.1e4),
                "unset.png",
                "Styx",
                850,
                0);
    
        // Nix
        final Planet nix = new Planet(
                new Orbit(
                        pluto,
                        new Distance(4.8694e7),
                        0.002036,
                        24.85463),
                4.5e16,
                new Distance(3.8e4),
                "unset.png",
                "Nix",
                300,
                0);
    
        // Kerberos
        final Planet kerberos = new Planet(
                new Orbit(
                        pluto,
                        new Distance(5.7783e7),
                        0.00328,
                        32.16756),
                1.65e16,
                new Distance(1.27e4),
                "unset.png",
                "Kerberos",
                650,
                0);
    
        // Hydra
        final Planet hydra = new Planet(
                new Orbit(
                        pluto,
                        new Distance(6.4738e7),
                        0.005862,
                        38.20177),
                4.8e16,
                new Distance(3.93e4),
                "unset.png",
                "Hydra",
                185,
                0);
    
        // Ceres
        final Planet ceres = new Planet(
                new Orbit(
                        sun,
                        Distance.byAU(2.7653),
                        0.07934,
                        1680.5),
                9.393e20,
                new Distance(4.635e5),
                "ceres.png",
                "Ceres",
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
    
        // Dysnomia
        final Planet dysnomia = new Planet(
                new Orbit(
                        eris,
                        new Distance(3.7273e7),
                        0.0062,
                        15.785899),
                4e20,
                new Distance(3.16e5),
                "unset.png",
                "Dysnomia",
                650,
                0);
        
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
        
        // S/2015 (136472) 1
        final Planet S2015_136472_1 = new Planet(
                new Orbit(
                        makemake,
                        new Distance(2.11e7),
                        0,  // unknown
                        12.4),
                1e15,  // unknown
                new Distance(8.75e4),
                "unset.png",
                "S/2015 (136472) 1",
                2200,
                0);
    
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
    
        // Hi'iaka
        final Planet hi_iaka = new Planet(
                new Orbit(
                        haumea,
                        new Distance(4.988e7),
                        0.0513,
                        49.12),
                1.79e19,
                new Distance(1.6e5),
                "unset.png",
                "Hi'iaka",
                240,
                0);
    
        // Namaka
        final Planet namaka = new Planet(
                new Orbit(
                        haumea,
                        new Distance(2.5657e7),
                        0.249,
                        18.2783),
                1.79e18,
                new Distance(8.5e4),
                "unset.png",
                "Namaka",
                775,
                0);
        
    
        Planets.all.add(mercury); sun.addMoon(mercury);
        
        Planets.all.add(venus); sun.addMoon(venus);
        
        Planets.all.add(earth); sun.addMoon(earth);
        Planets.all.add(moon); earth.addMoon(moon);
        
        Planets.all.add(mars); sun.addMoon(mars);
        Planets.all.add(phobos); mars.addMoon(phobos);
        Planets.all.add(deimos); mars.addMoon(deimos);
        
        Planets.all.add(jupiter); sun.addMoon(jupiter);
        Planets.all.add(io); jupiter.addMoon(io);
        Planets.all.add(europa); jupiter.addMoon(europa);
        Planets.all.add(ganymede); jupiter.addMoon(ganymede);
        Planets.all.add(callisto); jupiter.addMoon(callisto);
        
        Planets.all.add(saturn); sun.addMoon(saturn);
        Planets.all.add(mimas); saturn.addMoon(mimas);
        Planets.all.add(enceladus); saturn.addMoon(enceladus);
        Planets.all.add(tethys); saturn.addMoon(tethys);
        Planets.all.add(dione); saturn.addMoon(dione);
        Planets.all.add(rhea); saturn.addMoon(rhea);
        Planets.all.add(titan); saturn.addMoon(titan);
        Planets.all.add(iapetus); saturn.addMoon(iapetus);
        
        Planets.all.add(uranus); sun.addMoon(uranus);
        Planets.all.add(puck); uranus.addMoon(puck);
        Planets.all.add(miranda); uranus.addMoon(miranda);
        Planets.all.add(ariel); uranus.addMoon(ariel);
        Planets.all.add(umbriel); uranus.addMoon(umbriel);
        Planets.all.add(titania); uranus.addMoon(titania);
        Planets.all.add(oberon); uranus.addMoon(oberon);
        
        Planets.all.add(neptune); sun.addMoon(neptune);
        Planets.all.add(triton); neptune.addMoon(triton);
        Planets.all.add(proteus); neptune.addMoon(proteus);
        Planets.all.add(nereid); neptune.addMoon(nereid);
        Planets.all.add(larissa); neptune.addMoon(larissa);
        Planets.all.add(despina); neptune.addMoon(despina);
        Planets.all.add(galatea); neptune.addMoon(galatea);
        
        Planets.all.add(pluto); sun.addMoon(pluto);
        Planets.all.add(charon); pluto.addMoon(charon);
        Planets.all.add(styx); pluto.addMoon(styx);
        Planets.all.add(nix); pluto.addMoon(nix);
        Planets.all.add(kerberos); pluto.addMoon(kerberos);
        Planets.all.add(hydra); pluto.addMoon(hydra);
        
        Planets.all.add(ceres); sun.addMoon(ceres);
        
        Planets.all.add(eris); sun.addMoon(eris);
        Planets.all.add(dysnomia); eris.addMoon(dysnomia);
        
        Planets.all.add(makemake); sun.addMoon(makemake);
        Planets.all.add(S2015_136472_1); makemake.addMoon(S2015_136472_1);
        
        Planets.all.add(haumea); sun.addMoon(haumea);
        Planets.all.add(hi_iaka); haumea.addMoon(hi_iaka);
        Planets.all.add(namaka); haumea.addMoon(namaka);
        
    }
    
}

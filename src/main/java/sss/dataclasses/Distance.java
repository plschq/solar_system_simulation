package sss.dataclasses;


import sss.scenes.solarSystemScene.SolarSystemScene;


public class Distance {
    
    private final double distanceMeters;
    private final double distanceAU;
    private final double distancePixels;
    
    
    public Distance(double distanceMeters) {
        this.distanceMeters = distanceMeters;
        this.distanceAU = this.distanceMeters / SolarSystemScene.AU;
        this.distancePixels = this.distanceMeters * SolarSystemScene.SCALE ;
    }
    
    
    public double getMeters() {return this.distanceMeters;}
    public double getAU() {return this.distanceAU;}
    public double getPixels() {return this.distancePixels;}
    
    public static Distance byAU(double distanceAU) {
        return new Distance(distanceAU * SolarSystemScene.AU);
    }
    public static Distance byPixels(double distancePixels) {
        return new Distance((distancePixels / SolarSystemScene.SCALE) * SolarSystemScene.AU);
    }
    
}

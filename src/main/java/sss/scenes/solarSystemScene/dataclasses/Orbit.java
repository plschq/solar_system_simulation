package sss.scenes.solarSystemScene.dataclasses;


import sss.dataclasses.Distance;
import sss.dataclasses.XY;
import sss.scenes.solarSystemScene.SolarSystemScene;
import sss.scenes.solarSystemScene.components.Planet;


public class Orbit {
    
    public final Planet parent;
    
    public final XY center;
    public final XY semiAxes;
    
    public final Distance apocenter;
    public final Distance pericenter;
    
    public final double eccentricity;
    public final double treatmentPeriod;
    
    
    public Orbit(Planet parent, Distance semimajorAxis, double eccentricity) {
        this.parent = parent;
        
        this.semiAxes = new XY(semimajorAxis.getMeters() *
                Math.sqrt(1 - Math.pow(eccentricity, 2)), semimajorAxis.getMeters());
        
        this.apocenter = new Distance(getSemimajorAxis().getMeters() * (1 + eccentricity));
        this.pericenter = new Distance(getSemimajorAxis().getMeters() * (1 - eccentricity));
    
        this.center = new XY(0, 0.5 * (this.apocenter.getMeters() - this.pericenter.getMeters()));
    
        this.eccentricity = eccentricity;
        this.treatmentPeriod = 2 * Math.PI * Math.sqrt(
                Math.pow(this.getSemimajorAxis().getMeters(), 3) /
                        (SolarSystemScene.G * this.parent.mass)) / 24 / 60 / 60;
    }
    
    
    public double getTreatmentPeriod(double childMass) {
        return 2 * Math.PI * Math.sqrt(
                Math.pow(this.getSemimajorAxis().getMeters(), 3) /
                        (SolarSystemScene.G * (this.parent.mass + childMass))) / 24 / 60 / 60;
    }
    
    public Distance getSemimajorAxis() {
        // return new Distance(Math.max(this.semiAxes.x.getMeters(), this.semiAxes.y.getMeters()));
        return this.semiAxes.y;
    }
    
}

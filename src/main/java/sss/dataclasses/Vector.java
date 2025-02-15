package sss.dataclasses;


public class Vector {
    
    public Distance magnitude;
    public double angle;
    
    
    public Vector(Distance magnitude, double angle) {
        this.magnitude = magnitude;
        this.angle = angle;
    }
    
    
    // public Distance getX() {return new Distance(Math.sin(this.angle) * this.magnitude.getMeters());}
    // public Distance getY() {return new Distance(Math.cos(this.angle) * this.magnitude.getMeters());}
    
}

package sss.dataclasses;


public class XY {
    
    public final Distance x;
    public final Distance y;
    
    
    public XY(double x, double y) {
        this.x = new Distance(x);
        this.y = new Distance(y);
    }
    
}

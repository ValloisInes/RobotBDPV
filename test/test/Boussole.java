package test;



public class Boussole {
private double d ;
public Boussole() {
	d=0;
}
public void changementAngle(double a) {
	d=d+a;
	}
public double getD() {
	System.out.println(d);
	return d;
}
}


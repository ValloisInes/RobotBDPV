package firstApp.java;

import lejos.hardware.Battery;
import lejos.internal.ev3.EV3Battery;
import lejos.utility.Delay;

public class Batterietest {
public static double batterie() {
	double eb=Battery.getBatteryCurrent();
	System.out.println(eb);
	return eb;
	
}
public static double moteur() {
	double em=Battery.getBatteryCurrent();
	System.out.println(em);
	return em;
	
}
//public static Battery b=new Battery();
public static void main(String[] args) {
	batterie();
	Delay.msDelay(4000);
	moteur();
	Delay.msDelay(4000);
}
}

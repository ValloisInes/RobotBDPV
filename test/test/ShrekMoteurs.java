package test;
 
import lejos.hardware.motor.Motor;
import lejos.robotics.RegulatedMotor;
//import lejos.robotics.RegulatedMotor;
//import lejos.robotics.navigation.MovePilot;
import lejos.robotics.navigation.MovePilot;

public class ShrekMoteurs extends Boussole{
 	private MovePilot pilot;
 	//private RegulatedMotor pinces;
 	
 	public ShrekMoteurs(){
 		
       	pilot=new MovePilot(2.1f, 4.4f,Motor.A,Motor.B,false);
       	//pinces = new EV3LargeRegulatedMotor(MotorPort.C);
       	
 	}
 	
 	
 	public void avancer( double i) {
       	pilot.travel(i);
 	}
 	public void tourner(double angle) {
       	pilot.rotate(angle);
       	changementAngle(angle);
 	}
 	public void testArc(double r) {
 		pilot.arcForward(r);
 	}
 	public void testearc(double r) {
 		pilot.arc(r,r);
 	}
 	/*public void ouverturepinces(int angle) {
 		//pinces.close();
       	pinces.rotate(angle);
       	pinces.close();
 	}*/
 	
 	//public static void main(String[] args) {
       	// TODO Auto-generated method stub
 	public static void main(String[] args) {
       	ShrekMoteurs sm=new ShrekMoteurs();
       	//DifferentialPilot p=new DifferentialPilot(2.1f, 4.4f,Motor.A,Motor.B,false);
       	//sm.avancer(1.4);
       	//sm.tourner(90);
       	/*pilot.setAcceleration(7);
       	pilot.rotate(90);
       	pilot.travel(14.9);
       	pilot.setTravelSpeed(7);
       	pilot.rotate(90);
       	pilot.travel(17.8);
       	pilot.setAcceleration(-3);
       	pilot.rotate(90);
       	pilot.travel(14.9);*/
       //	sm.ouverturepinces(-200);
       	sm.testArc(Math.PI/2);
       	sm.testearc(90);
       	sm.getD();
       		
 	
}
}

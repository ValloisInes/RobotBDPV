package firstApp.java;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.motor.Motor;
import lejos.hardware.port.MotorPort;
import lejos.robotics.RegulatedMotor;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.utility.Delay;


public class PilotRoues {
	public DifferentialPilot pilot= new DifferentialPilot(1.3f, 3.1f,Motor.A,Motor.B,false);
	public static RegulatedMotor pinces = new EV3LargeRegulatedMotor(MotorPort.C);
	public static void avancer(DifferentialPilot p,double i) {
		p.travel(i);
	}
	public static void tourner(DifferentialPilot p,double angle) {
		p.rotate(angle);
	}
	public static void ouverturepinces(RegulatedMotor p,int angle) {
		p.rotate(angle);
	}
	
	//public static void main(String[] args) {
		// TODO Auto-generated method stub
	public static void main(String[] args) { {
		
		
		
		avancer(pilot,20);
		tourner(pilot,90);
		/*pilot.setAcceleration(7);
		pilot.rotate(90);
		pilot.travel(14.9);
		pilot.setTravelSpeed(7);
		pilot.rotate(90);
		pilot.travel(17.8);
		pilot.setAcceleration(-3);
		pilot.rotate(90);
		pilot.travel(14.9);*/
		ouverturepinces(pinces,20);
		Delay.msDelay(100);
	}
	
}
}

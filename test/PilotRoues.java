package firstApp.java;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.motor.Motor;
import lejos.hardware.port.MotorPort;
import lejos.robotics.RegulatedMotor;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.utility.Delay;


public class PilotRoues{
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DifferentialPilot pilot= new DifferentialPilot(1.3f, 3.2f,Motor.A,Motor.B,false);
		pilot.travel(17.8);
		pilot.rotate(90);
		pilot.travel(14.9);
		pilot.rotate(90);
		pilot.travel(17.8);
		pilot.rotate(90);
		pilot.travel(14.9);
		
		Delay.msDelay(100);
	}
	//AVEC PNEUS 1.6,4
}

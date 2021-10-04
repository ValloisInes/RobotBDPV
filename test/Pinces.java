package firstApp.java;
import lejos.ev3.*;
import lejos.hardware.BrickFinder;
import lejos.hardware.lcd.GraphicsLCD;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.motor.Motor;
import lejos.hardware.port.MotorPort;
import lejos.robotics.RegulatedMotor;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.utility.Delay;
import lejos.*;

public class Pinces {
   public static void main(String[] args) {
	   
	   RegulatedMotor m = new EV3LargeRegulatedMotor(MotorPort.C);
		m.rotate(-1000);
		Delay.msDelay(100);
   }

}

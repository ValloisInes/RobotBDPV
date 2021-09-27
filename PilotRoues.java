package firstApp.java;
import lejos.hardware.motor.Motor;
import lejos.ev3.*;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.utility.Delay;

public class PilotRoues {
DifferentialPilot pilot= new DifferentialPilot(2.1f, 4.4f,Motor.A,Motor.B,false);
pilot.travel=5;
pilot.travel=-20;
pilot.travel(10);
pilot.travel(-10);

Delay.msDelay(1000);
}

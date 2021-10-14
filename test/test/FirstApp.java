package test;
import lejos.hardware.BrickFinder;
import lejos.hardware.lcd.GraphicsLCD;
import lejos.utility.Delay;

public class FirstApp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GraphicsLCD g=BrickFinder.getDefault().getGraphicsLCD();
		g.drawString("Hello World", 0, 0, GraphicsLCD.VCENTER | GraphicsLCD.LEFT);
		Delay.msDelay(5000);

	}

}

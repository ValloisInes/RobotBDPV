package test;
/*import lejos.hardware.Device;
import lejos.hardware.sensor.BaseSensor;
import lejos.hardware.sensor.UARTSensor;
import lejos.hardware.sensor.EV3ColorSensor;
import java.lang.Object;
import lejos.robotics.Color;

public class ColorSensor extends java.lang.Object {
public ColorSensor(int red, int green, int blue) {
	
};
}*/
import lejos.robotics.filter.MeanFilter;
import lejos.utility.Delay;
import lejos.robotics.*;
import lejos.hardware.BrickFinder;
import lejos.hardware.lcd.GraphicsLCD;
import lejos.hardware.port.UARTPort;
import lejos.hardware.sensor.EV3ColorSensor;

public class ColorSensor<Port>
{
    private static float[] path_color;
    private static SampleProvider average;
    private static EV3ColorSensor colorSensor;
    private final static double ERROR = 0.01;

    public ColorSensor(Port port)
    {
    	colorSensor = new EV3ColorSensor((UARTPort) port);
    	average = new MeanFilter(colorSensor.getRGBMode(), 1);
		colorSensor.setFloodlight(Color.WHITE);
		System.out.println("Press enter to calibrate path color...");
		//ButtonENTER.waitForPressAndRelease();
		path_color = new float[average.sampleSize()];
		average.fetchSample(path_color, 0);
    }

    public boolean onPath()
    {
    	float[] sample = new float[average.sampleSize()];
		average.fetchSample(sample, 0);
		
		double scalaire = scalaire(sample, path_color);
		System.out.println(scalaire < ERROR);
		//Button.ENTER.waitForPressAndRelease();
		
		return scalaire(sample, path_color) < ERROR;
    	
    }
    
    public static double scalaire(float[] v1, float[] v2) {
		return Math.sqrt (Math.pow(v1[0] - v2[0], 2.0) +
				Math.pow(v1[1] - v2[1], 2.0) +
				Math.pow(v1[2] - v2[2], 2.0));
	}
    public static void main(String[] args) {
		// TODO Auto-generated method stub
		GraphicsLCD g=BrickFinder.getDefault().getGraphicsLCD();
		g.drawString("Hello World", 0, 0, GraphicsLCD.VCENTER | GraphicsLCD.LEFT);
		Delay.msDelay(5000);

	}

    
}




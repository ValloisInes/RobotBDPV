package firstApp.java;

import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.port.Port;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3TouchSensor;
import lejos.hardware.sensor.SensorMode;
import lejos.utility.Delay;
import lejos.robotics.*;
public class Toucher {
	static EV3TouchSensor sensor=new EV3TouchSensor(SensorPort.S2);
	public static void main(String[] args) {
	final SampleProvider sp = sensor.getTouchMode(); 
	float[] sample=new float[sp.sampleSize()];
	for(int i=0;i<10;i++) {
		sp.fetchSample(sample, 0);
		System.out.println(sample[0]);
		Delay.msDelay(40);
		
	}
	Delay.msDelay(4000);
	sensor.close();

}
}

	/*
	 * import ev3dev.sensors.Battery;
import lejos.hardware.port.SensorPort;
import lejos.robotics.SampleProvider;
import lejos.utility.Delay;

public class TouchSensorDemo {

	//Robot Configuration
	private static EV3TouchSensor touch1 = new EV3TouchSensor(SensorPort.S1);

	//Configuration
	private static int HALF_SECOND = 500;

	public static void main(String[] args) {



		final SampleProvider sp = touch1.getTouchMode();
		int touchValue = 0;

        //Control loop
        final int iteration_threshold = 20;
        for(int i = 0; i <= iteration_threshold; i++) {

        	float [] sample = new float[sp.sampleSize()];
            sp.fetchSample(sample, 0);
            touchValue = (int)sample[0];

			System.out.println("Iteration: {}" + i);
			System.out.println("Touch: {}" + touchValue);

            Delay.msDelay(HALF_SECOND);
        }

        System.out.println(Battery.getInstance().getVoltage());

	}

}
	 */




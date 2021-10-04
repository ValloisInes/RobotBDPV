package firstApp.java;

import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.robotics.SampleProvider;
import lejos.utility.Delay;

public class Distances {

	private static EV3UltrasonicSensor us1 = new EV3UltrasonicSensor(SensorPort.S2);

	public static void main(String[] args) {

		final SampleProvider sp = us1.getDistanceMode();
		int distanceValue = 0;

        final int iteration_threshold = 100;
        for(int i = 0; i <= iteration_threshold; i++) {

        	float [] sample = new float[sp.sampleSize()];
            sp.fetchSample(sample, 0);
            distanceValue = (int)sample[0];

			System.out.println("Iteration: " + i + ", Distance: " + distanceValue);

			Delay.msDelay(500);
        }

	}

}

package firstApp.java;


import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.robotics.SampleProvider;
import lejos.robotics.filter.MeanFilter;
import lejos.utility.Delay;

public class Distances {
	
	

	 static EV3UltrasonicSensor us1 = new EV3UltrasonicSensor(SensorPort.S1);

	public static void main(String[] args) {
		
		
final SampleProvider distance=us1.getDistanceMode();
float[] sample = new float[distance.sampleSize()];
for(int i=0;i<100;i++) {
	distance.fetchSample(sample,0);
	//int k=0;
	//for(float v:sample)
		//System.out.println("===v"+(k++)+"="+v);
	System.out.println(sample[0]);
	
	
}
us1.close();
Delay.msDelay(4000);
/*final SampleProvider average = new MeanFilter(distance, 5);
float[] sample = new float[average.sampleSize()];
average.fetchSample(sample, 0);

for(int i=0; i<average.sampleSize();i++) {
	System
}

		/*final SampleProvider sp = us1.getDistanceMode();
		int distanceValue = 0;
		//us1.enable();

        final int iteration_threshold = 10;
        for(int i = 0; i <= iteration_threshold; i++) {

        	float [] sample = new float[sp.sampleSize()];
            sp.fetchSample(sample, 0);
             distanceValue = (int)sample[i];
           // 
			System.out.println(distanceValue);

			Delay.msDelay(500);
        }

	//get a port instance*/
	/*static EV3UltrasonicSensor us1 = new EV3UltrasonicSensor(SensorPort.S1);

	 // get an instance of this sensor in measurement mode
	 SampleProvider distance= us1.getMode("Distance");

	 // initialize an array of floats for fetching samples. 
	 // Ask the SampleProvider how long the array should be
	 public static void main(String[] args) {
	final float[] sample = new float[us1.sampleSize()];

	 // fetch a sample
	 while(true) {
	   us1.fetchSample(sample, 0);
	 }*/
	 //Delay.msDelay(500);
}
/*
 * public SampleProvider getDistanceMode() {
  switchMode(MODE_DISTANCE, SWITCH_DELAY);
  return getMode(0);
}*/
 
}
package firstApp.java;
import lejos.hardware.Button;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.SensorMode;
import lejos.robotics.Color;
import lejos.robotics.SampleProvider;
import lejos.robotics.filter.MeanFilter;
import lejos.utility.Delay;
import lejos.robotics.filter.MeanFilter;


public class Coloresensore {
static EV3ColorSensor cs=new EV3ColorSensor(SensorPort.S3);


public static void main(String[] args) {
	
	final SampleProvider sp = cs.getColorIDMode();
	//final SampleProvider average = new MeanFilter(sp, 5);
	float [] sample= new float[sp.sampleSize()];
	sp.fetchSample(sample, 0);
	    
	       System.out.println(sample[0]);
	Delay.msDelay(5000);
	cs.close();
	
}

}


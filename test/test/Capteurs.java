package test;

import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.EV3TouchSensor;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.robotics.SampleProvider;
import lejos.utility.Delay;

public class Capteurs {
public static EV3UltrasonicSensor distances = new EV3UltrasonicSensor(SensorPort.S1);
public static EV3TouchSensor toucher=new EV3TouchSensor(SensorPort.S2);
public static  EV3ColorSensor cs=new EV3ColorSensor(SensorPort.S3);

public static float distances(EV3UltrasonicSensor s1) {
	final SampleProvider distance=distances.getDistanceMode();
	float[] sample = new float[distance.sampleSize()];
	//for(int i=0;i<100;i++) {
		distance.fetchSample(sample,0);
		float d=sample[0];
		System.out.println(d);
		return d;
	//}
}
public static int couleur(EV3ColorSensor cs){
cs.setFloodlight(true);
	
	final SampleProvider sp = cs.getColorIDMode();
	//final SampleProvider average = new MeanFilter(sp, 5);
	float [] scsample= new float[sp.sampleSize()];
	sp.fetchSample(scsample, 0);
	int c=(int)scsample[0];
	       System.out.println(c);  
return c;
}
public static boolean touche(EV3TouchSensor t) {
	final SampleProvider sp = toucher.getTouchMode(); 
	float[] sample=new float[sp.sampleSize()];
	//for(int i=0;i<10;i++) {
		sp.fetchSample(sample, 0);
		System.out.println(sample[0]);
		Delay.msDelay(40);
		return sample[0]==1;
	//}

}
	/*public static boolean istouched() {
	final SampleProvider sp = toucher.getTouchMode(); 
	float[] sample=new float[sp.sampleSize()];
	sp.fetchSample(sample, 0);
	if(sample[0] == 0)
	    return false;
	else 
	    return true;
}
	*/
public static void main(String[] args) {
	distances(distances);
	couleur(cs);
	touche(toucher);

}
}

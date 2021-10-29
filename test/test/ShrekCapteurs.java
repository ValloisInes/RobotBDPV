package test;

import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.EV3TouchSensor;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.robotics.SampleProvider;
import lejos.utility.Delay;
 
public class ShrekCapteurs {
 	private EV3UltrasonicSensor distances ;
 	private EV3TouchSensor toucher;
 	private EV3ColorSensor cs;
 	
 	public ShrekCapteurs() {
       	distances = new EV3UltrasonicSensor(SensorPort.S1);
       	toucher=new EV3TouchSensor(SensorPort.S2);
       	cs=new EV3ColorSensor(SensorPort.S3);
 
 	}
 
 	public  float distances() {
       	//final SampleProvider distance=distances.getDistanceMode();
       	//final EV3UltrasonicSensor s1=distances;
       	final SampleProvider distance=distances.getDistanceMode();;
       	float[] sample = new float[distance.sampleSize()];
       	//for(int i=0;i<100;i++) {
       	distance.fetchSample(sample,0);
       	float d=sample[0];
       	System.out.println(d);
       	
       	return d;
       	//}
 	}
 	public int couleur(){
       	//final EV3ColorSensor c=cs;
       	cs.setFloodlight(true);
 
       	final SampleProvider sp = cs.getColorIDMode();
       	//final SampleProvider average = new MeanFilter(sp, 5);
       	float [] scsample= new float[sp.sampleSize()];
       	sp.fetchSample(scsample, 0);
       	int cint=(int)scsample[0];
       	System.out.println(cint); 
       	Delay.msDelay(4000);
       	return cint;
 	}
 	public boolean touche() {
       	//final EV3TouchSensor t=toucher;
       	final SampleProvider sp = toucher.getTouchMode();
       	float[] sample=new float[sp.sampleSize()];
       	//for(int i=0;i<10;i++) {
       	sp.fetchSample(sample, 0);
       	System.out.println(sample[0]);
       	Delay.msDelay(4000);
       	return sample[0]==1.00;
       	//}
 
 	}
 	public static void main(String[] args) {
       	ShrekCapteurs sc=new ShrekCapteurs();
       	sc.distances();
       	sc.couleur();
       	sc.touche();
       	Delay.msDelay(7000);
 	}
}

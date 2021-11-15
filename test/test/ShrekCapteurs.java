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
       	float d=sample[0]; // affiche la distance, stoké puis overwritte dans la 1er case du tableau sample 
       	System.out.println(d);
       	
       	return d;
       	//}
 	}
 	public int couleur(){
       	//final EV3ColorSensor c=cs;
       	cs.setFloodlight(true);  // Lumiere blanche on pour aider a la distinction des couleurs 
 
       	final SampleProvider sp = cs.getColorIDMode(); // necessaire
       	//final SampleProvider average = new MeanFilter(sp, 5);
       	float [] scsample= new float[sp.sampleSize()]; // necessaire
       	sp.fetchSample(scsample, 0); //
       	int cint=(int)scsample[0]; // ID de la couleurs 
       	System.out.println(cint);  // renvoi ID couleurs
       	Delay.msDelay(4000); // Delai de 4 secondes pour pouvoir lire 
       	return cint;
 	}
 	public boolean touche() {
       	//final EV3TouchSensor t=toucher;
       	final SampleProvider sp = toucher.getTouchMode();
       	float[] sample=new float[sp.sampleSize()];
       	//for(int i=0;i<10;i++) {
       	sp.fetchSample(sample, 0);
       	System.out.println(sample[0]); // affiche la valeur 1 = Actionné ou 0 = pas actionné 
       	Delay.msDelay(4000);
       	return sample[0]==1.00; // renvoi true si sample[0]==1 donc actionné, false sinon 
       	//}
 
 	}
 	public static void main(String[] args) {
 		ShrekBoussole Boussole = new ShrekBoussole() ; 
    	ShrekCapteurs sc=new ShrekCapteurs();
       	ShrekMoteurs Shrek = new ShrekMoteurs( Boussole);
	
       	Shrek.shrekAvanceSynchro(0.5, true);
       //	while (shreck.isMoving())
       	Delay.msDelay(10000);
       	Shrek.shrekTourneDroite(20);
       	
       	
     //  	sc.distances();
      	//sc.couleur();
     // 	sc.touche();
     //  	Delay.msDelay(7000);
 	}
}

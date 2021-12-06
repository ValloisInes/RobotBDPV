package test;

import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.EV3TouchSensor;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.robotics.SampleProvider;
import lejos.utility.Delay;

/**
 * Class regroupant les <b>differents capteurs</b> que le robot possède. Cette class permet de :<ul>
 * - Instancier les 3 capteurs.</ul> <ul>
 * - Recuperer les capteurs individuellement pour pouvoir appeler leurs methodes respectives.</ul><p>
 * @author Pierre Parouty
 *
 */
public class ShrekCapteurs2 {
	
	/**
	 * Attribut permettant d'instancier le capteur UltraSonic
	 * @see ShrekUltraSonic
	 */
	private ShrekUltraSonic shrek1;
	
	/**
	 * Attribut permettant d'instancier le capteur de touché
	 * @see ShrekTouch 
	 */
	private ShrekTouch shrek2;
	
	/**
	 * Attribut permettant d'instancier le capteur de couleur
	 * @see ShrekCouleurs
	 */
	private ShrekCouleurs shrek3;

	
	/**
	 * Constructeur de la class qui initialise tous les attribue en appelant les constructeurs respectifs des capteurs
	 * @see ShrekUltraSonic#ShrekUltraSonic() le constructeur du capteur UltraSon
	 * @see ShrekTouch#ShrekTouch() le constructeur du capteur de touché
	 * @see ShrekCouleurs#ShrekCouleurs() le constructeur du capteur de couleurs
	 */
	public ShrekCapteurs2() {
		shrek1 = new ShrekUltraSonic();
		shrek2 = new ShrekTouch();
		shrek3=new ShrekCouleurs();
	}

	
	
	
	
	/**
	 * Methode pour recuperer le capteur UltraSon, pour ensuite pouvoir utiliser les methodes definis dans la class {@link #ShrekUltraSonic}
	 * @return {@link #shrek1} 
	 */
	public ShrekUltraSonic getCapteurUltraSonic(){
		return this.shrek1;
	}

	/**
	 * Methode pour recuperer le capteur de touché, pour pouvoir utiliser les methodes definis dans la class {@link #ShrekTouch}
	 * @return {@link #shrek2}
	 */
	public ShrekTouch getCapteurTouche() {
		return this.shrek2 ;
	}

	/**
	 * Methode pour recuperer le capteur de couleurs, pour pouvoir utiliser les methodes definis dans la class {@link #ShrekCouleurs}
	 * @return {@link #shrek3}
	 */
	public ShrekCouleurs getCapteurCouleurs() {
		return this.shrek3;
	}




}






























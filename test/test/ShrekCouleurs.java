package test;

import lejos.hardware.port.Port;
import lejos.utility.Delay;
import lejos.robotics.SampleProvider;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.EV3TouchSensor;


/**
 * Class permettant l'utilisation du <b>capteur de couleurs</b> du robot. La class permet de:<ul>
 * - instancier un capteur de couleurs de type EV3ColorSensor. </ul> <ul>
 * - recuperer une couleurs à un instant donné </ul> <ul>
 * @author Pierre Parouty <p>
 *@see lejos.hardware.sensor.EV3ColorSensor La documentation officielle du EV3ColorSensor.
 */
public class ShrekCouleurs {

	/**
	 * Un attribut ou sera stocké la couleurs observée à l'instant t. Les ID des couleurs sont des <b>entiers</b>, l'attribut est donc un <b>int</b>.
	 */
	private	int colEnregistrée ;
	
	/**
	 * Un SampleProvider pour gerer les flux de données.
	 */
	private SampleProvider trucAbstrait ; 

	/**
	 * Un tableau de float dans lequel on stockera les informations captées (ID des couleurs).
	 */
	private float[] tabCouleursCaptées ;

	/**
	 * Un attribut EV3ColorsSensor permettant d'instancier le capteur de couleurs
	 * @see lejos.hardware.sensor.EV3ColorSensor La documentation officielle du EV3ColorSensor.
	 */
	EV3ColorSensor shrekColor ;
	
	/**
	 * Un attribut boolean qui prendra <i>true</i> si la couleurs captée est le blanc, <i> false </i> sinon.
	 */
	boolean estBlanc ;


	
	/**
	 * Le constructeur de la class {@link ShrekCouleurs} qui initialise les attributs et instancie correctement le capteurs:<p><ul>
	 * - On attribut au capteur de couleurs le port <b> S3.</b> </ul> <ul>
	 * - On parametre le capteur avec le mode <b>.getColorIDMode()</b> pour recuperer l'ID des couleurs prédefinis dans lejos.</ul>
	 * @see lejos.hardware.sensor.EV3ColorSensor La documentaton officielle de l'EV3ColorSensor pour la description de la methode .getColorIDMode().
	 */
	public ShrekCouleurs() {
		this.shrekColor = new EV3ColorSensor(SensorPort.S3);
		this.trucAbstrait = shrekColor.getColorIDMode();
		this.tabCouleursCaptées = new float[trucAbstrait.sampleSize()];
		this.colEnregistrée=0    ;  
		if(this.colEnregistrée==6) {
			this.estBlanc = true;  
		}
		else {
			this.estBlanc= false;
		}
	}




	/**
	 * Methode qui permet de recuperer la derniere couleurs captée par le capteurs de couleurs qui est stocké dans la position 0 du tableau {@link #tabCouleursCaptées}. On decide de stocker la derniere couleurs captée dans l'attribut {@link #colEnregistrée} pour pouvoir réutiliser l'information plus simplement plus tard. <p>
	 * Pour faciliter la detection et aider le capteurs, on active la <b>lumiere blanche</b> avec l'instruction <b>.setFloodlight(true)</b>. 
	 * @return {@link #colEnregistrée}
	 * 
	 */
	public int getColor() {
		this.shrekColor.getColorIDMode();
		this.shrekColor.setFloodlight(true);  
		this.trucAbstrait.fetchSample(tabCouleursCaptées, 0);
		this.colEnregistrée= (int)tabCouleursCaptées[0];
		System.out.println("la couleurs est:" +colEnregistrée);
		return colEnregistrée;

	}




}

package test;

import lejos.hardware.port.Port;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3TouchSensor;
import lejos.robotics.SampleProvider;
import lejos.utility.Delay;

/**
 * Class permettant l'utilisation du <b>capteurs de touché</b>. Dans cette class nous faisons: <ul>
 * - On instancie le capteurs de touché de type EV3TouchSensor. </ul> <ul>
 * - On recupere l'information issue du capteurs pour l'exploiter. </ul>
 * @author Pierre Parouty <p>
 * @see lejos.hardware.sensor.EV3TouchSensor La class officielle du capteur de touché EV3TouchSensor.
 *
 */
public class ShrekTouch {

	
	/**
	 * Un attribut SampleProvider pour gerer les flux de données.
	 * @see lejos.robotics.SampleProvider doc officielle du SampleProvider.
	 */
	private SampleProvider trucAbstrait ;


	/**
	 * Un tableau de float pour stocker les informations retournées par le capteurs.
	 */
	private float[] tabTouchCaptées ;



	/**
	 * Un attribut boolean indiquant si le capteurs est activé (<i> true </i>) ou non (<i> false </i>)
	 */
	private boolean status ; 



	/**
	 * Une attribut EV3TouchSensor permettant d'initialiser le capteur de touché.
	 * @see EV3TouchSensor la doc officielle du EV3TouchSensor
	 */
	public EV3TouchSensor shrekT;
	
	
	/**
	 * Le constructeur de la class {@link ShrekTouch} : <ul>
	 * - On initialise l'attribut {@link #shrekT} avec le constructeur de l'EV3TouchSensor, en lui attribuant le port <b>S2</b> </ul> <ul>
	 * - On parametre le capteur sur le mode Touch en utilisant la methode .getTouchMode() </ul> 
	 * @see EV3TouchSensor la doc officielle du EV3TouchSensor pour la methode .getTouchMode et le constructeur de la class.
	 */
	public ShrekTouch() {
		this.shrekT = new EV3TouchSensor(SensorPort.S2);
		this.trucAbstrait=shrekT.getTouchMode();
		this.tabTouchCaptées= new float[trucAbstrait.sampleSize()];
		this.status=false;
	}
	
	/**
	 * Methode qui renvoi <i>true</i> si le capteurs de touché s'active lors de l'appel. On en profite pour attribuer à {@link #status} la valeur <i> true </i> pour ne pas risquer de perdre l'information. (robot qui pousse le palai en arrivant par exemple)
	 * 
	 * @return <i> true</i> si au moment de l'appel de la methode le capteurs s'active, <i> false</i> sinon. <p>
	 * @see #status
	 * @see #updateStatus()
	 */
	public boolean estTouche() {
		trucAbstrait.fetchSample(tabTouchCaptées, 0);
		System.out.println(tabTouchCaptées[0]);
		if(((int)tabTouchCaptées[0]==1)) {
			this.status=true;
		}
		return (int)tabTouchCaptées[0]==1;
	}



	/**
	 * Methode qui permet de verifier si dans le passé le capteur s'est activé et de mettre a jour {@link #status}.
	 * @return {@link #status} qui à pris la valeurs <i> true </i> si {@link #estTouche()} à renvoyé <i> true </i>, <i> false </i> sinon. <p>
	 * @see #status
	 * @see #estTouche()
	 */
	public boolean updateStatus() {
		if(this.estTouche()) {
			return this.status=true;
		}
		else {
			this.status=false;
			return this.status;
		}
	}
	
	/**
	 * Methode pour recuperer la valeur de {@link #status}.
	 * @return {@link #status}
	 */
	public boolean getStatus() {
		return this.status ;
	}

}








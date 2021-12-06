package test;

import lejos.hardware.port.Port;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.robotics.SampleProvider;



/**
 * Class permettant l'utilisation du capteur de Distance dans laquelle on: <ul>
 * - Instancie le capteur de distance de type EV3UltrasonicSensor. </ul> <ul>
 * - Realise des methodes pour recuperer et manipuler les données issues du capteur de distance </ul>
 * @author Pierre Parouty <p>
 * @see EV3UltrasonicSensor la doc officielle du capteur de distance EV3UltrasonicSensor.
 *
 */
public class ShrekUltraSonic {

	
	/**
	 * Un <b>tableau de float</b> pour stocker les infos renvoyées par le capteurs (des distances).
	 */
	private float[] tabDistCaptées ; 

	
	/**
	 * Un attribut EV3UltrasonicSensor pour utiliser le capteur de distance.
	 * @see EV3UltrasonicSensor la doc officielle du capteur de type EV3UltrasonicSensor.
	 */
	private EV3UltrasonicSensor shrekUltra ; 

	
	/**
	 * Un SampleProvider necessaire pour gerer les flux de données.
	 * @see SampleProvider la doc officielle du SampleProvider.
	 */
	private SampleProvider trucAbstrait ;
	
	
	/**
	 * Un attribut qui stockera la distance captée lors de l'appelle de la methode {@link #getDistance()}. 
	 */
	private float distanceActuelle ; 
	
	/**
	 * Un attribut qui stockera la distance <b>precedement mesurée</b> par le capteur.
	 */
	private float distancePassée ; 
	
	/**
	 * Constructeur de la class {@link ShrekUltraSonic} : <ul>
	 * - On initialise {@link #shrekUltra} en appelant le constructeur de la class EV3UltrasonicSensor, et en lui attribuant le port <b>S1</b> </ul> <ul>
	 * - On indique que l'on veut utiliser le mode distance à l'aide de la methode <b>.getDistanceMode()</b>. </ul> <ul>
	 * - On initialise {@link #distanceActuelle} avec la valeur à la position <b>0</b>  de {@link #tabDistCaptées} (donc la valeur la plus recente) </ul> <ul>
	 * - On initialise {@link #distancePassée} à 0 puisqu'au moment de l'instanciation, il n'y a pas encore de valeurs antérieurs. </ul>
	 * @see EV3UltrasonicSensor La documentation officielle de l'EV3UltrasonicSensor pour regarder le constructeur et la methode .getDistanceMode().
	 */
	public ShrekUltraSonic() {
		this.shrekUltra= new EV3UltrasonicSensor(SensorPort.S1); 

		this.trucAbstrait =shrekUltra.getDistanceMode();
		this.tabDistCaptées = new float[trucAbstrait.sampleSize()]; 
		this.distanceActuelle=tabDistCaptées[0]; 
		this.distancePassée = 0; 

	}

	
	/**
	 * Methode pour recuperer une distance à un instant donné : <ul>
	 * - On recupere la valeur du tableau {@link #tabDistCaptées} à la position <b>0</b>, puis on la renvoi. </ul>
	 * @return la valeur à la position <b>0</b>  de {@link #tabDistCaptées}au moment de l'appel de la methode.
	 * @see #tabDistCaptées.
	 */
	public float getDistance() {
		this.trucAbstrait = shrekUltra.getDistanceMode();
		this.trucAbstrait.fetchSample(tabDistCaptées, 0); 
		return tabDistCaptées[0];
	}



	/**
	 * Methode pour recuperer la distance actuellement mesurée. Oui en regardant cette methode et getDistance(), on aurait pu faire plus simple..
	 * @return {@link #distanceActuelle}
	 */
	public float getDistanceActuelle() {
		return this.getDistance();
	}
	
	/**
	 * Methode pour recuperer la distance <b>passée</b> (don celle qui a été mesurée avant celle actuellement mesurée).
	 * @return {@link #distancePassée}
	 */
	public float getDistancePassée() {
		return this.distancePassée;

	}


	
	/**
	 * Methode qui permet de verifier si le robot est en <b>face d'un mur</b>. (donc utile pour verifier si il a confondu un mur avec un palai). Lorsque la distance entre le robot est le palai est <0.3, le robot ne voit plus le palai (le palai passe sous le champs de vision du capteur)
	 * @return <i> true </i> si la distance actuellement captée est <b>inferieur à 0.3</b>, <i> false </i> sinon
	 */
	public boolean shrekFaceAuMur() { 
		return this.getDistance()<0.3; 
	}



	/**
	 * methode permettant de determiner si le robot a trouvé un palai: <ul>
	 * - Il faut que la distance actuelle soit <b>superieur à 30 cm</b>, pour eviter de prendre le mur. </ul> <ul>
	 * - Mais que la distance passée soit <b>inférieur a 0.4</b> pour assurer la detection de l'objet. </ul>
	 * @return <i> true </i>si les conditions citées dans la description de la methode sont respectées, <i> false </i> sinon.
	 */
	public boolean shrekATrouvePalai() {
		return (this.getDistancePassée()<=0.4 && this.getDistance()>0.3); 
	}



	/**
	 * Methode pour verifier si le robot vient de rater le palai (en passant a coté lorsqu'il a voulu s'en saisir par exemple), il faut verifier : <ul>
	 * - Si la distance actuelle est <b>supérieur à 0.4</b> (donc il n'y a rien de proche devant le robot) </ul> <ul>
	 * - Si la distance précedement mesurée <b>est plus petite</b> que la distance actuellement mesurée (ça signifierait que le palai est passé sur le coté du champ de vision. </ul>
	 * @return <i> true</i> si les conditions citées dans la description de la methode sont rencontrées, <i> false </i> sinon.
	 */
	public boolean shrekRateLePalai() {
		return this.getDistanceActuelle()>0.4 && this.getDistancePassée()<this.getDistanceActuelle();


	}






















}

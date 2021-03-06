package test;

import lejos.robotics.navigation.MovePilot ;
import lejos.utility.Delay;
import lejos.hardware.BrickFinder;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.motor.Motor;
import lejos.hardware.port.Port;
import lejos.robotics.chassis.Chassis;
import lejos.robotics.chassis.Wheel;
import lejos.robotics.chassis.WheeledChassis;
import lejos.robotics.navigation.*;
import lejos.hardware.Button;


/**
 * Class relative à tous les deplacements du robot qui permet de: <ul>
 * - Instancier un MovePilot pour acceder aux methodes de deplacements. </ul> <ul>
 * - Manipuler les deplacements (avancer, reculer, tourner..) </ul>
 * 
 * @author Pierre Parouty <p>
 * @see lejos.robotics.navigation.MovePilot la documentation officielle de la class MovePilot.
 *
 */
public class ShrekQuiBouge { 


	/**
	 * Un attribut MovePilot necessaire aux deplacements du robot.
	 * @see lejos.robotics.navigation.MovePilot la documentation officielle de la class MovePilot.
	 */
	private MovePilot Shrek ;

	
	/**
	 * Le constructeur de la class {@link ShrekQuiBouge}: <ul>
	 * - On suit la procedure d'initialisation du MovePilot décrite dans la documentation MovePilot (les valeurs peuvent varier car ce sont nos propres mesures).</ul> <ul>
	 * - On parametre le MovePilot sur les moteurs <b>A</b> et <b>B</b></ul>
	 * @see lejos.robotics.navigation.MovePilot la documentation officielle de la class MovePilot pour voir la procedure d'instanciation.
	 */
	public ShrekQuiBouge () {    

		Port motorb = BrickFinder.getDefault().getPort("B");
		Port motora = BrickFinder.getDefault().getPort("A");
		Wheel leftWheel = WheeledChassis.modelWheel(new EV3LargeRegulatedMotor(motorb),0.056).offset(-0.06075); 
		Wheel rightWheel = WheeledChassis.modelWheel(new EV3LargeRegulatedMotor(motora), 0.056).offset(0.06075);
		Chassis chassis = new WheeledChassis(new Wheel[] {leftWheel, rightWheel}, WheeledChassis.TYPE_DIFFERENTIAL);
		Shrek = new MovePilot(chassis);
		}
	
	
	/**
	 * Methode pour recuperer le MovePilot et l'utiliser dans d'autre class.
	 * @return {@link #Shrek} <p>
	 * @see lejos.robotics.navigation.MovePilot la documentation officielle de la class MovePilot.
	 */
	public MovePilot getPilot() {
		return this.Shrek;
	}



	/**
	 * Methode permettant d'avancer en ligne droite d'une distance rentrée en parametre.
	 * @param distance <p>
	 * @see lejos.robotics.navigation.MovePilot la documentation officielle de la class MovePilot pour la methode .travel(double distance).
	 */
	public void shrekAvance (double distance ) {

		Shrek.travel(distance);
	}

	
	/**
	 * Methode permettant de reculer en ligne droite d'une distance entrée en parametre.
	 * @param distance <p>
	 * @see lejos.robotics.navigation.MovePilot la documentation officielle de la class MovePilot pour la methode .travel(double distance).
	 */
	public void shrekRecule(double distance) {
		Shrek.travel(- distance);

	}



	/**
	 * Methode permettant d'effectuer une rotation (donc de tourner) vers la gauche, d'un angle rentré en parametre.
	 * @param angle <p>
	 * @see lejos.robotics.navigation.MovePilot la documentation officielle de la class MovePilot pour la methode .rotate(double angle)
	 */
	public void shrekTourneGauche (double angle) {
		Shrek.rotate(angle);
	}


	
	/**
	 * methode permettant d'effectuer une rotation (donc de tourner) vers la droite, d'un angle rentré en parametre. Pour aller <b>à droite</b>, la valeur de l'angle doit être <b>negative</b>.
	 * @param angle <p>
	 * @see lejos.robotics.navigation.MovePilot la documentation officielle de la class MovePilot pour la methode .rotate(double angle)
	 */
	public void shrekTourneDroite ( double angle) {
		Shrek.rotate(-angle);
	}



	
	/**
	 * Methode permettant d'effectuer une rotation soit à droite, soit a gauche, en laissant s'exécuter les instructions suivante dans le programme :<ul>
	 * - Si l'angle est negatif la rotation sera a droite, sinon elle sera a gauche. </ul> <ul>
	 * - On appelle la methode .rotate(double angle, boolean immediateReturn) et on fixe le boolean sur <i> true </i> indiquant qu'on autorise les instructions suivantes à s'exécuter. </ul>
	 * @param angle <p>
	 * @see lejos.robotics.navigation.MovePilot la documentation officielle de la class MovePilot pour la methode .rotate(double angle, boolean immediateReturn).
	 */
	public void shrekRotateWhile(double angle) { 
		Shrek.rotate(angle, true);


	}

	
	/**
	 * Methode permettant d'effectuer un deplacement en ligne droite et en laissant les instructions suivante du programme s'éxécuter: <ul>
	 * - Si la valeur rentrée en parametre est <b>positive, le robot avance</b> de cette distance, si elle est <b>negative le robot recule </b>de cette distance. </ul> <ul>
	 * - On fixe le boolean de la methode .travel(double distance, boolean immediateReturn) sur <i> true </i> indiquant qu'on laisse les instructions suivantes s'executer, sans attendre la fin de celle-ci. </ul>
	 * @param distance <p>
	 * @see lejos.robotics.navigation.MovePilot la documentation officielle de la class MovePilot pour la methode .travel(double distance, boolean immediateReturn).
	 */
	public void shrekMoveWhile(double distance) {
		Shrek.travel(distance, true);
	}


	/**
	 * Methode qui indique si le robot est en mouvement.
	 * @return true </b> </i> si le robot est en mouvement, <i> <b> false </b> </i> sinon. <p>
	 * @see lejos.robotics.navigation.MovePilot la documentation officielle de la class MovePilot pour la methode .isMoving()
	 */
	public boolean shrekIsMoving() {
		return Shrek.isMoving();
	}

	
	/**
	 * Methode qui permet de faire <b>avancer</b> le robot en ligne droite, d'une <b>distance indeterminée</b>. Le mieu c'est de l'utiliser dans une condition ou avec un while(isMoving).
	 * @see lejos.robotics.navigation.MovePilot la documentation officielle de la class MovePilot pour la methode .forward()
	 */
	public void shrekEnAvant() {
		this.Shrek.forward();

	}

	
	/**
	 * Methode qui permet d'arreter tous mouvement en cours du robot.
	 * @see lejos.robotics.navigation.MovePilot la documentation officielle de la class MovePilot pour la methode .stop() 
	 */
	public void shrekStop() {

		this.Shrek.stop();
	}

	public static void main(String[] args) {
		//droite = negatif pour rotation 

		Shrek2 s = new Shrek2(); // le 2 est le nouveeau 6 dans shrek2 ET dans shrekultraSonic donc dans testligne et getcolor + constructeur + .quiMarque les 6 remplacépar des 2 
/*Button.waitForAnyPress();
	if(Button.ENTER.isDown()) {


	/*	s.Run();
	}











}

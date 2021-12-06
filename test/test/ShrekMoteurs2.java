package test;

import lejos.utility.Delay;


/**
 * Class regroupant les <b> differents moteurs</b> que le robot possède. L'objectif de cette class est de: <ul>
 * - Instancier les moteurs, donc un {@link ShrekQuiBouge} (un MovePilot pour les deplacements) et un {@link ShrekPinces} (class permettant d'instancier les pinces du robot).</ul> <ul>
 * - Recuperer les class des moteurs pour pouvoir utiliser leurs methodes respectives. </ul>
 * @author Pierre Parouty <p>
 *
 *@see test.ShrekQuiBouge
 *@see test.ShrekPinces
 *@see lejos.robotics.navigation.MovePilot Pour plus d'infos la documentation officielle de lejos sur la class MovePilot.
 */
public class ShrekMoteurs2 {


	/**
	 * Un attribut {@link #ShrekQuiBouge} qui permet d'instancier un MovePilot, necessaire à tous les <b> déplacements</b> du robot.
	 * @see test.ShrekQuiBouge 
	 * @see lejos.robotics.navigation.MovePilot docu officielle de MovePilot.
	 */
	private ShrekQuiBouge shrekDeplacement ;



	/**
	 * Un attribut {@link #ShrekPinces} pour instancier les pinces du robot.
	 * @see test.ShrekPinces 
	 */
	private ShrekPinces shrekP ;


	/**
	 * Le constructeur de la class. Il initialise tous les attributs en appelant les constructeurs respectifs des class {@link #ShrekQuiBouge} et {@link #ShrekPinces}.
	 * @see ShrekQuiBouge#ShrekQuiBouge() Le constructeur de ShrekQuiBouge.
	 * @see ShrekPinces#ShrekPinces() Le constructeurs de ShrekPinces
	 */
	public ShrekMoteurs2() {
		shrekDeplacement = new ShrekQuiBouge();
		shrekP = new ShrekPinces();

	}



	/**
	 * Methode qui permet de recuperer l'instance de {@link #ShrekQuiBouge} pour utiliser ses methodes. 
	 * @return {@link #shrekDeplacement}
	 * @see test.ShrekQuiBouge 
	 */
	public ShrekQuiBouge getShrekQuiBouge() {
		return this.shrekDeplacement;
	}


	/**
	 * Methode qui permet de recuperer l'instance de {@link #ShrekPinces} pour utiliser ses methodes.
	 * @return {@link #shrekP}
	 * @see test.ShrekPinces 
	 */
	public ShrekPinces getShrekPinces() {
		return this.shrekP;
	}



}








































































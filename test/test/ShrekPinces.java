package test;

import lejos.hardware.motor.Motor;
import lejos.robotics.RegulatedMotor;
import lejos.hardware.BrickFinder;
//import lejos.robotics.RegulatedMotor;
//import lejos.robotics.navigation.MovePilot;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.motor.Motor;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.Port;
import lejos.robotics.RegulatedMotor;

import lejos.utility.Delay;
import lejos.robotics.navigation.MovePilot;



/**
 * Class permettant l'utilisation des pinces du RobotEV3 Shrek. Cette class permet de : <ul>
 * - Instancier les pinces du robot. </ul> <ul>
 * - Manipuler les pinces (ouverture, fermeture..) </ul>
 * 
 * @author Pierre Parouty <p>
 *
 */
public class ShrekPinces {


	/**
	 * Un attribut <b>RegulatedMotor</b> necessaire à l'instanciation des pinces. (Les pinces sont un moteur de type RegulatedMotor).
	 * @see lejos.robotics.RegulatedMotor La doc du RegulatedMotor.
	 */
	private	RegulatedMotor pinces; 

	/**
	 * Un attribut boolean indiquant si les pinces sont ouverte (<i> <b>true</b> </i>) ou fermée (<i> <b>false</b> </i>)
	 */
	private boolean etatPinces ; 


	/**
	 * Un attribut boolean indiquant le contenu des pinces: Avec un palai = <i> <b>true</b> </i>, sans palai =<i> <b>false</b> </i>.
	 */
	private boolean etatContenu ; 




	/**
	 * Constructeur de la class {@link ShrekPinces} permettant d'instancier les Pinces du robot de la maniere suivante : <ul>
	 * - On parametre les pinces sur le port C </ul> <ul>
	 * - On appelle le constructeur de la class EV3LargeRegulatedMotor. </ul> <ul>
	 * - les deux attributs booléens sont initialisé a <i> false </i> puisqu'au depart les pinces sont fermées, et elles ne contiennent pas encore de palai. </ul>
	 * @see EV3LargeRegulatedMotor 
	 */
	public ShrekPinces () {    

		Port motorc = BrickFinder.getDefault().getPort("C");
		this.pinces = new EV3LargeRegulatedMotor(motorc);
		this.etatContenu = false;
		this.etatPinces= false;
		System.out.println("cestbon");

	}


	/**
	 * Methode permettant de recuperer la valeur de l'attribut {@link #etatPinces}.
	 * @return {@link #etatPinces}
	 */
	public boolean getEtatPinces() {
		return this.etatPinces ;
	}

	
	/**
	 * Methode permettant de recuperer la valeur de l'attribut {@link #etatContenu}
	 * @return {@link #etatContenu}
	 */
	public boolean getEtatContenu() {

		return this.etatContenu ;
	}


	
	/**
	 * Methode permettant de fermer les pinces sans qu'elles contiennent de palai : <ul>
	 * - La methode fait appelle a la methode .rotate(int angle) de la class RegulatedMotor. Si l'angle entré en parametre est negatif, les pinces se ferment. </ul> <ul>
	 * - La methode parametre l'attribut {@link #etatPinces} sur <i> <b> false </b> </i> puisqu'elles sont maintenant fermée. </ul> <ul>
	 * - on ferme les pinces avec une valeur testée de 500 dans cette methode. </ul> 
	 * @see lejos.robotics.RegulatedMotor La doc officielle du RegulatedMotor pour la fonction .rotate(int angle).
	 */
	public void shrekFermePincesSansPalai() {
		pinces.rotate(-500);
		this.etatPinces = false ;
	}



	/**
	 * Methode pour fermé les pinces alors qu'elles sont vides, mais cette fois-ci avec un <b>angle</b> rentré en parametre. <ul>
	 * - On veut fermer les pinces donc on passe le parametre angle en negatif. </ul> <ul>
	 * - On indique que les pinces sont maintenant fermées avec une valeur <i> false</i> sur l'attribut {@link #etatPinces}. </ul>
	 * @param angle <p>
	 * @see lejos.robotics.RegulatedMotor La doc officielle du RegulatedMotor pour la fonction .rotate(int angle).
	 */
	public void shrekFermePincesSansPalai(int angle ) {
		pinces.rotate(-angle) ; 
		this.etatPinces = false ;

	}




	/**
	 * Methode pour fermer les pinces mais avec un Palai: <ul>
	 * - On appelle la methode .rotate(int angle) avec une valeur negative testée pour fermer les pinces. </ul> <ul>
	 * - On positionne les attributs {@link #etatContenu} et {@link #etatPinces} pinces sur <i> false </i> puisque les pinces sont fermées et contiennent un palai.</ul>
	 * @see lejos.robotics.RegulatedMotor La doc officielle du RegulatedMotor pour la fonction .rotate(int angle).
	 */
	public void shrekFermePincesAvecPalai() {
		pinces.rotate(-500); 
		this.etatPinces = false ;
		this.etatContenu = false ;
	}

	
	/**
	 * Methode pour fermer les pinces avec un palai en fonction d'un <b> angle rentré en parametre </b> avec un palai comme contenu: <ul>
	 * - On appelle la methode .rotate(int angle) avec l'angle entré en parametre. </ul> <ul>
	 * - On positionne les attributs {@link #etatContenu} et {@link #etatPinces} pinces sur <i> false </i> puisque les pinces sont fermées et contiennent un palai.</ul>
	 * @param angle <p>
	 * @see lejos.robotics.RegulatedMotor La doc officielle du RegulatedMotor pour la fonction .rotate(int angle).
	 * 
	 */
	public void shrekFermePincesAvecPalai(int angle ) {
		pinces.rotate(-angle);  
		this.etatPinces = false ;
		this.etatContenu = false ;
	}


	/**
	 * Methode permettant d'ouvrir les pinces sans palai: <ul>
	 * - On appelle la methode .rotate(int angle) avec une valeur testée positive pour ouvrir les pinces. </ul> <ul>
	 * - On indique que les pinces sont ouvertes on donnant la valeur <i> true</i> à l'attribut {@link #etatPinces}. </ul>
	 * @see lejos.robotics.RegulatedMotor La doc officielle du RegulatedMotor pour la fonction .rotate(int angle).
	 */
	public void shrekOuvrePincesSansPalai(){
		this.pinces.rotate(500);
		this.etatPinces = true ;



	}
	
	/**
	 * Methode permettant d'ouvrir les pinces sans palai avec un angle entré en parametre : <ul>
	 * - On appelle la methode .rotate(int angle) avec la valeur entrée en parametre pour ouvrire les pinces. </ul> <ul>
	 * - On indique que les pinces sont ouvertes en fixant l'attribut boolean {@link #etatPinces} sur <i> true </i>. </ul>
	 * @param angle <p>
	 * @see lejos.robotics.RegulatedMotor La doc officielle du RegulatedMotor pour la fonction .rotate(int angle).
	 */
	public void shrekOuvrePincesSansPalai(int angle){
		this.pinces.rotate(angle);
		this.etatPinces = true ;



	}

	
	/**
	 * Methode permettant d'ouvrir les pinces avec un palai: <ul>
	 * - On appelle la methode .rotate(angle int) d'une valeur testé positive. </ul> <ul>
	 * - On fixe les deux attribut boolean {@link #etatContenu} sur <i> false </i> et {@link #etatPinces} sur <i> true </i> puisqu'on vient d'ouvrir les pinces et elles avaient un palai.</ul>
	 * @see lejos.robotics.RegulatedMotor La doc officielle du RegulatedMotor pour la fonction .rotate(int angle).
	 */
	public void shrekOuvrePincesAvecPalai(){
		this.pinces.rotate(500);
		this.etatPinces = true ;
		this.etatContenu = false ;


	}

	
	/**
	 * Methode pour ouvrir les pinces avec un palai d'un angle entré en parametre: <ul>
	 * - On appelle la methode .rotate(int angle) avec la valeur rentré en parametre. </ul> <ul>
	 * - On fixe {@link #etatPinces} sur <i> true </i> et {@link #etatContenu} sur <i> false </i> </ul>
	 * @param angle <p>
	 * @see lejos.robotics.RegulatedMotor La doc officielle du RegulatedMotor pour la fonction .rotate(int angle).
	 */
	public void shrekOuvrePincesAvecPalai(int angle ){
		this.pinces.rotate(angle);
		this.etatPinces = true ;
		this.etatContenu = false ;


	}


	
	/**
	 * Methode permettant de fermer les pinces en laissant les fonctions suivantes s'exécuter. C'est a dire qu'il y a un retour immediat apres l'exécution de cette methode: <ul>
	 * - On appelle la methode .rotate(int angle, boolean b) avec un l'inverse de l'angle rentré en parametre, et le boolean sur <i> true </i> indiquant que la fermeture doit être <b>synchrone</b> avec l'instruction suivante. </ul>
	 * @param angle <p>
	 * @see lejos.robotics.RegulatedMotor La doc officielle du RegulatedMotor pour la fonction .rotate(int angle, boolean immediateReturn).
	 */
	public void closeWhile( int angle) {
		this.pinces.rotate(-angle, true);
	}
	
	/**
	 * Methode permettant d'ouvrir les pinces en laissant les methodes suivantes s'éxécuter: <ul>
	 * - On appelle la methode .rotate(int angle, boolean b) avec l'angle rentré en parametre, et le boolean sur <i> true </i> indiquant que la fermeture doit être <b>synchrone</b> avec l'instruction suivante. </ul>
	 * @param angle <p>
	 * @see lejos.robotics.RegulatedMotor La doc officielle du RegulatedMotor pour la fonction .rotate(int angle, boolean immediateReturn).
	 */
	public void openWhile(int angle) {
		this.pinces.rotate(angle, true);
	}




	
	/**
	 * Methode permettant de savoir si les pinces sont en mouvements.
	 * 
	 * @return <i> true</i> si les pinces sont en mouvements, <i> false </i> sinon. <p>
	 */
	public boolean areMoving() {
		return this.pinces.isMoving();
	}

}























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


// Cette class represente toutes les actions possibles des moteurs de Shrek
// Elle herite donc de ShrekQuiBouge
// On rajoute le moteur qui reste, celui des Pinces
//on va ensuite definir les methodes des pinces
//=>
// Les pinces ont un etat il faut pouvoir le renvoyer => Ouverte ou fermée
//Les pinces ont un contenue il faut pouvoir l'indiquer => Avec un palai ou sans palai
// Les pinces peuvent se fermer
// Les pinces peuvent s'ouvrir  


public class ShrekMoteurs extends ShrekQuiBouge  {
	
	// attribut
	
	
	RegulatedMotor pinces; // L'attribut pinces pour instancier une pince
	
	 private boolean etatPinces ; // true = ouverte, false = fermée
	
	private boolean etatContenu ; // true = possede un palai, false = pas de palai
	
	
	//Constructeur

	// dans le main instancier les pinces avec unr egulated motor 
	public ShrekMoteurs (ShrekBoussole B ) {    //, RegulatedMotor P) {
		super(B);
		//this.pinces = P;
		Port motorc = BrickFinder.getDefault().getPort("C");
		this.pinces = new EV3LargeRegulatedMotor(motorc);
		System.out.println("cestbon");

	}

	///////////////////////////////////////////////////////////////////////////////////////////////////

	// Methode boolean shrekPincesEtat
	// renvoie true si les pinces sont ouvertes
	//renvoie false si les pinces sont fermées 

	public boolean shrekPincesEtat() {
		return this.etatPinces ;
	}

	///////////////////////////////////////////////////////////////////////////////////////////////////


	// retourne true si les pinces ont un palai
	//false si les pinces sont vides 
	
	public boolean shrekPincesContenu() {
		return this.etatContenu ;
	}

	///////////////////////////////////////////////////////////////////////////////////////////////////

	 // shrekFermePincesSansPalai 
	
// ferme les pinces sans avoir de palai
	// ATTENTION
	// l'angle de fermeture est à verifier pour avoir ça de maniere optimal
	
	public void shrekFermePincesSansPalai() {
		pinces.rotate(-50); // A VERIFIER / MESURER POUR MEILLEUR VALEUR 
		this.etatPinces = false ;
	}
	
	// Ferme les pinces d'un angle rentré en parametre
	// peut etre mieu comme ça 
	
	
	public void shrekFermePincesSansPalai(int angle ) {
		pinces.rotate(-angle) ; // 
		this.etatPinces = false ;
	}
	
	
	
// FERMER LES PINCES AVEC UN PALAI 
	
	public void shrekFermePincesAvecPalai() {
		pinces.rotate(-50); // A VERIFIER / MESURER POUR MEILLEUR VALEUR 
		this.etatPinces = false ;
		this.etatContenu = false ;
	}
	
	
	public void shrekFermePincesAvecPalai(int angle ) {
		pinces.rotate(-angle); // A VERIFIER / MESURER POUR MEILLEUR VALEUR 
		this.etatPinces = false ;
		this.etatContenu = false ;
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////////

	
	// OUVRIR PINCES SANS PALAI 
	// MEME CHOSE FAIRE DES TESTS POUR I	
	
	
	public void shrekOuvrePincesSansPalai(){
		this.pinces.rotate(50);
		this.etatPinces = true ;
		
		
		
	}
	
	public void shrekOuvrePincesSansPalai(int angle){
		this.pinces.rotate(angle);
		this.etatPinces = true ;
		
		
		
	}
	
	// OUVRIR LES PINCES AVEC UN PALAI
	
	public void shrekOuvrePincesAvecPalai(){
		this.pinces.rotate(50);
		this.etatPinces = true ;
		this.etatContenu = true ;
		
		
	}
	

	public void shrekOuvrePincesAvecPalai(int angle ){
		this.pinces.rotate(angle);
		this.etatPinces = true ;
		this.etatContenu = true ;
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public static void main(String[] args) {
		
		ShrekBoussole Boussole = new ShrekBoussole();
	//	System.out.println("cestbon");
	//	RegulatedMotor pinces = new EV3LargeRegulatedMotor(MotorPort.C);
		//pinces.close();
	//	pinces.rotate(-1000);
		//Delay.msDelay(100);
   
		
	
		
	//ShrekMoteurs Shrek = new ShrekMoteurs( Boussole, pinces) ;
		
		ShrekMoteurs Shrek = new ShrekMoteurs( Boussole);
		
	//Shrek.shrekAvance(2);
	
	//ShrekQuiBouge Shrek = new ShrekQuiBouge ( Boussole);
	//Shrek.shrekAvance(2);
	Shrek.shrekOuvrePincesSansPalai();
	}

	}


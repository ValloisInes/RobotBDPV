package test;

import lejos.robotics.navigation.MovePilot ;
import lejos.hardware.motor.Motor;
import lejos.robotics.chassis.Chassis;
import lejos.robotics.chassis.Wheel;
import lejos.robotics.chassis.WheeledChassis;



public class ShrekQuiBouge { // cette classe regroupe tout les actions relatives aux deplacements physique que Shrek peut et va effectuer
	// il y aura avancer
	// reculer
	//tourner droite / gauche
	// faire une courbe
	// recuperer une position (relative a la boussole)
	// renvoyer une position (relative a la boussole)
	//accelerer
	//descelerer 
	//Modifier la vitesse 
	//Demarrer le robot
	// Arreter l'action du robot 
	
//@PP
	
	
	
private MovePilot Shrek ; // cette class aura  besoin d'un pilote normalement 

//private double angle; // potentielle angle pour tourner

// ==> FInalement ça sera gerer dans les methodes directement



//private double distance ; // potentielle distance a parcourir

//==> Finalement ça sera aussi geré dans les methodes directement 

private ShrekBoussole Boussole ; // Boussole de Shrek cf Class Boussole






// Constructeur de ShrekQuiBouge, on instance un pilot et une boussole, on place la position de depart  a 0 
// A MODIFIER LA BOUSSOLE EN FUR ET A MESURE 
public ShrekQuiBouge (ShrekBoussole B) {
	
//	this.Shrek = p;
	Wheel leftWheel = WheeledChassis.modelWheel(Motor.B,0.056).offset(-0.06075); 
	Wheel rightWheel = WheeledChassis.modelWheel(Motor.A, 0.056).offset(0.06075);
	Chassis chassis = new WheeledChassis(new Wheel[] {leftWheel, rightWheel}, WheeledChassis.TYPE_DIFFERENTIAL);
	Shrek = new MovePilot(chassis);
	this.Boussole = B ;
	Boussole.PositionDepart = (double) 0 ; // a voir si on regle pas la position de depart directement dans la class boussole 
}



//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


// AVANCER / RECULER


// Shrek avance d'une distance en metre rentrée en parametre

public void shrekAvance (double distance ) {
	Shrek.travel(distance);
}


// Shrek recule d'une distance rentré en parametre
public void shrekRecule(double distance) {
	Shrek.travel(- distance);
}



//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


// TOURNER / COURBER



//Shrek tourne vers la gauche, l'angle et rentré en parametre en °
public void shrekTourneGauche (double angle) {
	Shrek.rotate(angle);
}


//Shrek tourne vers la droite d'un angle rentré en parametre
public void shrekTourneDroite ( double angle) {
	Shrek.rotate(-angle);
}

//Shrek avance en effectuant une courbe 
// Si le radius est de 0 la rotation est sur place 
// 

public void shrekCourbeAvant (double radius , double angle) {
	Shrek.travelArc( radius ,  angle) ;
		
	}

// sensé effectuer un angle via le radius, semble mieu fonctionner que ShrekCourbeAvant, plus de test necessaire 
public void shrekarc (double radius, double angle2) {
	Shrek.arc(radius, angle2);
}



//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

// MODIFICATION DE POSITION

// modifie dans la boussole de SHrek la position actuelle 

public double shrekUpdatePosition (double position) {
	return this.Boussole.PositionActuelle + position ;
}


//Renvoyer la position actuelle de SHrek

public double  shrekGivePosition()     { //(ShrekQuiBouge S) {
	return this.Boussole.PositionActuelle ;
}

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	




// ACCELERATION / DESCELLERATION

// Shrek accelere d'une certaine valeur, s'additionnant a la vitesse actuelle

// ATTENTION : il s'agit de l' ACCELERATION de debut et fin d'action, PAS de la vitesse
public void shrekAccelere (double acceleration) {
	double accelerationactuelle = this.Shrek.getLinearAcceleration();
	this.Shrek.setLinearAcceleration(accelerationactuelle + acceleration);
}
	

//Shrek DESCELERE d'une valeur, toujours a propos d'acceleration et non de vitesse
public void shrekDescelere( double desceleration) {
	double accelerationactuelle =this.Shrek.getLinearAcceleration();
	this.Shrek.setLinearAcceleration(accelerationactuelle - desceleration);
}




//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////






// VARIATION VITESSE

// Additionne la vitesse actuelle du robot avec une valeur rentré en parametre, modifie la VITESSE du robot 
public void shrekSpeeding (double vitessePlus) {
	double vitesseactuelle = this.Shrek.getLinearSpeed();
	this.Shrek.setLinearSpeed(vitesseactuelle + vitessePlus);
}
	
// soustrait la vitesse actuelle avec une vitesse rentrée en parametre 
public void shrekNoMoreSpeed (double vitesseMoins) {
	double vitesseactuelle = this.Shrek.getLinearSpeed();
	this.Shrek.setLinearSpeed(vitesseactuelle + vitesseMoins);
}
	



//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

// DEMARAGE ROBOT

// On indique que Shrek ira en avant tant qu'il se deplacera

public void shrekEnAvant() {
	this.Shrek.forward();
}

// On indique que Shrek ira en arriere
public void shrekEnArriere() {
	this.Shrek.backward();
}



//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

// ARRET DU ROBOT


//Shrek arrete tout et decide de faire une pause 

public void shrekStop() {
	this.Shrek.stop();
}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

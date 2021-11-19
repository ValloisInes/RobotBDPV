package test.test;

public class Shrek {
	// Cette class est la class mere regroupant les class ShrekCapteurs et ShrekMoteurs
	// c'est dans cette class que nous allons effectuer les fonctions avancées
	
	//Shrek devra être capable de retourner un palai dans notre enbut (validation derriere la marque blanche d'un palai)
	
	
	
	
	
//@Valois
	//@PP
	
	private ShrekBoussole boussole ;
	public ShrekMoteurs m;
	public ShrekCapteurs c ;
	
	public Shrek() {
		boussole = new ShrekBoussole() ;
		m= new ShrekMoteurs(boussole) ;
		c = new  ShrekCapteurs();
		
	}
	
	
	
	
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//FONCTIONS 
	
	//m= ShrekMoteurs
	//boussole = ShrekBoussole
	//c = ShrekCapteurs 

	
	//Shrek est sensé etre sur le millieu de la ligne blanche, tourné vers notre enbut
	
	// RETOUR DE PALAI
	// avancer de 15 cm
	//ouvrir les palais
	//reculer de 15  cm
	// se retourner
	
	public void shrekQuiMarque(double x, double y, double xdiff) { // heresie liée au coordonné actuelle de Shrek (IL FAUT FAIRE UNE BOUSSOLE ASAP !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!)
	// x et y sont les coordonnées de Shrek sur le plateau
		// xp et yp sont les dimensions en metre du plateau (xp la longueur, yp la largeur)
		//xdiff est la longueure entre la ligne blanche et le bout du plateau (enbut interieur)
		
		
		
		//	this.shrekEnAvant(); // shrek passe en mod avant

if (this.c.couleur()==6) {
		
		this.m.shrekAvance(0.15);;// Shrek avance de 15 cm vers l'enbut
		this.m.shrekOuvrePincesAvecPalai(); // Shrek libere le palai en ouvrant ses pinces, le boolean du palai passe a false (plus de palai)
		//	this.shrekEnArriere(); // shrek passe en mod arriere
		this.m.shrekRecule(0.15);//Shrek recule de 0.15 cm pour revenir a la ligne blanche

		// ici on peut fermer les pinces en simultané pendant la rotation 

		this.m.shrekTourneDroite(180); // shrek fait un demi tour pour de positionner vers les palais 

	}
else {
	 ;//  compteur pour avancer 

	while(this.c.couleur()!=6 || (x !=xdiff)){ // couleur == blanc  ou alors x de shrek est different de 
		
			
		
		this.m.shrekAvance(x-xdiff);
		this.shrekQuiMarque(x, y, xdiff);
		
		
		}
		
	}
}

	
	

}


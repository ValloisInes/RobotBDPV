package test;
import lejos.utility.Delay;
import lejos.robotics.navigation.MovePilot ;
import lejos.hardware.BrickFinder;
import lejos.hardware.motor.EV3LargeRegulatedMotor;

/**
 * Class principale du programme. Elle sert à: <ul>
 * - Instancier les classes {@link ShrekMoteurs2}, {@link ShrekCapteurs2}, {@link ETAT}. </ul> <ul>
 * - Utiliser des methodes plus avancées, necessaires à nos strategies. </ul>
 * @author Pierre Parouty, Inès Vallois <p>
 * @see ShrekCapteurs2 
 * @see ShrekMoteurs2 
 *
 */
public class Shrek2 {

	/**
	 * Attribut permettant d'instancier un {@link ShrekMoteurs2}, pour ensuite pouvoir utiliser ses methodes.
	 * @see ShrekMoteurs2
	 */
	private ShrekMoteurs2 m;
	
	/**
	 * Attribut permettant d'instancier un {@link ShrekCapteurs2}, pour ensuite utiliser ses methodes.
	 * @see ShrekCapteurs2
	 */
	private ShrekCapteurs2 c ;
	
	/**
	 * Attribut pour instancier la base d' {@link ETAT} du robot.
	 * @see ETAT
	 */
	private ETAT e;


	/**
	 * Il s'agit d'un compteur de palai, utilisé dans la boucle de la grande methode {@link #Run()}. A chaque palai gagné le compteur prend +1.
	 * @see #test.Shrek2.Run()
	 */
	private int compteur ;


	/**
	 * Attribut qui enregistre la <b>direction</b> du robot. Cet attribut agira comme une boussole pour le robot: A <b>0</b> le robot pointe vers l'enbut.
	 * 
	 */
	private float position ;

	/**
	 * attribut distance qui est utilisé et renvoyé par la methode {@link #rechercheGraalOptimisePalai3EtPlus()}.
	 * @see #rechercheGraalOptimisePalai3EtPlus()
	 * @see #updatePosition(float)
	 */
	private float d ; 

	/**
	 * Constructeur de la class {@link Shrek2} : <ul>
	 * - Il initialise les attributs {@link #m} et {@link #c} en appelant les constructeurs respectifs de {@link ShrekMoteurs2} et {@link ShrekCapteurs2}.</ul> <ul>
	 * - Il initialise la base d'ETAT {@link #e} en utilisant le constructeur de la class {@link ETAT}. </ul> <ul>
	 * - Il initialise {@link #d} sur 0 car il sera changé plus tard, la {@link #position} sur <b>0</b> car on commence en direction de l'enbut, et le {@link #compteur} de palai sur <b>0</b> car nous n'avons pas encore de palai marqué à ce stade du jeu. </ul>
	 * @see ShrekCapteurs2#ShrekCapteurs2() le constructeur de la class ShrekCapteurs2 
	 * @see ShrekMoteurs2#ShrekMoteurs2() le constructeur de la class ShrekMoteurs2 
	 * 
	 */
	public Shrek2() {
		m= new ShrekMoteurs2() ;
		c = new  ShrekCapteurs2();
		d=0;
		e= new ETAT();
		position=0;
		compteur =0;

	}



	/**
	 * Methode qui permet de recuperer l'attribut {@link #m} qui est un ShrekMoteurs2, pour ensuite pouvoir exploiter ses methodes.
	 * @return {@link #m} <p>
	 * @see {@link ShrekMoteurs2}
	 */
	public ShrekMoteurs2 getShrekMoteurs2() {
		return this.m;
	}

	/**
	 * Methode qui permet de recuperer le ShrekCapteurs2 donc l'attribut {@link #c} qui gère les capteurs, pour ensuite pouvoir les exploiter.
	 * @return {@link #c} <p>
	 * @see {@link ShrekCapteurs2}
	 */
	public ShrekCapteurs2 getShrekCapteurs2() {
		return this.c;
	}

	/**
	 * Methode qui permet de recuper l'attribut distance d.
	 * @return {@link #d} <p>
	 * @see #rechercheGraalOptimisePalai3EtPlus()
	 * @see #d
	 */
	public float getd() {
		return this.d;
	}

	/**
	 * Methode pour recuperer l'attribut {@link #e}e, permettant d'utiliser les <b>methodes</b> de la class {@link ETAT}, utilisées dans la grande methode {@link #Run()}.
	 * @return {@link #e} <p>
	 * @see #e 
	 * @see {@link ETAT}
	 * @see #Run()
	 */
	public ETAT getETAT() {
		return this.e;
	}
	
	/**
	 * Getteur de l'attribut {@link #position}.
	 * @return {@link #position} <p>
	 * @see #position
	 */
	public float getPosition() {
		return this.position;
	}

	/**
	 * Methode pour recuperer la valeurs du {@link #compteur} de palai.
	 * @return {@link #compteur} <p>
	 * @see #compteur
	 */
	public int getCompteur() {
		return this.compteur;
	}




	/**
	 * Methode qui prend en parametre une distance et qui l'affecte à l'attribut {@link #d}.
	 * @param distance <p>
	 * @see #rechercheGraalOptimisePalai3EtPlus()
	 * @see #d l'attribut d
	 */
	public void setD(float distance) {
		this.d=distance;
	}


	/**
	 * Methode qui fait partie du "kit Boussole" du robot. Celle-ci permet de mettre a jour la {@link #position}: <ul>
	 * - On prend un parametre un angle de rotation (rotation qui vient d'être effectué </ul> <ul>
	 * - Puis on l'ajoute à la valeur actuelle de l'attribut {@link #position}. </ul>
	 * 
	 * @param angleTurned <p>
	 * @see #position
	 * @see #rechercheGraalOptimisePalai2()
	 * @see #rechercheGraalOptimisePalai3EtPlus()
	 * @see #quiMarque2EtPlus(double, double)
	 */
	public void updatePosition(float angleTurned) {
		this.position= this.position+ angleTurned;
	}




	/**
	 * Methode du "kit Boussole" du robot. Elle permet de se <b>repositionner</b> en direction de l'enbut (donc de remettre l'attribut {@link #position} à 0)
	 * @see #updatePosition(float)
	 * @see #position
	 */
	public void retour0() {
		double d= (this.getPosition()*100)%100;
		if(this.getPosition()<=360) {
			this.getShrekMoteurs2().getShrekQuiBouge().shrekRotateWhile(-this.getPosition());

		}

		else {
			this.getShrekMoteurs2().getShrekQuiBouge().shrekRotateWhile(-(this.getPosition()%360+0.01*d));
		}
	}





	/**
	 * Methode du "kit Boussole". Elle permet de réinitialiser la Boussole en affectant à l'attribut {@link #position} la valeur <b> 0 </b> donc la direction de l'enbut.
	 * @see #position
	 */
	public void setPosition0() {
		this.position=0;
	}



	/**
	 * Methode avancée appelée lorsque le robot est dans l' {@link ETAT} du robot est <b>"premierPalai"</b>: <ul>
	 * - Lorsque que la couleur captée par {@link ShrekCouleurs#getColor()} est le <b>blanc</b> (ID du blanc=6), le robot <b>avance</b> de 15cm, <b>ouvre ses pinces</b> pour libère le palet, <b>recule</b> de 15cm, <b>referme ses pinces en se repositionnant</b> vers les palets </ul> <ul>
	 * - L'ouvertur et la fermeture des pinces est d'un angle de <b>310</b>. L'angle fut defini par des tests, et est très correct. </ul> <ul>
	 * - On parametre la <b>vitesse de rotation à 50 </b> pour ne surtout pas risquer de se décaler et <b>fausser</b> la boussole à cause de la vitesse.  </ul> <ul>
	 * - Pour se repositionner en direction des palets, on tourne vers la <b> droite</b> de 180 - <b> 14 </b> ° puisque dans la methode {@link #testPremierPalai()} on effectue une rotation vers la droite de 14° pour ramener le palet en prenant une diagonale. </ul> <ul>
	 * - on appellele la methode {@link #updatePosition(float)} avec un angle negatif (puisqu'on a tournée vers la droite) pour garder la boussole à jour. </ul> <ul>
	 * - On vient de marquer un palai alors on met a jour le {@link #compteur}.</ul>
	 * @see #testPremierPalai()
	 * @see #updatePosition(float)
	 * @see {@link ShrekQuiBouge#shrekTourneDroite(double)}
	 * 
	 */
	public void quiMarquePremierPalai(){ 
		if (this.getShrekCapteurs2().getCapteurCouleurs().getColor()==6 ){		
			this.getShrekMoteurs2().getShrekQuiBouge().shrekAvance(0.15);			
			this.getShrekMoteurs2().getShrekPinces().shrekOuvrePincesAvecPalai(310);			
			this.getShrekMoteurs2().getShrekQuiBouge().shrekRecule(0.15); 	
			System.out.println("tout s'est bien passé");
			this.getShrekMoteurs2().getShrekPinces().closeWhile(310);
			this.getShrekMoteurs2().getShrekQuiBouge().getPilot().setAngularSpeed(50);
			this.getShrekMoteurs2().getShrekQuiBouge().shrekTourneDroite(180-14); // shrek fait un demi tour + angle de rotation fonction premier palai  pour de positionner vers les palais  
			this.updatePosition(-180);
			this.compteur= this.getCompteur() + 1;

			return ;
		}
	}

	/**
	 * Methode avancée appelée lorsque le robot doit marquer un palet (validation derriere la ligne blanche).<ul>
	 * - Lorsque que la couleur captée par {@link ShrekCouleurs#getColor()} est le <b>blanc</b> (ID du blanc=6), le robot <b>avance</b> de 15cm, <b>ouvre ses pinces</b> pour libère le palet, <b>recule</b> de 15cm, <b>referme ses pinces en se repositionnant</b> vers les palets </ul> <ul>
	 * - L'ouvertur et la fermeture des pinces est d'un angle de <b>310</b>. L'angle fut defini par des tests, et est très correct. </ul> <ul>
	 * - Lors du repositionnement on a tourné à droite de 180°, il faut donc mettre a jour l'attibut {@link #position} avec un angle de -180°.</ul> <ul>
	 * - Il faut ensuite mettre un jour le {@link #compteur} puisqu'un palai vient d'être validé. </ul> <ul>
	 * - Et réinitialiser l'attribut {@link #d distance palet-robot renvoyé par la recherchePalet3EtPlus} </ul>
	 * @see #updatePosition(float)
	 * @see {@link ShrekQuiBouge#shrekTourneDroite(double)}
	 * @see {@link ShrekCouleurs#getColor()}
	 */
	public void quiMarque2EtPlus(){
		if (this.getShrekCapteurs2().getCapteurCouleurs().getColor()==6 ){			
			this.getShrekMoteurs2().getShrekQuiBouge().shrekAvance(0.15);
			this.getShrekMoteurs2().getShrekPinces().shrekOuvrePincesAvecPalai(310);		
			this.getShrekMoteurs2().getShrekQuiBouge().shrekRecule(0.15);
			System.out.println("tout s'est bien passé");
			this.getShrekMoteurs2().getShrekPinces().closeWhile(310);
			this.getShrekMoteurs2().getShrekQuiBouge().shrekTourneDroite(180); 
			this.updatePosition(-180);
			this.compteur=this.getCompteur()+1;// update boussole
			this.d= 0;
			return ;
		}
	}


	/**
	 * Methode avancée qui permet d'avancer jusqu'a l'activation du {@link ShrekTouch du capteur de touché}: <ul>
	 * - On <b>avance</b> d'une distance arbitraire de 4 mettre (il faut au moin atteindre le palet) en laissant les instructions suivantes s'éxécuter.On n'utilise pas 	 * @see {@link ShrekQuiBouge#shrekEnAvant()} car après test, ici le robot avait tendance à ne pas rouler droit</ul> <ul>
	 * - Pendant que le robot avance on <b>ouvre</b> les pinces d'un angle optimisé de 310°, en laissant les instructions suivantes s'éxécuter. </ul> <ul>
	 * - Pendant que le robot avance et que les pinces s'ouvrent,(que le robot est en mouvement) <b>on examine</b> le capteur de touché. </ul> <ul>
	 * - on appelle ensuite en continu la methode {@link ShrekTouch#updateStatus()} pour verifier la presence d'une potentielle activation du {@link ShrekTouch capteur de touché}. </ul> <ul>
	 * - Si le capteur s'est activé, alors on arrete le robot avec la methode {@link ShrekQuiBouge#shrekStop()} puis on sort de la methode. </ul>
	 * @see {@link ShrekTouch#updateStatus()}
	 * @see {@link ShrekTouch#estTouche()}
	 * @see {@link ShrekQuiBouge#shrekMoveWhile(double)}
	 * @see {@link ShrekQuiBouge#shrekStop()}
	 * @see {@link ShrekQuiBouge#shrekIsMoving()}
	 * @see {@link ShrekPinces#openWhile(int)}
	 */
	public void testpince3() {
		boolean temp=false;
		this.getShrekMoteurs2().getShrekQuiBouge().shrekMoveWhile(4);
		this.getShrekMoteurs2().getShrekPinces().openWhile(310);
		while ((this.getShrekMoteurs2().getShrekQuiBouge().shrekIsMoving() ) && (this.getShrekCapteurs2().getCapteurTouche().estTouche()!=true)){
			while(temp !=true) {
				temp =this.getShrekCapteurs2().getCapteurTouche().updateStatus();

			}
			this.getShrekMoteurs2().getShrekQuiBouge().shrekStop();
			return;
		}
	}



	/**
	 * Methode avancée qui permet de recuperer et valider le 1er palai. Lors du tournois, cette methode de toutes,était la plus efficace pour recuperer le 1er palet.: <ul>
	 * - on appelle {@link #testpince3()}, puis on attend la fin de la methode à l'aide d'un while(isMoving) </ul> <ul>
	 * - A la sortie du la methode {@link #testpince3()}, on est face à un palet, on <b>ferme les pinces</b> d'un angle optimisé de 310° en laissant les instructions suivantes s'éxécuter. </ul> <ul>
	 * - <b>Pendant</b> que les pinces ce ferment, on tourne vers la droite de <b>14°</b> (l'angle a été determiné via des tests) </ul> <ul>
	 * - on appelle ensuite la methode {@link #testBut()} pour aller valider le palet. </ul>
	 * @see #testBut()
	 * @see #testpince3()
	 * @see {@link ShrekQuiBouge#shrekIsMoving()}
	 * @see {@link ShrekPinces#closeWhile(int)
	 */
	public void testPremierPalai() {
		this.testpince3();
		while(this.getShrekMoteurs2().getShrekQuiBouge().shrekIsMoving()) {

		}
		this.getShrekMoteurs2().getShrekPinces().closeWhile(310);
		this.getShrekMoteurs2().getShrekQuiBouge().shrekTourneDroite(14);
		this.testBut();


	}

	
	/**
	 * Methode avancée pour ramener le 1er palet dans l'enbut: <ul> 
	 * - on appelle la methode {@link #testligne()} pour avancer jusqu'a la ligne blanche.</ul> <ul>
	 * - On attend la fin de la methode {@link #testligne()} avec la methode {@link ShrekQuiBouge#shrekIsMoving()}. </ul> <ul>
	 * - On appelle ensuite la methode {@link #quiMarquePremierPalai()} pour valider le 1er palet. </ul>
	 * @see {@link ShrekQuiBouge#shrekIsMoving()}
	 * @see #testligne()
	 * @see #quiMarquePremierPalai()
	 */
	public void testBut() {
		this.testligne();
		while(this.getShrekMoteurs2().getShrekQuiBouge().shrekIsMoving()){			
		}
		this.quiMarquePremierPalai();
	}


	/**
	 * Methode avancée qui permet d'avancer jusqu'à la ligne blanche, essentielle au bon deroulement de notre strategie: <ul>
	 * - On avance tant que la couleur captée par le {@link ShrekCouleurs capteur de couleurs} n'est pas du blanc (ID du blanc =6). </ul> <ul>
	 * - Lorsque du blanc est capté, on arrete le robot, puis on sort de la boucle while, permettant au for de se terminer.(puisqu'on est sur la ligne blanche) </ul>
	 * La methode fonctionne correctement, les tests étaient très positif. En revanche, pour plus de <b>securité</b>, si jamais le capteur de couleurs devait devenir obsolete, une seconde verification avec le {@link ShrekUltraSonic capteur de distance} est une chose à rajouter.
	 *@see {@link ShrekQuiBouge#shrekIsMoving()}
	 *@see {@link ShrekCouleurs}
	 *@see {@link ShrekCouleurs#getColor()}
	 *@see {@link ShrekQuiBouge#shrekEnAvant()}
	 *@see {@link ShrekQuiBouge#shrekStop()}
	 *@see #testBut()
	 *@see #retourBut()
	 *
	 *
	 */
	public void testligne() {
		for(int i=0;this.getShrekCapteurs2().getCapteurCouleurs().getColor()!=6;i++) {
			this.m.getShrekQuiBouge().shrekEnAvant();
			while(this.getShrekMoteurs2().getShrekQuiBouge().shrekIsMoving()) {
				int c = this.getShrekCapteurs2().getCapteurCouleurs().getColor();
				if (c==6) {
					this.getShrekMoteurs2().getShrekQuiBouge().shrekStop();
					break;	
				}
			}
		}
	}

	/**
	 * Methode specifique pour aller saisir le palet numero 2. Apres ce palet, les conditions changent et on ne peut plus se permettre d'utiliser {@link #testpince3()}. La methode fonctionne de la maniere suivante: <ul>
	 * - On appelle {@link #testpince3()} pour avancer jusqu'au palet. </ul> <ul>
	 * - On attend que la methode {@link #testpince3()} se termine avant de laisser la methode {@link #vaVersPalai2()} se terminer. </ul>
	 * @see #testpince3() 
	 * @see {@link ShrekQuiBouge#shrekIsMoving()}
	 * @see #Run()
	 */
	public void vaVersPalai2() {
		this.testpince3();
		while(this.getShrekMoteurs2().getShrekQuiBouge().shrekIsMoving()) {

		}
	}



	/**
	 * Methode avancée pour <b>reperer</b> le second palet. Cette methode est <b>particulière</b> car <b>l'angle de recherche est different</b> puisqu'il y a encore (normalement) des palets a proximiter et le robot se trouve vers le <b>milieu</b> de la ligne blanche.<ul>
	 * - On initialise une variable <b>DistanceOpti</b> avec la ditance actuellement  captée par {@link ShrekUltraSonic le capteur de distance} Cette variable servira uniquement dans cette methode à savoir si on est face à un mur ou non. </ul> <ul>
	 * - On initialise une variable <b> angleOpti</b> à <b>0</b>. Cette variable servira à recuperer l'angle où se trouve le palet le plus proche. </ul> <ul>
	 * - On parametre la <b>vitesse de rotation à 50</b> pour gagner en <b>precition </b>lors des mesures de Distances et d'angles.</ul> <ul>
	 * - On va faire une recherche avec un angle de <b>95°</b> (determiné par des tests), il faut dans un premier temps se <b>positionner</b>, donc on fait une premiere rotation vers la <b>gauche d'un angle de 95/2°</b> </ul> <ul>
	 * - On met a jour la Boussole avec la methode {@link #updatePosition(float)}. </ul> <ul>
	 * - On effectue ensuite la recherche avec un <b>balayage vers la droite d'un angle de 95°</b> </ul> <ul>
	 * - Pendant le balayage, on compare la distance stockée dans la variable <b>DistanceOpti</b> avec la valeur mesurée par la methode {@link ShrekUltraSonic#getDistance() getDistance()}. </ul> <ul>
	 * - Si la distance actuellement mesurée par {@link ShrekUltraSonic#getDistance()} est inférieure à la distance stocké dans <b>DistanceOpti</b>, on stock la nouvelle mesure dans <b>DistanceOpti</b>, sinon on garde la valeur precedente. </ul> <ul>
	 * - Si on modifie <b> DistanceOpti</b> on stock également dans <b> angleOpti</b>  avec la methode <i>.getAngleTurned()</i>  l'angle vers lequel le nouvelle objet est orienté, puis on met a jour la boussole via {@link #updatePosition(float)}. </ul> <ul>
	 * - Au moment de revenir à la position initial, on <b>effectue une rotation de l'angle initial + l'angle de l'objet le plus proche</b> pour se positionner en face de l'objet, et on met à jour la Boussole.</ul> <ul>
	 * - Pour finir on verifie que l'on n'est pas face à un mur avec la methode  avec la methode {@link ShrekUltraSonic#shrekFaceAuMur()} : <ul> <p>
	 * - Si elle renvoie <i> true </i> alors on est face à un mur et on effectue une rotation de 180° (pour se mettre dos au mur, on met a jour la Boussole puis on renvoie <b> -1</b>, le reste sera géré dans la methode {@link #Run()}. </ul> <ul>
	 * - Si elle renvoie <i> false </i> on renvoie <b> DistanceOpti </b> qui est une valeurs <b>positive</b>. </ul> </ul>
	 * 
	 * @return -1 si on etait face à un mur, La distance du palet le plus proche sinon. <p>
	 * @see {@link ShrekUltraSonic} 
	 * @see  {@link ShrekUltraSonic#getDistance()} 
	 * @see {@link ShrekUltraSonic#shrekFaceAuMur()} 
	 * @see  {@link ShrekQuiBouge#shrekIsMoving()} 
	 * @see {@link ShrekQuiBouge#shrekTourneGauche(double)} 
	 * @see {@link ShrekQuiBouge#shrekRotateWhile(double)} 
	 * @see #updatePosition(float)
	 * @see {@link lejos.robotics.navigation.MovePilot la doc officielle de MovePilot pour les methodes <i> .setAngularSpeed(double speed) </i> et <i> .getMovement().getAngleTurned() </i>}
	 */
	public float rechercheGraalOptimisePalai2() {
		float DistanceOpti =this.getShrekCapteurs2().getCapteurUltraSonic().getDistance(); 					
		float angleOpti =0 ; 				
		this.getShrekMoteurs2().getShrekQuiBouge().getPilot().setAngularSpeed(50);
		this.getShrekMoteurs2().getShrekQuiBouge().shrekTourneGauche(95/2); // positionnement 		
		this.updatePosition((float)90/2);
		this.getShrekMoteurs2().getShrekQuiBouge().shrekRotateWhile(-95); // droite negatif 
		while(this.getShrekMoteurs2().getShrekQuiBouge().shrekIsMoving()) {				
			if(DistanceOpti> this.getShrekCapteurs2().getCapteurUltraSonic().getDistanceActuelle()) {
				DistanceOpti = this.getShrekCapteurs2().getCapteurUltraSonic().getDistanceActuelle();
				angleOpti=this.getShrekMoteurs2().getShrekQuiBouge().getPilot().getMovement().getAngleTurned();
				this.updatePosition((float)angleOpti);
			}
		}	
		Delay.msDelay(300);
		System.out.println(angleOpti);
		this.getShrekMoteurs2().getShrekQuiBouge().getPilot().rotate(95 + angleOpti);
		this.updatePosition((float)angleOpti+(float)95);
		if(this.getShrekCapteurs2().getCapteurUltraSonic().shrekFaceAuMur()) {
			this.getShrekMoteurs2().getShrekQuiBouge().getPilot().setAngularSpeed(100);
			this.getShrekMoteurs2().getShrekQuiBouge().getPilot().rotate(180);
			this.updatePosition(180);
			return -1;
		}
		else {		
		}
		return DistanceOpti;
	}



	/**
	 * Methode avancée de recherche de palet, utilisée à partir du 3eme palet, puisqu'a partir de ce stade du jeu, nous devons chercher des palets plus profond, et prendre le risque de se disputer les mêmes palets avec le robots ennemi: <ul>
	 * - On avance de 85 cmvers le millieu de terrain </ul> <ul>
	 * - De la même maniere que pour la methode {@link #rechercheGraalOptimisePalai2()} on initialise <b>DistanceOpti et angleOpti</b> </ul> <ul>
	 * - On parametre la <b>vitesse de rotation à 50</b> pour gagner en precision, puis on effectue une r<b>otation vers la droite de 90°</b>, en mettant à jour la Boussole. </ul> <ul>
	 * - La <b>premiere</b> specificité de cette methode, c'est qu'on balaye <b>toujours vers la droite de 90°</b> lors de la recherche. Grace à ça, si on est face à un mur, au mieu en 2 appelle on est dos au mur et la rotation est en direction des palets. </ul> <ul>
	 * - Lors de la rotation c'est le même principe que pour {@link #rechercheGraalOptimisePalai2()}, on compare la distance stockée dans <b> DistanceOpti</b> avec la distance <b>actuellement mesurée</b>, puis on garde la plus petite.</ul> <ul>
	 * - Si on trouve un distance inférieur <b>DistanceOpti</b>, on recupere l'angle avec lequel l'objet à été repéré, puis on le stock dans <b> angleOpti</b> </ul> <ul>
	 * - A la <b>fin</b> du balayage, lors du retour à la position initiale, on effectue une rotation de -90 ° + angleOpti (rotation vers la gauche), et on met a jour la boussole. </ul> <ul>
	 * - La <b>seconde</b> particularité de cette methode, c'est qu'elle affecte à l'attribut {@link #d} la valeurs de <b> DistanceOpti </b>, pour pouvoir avancer de cette distance jusqu'au palai (et non plus en utilisant {@link #testpince3()}, vu qu'on risque d'arriver apres l'autre robot et donc de ne pas activer le capteur Touché) </ul> <ul>
	 * - Pour finir on verifie que l'on n'est pas face à un mur avec la methode  avec la methode {@link ShrekUltraSonic#shrekFaceAuMur()} : <ul> <p>
	 * - Si elle renvoie <i> true </i> alors on est <b>face à un mur</b>.On <b>rentre dans l'etat estDansUnMur</b>, et on <b>sort de l'etat RecherchePalet3EtPlus</b>, puis on return -1. </ul> <ul>
	 * - Si elle renvoie <i> false </i> on renvoie <b> DistanceOpti </b> qui est une valeurs <b>positive</b>. </ul> </ul> </ul>
	 * @return -1 si on est face à un mur, <b> DistanceOpti</b> sinon. <p>
	 * @see {@link ShrekUltraSonic} 
	 * @see  {@link ShrekUltraSonic#getDistance()} 
	 * @see {@link ShrekUltraSonic#shrekFaceAuMur()} 
	 * @see  {@link ShrekQuiBouge#shrekIsMoving()} 
	 * @see {@link ShrekQuiBouge#shrekTourneGauche(double)} 
	 * @see {@link ShrekQuiBouge#shrekRotateWhile(double)} 
	 * @see #updatePosition(float)
	 * @see {@link ETAT} 
	 * @see {@link ETAT#getEstDansUnMur()}
	 * @see {@link ETAT#getRecherchePalai3EtPlus()}  
	 * @see {@link lejos.robotics.navigation.MovePilot la doc officielle de MovePilot pour les methodes <i> .setAngularSpeed(double speed) </i> et <i> .getMovement().getAngleTurned() </i>}
	 */
	public float rechercheGraalOptimisePalai3EtPlus(){ 
		this.getShrekMoteurs2().getShrekQuiBouge().shrekAvance(0.85);
		float DistanceOpti =this.getShrekCapteurs2().getCapteurUltraSonic().getDistance(); 
		float angleOpti =0 ; 
		this.getShrekMoteurs2().getShrekQuiBouge().getPilot().setAngularSpeed(50);		
		this.getShrekMoteurs2().getShrekQuiBouge().shrekRotateWhile(-90); 
		this.updatePosition(-90);
		while(this.getShrekMoteurs2().getShrekQuiBouge().shrekIsMoving()) {		
			if(DistanceOpti> this.getShrekCapteurs2().getCapteurUltraSonic().getDistanceActuelle()) {
				DistanceOpti = this.getShrekCapteurs2().getCapteurUltraSonic().getDistanceActuelle();
				angleOpti=this.getShrekMoteurs2().getShrekQuiBouge().getPilot().getMovement().getAngleTurned();
				this.updatePosition((float)angleOpti);
			}
		}
		Delay.msDelay(30);
		System.out.println(angleOpti);			
		this.getShrekMoteurs2().getShrekQuiBouge().getPilot().rotate(-90 + angleOpti);
		this.updatePosition((float)-90 + angleOpti);	
		this.setD(DistanceOpti);
			if(this.c.getCapteurUltraSonic().shrekFaceAuMur()) {
				this.getETAT().setEstDansUnMur(true);
				this.getETAT().setRecherchePalai3EtPlus(false);
				return -1;
			/*	this.m.getShrekQuiBouge().getPilot().setAngularSpeed(100);
				this.m.getShrekQuiBouge().getPilot().rotate(180);
				this.updatePosition(180);
				return -1;*/
			}
			else {			
			}

		return DistanceOpti;
	}



	/**
	 * Methode qui permet de recuperer un palet: <ul>
	 * - On <b>ferme</b> les pinces avec une rotation optimisée de <b>310</b>, en <b>laissant</b> les instructions suivantes s'éxécuter </ul> <ul>
	 * - Pendant la fermeture des pinces on appelle {@link #retour0()} qui permet de se <b>repositionner</b> en direction de l'enbut. </ul> <ul>
	 * - On attend que le robot ai fini de bouger avant de terminer la methode. (Pour ne pas causer de conflit si cette methode est appelée dans une autre methode). </ul>
	 * @see {@link ShrekQuiBouge#shrekIsMoving()}
	 * @see {@link ShrekPinces#closeWhile(int)}
	 * @see #retour0()  
	 */
	public void recupereUnPalai() {

		this.getShrekMoteurs2().getShrekPinces().closeWhile(310);
		this.retour0();
		while(this.getShrekMoteurs2().getShrekQuiBouge().shrekIsMoving()){			
		}
	}


	/**
	 * Methode qui permet le retour à l'enbut lorsque le robot est deja positionné dans la bonne direction: <ul>
	 * - On appelle la methode {@link #testligne()} pour que le robot avance jusqu'a la ligne blanche. </ul> <ul>
	 * - On <b>attend</b> que le robot s'arrete (donc lorsqu'il est sur la ligne blanche) </ul> <ul>
	 * - On appelle la methode {@link #quiMarque2EtPlus()} pour valider le palet. </ul>
	 * @see #testligne()
	 * @see {@link ShrekQuiBouge#shrekIsMoving()}
	 * @see #quiMarque2EtPlus()
	 */
	public void retourBut() {
		this.testligne();
		while(this.getShrekMoteurs2().getShrekQuiBouge().shrekIsMoving()){		
		}
		this.quiMarque2EtPlus();
	}

	/**
	 * Methode qui permet de corriger la position du robot lorsqu'il est face à un mur : <ul>
	 * - On se repositionne face à l'enbut avec {@link #retour0()}. </ul> <ul>
	 * - On effectue une nouvelle <b>rotation</b> vers la droite de <b>90°</b>, puis on met à jour la Boussole avec {@link #updatePosition(float)}. </ul>
	 * En effectuant des rotations de 90° toujours vers la droite on s'assure que dans la durée le robot finira par trouver tous les palets.<p>
	 * @see {@link ShrekQuiBouge#shrekTourneDroite(double)}
	 * @see #retour0()
	 * @see #updatePosition(float) 
	 */
	public void correctionDuMur() {
		this.retour0();
		this.getShrekMoteurs2().getShrekQuiBouge().shrekTourneDroite(90);
		this.updatePosition(90);
	}

	/**
	 * Methode qui permet d'avancer jusqu'à un palet, precedement reperé par une recherche issue de {@link #rechercheGraalOptimisePalai3EtPlus()}: <ul>
	 * - On avance jusqu'a la distance rentré en parametre (normalement il s'agira de l'attribut {@link #d renvoyé par la recherche de palet3EtPlus}) (on ajoute quelque mm pour s'assurer d'etre bien positionné). </ul> <ul>
	 * - Pendant que le robot se dirige vers le palet, on verifie en permanence qu'on ne se dirige pas vers un mur avec la methode {@link ShrekUltraSonic#shrekFaceAuMur()}. </ul> <ul>
	 * - Si elle renvoie <i> true</i> on rentre dans l'etat estDansUnMur et on sort de l'etatRecherchePalet3EtPlus, sinon on bascule dans l'etat <b>PrendPalet</b> directement dans cette methode. </ul>
	 * @param d <p>
	 * @see #rechercheGraalOptimisePalai3EtPlus()
	 * @see {@link ShrekUltraSonic} 
	 * @see {@link ShrekUltraSonic#shrekFaceAuMur()}
	 * @see {@link ETAT#getEstDansUnMur()} 
	 * @see {@link ShrekQuiBouge#shrekIsMoving()}  
	 * @see {@link ShrekQuiBouge#shrekMoveWhile(double)} 
	 */
	public void vaVersPalai(float d) {
		this.getShrekMoteurs2().getShrekQuiBouge().shrekMoveWhile((double) d+0.06);
		while(this.getShrekMoteurs2().getShrekQuiBouge().shrekIsMoving()) {
		if(this.getShrekCapteurs2().getCapteurUltraSonic().shrekFaceAuMur()==true) {
			this.getETAT().setVaVersPalai(false);
			this.getETAT().setEstDansUnMur(true);
			this.getETAT().setRecherchePalai3EtPlus(false);
			return;
		}
		else {
			this.getETAT().setVaVersPalai(false);
			this.getETAT().setPrendPalai(true);
		}
		}


	}




	/**
	 * C'est la methode qui gère les changements d'etat du robot et qui indique quoi faire en fonction d'un etat donné: <ul>
	 * - Lors de l'etat <b>démarrage </b>, on <b>passe à l'etat PremierPalet</b>, et sort de l'etat démarrage. </ul> <ul>
	 * - Lors de l'etat PremierPalai, on appelle la methode {@link #testPremierPalai()}, puis à sa resolution, on <b>sort</b> de l'etat PremierPalet et on <b>rentre dans l'etat RecherchePalai2.</b> </ul> <ul> <p>
	 * - Dans l'etat recherchepalet2 on appelle la methode {@link #rechercheGraalOptimisePalai2()} <ul>
	 * - Si la methode renvoie une valeurs <b><0</b> (donc -1), alors le robot était <b>face à un mur</b> et la correction est <b>deja effectuée</b>, on relance une recherche de niveau2 avec {@link #rechercheGraalOptimisePalai2()}.</ul> <ul>
	 * - Si la methode renvoie une valeur <b> >0</b> alors le robot à bien <b>detecter un palet</b>, on sort de l'etat recherchepalet2 et on <b>entre dans l'etat VaVersPalai2</b>. </ul> 
	 * - Dans l'etat VaVersPalai2, on appelle la methode {@link #vaVersPalai2()}, puis on sort de l'etat actuelle pour rentrer dans l'etat PrendPalai. </ul> <ul>
	 * - Dans l'état PrendPalai, on verifie que le {@link ShrekTouch capteur de touché} s'est activé : <ul> <p>
	 * - Si il s'est activé (donc {@link ShrekTouch#updateStatus() } renvoie true) alors il y a un palet en face du robot et on lance la recuperation avec la methode {@link #recupereUnPalai()}, puis on bascule dans l'etat retourEnbut </ul> <ul>
	 * - Sinon cela signifie que l'autre robot nous a devancé, on <b>sort de l'etat PrendPalai et on entre dans l'etat pbInconnu</b>, que l'on detaillera à la fin. </ul>
	 * - Dans l'etat retourEnbut, on appelle la methode {@link #retourBut()}, puis sort de l'etat retourEnbut, on <b>regarde le nombre de de palet</b> avec {@link #compteur}, si c'est inferieur à 9 on rentre dans l'etat recherchepalet3etplus, sinon on rentre dans l'etat Victoire. </ul> <ul>
	 * - Dans l'etat RecherchePalet3etPlus, on regarde la valeurs renvoyée par la methode {@link #rechercheGraalOptimisePalai3EtPlus()} : <ul> <p>
	 * - Si il s'agit d'une valeur <b>négative</b>, on switch vers l'etat estDansUnMur (qu'on expliquera plus tard) puis on sort du while (avec un break).</ul> <ul>
	 * - Sinon on bascule vers l'etat <b>VaVersPalai</b>. </ul>
	 * - Dans l'etat VaVersPalai, on appelle la methode {@link #vaVersPalai(float)}, puis on attend la resolution de la methode. Tous le reste est gérée dans la methode {@link #vaVersPalai(float)}. <p>
	 * - Dans l'etat <b>estDansUnMur</b>, on appelle la methode {@link #correctionDuMur()}, puis on rebascule dans un etat de recherchePalet3EtPlus.
	 * - Dans l'etat <b>pbInconnu</b> (normalement le palet reperé n'est plus a sa position lorsque le robot arrive), on retourne dans un etat de recherchepalet3EtPlus pour réeffectuer une recherche de niveau3. </ul>
	 * @see {@link ShrekQuiBouge#shrekIsMoving()} 
	 * @see {@link ETAT}
	 * @see #testBut()
	 * @see #testPremierPalai()
	 * @see #vaVersPalai(float)
	 * @see #vaVersPalai2()
	 * @see #rechercheGraalOptimisePalai2()
	 * @see #rechercheGraalOptimisePalai3EtPlus()
	 * @see #retourBut()
	 * @see {@link ShrekTouch#updateStatus()} 
	 * @see #recupereUnPalai()
	 * @see #correctionDuMur()
	 * 
	 */
	public void Run() {
		while((this.getETAT().getVictoire()==false)&& (this.getShrekCapteurs2().getCapteurCouleurs().getColor()!=7 && (this.getCompteur()!=9))){
			while(this.getETAT().getDemarrage()==true) {
				this.getETAT().setPremierPalai(true);
				this.getETAT().setDemarrage(false);
			}
			while(this.getETAT().getPremierPalai()==true) {
				this.testPremierPalai();
				this.compteur=1;
				this.getETAT().setPremierPalai(false);
				this.getETAT().setRecherchePalai2(true);
			}
			while(this.getETAT().getRecherchePalai2()==true) {
				if(this.rechercheGraalOptimisePalai2()<0) {
					this.rechercheGraalOptimisePalai2();
				}
				
				this.getETAT().setRecherchePalai2(false);
				this.getETAT().setVaVersPalai2(true);
			}
			while(this.getETAT().getVaVersPalai2()==true) {
				this.vaVersPalai2();
				while(this.getShrekMoteurs2().getShrekQuiBouge().shrekIsMoving()) {
				}
				this.getETAT().setVaVersPalai2(false);
				this.getETAT().setPrendPalai(true);
			}
			while(this.getETAT().getPrendPalai()==true) {
				if(this.getShrekCapteurs2().getCapteurTouche().getStatus()){ // on gere le probleme du palet disparu ici 
					this.recupereUnPalai();
					this.getETAT().setPrendPalai(false);
					this.getETAT().setRetourEnbut(true);
				}
				else {
					this.getETAT().setPrendPalai(false);
					this.getETAT().setPbInconnu(true);			
				}			
			}
			while(this.getETAT().getRetourEnbut()==true) {
				this.retourBut();
				this.getETAT().setRetourEnbut(false);
				if(this.getCompteur()==9) {
					this.getETAT().setVictoire(true);
				}
				this.getETAT().setRecherchePalai3EtPlus(true);
			}
			while((this.getETAT().getRecherchePalai3EtPlus()==true) ) {
				if(this.rechercheGraalOptimisePalai3EtPlus()<0) {
					this.getETAT().setRecherchePalai3EtPlus(false);
					this.getETAT().setEstDansUnMur(true);
					break;
				}		
				this.getETAT().setRecherchePalai3EtPlus(false);
				this.getETAT().setVaVersPalai(true);	
			}
			while(this.getETAT().getVaVersPalai()==true) {
				this.vaVersPalai(this.getd());
				while(this.getShrekMoteurs2().getShrekQuiBouge().shrekIsMoving()) {
				}
			}
			while(this.getETAT().getEstDansUnMur()==true) {
				this.correctionDuMur();
				this.getETAT().setEstDansUnMur(false);
				this.getETAT().setRecherchePalai3EtPlus(true);
			}
			while(this.getETAT().getPbInconnu()==true) {
				this.getETAT().setPbInconnu(false);
				this.getETAT().setRecherchePalai3EtPlus(true);
			}
		}
	}




} // class 





























package test;


/**
 * Class regroupant les <b>ETATS </b> possible de Shrek <p>
 * Les differents etats sont représenté par des <b>attributs boolean</b>. Changer la valeurs du boolean permet de switcher entre les etats, pour evoluer dans la methode .Run(). La class permet de: <ul>
 * - Instancier la base de données des etats possibles.</ul> <ul>
 * - Recuperer les valeurs booléennes des attributs representant les etats. </ul> <ul>
 * - Modifier le status d'un etat. </ul> <p>
 * 
 * @author Pierre Parouty<p>
 * @see Shrek2#Run()
 *
 */



public class ETAT {
	
	
	
	
	/**
	 * Etat <b>initial</b> dans lequel Shrek attend de passer à l'etat suivant.
	 */
	private boolean demarrage ;
	
	/**
	 * Etat qui lancera la recuperation du <b>premier palai uniquement</b>. 
	 */
	private boolean premierPalai  ;

	/**
	 * Etat qui lance la recherche du second palai, juste après la validation du premier palai.  
	 */
	private boolean recherchePalai2 ;
	
	/**
	 * Etat specifique pour le palai2, puisque c'est la derniere fois que l'on prendra un palai avec la methode {@link Shrek2#testpince3()} pour raison de securité (apres 2 Palais, le robot ennemi s'interesse peut-etre au même palai, pour plus d'info voir la doc des methodes indiquée dans "see also")
	 * @see Shrek2#rechercheGraalOptimisePalai3EtPlus()
	 * @see Shrek2#rechercheGraalOptimisePalai2()
	 */
	
	private boolean vaVersPalai2;
	
	/**
	 * Etat qui permet d'aller jusqu'au palai detecté par la recherche. Cet Etat fonctionne à partir d'une <b>distance renvoyée</b> par par la recherche et non avec la methode <b>testPinces3</b>
	 */
	private boolean vaVersPalai ;
	
	/**
	 *Etat dans lequel Shrek prend le palai puis se repositionne en direction de l'enbut.
	 */
	private boolean prendPalai ;
	
	/**
	 *Etat qui permet a Shrek d'apporter le palai dans la zone de but.
	 */
	private boolean retourEnbut ;
	
	
	/**
	 * Etat de recherche des palais plus profond. La recherche ne se fait plus depuis l'enBut, mais depuis le tier/milieu de terrain, pour les palais restant.
	 */
	private boolean recherchePalai3etPlus ;
	
	/**
	 *Shrek est face à un mur et il faudra se <b>repositionner</b> avant de retourner dans un etat {@link #recherchePalai3etPlus}
	 */
	private boolean estDansUnMur ;
	

	/**
	 *Shrek rencontre un autre probleme et relance une {@link #recherchePalai3etPlus}
	 *@see Shrek2#rechercheGraalOptimisePalai3EtPlus()
	 */
	private boolean pbInconnuAGerer ;
	
	/**
	 *Etat qui permettra l'arret du programme, car Shrek vient de gagner.
	 */
	private boolean victoire ;
	
	/**
	 *Etat permettant l'arret de Shrek.
	 */
	private boolean stop;
	
	
	

	
	/**
	 * Constructeur de la class ETAT<p>
	 * Tous les attributs sont initialisé à <i>false</i> sauf l'etat {@link #demarrage} qui sera initialisé avec <i> true</i> permettant d'initialiser le programme.
	 */
	public ETAT() {
		this.premierPalai = false;
		this.demarrage=true;
		this.recherchePalai2=false;
		this.vaVersPalai =false;
		this.recherchePalai2=false;
		this.prendPalai=false;
		this.retourEnbut=false;
		this.recherchePalai3etPlus=false;
		this.estDansUnMur=false;
		this.pbInconnuAGerer=false;
		this.victoire=false;
		this.stop=false;
	}
	
	
	
	
	/**
	 * Methode permettant de changer la valeur de l'attribut {@link #premierPalai}, en fonction du boolean rentré en parametre.
	 * @param b
	 */
	public void setPremierPalai(boolean b) {
		this.premierPalai=b;
	}


	/**
	 * Methode permettant de changer la valeur de l'attribut {@link #demarrage}, en fonction du boolean rentré en parametre.
	 * @param b
	 */
	public void setDemarrage(boolean b) {
		this.demarrage=b;
	}
	
	/**
	 * Methode permettant de changer la valeur de l'attribut {@link #recherchePalai2}, en fonction du boolean rentré en parametre.
	 * @param b
	 */
	public void setRecherchePalai2(boolean b) {
		this.recherchePalai2=b;
	}
	
	/**
	 * Methode permettant de changer la valeur de l'attribut {@link #vaVersPalai}, en fonction du boolean rentré en parametre.
	 * @param b
	 */
	public void setVaVersPalai(boolean b) {
		this.vaVersPalai=b;
	}
	
	/**
	 * Methode permettant de changer la valeur de l'attribut {@link #prendPalai}, en fonction du boolean rentré en parametre.
	 * @param b
	 */
	public void setPrendPalai(boolean b) {
		this.prendPalai=b;
	}
	
	/**
	 * Methode permettant de changer la valeur de l'attribut {@link #retourEnbut}, en fonction du boolean rentré en parametre.
	 * @param b
	 */
	public void setRetourEnbut(boolean b) {
		this.retourEnbut=b;
	}
	
	/**
	 * Methode permettant de changer la valeur de l'attribut {@link #recherchePalai3etPlus}, en fonction du boolean rentré en parametre.
	 * @param b
	 */
	public void setRecherchePalai3EtPlus(boolean b) {
		this.recherchePalai3etPlus=b;
	}
	
	/**
	 * Methode permettant de changer la valeur de l'attribut {@link #estDansUnMur}, en fonction du boolean rentré en parametre.
	 * @param b
	 */
	public void setEstDansUnMur(boolean b) {
		this.estDansUnMur=false;
	}
	
	/**
	 * Methode permettant de changer la valeur de l'attribut {@link #pbInconnuAGerer}, en fonction du boolean rentré en parametre.
	 * @param b
	 */
	public void setPbInconnu(boolean b) {
		this.pbInconnuAGerer=b;
	}
	
	/**
	 *  Methode permettant de changer la valeur de l'attribut {@link #victoire}, en fonction du boolean rentré en parametre.
	 * @param b
	 */
	public void setVictoire(boolean b) {
		this.victoire=b;
	}
	
	/**
	 *  Methode permettant de changer la valeur de l'attribut {@link #stop}, en fonction du boolean rentré en parametre.
	 * @param b
	 */
	public void setStop(boolean b) {
		this.stop=b;
	}
	
	/**
	 *  Methode permettant de changer la valeur de l'attribut {@link #vaVersPalai2}, en fonction du boolean rentré en parametre.
	 * @param b
	 */
	public void setVaVersPalai2(boolean b) {
		this.vaVersPalai2=b;
	}
	
	
	

	
	/**
	 * Methode permettant de recuperer le status actuel de l'etat PremierPalai (donc la valeur de l'attribut boolean associé)
	 * @return {@link #premierPalai}
	 */
	public boolean getPremierPalai() {
		return this.premierPalai;
	}


	/**
	 * Methode permettant de recuperer le status actuel de l'etat {@link #demarrage} 
	 * @return {@link #demarrage}
	 */
	public boolean getDemarrage() {
		return this.demarrage;
	}
	
	/**
	 * Methode permettant de recuperer le status actuel de l'etat {@link #recherchePalai2} 
	 * @return {@link #recherchePalai2}
	 */
	public boolean getRecherchePalai2() {
		return this.recherchePalai2;
	}
	
	/**
	 * Methode permettant de recuperer le status actuel de l'etat {@link #vaVersPalai} 
	 * @return {@link #vaVersPalai}
	 */
	public boolean getVaVersPalai() {
		return this.vaVersPalai;
	}
	
	/**
	 * Methode permettant de recuperer le status actuel de l'etat {@link #prendPalai} 
	 * @return {@link #prendPalai}
	 */
	public boolean getPrendPalai() {
		return this.prendPalai;
	}
	
	/**
	 * Methode permettant de recuperer le status actuel de l'etat {@link #retourEnbut} 
	 * @return {@link #retourEnbut}
	 */
	public boolean getRetourEnbut() {
		return this.retourEnbut;
	}
	
	/**
	 * Methode permettant de recuperer le status actuel de l'etat {@link #recherchePalai3etPlus} 
	 * @return {@link #recherchePalai3etPlus}
	 */
	public boolean getRecherchePalai3EtPlus() {
		return this.recherchePalai3etPlus;
	}
	
	/**
	 * Methode permettant de recuperer le status actuel de l'etat {@link #estDansUnMur} 
	 * @return {@link #estDansUnMur}
	 */
	public boolean getEstDansUnMur() {
		return this.estDansUnMur;
	}
	
	/**
	 * Methode permettant de recuperer le status actuel de l'etat {@link #pbInconnuAGerer} 
	 * @return {@link #pbInconnuAGerer}
	 */
	public boolean getPbInconnu() {
		return this.pbInconnuAGerer;
	}
	
	
	/**
	 * Methode permettant de recuperer le status actuel de l'etat {@link #victoire} 
	 * @return {@link #victoire}
	 */
	public boolean getVictoire() {
		return this.victoire;
	}
	
	/**
	 * Methode permettant de recuperer le status actuel de l'etat {@link #stop} 
	 * @return {@link #stop}
	 */
	public boolean getStop() {
		return this.stop;
	}
	
	
	/**
	 * Methode permettant de recuperer le status actuel de l'etat {@link #vaVersPalai2} 
	 * @return {@link #vaVersPalai2}
	 */
	public boolean getVaVersPalai2() {
		return this.vaVersPalai2;
	}
	
	
	
	
	

}

package test;

public class ShrekBoussole { // Boussole interne de Shrek il s'agira de la mettre a jour pour que Shrek puisse se reperer et retourner dans l'enbut
	// IMPORTANT
	
	//// MODIFIER SHREKQUIBOUGE AU FUR ET A MESURE QUE CETTE CLASS AVANCE 
	
	
	public Double PositionDepart; // la position de depart de Shrek, donc probablement notre ligne blanche d'embute 
	
	public Double PositionActuelle; // Position actuelle de SHrek 
	
	
	

		private double direction ;
		
		
		public ShrekBoussole() {
			direction=0;
			PositionDepart = (double) 0;
			PositionActuelle = (double) 0;
		}
		
		
		public void changementAngle(double a) {
			direction=direction+a;
			}
		public double getD() {
			System.out.println(direction);
			return direction;
		}
		

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

package modele;

public class Eveque extends Personnages {

	public Eveque() {
		super("Eveque", 5, Caracteristiques.EVEQUE);
	}

	@Override
	public void utiliserPouvoir() {
		//Pas de pouvoir.
	}
	public void percevoirRessourcesSpecifiques() {
	
		int piecesReligieuses=0;
		for (int i = 0; i < this.getJoueur().nbQuartiersDansCite(); i++) {
			if (this.getJoueur().getCite()[i].getType().equals("RELIGIEUX")) {
			    piecesReligieuses++;
				
			}
		}
		this.getJoueur().ajouterPieces(piecesReligieuses);
	}

}

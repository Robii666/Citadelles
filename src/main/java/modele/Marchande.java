package modele;

public class Marchande extends Personnage {

	public Marchande() {
		super("Marchande", 6, Caracteristiques.MARCHANDE);
	}

	@Override
	public void utiliserPouvoir() {

		this.getJoueur().ajouterPieces(1);
	}

	@Override
	public void utiliserPouvoirAvatar() {
		// TODO Auto-generated method stub
		this.getJoueur().ajouterPieces(1);
	}
	public void percevoirRessourcesSpecifiques() {
	
		//System.out.println(this.getJoueur().nbPieces());
		//System.out.println(this.getJoueur().nbQuartiersDansCite());
		int piecesMarchandes=0;
		for (int i = 0; i < this.getJoueur().nbQuartiersDansCite(); i++) {
			//System.out.println(this.getJoueur().nbPieces());
			//System.out.println(this.getJoueur().getCite()[i].getType());
			if (this.getJoueur().getCite()[i].getType().equals("MARCHANDE")) {
				piecesMarchandes++;
				//System.out.println(this.getJoueur().nbPieces());
			}
		}
		this.getJoueur().ajouterPieces(piecesMarchandes);
	}

}

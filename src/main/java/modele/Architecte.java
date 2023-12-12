package modele;

import java.util.Random;

public class Architecte extends Personnage{
	
	public Architecte() {
		super("Architecte", 6, Caracteristiques.ARCHITECTE);
		this.pioche = new Pioche();
		gen = new Random(100);
	}

	@Override
	public void utiliserPouvoir() {
		this.getJoueur().ajouterQuartierDansMain(pioche.piocher());
		this.getJoueur().ajouterQuartierDansMain(pioche.piocher());
	}

	@Override
	public void utiliserPouvoirAvatar() {
		int choix = gen.nextInt();
		if (choix%2==0) {
			this.getJoueur().ajouterQuartierDansMain(pioche.piocher());
			this.getJoueur().ajouterQuartierDansMain(pioche.piocher());
		}
	}

}
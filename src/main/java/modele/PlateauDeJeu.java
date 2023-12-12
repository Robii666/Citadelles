package modele;

public class PlateauDeJeu {
	private Personnage[] listePersonnage;
	private Joueur[] listeJoueurs;
	private Pioche pioche;
	private int nombrePersonnages, nombreJoueurs;

	
	public PlateauDeJeu() {
		listePersonnage = new Personnage[9];
		listeJoueurs = new Joueur[4];
		pioche=new Pioche();
		nombreJoueurs =0;
		nombrePersonnages=0;
	}
	public void plateauDeJeu() {
		
	}
	public void ajouterPersonnage(Personnage nouveau) {
		int i = 0;
		while (listePersonnage[i] != null &&  i<9) {
			i++;
		}
		if (i>=0 && i<9) {
			listePersonnage[i] = nouveau;
			nouveau.setPlateau(this);
			nombrePersonnages++;
		}
	}
	public Personnage getPersonnage(int i) {
		return this.listePersonnage[i];
		
	}
	public void ajouterJoueur(Joueur nouveau) {
		int i = 0;
		while (listeJoueurs[i] != null &&  i<4) {
			i++;
		}
		if (i>=0 && i<4) {
			listeJoueurs[i] = nouveau;
			nombreJoueurs++;
		}
	}
	public Joueur getJoueur(int i) {
		return this.listeJoueurs[i];
		
	}
	public Joueur[] getListeJoueur() {
		return this.listeJoueurs;
		
	}
	public int getNombrePersonnages() {
		return nombrePersonnages;
	}
	public int getNombreJoueurs() {
		return nombreJoueurs;
	}
	public Pioche getPioche() {
		return pioche;
	}
	public void ajouterPioche(Pioche pioche) {
		this.pioche=pioche;
	}

}

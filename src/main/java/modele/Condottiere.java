package modele;

import java.util.Iterator;
import java.util.Random;

import controleur.Interaction;

public class Condottiere extends Personnage{

	public Condottiere() {
		super("Condottiere", 8, Caracteristiques.CONDOTTIERE);
		this.interaction = new Interaction();
		gen = new Random(10);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void utiliserPouvoir() {
		// TODO Auto-generated method stub
		boolean reponse = true;
		int i = 1;
		int j = 1;
		int q = 1;
		int quartierSelectionne = 0;
		int joueurSelectionne = 0;
		System.out.print("Voulez-vous utiliser votre pouvoir de destruction ? : ");
		reponse = interaction.lireOuiOuNon();
		if (reponse) {
			System.out.println("Voici la liste des joueurs et le contenu de leur cite :");
			
			for (int a = 0; a < this.getPlateau().getNombreJoueurs(); a++) {
				System.out.print(i+" "+this.getPlateau().getJoueur(a).getNom()+" : ");
				for (int k = 0; k <this.getPlateau().getJoueur(a).nbQuartiersDansCite(); k++) {
					System.out.print(j+" "+this.getPlateau().getJoueur(a).getCite()[k].getNom()+" (cout "+this.getPlateau().getJoueur(a).getCite()[k].getCout()+" ) , ");
					j++;
					q++;
				}
				i++;
				j=1;
				System.out.println(" ");
			}
			System.out.println("Pour information, vous avez "+ this.getJoueur().nbPieces() +" pieces d�or dans votre tresor");
			System.out.println("Quel joueur choisissez-vous ? (0 pour ne rien faire) ");
			joueurSelectionne = interaction.lireUnEntier(0,i);
			System.out.println(this.getPlateau().getJoueur(joueurSelectionne-1).getNom());
			
			
			
			System.out.println(" Quel quartier choisissez-vous ? ");
			do {
				System.out.println("Votre choix : ");
				quartierSelectionne = interaction.lireUnEntier(0,q);
				if (this.getJoueur().nbPieces() < this.getPlateau().getJoueur(joueurSelectionne-1).getCite()[quartierSelectionne-1].getCout()) {
					System.out.println("Votre tresor n�est pas suffisant");
				}
			} while (this.getJoueur().nbPieces() < this.getPlateau().getJoueur(joueurSelectionne-1).getCite()[quartierSelectionne-1].getCout());
			System.out.println(this.getPlateau().getListeJoueur()[joueurSelectionne-1].getCite()[quartierSelectionne-1].getNom() +" En cours de retrait � "+this.getPlateau().getListeJoueur()[joueurSelectionne-1].getNom());
			this.getPlateau().getListeJoueur()[joueurSelectionne-1].retirerQuartierDansCite(this.getPlateau().getListeJoueur()[joueurSelectionne-1].getCite()[quartierSelectionne-1].getNom());
			System.out.println(" => Retrait avec succes !!!!");
			
		}
	}
	public void percevoirRessourcesSpecifiques() {
		
		for (int i = 0; i < this.getJoueur().nbQuartiersDansCite(); i++) {
			if (this.getJoueur().getCite()[i].getType().equals("MILITAIRE")) {
				this.getJoueur().ajouterPieces(1);
			}
		}
	}
}

package modele;

import java.util.Random;

import controleur.Interaction;

public class Voleur extends Personnage{

    public Voleur() {
        super("Voleur", 2, Caracteristiques.VOLEUR);
        this.interaction = new Interaction();
        gen = new Random(8);
    }

    @Override
    public void utiliserPouvoir() {
        System.out.println(this.getJoueur().getNom());
        System.out.println(this.getJoueur().nbPieces());
        int choix = 0;
        boolean condition = true;
        System.out.println("1 : Assassin");
        System.out.println("2 : Voleur");
        System.out.println("3 : Eveque");
        System.out.println("4 : Roi");
        System.out.println("5 : Magicienne");
        System.out.println("6 : Condottiere");
        System.out.println("7 : Marchande");
        System.out.println("8 : Architecte");

        do {
            System.out.print("Votre choix : ");
            choix=interaction.lireUnEntier();

            if (choix > 2 && choix <= 8) {
                int i = 0;
                do {
                    if (this.getPlateau().getPersonnage(i).getNom().equals(tableauPersonnage[choix-1])){
                        this.getPlateau().getPersonnage(i).setVole();
                        this.getJoueur().ajouterPieces(this.getPlateau().getPersonnage(i).getJoueur().nbPieces());
                        this.getPlateau().getPersonnage(i).getJoueur().retirerPieces(this.getPlateau().getPersonnage(i).getJoueur().nbPieces());
                        condition = false;
                    }
                    i++;

                } while (!this.getPlateau().getPersonnage(i-1).getNom().equals(tableauPersonnage[choix-1]) && i < this.getPlateau().getNombrePersonnages());
            }

            else if (choix == 1) {
                System.out.println(" Vous ne pouvez pas choisir le personnage de Rang 1");
            }

            else if (choix == 2) {
                System.out.println(" Vous ne pouvez pas vous choisir");
            }

            else System.out.println(" Selectionnez un numero de personnage");

        } while (condition);

    }

}

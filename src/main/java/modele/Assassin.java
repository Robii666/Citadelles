package modele;

import java.util.Random;
import java.util.Scanner;

import controleur.Interaction;

    public class Assassin extends Personnage{


        public Assassin() {
            super("Assassin", 1, Caracteristiques.ASSASSIN);
            this.interaction = new Interaction();
            gen = new Random(8);
        }

        @Override
        public void utiliserPouvoir() {
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

                if (choix >= 2 && choix <= 8) {
                    condition = false;

                    int i = 0;
                    do {
                        System.out.println(this.getPlateau().getPersonnage(i).getNom());
                        System.out.println(tableauPersonnage[choix-1]);
                        if (this.getPlateau().getPersonnage(i).getNom().equals(tableauPersonnage[choix-1])){
                            this.getPlateau().getPersonnage(i).setAssassine();
                        }
                        i++;

                    } while (!this.getPlateau().getPersonnage(i-1).getNom().equals(tableauPersonnage[choix-1]) && i < this.getPlateau().getNombrePersonnages());
                }

                else if (choix == 1) {
                    System.out.println(" Vous ne pouvez pas vous assassiner");
                }
                else System.out.println(" Selectionnez un numero de personnage");

            } while (condition);
        }

    }


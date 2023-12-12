package application;

import modele.PlateauDeJeu;
import modele.Pioche;
import application.Configuration;

public class Application {
    public static void main(String[] args) {
        Configuration configuration = new Configuration();
        PlateauDeJeu plateauDeJeu = new PlateauDeJeu();
        Pioche pioche = new Pioche();
        pioche = configuration.nouvellePioche();
        plateauDeJeu = configuration.configurationDeBase(pioche);
        Jeu jeu = new Jeu();
        jeu.jouer();

    }

}

package application;

import modele.Architecte;
import modele.Assassin;
import modele.Condottiere;
import modele.Eveque;
import modele.Joueur;
import modele.Magicienne;
import modele.Marchande;
import modele.Personnage;
import modele.Pioche;
import modele.PlateauDeJeu;
import modele.Quartier;
import modele.Roi;
import modele.Voleur;

public class Configuration {
    // Méthode pour créer une nouvelle pioche de cartes
    public static Pioche nouvellePioche() {
        Pioche pioche = new Pioche();
        int count = 0;
        do {
            Quartier q = new Quartier();
            // Répartition des types de quartiers dans la pioche
            if (count < 14) {
                q.setType("RELIGIEUX");
            }else if (count >= 14 && count<28) {
                q.setType("MILITAIRE");
            }else if (count >= 28 && count<41) {
                q.setType("NOBLE");
            }else {
                q.setType("COMMERCANT");
            }
            count++;
            pioche.ajouter(q);
        } while (pioche.nombreElements()<54);
        pioche.melanger();
        return pioche;
    }

    // Méthode pour configurer le plateau de jeu de base
    public static PlateauDeJeu configurationDeBase(Pioche pioche) {
        PlateauDeJeu plateauDeJeu = new PlateauDeJeu();
        System.out.println("Configuration de base");
        Personnage assassin = new Assassin();
        Personnage voleur = new Voleur();
        Personnage magicienne = new Magicienne();
        Personnage roi = new Roi();
        Personnage eveque = new Eveque();
        Personnage marchande = new Marchande();
        Personnage condottiere = new Condottiere();
        Personnage architecte = new Architecte();

        // Tableau des personnages
        Personnage[] personnage = {assassin,voleur,magicienne,roi,eveque,marchande,condottiere,architecte};
        for (int i = 0; i < personnage.length; i++) {
            personnage[i].setPlateau(plateauDeJeu);
            plateauDeJeu.ajouterPersonnage(personnage[i]);
        }

        // Création des quartiers merveilles du jeu
        Quartier q1 = new Quartier();
        Quartier q2 = new Quartier();
        Quartier q3 = new Quartier();
        Quartier q4 = new Quartier();
        Quartier q5 = new Quartier();
        Quartier q6 = new Quartier();
        Quartier q7 = new Quartier();
        Quartier q8 = new Quartier();
        Quartier q9 = new Quartier();
        Quartier q10 = new Quartier();
        Quartier q11 = new Quartier();
        Quartier q12 = new Quartier();
        Quartier q13 = new Quartier();
        Quartier q14 = new Quartier();
        Quartier [] q = {q1,q2,q3,q4,q5,q6,q7,q8,q9,q10,q11,q12,q13,q14};

        // Attribution des noms aux quartiers merveilles
        q1.setNom("Bibliotheque");
        q2.setNom("Carriere");
        q3.setNom("Cour des Miracles");
        q4.setNom("Donjon");
        q5.setNom("Dracoport ");
        q6.setNom("Ecole de Magie");
        q7.setNom("Fontaine aux Souhaits");
        q8.setNom("Forge");
        q9.setNom("Laboratoire");
        q10.setNom("Manufacture");
        q11.setNom("Salle des Cartes");
        q12.setNom("Statue Equestre");
        q13.setNom("Tresor Imperial");
        q14.setNom("Tripot");

        for (int i = 0; i < q.length; i++) {
            q[i].setType("MERVEILLE");
            pioche.ajouter(q[i]);
        }

        // Création des quartiers merveilles spécifiques avec des attributs définis
        Quartier bibliotheque = new Quartier("Bibliotheque",Quartier.TYPE_QUARTIERS[4],6,"Si vous choisissez de piocher des cartes au début du tour, concervez-les toutes.\r\n");
        Quartier carriere = new Quartier("Carriere",Quartier.TYPE_QUARTIERS[4],5,"Vous pouvez batir des quartiers identiques a d'autres quartiers de votre cite. Le proprietaire\r\n"
                + "de la carriere peut batir autant de quartiers identiques qu’il le souhaite");
        Quartier courDesMiracles = new Quartier("Cour des Miracles",Quartier.TYPE_QUARTIERS[4],2,"Pour le calcul du score final, la Cour des Miracles est considérée comme un quartier de type\r\n"
                + "(couleur) de votre choix. Dans la cas où le propriétaire la considère comme un quartier noble,\r\n"
                + "militaire, marchant ou religieux, la Cour des Miracles ne peut plus être considérée comme\r\n"
                + "une merveille.");
        Quartier donjon = new Quartier("Donjon",Quartier.TYPE_QUARTIERS[4],3,"Le Donjon ne peut être affecté par les pouvoirs des personnages de rang 8.\r\n");
        Quartier dracoport = new Quartier("Dracoport",Quartier.TYPE_QUARTIERS[4],6,"Marquez 2 points supplémentaires à la fin de la partie.\r\n");
        Quartier ecoleDeMagie = new Quartier("Ecole de Magie",Quartier.TYPE_QUARTIERS[4],6,"Pour la perception des revenus des personnages, l’Ecole de Magie est considérée comme un ´\r\n"
                + "quartier du type (couleur) de votre choix");
        Quartier fontaineAuxSouhaits = new Quartier("Fontaine aux Souhaits",Quartier.TYPE_QUARTIERS[4],5,"A la fin de la partie, marquez 1 point supplm´entaire par merveille dans votre cit´e, y compris `\r\n"
                + "la Fontaine aux Souhaits");
        Quartier forge = new Quartier("Forge",Quartier.TYPE_QUARTIERS[4],5,"Une fois par tour, vous pouvez payez 2 pieces d’or pour piocher 3 cartes.");
        Quartier laboratoire = new Quartier("Laboratoire",Quartier.TYPE_QUARTIERS[4],5,"Une fois par tour, vous pouvez defausser 1 carte pour recevoir 2 pieces d’or.\r\n");
        Quartier manufacture = new Quartier("Manufacture",Quartier.TYPE_QUARTIERS[4],5,"Payez 1 piece d’or de moins lorsque vous bˆatissez une autre merveille.\r\n");
        Quartier salleDesCartes = new Quartier("Salle des Cartes",Quartier.TYPE_QUARTIERS[4],5,"A la fin de la partie, marquez 1 point suppl´ementaire par carte dans votre main. `\r\n");
        Quartier statueEquestre = new Quartier("Statue Equestre",Quartier.TYPE_QUARTIERS[4],3,"Si vous d´etenez le Couronne `a la fin de la partie, marquez 5 points suppl´ementaires.\r\n");
        Quartier tresorImperial = new Quartier("Tresor Imperial",Quartier.TYPE_QUARTIERS[4],5,"A la fin de la partie, marquez 1 point suppl´ementaire par pi`ece d’or dans votre tr´esor. \r\n");
        Quartier tripot = new Quartier("Tripot",Quartier.TYPE_QUARTIERS[4],6);


        pioche.ajouter(bibliotheque);
        pioche.ajouter(carriere);
        pioche.ajouter(courDesMiracles);
        pioche.ajouter(donjon);
        pioche.ajouter(dracoport);
        pioche.ajouter(ecoleDeMagie);
        pioche.ajouter(fontaineAuxSouhaits);
        pioche.ajouter(forge);
        pioche.ajouter(laboratoire);
        pioche.ajouter(manufacture);
        pioche.ajouter(salleDesCartes);
        pioche.ajouter(statueEquestre);
        pioche.ajouter(tresorImperial);
        pioche.ajouter(tripot);
        return plateauDeJeu;
    }
}


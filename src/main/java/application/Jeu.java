package application;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import modele.Joueur;
import modele.Personnage;
import modele.Pioche;
import modele.PlateauDeJeu;
import modele.Quartier;
import controleur.Interaction;

public class Jeu {
    private PlateauDeJeu plateauDeJeu;
    private int numeroConfiguration;
    private Random generateur;
    private int nbrJoueurCiteFini;


    // Constructeur
    public Jeu() {
        // Initialisation des attributs dans le constructeur
        generateur = new Random();
        plateauDeJeu = new PlateauDeJeu();
        nbrJoueurCiteFini = 0;
    }

    // Methode public pour démarrer le jeu
    public void jouer(){
        int choix = 0;
        System.out.println("Bienvenue dans Citadelles");

        do {
            System.out.println("Jouer une partie ? (1), Afficher les regles ? (2), Quitter ? (3)");
            choix=Interaction.lireUnEntier();
            if (choix == 1) {
                this.jouerPartie();
            }else if(choix == 2) {
                System.out.println(this.afficherLesRegles());
            }
        }while(choix != 3);

    }

    private String afficherLesRegles() {
        return "Voici les règles du jeu : ";
    }

    public void jouerPartie() {
            this.initialisation();

            do {
                this.tourDeJeu();
                this.gestionCouronne();
                this.reinitialisationPersonnages();
            }while(this.partieFinie()==false);

            this.calculDesPoints();
    }


    public void initialisation() {

        //Mise en place de la configuration
        Pioche pioche = Configuration.nouvellePioche();
        this.plateauDeJeu = Configuration.configurationDeBase(pioche);
        this.plateauDeJeu.ajouterPioche(pioche);

        //Ajout des joueurs
        Scanner scanner = new Scanner(System.in);
        System.out.println("Quel est votre nom?");
        String util = scanner.nextLine();
        Joueur ordi1 = new Joueur("ordinateurUn");
        Joueur ordi2 = new Joueur("ordinateurDeux");
        Joueur ordi3 = new Joueur("ordinateurTrois");
        Joueur utilisateur = new Joueur(util);
        plateauDeJeu.ajouterJoueur(utilisateur);
        plateauDeJeu.ajouterJoueur(ordi1);
        plateauDeJeu.ajouterJoueur(ordi2);
        plateauDeJeu.ajouterJoueur(ordi3);


        //Ajout de l'or
        utilisateur.ajouterPieces(2);
        ordi1.ajouterPieces(2);
        ordi2.ajouterPieces(2);
        ordi3.ajouterPieces(2);

        //Ajout des cartes
        for (int i =0; i<plateauDeJeu.getNombreJoueurs();i++) {
            for (int j = 0; j<4; j++) {
                plateauDeJeu.getJoueur(i).ajouterQuartierDansMain(plateauDeJeu.getPioche().piocher());
            }
        }

        //Attribution de la couronne
        int couronne = generateur.nextInt(4);
        plateauDeJeu.getJoueur(couronne).setPossedeCouronne(true);
        System.out.println("Initialisation terminée!");
    }


    private void gestionCouronne(){
        for (int i =0; i<=plateauDeJeu.getNombreJoueurs();i++) {
            if (this.plateauDeJeu.getJoueur(i).getPersonnage().getNom()=="Roi") {
                System.out.println(plateauDeJeu.getJoueur(i).getNom()+" est le roi");
                this.plateauDeJeu.getJoueur(i).setPossedeCouronne(true);
            }
        }
    }

    private void reinitialisationPersonnages(){
        for(int i=0;i<this.plateauDeJeu.getNombreJoueurs();i++) {
            this.plateauDeJeu.getJoueur(i).getPersonnage().reinitialiser();
        }
    }

    private boolean partieFinie(){
        boolean partieEstFinie = false;
        for(int i=0; i<this.plateauDeJeu.getNombreJoueurs(); i++) {
            if(this.plateauDeJeu.getJoueur(i).nbQuartiersDansCite()>=7) {
                partieEstFinie=true;
            }else {
                System.out.println("Tour suivant :");
            }
        }
        return partieEstFinie;
    }

    private void tourDeJeu(){
        this.choixPersonnages();

        ArrayList<Personnage> listChoix = new ArrayList<Personnage>();
        for (int i = 0; i < this.plateauDeJeu.getNombrePersonnages(); i++) {
            if (this.plateauDeJeu.getPersonnage(i).getJoueur()!= null ) {
                listChoix.add(this.plateauDeJeu.getPersonnage(i));
            }
        }
        for (int i = 0; i < listChoix.size(); i++) {
            if( listChoix.get(i).getJoueur() == this.plateauDeJeu.getJoueur(0)) {
                System.out.println("C'est a vous de jouer");
                if(this.plateauDeJeu.getJoueur(0).getPersonnage().getAssassine()) {
                    System.out.println("Vous êtes assassiné");
                }else {
                    if(this.plateauDeJeu.getJoueur(0).getPersonnage().getVole()) {
                        System.out.println("Vous êtes volé");
                    }else {
                        percevoirRessource(0);
                        this.plateauDeJeu.getJoueur(0).getPersonnage().percevoirRessourcesSpecifiques();
                        System.out.println("Voulez-vous utiliser le pouvoir de "+this.plateauDeJeu.getJoueur(0).getPersonnage().getNom()+" ?");
                        boolean choix = Interaction.lireOuiOuNon();
                        if (choix) {
                            this.plateauDeJeu.getJoueur(0).getPersonnage().utiliserPouvoir();
                        }
                        for(int b=0; b< this.plateauDeJeu.getJoueur(0).nbQuartiersDansCite() ;b++) {
                            if( this.plateauDeJeu.getJoueur(0).getCite()[b].getNom() == "Forge") {
                                System.out.println("Voulez-vous utiliser le pouvoir de la forge ?");
                                choix = Interaction.lireOuiOuNon();
                                if(choix) {
                                    if(this.plateauDeJeu.getJoueur(0).nbPieces()>2) {
                                        Quartier pioche1 = this.plateauDeJeu.getPioche().piocher();
                                        String pioche1string=pioche1.getNom();
                                        Quartier pioche2 = this.plateauDeJeu.getPioche().piocher();
                                        String pioche2string=pioche2.getNom();
                                        Quartier pioche3 = this.plateauDeJeu.getPioche().piocher();
                                        String pioche3string=pioche3.getNom();
                                        this.plateauDeJeu.getJoueur(0).ajouterQuartierDansMain(pioche1);
                                        this.plateauDeJeu.getJoueur(0).ajouterQuartierDansMain(pioche2);
                                        this.plateauDeJeu.getJoueur(0).ajouterQuartierDansMain(pioche3);
                                        System.out.println("Les cartes pioches sont "+pioche1string+" "+pioche2string+ " " + pioche3string+"\n");
                                        this.plateauDeJeu.getJoueur(0).retirerPieces(2);
                                        System.out.println("Vous avez " + this.plateauDeJeu.getJoueur(0).nbPieces() + " pieces \n");
                                    }else {
                                        System.out.println("Pas assez de pieces");
                                    }
                                }
                            }
                            if( this.plateauDeJeu.getJoueur(0).getCite()[b].getNom() == "Laboratoire") {
                                System.out.println("Voulez-vous utiliser le pouvoir du laboratoire ?");
                                choix = Interaction.lireOuiOuNon();
                                if(choix) {
                                    if(this.plateauDeJeu.getJoueur(0).getMain().size() != 0) {
                                        for (int j = 0; j < this.plateauDeJeu.getJoueur(0).getMain().size(); j++) {
                                            System.out.println((j+1) +" "
                                                    +this.plateauDeJeu.getJoueur(0).getMain().get(j).getNom()
                                                    + " cout : "+this.plateauDeJeu.getJoueur(0).getMain().get(j).getCout()
                                                    +" type : " +this.plateauDeJeu.getJoueur(0).getMain().get(j).getType());
                                        }
                                        System.out.println("Quelle quartier voulez vous defauser ");
                                        int choix2= Interaction.lireUnEntier(1, this.plateauDeJeu.getJoueur(0).getMain().size()+1)-1;
                                        this.plateauDeJeu.getJoueur(0).getMain().remove(choix2);
                                        this.plateauDeJeu.getJoueur(0).ajouterPieces(2);
                                    } else {
                                        System.out.println("Pas asser de carte en main");
                                    }
                                }
                            }
                        }
                        System.out.println("Voulez-vous construire ?");
                        choix = Interaction.lireOuiOuNon();
                        if (choix) {
                            System.out.println("Voici votre main :");
                            boolean peutConstruire = false;
                            for (int j = 0; j < this.plateauDeJeu.getJoueur(0).getMain().size(); j++) {
                                System.out.println((j+1) +" "
                                        +this.plateauDeJeu.getJoueur(0).getMain().get(j).getNom()
                                        + " cout : "+this.plateauDeJeu.getJoueur(0).getMain().get(j).getCout()
                                        +" type : " +this.plateauDeJeu.getJoueur(0).getMain().get(j).getType());
                                if(this.plateauDeJeu.getJoueur(0).getMain().get(j).getCout() <= this.plateauDeJeu.getJoueur(0).nbPieces()){
                                    peutConstruire = true;
                                }
                            }
                            if(peutConstruire) {
                                System.out.println("Vous avez " + this.plateauDeJeu.getJoueur(0).nbPieces() + " pieces dans votre tresor \n"
                                        + "Quelle quartier voulez vous construire?");
                                int choix2= Interaction.lireUnEntier(1, this.plateauDeJeu.getJoueur(0).getMain().size()+1)-1;
                                for(int b=0; b< this.plateauDeJeu.getJoueur(0).nbQuartiersDansCite() ;b++) {
                                    if(this.plateauDeJeu.getJoueur(0).getCite()[b].getNom() == "Manufacture") {
                                        if(this.plateauDeJeu.getJoueur(0).getMain().get(choix2).getType() == "MERVEILLE") {
                                            this.plateauDeJeu.getJoueur(0).getMain().get(choix2).setCout(this.plateauDeJeu.getJoueur(0).getMain().get(choix2).getCout()-1);
                                        }
                                    }
                                }
                                while (this.plateauDeJeu.getJoueur(0).getMain().get(choix2).getCout() > this.plateauDeJeu.getJoueur(0).nbPieces()) {
                                    System.out.println("Vous ne possedez pas assez de pieces dans votre coffre\n"
                                            + "Veuillez choisir un quartier de votre main moins chere: ");
                                    choix2= Interaction.lireUnEntier(1, this.plateauDeJeu.getJoueur(0).getMain().size()+1)-1;
                                }
                                int b = 0;
                                int d = 0;
                                for(int e=0;e<this.plateauDeJeu.getJoueur(0).nbQuartiersDansCite();e++) {
                                    if(this.plateauDeJeu.getJoueur(0).getCite()[e].getNom() == "Carriere"){
                                        b = 1;
                                    }
                                    for(int a=0;a<this.plateauDeJeu.getJoueur(0).nbQuartiersDansCite();a++) {
                                        while(b == 0 && this.plateauDeJeu.getJoueur(0).getMain().get(choix2).getNom() == this.plateauDeJeu.getJoueur(0).getCite()[a].getNom()){
                                            System.out.println("Vous ne pouvez pas construire un quartier identique \n"
                                                    + "Veuillez choisir un quartier de votre main différent : ");
                                            int c = choix2 +1 ;
                                            choix2= Interaction.lireUnEntier(1, this.plateauDeJeu.getJoueur(0).getMain().size()+1)-1;
                                            if(choix2 == c) {
                                                d = 1;
                                            }
                                        }
                                    }
                                }
                                if(d==0) {
                                    this.plateauDeJeu.getJoueur(0).ajouterQuartierDansCite(this.plateauDeJeu.getJoueur(0).getMain().get(choix2));
                                    this.plateauDeJeu.getJoueur(0).retirerPieces(this.plateauDeJeu.getJoueur(0).getMain().get(choix2).getCout());
                                    System.out.println("Le quartier " + this.plateauDeJeu.getJoueur(0).getMain().get(choix2).getNom() + " a ete construit dans votre cite");
                                    this.plateauDeJeu.getJoueur(0).getMain().remove(choix2);
                                    System.out.println(this.plateauDeJeu.getJoueur(0).getNom()+" possede "+this.plateauDeJeu.getJoueur(0).nbQuartiersDansCite()+" quartiers dans sa cite" );
                                }
                            }
                        }else {
                            System.out.println("Vous n avez pas asser d argent pour constuire quoique ce soit...");
                        }
                    }
                }
                //Tour de jeu du bot
            }else {
                for(int k=1;k<=this.plateauDeJeu.getNombreJoueurs();k++) {
                    if(listChoix.get(i).getJoueur() == this.plateauDeJeu.getJoueur(k)) {
                        System.out.println("C'est au joueur "+this.plateauDeJeu.getJoueur(k).getNom()+" jouant le personnage \"" +listChoix.get(i).getNom() +"\" de rang " + listChoix.get(i).getRang() + " de jouer" );
                        if(this.plateauDeJeu.getJoueur(k).getPersonnage().getAssassine()) {
                            System.out.println("Il est assassine, il ne peut pas jouer");
                        }else {
                            if(this.plateauDeJeu.getJoueur(k).getPersonnage().getVole()) {
                                System.out.println("Il est vole, il perd son trésor");
                            }else {
                                percevoirRessource(k);
                                this.plateauDeJeu.getJoueur(k).getPersonnage().percevoirRessourcesSpecifiques();
                                int choix = generateur.nextInt(2);
                                if (choix==0) {
                                    System.out.println("Le joueur "+this.plateauDeJeu.getJoueur(k).getNom()+" utilise son pouvoir de "+this.plateauDeJeu.getJoueur(k).getPersonnage().getNom());
                                    this.plateauDeJeu.getJoueur(k).getPersonnage().utiliserPouvoirAvatar();
                                }
                                else {
                                    System.out.println("Le joueur "+this.plateauDeJeu.getJoueur(k).getNom()+" choisit de ne pas utiliser son pouvoir de "+ this.plateauDeJeu.getJoueur(k).getPersonnage().getNom());
                                }
                                choix = generateur.nextInt(2);
                                for(int b=0; b< this.plateauDeJeu.getJoueur(k).nbQuartiersDansCite() ;b++) {
                                    if( this.plateauDeJeu.getJoueur(k).getCite()[b].getNom() == "Forge") {
                                        System.out.println("Voulez-vous utiliser le pouvoir de la forge ?");
                                        if (choix== 0) {
                                            if(this.plateauDeJeu.getJoueur(k).nbPieces()>2) {
                                                Quartier pioche1 = this.plateauDeJeu.getPioche().piocher();
                                                String pioche1s=pioche1.getNom();
                                                Quartier pioche2 = this.plateauDeJeu.getPioche().piocher();
                                                String pioche2s=pioche2.getNom();
                                                Quartier pioche3 = this.plateauDeJeu.getPioche().piocher();
                                                String pioche3s=pioche3.getNom();
                                                this.plateauDeJeu.getJoueur(k).ajouterQuartierDansMain(pioche1);
                                                this.plateauDeJeu.getJoueur(k).ajouterQuartierDansMain(pioche2);
                                                this.plateauDeJeu.getJoueur(k).ajouterQuartierDansMain(pioche3);
                                                System.out.println("Les cartes piochés sont "+pioche1s+" "+pioche2s+ " " + pioche3s+"\n");
                                                this.plateauDeJeu.getJoueur(k).retirerPieces(2);
                                                System.out.println("Vous avez desormais " + this.plateauDeJeu.getJoueur(0).nbPieces() + " pieces \n");
                                            }else {
                                                System.out.println("Pas assez de pieces");
                                            }
                                        }
                                    }
                                    if( this.plateauDeJeu.getJoueur(k).getCite()[b].getNom() == "Laboratoire") {
                                        System.out.println("Voulez-vous utiliser le pouvoir du laboratoire ?");
                                        choix = generateur.nextInt(2);;
                                        if(choix == 0) {
                                            if(this.plateauDeJeu.getJoueur(k).getMain().size() != 0) {
                                                for (int j = 0; j < this.plateauDeJeu.getJoueur(k).getMain().size(); j++) {
                                                    System.out.println((j+1) +" "
                                                            +this.plateauDeJeu.getJoueur(k).getMain().get(j).getNom()
                                                            + " cout : "+this.plateauDeJeu.getJoueur(k).getMain().get(j).getCout()
                                                            +" type : " +this.plateauDeJeu.getJoueur(k).getMain().get(j).getType());
                                                }
                                                System.out.println("Quelle quartier voulez vous defauser ");
                                                int choix2= generateur.nextInt(this.plateauDeJeu.getJoueur(k).getMain().size());
                                                this.plateauDeJeu.getJoueur(k).getMain().remove(choix2);
                                                this.plateauDeJeu.getJoueur(k).ajouterPieces(2);
                                            } else {
                                                System.out.println("Pas asser de carte en main");
                                            }
                                        }
                                    }
                                    if(this.plateauDeJeu.getJoueur(k).getCite()[b].getNom() == "Manufacture") {
                                        if(this.plateauDeJeu.getJoueur(k).getCite()[b].getType() == "MERVEILLE") {
                                            this.plateauDeJeu.getJoueur(k).getCite()[b].setCout(this.plateauDeJeu.getJoueur(0).getCite()[b].getCout()-1);
                                        }
                                    }



                                }
                                int choix1 = generateur.nextInt(2);
                                if (choix1==0) {
                                    boolean peutConstruire = false;
                                    for (int j = 0; j < this.plateauDeJeu.getJoueur(k).getMain().size(); j++) {
                                        if(this.plateauDeJeu.getJoueur(k).getMain().get(j).getCout() <= this.plateauDeJeu.getJoueur(k).nbPieces()){
                                            peutConstruire = true;
                                        }
                                    }
                                    if(peutConstruire) {
                                        int choix2 = generateur.nextInt(this.plateauDeJeu.getJoueur(k).getMain().size());
                                        for(int b=0; b< this.plateauDeJeu.getJoueur(k).nbQuartiersDansCite() ;b++) {
                                            if(this.plateauDeJeu.getJoueur(k).getCite()[b].getNom() == "Manufacture") {
                                                if(this.plateauDeJeu.getJoueur(k).getMain().get(choix2).getType() == "MERVEILLE") {
                                                    this.plateauDeJeu.getJoueur(k).getMain().get(choix2).setCout(this.plateauDeJeu.getJoueur(k).getMain().get(choix2).getCout()-1);
                                                }
                                            }
                                        }
                                        while (this.plateauDeJeu.getJoueur(k).getMain().get(choix2).getCout() > this.plateauDeJeu.getJoueur(k).nbPieces()) {
                                            choix2 = generateur.nextInt(this.plateauDeJeu.getJoueur(k).getMain().size());
                                        }
                                        int b = 0;
                                        int d = 0;
                                        for(int e=0;e<this.plateauDeJeu.getJoueur(k).nbQuartiersDansCite();e++) {
                                            if(this.plateauDeJeu.getJoueur(k).getCite()[e].getNom() == "Carriere"){
                                                b = 1;
                                            }
                                            for(int a=0;a<this.plateauDeJeu.getJoueur(k).nbQuartiersDansCite();a++) {
                                                while(b == 0 && this.plateauDeJeu.getJoueur(k).getMain().get(choix2).getNom() == this.plateauDeJeu.getJoueur(0).getCite()[a].getNom() && d==0){
                                                    System.out.println("Vous ne pouvez pas construire un quartier identique \n"
                                                            + "Veuillez choisir un quartier de votre main différent : ");
                                                    int c = choix2;
                                                    choix2= generateur.nextInt(this.plateauDeJeu.getJoueur(k).getMain().size());;
                                                    if(choix2 == c) {
                                                        System.out.println("Vous rennoncer a construire");
                                                        d = 1;
                                                    }
                                                }
                                            }
                                        }
                                        if(d==0) {
                                            this.plateauDeJeu.getJoueur(k).ajouterQuartierDansCite(this.plateauDeJeu.getJoueur(k).getMain().get(choix2));
                                            this.plateauDeJeu.getJoueur(k).retirerPieces(this.plateauDeJeu.getJoueur(k).getMain().get(choix2).getCout());
                                            System.out.println("Le joueur "+this.plateauDeJeu.getJoueur(k).getNom()+" choisi de construire le quartier "+ this.plateauDeJeu.getJoueur(k).getMain().get(choix2).getNom() + " dans sa cite");
                                            this.plateauDeJeu.getJoueur(k).getMain().remove(choix2);
                                        }
                                    }else {
                                        System.out.println("Vous n avez pas asser d argent");
                                    }
                                }else {
                                    System.out.println("Le joueur "+this.plateauDeJeu.getJoueur(k).getNom()+" choisi de ne rien construire");
                                }
                            }
                        }
                    }
                }
            }
        }


    }


    private void choixPersonnages(){
        System.out.println("Choix des personnages :");
        ArrayList<Personnage> listPerso = new ArrayList<Personnage>();
        for (int i = 0; i < this.plateauDeJeu.getNombrePersonnages(); i++) {
            listPerso.add(this.plateauDeJeu.getPersonnage(i));
        }
        System.out.println(listPerso.size());
        //Les personnages de rang 4 sont mis de cotes
        for (int i = 0; i < 2; i++) {
            int j = 0;
            while(j==0) {
                int r = generateur.nextInt(listPerso.size());
                if(listPerso.get(r).getRang()!=4) {
                    System.out.println("Le personnage "+listPerso.get(r).getNom()+ " de rang "+listPerso.get(r).getRang()+" est écarté face visible");
                    listPerso.remove(r);
                    j=10;
                }
                else
                    j=0;
            }
            if (i == 1) {
                int r = generateur.nextInt(listPerso.size());
                System.out.println("Un personnage est écarté face cachée");
                listPerso.remove(r);
            }
        }

        //Identification du personnage avec couronne
        if(this.plateauDeJeu.getJoueur(0).getPossedeCouronne()) {
            System.out.println("Vous avez la couronne");
        }else {
            for (int i = 1; i <this.plateauDeJeu.getNombreJoueurs(); i++) {
                if (this.plateauDeJeu.getJoueur(i).getPossedeCouronne()) {
                    System.out.println(this.plateauDeJeu.getJoueur(i).getNom() + " à la couronne");
                    i = 10;
                }
            }
        }

        //Stockage des Rang de personnage disponible et affichage
        ArrayList<Integer> rangDispo = new ArrayList<Integer>();
        for (int i = 0; i < listPerso.size(); i++) {
            System.out.println(listPerso.get(i).getRang()+" "+ listPerso.get(i).getNom());
            rangDispo.add(listPerso.get(i).getRang());
        }

        //Choix du personnage parmis la liste
        System.out.println("Quelle personnage choisissez-vous ?");
        int choix = Interaction.lireUnEntier(1, 9);

        //Verification que le choix du rang est disponible
        while (!rangDispo.contains(choix)) {
            System.out.println("Ce personnage n'est pas dans la liste");
            choix = Interaction.lireUnEntier(1, 9);
        }

        System.out.println(plateauDeJeu.getJoueur(0).getNom());
        this.plateauDeJeu.getPersonnage(choix-1).setJoueur(this.plateauDeJeu.getJoueur(0));
        listPerso.remove(this.plateauDeJeu.getPersonnage(choix-1));
        this.plateauDeJeu.getJoueur(0).setMonPersonnage(this.plateauDeJeu.getPersonnage(choix-1));

    }


    private void percevoirRessource(int j){
        //On fait la difference entre joueur et bot
        //L'utilisateur choisi
        int b = 0 ;
        if(j==0) {
            System.out.println("Choisissez-vous de percevoir 2 pieces (1) ou de piocher 2 cartes pour en choisir une (2) ?");
            int choix = Interaction.lireUnEntier(1, 3);
            if (choix==1) {
                this.plateauDeJeu.getJoueur(j).getPersonnage().ajouterPieces();
                System.out.println("Vous avez perçu 2 pieces");
            }
            else {
                System.out.println("Pioche de 2 cartes...");
                Quartier pioche1 = this.plateauDeJeu.getPioche().piocher();
                String pioche1string=pioche1.getNom();
                Quartier pioche2 = this.plateauDeJeu.getPioche().piocher();
                String pioche2string=pioche2.getNom();
                for(int i=0;i<this.plateauDeJeu.getJoueur(j).nbQuartiersDansCite();i++) {
                    if(this.plateauDeJeu.getJoueur(j).getCite()[i].getNom() == "Bibliotheque"){
                        System.out.println(this.plateauDeJeu.getJoueur(j).getNom()+" choisi de piocher et garder les cartes grâce à la bibliotheque");
                        this.plateauDeJeu.getJoueur(j).ajouterQuartierDansMain(pioche1);
                        this.plateauDeJeu.getJoueur(j).ajouterQuartierDansMain(pioche2);
                        System.out.println("Les 2 cartes pioches sont "+pioche1string+" et "+pioche2string+"\n");
                        b=1;
                        break;
                    }
                }
                if(b==0) {
                    System.out.println("Les 2 cartes piochés sont "+pioche1string+" et "+pioche2string+".\nChoisissez-vous la 1ere ou la seconde carte ?");
                    int choix1 = Interaction.lireUnEntier(1, 3);
                    if (choix1==1) {
                        this.plateauDeJeu.getJoueur(j).ajouterQuartierDansMain(pioche1);
                        this.plateauDeJeu.getPioche().ajouter(pioche2);
                        System.out.println("Vous gardez dans votre main la cité "+pioche1string);
                    } else {
                        this.plateauDeJeu.getJoueur(j).ajouterQuartierDansMain(pioche2);
                        this.plateauDeJeu.getPioche().ajouter(pioche1);
                        System.out.println("Vous gardez dans votre main la cité "+pioche2string);
                    }
                }
            }
        }
        else {
            //On choisi pour les bots
            int choix = generateur.nextInt(2);
            if(choix==0) {
                this.plateauDeJeu.getJoueur(j).getPersonnage().ajouterPieces();
                System.out.println(this.plateauDeJeu.getJoueur(j).getNom()+" choisit de perçevoir 2 pieces");
            }
            else {
                Quartier pioche1 = this.plateauDeJeu.getPioche().piocher();
                Quartier pioche2 = this.plateauDeJeu.getPioche().piocher();
                for(int i=0;i<this.plateauDeJeu.getJoueur(j).nbQuartiersDansCite();i++) {
                    if(this.plateauDeJeu.getJoueur(j).getCite()[i].getNom() == "Bibliotheque"){
                        System.out.println(this.plateauDeJeu.getJoueur(j).getNom()+" choisi de piocher et garder les cartes grace a la bibliotheque");
                        this.plateauDeJeu.getJoueur(j).ajouterQuartierDansMain(pioche1);
                        this.plateauDeJeu.getJoueur(j).ajouterQuartierDansMain(pioche2);
                        System.out.println("Les 2 cartes pioches sont "+pioche1+" et "+pioche2+"\n");
                        break;
                    }
                }
                int choix1 = generateur.nextInt(2);
                if (choix1==0) {
                    this.plateauDeJeu.getJoueur(j).ajouterQuartierDansMain(pioche1);
                    this.plateauDeJeu.getPioche().ajouter(pioche2);
                } else {
                    this.plateauDeJeu.getJoueur(j).ajouterQuartierDansMain(pioche2);
                    this.plateauDeJeu.getPioche().ajouter(pioche1);
                }
                System.out.println(this.plateauDeJeu.getJoueur(j).getNom()+" choisi de piocher une cite");
            }

        }
    }

    public void calculDesPoints() {
    }
}

package modele;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import controleur.Interaction;

public class Magicienne extends Personnage {
    ArrayList<Quartier> mainJoueur ;
    ArrayList<Quartier> mainJoueurChoisi ;
    Interaction sc ;
    public Magicienne(){
        super("Magicienne", 3, Caracteristiques.MAGICIENNE);
        mainJoueur = new ArrayList<Quartier>();
        mainJoueurChoisi = new  ArrayList<Quartier>();
        sc = new Interaction();

    }

    @Override
    public void utiliserPouvoir() {
        if(this.getAssassine()){
            System.out.println("Attendez le prochain tour : Votre personnage a �t� assassin�");
        }else{

            System.out.println("Echanger toutes vos cartes avec celles d'un autre joueur ? (o/n) ");
            boolean echangerCarte = sc.lireOuiOuNon();
            System.out.println(echangerCarte);
            if(echangerCarte){
                boolean continu = true;
                do{
                    for(int i = 0; i < this.getPlateau().getNombreJoueurs(); i++){
                        System.out.println("Joueur "+(i+1) + "." + this.getPlateau().getJoueur(i).getNom() + " : " + this.getPlateau().getJoueur(i).getMain().size() + " carte(s).");
                    }
                    System.out.println("Veuillez choisir un joueur : ");
                    int choix  = sc.lireUnEntier(1, this.getPlateau().getNombreJoueurs()+1)-1 ;
                    if(this.getPlateau().getPersonnage(choix).getNom().equals(this.getNom())){
                        System.out.println("Vous ne pouvez pas �changer vos propres cartes");
                        System.out.println("Quel joueur choisissez-vous ? 0 pour ne rien faire");
                    }else {

                        mainJoueurChoisi.addAll(this.getPlateau().getJoueur(choix).getMain());
                        mainJoueur.addAll(getJoueur().getMain());



                        this.getPlateau().getJoueur(choix).getMain().clear();
                        this.getJoueur().getMain().clear();

                        this.getPlateau().getJoueur(choix).getMain().addAll(mainJoueur);
                        this.getJoueur().getMain().addAll(mainJoueurChoisi);

                        continu = false;
                    }
                }while(continu);

            } else if(!echangerCarte){
                ArrayList<Quartier> copie = (ArrayList<Quartier>) this.getJoueur().getMain().clone();

                System.out.println("Combien de cartes voulez-vous prendre dans la pioche ?");
                int nbCarteAtirer = sc.lireUnEntier(0, this.getJoueur().getMain().size()+1);
                if (nbCarteAtirer > 0 && nbCarteAtirer < this.getJoueur().getMain().size()){
                    for(int j = 0; j < nbCarteAtirer; j++){
                        System.out.println("Affichage de votre main");
                        for(int i = 0; i < this.getJoueur().getMain().size(); i++){
                            System.out.println((i+1) + ". " + this.getJoueur().getMain().get(i).getNom() + "( " + this.getJoueur().getMain().get(i).getCout() + " )");
                        }
                        System.out.println("Quel numero de carte souhaitez vous retirer ? ");
                        int numeroCarte = sc.lireUnEntier(1, this.getJoueur().getMain().size()+1)-1;
                        this.getPlateau().getPioche().ajouter(copie.get(numeroCarte));
                        copie.remove(numeroCarte-1);
                    }
                    for (int i = 0; i < nbCarteAtirer; i++) {
                        copie.add(this.getPlateau().getPioche().piocher());
                    }
                    this.getJoueur().getMain().clear();
                    this.getJoueur().getMain().addAll(copie);
                }else if(nbCarteAtirer == 0){

                }else if(nbCarteAtirer == this.getJoueur().getMain().size()){

                    for(int k = 0; k < nbCarteAtirer; k++){
                        this.getJoueur().getMain().remove(0);
                        this.getPlateau().getPioche().ajouter(copie.get(k));
                        this.getJoueur().getMain().add(this.getPlateau().getPioche().piocher());
                    }
                }else{
                    for(int k = 0; k < this.getJoueur().getMain().size(); k++){
                        System.out.println((k+1) + copie.get(k).getCaracteristiques());
                    }
                    for(int j = 0; j < nbCarteAtirer; j ++){
                        System.out.println("Veuillez choisir une carte : ");
                        int reponse = sc.lireUnEntier() - 1;
                        copie.remove(this.getJoueur().getMain().get(reponse));
                        this.getPlateau().getPioche().ajouter(this.getJoueur().getMain().get(reponse));
                        copie.add(this.getPlateau().getPioche().piocher());
                    }
                    this.getJoueur().getMain().clear();
                    this.getJoueur().getMain().addAll(copie);
                }
            }

        }




    }

    @Override
    public void utiliserPouvoirAvatar() {
        if(this.getAssassine()){
            System.out.println("Votre personnage a �t� assassin�");
        }else{
            System.out.println("Voulez-vous �changer vos cartes avec celles d'un autre joueur ? (o/n) ");
            boolean verite[] = {true, false};
            int random = ThreadLocalRandom.current().nextInt(-1, 1) +1;
            boolean reponse1  = verite[random];
            if(!reponse1){
                System.out.println("Combien de cartes voulez-vous prendre dans la pioche ?");
                int nb = ThreadLocalRandom.current().nextInt(-1, this.getJoueur().getMain().size()) +1;
                if (nb > 0 && nb < this.getJoueur().getMain().size()){
                    for(int j = 0; j < nb; j++){
                        System.out.println("Voici les cartes de votre main");
                        for(int i = 0; i < this.getJoueur().getMain().size(); i++){
                            System.out.println((i+1) + ". " + this.getJoueur().getMain().get(i).getNom() + "( " + this.getJoueur().getMain().get(i).getCout() + " )");
                        }
                        System.out.println("Quel est le numero de la carte que vous voulez retirer ? ");
                        int reponse3 = ThreadLocalRandom.current().nextInt(0, this.getJoueur().getMain().size()) + 1;
                    }
                }else if(nb == 0){
                }else if(nb == this.getJoueur().getMain().size()){
                    ArrayList<Quartier> copie = (ArrayList<Quartier>) this.getJoueur().getMain().clone();
                    for(int k = 0; k < nb; k++){
                        this.getJoueur().getMain().remove(0);
                        this.getPlateau().getPioche().ajouter(copie.get(k));
                        this.getJoueur().getMain().add(this.getPlateau().getPioche().piocher());
                    }
                }else{
                    ArrayList<Quartier> copie = (ArrayList<Quartier>) this.getJoueur().getMain().clone();
                    for(int k = 0; k < this.getJoueur().getMain().size(); k++){
                        System.out.println((k+1) + copie.get(k).getCaracteristiques());
                    }
                    for(int j = 0; j < nb; j ++){
                        System.out.println("Veuillez choisir une carte : ");
                        int reponse = ThreadLocalRandom.current().nextInt(0, this.getJoueur().getMain().size())+1;
                        copie.remove(this.getJoueur().getMain().get(reponse));
                        this.getPlateau().getPioche().ajouter(this.getJoueur().getMain().get(reponse));
                        copie.add(this.getPlateau().getPioche().piocher());
                    }
                    this.getJoueur().getMain().clear();
                    this.getJoueur().getMain().addAll(copie);
                }
            }else if(reponse1){
                boolean continu = true;
                do{

                    for(int i = 0; i < this.getPlateau().getNombreJoueurs(); i++){
                        System.out.println((i+1) + "." + this.getPlateau().getJoueur(i).getNom() + " : " + this.getPlateau().getJoueur(i).getMain().size() + " carte(s).");
                    }
                    System.out.println("Veuillez choisir un joueur : ");
                    Random rd = new Random();
                    int choix = rd.nextInt(this.getPlateau().getNombreJoueurs());
                    if(this.getPlateau().getPersonnage(choix).getNom().equals(this.getNom())){

                        System.out.println("Erreur : Vous ne pouvez pas �changer vos propres cartes");
                        System.out.println("Quel joueur choisissez-vous ? (0 pour ne rien faire)");
                    }else {
                        ArrayList<Quartier> copie1 = this.getJoueur().getMain();
                        ArrayList<Quartier> copie2 = this.getPlateau().getJoueur(choix).getMain();
                        this.getJoueur().getMain().clear();
                        this.getPlateau().getJoueur(choix).getMain().clear();
                        System.out.println(copie2.size());
                        this.getJoueur().getMain().addAll(copie2);
                        System.out.println(copie1.size());
                        this.getPlateau().getJoueur(choix).getMain().addAll(copie1);
                        continu = false;
                    }

                }
                while(continu);

            }

        }


    }
}

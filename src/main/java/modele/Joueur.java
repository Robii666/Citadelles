package modele;

import java.util.ArrayList;
import java.util.Random;

public class Joueur {
    private String nom;
    private int tresor;
    private Quartier[] cite;
    private int nbQuartiers;
    private ArrayList<Quartier> main;
    private boolean possedeCouronne;


    public Joueur(String nom) {
        this.nom = nom;
        this.cite = new Quartier[8];
        this.tresor = 0;
        this.main = new ArrayList<Quartier>();
        this.possedeCouronne = false;

    }
    public String getNom() {
        return nom;
    }

    public int nbPieces() {
        return tresor;
    }

    public int nbQuartiersDansCite() {
        return nbQuartiers;
    }

    public Quartier[] getCite() {
        return cite;
    }

    public ArrayList<Quartier> getMain() {
        return main;
    }

    public int nbQuartiersDansMain() {
        return main.size();
    }

    public boolean getPossedeCouronne() {
        return possedeCouronne;
    }

    public void setPossedeCouronne(boolean b) {
        possedeCouronne = b;
    }

    public void ajouterPieces(int unePiece) {
        if(unePiece>0) {

            this.tresor = tresor+unePiece;
        }
    }


    public void retirerPieces(int unePiece) {
        if(unePiece > 0 && unePiece <= this.tresor) {
            this.tresor = this.tresor-unePiece;
        }
    }

    public void ajouterQuartierDansCite(Quartier quartier) {
        if (nbQuartiers < 8) {
            cite[nbQuartiers] = quartier;
            nbQuartiers++;
        }
    }

    public boolean quartierPresentDansCite(String nom) {
        for (Quartier quartier : cite) {
            if (quartier != null && quartier.getNom().equals(nom)) {
                return true;
            }
        }
        return false;
    }

    public Quartier retirerQuartierDansCite(String quartier) {
        Quartier q = null;
        int j = 0;

        for (int i = 0; i < this.nbQuartiersDansCite(); i++) {
            if (this.cite[i].getNom().equals(quartier)) {
                j = i;
                System.out.println(j);
                q = this.cite[i];
            }
        }
        if (q!=null) {
            for (int i = j; i < nbQuartiers; i++) {
                this.cite[i] = this.cite[i+1];
            }
            this.nbQuartiers--;
        }
        return q;
    }

    public void ajouterQuartierDansMain(Quartier quartier) {
        getMain().add(quartier);
    }

    public Quartier retirerQuartierDansMain() {
        System.out.println(this.main.size());
        if (this.main.size() > 0) {
            Random generateur = new Random();
            int numeroHasard = generateur.nextInt(this.nbQuartiersDansMain());
            Quartier retire = this.main.get(numeroHasard);
            this.main.remove(numeroHasard);
            return retire;
        }else return null;

    }
    public void reinitialiser() {

        this.main.clear();
        this.tresor = 0;
        this.nbQuartiers = 0;
        for (int i = 0; i < cite.length; i++) {
            this.cite[i] = null;
        }
    }
}

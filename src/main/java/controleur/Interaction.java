package controleur;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Interaction {
    private static Scanner sc = new Scanner(System.in);

    public static int lireUnEntier() {
        int i = 0;
        boolean continu = true;
        do {
            try {
                i = sc.nextInt();
                continu = false;
            } catch (InputMismatchException e) {
                System.out.print("Veuillez rentrer un chiffre : ");
                sc.next(); // passe l'entier pour eviter de boucler
            }
        } while(continu);
        return i;
    }

    // renvoie un entier lu au clavier compris dans l'intervalle
    //     [borneMin, borneMax[
    public static int lireUnEntier(int borneMin, int borneMax) {
        int i = 0;
        int taille = borneMax - borneMin;
        int [] tab = new int [taille];
        int calculMin = 0;
        int calculMax = 0;
        boolean continu = true;
        do {

            try {
                i = sc.nextInt();
                calculMin = i-borneMin;
                calculMax = borneMax-i-1;
                tab[calculMin] = 0;
                tab[calculMax] = 1;
                continu = false;
            } catch (ArrayIndexOutOfBoundsException | InputMismatchException e) {
                System.out.print("Veuillez rentrer un chiffre  compris entre "+borneMin+" compris et "+borneMax+" non compris : ");
            }
        } while(continu);
        return i;
    }

    // lit les reponses "oui", "non", "o" ou "n" et renvoie un booleen
    public static boolean lireOuiOuNon() {
        int element1 = 0;
        int element2 = 1;

        boolean retour = true;
        boolean continu = true;
        String reponse = "";
        do {
            reponse = sc.nextLine();
            reponse.toLowerCase();

            if (reponse.equals("o") || reponse.equals("oui")) {
                retour = true;
                element1 = 1;

            }else if (reponse.equals("n") || reponse.equals("non")) {
                retour = false;
                element1 = 1;

            }

            try {
                element1=element2/element1;
                continu = false;
            } catch (InputMismatchException | ArithmeticException e) {
                System.out.print("Veuillez rentrer non, n, oui ou o : ");
                sc.nextLine(); // passe l'entier pour eviter de boucler
            }
        } while(continu);
        return retour;
    }

    // renvoie une chaine de caractere lu au clavier:
    public static String lireUneChaine() {
        boolean continu = true;
        int controle = 0;
        String retour = "";
        do {
            try {
                retour = sc.nextLine();
                controle= retour.replaceAll(" ", "").length();
                System.out.println(controle);
                controle = retour.length()/controle;
                continu = false;

            } catch (InputMismatchException | ArithmeticException e) {
                System.out.print("Entrer un mot : ");
                sc.nextLine(); // passe la ligne pour eviter de boucler
            }
        } while(continu);
        return retour;
    }
}
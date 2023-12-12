package modele;

import java.util.Random;
import controleur.Interaction;

public abstract class Personnage {
    private String nom; 
    private int rang;
    private String caracteristiques;  
    private Joueur joueur;
    private boolean assassine;
    private boolean vole;
    private PlateauDeJeu plateau;
    Interaction interaction;
    Quartier quartier ;
    Pioche pioche;
    protected final String tableauPersonnage[] = {"Assassin","Voleur","Eveque","Roi","Magicienne","Condottiere","Marchande","Architecte"};
    Random gen = null;
    
    
    //constructeur
    public Personnage (String unNom, int unRang, String lesCaracteristiques){
        this.nom = unNom;
        this.rang = unRang;
        this.caracteristiques = lesCaracteristiques;
        this.joueur = null;
        this.vole = false;
        this.assassine = false;
        
    }
    
    
    //methodes
    public void ajouterPieces(){
    		if(this.assassine != true && this.joueur != null ){
    			this.joueur.ajouterPieces(2);
                System.out.println("Deux pièces ont été ajoutées au trésor de " + joueur.getNom());
            }
            else{
                    System.out.println("Le personnage ne peut pas ajouter de pièces");   
            }
    		
    }
    public void ajouterQuartier(Quartier nouveau){
	        if(this.assassine != true && this.joueur != null ){
	            this.joueur.ajouterQuartierDansMain(nouveau);
                System.out.println("Le quartier '" + nouveau.getNom() + "a été ajouté dans la main de " + joueur.getNom());
	        }
    	
    }
    public void construire(Quartier nouveau){
	        if(this.assassine != true && this.joueur != null ){
	            this.joueur.ajouterQuartierDansCite(nouveau);
                System.out.println("Le quartier '" + nouveau.getNom() + "' a été construit dans la cité de " + joueur.getNom());
	        }
            else{
                System.out.println("Le quartier ne peut pas être construit");
            }
    	
    }
    public void percevoirRessourcesSpecifiques(){
        if(this.assassine != true && this.joueur != null ){
            //TODO percevoir les ressources
            System.out.println("Aucune ressource specifique");
        } 
    }
    public abstract void utiliserPouvoir();
    public abstract void utiliserPouvoirAvatar();
   
    
    public void reinitialiser(){
        if (this.joueur!=null) {
            this.joueur.monPersonnage = null;
        }
        this.vole = false;
        this.assassine = false;
        this.joueur = null;

    }


    //accesseurs
    public String getNom(){
        return this.nom;
    }
    public int getRang(){
        return this.rang;
    }
    public String getCaracteristiques(){
        return this.caracteristiques;
    }
    public Joueur getJoueur(){
        return this.joueur;
    }
    public boolean getAssassine(){
        return this.assassine;
    }
    public boolean getVole(){
        return this.vole;
    }
    public PlateauDeJeu getPlateau() {
    	return this.plateau;
    }
    

    public void setJoueur(Joueur joueur){
        if (joueur != null) {
            joueur.monPersonnage = this;
        } else {
            System.out.println("Essayer de définir le joueur null pour" + this.getNom());
        }
            this.joueur = joueur;
    }
    public void setVole(){
        this.vole = true;
    }
    public void setAssassine(){
        this.assassine = true;
    }
    public void setPlateau(PlateauDeJeu p){
        this.plateau = p;
    }

}

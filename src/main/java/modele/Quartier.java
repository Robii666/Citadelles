package modele;

public class Quartier {
    // Attributs privés
    private String nom;
    private String type;
    private int coutConstruction;
    private String caracteristiques;

    // La constante TYPE QUARTIERS
    public static final String[] TYPE_QUARTERS ={"RELIGIEUX", "MILITAIRE", "NOBLE", "COMMERCANT", "MERVEILLE"};

    //Implementation des accesseurs de l'attribut nom
    public String getNom(String nom){
        return this.nom;
    }
    public void setNom(String nom){
        this.nom = nom;
    }

    //Implementation des accesseurs de l'attribut type
    public String getType() {
        return this.type;
    }

    public void setType(String type){
        //Verifie que l'arguement correspond à TYPE_QUARTIERS
        if(type.equals(TYPE_QUARTERS)) {
            this.type = type;
        } else {
            this.type = "";
        }
    }

    //Implementation des accesseurs de l'attribut coutConstruction
    public int getCout() {

        return this.coutConstruction;
    }

    public void setCout(int coutConstruction) {
        // coût entre 1 et 6
        if (coutConstruction >= 1 && coutConstruction <= 6) {
            this.coutConstruction = coutConstruction;
        } else {
            this.coutConstruction = 0;
        }
    }

    //Implémentations des accesseurs de l'attribut caracteristiques
    public String getCaracteristiques() {
        return this.caracteristiques;
    }

    public void setCaracteristiques(String caracteristiques) {
        this.caracteristiques = caracteristiques;
    }

    // Constructeur
    public Quartier(String nom, String type, int coutConstruction, String caracteristiques) {
        // Utilisation des accesseurs de chaque attribut pour les initialiser
        setNom(nom);
        setType(type);
        setCout(coutConstruction);
        setCaracteristiques(caracteristiques);
    }
}

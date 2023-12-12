package modele;

public class Roi extends Personnage {

    public Roi() {
        super("Roi", 4, Caracteristiques.ROI);
    }

    public void utiliserPouvoir() {
        if (this.getJoueur() != null) {
            this.getJoueur().setPossedeCouronne(true);
        }

    }

    public void percevoirRessourcesSpecifiques() {
        if (super.getJoueur() != null) {
            int nombreQuartierNoble = 0;
            for (int i = 0; i < super.getJoueur().getCite().length; i++) {
                if (super.getJoueur().getCite()[i] != null && "NOBLE".equals(super.getJoueur().getCite()[i].getType())) {

                    nombreQuartierNoble++;
                }
            }

            super.getJoueur().ajouterPieces(nombreQuartierNoble);
            String message = "Le nombre de piece ajouté :" + nombreQuartierNoble;
            System.out.println(message);

        }
    }
    public void setAssassine() {
        super.setAssassine();
    }
}


package cinema;

public class Film {

    private String categorie;
    private String description;
    private String duree;
    private String titre;

    public Film(String titre, String description, String categorie, String duree){
        this.titre = titre;
        this.description = description;
        this.categorie = categorie;
        this.duree = duree;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public String getDecription() {
        return description;
    }

    public void setDecription(String decription) {
        this.description = decription;
    }

    public String getDuree() {
        return duree;
    }

    public void setDuree(String durée) {
        this.duree = durée;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }


}

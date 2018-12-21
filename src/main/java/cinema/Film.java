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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

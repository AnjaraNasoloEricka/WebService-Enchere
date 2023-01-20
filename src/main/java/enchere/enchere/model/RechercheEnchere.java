package enchere.enchere.model;

import java.sql.Date;

public class RechercheEnchere {
    private String motcle;
    private Date[] date;
    private int[] idcategorie;
    private int[] prix;
    private int[] statut;

    public String getMotcle() {
        return motcle;
    }

    public void setMotcle(String motcle) {
        this.motcle = motcle;
    }

    public Date[] getDate() {
        return date;
    }

    public void setDate(Date[] d) {
        this.date = d;
    }

    public int[] getIdcategorie() {
        return idcategorie;
    }

    public void setIdcategorie(int[] idcategorie) {
        this.idcategorie = idcategorie;
    }

    public int[] getPrix() {
        return prix;
    }

    public void setPrix(int[] prix) {
        this.prix = prix;
    }

    public int[] getStatut() {
        return statut;
    }

    public void setStatut(int[] st) {
        this.statut = st;
    }

}

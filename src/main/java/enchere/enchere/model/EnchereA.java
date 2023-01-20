package enchere.enchere.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

import enchere.enchere.connexion.Connexion;

public class EnchereA {
    private long id;
    private String nomproduit;
    private int statut;
    private Timestamp datedebut;
    private Timestamp datefin;
    private int idutilisateur;
    private int idcategorie;
    private int idutilisateurgagnant;
    private double prixmin;
    private double prix;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNomproduit() {
        return nomproduit;
    }

    public void setNomproduit(String nomproduit) {
        this.nomproduit = nomproduit;
    }

    public int getStatut() {
        return statut;
    }

    public void setStatut(int statut) {
        this.statut = statut;
    }

    public Timestamp getDatedebut() {
        return datedebut;
    }

    public void setDatedebut(Timestamp datedebut) {
        this.datedebut = datedebut;
    }

    public Timestamp getDatefin() {
        return datefin;
    }

    public void setDatefin(Timestamp datefin) {
        this.datefin = datefin;
    }

    public int getIdutilisateur() {
        return idutilisateur;
    }

    public void setIdutilisateur(int idutilisateur) {
        this.idutilisateur = idutilisateur;
    }

    public int getIdcategorie() {
        return idcategorie;
    }

    public void setIdcategorie(int idcategorie) {
        this.idcategorie = idcategorie;
    }

    public int getIdutilisateurgagnant() {
        return idutilisateurgagnant;
    }

    public void setIdutilisateurgagnant(int idutilisateurgagnant) {
        this.idutilisateurgagnant = idutilisateurgagnant;
    }

    public double getPrixmin() {
        return prixmin;
    }

    public void setPrixmin(double prixmin) {
        this.prixmin = prixmin;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public EnchereA() {
    }

    public EnchereA(long id, String nomproduit, int statut, Timestamp datedebut, Timestamp datefin, int idutilisateur,
            int idcategorie, int idutilisateurgagnant, double prixmin, double prix) {
        this.id = id;
        this.nomproduit = nomproduit;
        this.statut = statut;
        this.datedebut = datedebut;
        this.datefin = datefin;
        this.idutilisateur = idutilisateur;
        this.idcategorie = idcategorie;
        this.idutilisateurgagnant = idutilisateurgagnant;
        this.prixmin = prixmin;
        this.prix = prix;
    }

    public static void insertEnchere(EnchereA ctg) throws Exception {
        PreparedStatement stat = null;
        Connection co = null;

        try {
            co = Connexion.getConnection();
            String requete = "INSERT INTO enchere VALUES(default,?,?,?,?,?,?,?,?,?)";
            stat = co.prepareStatement(requete);
            stat.setInt(1, ctg.getIdcategorie());
            stat.setInt(2, ctg.getIdutilisateur());
            stat.setString(3, ctg.getNomproduit());
            stat.setTimestamp(4, ctg.getDatedebut());
            stat.setTimestamp(5, ctg.getDatefin());
            stat.setInt(6, ctg.getIdutilisateurgagnant());
            stat.setDouble(7, ctg.getPrixmin());
            stat.setDouble(8, ctg.getPrix());
            stat.setInt(9, ctg.getStatut());
            stat.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (stat != null) {
                stat.close();
            }
            co.close();
        }
    }
}

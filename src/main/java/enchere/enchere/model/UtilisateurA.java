package enchere.enchere.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.persistence.*;

import enchere.enchere.connexion.Connexion;

public class UtilisateurA {
    private long id;
    private String nom;
    private String prenom;
    private String tel;
    private String logins;
    private String mdp;
    private double solde;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getSolde() {
        return solde;
    }

    public void setSolde(double solde) {
        this.solde = solde;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getLogins() {
        return logins;
    }

    public void setLogins(String logins) {
        this.logins = logins;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public static void insertUtilisateur(UtilisateurA ctg) throws Exception {
        PreparedStatement stat = null;
        Connection co = null;

        try {
            co = Connexion.getConnection();
            String requete = "INSERT INTO utilisateur VALUES(default,?,?,?,?,md5(?))";
            stat = co.prepareStatement(requete);
            stat.setString(1, ctg.getNom());
            stat.setString(2, ctg.getPrenom());
            stat.setString(3, ctg.getTel());
            stat.setString(4, ctg.getLogins());
            stat.setString(5, ctg.getMdp());
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

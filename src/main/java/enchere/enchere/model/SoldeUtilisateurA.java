package enchere.enchere.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

import enchere.enchere.connexion.Connexion;

public class SoldeUtilisateurA {
    int id;
    int idutilisateur;
    double solde;
    Date daterecharge;
    int statut;

    public SoldeUtilisateurA() {
    }

    public SoldeUtilisateurA(int id, int idutilisateur, double solde, Date daterecharge, int statut) {
        this.id = id;
        this.idutilisateur = idutilisateur;
        this.solde = solde;
        this.daterecharge = daterecharge;
        this.statut = statut;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdutilisateur() {
        return idutilisateur;
    }

    public void setIdutilisateur(int idutilisateur) {
        this.idutilisateur = idutilisateur;
    }

    public double getSolde() {
        return solde;
    }

    public void setSolde(double solde) {
        this.solde = solde;
    }

    public Date getDaterecharge() {
        return daterecharge;
    }

    public void setDaterecharge(Date daterecharge) {
        this.daterecharge = daterecharge;
    }

    public int getStatut() {
        return statut;
    }

    public void setStatut(int statut) {
        this.statut = statut;
    }

    public static double mySoldeUser(int id) throws Exception {
        Statement stat = null;
        Connection co = null;
        double solde = 0;

        try {
            co = Connexion.getConnection();
            String sql = "SELECT SUM(solde) as somme FROM soldeutilisateur WHERE idutilisateur = ? AND statut = 1";
            stat = co.createStatement();
            ResultSet rs = stat.executeQuery(sql);
            if (rs.next()) {
                solde = rs.getDouble("somme");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (stat != null) {
                stat.close();
            }
            co.close();
        }
        return solde;
    }

    public static void insertSoldeUtilisateurA(SoldeUtilisateurA ctg) throws Exception {
        PreparedStatement stat = null;
        Connection co = null;

        try {
            co = Connexion.getConnection();
            String requete = "INSERT INTO soldeutilisateur VALUES(default,?,?,?,?)";
            stat = co.prepareStatement(requete);
            stat.setInt(1, ctg.getIdutilisateur());
            stat.setDouble(2, ctg.getSolde());
            stat.setDate(3, ctg.getDaterecharge());
            stat.setInt(4, 0);

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

package enchere.enchere.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import enchere.enchere.connexion.Connexion;

public class SoldeUtilisateur {
    private long id;
    private Utilisateur utilisateur;
    private double solde;
    private Date daterecharge;
    private int idutilisateur;

    public SoldeUtilisateur(long u, double solde, Timestamp recharge) {
        this.idutilisateur = (int) u;
        this.setSolde(solde);
        this.setDaterecharge(new Date(recharge.getTime()));
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getIdutilisateur() {
        return idutilisateur;
    }

    public void setIdutilisateur(int utilisateur) {
        this.idutilisateur = utilisateur;
    }

    @Column(name = "solde", nullable = false)
    public double getSolde() {
        return solde;
    }

    public void setSolde(double solde) {
        this.solde = solde;
    }

    @Column(name = "daterecharge", nullable = false)
    public Date getDaterecharge() {
        return daterecharge;
    }

    public void setDaterecharge(Date daterecharge) {
        this.daterecharge = daterecharge;
    }

    public SoldeUtilisateur() {

    }

    public SoldeUtilisateur(int idutilisateur, double solde, Date daterecharge) {
        this.idutilisateur = idutilisateur;
        this.solde = solde;
        this.daterecharge = daterecharge;

    }

    public static void insertSoldeUtilisateur(SoldeUtilisateur ctg) throws Exception {
        PreparedStatement stat = null;
        Connection co = null;

        try {
            co = Connexion.getConnection();
            String requete = "INSERT INTO soldeutilisateur VALUES(default,?,?,?,?)";
            stat = co.prepareStatement(requete);
            stat.setInt(1, ctg.getIdutilisateur());
            stat.setDouble(2, ctg.getSolde());
            stat.setDate(3, ctg.getDaterecharge());
            stat.setInt(4, 1);

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

    // function selectById
    public SoldeUtilisateur soldeUtilisateur(int iduser) {
        Connection co = Connexion.getConnection();
        SoldeUtilisateur s = null;
        try {
            String sql = "SELECT * FROM V_TrueSolde where idutilisateur=" + iduser;
            Statement stat = co.createStatement();
            ResultSet res = stat.executeQuery(sql);
            if (res.next()) {
                s = new SoldeUtilisateur();
                s.setId(res.getLong("id"));
                s.setIdutilisateur(res.getInt("idutilisateur"));
                s.setSolde(res.getDouble("solde"));
                s.setDaterecharge(res.getDate("daterecharge"));

            }
            co.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return s;
    }

}

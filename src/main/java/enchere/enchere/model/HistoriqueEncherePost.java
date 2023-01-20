package enchere.enchere.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.data.mongodb.core.mapping.Document;

@Entity
@Table(name = "historiqueenchere")
public class HistoriqueEncherePost {
    private long id;
    private Utilisateur utilisateur;
    private int idenchere;
    private double montant;
    private Date dateenchere;

    public HistoriqueEncherePost() {

    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @ManyToOne
    @JoinColumn(name = "idutilisateur")
    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    @Column(name = "idenchere", nullable = false)
    public int getIdenchere() {
        return idenchere;
    }

    public void setIdenchere(int enchere) {
        this.idenchere = enchere;
    }

    @Column(name = "montant", nullable = false)
    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    @Column(name = "dateenchere", nullable = false)
    public Date getDateenchere() {
        return dateenchere;
    }

    public void setDateenchere(Date date) {
        this.dateenchere = date;
    }

}

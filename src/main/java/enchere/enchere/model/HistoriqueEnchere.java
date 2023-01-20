package enchere.enchere.model;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import enchere.enchere.repository.EnchereRepository;

@Document("historiqueenchere")
public class HistoriqueEnchere {

    private String id;

    private int idutilisateur;

    private int idenchere;

    private double montant;

    private Date dateenchere;

    // getters and setters

    public List<HistoriqueEnchere> historique(EnchereRepository rep, int iduser, List<HistoriqueEnchere> ls) {

        List<HistoriqueEnchere> temp = new ArrayList<>();
        for (HistoriqueEnchere historique : ls) {
            Enchere myenchere = historique.getEnchere(rep);
            if (myenchere.getUtilisateur().getId() == iduser) {
                temp.add(historique);
            }
        }

        return ls;
    }

    public HistoriqueEnchere() {

    }

    // getters and setters
    @Id
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getIdutilisateur() {
        return idutilisateur;
    }

    public void setIdutilisateur(int id) {
        this.idutilisateur = id;
    }

    public int getIdenchere() {
        return idenchere;
    }

    public void setIdenchere(int id) {
        this.idenchere = id;
    }

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    public Date getDateenchere() {
        return dateenchere;
    }

    public void setDateenchere(Date date) {
        this.dateenchere = date;
    }

    public void updateEnchereProperty(Enchere ench) {
        ench.setPrix(this.montant);
        ench.setUtilisateurgagnant(new Utilisateur(this.idutilisateur));
    }

    public Enchere getEnchere(EnchereRepository repo) {
        System.out.println("MYENCHE333" + this.idenchere);
        Enchere enc = repo.findById(new Long("2")).get();
        return enc;
    }

    public double getTotalSolde() {
        SoldeUtilisateur s = new SoldeUtilisateur().soldeUtilisateur(this.idutilisateur);
        return s.getSolde();
    }

    public boolean checkEnchereProperty(EnchereRepository rep) throws Exception {
        Enchere myenchere = this.getEnchere(rep);
        double solde = this.getTotalSolde();
        System.out.println("ench" + myenchere.getUtilisateur().getId());
        if (this.idutilisateur == myenchere.getUtilisateur().getId()) {
            throw new Exception("Cet utilisateur est le proprietaire de cet enchere");
        }

        if (solde < this.montant) {
            throw new Exception("Solde inferieur au montant");
        }

        if (this.montant < myenchere.getPrix()) {
            throw new Exception("Vous devez entrez un prix superieur au montant");
        }

        return true;
    }
}

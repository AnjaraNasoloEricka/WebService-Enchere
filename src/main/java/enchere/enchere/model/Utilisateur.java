package enchere.enchere.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.*;

@Entity
@Table(name = "utilisateur")
public class Utilisateur {
    private long id;
    private String nom;
    private String prenom;
    private String tel;
    private String logins;
    private String mdp;
    private TokenUtilisateur token = null;

    public Utilisateur(int id, String nom, String prenom, String tel, String logins, String mdp,
            TokenUtilisateur token) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.tel = tel;
        this.logins = logins;
        this.mdp = mdp;
        this.token = token;
    }

    public Utilisateur() {

    }

    public Utilisateur(int id) {
        this.id = (long) id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(name = "nom", nullable = false)
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @Column(name = "prenom", nullable = false)
    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    @Column(name = "tel", nullable = false)
    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    @Column(name = "logins", nullable = false)
    public String getLogins() {
        return logins;
    }

    public void setLogins(String logins) {
        this.logins = logins;
    }

    @Column(name = "mdp", nullable = false)
    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    @Transient
    public TokenUtilisateur getToken() {
        return token;
    }

    public void setToken(TokenUtilisateur t) {
        this.token = t;
    }

}

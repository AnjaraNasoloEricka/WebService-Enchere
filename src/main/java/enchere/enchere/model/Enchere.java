package enchere.enchere.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDate;
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
import javax.persistence.Transient;

import enchere.enchere.connexion.Connexion;
import enchere.enchere.repository.EnchereRepository;
import enchere.enchere.repository.HistoriqueEnchereRepository;
import enchere.enchere.service.SignalService;

@Entity
@Table(name = "enchere")
public class Enchere {
    private long id;
    private String nomproduit;
    private int statut;
    private Timestamp datedebut;
    private Timestamp datefin;
    private Utilisateur utilisateur;
    private Categorie categorie;
    private Utilisateur utilisateurgagnant;
    private double prixmin;
    private double prix;

    @Transient
    private List<ImageEnchereA> allimage;

    public List<ImageEnchereA> initAllImageEnchere() throws Exception {
        List<ImageEnchereA> allimage = new ArrayList<ImageEnchereA>();
        Connection conn = Connexion.getConnection();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM imageenchere WHERE idenchere = " + this.id);
        while (rs.next()) {
            ImageEnchereA image = new ImageEnchereA();
            image.setId(rs.getInt("id"));
            image.setBase8(rs.getString("base8"));
            image.setIdEnchere(rs.getInt("idenchere"));
            allimage.add(image);
        }
        return allimage;
    }

    @Transient
    public List<ImageEnchereA> getAllimage() {
        return allimage;
    }

    public void setAllimage(List<ImageEnchereA> allimage) {
        this.allimage = allimage;
    }

    public Enchere() {
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

    @ManyToOne
    @JoinColumn(name = "idutilisateurgagnant", nullable = true)
    public Utilisateur getUtilisateurgagnant() {
        return utilisateurgagnant;
    }

    public void setUtilisateurgagnant(Utilisateur utilisateur) {
        this.utilisateurgagnant = utilisateur;
    }

    @ManyToOne
    @JoinColumn(name = "idcategorie")
    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

    public boolean checkFini() {
        Date now = Date.valueOf(LocalDate.now());
        System.out.println("now" + now + " datefin" + this.getDatefin());

        if (now.getTime() >= this.getDatefin().getTime()) {
            return true;
        }
        return false;
    }

    public void actionFini(EnchereRepository erep, Timestamp fin, List<HistoriqueEnchere> allhist) throws Exception {
        this.statut = 1;
        erep.save(this);
        System.out.println("enchere termine");
        String requestEnchere = "Enchere " + this.getId() + " du produit " + this.getNomproduit() + " est fini";
        if (this.getUtilisateurgagnant() != null) {
            SoldeUtilisateur s = new SoldeUtilisateur((int) this.getUtilisateurgagnant().getId(),
                    (this.getPrix() * (-1.0)), fin);
            SoldeUtilisateur.insertSoldeUtilisateur(s);
            requestEnchere += " et le gagnant est " + this.getUtilisateurgagnant().getNom() + " "
                    + this.getUtilisateurgagnant().getPrenom();

        }
        String requestidUser = HistoriqueEnchere.knowAllUser(allhist);
        // String requestBody =
        // "{\"app_id\":\"f43c166c-4351-40de-8114-d992abf563f4\",\"include_external_user_ids"
        // + requestidUser + "\",\"contents\": {\"en\": \""
        // + requestEnchere + " \"},\"name\": \"INTERNAL_CAMPAIGN_NAME\"}";
        String requestBody = "{\"app_id\":\"f43c166c-4351-40de-8114-d992abf563f4\",\"included_segments\":[\"Subscribed Users\"],\"contents\": {\"en\": \""
                + requestEnchere + " \"},\"name\": \"INTERNAL_CAMPAIGN_NAME\"}";
        new SignalService().callWS(requestBody);
    }

    public void setFini(EnchereRepository erep, HistoriqueEnchereRepository hrep) throws Exception {
        if (this.checkFini()) {
            List<HistoriqueEnchere> allhist = hrep.findByEnchere((int) this.id);
            this.actionFini(erep, datefin, allhist);

        }
    }

    @Column(name = "prixmin", nullable = true)
    public double getPrixmin() {
        return prixmin;
    }

    public void setPrixmin(double prix) {
        this.prixmin = prix;
    }

    @Column(name = "prix", nullable = false)
    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    @Column(name = "nomproduit", nullable = false)
    public String getNomproduit() {
        return nomproduit;
    }

    public void setNomproduit(String nomproduit) {
        this.nomproduit = nomproduit;
    }

    @Column(name = "statut", nullable = false)
    public int getStatut() {
        return statut;
    }

    public void setStatut(int statut) {
        this.statut = statut;
    }

    @Column(name = "datedebut", nullable = false)
    public Timestamp getDatedebut() {
        return datedebut;
    }

    public void setDatedebut(Timestamp datedebut) {
        this.datedebut = datedebut;
    }

    @Column(name = "datefin", nullable = false)
    public Timestamp getDatefin() {
        return datefin;
    }

    public void setDatefin(Timestamp datefin) {
        this.datefin = datefin;
    }

    public String setSQLwithDate(String sql, Date[] date) {
        if (date != null) {
            for (int i = 0; i < date.length; i++) {
                if (date[i] != null) {
                    sql += " and datedebut";
                    if (i == 0) {
                        sql += ">=";
                    } else {
                        sql += "<=";
                    }
                    sql += "'" + date[i].toString() + "'";
                }
            }
        }
        return sql;
    }

    public String setSQLwithcategorie(String sql, int[] idCategorie) {
        if (idCategorie != null) {
            for (int i = 0; i < idCategorie.length; i++) {
                if (i == 0) {
                    sql += " and (idcategorie=" + idCategorie[i];
                } else {
                    sql += " or idcategorie=" + idCategorie[i];
                }
                if (i == (idCategorie.length - 1)) {
                    sql += ")";
                }
            }
        }
        return sql;
    }

    public String setSQLwithstatut(String sql, int[] statut) {
        if (statut != null) {
            for (int i = 0; i < statut.length; i++) {
                if (i == 0) {
                    sql += " and (statut=" + statut[i];
                } else {
                    sql += " or statut=" + statut[i];
                }
                if (i == (statut.length - 1)) {
                    sql += ")";
                }
            }
        }
        return sql;
    }

    public String setSQLwithPrix(String sql, int[] prix) {
        if (prix != null) {
            for (int i = 0; i < prix.length; i++) {
                if (prix[i] != 0) {
                    sql += " and prix";
                    if (i == 0) {
                        sql += ">=";
                    } else {
                        sql += "<=";
                    }
                    sql += prix[i];

                }
            }
        }
        return sql;
    }

    public String getSearchSQL(RechercheEnchere recherche) {
        String sql = "SELECT * FROM Enchere WHERE 1=1 ";
        if (recherche.getMotcle().length() != 0) {
            sql += " and nomproduit like '%" + recherche.getMotcle() + "%'";
        }
        Date[] date = recherche.getDate();
        sql = this.setSQLwithDate(sql, date);
        int[] idcategorie = recherche.getIdcategorie();
        sql = this.setSQLwithcategorie(sql, idcategorie);
        sql = this.setSQLwithPrix(sql, recherche.getPrix());
        sql = this.setSQLwithstatut(sql, recherche.getStatut());
        return sql;
    }

    public List<Enchere> getAllEnchere(String sql) {
        Connection co = Connexion.getConnection();
        Statement stat = null;
        List<Enchere> lsenchere = new ArrayList<Enchere>();
        try {
            stat = co.createStatement();
            ResultSet result = stat.executeQuery(sql);
            while (result.next()) {
                Enchere temp = new Enchere();
                temp.setId((long) result.getInt("id"));
                temp.setCategorie(new Categorie(result.getInt("idcategorie")));
                temp.setUtilisateur(new Utilisateur(result.getInt("idutilisateur")));
                temp.setNomproduit(result.getString("nomproduit"));
                temp.setDatedebut(result.getTimestamp("datedebut"));
                temp.setDatefin(result.getTimestamp("datefin"));
                temp.setUtilisateurgagnant(new Utilisateur(result.getInt("idutilisateurgagnant")));
                temp.setPrixmin(result.getDouble("prixmin"));
                temp.setPrix(result.getDouble("prix"));
                temp.setStatut(result.getInt("statut"));
                lsenchere.add(temp);
            }
            co.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lsenchere;

    }

}

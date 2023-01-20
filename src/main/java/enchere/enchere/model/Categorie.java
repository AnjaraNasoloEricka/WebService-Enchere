package enchere.enchere.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "categorie")
public class Categorie {
    private long id;
    private String typecategorie;

    public Categorie() {
    }

    public Categorie(int id) {
        this.id=(long)id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(name = "typecategorie", nullable = false)
    public String getTypecategorie() {
        return typecategorie;
    }

    public void setTypecategorie(String typecategorie) {
        this.typecategorie = typecategorie;
    }

}

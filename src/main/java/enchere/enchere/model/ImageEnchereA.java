package enchere.enchere.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import enchere.enchere.connexion.Connexion;

public class ImageEnchereA {
    int id;
    int idEnchere;
    String base8;

    public ImageEnchereA() {
    }

    public ImageEnchereA(int id, int idEnchere, String base8) {
        this.id = id;
        this.idEnchere = idEnchere;
        this.base8 = base8;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdEnchere() {
        return idEnchere;
    }

    public void setIdEnchere(int idEnchere) {
        this.idEnchere = idEnchere;
    }

    public String getBase8() {
        return base8;
    }

    public void setBase8(String base8) {
        this.base8 = base8;
    }

    public void insertImageEnchereA(ImageEnchereA ctg) throws Exception {
        PreparedStatement stat = null;
        Connection co = null;

        try {
            co = Connexion.getConnection();
            String requete = "INSERT INTO ImageEnchere VALUES(default,?,?)";
            stat = co.prepareStatement(requete);
            stat.setInt(1, ctg.getIdEnchere());
            stat.setString(2, ctg.getBase8());

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

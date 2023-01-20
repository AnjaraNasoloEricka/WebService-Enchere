package enchere.enchere.model;

import org.springframework.util.DigestUtils;

import java.io.Serializable;
import java.math.BigInteger;
import java.security.*;
import java.sql.Connection;
import java.sql.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class TokenUtilisateur implements Serializable {
    private int id;
    private String token;
    private int idutilisateur;
    private Timestamp dateexpiration;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getIdutilisateur() {
        return idutilisateur;
    }

    public TokenUtilisateur(int id, String token, int idutilisateur, Timestamp dateexpiration) {
        this.id = id;
        this.token = token;
        this.idutilisateur = idutilisateur;
        this.dateexpiration = dateexpiration;
    }

    public TokenUtilisateur() {
    }

    public void setIdutilisateur(int idutilisateur) {
        this.idutilisateur = idutilisateur;
    }

    public Timestamp getDateexpiration() {
        return dateexpiration;
    }

    public TokenUtilisateur(String token, int idutilisateur) {
        this.token = token;
        this.idutilisateur = idutilisateur;
    }

    public void setDateexpiration(Timestamp dateexpiration) {
        this.dateexpiration = dateexpiration;
    }

    // Hash Text
    private String encryptToken(String mytext) throws Exception {
        MessageDigest md = MessageDigest.getInstance("SHA-1");
        byte[] messageDigest = md.digest(mytext.getBytes());
        BigInteger no = new BigInteger(1, messageDigest);
        String hashtext = no.toString(16);
        return hashtext;
    }

    // Generate token
    public void generateToken(Connection co, int idutilisateur) throws Exception {
        Time delaiToken = Time.valueOf("01:00:00"); // delai du Token
        Timestamp now = Timestamp.valueOf(LocalDateTime.now());
        String token = this.encryptToken(idutilisateur + now.toString());
        Timestamp expiration = new Timestamp(delaiToken.getTime() + now.getTime() + Time.valueOf("06:00:00").getTime());
        this.insertToken(co, idutilisateur, token, expiration);
        this.idutilisateur = idutilisateur;
        this.token = token;
        this.dateexpiration = expiration;
    }

    // insert token in database
    public void insertToken(Connection co, int idutilisateur, String token, Timestamp expiration) throws Exception {
        String sql = "INSERT INTO TokenUtilisateur(idutilisateur,token,dateexpiration)VALUES(" + idutilisateur + ",'"
                + token + "','" + expiration + "');";
        Statement stat = co.createStatement();
        stat.executeUpdate(sql);
    }

    // check the expiration of the token
    public TokenUtilisateur checkIfExpire(Connection co) throws Exception {
        String sql = "SELECT * FROM V_TokenNonExpire WHERE token='" + this.token + "' and idutilisateur="
                + this.idutilisateur;
        Statement stat = co.createStatement();
        ResultSet result = stat.executeQuery(sql);
        if (result.next()) {
            return new TokenUtilisateur(result.getInt("id"), result.getString("token"), result.getInt("idutilisateur"),
                    result.getTimestamp("dateexpiration"));
        }

        return null;
    }
}

package enchere.enchere.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import enchere.enchere.model.Utilisateur;

@Repository
public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {

    @Query("select u from Utilisateur u where u.logins = :#{#utilisateur.logins} and u.mdp = md5(:#{#utilisateur.mdp})")
    public Utilisateur login(@Param("utilisateur") Utilisateur utilisateur);

    @Query("SELECT u FROM Utilisateur u WHERE u.logins ='johndoe' AND u.mdp = md5('123')")
    public Utilisateur findByLogins(String logins, String mdp);
}

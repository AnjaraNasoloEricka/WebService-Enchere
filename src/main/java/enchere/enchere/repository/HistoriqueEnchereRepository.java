package enchere.enchere.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.*;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import enchere.enchere.model.HistoriqueEnchere;
import enchere.enchere.model.HistoriqueEncherePost;

@Repository
public interface HistoriqueEnchereRepository extends MongoRepository<HistoriqueEnchere, Long> {

    @Query("{idutilisateur:?0}")
    List<HistoriqueEnchere> findByUser(int idutilisateur);

}

package enchere.enchere.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import enchere.enchere.model.Enchere;

@Repository
public interface EnchereRepository extends JpaRepository<Enchere, Long> {

    @Query("SELECT e FROM Enchere e WHERE statut=0")
    public List<Enchere> findNotFinished();

    @Query("SELECT e FROM Enchere e WHERE id=?0")
    public Enchere findByIdEnchere(int id);

}

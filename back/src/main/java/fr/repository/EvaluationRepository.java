package fr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import fr.entity.Evaluation;

public interface EvaluationRepository extends JpaRepository<Evaluation, Integer> {
    
    @Query("SELECT r FROM Evaluation r WHERE r.prestation.id = :id")
    Iterable<Evaluation> findAllByPrestationId(@Param("id") Integer id);

    @Query("SELECT r, p FROM Evaluation r JOIN Prestation p")
    Iterable<Evaluation> findAllPrestationWithComment();
}

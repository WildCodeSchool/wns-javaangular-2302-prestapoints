package fr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import fr.model.Prestation;

public interface PrestationRepository extends JpaRepository<Prestation, Long> {

    @Query("SELECT p FROM Prestation p JOIN p.registeredUsers ru WHERE ru.id = :id")
    Iterable<Prestation> findAllPrestationsInUser(@Param("id") Long id);
}

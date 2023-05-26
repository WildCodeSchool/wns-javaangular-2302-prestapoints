package fr.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import fr.entity.Registration;

public interface RegistrationRepository extends JpaRepository<Registration, Integer> {
    
    @Query("SELECT r FROM Registration r WHERE r.prestation.id = :id")
    List<Registration> findAllByPrestationId(@Param("id") Integer id);

    @Query("SELECT r, p FROM Registration r JOIN Prestation p")
    Iterable<Registration> findAllPrestationWithComment();
}

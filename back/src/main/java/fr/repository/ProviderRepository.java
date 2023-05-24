package fr.repository;

// import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import fr.model.Registration;

// @Repository
public interface ProviderRepository extends JpaRepository<Registration, Long>{
    
}

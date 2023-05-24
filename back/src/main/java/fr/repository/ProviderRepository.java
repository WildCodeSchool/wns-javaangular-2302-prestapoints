package fr.repository;

// import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import fr.entity.Provider;

// @Repository
public interface ProviderRepository extends JpaRepository<Provider, Long>{
    
}

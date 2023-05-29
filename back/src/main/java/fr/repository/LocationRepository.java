package fr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.entity.Location;

@Repository
public interface LocationRepository extends JpaRepository<Location, Integer> {

}
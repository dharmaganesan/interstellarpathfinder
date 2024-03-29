package za.co.discovery.assignment.ganesan.repository;

import za.co.discovery.assignment.ganesan.entity.Planet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlanetRepository extends JpaRepository<Planet, String> {

}

package za.co.discovery.assignment.ganesan.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.co.discovery.assignment.ganesan.entity.PlanetRoute;

@Repository
public interface PlanetRouteRepository extends JpaRepository<PlanetRoute, Long> {

}

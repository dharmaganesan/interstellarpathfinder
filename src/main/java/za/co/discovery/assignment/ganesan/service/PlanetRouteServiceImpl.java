package za.co.discovery.assignment.ganesan.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import za.co.discovery.assignment.ganesan.dto.PlanetRouteDto;
import za.co.discovery.assignment.ganesan.entity.Planet;
import za.co.discovery.assignment.ganesan.entity.PlanetRoute;
import za.co.discovery.assignment.ganesan.repository.PlanetRouteRepository;

@Service
@Transactional
public class PlanetRouteServiceImpl extends AbstractCrudService<PlanetRouteDto, PlanetRoute, Long> implements PlanetRouteService {


    public PlanetRouteServiceImpl(PlanetRouteRepository repository) {
        super(repository);
    }

    @Override
    protected PlanetRouteDto toDto(PlanetRoute entity) {
        PlanetRouteDto planetRouteDto = new PlanetRouteDto();
        planetRouteDto.setId(entity.getId());
        planetRouteDto.setOrigin(entity.getOrigin().getId());
        planetRouteDto.setDestination(entity.getDestination().getId());
        planetRouteDto.setDistance(entity.getDistance());
        planetRouteDto.setTraffic(entity.getTraffic());
        return planetRouteDto;
    }

    @Override
    protected PlanetRoute toEntity(PlanetRouteDto dto) {
        PlanetRoute entity = new PlanetRoute();
        entity.setId(dto.getId());
        entity.setOrigin(Planet.builder().id(dto.getOrigin()).build());
        entity.setDestination(Planet.builder().id(dto.getDestination()).build());
        entity.setDistance(dto.getDistance());
        entity.setTraffic(dto.getTraffic());
        return entity;
    }
}

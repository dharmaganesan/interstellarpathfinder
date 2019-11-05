package za.co.discovery.assignment.ganesan.service;

import org.springframework.transaction.annotation.Transactional;
import za.co.discovery.assignment.ganesan.dto.PlanetDto;
import za.co.discovery.assignment.ganesan.entity.Planet;
import za.co.discovery.assignment.ganesan.repository.PlanetRepository;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class PlanetServiceImpl extends AbstractCrudService<PlanetDto, Planet, String> implements PlanetService {

    public PlanetServiceImpl(PlanetRepository repository) {
        super(repository);
    }

    @Override
    protected PlanetDto toDto(Planet entity) {
        PlanetDto planetDto = new PlanetDto();
        planetDto.setId(entity.getId());
        planetDto.setName(entity.getName());
        return planetDto;
    }

    @Override
    protected Planet toEntity(PlanetDto dto) {
        Planet entity = new Planet();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        return entity;
    }
}

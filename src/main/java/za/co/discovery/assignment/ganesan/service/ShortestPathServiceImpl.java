package za.co.discovery.assignment.ganesan.service;

import org.springframework.stereotype.Service;
import za.co.discovery.assignment.ganesan.dto.PlanetRouteDto;
import za.co.discovery.assignment.ganesan.dto.ShortestPathRequestDto;
import za.co.discovery.assignment.ganesan.dto.ShortestPathResponseDto;

import java.util.function.Function;

@Service
public class ShortestPathServiceImpl implements ShortestPathService {

    private final PlanetRouteService planetRouteService;

    public ShortestPathServiceImpl(PlanetRouteService planetRouteService) {
        this.planetRouteService = planetRouteService;
    }


    @Override
    public ShortestPathResponseDto findShortestPath(ShortestPathRequestDto shortestPathRequestDto) {
        Function<PlanetRouteDto, Double> weightFunc = shortestPathRequestDto.isIncludeTraffic() ? PlanetRouteDto::getDistancePlusTraffic : PlanetRouteDto::getDistance;
        ShortestPath shortestPath = new ShortestPath(planetRouteService.getAll(), weightFunc);
        shortestPath.execute(shortestPathRequestDto.getSource());
        return new ShortestPathResponseDto(shortestPath.getPath(shortestPathRequestDto.getDestination()));
    }


}

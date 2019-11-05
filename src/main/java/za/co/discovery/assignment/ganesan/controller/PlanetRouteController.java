package za.co.discovery.assignment.ganesan.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import za.co.discovery.assignment.ganesan.dto.PlanetDto;
import za.co.discovery.assignment.ganesan.dto.PlanetRouteDto;
import za.co.discovery.assignment.ganesan.service.PlanetRouteService;
import za.co.discovery.assignment.ganesan.service.PlanetService;

@RestController
@RequestMapping("/planetroute")
public class PlanetRouteController extends CrudController<PlanetRouteDto, Long> {


    protected PlanetRouteController(PlanetRouteService service) {
        super(service);
    }
}

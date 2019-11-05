package za.co.discovery.assignment.ganesan.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import za.co.discovery.assignment.ganesan.dto.PlanetDto;
import za.co.discovery.assignment.ganesan.service.PlanetService;

@RestController
@RequestMapping("/planet")
public class PlanetController extends CrudController<PlanetDto, String> {


    protected PlanetController(PlanetService planetService) {
        super(planetService);
    }
}

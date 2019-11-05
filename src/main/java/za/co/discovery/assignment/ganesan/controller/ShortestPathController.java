package za.co.discovery.assignment.ganesan.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import za.co.discovery.assignment.ganesan.dto.ShortestPathRequestDto;
import za.co.discovery.assignment.ganesan.dto.ShortestPathResponseDto;
import za.co.discovery.assignment.ganesan.service.ShortestPathService;

import javax.validation.Valid;

@RestController
public class ShortestPathController {

    private final ShortestPathService shortestPathService;

    public ShortestPathController(ShortestPathService shortestPathService) {
        this.shortestPathService = shortestPathService;
    }

    @GetMapping
    @RequestMapping("/shortestpath")
    public ShortestPathResponseDto findShortestPath(@Valid ShortestPathRequestDto requestDto) {
        return shortestPathService.findShortestPath(requestDto);
    }
}

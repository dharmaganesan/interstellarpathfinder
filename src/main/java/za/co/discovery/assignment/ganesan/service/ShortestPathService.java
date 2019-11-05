package za.co.discovery.assignment.ganesan.service;


import za.co.discovery.assignment.ganesan.dto.ShortestPathRequestDto;
import za.co.discovery.assignment.ganesan.dto.ShortestPathResponseDto;

public interface ShortestPathService {

    ShortestPathResponseDto findShortestPath(ShortestPathRequestDto shortestPathRequestDto);

}

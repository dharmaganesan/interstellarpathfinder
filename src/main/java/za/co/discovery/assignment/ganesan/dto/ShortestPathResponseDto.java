package za.co.discovery.assignment.ganesan.dto;

import lombok.Value;

import java.util.List;

@Value
public class ShortestPathResponseDto {

    private List<String> shortestPath;

}

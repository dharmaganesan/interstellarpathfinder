package za.co.discovery.assignment.ganesan.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class ShortestPathRequestDto {

    @NotNull
    private String source;
    @NotNull
    private String destination;

    private boolean includeTraffic;

}

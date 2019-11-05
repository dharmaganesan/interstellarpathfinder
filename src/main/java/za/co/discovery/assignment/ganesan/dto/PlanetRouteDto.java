package za.co.discovery.assignment.ganesan.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.Wither;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Wither
public class PlanetRouteDto implements Dto {


    private Long id;
    @NotNull
    private String origin;
    @NotNull
    private String destination;
    @NotNull
    private Double distance;
    @NotNull
    private Double traffic;

    @JsonIgnore
    public Double getDistancePlusTraffic() {
        return distance + traffic;
    }
}

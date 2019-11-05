package za.co.discovery.assignment.ganesan.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlanetDto implements Dto<String> {

    @NotNull
    private String id;
    @NotNull
    private String name;

}

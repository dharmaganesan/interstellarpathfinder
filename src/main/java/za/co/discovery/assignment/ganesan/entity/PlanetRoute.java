package za.co.discovery.assignment.ganesan.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@javax.persistence.Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlanetRoute implements Entity {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Planet origin;

    @ManyToOne
    private Planet destination;
    private Double distance;
    private Double traffic;

}

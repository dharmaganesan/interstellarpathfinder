package za.co.discovery.assignment.ganesan.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;

@Data
@javax.persistence.Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Planet implements Entity {

    @Id
    private String id;
    private String name;

}

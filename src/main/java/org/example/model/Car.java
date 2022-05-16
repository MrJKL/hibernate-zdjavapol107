package org.example.model;

// użycie lombok

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class Car {
    private String model;
    private int productionYear;
    private int amountOfDoors;
}

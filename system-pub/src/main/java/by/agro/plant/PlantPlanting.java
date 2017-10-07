package by.agro.plant;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PlantPlanting {

    private long id;
    private String name;
    private double sowingArea;
    private String maintainer;
}

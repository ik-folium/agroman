package by.agro.performer;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Performer {

    private long id;
    private String firstName;
    private String lastName;
    private int age;

}

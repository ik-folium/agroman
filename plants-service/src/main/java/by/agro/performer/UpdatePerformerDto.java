package by.agro.performer;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UpdatePerformerDto {

    private long id;
    private String firstName;
    private String lastName;
    private int age;
}

package co.com.orchestation.consumer.commons.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class PersonDTO {
    private int id;

    private String firstName;

    private Boolean isUser;

}

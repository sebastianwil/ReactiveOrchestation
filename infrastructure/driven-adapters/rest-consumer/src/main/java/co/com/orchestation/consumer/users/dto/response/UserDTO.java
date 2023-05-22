package co.com.orchestation.consumer.users.dto.response;

    import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
    import lombok.Builder;
    import lombok.AllArgsConstructor;
    import lombok.Getter;
    import lombok.NoArgsConstructor;
    import lombok.Setter;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder(toBuilder = true)
    @JsonIgnoreProperties(ignoreUnknown = true)
public class UserDTO {
            private int userId;

            private String firstName;

            private String lastName;

}
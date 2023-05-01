package co.com.orchestation.consumer.users.dto.response;

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
public class UserDTO {
            private int userId;

            private String firstname;

            private String lastname;


}
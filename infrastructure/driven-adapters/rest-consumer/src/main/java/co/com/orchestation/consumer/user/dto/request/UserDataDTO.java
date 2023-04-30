package co.com.orchestation.consumer.user.dto.request;

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
public class UserDataDTO {

private String val1;
private String val2;

}

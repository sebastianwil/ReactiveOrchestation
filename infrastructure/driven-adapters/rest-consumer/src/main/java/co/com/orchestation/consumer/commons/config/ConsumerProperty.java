package co.com.orchestation.consumer.commons.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Configuration
@AllArgsConstructor
@NoArgsConstructor
@ConfigurationProperties(prefix="consumer")
public class ConsumerProperty {

    private String hostApi1;
    private String hostApi2;
}

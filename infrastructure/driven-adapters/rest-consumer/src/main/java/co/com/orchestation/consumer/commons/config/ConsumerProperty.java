package co.com.orchestation.consumer.commons.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix="consumer")
public class ConsumerProperty {

    private String hostApi1;
    private String hostApi2;
}

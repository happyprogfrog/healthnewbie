package me.progfrog.healthnewbie.user.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("server")
@Getter
@Setter
public class ServerProperties {

    private String url;
    private String port;
}

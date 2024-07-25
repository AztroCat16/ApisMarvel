package com.challenge.apismarvel.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "app")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AppConfig {

    @Value("${server.servlet.context-path}")
    private String contextVersion;

    private String marvelApi;
    private String publicKey;
    private String privateKey;

    private String allowedOrigins = "*";
    private String allowedMethods = "GET";
    private String allowedHeaders = "Access-Control-Allow-Origin, Access-Control-Allow-Headers, Access-Control-Allow-Methods, Accept, "
            + "Authorization, Content-Type, Method, Origin, X-Forwarded-For, X-Real-IP";
    private String exposedHeaders = "X-Get-Header, Authorization";

}

package me.progfrog.healthnewbie.user.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

// http://localhost:8081/swagger-ui/index.html
@OpenAPIDefinition(
        info = @Info(title = "API 명세서", description = "운동 좋아하는 사람 모여라 <헬린이>의 API 명세서", version = "1.0")
)
@Configuration
public class SwaggerConfig {
}
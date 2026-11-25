package com.example.systeminitbackend.config

import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class OpenApiConfig {

    @Bean
    fun openApi(): OpenAPI = OpenAPI()
        .info(
            Info()
                .title("System Init Backend API")
                .description("시스템 초기 구성(계정·메뉴·권한·공통코드·알림) API")
                .version("v1"),
        )
}

package com.example.systeminitbackend.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.domain.AuditorAware
import org.springframework.data.jpa.repository.config.EnableJpaAuditing
import java.util.Optional

@Configuration
@EnableJpaAuditing
class JpaAuditingConfig {

    @Bean
    fun auditorAware(): AuditorAware<Long> = AuditorAware { Optional.of(0L) }
}

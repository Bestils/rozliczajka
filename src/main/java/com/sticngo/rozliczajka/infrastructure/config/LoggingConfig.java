package com.sticngo.rozliczajka.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.sticngo.rozliczajka.infrastructure.logging.LoggingAspect;


@Configuration
class LoggingConfig {

  @Bean
  LoggingAspect loggingAspect() {
    return new LoggingAspect();
  }
}

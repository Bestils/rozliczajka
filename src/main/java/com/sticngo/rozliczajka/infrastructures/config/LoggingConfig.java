package com.sticngo.rozliczajka.infrastructures.config;

import com.sticngo.rozliczajka.infrastructures.logging.LoggingAspect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.sticngo.rozliczajka.infrastructures.logging.LoggingAspect;


@Configuration
class LoggingConfig {

  @Bean
  LoggingAspect loggingAspect() {
    return new LoggingAspect();
  }
}

package com.sticngo.rozliczajka.domain.calculations;


import com.sticngo.rozliczajka.domain.members.MemberService;
import com.sticngo.rozliczajka.domain.user.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class CalculationConfiguration {

  @Bean
  public CalculationService calculationService(CalculationRepository calculationRepository, UserService userService, MemberService memberService) {
    return new CalculationService(calculationRepository, userService, memberService);
  }

  public CalculationService calculationService(UserService userService, MemberService memberService) {
    return calculationService(new InMemoryCalculationRepository(), userService, memberService);
  }
}

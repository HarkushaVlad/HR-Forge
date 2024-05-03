package com.vhark.hrforgeapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class HrForgeApiApplication {

  public static void main(String[] args) {
    SpringApplication.run(HrForgeApiApplication.class, args);
  }
}

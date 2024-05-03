package com.vhark.hrforgeapi.employee;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Token {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String token;

  private LocalDateTime createdAt;

  private LocalDateTime expiresAt;

  private LocalDateTime validatedAt;

  @ManyToOne
  @JoinColumn(name = "employeeId", nullable = false)
  private Employee employee;
}

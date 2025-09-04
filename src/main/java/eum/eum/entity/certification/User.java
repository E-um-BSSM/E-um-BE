package eum.eum.entity.certification;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String email;
    private String password; // BCrypt로 암호화 저장
    private Integer strength = 1000;

    @Column(name = "system_role")
    private String systemRole; // student | admin
    private String bsmId;
    private LocalDateTime createdAt = LocalDateTime.now();
}

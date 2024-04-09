package apiBoschEsy.apiInSpringBoot.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity(name = "User")
@Table(name = "user")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String login;
    private String password;
}

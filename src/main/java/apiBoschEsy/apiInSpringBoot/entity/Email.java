package apiBoschEsy.apiInSpringBoot.entity;

import apiBoschEsy.apiInSpringBoot.constants.StatusEmail;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity(name = "Email")
@Table(name = "email_table")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "email_id")
public class Email {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long email_id;
    private String ownerRef;
    private String emailFrom;
    private String emailTo;
    private String title_email;
    private String body;
    private LocalDate sendDateEmail;
    private LocalTime timeEmail;
    private StatusEmail statusEmail;
}

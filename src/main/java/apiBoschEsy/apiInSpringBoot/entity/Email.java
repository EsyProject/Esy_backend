package apiBoschEsy.apiInSpringBoot.entity;

import apiBoschEsy.apiInSpringBoot.constants.StatusEmail;
import apiBoschEsy.apiInSpringBoot.dto.email.DataRegisterEmail;
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

    // Final emoji
    private final String emojihappy = "\\uD83D\\uDE0A";

    // Attributes
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long email_id;
    private String ownerRef;
    private String emailFrom;
    private String emailTo;
    private String title_email;
    @Column(columnDefinition = "TEXT")
    private String body;
    private LocalDate sendDateEmail;
    private LocalTime timeEmail;
    private StatusEmail statusEmail;

    //    private MultipartFile imageQRCode;

    // Constructor with DataRegisterEmail
    public Email(DataRegisterEmail dataRegisterEmail){
        this.emailFrom = dataRegisterEmail.emailFrom();
        this.emailTo = dataRegisterEmail.emailTo();
    }

}

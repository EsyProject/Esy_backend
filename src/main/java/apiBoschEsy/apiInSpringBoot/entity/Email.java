package apiBoschEsy.apiInSpringBoot.entity;

import apiBoschEsy.apiInSpringBoot.constants.StatusEmail;
import apiBoschEsy.apiInSpringBoot.dto.email.DataRegisterEmail;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.cglib.core.Local;
import org.springframework.web.multipart.MultipartFile;

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

    // Constructor

    public Email(DataRegisterEmail data){
        this.ownerRef = data.ownerRef();
        this.title_email = data.title_email();
        this.emailTo = data.emailTo();
        this.emailFrom = data.emailFrom();
    }

    // Method
    public String emailDefaultSendTicket(String username){
        // Creating attributes
        LocalTime currentTime = LocalTime.now();

        LocalTime inTheAfternoon = LocalTime.of(12,0);

        if(currentTime.isBefore(inTheAfternoon)){
            return "Bom Dia " + username + "!" + "Nós agradecemos por estar interessado em ir ao nosso evento.\n" +
                    "Espero que goste!" +
                    "\nEstamos enviando a imagem do seu QRCode para que possa autenticar na hora da entrada: ";

        }
        return "Bom Tarde " + username + "!" + "Nós agradecemos por estar interessado em ir no nosso evento.\n" +
                "Espero que goste!\n" +
                "\nEstamos enviando a imagem do seu QRCode para que possa autenticar na hora da entrada: ";
    }
}

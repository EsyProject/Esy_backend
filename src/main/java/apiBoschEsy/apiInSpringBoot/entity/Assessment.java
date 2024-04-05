    package apiBoschEsy.apiInSpringBoot.entity;

    import apiBoschEsy.apiInSpringBoot.dto.assessment.DataRegisterAssessment;
    import com.fasterxml.jackson.annotation.JsonFormat;
    import jakarta.persistence.*;
    import lombok.*;
    import org.springframework.format.annotation.DateTimeFormat;

    import java.time.LocalDate;
    import java.time.LocalTime;
    import java.time.format.DateTimeFormatter;

    @Entity(name = "Assessment")
    @Table(name = "assessment")
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @EqualsAndHashCode(of = "id")
    public class Assessment {
        // Attributes of Assessment
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @DateTimeFormat(pattern = "dd-MM-yyyy")
        private String date_created;
        @JsonFormat(pattern = "HH:mm:ss")
        private String hour;
        @Column(unique = true)
        private String name;
        private String description_comment;
        private String suggestion;
        private String assessment;

        public Assessment(DataRegisterAssessment data){
            this.name = data.name();
            this.description_comment = data.description_comment();
            this.assessment = data.assessment();
            this.hour = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
            this.suggestion = data.suggestion();
            this.date_created = LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        }
    }

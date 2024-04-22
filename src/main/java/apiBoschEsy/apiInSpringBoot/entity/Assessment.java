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
    @EqualsAndHashCode(of = "assessment_id")
    public class Assessment {
        // Attributes of Assessment
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long assessment_id;

        @DateTimeFormat(pattern = "dd-MM-yyyy")
        private LocalDate date_created;
        @JsonFormat(pattern = "HH:mm:ss")
        private String hour;
        private String name;
        private String description_comment;
        private String suggestion;
        @Column(columnDefinition = "integer")
        private Integer assessment;

        // Creating a relationship with others tables
            // Assessment and Events
            @ManyToOne
            @JoinColumn(name = "event_id", nullable = true)
            private Event event;

        public Assessment(DataRegisterAssessment data){
            this.name = data.name();
            this.description_comment = data.description_comment();
            this.assessment = data.assessment();
            this.hour = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
            this.suggestion = data.suggestion();
            this.date_created = LocalDate.now();
        }
    }
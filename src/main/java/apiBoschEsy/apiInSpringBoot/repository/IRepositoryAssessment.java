    package apiBoschEsy.apiInSpringBoot.repository;

    import apiBoschEsy.apiInSpringBoot.entity.Assessment;
    import org.springframework.data.jpa.repository.JpaRepository;
    import org.springframework.stereotype.Repository;

    @Repository
    public interface IRepositoryAssessment extends JpaRepository<Assessment, Long> {
//        Boolean existsByEvent_IdAndAuthor(Long eventId, String username);
    }

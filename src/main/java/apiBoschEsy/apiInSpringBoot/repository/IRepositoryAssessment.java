package apiBoschEsy.apiInSpringBoot.repository;

import apiBoschEsy.apiInSpringBoot.entity.Assessment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRepositoryAssessment extends JpaRepository<Assessment, Long> {
}

package apiBoschEsy.apiInSpringBoot.repository;

import apiBoschEsy.apiInSpringBoot.entity.Email;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRepositoryEmail extends JpaRepository<Email, Long> {
}

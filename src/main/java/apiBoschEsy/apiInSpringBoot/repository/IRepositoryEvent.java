package apiBoschEsy.apiInSpringBoot.repository;

import apiBoschEsy.apiInSpringBoot.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRepositoryEvent extends JpaRepository<Event, Long> {
}

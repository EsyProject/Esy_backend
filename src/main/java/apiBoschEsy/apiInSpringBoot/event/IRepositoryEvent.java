package apiBoschEsy.apiInSpringBoot.event;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IRepositoryEvent extends JpaRepository<Event, Long> {
}

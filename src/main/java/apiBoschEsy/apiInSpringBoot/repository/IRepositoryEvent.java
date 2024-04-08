package apiBoschEsy.apiInSpringBoot.repository;

import apiBoschEsy.apiInSpringBoot.dto.event.DataListEvent;
import apiBoschEsy.apiInSpringBoot.entity.Event;
import org.hibernate.query.SelectionQuery;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRepositoryEvent extends JpaRepository<Event, Long> {
}

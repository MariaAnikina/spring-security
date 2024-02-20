package maria.anikina.springsecurity.repository;

import maria.anikina.springsecurity.model.NewsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface NewsRepository extends JpaRepository<NewsEntity, Long> {
	List<NewsEntity> getNewsEntityByCreatedAfter(LocalDate localDate);
}

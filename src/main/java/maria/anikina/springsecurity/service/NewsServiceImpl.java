package maria.anikina.springsecurity.service;

import lombok.AllArgsConstructor;
import maria.anikina.springsecurity.model.NewsEntity;
import maria.anikina.springsecurity.repository.NewsRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class NewsServiceImpl implements NewsService {

	private final NewsRepository newsRepository;

	@Override
	public List<NewsEntity> getNewsForMonth() {
		LocalDate localDate = LocalDate.now().minusMonths(1);
		return newsRepository.getNewsEntityByCreatedAfter(localDate);
	}

	@Override
	public NewsEntity saveNews(NewsEntity news) {
		return newsRepository.save(news);
	}
}

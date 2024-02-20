package maria.anikina.springsecurity.service;

import maria.anikina.springsecurity.model.NewsEntity;

import java.util.List;

public interface NewsService {
	List<NewsEntity> getNewsForMonth();
	NewsEntity saveNews(NewsEntity news);
}

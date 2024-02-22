package maria.anikina.springsecurity.controller;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import maria.anikina.springsecurity.config.SecurityConfig;
import maria.anikina.springsecurity.model.NewsEntity;
import maria.anikina.springsecurity.service.NewsService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.List;

import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(NewsController.class)
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@Import(SecurityConfig.class)
class NewsControllerTest {
	@MockBean
	private NewsService newsService;
	@Autowired
	private MockMvc mvc;
	private final List<NewsEntity> news = List.of(
			new NewsEntity(1L, "Новость 1", "Это первая новость", LocalDate.now()),
			new NewsEntity(2L, "Новость 2", "Это вторая новость", LocalDate.now()));

	@SneakyThrows
	@WithMockUser
	@Test
	void gettingNewsForMonthByUser() {
		when(newsService.getNewsForMonth()).thenReturn(news);

		mvc.perform(get("/news")
						.characterEncoding(StandardCharsets.UTF_8)
						.accept(MediaType.TEXT_HTML))
				.andExpect(view().name("get-news"))
				.andExpect(model().attribute("news", news))
				.andExpect(status().isOk());
	}

	@SneakyThrows
	@Test
	void gettingNewsForMonthWithoutAccessRights() {
		when(newsService.getNewsForMonth()).thenReturn(news);

		mvc.perform(get("/news")
						.characterEncoding(StandardCharsets.UTF_8)
						.accept(MediaType.TEXT_HTML))
				.andExpect(status().is(302));
	}

	@SneakyThrows
	@Test
	void checkCreateNews() {
		mvc.perform(get("/news/create")
						.characterEncoding(StandardCharsets.UTF_8)
						.accept(MediaType.TEXT_HTML))
				.andExpect(status().is(302));
	}

	@SneakyThrows
	@Test
	void checkSavingNews() {
		when(newsService.saveNews(any(NewsEntity.class))).then(returnsFirstArg());

		mvc.perform(post("/news/save")
						.with(SecurityMockMvcRequestPostProcessors.user("anna").roles("ADMIN"))
						.with(SecurityMockMvcRequestPostProcessors.csrf())
						.characterEncoding(StandardCharsets.UTF_8)
						.accept(MediaType.TEXT_HTML))
				.andExpect(view().name("create-news"))
				.andExpect(status().isOk());
	}

	@SneakyThrows
	@Test
	void checkSavingNewsWithoutAccessRights() {
		when(newsService.saveNews(any(NewsEntity.class))).then(returnsFirstArg());

		mvc.perform(post("/news/save")
						.with(SecurityMockMvcRequestPostProcessors.user("anna").roles("USER"))
						.with(SecurityMockMvcRequestPostProcessors.csrf())
						.characterEncoding(StandardCharsets.UTF_8)
						.accept(MediaType.TEXT_HTML))
				.andExpect(status().is(403));
	}
}
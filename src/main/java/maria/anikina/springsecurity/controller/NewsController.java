package maria.anikina.springsecurity.controller;

import lombok.AllArgsConstructor;
import maria.anikina.springsecurity.model.NewsEntity;
import maria.anikina.springsecurity.service.NewsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/news")
public class NewsController {

	private final NewsService service;

	@GetMapping
	public List<NewsEntity> getNewsForMonth() {
		return service.getNewsForMonth();
	}
}

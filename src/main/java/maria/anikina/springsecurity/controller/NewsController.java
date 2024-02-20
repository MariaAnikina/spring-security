package maria.anikina.springsecurity.controller;

import lombok.AllArgsConstructor;
import maria.anikina.springsecurity.model.NewsEntity;
import maria.anikina.springsecurity.service.NewsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@AllArgsConstructor
@Controller
@RequestMapping("/news")
public class NewsController {

	private final NewsService service;

	@GetMapping
	public String getNewsForMonth(Model model) {
		model.addAttribute("news", service.getNewsForMonth());
		return "get-news";
	}

	@GetMapping("/create")
	public String createNews(@ModelAttribute("news") NewsEntity news) {
		return "create-news";
	}

	@PostMapping("/save")
	public String saveNews(@Valid @ModelAttribute("news") NewsEntity news,
	                       BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "create-news";
		}
		service.saveNews(news);
		return "redirect:/news";
	}
}

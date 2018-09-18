package be.vdab.personeel.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
class IndexController {
	private static final String INDEX_VIEW = "index";

	@GetMapping
	ModelAndView index() {
		return new ModelAndView(INDEX_VIEW);
	}

}

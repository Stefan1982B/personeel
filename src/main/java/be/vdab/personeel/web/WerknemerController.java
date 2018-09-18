package be.vdab.personeel.web;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import be.vdab.personeel.entities.Werknemer;
import be.vdab.personeel.services.WerknemerService;

@Controller
@RequestMapping("werknemershiërarchie")
class WerknemerController {
	private static final String WERKNEMER_VIEW = "werknemers/werknemer";

	private final WerknemerService werknemerService;

	WerknemerController(WerknemerService werknemerService) {
		this.werknemerService = werknemerService;
	}

	@GetMapping
	ModelAndView initieel() {
		long id = werknemerService.readWnZonderChef().get().getId();
		List<Werknemer> ondergeschikten = werknemerService.findByChef(werknemerService.read(id).get());
		return new ModelAndView(WERKNEMER_VIEW, "werknemer", werknemerService.readWnZonderChef().get())
				.addObject("ondergeschikten", ondergeschikten);
	}

	@GetMapping("{werknemer}")
	ModelAndView werknemer(@PathVariable Optional<Werknemer> werknemer, BindingResult bindingResult) {
		long id = werknemer.get().getId();
		List<Werknemer> werknemers = werknemerService.findByChef(werknemerService.read(id).get());
		return new ModelAndView(WERKNEMER_VIEW);
	}

}

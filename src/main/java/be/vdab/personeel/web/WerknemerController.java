package be.vdab.personeel.web;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import be.vdab.personeel.entities.Werknemer;
import be.vdab.personeel.services.WerknemerService;

@Controller
@RequestMapping("werknemers")
@SessionAttributes("werknemer")
class WerknemerController {
	private static final String WERKNEMER_VIEW = "werknemers/werknemer";

	private final WerknemerService werknemerService;

	WerknemerController(WerknemerService werknemerService) {
		this.werknemerService = werknemerService;
	}

	@GetMapping
	ModelAndView initieel() {
		Werknemer superChef=werknemerService.readWnZonderChef().get(); 
		return new ModelAndView(WERKNEMER_VIEW, "werknemer", superChef);
	}

	private static final String REDIRECT_BIJ_WERKNEMER_NIET_GEVONDEN = "redirect:/";

	@GetMapping("{werknemer}")
	ModelAndView werknemer(@PathVariable Optional<Werknemer> werknemer, RedirectAttributes redirectAttributes) {
		if (werknemer.isPresent()) {
			return new ModelAndView(WERKNEMER_VIEW).addObject(werknemer.get());
		}
		redirectAttributes.addAttribute("fout", "Werknemer niet gevonden");
		return new ModelAndView(REDIRECT_BIJ_WERKNEMER_NIET_GEVONDEN);
	}

	private static final String OPSLAG_VIEW = "werknemers/opslag";

	@GetMapping("{werknemer}/opslag")
	ModelAndView opslag(@PathVariable Optional<Werknemer> werknemer, RedirectAttributes redirectAttributes) {
		if (werknemer.isPresent()) {
			return new ModelAndView(OPSLAG_VIEW).addObject(new OpslagForm()).addObject(werknemer.get());
		}
		redirectAttributes.addAttribute("fout", "Werknemer niet gevonden");
		return new ModelAndView(REDIRECT_BIJ_WERKNEMER_NIET_GEVONDEN);
	}

	private static final String REDIRECT_NA_OPSLAG = "redirect:/werknemers/{id}";

	@PostMapping(value = "{werknemer}/opslag", params = "opslag")
	ModelAndView opslag(@PathVariable Optional<Werknemer> werknemer, @Valid OpslagForm form,
			BindingResult bindingResult, RedirectAttributes redirectAttributes) {
		if (werknemer.isPresent()) {
			if (bindingResult.hasErrors()) {
				return new ModelAndView(OPSLAG_VIEW).addObject(werknemer.get());
			}
			werknemerService.opslag(werknemer.get().getId(), form.getOpslag());
			redirectAttributes.addAttribute("id", werknemer.get().getId());
			return new ModelAndView(REDIRECT_NA_OPSLAG);
		}
		redirectAttributes.addAttribute("fout", "Werknemer niet gevonden");
		return new ModelAndView(REDIRECT_BIJ_WERKNEMER_NIET_GEVONDEN);
	}

	private static final String RIJKSREGISTERNR_VIEW = "werknemers/rijksregisternr";

	@GetMapping("{werknemer}/rijksregisterNr")
	ModelAndView rijksregisterNr(@PathVariable Optional<Werknemer> werknemer, RedirectAttributes redirectAttributes) {
		if (werknemer.isPresent()) {
			return new ModelAndView(RIJKSREGISTERNR_VIEW).addObject(werknemer.get());
		}
		redirectAttributes.addAttribute("fout", "Werknemer niet gevonden");
		return new ModelAndView(REDIRECT_BIJ_WERKNEMER_NIET_GEVONDEN);
	}
	
	private static final String REDIRECT_NA_RIJKSREGISTERNR = "redirect:/werknemers/{id}";
	
	@PostMapping(value = "{werknemer}/rijksregisterNr", params = "rijksregisterNr")
	ModelAndView rijksregisterNr(@Valid Werknemer werknemer,
			BindingResult bindingResult, SessionStatus sessionStatus,RedirectAttributes redirectAttributes) {
			if (bindingResult.hasErrors()) {
				return new ModelAndView(RIJKSREGISTERNR_VIEW).addObject(werknemer);
			}
			werknemerService.update(werknemer);
			sessionStatus.setComplete();
			redirectAttributes.addAttribute("id", werknemer.getId());
			return new ModelAndView(REDIRECT_NA_RIJKSREGISTERNR);
		}
	}



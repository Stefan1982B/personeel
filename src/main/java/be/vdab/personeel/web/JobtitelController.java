package be.vdab.personeel.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import be.vdab.personeel.entities.Jobtitel;
import be.vdab.personeel.services.JobtitelService;
import be.vdab.personeel.services.WerknemerService;

@Controller
@RequestMapping("jobtitels")
class JobtitelController {
	private final JobtitelService jobtitelService;
	
	JobtitelController(JobtitelService jobtitelService, WerknemerService werknemerService){
		this.jobtitelService = jobtitelService;
	}
	private final static String JOBTITEL_VIEW = "jobtitels/jobtitels";
	
	@GetMapping()
	ModelAndView jobtitels() {
		return new ModelAndView(JOBTITEL_VIEW, "jobtitels", jobtitelService.findAll());
	}
	
	@GetMapping("{jobtitel}")
	ModelAndView jobtitelsWerknemers(@PathVariable Jobtitel jobtitel) {
		return new ModelAndView(JOBTITEL_VIEW, "jobtitels", jobtitelService.findAll());
	}
}

package be.vdab.personeel.web;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import be.vdab.personeel.entities.Werknemer;
import be.vdab.personeel.services.WerknemerService;

public class WerknemerControllerTest {
	@Mock
	private WerknemerService dummyWerknemerService;
	
	@Mock
	private RedirectAttributes redirectAttributes;

	private WerknemerController controller;
	private Optional<Werknemer> werknemer;

	@Before
	public void before() {
		controller = new WerknemerController(dummyWerknemerService);
//		werknemer = new Werknemer();
	}

	@Test
	public void WerknemerWerktSamenMetWerknemerDotHtml() {
		ModelAndView modelAndView = controller.werknemer(werknemer, redirectAttributes);
		assertEquals("werknemers/werknemer", modelAndView.getViewName());
	}

	@Test
	public void WerknemerGeeftWerknemerDoor() {
		ModelAndView modelAndView = controller.werknemer(werknemer, redirectAttributes);
		assertTrue(modelAndView.getModel().containsKey("werknemer"));
	}

}

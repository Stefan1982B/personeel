package be.vdab.personeel.web;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.web.servlet.ModelAndView;

import be.vdab.personeel.entities.Jobtitel;
import be.vdab.personeel.services.JobtitelService;
import be.vdab.personeel.services.WerknemerService;

@RunWith(MockitoJUnitRunner.class)
public class JobtitelControllerTest {

	@Mock
	private WerknemerService dummyWerknemerService;

	@Mock
	private JobtitelService dummyJobtitelService;

	private JobtitelController controller;
	private Jobtitel jobtitel;

	@Before
	public void before() {
		controller = new JobtitelController(dummyJobtitelService, dummyWerknemerService);
		jobtitel = new Jobtitel();
	}

	@Test
	public void JobtitelWerktSamenMetJobtitelsDotHtml() {
		ModelAndView modelAndView = controller.jobtitels();
		assertEquals("jobtitels/jobtitels", modelAndView.getViewName());
	}

	@Test
	public void JobtitelGeeftJobtitelsDoor() {
		ModelAndView modelAndView = controller.jobtitels();
		assertTrue(modelAndView.getModel().containsKey("jobtitels"));
	}
	
	@Test
	public void JobtitelGeeftJobtitelsWerknemersDoor() {
		ModelAndView modelAndView = controller.jobtitelsWerknemers(jobtitel);
		assertTrue(modelAndView.getModel().containsKey("werknemers"));
	}
}

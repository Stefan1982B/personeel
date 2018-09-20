package be.vdab.personeel.web;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

public class IndexControllerTest {

	private IndexController controller;

	@Before
	public void before() {
		controller = new IndexController();
	}

	@Test
	public void IndexWerktSamenMetIndexDotHtml() {
		ModelAndView modelAndView = controller.index();
		assertEquals("index", modelAndView.getViewName());
	}

}

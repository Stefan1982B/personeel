package be.vdab.personeel.web;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.util.ReflectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import be.vdab.personeel.entities.Jobtitel;
import be.vdab.personeel.entities.Werknemer;
import be.vdab.personeel.services.WerknemerService;

@RunWith(MockitoJUnitRunner.class)
public class WerknemerControllerTest {
	@Mock
	private WerknemerService dummyWerknemerService;

	@Mock
	private RedirectAttributes redirectAttributes;
	
	@Mock
	private BindingResult bindingResult;
	
	@Mock
	private SessionStatus sessionStatus;

	private WerknemerController controller;
	private Werknemer werknemer, werknemer2;
	private Jobtitel jobtitel;
	private OpslagForm opslagForm;

	void vulPrivate(String veldnaam, Object waarde, Werknemer werknemer) {
		Field field = ReflectionUtils.findField(Werknemer.class, veldnaam);
		ReflectionUtils.makeAccessible(field);
		ReflectionUtils.setField(field, werknemer, waarde);
	}

	@Before
	public void before() {
		werknemer = new Werknemer();
		vulPrivate("familienaam", "Schuddinck",werknemer);
		vulPrivate("voornaam", "Stefan", werknemer);
		vulPrivate("email", "stefanschuddinck@hotmail.com", werknemer);
		vulPrivate("chef", werknemer2, werknemer);
		vulPrivate("jobtitel", jobtitel, werknemer);
		vulPrivate("paswoord", "vdab", werknemer);
		vulPrivate("geboorte", LocalDate.of(1989, 10, 10), werknemer);
		vulPrivate("rijksregisternr", 82080432176L, werknemer);
		vulPrivate("versie", 1, werknemer);
		vulPrivate("salaris", BigDecimal.TEN, werknemer);
		vulPrivate("id", 1L, werknemer);
		werknemer2 = new Werknemer();
		vulPrivate("id", 2L, werknemer2);
		jobtitel = new Jobtitel();
		when(dummyWerknemerService.readWnZonderChef()).thenReturn(Optional.of(werknemer));
		List<Werknemer> werknemers = new ArrayList<>();
		werknemers.add(werknemer);
//		when(dummyWerknemerService.findByChef(werknemer2)).thenReturn(werknemers);
		when(dummyWerknemerService.read(1)).thenReturn(Optional.of(werknemer));
		controller = new WerknemerController(dummyWerknemerService);
		opslagForm = new OpslagForm();
	}

	@Test
	public void InitieelWerktSamenMetWerknemerDotHtml() {
		ModelAndView modelAndView = controller.initieel();
		assertEquals("werknemers/werknemer", modelAndView.getViewName());
	}

	@Test
	public void InitieelGeeftWerknemerDoor() {
		ModelAndView modelAndView = controller.initieel();
		assertTrue(modelAndView.getModel().containsKey("werknemer"));
	}

	@Test
	public void WerknemerWerktSamenMetWerknemerDotHtml() {
		ModelAndView modelAndView = controller.werknemer(Optional.of(werknemer), redirectAttributes);
		assertEquals("werknemers/werknemer", modelAndView.getViewName());
	}
	
	@Test
	public void WerknemerWerktSamenMetRedirectIndexDotHtml() {
		ModelAndView modelAndView = controller.werknemer(Optional.ofNullable(null), redirectAttributes);
		assertEquals("redirect:/", modelAndView.getViewName());
	}

	@Test
	public void WerknemerGeeftWerknemerDoor() {
		ModelAndView modelAndView = controller.werknemer(Optional.of(werknemer), redirectAttributes);
		assertTrue(modelAndView.getModel().containsKey("werknemer"));
	}
	
	@Test
	public void OpslagWerktSamenMetOpslagDotHtml() {
		ModelAndView modelAndView = controller.opslag(Optional.of(werknemer), redirectAttributes);
		assertEquals("werknemers/opslag", modelAndView.getViewName());
	}

	@Test
	public void OpslagGeeftWerknemerDoor() {
		ModelAndView modelAndView = controller.opslag(Optional.of(werknemer), redirectAttributes);
		assertTrue(modelAndView.getModel().containsKey("werknemer"));
	}
	
	@Test
	public void OpslagWerktSamenMetRedirectIndexDotHtml() {
		ModelAndView modelAndView = controller.opslag(Optional.ofNullable(null), redirectAttributes);
		assertEquals("redirect:/", modelAndView.getViewName());
	}
	
	@Test
	public void OpslagGeeftOpslagFormDoor() {
		ModelAndView modelAndView = controller.opslag(Optional.of(werknemer), redirectAttributes);
		assertTrue(modelAndView.getModel().containsKey("opslagForm"));
	}
	
	@Test
	public void RrnrGeeftWerknemerDoor() {
		ModelAndView modelAndView = controller.rijksregisterNr(Optional.of(werknemer), redirectAttributes);
		assertTrue(modelAndView.getModel().containsKey("werknemer"));
	}
	
	@Test
	public void RrnrWerktSamenMetRijksregisterNrHtml() {
		ModelAndView modelAndView = controller.rijksregisterNr(Optional.of(werknemer), redirectAttributes);
		assertEquals("werknemers/rijksregisternr", modelAndView.getViewName());
	}
	
	@Test
	public void RrnrWerktSamenMetRedirectIndexDotHtml() {
		ModelAndView modelAndView = controller.rijksregisterNr(Optional.ofNullable(null), redirectAttributes);
		assertEquals("redirect:/", modelAndView.getViewName());
	}
	
	@Test
	public void OpslagPostWerktSamenMetRedirectWerknemerDotHtml() {
		ModelAndView modelAndView = controller.opslag(Optional.of(werknemer), opslagForm, bindingResult, redirectAttributes);
		assertEquals("redirect:/werknemers/{id}", modelAndView.getViewName());
	}
	
	@Test
	public void OpslagPostWerktSamenMetRedirectIndexDotHtml() {
		ModelAndView modelAndView = controller.opslag(Optional.ofNullable(null), opslagForm, bindingResult, redirectAttributes);
		assertEquals("redirect:/", modelAndView.getViewName());
	}
	
	@Test
	public void RrnrPostWerktSamenMetRedirectWerknemerDotHtml() {
		ModelAndView modelAndView = controller.rijksregisterNr(werknemer, bindingResult, sessionStatus, redirectAttributes);
		assertEquals("redirect:/werknemers/{id}", modelAndView.getViewName());
	}
	

	

	
	

}

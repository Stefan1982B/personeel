package be.vdab.personeel.repositories;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.ReflectionUtils;

import be.vdab.personeel.entities.Werknemer;

@RunWith(SpringRunner.class)
@DataJpaTest
public class WerknemerRepositoryTest extends AbstractTransactionalJUnit4SpringContextTests {

	private Werknemer werknemer;
	private static final String WERKNEMERS = "werknemers"; 

	@Autowired
	private WerknemerRepository repository;

	void vulPrivate(String veldnaam, Object waarde) {
		Field field = ReflectionUtils.findField(Werknemer.class, veldnaam);
		ReflectionUtils.makeAccessible(field);
		ReflectionUtils.setField(field, werknemer, waarde);

	}

	@Before
	public void before() {
		werknemer = new Werknemer();
		vulPrivate("familienaam", "Schuddinck");
		vulPrivate("voornaam", "Stefan");
		vulPrivate("email", "stefanschuddinck@hotmail.com");
//		vulPrivate("chefid", 1L);
		vulPrivate("jobtitelid", 1L);
		vulPrivate("paswoord", "vdab");
		vulPrivate("geboorte", LocalDate.of(1989, 10, 10));
		vulPrivate("rijksregisternr", 82080432176L);
		vulPrivate("versie", 1);
		vulPrivate("salaris", BigDecimal.TEN);
		vulPrivate("id", 1L);
	}

	@Test
	public void eenIngevuldeWerknemerMaken() {
		assertEquals("Schuddinck", werknemer.getFamilienaam());
		assertEquals("Stefan", werknemer.getVoornaam());
		assertEquals("stefanschuddinck@hotmail.com", werknemer.getEmail());
		assertEquals(0, BigDecimal.TEN.compareTo(werknemer.getSalaris()));
		assertEquals(1L, werknemer.getId());

	}

	@Test
	public void create() {
		int aantalWerknemers = super.countRowsInTable(WERKNEMERS);
		repository.save(werknemer);
		assertEquals(aantalWerknemers + 1, super.countRowsInTable(WERKNEMERS));
		assertNotEquals(0, werknemer.getId());
		assertEquals(1, super.countRowsInTableWhere(WERKNEMERS, "id=" + werknemer.getId()));
	}

}

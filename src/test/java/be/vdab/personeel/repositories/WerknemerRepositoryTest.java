package be.vdab.personeel.repositories;

import static org.junit.Assert.assertEquals;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.ReflectionUtils;

import be.vdab.personeel.entities.Jobtitel;
import be.vdab.personeel.entities.Werknemer;

@RunWith(SpringRunner.class)
@DataJpaTest
@Sql("/insertJobtitel.sql")
@Sql("/insertWerknemer.sql")
public class WerknemerRepositoryTest extends AbstractTransactionalJUnit4SpringContextTests {

	private Werknemer werknemer;
	private Jobtitel jobtitel;

	@Autowired
	private WerknemerRepository werknemerRepository;
	
	@Autowired JobtitelRepository jobtitelRepository;

	private long idVanTestWerknemer() {
		return super.jdbcTemplate.queryForObject("select id from werknemers where voornaam='testVoornaam'", Long.class);
	}
	
	private long idVanJobtitel() {
		return super.jdbcTemplate.queryForObject("select id from jobtitels where naam='testTitelNaam'", Long.class);
	}

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
		vulPrivate("chef", null);
		vulPrivate("jobtitel", jobtitel);
		vulPrivate("paswoord", "vdab");
		vulPrivate("geboorte", LocalDate.of(1989, 10, 10));
		vulPrivate("rijksregisternr", 82080432176L);
		vulPrivate("versie", 1);
		vulPrivate("salaris", BigDecimal.TEN);
		vulPrivate("id", 1L);
		jobtitel = new Jobtitel();
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
	public void findByJobtitel() {
		List<Werknemer> werknemers = werknemerRepository.findByJobtitel(jobtitelRepository.findById(idVanJobtitel()).get());
		assertEquals(1, werknemers.size());
		assertEquals("testFamilienaam", werknemers.get(0).getFamilienaam());
	}

	@Test
	public void findByChef() {
		List<Werknemer> werknemers = werknemerRepository.findByChef(werknemerRepository.findById(idVanTestWerknemer()).get());
		assertEquals(1, werknemers.size());
		assertEquals("testFamilienaam", werknemers.get(0).getFamilienaam());
	}

	@Test 
	public void WerknemerLazyLoaded() { 
		Werknemer werknemer = werknemerRepository.findById(idVanTestWerknemer()).get(); 
		assertEquals("testTitelNaam", werknemer.getJobtitel().getNaam()); 
		}

}

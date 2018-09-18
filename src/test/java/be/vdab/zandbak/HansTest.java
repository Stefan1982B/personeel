package be.vdab.zandbak;

import static org.junit.Assert.assertEquals;

import java.lang.reflect.Field;
import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;
import org.springframework.util.ReflectionUtils;

import be.vdab.personeel.entities.Werknemer;

public class HansTest {
	private Werknemer werknemer;
	
	@Before
	public void before() {
		werknemer=new Werknemer();
	}
	
	@Test
	public void eenIngevuldeWerknemerMaken() {
		vulPrivate("voornaam", "Sam");
		assertEquals("Sam",werknemer.getVoornaam());
		vulPrivate("salaris", BigDecimal.TEN);
		assertEquals(0,BigDecimal.TEN.compareTo(werknemer.getSalaris()));
		vulPrivate("id", 1L);
		assertEquals(1L, werknemer.getId());
		List<String>
	}
	
	void vulPrivate(String veldnaam, Object waarde) {
		Field field = ReflectionUtils.findField(Werknemer.class, veldnaam);
		ReflectionUtils.makeAccessible(field);
		ReflectionUtils.setField(field, werknemer, waarde);
		
	}
	
	
	
	

}

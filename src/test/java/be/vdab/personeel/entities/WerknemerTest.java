package be.vdab.personeel.entities;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.math.BigDecimal;
import java.lang.reflect.Field;
import org.junit.Before;
import org.junit.Test;
import org.springframework.util.ReflectionUtils;

public class WerknemerTest {

	private Werknemer werknemer1, nogEensWerknemer1, werknemer2;

	void vulPrivate(String veldnaam, Object waarde) {
		Field field = ReflectionUtils.findField(Werknemer.class, veldnaam);
		ReflectionUtils.makeAccessible(field);
		ReflectionUtils.setField(field, werknemer1, waarde);

	}
	void vulPrivate2(String veldnaam, Object waarde) {
		Field field = ReflectionUtils.findField(Werknemer.class, veldnaam);
		ReflectionUtils.makeAccessible(field);
		ReflectionUtils.setField(field, nogEensWerknemer1, waarde);

	}
	void vulPrivate3(String veldnaam, Object waarde) {
		Field field = ReflectionUtils.findField(Werknemer.class, veldnaam);
		ReflectionUtils.makeAccessible(field);
		ReflectionUtils.setField(field, werknemer2, waarde);

	}
	

	@Before
	public void before() {
		werknemer1 = new Werknemer();
		vulPrivate("salaris", BigDecimal.valueOf(2000));
		vulPrivate("rijksregisternr", 82080432176L);
		nogEensWerknemer1 = new Werknemer();
		vulPrivate2("salaris", BigDecimal.valueOf(2000));
		vulPrivate2("rijksregisternr", 82080432176L);
		werknemer2 = new Werknemer();
		vulPrivate3("salaris", BigDecimal.valueOf(2000));
		vulPrivate3("rijksregisternr", 50013100129L);
	}

	@Test
	public void opslag() {
		werknemer1.opslag(BigDecimal.valueOf(200));
		assertEquals(0, BigDecimal.valueOf(2200).compareTo(werknemer1.getSalaris()));
	}

	@Test
	public void WerknemersZijnGelijkAlsHunRrnrsGelijkZijn() {
		assertEquals(werknemer1, nogEensWerknemer1);
	}

	@Test
	public void WerknemersZijnVerschillendAlsHunRrnrsVerschillen() {
		assertNotEquals(werknemer1, werknemer2);
	}

	@Test
	public void eenWerknemerVerschiltVanNull() {
		assertNotEquals(werknemer1, null);
	}

	@Test
	public void eenWerknemerVerschiltVanEenAnderTypeObject() {
		assertNotEquals(werknemer1, "");
	}

	@Test
	public void gelijkeWerknemersGevenDezelfdeHashCode() {
		assertEquals(werknemer1.hashCode(), nogEensWerknemer1.hashCode());
	}

}

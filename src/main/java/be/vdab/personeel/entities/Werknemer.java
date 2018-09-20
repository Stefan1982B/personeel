package be.vdab.personeel.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

import be.vdab.personeel.constraints.RijksregisterNr;

@Entity
@Table(name = "werknemers")
@RijksregisterNr
public class Werknemer implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String familienaam;
	private String voornaam;
	private String email;
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "jobtitelid")
	private Jobtitel jobtitel;
	@NotNull
	@Min(1)
	@Digits(integer = 10, fraction = 2)
	@NumberFormat(style = Style.NUMBER)
	private BigDecimal salaris;
	private String paswoord;
	private LocalDate geboorte;
	@Column(unique = true)
	@NotNull
	private long rijksregisternr;
	@Version
	private int versie;
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "chefid")
	private Werknemer chef;
	@OneToMany(mappedBy = "chef")
	private Set<Werknemer> werknemerLijst;

	public long getId() {
		return id;
	}

	public String getFamilienaam() {
		return familienaam;
	}

	public String getVoornaam() {
		return voornaam;
	}

	public String getEmail() {
		return email;
	}

	public BigDecimal getSalaris() {
		return salaris;
	}

	public String getPaswoord() {
		return paswoord;
	}

	public LocalDate getGeboorte() {
		return geboorte;
	}

	public long getRijksregisternr() {
		return rijksregisternr;
	}

	public int getVersie() {
		return versie;
	}

	public Werknemer getChef() {
		return chef;
	}

	public Set<Werknemer> getWerknemerLijst() {
		return Collections.unmodifiableSet(werknemerLijst);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (rijksregisternr ^ (rijksregisternr >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Werknemer other = (Werknemer) obj;
		if (rijksregisternr != other.rijksregisternr)
			return false;
		return true;
	}

	public Jobtitel getJobtitel() {
		return jobtitel;
	}

	public void opslag(BigDecimal opslag) {
		salaris = salaris.add(opslag);
	}

	public void setRijksregisternr(long rijksregisternr) {
		this.rijksregisternr = rijksregisternr;
	}

}

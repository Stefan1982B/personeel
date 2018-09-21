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

import be.vdab.personeel.constraints.RijksregisterNr;

@Entity
@Table(name = "werknemers")
@RijksregisterNr
public class Werknemer implements Serializable {

	public static final String MET_JOBTITEL = "Werknemer.metJobtitel";

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
	@NumberFormat(pattern = "#,##0.00")
	private BigDecimal salaris;
	private String paswoord;
	private LocalDate geboorte;
	@Column(unique = true)
	@NotNull
	private Long rijksregisternr;
	@Version
	private int versie;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "chefid")
	private Werknemer chef;
	@OneToMany(mappedBy = "chef")
	private Set<Werknemer> ondergeschikten;

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

	public Long getRijksregisternr() {
		return rijksregisternr;
	}

	public int getVersie() {
		return versie;
	}

	public Werknemer getChef() {
		return chef;
	}

	public Set<Werknemer> getOndergeschikten() {
		return Collections.unmodifiableSet(ondergeschikten);
	}

	public Jobtitel getJobtitel() {
		return jobtitel;
	}

	public void opslag(BigDecimal opslag) {
		salaris = salaris.add(opslag);
	}

	public void setRijksregisternr(Long rijksregisternr) {
		this.rijksregisternr = rijksregisternr;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((rijksregisternr == null) ? 0 : rijksregisternr.hashCode());
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
		if (rijksregisternr == null) {
			if (other.rijksregisternr != null)
				return false;
		} else if (!rijksregisternr.equals(other.rijksregisternr))
			return false;
		return true;
	}

}

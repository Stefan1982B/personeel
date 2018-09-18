package be.vdab.personeel.entities;

import java.io.Serializable;
import java.util.Collections;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name = "jobtitels")
public class Jobtitel implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String naam;
	@Version
	private int versie;
	@OneToMany
	@JoinColumn(name = "jobtitelid")
	private Set<Werknemer> werknemerLijst;

	public Set<Werknemer> getWerknemerLijst() {
		return Collections.unmodifiableSet(werknemerLijst);
	}

	public long getId() {
		return id;
	}

	public String getNaam() {
		return naam;
	}

	public int getVersie() {
		return versie;
	}

}

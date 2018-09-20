package be.vdab.personeel.web;

import javax.validation.constraints.NotNull;

public class RijksregisterNrForm {
	@NotNull
	private long rijksregisterNr;

	public long getRijksregisterNr() {
		return rijksregisterNr;
	}

	public void setRijksregisterNr(long rijksregisterNr) {
		this.rijksregisterNr = rijksregisterNr;
	}

}

package be.vdab.personeel.web;

import javax.validation.constraints.NotNull;

import be.vdab.personeel.constraints.RijksregisterNr;

public class RijksregisterNrForm {
	@NotNull
	@RijksregisterNr
	private long rijksregisterNr;

	public long getRijksregisterNr() {
		return rijksregisterNr;
	}

	public void setRijksregisterNr(long rijksregisterNr) {
		this.rijksregisterNr = rijksregisterNr;
	}

}

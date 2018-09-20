package be.vdab.personeel.constraints;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import be.vdab.personeel.entities.Werknemer;

public class RijksregisterNrValidator implements ConstraintValidator<RijksregisterNr, Werknemer> {
	
	@Override   public void initialize(RijksregisterNr rijksregisterNr) { 
		
	}

	@Override
	public boolean isValid(Werknemer werknemer, ConstraintValidatorContext context) {
		Long rijksregisterNr = werknemer.getRijksregisternr();
		if(rijksregisterNr.equals(null)) {
			return true;
		}
		long eerste6Cijfers = rijksregisterNr/100000;
		long eerste9Cijfers=rijksregisterNr/100;	
		long controleGetal = rijksregisterNr%100;
		String geboorteDatumTekst = werknemer.getGeboorte().toString();
		String korteDatum = geboorteDatumTekst.replaceAll("-", "");
    	String geboorteJaar = korteDatum.substring(0, 4);
    	String controleGeboorteDatum = korteDatum.substring(2,8);
    	long controleGeboorteDatumLong = Long.parseLong(controleGeboorteDatum);
    	if(Long.parseLong(geboorteJaar)>2000) {
    	String eerste10Cijfers = "2"+ Long.toString(eerste9Cijfers);
    	eerste9Cijfers = Long.parseLong(eerste10Cijfers);
    	}
		return (eerste6Cijfers==controleGeboorteDatumLong && (97 - (eerste9Cijfers%97)==controleGetal));
	}
	
}

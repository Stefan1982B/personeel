package be.vdab.personeel.services;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import be.vdab.personeel.entities.Jobtitel;
import be.vdab.personeel.entities.Werknemer;

public interface WerknemerService {
	Optional<Werknemer> read(long id);
	Optional<Werknemer> readWnZonderChef();
	void opslag(long id, BigDecimal opslag);
	void update(Werknemer werknemer);
}

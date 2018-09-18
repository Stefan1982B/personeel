package be.vdab.personeel.services;

import java.util.List;
import java.util.Optional;

import be.vdab.personeel.entities.Werknemer;

public interface WerknemerService {
//	List<Werknemer> findByJobtitelid(long jobtitelid);
	List<Werknemer> findByChef(Werknemer chef);
	Optional<Werknemer> read(long id);
	Optional<Werknemer> readWnZonderChef();
}

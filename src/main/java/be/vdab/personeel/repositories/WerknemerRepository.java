package be.vdab.personeel.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import be.vdab.personeel.entities.Jobtitel;
import be.vdab.personeel.entities.Werknemer;

public interface WerknemerRepository extends JpaRepository<Werknemer, Long> {
	List<Werknemer> findByJobtitel(Jobtitel jobtitel);
	@EntityGraph(Werknemer.MET_JOBTITEL) 
	List<Werknemer> findByChef(Werknemer chef);
	Optional<Werknemer> findByChefIsNull();
}

package be.vdab.personeel.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import be.vdab.personeel.entities.Werknemer;

public interface WerknemerRepository extends JpaRepository<Werknemer, Long> {
	Optional<Werknemer> findByChefIsNull();
}

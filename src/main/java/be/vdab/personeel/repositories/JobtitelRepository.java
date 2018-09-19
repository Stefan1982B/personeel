package be.vdab.personeel.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import be.vdab.personeel.entities.Jobtitel;

public interface JobtitelRepository extends JpaRepository<Jobtitel, Long>{
//	List<Jobtitel> findJobtitels();
}

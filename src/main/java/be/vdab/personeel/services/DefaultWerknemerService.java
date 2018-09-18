package be.vdab.personeel.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import be.vdab.personeel.entities.Werknemer;
import be.vdab.personeel.repositories.WerknemerRepository;

@Service
@Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)
class DefaultWerknemerService implements WerknemerService {

	private final WerknemerRepository werknemerRepository;

	DefaultWerknemerService(WerknemerRepository werknemerRepository) {
		this.werknemerRepository = werknemerRepository;
	}

//	@Override
//	public List<Werknemer> findByJobtitelid(long jobtitelid) {
//		return werknemerRepository.findByJobtitelid(jobtitelid);
//	}

	@Override
	public Optional<Werknemer> read(long id) {
		return werknemerRepository.findById(id);
	}

	@Override
	public List<Werknemer> findByChef(Werknemer chef) {
		return werknemerRepository.findByChef(chef);
	}

	@Override
	public Optional<Werknemer> readWnZonderChef() {
		return werknemerRepository.findByChefIsNull();
	}

}

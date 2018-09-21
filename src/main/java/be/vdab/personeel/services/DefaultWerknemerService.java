package be.vdab.personeel.services;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import be.vdab.personeel.entities.Werknemer;
import be.vdab.personeel.exceptions.WerknemerNietGevondenException;
import be.vdab.personeel.repositories.WerknemerRepository;

@Service
@Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)
class DefaultWerknemerService implements WerknemerService {

	private final WerknemerRepository werknemerRepository;

	DefaultWerknemerService(WerknemerRepository werknemerRepository) {
		this.werknemerRepository = werknemerRepository;
	}

	@Override
	public Optional<Werknemer> read(long id) {
		return werknemerRepository.findById(id);
	}

	@Override
	public Optional<Werknemer> readWnZonderChef() {
		return werknemerRepository.findByChefIsNull();
	}

	@Override
	@Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED)
	public void opslag(long id, BigDecimal opslag) {
		Optional<Werknemer> werknemer = werknemerRepository.findById(id);
		if (werknemer.isPresent()) {
			werknemer.get().opslag(opslag);
		}
		throw new WerknemerNietGevondenException();

	}

	@Override
	@Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED)
	public void update(Werknemer werknemer) {
		werknemerRepository.save(werknemer);

	}
}

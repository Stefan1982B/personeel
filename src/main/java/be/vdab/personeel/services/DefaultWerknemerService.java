package be.vdab.personeel.services;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import be.vdab.personeel.entities.Jobtitel;
import be.vdab.personeel.entities.Werknemer;
import be.vdab.personeel.repositories.WerknemerRepository;

@Service
@Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)
class DefaultWerknemerService implements WerknemerService {

	private final WerknemerRepository werknemerRepository;

	DefaultWerknemerService(WerknemerRepository werknemerRepository) {
		this.werknemerRepository = werknemerRepository;
	}

	@Override
	public List<Werknemer> findByJobtitel(Jobtitel jobtitel) {
		return werknemerRepository.findByJobtitel(jobtitel);
	}

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

	@Override
	@Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED)
	public void opslag(long id, BigDecimal opslag) {
		werknemerRepository.findById(id).ifPresent(werknemer -> werknemer.opslag(opslag));

	}

//	@Override
//	@Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED)
//	public void wijzigRijksregisternr(long id, long rijksregisterNr) {
//		werknemerRepository.findById(id).ifPresent(werknemer -> werknemer.wijzigRijksregisternr(rijksregisterNr));
//
//	}

	@Override
	@Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED)
	public void update(Werknemer werknemer) {
		werknemerRepository.save(werknemer);

	}
}

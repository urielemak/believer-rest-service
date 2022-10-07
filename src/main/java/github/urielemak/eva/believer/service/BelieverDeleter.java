package github.urielemak.eva.believer.service;

import org.springframework.stereotype.Service;

import github.urielemak.eva.believer.domain.BelieverNotFoundException;
import github.urielemak.eva.believer.infraestructure.BelieverRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public record BelieverDeleter(BelieverRepository repository) {

    public void byId(Long id){
	if(!repository.existsById(id)){
	    String error = String.format("Believer by id %s does not exists", id);
	    log.warn(error);
	    throw new BelieverNotFoundException(error);
	}

	repository.deleteById(id);
	log.info("Believer by id {} deleted", id);
    }
}

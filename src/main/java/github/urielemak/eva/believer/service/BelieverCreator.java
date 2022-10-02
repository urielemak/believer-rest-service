package github.urielemak.eva.believer.service;

import org.springframework.stereotype.Service;

import github.urielemak.eva.believer.domain.Believer;
import github.urielemak.eva.believer.domain.BelieverAlreadyExistsException;
import github.urielemak.eva.believer.infraestructure.BelieverRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public record BelieverCreator(BelieverRepository repository) { 

    public void create(Believer believer){
	if(repository.existsById(believer.id())){
	    var errorMsg = String.format("Believer with id:%s already exists", believer.id());
	    log.warn(errorMsg);
	    throw new BelieverAlreadyExistsException(errorMsg);
	}

	this.repository.save(believer);
	log.info("Believer created, {}", believer.toString());
    }
}

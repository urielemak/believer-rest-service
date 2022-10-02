package github.urielemak.eva.believer.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import github.urielemak.eva.believer.domain.Believer;
import github.urielemak.eva.believer.domain.BelieverNotFoundException;
import github.urielemak.eva.believer.infraestructure.BelieverRepository;

@Service
public record BelieverFinder(BelieverRepository repository) {

    public Optional<Believer> byId(Long id){
	if(!repository.existsById(id)){
	    var errorMsg = String.format("Believer with id %s not found", id);
	    throw new BelieverNotFoundException(errorMsg);
	}
	return this.repository.findById(id);
    }

    public List<Believer> all(){
	return this.repository.findAll();
    }

    public List<Believer> all(LocalDate from, LocalDate to){
	return this.repository.allFrom(from, to);
    }

    public List<Believer> birthdayBoys(LocalDate date){
	return all().stream()
	    .filter(b -> b.isHisBirthday(date))
	    .toList();
    }
}

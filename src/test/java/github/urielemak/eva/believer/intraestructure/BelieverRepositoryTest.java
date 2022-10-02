package github.urielemak.eva.believer.intraestructure;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import github.urielemak.eva.believer.domain.FakeBeliever;
import github.urielemak.eva.believer.infraestructure.BelieverRepository;

@DataJpaTest
public class BelieverRepositoryTest {

    @Autowired
    private BelieverRepository repository;

    @Test
    public void should_return_all_believers_born_since_2001_to_2005(){
	var from = LocalDate.of(2001, 1, 1);
	var to = LocalDate.of(2005, 1, 1);

	var believer =	FakeBeliever.randomWithBirth(LocalDate.of(2003, 5, 1));
	var anotherBeliever = FakeBeliever.randomWithBirth(LocalDate.of(2004, 2, 1));

	var believers = Arrays.asList(believer, anotherBeliever);

	repository.saveAll(believers);

	assertTrue(repository.allFrom(from, to).containsAll(believers));
    }

    @Test
    public void should_return_any_believers_when_birth_range_isnt_supplied(){
	var from = LocalDate.of(2006, 1, 1);
	var to = LocalDate.of(2010, 1, 1);

	var believer =	FakeBeliever.randomWithBirth(LocalDate.of(2004, 5, 6));
	var anotherBeliever = FakeBeliever.randomWithBirth(LocalDate.of(2002, 6, 10));

	var believers = Arrays.asList(believer, anotherBeliever);

	repository.saveAll(believers);
	
	assertFalse(repository.allFrom(from, to).containsAll(believers));
    }
}

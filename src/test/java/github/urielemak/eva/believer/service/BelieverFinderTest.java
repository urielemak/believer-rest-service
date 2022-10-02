package github.urielemak.eva.believer.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import github.urielemak.eva.believer.domain.BelieverNotFoundException;
import github.urielemak.eva.believer.domain.FakeBeliever;
import github.urielemak.eva.believer.infraestructure.BelieverRepository;

public class BelieverFinderTest {

    @InjectMocks
    private BelieverFinder finder;

    @Mock
    private BelieverRepository repository;

    @BeforeEach
    public void setUp(){
	MockitoAnnotations.openMocks(this);
    }

    @Test
    public void should_find_a_believer_by_id(){
	var expectedBeliever = FakeBeliever.random();

	Mockito
	    .when(repository.findById(expectedBeliever.id()))
	    .thenReturn(Optional.of(expectedBeliever));

	Mockito.when(repository.existsById(expectedBeliever.id()))
	    .thenReturn(true);

	var result = finder.byId(expectedBeliever.id()).get();

	assertEquals(result, expectedBeliever);
    }

    @Test
    public void should_return_all_birthday_boys(){
	var today = LocalDate.of(2022, 2, 2);

	var believer = FakeBeliever.randomWithBirth(LocalDate.of(2005, 2, 2));
	var anotherBelivier = FakeBeliever.randomWithBirth(LocalDate.of(2001, 2, 2));

	var birthdayBoys = Arrays.asList(believer, anotherBelivier);

	Mockito.when(repository.findAll()).thenReturn(birthdayBoys);
	var results = finder.birthdayBoys(today);

	assertEquals(results, birthdayBoys);
   }

    @Test
    public void should_return_a_empty_list_if_no_one_has_birthday_today(){
	var today = LocalDate.of(2022, 9, 10);

	var believer = FakeBeliever.randomWithBirth(LocalDate.of(2012, 6, 7));
	var anotherBelivier = FakeBeliever.randomWithBirth(LocalDate.of(2011, 2, 6));

	var believers = Arrays.asList(believer, anotherBelivier);

	Mockito.when(repository.findAll()).thenReturn(believers);
	var results = finder.birthdayBoys(today);

	assertTrue(results.isEmpty());;
    }

    @Test
    public void should_return_an_exception_when_believer_by_id_not_found(){
	var believer = FakeBeliever.random();

	Mockito.when(repository.existsById(believer.id()))
	    .thenReturn(false);

	assertThrows(BelieverNotFoundException.class, () -> finder.byId(believer.id()));
    }
}

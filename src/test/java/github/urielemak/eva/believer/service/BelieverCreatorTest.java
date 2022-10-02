package github.urielemak.eva.believer.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import github.urielemak.eva.believer.domain.BelieverAlreadyExistsException;
import github.urielemak.eva.believer.domain.FakeBeliever;
import github.urielemak.eva.believer.infraestructure.BelieverRepository;

public class BelieverCreatorTest {

	@InjectMocks
	private BelieverCreator creator;

	@Mock
	private BelieverRepository repository;

	@BeforeEach
	public void setUp(){
	    MockitoAnnotations.openMocks(this);
	}

	@Test
	public void should_create_a_believer(){
	    var expectedBeliever = FakeBeliever.random();

	    Mockito
		.when(repository.findById(expectedBeliever.id()))
		.thenReturn(Optional.of(expectedBeliever));

	    var believerReturned = repository.findById(expectedBeliever.id()).get();
	    assertEquals(believerReturned, expectedBeliever);
	}

	public void should_throw_a_exception_if_believer_already_exists(){
	    var believer = FakeBeliever.random();

	    // Believer already exists !
	    Mockito.when(repository.existsById(believer.id()))
		.thenReturn(true);

	    assertThrows(BelieverAlreadyExistsException.class, () -> creator.create(believer));
	}
}

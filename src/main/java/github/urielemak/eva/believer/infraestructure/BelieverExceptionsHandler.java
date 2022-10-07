package github.urielemak.eva.believer.infraestructure;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import github.urielemak.eva.believer.domain.BelieverAlreadyExistsException;
import github.urielemak.eva.believer.domain.BelieverNotFoundException;

@ControllerAdvice
public class BelieverExceptionsHandler {

    private String error(String status, String msg){
	return String.format("%s - %s", status, msg);
    }

    @ExceptionHandler(BelieverAlreadyExistsException.class)
    public ResponseEntity<String> handleBelieverAlreadyExists(
	    BelieverAlreadyExistsException exception)
    {
	return ResponseEntity
	    .status(HttpStatus.CONFLICT)
	    .body(error("CONFLICT", exception.getMessage()));
    }

    @ExceptionHandler(BelieverNotFoundException.class)
    public ResponseEntity<String> handleBelieverNotFound(
	    BelieverNotFoundException excetpion)
    {
	return ResponseEntity
	    .status(HttpStatus.NOT_FOUND)
	    .body(error("NOT FOUND", excetpion.getMessage()));
    }

}

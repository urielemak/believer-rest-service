package github.urielemak.eva.believer.domain;

public class BelieverAlreadyExistsException extends RuntimeException {

    public BelieverAlreadyExistsException(String msg){
	super(msg);
    }
}

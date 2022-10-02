package github.urielemak.eva.believer.domain;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class BelieverTest {
  
    @Test
    public void should_return_true_if_is_his_birthday(){
	var today = LocalDate.of(2022, 9, 28);
	var birthday = today;

	var believer = FakeBeliever.randomWithBirth(birthday);

	assertTrue(believer.isHisBirthday(today));
    }

    @Test
    public void should_return_false_if_isnt_his_birthday(){
	var today = LocalDate.of(2022, 5, 15);
	var birthday = LocalDate.of(2001, 9, 22);

	var believer = FakeBeliever.randomWithBirth(birthday); 

	assertFalse(believer.isHisBirthday(today));
    }
}

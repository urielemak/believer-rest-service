package github.urielemak.eva.believer.domain;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.UUID;

import com.github.javafaker.Faker;

public class FakeBeliever {

    private static Faker faker = new Faker();

    public static Believer random(){
	var birth = faker.date().birthday();

	return Believer.builder()
	    .id(UUID.randomUUID().toString())
	    .name(faker.name().firstName())
	    .lastName(faker.name().lastName())
	    .email(faker.internet().emailAddress())
	    .birth(localDateOf(birth))
	    .build();
    }

    public static Believer randomWithBirth(LocalDate birth){
	return Believer.builder()
	    .id(UUID.randomUUID().toString())
	    .name(faker.name().firstName())
	    .lastName(faker.name().lastName())
	    .email(faker.internet().emailAddress())
	    .birth(birth)
	    .build();
    }

    private static LocalDate localDateOf(Date date){
	return Instant.ofEpochMilli(date.getTime())
	    .atZone(ZoneId.systemDefault())
	    .toLocalDate();
    }
}

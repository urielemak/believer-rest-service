package github.urielemak.eva.believer.domain;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Accessors(fluent = true)
@JsonPropertyOrder({"id", "name", "lastName", "email", "birth"})
public class Believer {

    @Id
    @JsonProperty
    private Long id;

    @JsonProperty
    private String name;

    @JsonProperty
    private String lastName;

    @JsonProperty
    private String phoneNumber;

    @JsonProperty
    private String email;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate birth;

    public boolean isHisBirthday(LocalDate date){
	final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM");

	var birthDay = birth().format(formatter);

	return (birthDay.equals(date.format(formatter)));
    } 
}

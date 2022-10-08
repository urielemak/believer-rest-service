package github.urielemak.eva.believer.infraestructure;

import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import github.urielemak.eva.believer.domain.Believer;
import github.urielemak.eva.believer.service.BelieverCreator;
import github.urielemak.eva.believer.service.BelieverDeleter;
import github.urielemak.eva.believer.service.BelieverFinder;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
public record BelieverController(
	BelieverFinder finder,
	BelieverDeleter  deleter,
	BelieverCreator creator){

    @Operation(summary = "Return all believers")
    @GetMapping("/believers")
    public List<Believer> believers(){
	return this.finder.all();
    }

    @Operation(summary = "Return all believers born in a given date range")
    @GetMapping("/believers/born")
    public List<Believer> believersByQuery(
	    @RequestParam("from")
	    @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate from,
	    @RequestParam("to") 
	    @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate to)
    {
	return this.finder.all(from, to);
    }

    @Operation(summary = "Creates a believer resource")
    @ApiResponses(value = {
	@ApiResponse(responseCode = "201", description = "Beliver successfully created"),
	@ApiResponse(responseCode = "409", description = "Believer already exists"),
	@ApiResponse(responseCode = "500", description = "Unhandled exception")
    })
    @PostMapping("/believer")
    public ResponseEntity<HttpStatus> create(@RequestBody Believer believer){
	this.creator.create(believer);
	return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = "Deletes a beliver resource")
    @ApiResponses(value = {
	@ApiResponse(responseCode = "200", description = "Believer successfully deleted"),
	@ApiResponse(responseCode = "401", description = "Believer by id not found"),
	@ApiResponse(responseCode = "500", description = "Unhandled excption")
    })
    @DeleteMapping("/believer/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") String believerId){
	this.deleter.byId(believerId);
	return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Operation(summary = "Return if it's a believer's birthday.")
    @ApiResponses(value = {
	@ApiResponse(responseCode = "200", description = "Boolean successfully returned")
    })
    @GetMapping("/believer/{id}/ishisbirthday")
    public boolean isHisBirthday(@PathVariable("id") String believerId){
	var believer = this.finder.byId(believerId).get();
	var today = LocalDate.now();
	return believer.isHisBirthday(today);
    }
}

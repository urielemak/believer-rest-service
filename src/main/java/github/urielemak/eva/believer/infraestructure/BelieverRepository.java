package github.urielemak.eva.believer.infraestructure;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import github.urielemak.eva.believer.domain.Believer;

@Repository
public interface BelieverRepository extends JpaRepository<Believer, Long>{

    @Query("SELECT b FROM Believer as b WHERE b.birth BETWEEN :from AND :to")
    public List<Believer> allFrom(
	    @Param("from") LocalDate from,
	    @Param("to") LocalDate to); 
}

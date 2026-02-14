package es.codeurjc.db.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import es.codeurjc.db.model.Match;
import es.codeurjc.db.model.Tournament;

public interface MatchRepository extends JpaRepository<Match, Long> {

	@Query("SELECT m FROM Match m WHERE m.tournament = :t")
	public List<Match> getMatchesByTournament(Tournament t);

}

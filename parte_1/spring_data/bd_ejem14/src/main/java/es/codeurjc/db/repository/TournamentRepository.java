package es.codeurjc.db.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import es.codeurjc.db.model.Team;
import es.codeurjc.db.model.Tournament;

public interface TournamentRepository extends JpaRepository<Tournament, Long> {

	@Query("SELECT distinct t FROM Match m JOIN m.tournament t " +
		"WHERE m.team1 = :team OR m.team2 = :team")
	public List<Tournament> getTournamentsByTeam(Team team);

    public Tournament findByName(String tournament);

}

package es.codeurjc.db.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import es.codeurjc.db.model.Team;
import es.codeurjc.db.model.Tournament;

public interface TeamRepository extends JpaRepository<Team, Long> {

	@Query("SELECT distinct team FROM Match m, Team team "
			+ "WHERE (m.team1 = team OR m.team2 = team) AND  m.tournament = :t")
	public List<Team> getTeamsByTournament(Tournament t);

    public Team findByName(String team);

}

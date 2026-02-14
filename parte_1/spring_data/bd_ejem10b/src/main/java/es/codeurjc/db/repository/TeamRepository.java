package es.codeurjc.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import es.codeurjc.db.model.Team;
import jakarta.transaction.Transactional;

public interface TeamRepository extends JpaRepository<Team, Long> {

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM player_teams WHERE teams_id = :teamId", nativeQuery = true)
    public void deleteTeamFromAllPlayers(@Param("teamId") Long teamId);

}
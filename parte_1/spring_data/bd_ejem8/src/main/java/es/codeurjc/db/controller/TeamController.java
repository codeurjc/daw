package es.codeurjc.db.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import es.codeurjc.db.model.Player;
import es.codeurjc.db.model.Team;
import es.codeurjc.db.repository.PlayerRepository;
import es.codeurjc.db.repository.TeamRepository;
import jakarta.annotation.PostConstruct;

@Controller
public class TeamController {

	@Autowired
	private TeamRepository teamRepository;
	
	@Autowired
	private PlayerRepository playerRepository;

	@PostConstruct
	public void init() {
		
		Team team = new Team("Selección Española", 1);
		
		teamRepository.save(team);
		
		Player p1 = new Player("Torres", 10);
		Player p2 = new Player("Iniesta", 10);
		Player p3 = new Player("Messi", 20);
		
		p1.setTeam(team);
		p2.setTeam(team);
		
		playerRepository.save(p1);
		playerRepository.save(p2);
		playerRepository.save(p3);
	}

	@GetMapping("/")
	public String getIndex(Model model) {
		model.addAttribute("teams", teamRepository.findAll());
		model.addAttribute("players", playerRepository.findAll());
		return "index";
	}

	@PostMapping("/teams/new")
	public String newTeam(Team team) {
		teamRepository.save(team);
		return "saved_team";
	}

	@PostMapping("/teams/{team_id}/players/new")
	public String newPlayer(Player player, @PathVariable long team_id) {
		playerRepository.save(player);
		Team team = teamRepository.findById(team_id).get();
		player.setTeam(team);
		playerRepository.save(player);
		return "saved_player";
	}

	@GetMapping("/teams/{id}")
	public String getTeam(Model model, @PathVariable long id) {
		Optional<Team> teamOptional = teamRepository.findById(id);
		if(teamOptional.isEmpty()) {
			return "team_not_found";
		}else{
			Team team = teamOptional.get();
			model.addAttribute("team", team);
			return "show_team";
		}
	}

	@GetMapping("/players/{id}")
	public String getPlayer(Model model, @PathVariable long id) {
		Optional<Player> playerOptional = playerRepository.findById(id);
		if(playerOptional.isEmpty()) {
			return "player_not_found";
		}else{
			Player player = playerOptional.get();
			model.addAttribute("player", player);
			return "show_player";
		}
	}
	
	// Deleting a team doesn't delete its associated players
	@PostMapping("/teams/{id}/delete")	
	public String deleteTeam(@PathVariable Long id) {
		Optional<Team> teamOptional = teamRepository.findById(id);
		if(teamOptional.isEmpty()) {
			return "team_not_found";
		}else{
			Team team = teamOptional.get();
			team.getPlayers().forEach(player -> player.setTeam(null));
			teamRepository.deleteById(id);
			return "deleted_team";
		}
	}
	
	// A player with a team now can be deleted
	@PostMapping("/players/{id}/delete")	
	public String deleteProject(@PathVariable Long id) {
		Optional<Player> playerOptional = playerRepository.findById(id);
		if(playerOptional.isEmpty()) {
			return "player_not_found";
		}else{
			playerRepository.deleteById(id);
			return "deleted_player";
		}
	}
}

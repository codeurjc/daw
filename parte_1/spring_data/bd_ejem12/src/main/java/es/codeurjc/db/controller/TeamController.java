package es.codeurjc.db.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
		
		Team team1 = new Team("Selección Española", 1);
		Team team2 = new Team("FC Barcelona", 2);
		Team team3 = new Team("Atlético de Madrid", 3);
		
		teamRepository.save(team1);
		teamRepository.save(team2);
		teamRepository.save(team3);
		
		Player p1 = new Player("Torres", 10);
		Player p2 = new Player("Iniesta", 10);
		Player p3 = new Player("Messi", 20);
		
		p1.getTeams().add(team1);
		p1.getTeams().add(team3);
		
		p2.getTeams().add(team1);
		p2.getTeams().add(team2);

		p3.getTeams().add(team2);
		
		playerRepository.save(p1);
		playerRepository.save(p2);
		playerRepository.save(p3);
	}

	@GetMapping("/")
	public String getIndex(Model model, @RequestParam(required=false) String teamName) {
		if(teamName == null){
			model.addAttribute("teams", teamRepository.findAll());
		} else {
			model.addAttribute("teams", teamRepository.findByName(teamName));
		}
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
		player.getTeams().add(team);
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
			model.addAttribute("teams", teamRepository.findAll());
			model.addAttribute("player", player);
			return "show_player";
		}
	}

	@PostMapping("/players/{player_id}/add_team/")
	public String addTeamToPlayer(@PathVariable long player_id, long team_id) {
		Player player = playerRepository.findById(player_id).get();
		Team team = teamRepository.findById(team_id).get();

		// Avoid adding the same team twice
		if (!player.getTeams().contains(team)) player.getTeams().add(team);
		
		playerRepository.save(player);

		return "redirect:/players/" + player_id;
	}

	@PostMapping("/players/{player_id}/remove_team/{team_id}")
	public String removeTeamFromPlayer(@PathVariable long player_id, @PathVariable long team_id) {
		Player player = playerRepository.findById(player_id).get();
		Team team = teamRepository.findById(team_id).get();

		player.getTeams().remove(team);
		
		playerRepository.save(player);

		return "redirect:/players/" + player_id;
	}
	
	// Deleting a team doesn't delete its associated players
	@PostMapping("/teams/{id}/delete")	
	public String deleteTeam(@PathVariable Long id) {
		Optional<Team> teamOptional = teamRepository.findById(id);
		if(teamOptional.isEmpty()) {
			return "team_not_found";
		}else{
			Team team = teamOptional.get();
			team.getPlayers().forEach(player -> player.getTeams().remove(team));
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

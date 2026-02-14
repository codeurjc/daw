package es.codeurjc.db.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import es.codeurjc.db.model.Match;
import es.codeurjc.db.model.Team;
import es.codeurjc.db.model.Tournament;
import es.codeurjc.db.repository.MatchRepository;
import es.codeurjc.db.repository.TeamRepository;
import es.codeurjc.db.repository.TournamentRepository;
import jakarta.annotation.PostConstruct;


@Controller
public class SoccerController {
    
    @Autowired
	private TournamentRepository tournamentRepository;
	
	@Autowired
	private MatchRepository matchRepository;
	
	@Autowired
	private TeamRepository teamRepository;
	
	@PostConstruct
	public void init() {
		
		Team t1 = new Team("Real Madrid");
		Team t2 = new Team("FC Barcelona");
		Team t3 = new Team("Atlético de Madrid");
		
		teamRepository.save(t1);
		teamRepository.save(t2);
		teamRepository.save(t3);
		
		Tournament tournament = new Tournament("La Liga");
		tournamentRepository.save(tournament);
		
		Match m1 = new Match("El clásico");
		m1.setTeam1(t1);
		m1.setTeam2(t2);
		
		Match m2 = new Match("Atlético - Barça");
		m2.setTeam1(t2);
		m2.setTeam2(t3);
		
		Match m3 = new Match("Derbi madrileño");
		m3.setTeam1(t1);
		m3.setTeam2(t3);
		
		m1.setTournament(tournament);
		m2.setTournament(tournament);
		m3.setTournament(tournament);
		
		matchRepository.save(m1);
		matchRepository.save(m2);
		matchRepository.save(m3);

        Tournament tournament2 = new Tournament("Copa del Rey");
		tournamentRepository.save(tournament2);

        Match m4 = new Match("Final");
        m4.setTeam1(t1);
        m4.setTeam2(t2);

        m4.setTournament(tournament2);

        matchRepository.save(m4);

	}

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/tournaments")
    public String getTournaments(Model model, @RequestParam(required = false) String team) {
        if(team != null){
            Team t = teamRepository.findByName(team);
            List<Tournament> tournaments = tournamentRepository.getTournamentsByTeam(t);
            model.addAttribute("tournaments", tournaments);
            return "tournaments";
        }else{
            List<Tournament> tournaments = tournamentRepository.findAll();
            model.addAttribute("tournaments", tournaments);
            return "tournaments";
        }
    }

    @GetMapping("/matches")
    public String getMatches(Model model, @RequestParam(required = false) String tournament) {
        if(tournament != null){
            Tournament t = tournamentRepository.findByName(tournament);
            List<Match> matches = matchRepository.getMatchesByTournament(t);
            model.addAttribute("matches", matches);
            return "matches";
        }else{
            List<Match> matches = matchRepository.findAll();
            model.addAttribute("matches", matches);
            return "matches";
        }
    }

    @GetMapping("/teams")
    public String getTeams(Model model, @RequestParam(required = false) String tournament) {
        if(tournament != null){
            Tournament t = tournamentRepository.findByName(tournament);
            List<Team> teams = teamRepository.getTeamsByTournament(t);
            model.addAttribute("teams", teams);
            return "teams";
        }else{
            List<Team> teams = teamRepository.findAll();
            model.addAttribute("teams", teams);
            return "teams";
        }
    }
    

}

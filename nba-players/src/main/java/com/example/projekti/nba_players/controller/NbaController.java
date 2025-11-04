package com.example.projekti.nba_players.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.projekti.nba_players.model.Player;
import com.example.projekti.nba_players.model.PlayerRepository;
import com.example.projekti.nba_players.model.PositionRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@Controller
public class NbaController {

    private final PlayerRepository playerRepository;
    private final PositionRepository positionRepository;

    public NbaController(PlayerRepository playerRepository, PositionRepository positionRepository) {
        this.playerRepository = playerRepository;
        this.positionRepository = positionRepository;
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/playerlist")
    public String playerList(Model model) {
        model.addAttribute("players", playerRepository.findAll());
        return "playerlist";
    }

    @GetMapping("/addplayer")
    public String addPlayerForm(Model model) {
        model.addAttribute("player", new Player());
        model.addAttribute("positions", positionRepository.findAll());
        return "addplayer";
    }

    @PostMapping("/addplayer")
    public String savePlayer(Player player) {
        playerRepository.save(player);
        return "redirect:/playerlist";
    }

    
    
}

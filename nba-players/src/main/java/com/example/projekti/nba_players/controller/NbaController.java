package com.example.projekti.nba_players.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.projekti.nba_players.model.Player;
import com.example.projekti.nba_players.model.PlayerRepository;
import com.example.projekti.nba_players.model.PositionRepository;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

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
    public String playerList(@RequestParam(required = false) String sort, Model model) {
        List<Player> players;
        
        if ("name".equals(sort)) {
            players = playerRepository.findAllByOrderByNameAsc();
        } else if ("points".equals(sort)) {
            players = playerRepository.findAllByOrderByPointsDesc();
        } else if ("team".equals(sort)) {
            players = playerRepository.findAllByOrderByTeamAsc();
        } else if ("age".equals(sort)) {
            players = playerRepository.findAllByOrderByAgeAsc();
        } else {
            players = (List<Player>) playerRepository.findAll();
        }
        
        model.addAttribute("players", players);
        return "playerlist";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/addplayer")
    public String addPlayerForm(Model model) {
        model.addAttribute("player", new Player());
        model.addAttribute("positions", positionRepository.findAll());
        return "addplayer";
    }

    @PostMapping("/addplayer")
    public String savePlayer(@Valid Player player, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("positions", positionRepository.findAll());
            return "addplayer";
        }
        playerRepository.save(player);
        return "redirect:/playerlist";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/delete/{id}")
    public String deletePlayer(@PathVariable("id") Long playerId) {
        playerRepository.deleteById(playerId);
        return "redirect:/playerlist";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/edit/{id}")
    public String editPlayer(@PathVariable("id") Long playerId, Model model) {
        Player player = playerRepository.findById(playerId).orElse(null);
        model.addAttribute("player", player);
        model.addAttribute("positions", positionRepository.findAll());
        return "editplayer";
    }

    @PostMapping("/edit/{id}")
    public String updatePlayer(@PathVariable("id") Long id, @Valid Player updatedPlayer, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("positions", positionRepository.findAll());
            return "editplayer";
        }
        updatedPlayer.setId(id);
        playerRepository.save(updatedPlayer);
        return "redirect:/playerlist";
    }
}

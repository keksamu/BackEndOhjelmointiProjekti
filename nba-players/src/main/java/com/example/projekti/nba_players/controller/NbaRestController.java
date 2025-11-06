package com.example.projekti.nba_players.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.RestController;

import com.example.projekti.nba_players.model.Player;
import com.example.projekti.nba_players.model.PlayerRepository;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;




@RestController
public class NbaRestController {

    private PlayerRepository playerRepository;

    public NbaRestController(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    @RequestMapping(value="api/players", method=RequestMethod.GET)
    public @ResponseBody List<Player> getAllPlayers() {
        return (List<Player>) playerRepository.findAll();
    }

        @RequestMapping(value="/api/players/{id}", method=RequestMethod.GET)
    public @ResponseBody Optional<Player> findPlayerRest(@PathVariable("id") Long playerId) {
        return playerRepository.findById(playerId);
    }

    @RequestMapping(value="/api/players", method=RequestMethod.POST)
    public @ResponseBody Player addPlayer(@RequestBody Player newPlayer) {
        return playerRepository.save(newPlayer);
}

    @RequestMapping(value="/api/players/{id}", method=RequestMethod.DELETE)
    public void deletePlayer(@PathVariable("id") Long playerId) {
        playerRepository.deleteById(playerId);
}

    @RequestMapping(value="/api/players/{id}", method=RequestMethod.PUT)
    public @ResponseBody Player updatePlayer(@PathVariable("id") Long playerId, @RequestBody Player updatedPlayer) {
        updatedPlayer.setId(playerId);
        return playerRepository.save(updatedPlayer);
    }
    
    

}

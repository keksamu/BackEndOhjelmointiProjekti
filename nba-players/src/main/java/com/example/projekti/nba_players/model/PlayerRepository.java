package com.example.projekti.nba_players.model;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface PlayerRepository extends CrudRepository<Player, Long> {
    List<Player> findAllByOrderByNameAsc();
    List<Player> findAllByOrderByPointsDesc();
    List<Player> findAllByOrderByTeamAsc();
    List<Player> findAllByOrderByAgeAsc();
}

package com.example.projekti.nba_players;

import com.example.projekti.nba_players.model.Position;
import com.example.projekti.nba_players.model.PositionRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataLoader {

    @Bean
    CommandLineRunner initDatabase(PositionRepository positionRepository) {
        return args -> {
            if (positionRepository.count() == 0) {
                positionRepository.save(new Position("Point Guard (PG)"));
                positionRepository.save(new Position("Shooting Guard (SG)"));
                positionRepository.save(new Position("Small Forward (SF)"));
                positionRepository.save(new Position("Power Forward (PF)"));
                positionRepository.save(new Position("Center (C)"));
            }
        };
    }
}
package com.example.projekti.nba_players;

import com.example.projekti.nba_players.model.AppUser;
import com.example.projekti.nba_players.model.AppUserRepository;
import com.example.projekti.nba_players.model.Position;
import com.example.projekti.nba_players.model.PositionRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class DataLoader {

    @Bean
    CommandLineRunner initDatabase(PositionRepository positionRepository, AppUserRepository userRepository) {
        return args -> {
            if (positionRepository.count() == 0) {
                positionRepository.save(new Position("Point Guard (PG)"));
                positionRepository.save(new Position("Shooting Guard (SG)"));
                positionRepository.save(new Position("Small Forward (SF)"));
                positionRepository.save(new Position("Power Forward (PF)"));
                positionRepository.save(new Position("Center (C)"));
            }
            

            if (userRepository.count() == 0) {
                BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
                
                AppUser admin = new AppUser("admin", encoder.encode("admin"), "admin@nba.com", "ADMIN");

                AppUser user = new AppUser("user", encoder.encode("user"), "user@nba.com", "USER");

                userRepository.save(admin);
                userRepository.save(user);
            }
        };
    }
}
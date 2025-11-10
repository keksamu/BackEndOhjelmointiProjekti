package com.example.projekti.nba_players.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.http.HttpMethod;

@Configuration
@EnableMethodSecurity(securedEnabled = true)
public class SecurityConfig {

    private final UserDetailsService userDetailsService; 

    public SecurityConfig(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }
    
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/h2-console/**").permitAll()
                .requestMatchers("/css/**").permitAll()
                .requestMatchers("/register").permitAll()
                .requestMatchers(HttpMethod.GET, "/api/players/**").authenticated()
                .requestMatchers(HttpMethod.POST, "/api/players/**").hasAuthority("ADMIN")
                .requestMatchers(HttpMethod.PUT, "/api/players/**").hasAuthority("ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/api/players/**").hasAuthority("ADMIN") 
                .anyRequest().authenticated()
            )
            .formLogin(form -> form
                .loginPage("/login")
                .permitAll()
                .defaultSuccessUrl("/playerlist", true)
            )
            .logout(logout -> logout
                .permitAll()
            )
            .httpBasic(basic -> {})
            .csrf(csrf -> csrf
                .ignoringRequestMatchers("/api/**", "/h2-console/**"))
            .headers(headers -> headers
                .frameOptions(frameOptions -> frameOptions.sameOrigin()));
        return http.build();
    }
}
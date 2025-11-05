package com.example.projekti.nba_players.service;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.projekti.nba_players.model.AppUser;
import com.example.projekti.nba_players.model.AppUserRepository;

@Service
public class UserDetailServiceImpl implements UserDetailsService {
    private final AppUserRepository appUserRepository;

    public UserDetailServiceImpl(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser curruser = appUserRepository.findByUsername(username);
        UserDetails user = new org.springframework.security.core.userdetails.User(username, curruser.getPasswordHash(), AuthorityUtils.createAuthorityList(curruser.getRole()));
        return user;
    }

}   

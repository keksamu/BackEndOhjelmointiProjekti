package com.example.projekti.nba_players.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class NbaController {

    @RequestMapping("/main")
    @ResponseBody
    public String playerName() {
        return "LeBron James";
    }
    

}

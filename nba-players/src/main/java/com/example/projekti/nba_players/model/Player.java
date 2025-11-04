package com.example.projekti.nba_players.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String team;
    private int number;
    private String country;
    private int height;
    private int weight;
    private int age;
    private int points;

    @ManyToOne
    @JoinColumn(name = "position_id")
    @JsonIgnoreProperties("players")
    private Position position;

    public Player() {
    }

    public Player(Long id, String name, String team, int number, String country, int height, int weight, int age,
            int points) {
        this.id = id;
        this.name = name;
        this.team = team;
        this.number = number;
        this.country = country;
        this.height = height;
        this.weight = weight;
        this.age = age;
        this.points = points;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return "Player [id=" + id + ", name=" + name + ", team=" + team + ", number=" + number + ", country=" + country
                + ", height=" + height + ", weight=" + weight + ", age=" + age + ", points=" + points + ", position=" + (position != null ? position.getName() : "null") + "]";
    }

}

package edu.northeastern.cs5200.person.trainer;

import edu.northeastern.cs5200.person.Person;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "Trainer")
public class Trainer extends Person {
    private int berries;
    private int potion;
    private int coins;
    private int points;

    public int getBerries() {
        return berries;
    }

    public void setBerries(int berries) {
        this.berries = berries;
    }

    public int getPotion() {
        return potion;
    }

    public void setPotion(int potion) {
        this.potion = potion;
    }

    public int getCoins() {
        return coins;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
}

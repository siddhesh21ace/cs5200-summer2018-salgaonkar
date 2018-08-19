package edu.northeastern.cs5200.person.trainer;

import com.fasterxml.jackson.annotation.JsonIgnore;
import edu.northeastern.cs5200.battle.Battle;
import edu.northeastern.cs5200.order.Order;
import edu.northeastern.cs5200.person.GymLeader.GymLeader;
import edu.northeastern.cs5200.person.Person;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "Trainer")
public class Trainer extends Person {
    private Integer berries = 20;
    private Integer potion = 20;
    private Integer coins = 100;
    private Integer points = 100;

    @OneToMany(
            mappedBy = "trainer",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JsonIgnore
    private List<Battle> battles = new ArrayList<>();

    @OneToMany(mappedBy = "trainer", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Order> orders = new ArrayList<>();

    public void addBattle(GymLeader gymLeader) {
        Battle battle = new Battle(this, gymLeader);
        battles.add(battle);
        gymLeader.getBattles().add(battle);
    }

    public void removeBattle(GymLeader gymLeader) {
        for (Iterator<Battle> iterator = battles.iterator(); iterator.hasNext(); ) {
            Battle battle = iterator.next();

            if (battle.getTrainer().equals(this) && battle.getGymLeader().equals(gymLeader)) {
                iterator.remove();
                battle.getGymLeader().getBattles().remove(battle);
                battle.setTrainer(null);
                battle.setGymLeader(null);
            }
        }
    }

    public void updateBattle(GymLeader gymLeader) {
        for (Battle battle : battles) {
            if (battle.getTrainer().equals(this) && battle.getGymLeader().equals(gymLeader)) {
                battle.setTrainerWon(true);
                break;
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass())
            return false;

        Trainer trainer = (Trainer) o;
        return Objects.equals(getUsername(), trainer.getUsername());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUsername());
    }

    public Integer getBerries() {
        return berries;
    }

    public void setBerries(Integer berries) {
        this.berries = berries;
    }

    public Integer getPotion() {
        return potion;
    }

    public void setPotion(Integer potion) {
        this.potion = potion;
    }

    public Integer getCoins() {
        return coins;
    }

    public void setCoins(Integer coins) {
        this.coins = coins;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public List<Battle> getBattles() {
        return battles;
    }

    public void setBattles(List<Battle> battles) {
        this.battles = battles;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public void addOrder(Order order) {
        orders.add(order);
        order.setTrainer(this);
    }

    public void removeOrder(Order order) {
        orders.remove(order);
        order.setTrainer(null);
    }
}

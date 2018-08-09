package edu.northeastern.cs5200.person.GymLeader;

import edu.northeastern.cs5200.person.Person;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "Gym_Leader")
public class GymLeader extends Person {
    private String type;
    private String badge;
    private String region;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBadge() {
        return badge;
    }

    public void setBadge(String badge) {
        this.badge = badge;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }
}

package edu.northeastern.cs5200.person.GymLeader;

import com.fasterxml.jackson.annotation.JsonIgnore;
import edu.northeastern.cs5200.battle.Battle;
import edu.northeastern.cs5200.person.Person;
import jdk.nashorn.internal.ir.annotations.Ignore;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "Gym_Leader")
public class GymLeader extends Person {

    private String type;
    private String badge;
    private String region;

    @OneToMany(
            mappedBy = "gymLeader",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JsonIgnore
    private List<Battle> battles = new ArrayList<>();

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GymLeader gymLeader = (GymLeader) o;
        return Objects.equals(badge, gymLeader.badge);
    }

    @Override
    public int hashCode() {
        return Objects.hash(badge);
    }

    public List<Battle> getBattles() {
        return battles;
    }

    public void setBattles(List<Battle> battles) {
        this.battles = battles;
    }
}

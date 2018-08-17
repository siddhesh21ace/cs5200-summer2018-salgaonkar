package edu.northeastern.cs5200.pokemon;

public class Stats {
    private String name;
    private Integer baseStats;

    public Stats() {
        super();
    }

    public Stats(String name, Integer baseStats) {
        this.name = name;
        this.baseStats = baseStats;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getBaseStats() {
        return baseStats;
    }

    public void setBaseStats(Integer baseStats) {
        this.baseStats = baseStats;
    }
}

package edu.northeastern.cs5200.pokemon;

import java.util.List;

public class Species {
    private String habitat;
    private String color;
    private String shape;
    private String evolves_from;
    private String description;
    private String genus;
    private List<EvoChain> evoChains;

    public Species() {
    }

    public String getHabitat() {
        return habitat;
    }

    public void setHabitat(String habitat) {
        this.habitat = habitat;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getShape() {
        return shape;
    }

    public void setShape(String shape) {
        this.shape = shape;
    }

    public String getEvolves_from() {
        return evolves_from;
    }

    public void setEvolves_from(String evolves_from) {
        this.evolves_from = evolves_from;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGenus() {
        return genus;
    }

    public void setGenus(String genus) {
        this.genus = genus;
    }

    public List<EvoChain> getEvoChains() {
        return evoChains;
    }

    public void setEvoChains(List<EvoChain> evoChains) {
        this.evoChains = evoChains;
    }
}

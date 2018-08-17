package edu.northeastern.cs5200.pokemon;

public class EvoChain {
    private String name;
    private Integer pokeId;

    public EvoChain() {
    }

    public EvoChain(String name, Integer pokeId) {
        this.name = name;
        this.pokeId = pokeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPokeId() {
        return pokeId;
    }

    public void setPokeId(Integer pokeId) {
        this.pokeId = pokeId;
    }
}

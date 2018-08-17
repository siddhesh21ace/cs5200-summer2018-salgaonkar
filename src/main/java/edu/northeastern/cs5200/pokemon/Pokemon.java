package edu.northeastern.cs5200.pokemon;

import com.fasterxml.jackson.annotation.JsonIgnore;
import edu.northeastern.cs5200.pet.Pet;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Pokemon")
public class Pokemon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "pokedex_number")
    private Integer pokedex_number;
    private String name;

    @Transient
    private me.sargunvohra.lib.pokekotlin.model.Pokemon metaPokemon;
    @Transient
    private List<String> types;
    @Transient
    private List<String> weaknesses;
    @Transient
    private List<Ability> abilities;
    @Transient
    private List<Stats> stats;
    @Transient
    private List<String> moves;
    @Transient
    private Species species;

    public Integer getPokedex_number() {
        return pokedex_number;
    }

    public void setPokedex_number(Integer pokedex_number) {
        this.pokedex_number = pokedex_number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public me.sargunvohra.lib.pokekotlin.model.Pokemon getMetaPokemon() {
        return metaPokemon;
    }

    public void setMetaPokemon(me.sargunvohra.lib.pokekotlin.model.Pokemon metaPokemon) {
        this.metaPokemon = metaPokemon;
    }

    public List<String> getTypes() {
        return types;
    }

    public void setTypes(List<String> types) {
        this.types = types;
    }

    public List<String> getWeaknesses() {
        return weaknesses;
    }

    public void setWeaknesses(List<String> weaknesses) {
        this.weaknesses = weaknesses;
    }

    public List<Ability> getAbilities() {
        return abilities;
    }

    public void setAbilities(List<Ability> abilities) {
        this.abilities = abilities;
    }

    public List<Stats> getStats() {
        return stats;
    }

    public void setStats(List<Stats> stats) {
        this.stats = stats;
    }

    public List<String> getMoves() {
        return moves;
    }

    public void setMoves(List<String> moves) {
        this.moves = moves;
    }

    public Species getSpecies() {
        return species;
    }

    public void setSpecies(Species species) {
        this.species = species;
    }
}

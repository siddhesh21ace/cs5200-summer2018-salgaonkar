package edu.northeastern.cs5200.pokemon;

import me.sargunvohra.lib.pokekotlin.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PokemonController {
    private final PokemonService pokemonService;

    @Autowired
    public PokemonController(PokemonService pokemonService) {
        this.pokemonService = pokemonService;
    }

    @GetMapping(value = "/pokeapi/pokemon")
    public NamedApiResourceList fetchAllPokemons() {
        return pokemonService.fetchAllPokemons();
    }

    @GetMapping(value = "/pokeapi/type")
    public NamedApiResourceList fetchAllTypes() {
        return pokemonService.fetchAllTypes();
    }

    @GetMapping(value = "/pokeapi/type/{typeId}")
    public Type fetchTypeById(@PathVariable("typeId") int typeId) {
        return pokemonService.fetchTypeById(typeId);
    }

    @GetMapping(value = "/pokeapi/pokemon/{pokemonId}")
    public Pokemon fetchPokemonById(@PathVariable("pokemonId") int pokemonId) {
        return pokemonService.fetchPokemonById(pokemonId);
    }

    @GetMapping(value = "/pokeapi/type/{typeId}/pokemon")
    public List<TypePokemon> fetchPokemonByTypeId(@PathVariable("typeId") int typeId) {
        return pokemonService.fetchPokemonByTypeId(typeId);
    }

    @GetMapping(value = "/pokeapi/pokemon/{pokemonId}/species")
    public PokemonSpecies fetchPokemonSpeciesById(@PathVariable("pokemonId") int pokemonId) {
        return pokemonService.fetchPokemonSpeciesById(pokemonId);
    }

    @GetMapping(value = "/pokeapi/evoChain/{evoChainId}")
    public EvolutionChain fetchEvolutionChainById(@PathVariable("evoChainId") int evoChainId) {
        return pokemonService.fetchEvolutionChainById(evoChainId);
    }

    @GetMapping(value = "/pokeapi/ability/{abilityId}")
    public me.sargunvohra.lib.pokekotlin.model.Ability fetchAbilityById(@PathVariable("abilityId") int abilityId) {
        return pokemonService.fetchAbilityById(abilityId);
    }

    /* DB API */
    @GetMapping(value = "/api/pokemon")
    public List<Pokemon> findAllPokemons() {
        return pokemonService.findAllPokemons();
    }

    @GetMapping(value = "/api/pokemon/{pokemonId}")
    public Pokemon findPetById(@PathVariable("pokemonId") Long pokemonId) {
        return pokemonService.findPokemonById(pokemonId);
    }

    @GetMapping(value = "/api/pokemon", params = "name")
    public Pokemon findPokemonByName(@RequestParam String name) {
        return pokemonService.findPokemonByName(name);
    }

}

package edu.northeastern.cs5200.pokemon;

import me.sargunvohra.lib.pokekotlin.client.PokeApi;
import me.sargunvohra.lib.pokekotlin.client.PokeApiClient;
import me.sargunvohra.lib.pokekotlin.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PokemonService {
    private static final PokeApi POKE_API = new PokeApiClient();
    private static final int OFFSET = 0;
    private static final int LIMIT = 1000;

    private final PokemonRepository pokemonRepository;

    @Autowired
    public PokemonService(PokemonRepository pokemonRepository) {
        this.pokemonRepository = pokemonRepository;
    }

    public NamedApiResourceList fetchAllPokemons() {
        return POKE_API.getPokemonList(OFFSET, LIMIT);
    }

    public NamedApiResourceList fetchAllTypes() {
        return POKE_API.getTypeList(OFFSET, LIMIT);
    }

    public Type fetchTypeById(int typeId) {
        return POKE_API.getType(typeId);
    }

    public Pokemon fetchPokemonById(int pokemonId) {
        me.sargunvohra.lib.pokekotlin.model.Pokemon metaPokemon = POKE_API.getPokemon(pokemonId);
        return populateDetails(metaPokemon);
    }

    public PokemonSpecies fetchPokemonSpeciesById(int pokemonId) {
        return POKE_API.getPokemonSpecies(pokemonId);
    }

    public EvolutionChain fetchEvolutionChainById(int evoChainId) {
        return POKE_API.getEvolutionChain(evoChainId);
    }

    public me.sargunvohra.lib.pokekotlin.model.Ability fetchAbilityById(int abilityId) {
        return POKE_API.getAbility(abilityId);
    }

    public List<TypePokemon> fetchPokemonByTypeId(int typeId) {
        Type type = fetchTypeById(typeId);
        return type.getPokemon();
    }

    /* DB Based APIs */
    public List<Pokemon> findAllPokemons() {
        return (List<Pokemon>) pokemonRepository.findAll();
    }

    Pokemon findPokemonByName(String name) {
        List<Pokemon> pokemons = (List<Pokemon>) pokemonRepository.findPokemonByName(name);
        if (!CollectionUtils.isEmpty(pokemons)) {
            return pokemons.get(0);
        }
        return null;
    }

    Pokemon findPokemonById(Long pokemonId) {
        Optional<Pokemon> data = pokemonRepository.findById(pokemonId);
        return data.orElse(null);
    }

    Pokemon populateDetails(me.sargunvohra.lib.pokekotlin.model.Pokemon metaPokemon) {
        Pokemon pokemon = new Pokemon();
        pokemon.setMetaPokemon(metaPokemon);

        List<String> types = new ArrayList<>();
        metaPokemon.getTypes().forEach(pokemonType -> types.add(pokemonType.getType().getName()));
        pokemon.setTypes(types);

        List<String> weaknessses = new ArrayList<>();
        metaPokemon.getTypes().forEach(pokemonType -> {
            Type type = fetchTypeById(pokemonType.getType().getId());
            List<NamedApiResource> list = type.getDamageRelations().getDoubleDamageFrom();
            list.forEach(obj -> weaknessses.add(obj.getName()));
        });
        pokemon.setWeaknesses(weaknessses);

        List<Ability> abilities = new ArrayList<>();
        metaPokemon.getAbilities().forEach(pokemonAbility -> {
            me.sargunvohra.lib.pokekotlin.model.Ability ability = fetchAbilityById(pokemonAbility.getAbility().getId());
            ability.getEffectEntries().forEach(effectEntry -> {
                if (effectEntry.getLanguage().getName().equals("en")) {
                    abilities.add(new Ability(pokemonAbility.getAbility().getName(), effectEntry.getShortEffect()));
                }
            });
        });
        pokemon.setAbilities(abilities);

        List<Stats> stats = new ArrayList<>();
        metaPokemon.getStats().forEach(pokeStats -> {
            stats.add(new Stats(pokeStats.getStat().getName(), pokeStats.getBaseStat()));
        });
        pokemon.setStats(stats);

        List<String> moves = new ArrayList<>();
        metaPokemon.getMoves().forEach(pokemonMove -> {
            moves.add(pokemonMove.getMove().getName());
        });
        pokemon.setMoves(moves);

        PokemonSpecies pokemonSpecies = fetchPokemonSpeciesById(metaPokemon.getId());
        Species species = new Species();
        species.setColor(pokemonSpecies.getColor().getName());
        species.setShape(pokemonSpecies.getShape().getName());

        if (pokemonSpecies.getHabitat() != null)
            species.setHabitat(pokemonSpecies.getHabitat().getName());
        if (pokemonSpecies.getEvolvesFromSpecies() != null)
            species.setEvolves_from(pokemonSpecies.getEvolvesFromSpecies().getName());

        pokemonSpecies.getFlavorTextEntries().forEach(pokemonSpeciesFlavorText -> {
            if (pokemonSpeciesFlavorText.getLanguage().getName().equals("en")) {
                species.setDescription(pokemonSpeciesFlavorText.getFlavorText());
            }
        });

        pokemonSpecies.getGenera().forEach(genus -> {
            if (genus.getLanguage().getName().equals("en")) {
                species.setGenus(genus.getGenus());
            }
        });

        List<EvoChain> evoChains = new ArrayList<>();
        Integer evoChainId = pokemonSpecies.getEvolutionChain().getId();
        ChainLink chainLink = fetchEvolutionChainById(evoChainId).getChain();

        do {
            evoChains.add(new EvoChain(chainLink.getSpecies().getName(), chainLink.getSpecies().getId()));
            chainLink = !CollectionUtils.isEmpty(chainLink.getEvolvesTo()) ? chainLink.getEvolvesTo().get(0) : null;
        } while (chainLink != null);

        species.setEvoChains(evoChains);

        pokemon.setSpecies(species);
        return pokemon;

    }
}



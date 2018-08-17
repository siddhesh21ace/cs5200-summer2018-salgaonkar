package edu.northeastern.cs5200.pokemon;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PokemonRepository extends CrudRepository<Pokemon, Long> {
    @Query("SELECT p FROM Pokemon p WHERE p.name=:name")
    Iterable<Pokemon> findPokemonByName(@Param("name") String name);
}

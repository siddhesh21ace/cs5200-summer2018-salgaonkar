package edu.northeastern.cs5200.pokemon;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Pokemon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int pokedex_no;

    public Pokemon() {

    }

    public Pokemon(int pokedex_no) {
        this.pokedex_no = pokedex_no;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPokedex_no() {
        return pokedex_no;
    }

    public void setPokedex_no(int pokedex_no) {
        this.pokedex_no = pokedex_no;
    }
}

package com.example.pokemongo1;

import java.util.ArrayList;

public class Trainer {
    private String id;
    private String name;
    private ArrayList<Pokemonp> pokemons;

    private static Trainer instance; // Singleton Pattern

    private Trainer(String id) {
        this.id = id;
        // TODO: get name, string from firebase

        pokemons = new ArrayList<>();
    }

    public static Trainer getInstance(String id) {
        if (instance == null) {
            instance = new Trainer(id);
        }
        return instance;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Pokemonp> getPokemons() {
        return pokemons;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addPokemon(Pokemonp pkm) {
        this.pokemons.add(pkm);
    }

    public void removePokemon(int pkmIndex) {
        this.pokemons.remove(pkmIndex);
    }
}

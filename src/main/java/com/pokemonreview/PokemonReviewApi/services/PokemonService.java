package com.pokemonreview.PokemonReviewApi.services;
import com.pokemonreview.PokemonReviewApi.dtos.PokemonDto;
import com.pokemonreview.PokemonReviewApi.dtos.PokemonResponse;

import java.util.List;

public interface PokemonService {
    PokemonDto createPokemon(PokemonDto pokemonDto);
    PokemonResponse getPokemons(int pageNo, int pageSize);
    PokemonDto updatePokemon(int id, PokemonDto pokemonDto);
    PokemonDto getPokemonById(int id);
    void deletePokemonById(int id);
}

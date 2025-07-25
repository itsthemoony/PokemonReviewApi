package com.pokemonreview.PokemonReviewApi.repository;

import com.pokemonreview.PokemonReviewApi.models.Pokemon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PokemonRepository extends JpaRepository<Pokemon, Integer> {
}

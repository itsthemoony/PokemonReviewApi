package com.pokemonreview.PokemonReviewApi.repository;

import com.pokemonreview.PokemonReviewApi.models.PokemonModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PokemonRepository extends JpaRepository<PokemonModel, Integer> {
}

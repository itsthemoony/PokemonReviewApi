package com.pokemonreview.PokemonReviewApi.repository;
import com.pokemonreview.PokemonReviewApi.models.ReviewModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<ReviewModel, Integer> {
    List<ReviewModel> findByPokemonId(int pokemonId);
}

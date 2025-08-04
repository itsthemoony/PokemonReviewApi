package com.pokemonreview.PokemonReviewApi.repository;
import com.pokemonreview.PokemonReviewApi.models.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Integer> {
    List<Review> findByPokemonId(int pokemonId);
}

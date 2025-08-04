package com.pokemonreview.PokemonReviewApi.dtos;
import lombok.Data;

@Data
public class ReviewDto {
    private int id;
    private String title;
    private String content;
    private int stars;
}

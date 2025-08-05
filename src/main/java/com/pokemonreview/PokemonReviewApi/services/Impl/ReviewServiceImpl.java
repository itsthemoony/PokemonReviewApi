package com.pokemonreview.PokemonReviewApi.services.Impl;
import com.pokemonreview.PokemonReviewApi.dtos.ReviewDto;
import com.pokemonreview.PokemonReviewApi.exceptions.PokemonNotFoundException;
import com.pokemonreview.PokemonReviewApi.exceptions.ReviewNotFoundException;
import com.pokemonreview.PokemonReviewApi.models.PokemonModel;
import com.pokemonreview.PokemonReviewApi.models.ReviewModel;
import com.pokemonreview.PokemonReviewApi.repository.PokemonRepository;
import com.pokemonreview.PokemonReviewApi.repository.ReviewRepository;
import com.pokemonreview.PokemonReviewApi.services.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewServiceImpl implements ReviewService {
    private ReviewRepository reviewRepository;
    private PokemonRepository pokemonRepository;

    @Autowired
    public ReviewServiceImpl(ReviewRepository reviewRepository, PokemonRepository pokemonRepository) {
        this.reviewRepository = reviewRepository;
        this.pokemonRepository = pokemonRepository;
    }

    @Override
    public ReviewDto createReview(int pokemonId, ReviewDto reviewDto) {
        ReviewModel review =  mapDtoToReview(reviewDto);
        PokemonModel pokemon = pokemonRepository.findById(pokemonId).orElseThrow(() -> new PokemonNotFoundException("Pokemon Not Found"));
        review.setPokemon(pokemon);
        ReviewModel newReview = reviewRepository.save(review);
        return mapReviewToDto(newReview);
    }

    @Override
    public List<ReviewDto> getReviewsByPokemonId(int id) {
        List<ReviewModel> reviews = reviewRepository.findByPokemonId(id);
        return reviews.stream().map(this::mapReviewToDto).collect(Collectors.toList());
    }

    @Override
    public ReviewDto getReviewById(int reviewId, int pokemonId) {
        PokemonModel pokemon = pokemonRepository.findById(pokemonId).orElseThrow(() -> new PokemonNotFoundException("Pokemon Not Found"));
        ReviewModel review = reviewRepository.findById(reviewId).orElseThrow(()-> new ReviewNotFoundException("Review Not Found"));
        if(review.getPokemon().getId() != pokemon.getId()){
            throw new ReviewNotFoundException("Review Not Found");
        }
        return mapReviewToDto(review);
    }

    @Override
    public ReviewDto updateReview(int reviewId, int pokemonId, ReviewDto reviewDto) {
        PokemonModel pokemon = pokemonRepository.findById(pokemonId).orElseThrow(() -> new PokemonNotFoundException("Pokemon Not Found"));
        ReviewModel review = reviewRepository.findById(reviewId).orElseThrow(()-> new ReviewNotFoundException("Review Not Found"));
        if(review.getPokemon().getId() != pokemon.getId()){
            throw new ReviewNotFoundException("Review Not Found");
        }
        review.setTitle(reviewDto.getTitle());
        review.setContent(reviewDto.getContent());
        review.setStars(reviewDto.getStars());
        ReviewModel updatedReview = reviewRepository.save(review);
        return mapReviewToDto(updatedReview);
    }

    @Override
    public void deleteReview(int reviewId, int pokemonId) {
        PokemonModel pokemon = pokemonRepository.findById(pokemonId).orElseThrow(() -> new PokemonNotFoundException("Pokemon Not Found"));
        ReviewModel review = reviewRepository.findById(reviewId).orElseThrow(()-> new ReviewNotFoundException("Review Not Found"));
        if(review.getPokemon().getId() != pokemon.getId()){
            throw new ReviewNotFoundException("Review Not Found");
        }
        reviewRepository.delete(review);
    }


    private ReviewDto mapReviewToDto(ReviewModel review) {
        ReviewDto reviewDto = new ReviewDto();
        reviewDto.setId(review.getId());
        reviewDto.setTitle(review.getTitle());
        reviewDto.setContent(review.getContent());
        reviewDto.setStars(review.getStars());
        return reviewDto;
    }

    private ReviewModel mapDtoToReview(ReviewDto reviewDto){
        ReviewModel review = new ReviewModel();
        review.setId(reviewDto.getId());
        review.setTitle(reviewDto.getTitle());
        review.setContent(reviewDto.getContent());
        review.setStars(reviewDto.getStars());
        return review;
    }
}

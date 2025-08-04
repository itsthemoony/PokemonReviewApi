package com.pokemonreview.PokemonReviewApi.controllers;
import com.pokemonreview.PokemonReviewApi.dtos.ReviewDto;
import com.pokemonreview.PokemonReviewApi.services.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/")
public class ReviewController {

    private final ReviewService reviewService;

    @Autowired
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping("pokemon/{pokemonId}/review")
    public ResponseEntity<ReviewDto> createReview(@PathVariable("pokemonId") int pokemonId, @RequestBody ReviewDto reviewDto) {
        return new ResponseEntity<>(reviewService.createReview(pokemonId, reviewDto), HttpStatus.CREATED);
    }

    @GetMapping("pokemon/{pokemonId}/review")
    public ResponseEntity<List<ReviewDto>>  getReviewsByPokemonId(@PathVariable("pokemonId") int pokemonId) {
        return new ResponseEntity<>(reviewService.getReviewsByPokemonId(pokemonId), HttpStatus.OK);
    }

    @GetMapping("pokemon/{pokemonId}/review/{id}")
    public ResponseEntity<ReviewDto> getReviewById(@PathVariable("pokemonId") int pokemonId, @PathVariable("id") int id) {
        ReviewDto reviewDto = reviewService.getReviewById(pokemonId, id);
        return new ResponseEntity<>(reviewDto, HttpStatus.OK);
    }

    @PutMapping("pokemon/{pokemonId}/review/{id}")
    public ResponseEntity<ReviewDto> updateReview(@PathVariable("pokemonId") int pokemonId, @PathVariable("id") int reviewId, @RequestBody ReviewDto reviewDto) {
        ReviewDto updatedReview = reviewService.updateReview(pokemonId, reviewId, reviewDto);
        return new ResponseEntity<>(updatedReview, HttpStatus.OK);
    }

    @DeleteMapping("pokemon/{pokemonId}/review/{id}")
    public ResponseEntity<String > deleteReview(@PathVariable("pokemonId") int pokemonId, @PathVariable("id") int reviewId) {
        reviewService.deleteReview(pokemonId, reviewId);
        return new ResponseEntity<>("Review Deleted Successfully", HttpStatus.OK);
    }


}

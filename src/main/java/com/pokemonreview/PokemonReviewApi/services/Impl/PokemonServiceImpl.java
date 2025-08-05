package com.pokemonreview.PokemonReviewApi.services.Impl;
import com.pokemonreview.PokemonReviewApi.dtos.PokemonDto;
import com.pokemonreview.PokemonReviewApi.dtos.PokemonResponse;
import com.pokemonreview.PokemonReviewApi.exceptions.PokemonNotFoundException;
import com.pokemonreview.PokemonReviewApi.models.PokemonModel;
import com.pokemonreview.PokemonReviewApi.repository.PokemonRepository;
import com.pokemonreview.PokemonReviewApi.services.PokemonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PokemonServiceImpl implements PokemonService {

    private PokemonRepository pokemonRepository;

    @Autowired
    public PokemonServiceImpl(PokemonRepository pokemonRepository) {
        this.pokemonRepository = pokemonRepository;
    }

    @Override
    public PokemonDto createPokemon(PokemonDto pokemonDto) {
        PokemonModel pokemon = mapToEntity(pokemonDto);
        PokemonModel savedPokemon = pokemonRepository.save(pokemon);
        return mapPokemonToDto(savedPokemon);
    }

    @Override
    public PokemonResponse getPokemons(int pageNo, int pageSize) {
        PageRequest pageable = PageRequest.of(pageNo, pageSize);
        Page<PokemonModel> pokemons = pokemonRepository.findAll(pageable);
        List<PokemonModel> listOfPokemon = pokemons.getContent();
        List<PokemonDto> content = pokemons.stream().map(this::mapPokemonToDto).collect(Collectors.toList());
        PokemonResponse pokemonResponse = new PokemonResponse();
        pokemonResponse.setContent(content);
        pokemonResponse.setPageNo(pokemons.getNumber());
        pokemonResponse.setPageSize(pokemons.getSize());
        pokemonResponse.setTotalElements(pokemons.getTotalElements());
        pokemonResponse.setTotalPages(pokemons.getTotalPages());
        pokemonResponse.setLast(pokemons.isLast());

        return pokemonResponse;
    }

    @Override
    public PokemonDto updatePokemon(int id, PokemonDto pokemonDto) {
        PokemonModel pokemon = pokemonRepository.findById(id).orElseThrow(() -> new PokemonNotFoundException("Pokemon not found"));
        pokemon.setName(pokemonDto.getName());
        pokemon.setType(pokemonDto.getType());
        pokemonRepository.save(pokemon);
        return mapPokemonToDto(pokemon);
    }

    @Override
    public PokemonDto getPokemonById(int id) {
        PokemonModel pokemon = pokemonRepository.findById(id).orElseThrow(() -> new PokemonNotFoundException("Pokemon id " + id + " not found"));
        return mapPokemonToDto(pokemon);
    }

    @Override
    public void deletePokemonById(int id) {
        PokemonModel pokemon = pokemonRepository.findById(id).orElseThrow(() -> new PokemonNotFoundException("Pokemon id " + id + " not found"));
        pokemonRepository.delete(pokemon);
    }


    private PokemonDto mapPokemonToDto(PokemonModel pokemon) {
        PokemonDto pokemonDto = new PokemonDto();
        pokemonDto.setId(pokemon.getId());
        pokemonDto.setName(pokemon.getName());
        pokemonDto.setType(pokemon.getType());

        return pokemonDto;
    }

    private PokemonModel mapToEntity(PokemonDto pokemonDto){
        PokemonModel pokemon = new PokemonModel();
        pokemon.setName(pokemonDto.getName());
        pokemon.setType(pokemonDto.getType());
        return pokemon;
    }
}

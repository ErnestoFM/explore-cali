package com.example.explorecalijpa.controller;

import com.example.explorecalijpa.dto.RatingDto;
import com.example.explorecalijpa.model.TourRating;
import com.example.explorecalijpa.service.TourRatingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;


/**
 * Tour Rating Controller
 *
 * Created by Mary Ellen Bowman
 */
@RestController
@Tag(name = "Tour Rating", description = "The rating for a Tour API")
@RequestMapping(path = "/tours/{tourId}/ratings")
public class TourRatingController {
    private final TourRatingService tourRatingService;

    public TourRatingController(TourRatingService tourRatingService) {
        this.tourRatingService = tourRatingService;
    }

    @PostMapping
    @Operation(summary = "Create a Tour Rating")
    @ResponseStatus(HttpStatus.CREATED)
    public void createTourRating(@PathVariable(value = "tourId") int tourId,
                                 @RequestBody @Valid RatingDto ratingDto){
    tourRatingService.createNew(tourId, ratingDto.getCustomerId(), ratingDto.getScore(), ratingDto.getComment());


    }

    @Operation(summary = "Lookup All Ratings for a Tour")
    @GetMapping
    public List<RatingDto> getALlRatingForTour(@PathVariable(value = "tourId") int tourId){
        List<TourRating> tourRatings = tourRatingService.lookupRatings(tourId);
        return tourRatings.stream().map(RatingDto::new).toList();
    }

    @GetMapping("/avarage")
    public Map<String, Double> getAvarage(@PathVariable(value = "tourId") int tourId){
        return Map.of("avarage", tourRatingService.getAverageScore(tourId));
    }

    @PutMapping
    public RatingDto updateWithPut(@PathVariable(value = "tourId") int tourId,
                                   @RequestBody @Valid RatingDto ratingDto){
        return new RatingDto(tourRatingService.update(
                tourId,
                ratingDto.getCustomerId(),
                ratingDto.getScore(),
                ratingDto.getComment())
        );
    }

    @PatchMapping
    public RatingDto updateWithPatch(@PathVariable(value = "tourId") int tourId,
                                     @RequestBody @Valid RatingDto ratingDto){
        return new RatingDto(tourRatingService.updateSome(
                tourId,
                ratingDto.getCustomerId(),
                Optional.ofNullable(ratingDto.getScore()),
                Optional.ofNullable(ratingDto.getComment()))
        );
    }

    @DeleteMapping("{customerId}")
    public void delete(@PathVariable(value = "tourId") int tourId,
                       @PathVariable(value = "customerId") int customerId){
        tourRatingService.delete(tourId, customerId);
    }

    @ExceptionHandler(NoSuchElementException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String retur404(NoSuchElementException e){
        return e.getMessage();
    }
}
package com.swapi.sevices.models;

import com.google.gson.annotations.SerializedName;
import io.qameta.allure.internal.shadowed.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class Film {
    private String title;
    @SerializedName("episode_id")
    private Long episodeId;
    @SerializedName("opening_crawl")
    private String openingCrawl;
    private String director;
    private String producer;
    @SerializedName("release_date")
    private String releaseDate;
    private List<String> characters;
    private List<String> planets;
    private List<String> starships;
    private List<String> vehicles;
    private List<String> species;
    private String created;
    private String edited;
    private String url;

}

package com.swapi.helpares;

import com.google.gson.reflect.TypeToken;
import com.swapi.sevices.FilmService;
import com.swapi.sevices.models.BaseResponse;
import com.swapi.sevices.models.Film;
import io.restassured.response.Response;
import lombok.extern.log4j.Log4j2;

import java.util.*;
@Log4j2
public class FilmServiceHelper {

    private FilmServiceHelper() {

    }


    public static BaseResponse<Film> getFilmsList() {
        log.info("get  films list");
        Response response = FilmService.getFilms().then().statusCode(200).extract().response();
        return response.andReturn().as(new TypeToken<BaseResponse<Film>>() {
        }.getType());
    }

    public static Film getLatestFilm() {
        log.info("get Latest film");
        List<Film> results = getFilmsList().getResults();
        return results.stream().max(Comparator.comparing(Film::getReleaseDate)).orElse(null);
    }




}

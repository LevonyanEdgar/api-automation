package com.swapi.sevices;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class FilmService extends BaseService {
   private static String FILM_BASE_PATH= "films/";

    private FilmService(){

    }

    public static Response getFilms(){
        RequestSpecification request = configRequestAPI();
        request.basePath(FILM_BASE_PATH);
        return get(request);
    }




}

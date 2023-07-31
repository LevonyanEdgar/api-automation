package com.swapi.sevices;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class PeopleService extends BaseService {

    private static String PEOPLE_BASE_PATH= "people/";

    private PeopleService() {

    }

    public static Response getPeople(String person) {
        RequestSpecification request = configRequestAPI();
        request.baseUri(person);
        return get(request);
    }

    public static Response getPeoples(int page){
        RequestSpecification request = configRequestAPI();
        request.queryParam("page", page);
        request.basePath(PEOPLE_BASE_PATH);
        return get(request);
    }

}

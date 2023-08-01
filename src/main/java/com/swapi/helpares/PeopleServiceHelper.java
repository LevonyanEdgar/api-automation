package com.swapi.helpares;

import com.google.gson.reflect.TypeToken;
import com.swapi.sevices.PeopleService;
import com.swapi.sevices.models.BaseResponse;
import com.swapi.sevices.models.People;
import io.restassured.response.Response;
import lombok.extern.log4j.Log4j2;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Log4j2
public class PeopleServiceHelper {

    private PeopleServiceHelper() {

    }


    public static List<People> getPeoples(List<String> peoplesUrlList) {
        List<People> peopleList = new ArrayList<>();
        for (String url : peoplesUrlList
        ) {
            log.info("Gating user by url " + url);
            Response response = PeopleService.getPeople(url).then().statusCode(200).extract().response();
            People peopleBaseResponse = response.andReturn().as(new TypeToken<People>() {
            }.getType());
            peopleList.add(peopleBaseResponse);
        }
        return peopleList;
    }

    public static BaseResponse<People> getPeoples(int page) {
        Response response = PeopleService.getPeoples(page).then().statusCode(200).extract().response();
        return response.andReturn().as(new TypeToken<BaseResponse<People>>() {
        }.getType());
    }


    public static List<People> getAllPeoples() {
        int firstPage = 1;
        int count = 10;
        boolean hasNext = true;
        List<People> peopleList = new ArrayList<>();
        while (hasNext && count != 0) {
            BaseResponse<People> peoples = getPeoples(firstPage);
            peoples.getResults().stream().forEach(people -> peopleList.add(people));
            hasNext = peoples.getNext() != null;
            count--;
            firstPage++;
        }
        return peopleList;
    }


    public static List<People> getPeoplesByUrl(List<String> urlList, List<People> allPeoples) {
        List<People> filteredPeoples = new ArrayList<>();
        log.info("gating people by urls ");
        for (String url :
                urlList) {
            log.info("gating peale by url " + url);
            Optional<People> people = allPeoples.stream().filter(p -> p.getUrl().equals(url)).findFirst();
            people.ifPresent(filteredPeoples::add);
        }
        return filteredPeoples;
    }

    public static List<People> getPeoplesByFilmsCount(List<People> peopleList, int filmCount) {
        List<People> peoples = new ArrayList<>();
        log.info("gating people by urls ");
        while (peoples.isEmpty() && filmCount != 0) {
            List<People> list = new ArrayList<>();
            for (People p : peopleList) {
                if (p.getFilms().size() == filmCount) {
                    list.add(p);
                }
            }
            filmCount--;
            peoples = list;
        }
        return peoples;
    }


    public static People getTallestPerson(List<People> peoplesUrlList) {
        return peoplesUrlList.stream().max(Comparator.comparing(People::getHeight)).orElse(null);
    }


}

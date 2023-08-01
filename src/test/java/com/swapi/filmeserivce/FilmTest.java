package com.swapi.filmeserivce;

import com.swapi.BaseTest;
import com.swapi.helpares.FilmServiceHelper;
import com.swapi.helpares.PeopleServiceHelper;
import com.swapi.sevices.models.Film;
import com.swapi.sevices.models.People;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;


public class FilmTest extends BaseTest {

    @Test
    public void finedTallestPerson() {
        SoftAssert softAssert = new SoftAssert();
        Film latestFilm = FilmServiceHelper.getLatestFilm();

        List<People> allPeoples = PeopleServiceHelper.getAllPeoples();
        List<People> usersByUrl = PeopleServiceHelper.getPeoplesByUrl(latestFilm.getCharacters(),allPeoples);
        softAssert.assertNotNull(usersByUrl, "Cant fined peoples by " + latestFilm.getCharacters().toString() + "Characters url");

        People tallestPerson = PeopleServiceHelper.getTallestPerson(usersByUrl);

        softAssert.assertNotNull(tallestPerson.getHeight(), " person height cant be null");

        softAssert.assertNotNull(tallestPerson.getVehicles(), " person vehicle list is null");

        List<People> peoplesByFilmsCount = PeopleServiceHelper.getPeoplesByFilmsCount(allPeoples, FilmServiceHelper.getFilmsList().getCount());

        People tallestPerson1 = PeopleServiceHelper.getTallestPerson(peoplesByFilmsCount);

        softAssert.assertNotNull(tallestPerson1,"can't fined user");
        softAssert.assertAll();
    }
}

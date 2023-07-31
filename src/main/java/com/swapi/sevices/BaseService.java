package com.swapi.sevices;

import com.swapi.allure.AllureLogger;

import com.swapi.allure.Logger;
import io.restassured.RestAssured;
import io.restassured.config.ObjectMapperConfig;
import io.restassured.http.ContentType;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.SneakyThrows;

import static com.swapi.config.Configuration.API_DOMAIN_URL;
import static io.restassured.RestAssured.given;
import static io.restassured.config.EncoderConfig.encoderConfig;

public class BaseService {



    @SneakyThrows
    protected static Response get(RequestSpecification requestSpecification) {
        requestSpecification.log().ifValidationFails();
        return requestSpecification.get();
    }

    @SneakyThrows
    protected static Response post(RequestSpecification requestSpecification) {
        requestSpecification.log().ifValidationFails();
        return requestSpecification.post();
    }

    @SneakyThrows
    protected static Response put(RequestSpecification requestSpecification) {
        requestSpecification.log().ifValidationFails();
        return requestSpecification.put();
    }

    @SneakyThrows
    protected static Response patch(RequestSpecification requestSpecification) {
        requestSpecification.log().ifValidationFails();
        return requestSpecification.patch();
    }


    @SneakyThrows
    protected static Response delete(RequestSpecification requestSpecification) {
        requestSpecification.log().ifValidationFails();
        return requestSpecification.delete();
    }

    @SneakyThrows
    private static RequestSpecification baseConfigRequest() {

     return given()
                .config(RestAssured.config().encoderConfig(encoderConfig().appendDefaultContentCharsetToContentTypeIfUndefined(false))
                        .objectMapperConfig(new ObjectMapperConfig(ObjectMapperType.GSON)))
                .filters(new Logger(),new AllureLogger())
                .urlEncodingEnabled(false);


    }


    @SneakyThrows
    protected static RequestSpecification configRequestAPI() {
        return baseConfigRequest().contentType(ContentType.JSON).baseUri(API_DOMAIN_URL).relaxedHTTPSValidation();
    }


}

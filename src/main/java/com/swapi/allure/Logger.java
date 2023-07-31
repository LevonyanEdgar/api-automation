package com.swapi.allure;

import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class Logger implements Filter {

    public static void setRequest(FilterableRequestSpecification request) {
        StringBuilder logMessage = new StringBuilder();
        logMessage.append("\n=============================== REQUEST ===============================\n");
        logMessage.append(String.format("==> : request %s/%s ", request.getBaseUri(), request.getBasePath()));
        logMessage.append("==> : requestPathParams ").append(request.getPathParams());
        logMessage.append("==> : requestQueryParams ").append(request.getQueryParams());
        log.info(logMessage.toString());
    }

    @Override
    public Response filter(FilterableRequestSpecification requestSpec, FilterableResponseSpecification responseSpec, FilterContext filterContext) {
        Response response = filterContext.next(requestSpec, responseSpec);
        setRequest(requestSpec);
        return response;
    }

}

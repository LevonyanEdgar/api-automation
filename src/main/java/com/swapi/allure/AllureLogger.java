package com.swapi.allure;


import com.google.gson.Gson;
import io.qameta.allure.attachment.DefaultAttachmentProcessor;
import io.qameta.allure.attachment.FreemarkerAttachmentRenderer;
import io.qameta.allure.attachment.http.HttpRequestAttachment;
import io.qameta.allure.attachment.http.HttpResponseAttachment;
import io.restassured.filter.FilterContext;
import io.restassured.filter.OrderedFilter;
import io.restassured.internal.NameAndValue;
import io.restassured.internal.support.Prettifier;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static io.qameta.allure.attachment.http.HttpRequestAttachment.Builder.create;

public class AllureLogger implements OrderedFilter {

    private final String REQUEST_TEMPLATE_PATH = "http-request.ftl";
    private final String RESPONSE_TEMPLATE_PATH = "http-response.ftl";

    @Override
    public Response filter(final FilterableRequestSpecification requestSpec,
                           final FilterableResponseSpecification responseSpec,
                           final FilterContext filterContext) {

        final HttpRequestAttachment requestAttachment = createRequestAttachment(requestSpec);

        new DefaultAttachmentProcessor().addAttachment(
                requestAttachment,
                new FreemarkerAttachmentRenderer(REQUEST_TEMPLATE_PATH)
        );

        final Response response = filterContext.next(requestSpec, responseSpec);
        final HttpResponseAttachment responseAttachment = createResponseAttachment(response);

        new DefaultAttachmentProcessor().addAttachment(
                responseAttachment,
                new FreemarkerAttachmentRenderer(RESPONSE_TEMPLATE_PATH)
        );

        return response;
    }


    private static Map<String, String> toMapConverter(final Iterable<? extends NameAndValue> items) {
        final Map<String, String> result = new HashMap<>();
        items.forEach(h -> result.put(h.getName(), h.getValue()));
        return result;
    }

    protected static HttpResponseAttachment createResponseAttachment(Response response){
        return HttpResponseAttachment.Builder.create(response.getStatusLine())
                .setResponseCode(response.getStatusCode())
                .setHeaders(toMapConverter(response.getHeaders()))
                .setBody(new Prettifier().getPrettifiedBodyIfPossible(response, response.getBody()))
                .build();
    }


    protected static HttpRequestAttachment createRequestAttachment(FilterableRequestSpecification requestSpec) {

        final HttpRequestAttachment.Builder requestAttachmentBuilder = create("Request: " + requestSpec.getMethod() + ": " + requestSpec.getURI(), requestSpec.getURI())
                .setMethod(requestSpec.getMethod())
                .setHeaders(toMapConverter(requestSpec.getHeaders()))
                .setCookies(toMapConverter(requestSpec.getCookies()));

        if (!requestSpec.getFormParams().isEmpty())
            requestAttachmentBuilder.setBody(new Gson().toJsonTree(requestSpec.getFormParams()).toString());

        if (Objects.nonNull(requestSpec.getBody())) {
            requestAttachmentBuilder.setBody(new Prettifier().getPrettifiedBodyIfPossible(requestSpec));
        }

        return requestAttachmentBuilder.build();
    }

    @Override
    public int getOrder() {
        return Integer.MAX_VALUE;
    }
}
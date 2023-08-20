package ru.praktikum.scooter.order;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import ru.praktikum.scooter.models.CreateOrder;

import static io.restassured.RestAssured.given;

public class ActionsWithOrder {
    private static final String BASE_URI = "https://qa-scooter.praktikum-services.ru";
    private static final String CREATE_URL = "/api/v1/orders";
    private static final String GET_LIST_OF_ORDERS_BY_NUMBER_URL = "/api/v1/orders/track";


    public ActionsWithOrder() {
        RestAssured.baseURI = BASE_URI;
    }

    public Response create(CreateOrder createOrder) {
        return given()
                .header("Content-type", "application/json")
                .and()
                .body(createOrder)
                .when()
                .post(CREATE_URL);
    }

    public Response getListOfOrdersByNumber(int track) {
        return given()
                .queryParam("t", track)
                .when()
                .get(GET_LIST_OF_ORDERS_BY_NUMBER_URL);
    }
}

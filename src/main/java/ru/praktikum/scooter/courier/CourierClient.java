package ru.praktikum.scooter.courier;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import ru.praktikum.scooter.models.CourierCreds;
import ru.praktikum.scooter.models.CreateCourier;

import static io.restassured.RestAssured.given;

public class CourierClient {
    private static final String BASE_URI = "https://qa-scooter.praktikum-services.ru";
    private static final String CREATE_URL = "api/v1/courier";
    private static final String LOGIN_URL = "api/v1/courier/login";
    private static final String DELETE_URL = "api/v1/courier/{:id}";

    public CourierClient() {
        RestAssured.baseURI = BASE_URI;
    }

    public Response create(CreateCourier courier) {
        return given()
                .header("Content-type", "application/json")
                .and()
                .body(courier)
                .when()
                .post(CREATE_URL);
    }

    public Response login(CourierCreds creds) {
        return given()
                .header("Content-type", "application/json")
                .and()
                .body(creds)
                .when()
                .post(LOGIN_URL);

    }

    public void delete(int id) {
        given()
                .delete(DELETE_URL, id);
    }
}

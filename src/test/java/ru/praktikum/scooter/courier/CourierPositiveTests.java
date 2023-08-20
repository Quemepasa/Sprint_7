package ru.praktikum.scooter.courier;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.praktikum.scooter.models.CourierId;
import ru.praktikum.scooter.models.CreateCourier;

import static org.apache.http.HttpStatus.SC_CREATED;
import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertEquals;
import static ru.praktikum.scooter.courier.CourierGenerator.courierWithoutFirstname;
import static ru.praktikum.scooter.courier.CourierGenerator.randomCourier;
import static ru.praktikum.scooter.models.CourierCreds.credsFrom;

public class CourierPositiveTests {
    private CourierClient courierClient;
    private Response loginResponse;
    @Before
    public void setUp() {
        courierClient = new CourierClient();
    }

    @DisplayName("Create courier with all fields and authorize")
    @Test
    public void createCourierWithAllFieldsAndAuthorize() {
        CreateCourier courier = randomCourier();

        Response createResponse = courierClient.create(courier);

        assertEquals("Неверный статус код", SC_CREATED, createResponse.statusCode());
        createResponse.then().assertThat().body("ok", equalTo(true));

        loginResponse = courierClient.login(credsFrom(courier));

        assertEquals("Курьер не залогинен", SC_OK, loginResponse.statusCode());
        loginResponse.then().assertThat().body("id", notNullValue());
    }

    @DisplayName("Create courier without firstname field and authorize")
    @Test
    public void createCourierWithoutFirstnameAndAuthorize() {
        CreateCourier courier = courierWithoutFirstname();

        Response createResponse = courierClient.create(courier);

        assertEquals("Неверный статус код", SC_CREATED, createResponse.statusCode());
        createResponse.then().assertThat().body("ok", equalTo(true));

        loginResponse = courierClient.login(credsFrom(courier));

        assertEquals("Курьер не залогинен", SC_OK, loginResponse.statusCode());
        loginResponse.then().assertThat().body("id", notNullValue());
    }

    @After
    public void tearDown() {
        CourierId courierId = loginResponse.body().as(CourierId.class);
        courierClient.delete(courierId.getId());
    }
}

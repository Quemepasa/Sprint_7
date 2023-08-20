package ru.praktikum.scooter.courier;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.praktikum.scooter.models.CourierId;
import ru.praktikum.scooter.models.CreateCourier;

import static org.apache.http.HttpStatus.*;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertEquals;
import static ru.praktikum.scooter.courier.CourierGenerator.*;
import static ru.praktikum.scooter.models.CourierCreds.*;

public class CourierNegativeWithTearDownTests {
    private CourierClient courierClient;
    private Response loginResponse;
    @Before
    public void setUp() {
        courierClient = new CourierClient();
    }

    @DisplayName("Create courier with the same login")
    @Test
    public void createCourierWithTheSameLogin() {
        CreateCourier courier = randomCourier();

        courierClient.create(courier);

        // To get the id to delete the courier after the test
        loginResponse = courierClient.login(credsFrom(courier));

        Response createResponse = courierClient.create(courier);

        assertEquals("Неверный статус код", SC_CONFLICT, createResponse.statusCode());
        createResponse.then().assertThat().body("message", equalTo("Этот логин уже используется"));
    }

    @DisplayName("Authorize with non-existent login")
    @Test
    public void authorizeWithNonExistentLogin() {
        CreateCourier courier = randomCourier();

        courierClient.create(courier);

        Response response = courierClient.login(randomNonExistentLogin(courier));

        assertEquals("Неверный статус код", SC_NOT_FOUND, response.statusCode());
        response.then().assertThat().body("message", equalTo("Учетная запись не найдена"));

        // To get the id to delete the courier after the test
        loginResponse = courierClient.login(credsFrom(courier));
    }

    @DisplayName("Authorize with non-existent password")
    @Test
    public void authorizeWithNonExistentPassword() {
        CreateCourier courier = randomCourier();

        courierClient.create(courier);

        Response response = courierClient.login(randomNonExistentPassword(courier));

        assertEquals("Неверный статус код", SC_NOT_FOUND, response.statusCode());
        response.then().assertThat().body("message", equalTo("Учетная запись не найдена"));

        // To get the id to delete the courier after the test
        loginResponse = courierClient.login(credsFrom(courier));
    }

    @After
    public void tearDown() {
        CourierId courierId = loginResponse.body().as(CourierId.class);
        courierClient.delete(courierId.getId());
    }
}
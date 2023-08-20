package ru.praktikum.scooter.courier;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;
import ru.praktikum.scooter.models.CreateCourier;

import static org.apache.http.HttpStatus.SC_BAD_REQUEST;
import static org.apache.http.HttpStatus.SC_NOT_FOUND;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertEquals;
import static ru.praktikum.scooter.courier.CourierGenerator.*;
import static ru.praktikum.scooter.models.CourierCreds.credsFrom;
import static ru.praktikum.scooter.models.CourierCreds.randomNonExistentCreds;

public class CourierNegativeTests {
    private CourierClient courierClient;

    @Before
    public void setUp() {
        courierClient = new CourierClient();
    }

    @DisplayName("Create courier without login field")
    @Test
    public void createCourierWithoutLoginField() {
        CreateCourier courier = courierWithoutLogin();

        Response createResponse = courierClient.create(courier);

        assertEquals("Неверный статус код", SC_BAD_REQUEST, createResponse.statusCode());
        createResponse.then().assertThat().body("message", equalTo("Недостаточно данных для создания учетной записи"));
    }

    @DisplayName("Create courier without password field")
    @Test
    public void createCourierWithoutPasswordField() {
        CreateCourier courier = courierWithoutPassword();

        Response createResponse = courierClient.create(courier);

        assertEquals("Неверный статус код", SC_BAD_REQUEST, createResponse.statusCode());
        createResponse.then().assertThat().body("message", equalTo("Недостаточно данных для создания учетной записи"));
    }

    @DisplayName("Create courier without login and password fields")
    @Test
    public void createCourierWithoutLoginAndPasswordFields() {
        CreateCourier courier = courierWithoutLoginAndPassword();

        Response createResponse = courierClient.create(courier);

        assertEquals("Неверный статус код", SC_BAD_REQUEST, createResponse.statusCode());
        createResponse.then().assertThat().body("message", equalTo("Недостаточно данных для создания учетной записи"));
    }

    @DisplayName("Authorize without login field")
    @Test
    public void authorizeWithoutLoginField() {
        CreateCourier courier = courierWithoutLogin();

        Response loginResponse = courierClient.login(credsFrom(courier));

        assertEquals("Неверный статус код", SC_BAD_REQUEST, loginResponse.statusCode());
        loginResponse.then().assertThat().body("message", equalTo("Недостаточно данных для входа"));
    }

    @DisplayName("Authorize without password field")
    @Test
    public void authorizeWithoutPasswordField() {
        CreateCourier courier = courierWithoutPassword();

        Response loginResponse = courierClient.login(credsFrom(courier));

        assertEquals("Неверный статус код", SC_BAD_REQUEST, loginResponse.statusCode());
        loginResponse.then().assertThat().body("message", equalTo("Недостаточно данных для входа"));
    }

    @DisplayName("Authorize without login and password fields")
    @Test
    public void authorizeWithoutLoginAndPasswordFields() {
        CreateCourier courier = courierWithoutLoginAndPassword();

        Response loginResponse = courierClient.login(credsFrom(courier));

        assertEquals("Неверный статус код", SC_BAD_REQUEST, loginResponse.statusCode());
        loginResponse.then().assertThat().body("message", equalTo("Недостаточно данных для входа"));
    }

    @DisplayName("Authorize with non-existent creds")
    @Test
    public void authorizeWithNonExistentCreds() {
        Response loginResponse = courierClient.login(randomNonExistentCreds());

        assertEquals("Неверный статус код", SC_NOT_FOUND, loginResponse.statusCode());
        loginResponse.then().assertThat().body("message", equalTo("Учетная запись не найдена"));
    }
}

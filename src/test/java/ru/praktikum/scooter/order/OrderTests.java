package ru.praktikum.scooter.order;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.praktikum.scooter.models.CreateOrder;
import ru.praktikum.scooter.models.OrderTrack;
import ru.praktikum.scooter.order.ActionsWithOrder;

import static org.apache.http.HttpStatus.SC_CREATED;
import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class OrderTests {
    private static final String FIRST_NAME = "Naruto";
    private static final String LAST_NAME = "Uchiha";
    private static final String ADDRESS = "Konoha, 142 apt.";
    private static final int METRO_STATION = 4;
    private static final String PHONE = "+7 800 355 35 35";
    private static final int RENT_TIME = 5;
    private static final String DELIVERY_DATE = "2020-06-06";
    private static final String COMMENT = "Saske, come back to Konoha";
    private final String[] color;
    private ActionsWithOrder actionsWithOrder;

    public OrderTests(String[] color) {
        this.color = color;
    }

    @Parameterized.Parameters
    public static Object[][] getTestData() {
        return new Object[][] {
                { new String[]{"BLACK"} },
                { new String[]{"GREY" } },
                { new String[]{"BLACK", "GREY"} },
                { new String[]{} },
        };
    }

    @Before
    public void setUp() {
        actionsWithOrder = new ActionsWithOrder();
    }

    @DisplayName("Create order with different color and get list of orders")
    @Test
    public void createOrderWithDifferentColorAndGetListOfOrders() {
        CreateOrder order = new CreateOrder(FIRST_NAME, LAST_NAME, ADDRESS, METRO_STATION, PHONE, RENT_TIME, DELIVERY_DATE,
                COMMENT, color);

        Response createResponse = actionsWithOrder.create(order);

        assertEquals("Неверный статус код", SC_CREATED, createResponse.statusCode());
        createResponse.then().assertThat().body("track", notNullValue());

        OrderTrack orderTrack = createResponse.body().as(OrderTrack.class);
        Response getListOfOrdersResponse = actionsWithOrder.getListOfOrdersByNumber(orderTrack.getTrack());

        assertEquals("Неверный статус код", SC_OK, getListOfOrdersResponse.statusCode());
        getListOfOrdersResponse.then().assertThat().body("order", notNullValue());
    }
}

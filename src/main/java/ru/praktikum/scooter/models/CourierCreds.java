package ru.praktikum.scooter.models;

import static ru.praktikum.scooter.utils.Utils.*;

public class CourierCreds {
    private String login;
    private String password;

    public CourierCreds(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public static CourierCreds credsFrom(CreateCourier courier) {
        return new CourierCreds(courier.getLogin(), courier.getPassword());
    }

    public static CourierCreds randomNonExistentLogin(CreateCourier courier) {
        return new CourierCreds(randomString(12), courier.getPassword());
    }

    public static CourierCreds randomNonExistentPassword(CreateCourier courier) {
        return new CourierCreds(courier.getLogin(), randomString(12));
    }

    public static CourierCreds randomNonExistentCreds() {
        return new CourierCreds(randomString(12), randomString(12));
    }
}

package ru.praktikum.scooter.courier;

import ru.praktikum.scooter.models.CreateCourier;

import static ru.praktikum.scooter.utils.Utils.randomString;

public class CourierGenerator {
    public static CreateCourier randomCourier() {
        return new CreateCourier()
                .withLogin(randomString(8))
                .withPassword(randomString(12))
                .withFirstName(randomString(10));
    }

    public static CreateCourier courierWithoutFirstname() {
        return new CreateCourier()
                .withLogin(randomString(8))
                .withPassword(randomString(12));
    }

    public static CreateCourier courierWithoutLogin() {
        return new CreateCourier()
                .withPassword(randomString(12))
                .withFirstName(randomString(10));
    }

    public static CreateCourier courierWithoutPassword() {
        return new CreateCourier()
                .withLogin(randomString(8))
                .withFirstName(randomString(10));
    }

    public static CreateCourier courierWithoutLoginAndPassword() {
        return new CreateCourier()
                .withFirstName(randomString(10));
    }
}

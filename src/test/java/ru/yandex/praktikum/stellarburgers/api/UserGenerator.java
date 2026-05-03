package ru.yandex.praktikum.stellarburgers.api;

import com.github.javafaker.Faker;
import java.util.Locale;

public class UserGenerator {
    private static final Faker faker = new Faker(new Locale("en"));

    public static User getRandomUser() {
        return User.builder()
                .email(faker.internet().emailAddress())
                .password(faker.internet().password(6, 12))
                .name(faker.name().firstName())
                .build();
    }

    public static User getUserWithShortPassword() {
        return User.builder()
                .email(faker.internet().emailAddress())
                .password(faker.internet().password(1, 5))
                .name(faker.name().firstName())
                .build();
    }
}
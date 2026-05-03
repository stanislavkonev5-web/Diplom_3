package ru.yandex.praktikum.stellarburgers.api;

import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import ru.yandex.praktikum.stellarburgers.config.ApiEndpoints;

import static io.restassured.RestAssured.given;

public class UserClient {

    @Step("Создание пользователя")
    public ValidatableResponse create(User user) {
        return given()
                .log().all()
                .contentType(ContentType.JSON)
                .header("Content-type", "application/json")
                .baseUri(ApiEndpoints.API_BASE_URL)
                .body(user)
                .when()
                .post(ApiEndpoints.USER_CREATE)
                .then();
    }

    @Step("Логин пользователя")
    public ValidatableResponse login(User user) {
        return given()
                .log().all()
                .contentType(ContentType.JSON)
                .baseUri(ApiEndpoints.API_BASE_URL)
                .body(user)
                .when()
                .post(ApiEndpoints.USER_LOGIN)
                .then();
    }

    @Step("Удаление пользователя")
    public ValidatableResponse delete(String accessToken) {
        return given()
                .log().all()
                .contentType(ContentType.JSON)
                .header("Authorization", accessToken) // Токен нужен для удаления
                .baseUri(ApiEndpoints.API_BASE_URL)
                .when()
                .delete(ApiEndpoints.USER_ACTION)
                .then();
    }
}
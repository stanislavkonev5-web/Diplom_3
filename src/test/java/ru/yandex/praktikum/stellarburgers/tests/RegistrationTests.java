package ru.yandex.praktikum.stellarburgers.tests;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import ru.yandex.praktikum.stellarburgers.BrowserRule;
import ru.yandex.praktikum.stellarburgers.api.User;
import ru.yandex.praktikum.stellarburgers.api.UserClient;
import ru.yandex.praktikum.stellarburgers.api.UserGenerator;
import ru.yandex.praktikum.stellarburgers.config.Urls;
import ru.yandex.praktikum.stellarburgers.pageobject.LoginPage;
import ru.yandex.praktikum.stellarburgers.pageobject.RegisterPage;

import static org.junit.Assert.assertTrue;

public class RegistrationTests {

    @Rule
    public BrowserRule browserRule = new BrowserRule();

    private UserClient userClient;
    private User user;

    @Before
    public void setUp() {

        userClient = new UserClient();
    }

    @Test
    @DisplayName("Успешная регистрация")
    @Description("Переход на страницу логина после заполнения валидных данных")
    public void successRegistrationTest() {
        user = UserGenerator.getRandomUser();
        RegisterPage registerPage = new RegisterPage(browserRule.getDriver());

        browserRule.getDriver().get(Urls.REGISTER_PAGE);
        registerPage.register(user.getName(), user.getEmail(), user.getPassword());

        LoginPage loginPage = new LoginPage(browserRule.getDriver());
        assertTrue("Страница логина не открылась после регистрации", loginPage.isLoginPageOpened());
    }

    @Test
    @DisplayName("Ошибка регистрации при некорректном пароле")
    @Description("Появление сообщения об ошибке для пароля менее 6 символов")
    public void errorRegistrationWithShortPasswordTest() {
        user = UserGenerator.getUserWithShortPassword();
        RegisterPage registerPage = new RegisterPage(browserRule.getDriver());

        browserRule.getDriver().get(Urls.REGISTER_PAGE);
        registerPage.register(user.getName(), user.getEmail(), user.getPassword());

        assertTrue("Сообщение об ошибке пароля не отображается", registerPage.isPasswordErrorDisplayed());
    }

    @After
    public void tearDown() {
        if (user != null) {

            ValidatableResponse loginResponse = userClient.login(user);

            if (loginResponse.extract().statusCode() == 200) {
                String accessToken = loginResponse.extract().path("accessToken");
                if (accessToken != null) {
                    userClient.delete(accessToken);
                }
            }
        }
    }
}

package ru.yandex.praktikum.stellarburgers.tests;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import ru.yandex.praktikum.stellarburgers.BrowserRule;
import ru.yandex.praktikum.stellarburgers.api.User;
import ru.yandex.praktikum.stellarburgers.api.UserClient;
import ru.yandex.praktikum.stellarburgers.api.UserGenerator;
import ru.yandex.praktikum.stellarburgers.config.Urls;
import ru.yandex.praktikum.stellarburgers.pageobject.ForgotPasswordPage;
import ru.yandex.praktikum.stellarburgers.pageobject.LoginPage;
import ru.yandex.praktikum.stellarburgers.pageobject.MainPage;
import ru.yandex.praktikum.stellarburgers.pageobject.RegisterPage;

import static org.junit.Assert.assertTrue;

public class LoginTests {

    @Rule
    public BrowserRule browserRule = new BrowserRule();

    private UserClient userClient;
    private User user;
    private String accessToken;

    @Before
    public void setUp() {
        userClient = new UserClient();
        user = UserGenerator.getRandomUser();
        accessToken = userClient.create(user).extract().path("accessToken");
    }

    @Test
    @DisplayName("Вход по кнопке «Войти в аккаунт» на главной странице")
    @Description("Успешный логин при использовании основной кнопки входа на главной странице")
    public void loginFromMainPageButtonTest() {
        MainPage mainPage = new MainPage(browserRule.getDriver());
        browserRule.getDriver().get(Urls.BASE_URL);

        mainPage.clickLoginAccountButton();

        LoginPage loginPage = new LoginPage(browserRule.getDriver());
        loginPage.login(user.getEmail(), user.getPassword());

        loginPage.waitForUrl(Urls.BASE_URL);
        assertTrue("Вход не выполнен: URL не совпадает с базовым",
                browserRule.getDriver().getCurrentUrl().equals(Urls.BASE_URL));
    }

    @Test
    @DisplayName("Вход через кнопку «Личный кабинет»")
    @Description("Переход на форму логина и успешный вход через кнопку Личного кабинета")
    public void loginFromPersonalAccountButtonTest() {
        MainPage mainPage = new MainPage(browserRule.getDriver());
        browserRule.getDriver().get(Urls.BASE_URL);

        mainPage.clickPersonalAccountButton();

        LoginPage loginPage = new LoginPage(browserRule.getDriver());
        loginPage.login(user.getEmail(), user.getPassword());

        loginPage.waitForUrl(Urls.BASE_URL);
        assertTrue("Не удалось зайти в Личный кабинет",
                browserRule.getDriver().getCurrentUrl().equals(Urls.BASE_URL));
    }

    @Test
    @DisplayName("Вход через ссылку в форме регистрации")
    @Description("Ссылка 'Войти' на странице регистрации ведет к успешной авторизации")
    public void loginFromRegisterFormTest() {
        browserRule.getDriver().get(Urls.REGISTER_PAGE);
        RegisterPage registerPage = new RegisterPage(browserRule.getDriver());

        registerPage.clickLoginLink();

        LoginPage loginPage = new LoginPage(browserRule.getDriver());
        loginPage.login(user.getEmail(), user.getPassword());

        loginPage.waitForUrl(Urls.BASE_URL);
        assertTrue("Не удалось войти через страницу регистрации",
                browserRule.getDriver().getCurrentUrl().equals(Urls.BASE_URL));
    }

    @Test
    @DisplayName("Вход через ссылку в форме восстановления пароля")
    @Description("Авторизация после перехода по ссылке 'Войти' со страницы восстановления пароля")
    public void loginFromForgotPasswordFormTest() {
        browserRule.getDriver().get(Urls.FORGOT_PASSWORD_PAGE);
        ForgotPasswordPage forgotPage = new ForgotPasswordPage(browserRule.getDriver());

        forgotPage.clickLoginLink();

        LoginPage loginPage = new LoginPage(browserRule.getDriver());
        loginPage.login(user.getEmail(), user.getPassword());

        loginPage.waitForUrl(Urls.BASE_URL);
        assertTrue("Не удалось войти через страницу восстановления пароля",
                browserRule.getDriver().getCurrentUrl().equals(Urls.BASE_URL));
    }

    @After
    public void tearDown() {
        if (accessToken != null) {
            userClient.delete(accessToken);
        }
    }
}
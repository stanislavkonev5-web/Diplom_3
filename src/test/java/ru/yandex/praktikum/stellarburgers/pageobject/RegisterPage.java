package ru.yandex.praktikum.stellarburgers.pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegisterPage extends BasePage {

    private final By nameField = By.xpath(".//label[text()='Имя']/following-sibling::input");
    private final By emailField = By.xpath(".//label[text()='Email']/following-sibling::input");
    private final By passwordField = By.xpath(".//label[text()='Пароль']/following-sibling::input");

    private final By registerButton = By.xpath(".//button[text()='Зарегистрироваться']");

    private final By loginLink = By.xpath(".//a[@href='/login']");

    private final By passwordError = By.xpath(".//p[contains(@class, 'input__error') and text()='Некорректный пароль']");

    public RegisterPage(WebDriver driver) {
        super(driver);
    }
    @Step("Регистрация пользователя: имя {name}, email {email}")
    public void register(String name, String email, String password) {
        type(nameField, name);
        type(emailField, email);
        type(passwordField, password);
        click(registerButton);
    }

    @Step("Отображение ошибки пароля")
    public boolean isPasswordErrorDisplayed() {
        return isDisplayed(passwordError); // этот метод мы добавим в BasePage для удобства
    }

    @Step("Клик по ссылке 'Войти' на странице регистрации")
    public void clickLoginLink() {
        click(loginLink);
    }
}

package ru.yandex.praktikum.stellarburgers.pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ForgotPasswordPage extends BasePage {

    private final By loginLink = By.xpath(".//a[text()='Войти' and @class='Auth_link__1fOlj']");

    public ForgotPasswordPage(WebDriver driver) {
        super(driver);
    }

    @Step("Клик по ссылке 'Войти' на странице восстановления пароля")
    public void clickLoginLink() {
        click(loginLink);
    }

    @Step("Открытие страницы восстановления пароля")
    public boolean isForgotPasswordPageOpened() {
        return isDisplayed(By.xpath(".//h2[text()='Восстановление пароля']"));
    }
}

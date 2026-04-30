package ru.yandex.praktikum.stellarburgers.pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ru.yandex.praktikum.stellarburgers.config.Urls;

public class MainPage extends BasePage {

    private final By loginAccountButton = By.xpath(".//button[text()='Войти в аккаунт']");
    private final By personalAccountButton = By.xpath(".//a[@href='/account']");

    private final By bunsTab = By.xpath(".//span[text()='Булки']/parent::div");
    private final By saucesTab = By.xpath(".//span[text()='Соусы']/parent::div");
    private final By fillingsTab = By.xpath(".//span[text()='Начинки']/parent::div");

    public MainPage(WebDriver driver) {

        super(driver);
    }

    @Step("Ожидание загрузки главной страницы")
    public void waitForLoadMainPage() {
        waitForUrl(Urls.BASE_URL);
        waitForVisibility(By.xpath(".//h1[text()='Соберите бургер']"));
    }

    @Step("Клик по кнопке 'Войти в аккаунт' на главной странице")
    public void clickLoginAccountButton() {
        click(loginAccountButton);
    }

    @Step("Клик по кнопке 'Личный кабинет'")
    public void clickPersonalAccountButton() {
        click(personalAccountButton);
    }

    @Step("Переход к разделу 'Булки'")
    public void clickBunsTab() {

        jsClick(bunsTab);
    }

    @Step("Переход к разделу 'Соусы'")
    public void clickSaucesTab() {
        jsClick(saucesTab);
    }

    @Step("Переход к разделу 'Начинки'")
    public void clickFillingsTab() {
        jsClick(fillingsTab);
    }

    @Step("Проверка, что раздел {tabName} активен")
    public boolean isTabActive(String tabName) {
        By tabLocator;
        switch (tabName) {
            case "Булки": tabLocator = bunsTab; break;
            case "Соусы": tabLocator = saucesTab; break;
            case "Начинки": tabLocator = fillingsTab; break;
            default: return false;
        }
        return wait.until(ExpectedConditions.attributeContains(tabLocator, "class", "tab_tab_type_current"));
    }
}

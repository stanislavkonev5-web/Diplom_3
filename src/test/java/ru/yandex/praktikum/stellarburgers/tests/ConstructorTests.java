package ru.yandex.praktikum.stellarburgers.tests;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import ru.yandex.praktikum.stellarburgers.BrowserRule;
import ru.yandex.praktikum.stellarburgers.config.Urls;
import ru.yandex.praktikum.stellarburgers.pageobject.MainPage;


import static org.junit.Assert.assertTrue;

public class ConstructorTests {

    @Rule
    public BrowserRule browserRule = new BrowserRule();

    private MainPage mainPage;

    @Before
    public void setUp() {
        browserRule.getDriver().get(Urls.BASE_URL);
        mainPage = new MainPage(browserRule.getDriver());
        mainPage.waitForLoadMainPage();
    }

    @Test
    @DisplayName("Переход к разделу «Соусы»")
    @Description("При клике на 'Соусы' раздел становится активным")
    public void goToSaucesSectionTest() {
        mainPage.clickSaucesTab();
        assertTrue("Раздел «Соусы» не стал активным", mainPage.isTabActive("Соусы"));
    }

    @Test
    @DisplayName("Переход к разделу «Начинки»")
    @Description("При клике на 'Начинки' раздел становится активным")
    public void goToFillingsSectionTest() {
        mainPage.clickFillingsTab();
        assertTrue("Раздел «Начинки» не стал активным", mainPage.isTabActive("Начинки"));
    }

    @Test
    @DisplayName("Переход к разделу «Булки»")
    @Description("После перехода в другой раздел можно вернуться к 'Булкам'")
    public void goToBunsSectionTest() {
        mainPage.clickSaucesTab();
        mainPage.isTabActive("Соусы"); // Ждем, что ушли с булок

        mainPage.clickBunsTab();
        assertTrue("Раздел «Булки» не стал активным", mainPage.isTabActive("Булки"));
    }
}
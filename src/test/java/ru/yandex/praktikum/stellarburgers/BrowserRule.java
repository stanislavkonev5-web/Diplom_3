package ru.yandex.praktikum.stellarburgers;

import org.junit.rules.ExternalResource;
import org.openqa.selenium.WebDriver;
import java.time.Duration;

public class BrowserRule extends ExternalResource {
    private WebDriver driver;

    @Override
    protected void before() {
        driver = WebDriverFactory.getDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    @Override
    protected void after() {
        try {
            if (driver != null) {
                driver.quit();
            }
        } catch (Exception e) {

        } finally {
            driver = null;
        }
    }

    public WebDriver getDriver() {

        return driver;
    }
}

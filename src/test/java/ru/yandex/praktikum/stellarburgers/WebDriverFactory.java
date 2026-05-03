package ru.yandex.praktikum.stellarburgers;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class WebDriverFactory {

    public static WebDriver getDriver() {
        String browser = System.getProperty("browser", "chrome").toLowerCase();
        WebDriver driver;
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--remote-allow-origins=*");

        options.addArguments("--disable-blink-features=AutomationControlled");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");

        switch (browser) {
            case "yandex":
                WebDriverManager.chromedriver().browserVersion("144").setup();

                String yandexBinary = System.getProperty("yandex.binary");
                if (yandexBinary == null) {
                    String localAppData = System.getenv("LOCALAPPDATA");
                    if (localAppData != null) {
                        yandexBinary = localAppData + "\\Yandex\\YandexBrowser\\Application\\browser.exe";
                    }
                }
                if (yandexBinary != null) {
                    options.setBinary(yandexBinary);
                }
                driver = new ChromeDriver(options);
                break;

            case "chrome":
            default:
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver(options);
                break;
        }
        return driver;
    }
}



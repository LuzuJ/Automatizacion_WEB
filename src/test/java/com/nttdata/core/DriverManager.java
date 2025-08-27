package com.nttdata.core;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class DriverManager {
    private static WebDriver driver;
    private static Scenario scenario;

    public static WebDriver getDriver() {
        return driver;
    }

    @Before(order = 0)
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "drivers\\chromedriver.exe");

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--disable-extensions");
        options.addArguments("--disable-popup-blocking");

        driver = new ChromeDriver(options);

        driver.manage().deleteAllCookies();
    }

    @Before(order = 1)
    public void setScenario(Scenario scenario) {
        DriverManager.scenario = scenario;
    }

    @After
    public void quitDriver() {
        if (driver != null) {
            driver.quit();
        }
    }

    public static void takeScreenshot() {
        if (driver != null && scenario != null) {
            byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", "evidencia");
        }
    }
}

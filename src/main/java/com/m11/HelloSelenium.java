package com.m11;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class HelloSelenium {

    public static void main(String[] args) throws InterruptedException {
        System.setProperty("webdriver.gecko.driver", "src/main/resources/WebDrivers/geckodriver");
        WebDriver driver = new FirefoxDriver();
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10).toSeconds());
//        WebDriver.Timeouts wait1 = driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        try {
            driver.get("https://twitter.com/");

            driver.findElement(By.xpath("//*[@id=\"react-root\"]/div/div/div[2]/header/div[2]/div[1]/div/div[2]/div[1]/div[1]/a")).click();

            Thread.sleep(10000L);

            //TODO: make login
        } finally {
            driver.quit();
        }
    }
}

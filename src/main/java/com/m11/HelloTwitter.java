package com.m11;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class HelloTwitter {

    /**
     * Make Twitter login and send tweet "Hello Twitter!"
     * @param args - args[0] - login, args[1] - password
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        System.setProperty("webdriver.gecko.driver", "src/main/resources/WebDrivers/geckodriver");
        WebDriver driver = new FirefoxDriver();

        try {
            driver.get("https://twitter.com/");

            //Click login
            driver.findElement(By.xpath("//*[@id=\"react-root\"]/div/div/div[2]/header/div[2]/div[1]/div/div[2]/div[1]/div[1]/a")).click();

            //Write login
            driver.findElement(By.xpath("//*[@id=\"react-root\"]/div/div/div[2]/main/div/div/form/div/div[1]/label/div/div[2]/div/input"))
                    .sendKeys(args[0]);

            //Write password
            driver.findElement(By.xpath("/html/body/div/div/div/div[2]/main/div/div/form/div/div[2]/label/div/div[2]/div/input"))
                    .sendKeys(args[1]);

            //click Login button
            driver.findElement(By.xpath("/html/body/div/div/div/div[2]/main/div/div/form/div/div[3]/div")).click();

            //click Tweet button
            driver.findElement(By.xpath("/html/body/div/div/div/div[2]/header/div/div/div/div[1]/div[3]/a")).click();

            //Write tweet
            driver.findElement(By.xpath("/html/body/div/div/div/div[1]/div[2]/div/div/div/div[2]/div[2]/div/div[3]/div/div/div/div[1]/div/div/div/div/div[2]/div[1]/div/div/div/div/div/div/div/div/div/div[1]/div/div/div/div[2]/div"))
                    .sendKeys("Hello Twitter!");

            Thread.sleep(1000L);
            //Click tweet button
            driver.findElement(By.xpath("//*[@id=\"react-root\"]/div/div/div[1]/div[2]/div/div/div/div[2]/div[2]/div/div[3]/div/div/div/div[1]/div/div/div/div/div[2]/div[2]/div/div/div[2]/div[4]")).click();

            Thread.sleep(10000L);

        } finally {
            driver.quit();
        }
    }
}

package com.m11;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.Augmenter;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Github {

    /**
     * Make screenshot of the github's mosaic"
     */
    public static void main(String[] args) throws InterruptedException {
        System.setProperty("webdriver.gecko.driver", "src/main/resources/WebDrivers/geckodriver");
        WebDriver driver = new FirefoxDriver();

        try {
            driver.get("https://github.com/mshemanskyi");

            //mosaic element
            WebElement mosaic = driver.findElement(By.xpath("//*[@id=\"js-pjax-container\"]/div[2]/div/div[2]/div[2]/div/div[2]/div/div[1]/div[1]/div[1]/div"));

            String path;
            try {
                WebDriver augmentedDriver = new Augmenter().augment(driver);
                File source = ((TakesScreenshot)augmentedDriver).getScreenshotAs(OutputType.FILE);

                Rectangle rectangle = new Rectangle(mosaic.getSize().width, mosaic.getSize().height, mosaic.getSize().height, mosaic.getSize().width);
                Point location = mosaic.getLocation();
                BufferedImage bufferedImage = ImageIO.read(source);
                BufferedImage destImage = bufferedImage.getSubimage(location.x, location.y, rectangle.width, rectangle.height);
                ImageIO.write(destImage, "png", source);

                path = "src/main/resources/img/" + source.getName();
                FileUtils.copyFile(source, new File(path));
            }
            catch(IOException e) {
                System.out.println("Failed to capture screenshot: " + e.getMessage());
            }

            Thread.sleep(5000L);

        } finally {
            driver.quit();
        }
    }
}

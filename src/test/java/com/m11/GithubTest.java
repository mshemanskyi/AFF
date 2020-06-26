package com.m11;

import com.m11.util.OSCheck;
import com.m11.util.WebDriverUtil;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.Augmenter;
import org.testng.annotations.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static org.testng.Assert.assertNotNull;



public class GithubTest {

    @Test
    public void getMosaicImage() throws InterruptedException {
        System.out.println(OSCheck.isUnix());
        System.out.println(OSCheck.isMac());
        System.out.println(System.getProperty("os.name").toLowerCase());
        System.setProperty("webdriver.chrome.driver", WebDriverUtil.getWebDriverByOS());
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized"); // open Browser in maximized mode
        options.addArguments("disable-infobars"); // disabling infobars
        options.addArguments("--disable-extensions"); // disabling extensions
        options.addArguments("--disable-gpu"); // applicable to windows os only
        options.addArguments("--disable-dev-shm-usage"); // overcome limited resource problems
        options.addArguments("--no-sandbox"); // Bypass OS security model
        WebDriver driver = new ChromeDriver(options);

        try {
            driver.get("https://github.com/mshemanskyi");

            //mosaic element
            WebElement mosaic = driver.findElement(By.xpath("//*[@id=\"js-pjax-container\"]/div[2]/div/div[2]/div[2]/div/div[2]/div/div[1]/div[1]/div[1]/div"));

            String path = null;
            try {
                WebDriver augmentedDriver = new Augmenter().augment(driver);
                File source = ((TakesScreenshot) augmentedDriver).getScreenshotAs(OutputType.FILE);

                Rectangle rectangle = new Rectangle(mosaic.getSize().width, mosaic.getSize().height, mosaic.getSize().height, mosaic.getSize().width);
                Point location = mosaic.getLocation();
                BufferedImage bufferedImage = ImageIO.read(source);
                BufferedImage destImage = bufferedImage.getSubimage(location.x, location.y, rectangle.width, rectangle.height);
                ImageIO.write(destImage, "png", source);

                path = "src/main/resources/img/" + source.getName();
                FileUtils.copyFile(source, new File(path));
            } catch (IOException e) {
                System.out.println("Failed to capture screenshot: " + e.getMessage());
            }

            //Thread.sleep(5000L);
            assertNotNull(path);

        } finally {
            driver.quit();
        }
    }
}
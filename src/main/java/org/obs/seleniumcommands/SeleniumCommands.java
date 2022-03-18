package org.obs.seleniumcommands;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SeleniumCommands {
    WebDriver driver;
    public void testInitialize(String browser) {
        if (browser.equals("ChromeDriver")) {
            System.setProperty("webdriver.chrome.driver", "C:\\ChromeDriver\\chromedriver.exe");
            driver = new ChromeDriver();
        } else if (browser.equals("FirefoxDriver")) {
            System.setProperty("webdriver.gecko.driver", "C:\\Selenium_files\\geckodriver-v0.30.0-win64\\geckodriver.exe");
            driver = new FirefoxDriver();
        } else if (browser.equals("EdgeDriver")) {
            System.setProperty("webdriver.edge.driver", "C:\\Selenium_files\\edgedriver_win64\\msedgedriver.exe");
            driver = new EdgeDriver();
        } else {
            try {
                throw new Exception("invalid browsername");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
    }

    @BeforeMethod
    public void setUp(){
        testInitialize("ChromeDriver");
        driver.get("http://demowebshop.tricentis.com");
    }

    @AfterMethod
    public void tearDown(){
        driver.close();
    }

    @Test
    public void verifyHomepageTitle(){
        String actualTitle= driver.getTitle();
        String expectedTitle="Demo Web Shop";
        Assert.assertEquals(actualTitle,expectedTitle,"invalid Page Title");
    }


}


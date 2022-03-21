package org.obs.seleniumcommands;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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
        if (browser.equals("chrome")) {
            System.setProperty("webdriver.chrome.driver", "C:\\ChromeDriver\\chromedriver.exe");
            driver = new ChromeDriver();
        } else if (browser.equals("firefox")) {
            System.setProperty("webdriver.gecko.driver", "C:\\Selenium_files\\geckodriver-v0.30.0-win64\\geckodriver.exe");
            driver = new FirefoxDriver();
        } else if (browser.equals("edge")) {
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
        testInitialize("chrome");//driver.get("http://demowebshop.tricentis.com");
    }

    @AfterMethod
    public void tearDown(){
        driver.close();
    }

   @Test
    public void verifyHomepageTitle(){
        driver.get("http://demowebshop.tricentis.com");
        String actualTitle= driver.getTitle();
        String expectedTitle="Demo Web Shop";
        Assert.assertEquals(actualTitle,expectedTitle,"invalid Page Title");
    }

    @Test
    public void verifyLogin(){

        driver.get("http://demowebshop.tricentis.com");
        WebElement login=driver.findElement(By.cssSelector("li>a.ico-login"));
        login.click();
        WebElement email=driver.findElement(By.cssSelector("input#Email"));
        email.sendKeys("suryasomaraj94@gmail.com");
        WebElement password=driver.findElement(By.cssSelector("input.password"));
        password.sendKeys("qwerty@123");
        WebElement checkbox=driver.findElement(By.cssSelector("input[type='checkbox']"));
        checkbox.click();
        WebElement submit=driver.findElement(By.cssSelector("input[value='Log in']"));
        submit.click();
        WebElement account= driver.findElement(By.xpath("//div[@class='header-links']//a[@class='account']"));
        String actualemailID=account.getText();
        String expectedemailID="suryasomaraj94@gmail.com";
        Assert.assertEquals(actualemailID,expectedemailID,"User login Failed");
    }



}


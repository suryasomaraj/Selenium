package org.obs.seleniumcommands;

import obs.selenium.Utility;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Random;

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
    @Test
    public void verifyClear(){
        driver.get("http://demowebshop.tricentis.com");
        WebElement login=driver.findElement(By.cssSelector("a.ico-login"));
        login.click();
        WebElement email=driver.findElement(By.cssSelector("input#Email"));
        email.sendKeys("suryasomaraj94@gmail.com");
        WebElement password=driver.findElement(By.cssSelector("input.password"));
        password.sendKeys("qwerty@123");
        email.clear();
        WebElement submit=driver.findElement(By.cssSelector("input[value='Log in']"));
        submit.click();
        WebElement clear=driver.findElement(By.xpath("//div[@class='validation-summary-errors']/child::span"));
        String actualemail=clear.getText();
        String expectedemail="Login was unsuccessful. Please correct the errors and try again.";
        Assert.assertEquals(actualemail,expectedemail,"Invalid Error Message");
    }
    @Test
    public void verifyWebelementCommands(){
        driver.get("http://demowebshop.tricentis.com");
        WebElement login=driver.findElement(By.cssSelector("li>a.ico-login"));
        login.click();
        WebElement submit=driver.findElement(By.cssSelector("input[value='Log in']"));
        Dimension dimension =submit.getSize();
        int height=dimension.height;
        int width=dimension.width;
        System.out.println("height=" +height);
        System.out.println("width= "+width);
        Point point=submit.getLocation();
        int x=point.x;
        int y=point.y;
        System.out.println("X-coordinate= "+x);
        System.out.println("Y-coordinate= "+y);
        String actualloginbuttontext=submit.getAttribute("value");
        System.out.println("actual login button text= "+actualloginbuttontext);
        String expectedloginbuttontext="Log in";
        Assert.assertEquals(actualloginbuttontext,expectedloginbuttontext,"incorrect text in login button");
    }
    @Test
    public void searchButton(){
        driver.get("http://demowebshop.tricentis.com");
        WebElement search=driver.findElement(By.cssSelector("input[value='Search']"));
        Dimension dimension =search.getSize();
        int height=dimension.height;
        int width=dimension.width;
        System.out.println("height=" +height);
        System.out.println("width= "+width);
        Point point=search.getLocation();
        int x=point.x;
        int y=point.y;
        System.out.println("X-coordinate= "+x);
        System.out.println("Y-coordinate= "+y);
        String actualsearchbuttontext=search.getAttribute("value");
        System.out.println("actual Search button text= "+actualsearchbuttontext);
        String expectedsearchbuttontext="Search";
        Assert.assertEquals(actualsearchbuttontext,expectedsearchbuttontext,"incorrect text in search button");
    }
    @Test
    public void verifyRegistration() throws InterruptedException {
        driver.get("http://demowebshop.tricentis.com");
        WebElement register=driver.findElement(By.cssSelector("a.ico-register"));
        register.click();
        selectGender("Female");
        Thread.sleep(5000);
        WebElement name=driver.findElement(By.cssSelector("input[name='FirstName']"));
        name.sendKeys("surya");
        WebElement namel=driver.findElement(By.cssSelector("input#LastName"));
        namel.sendKeys("somaraj");
        Utility utility=new Utility();
        String mail=Utility.random();
        WebElement email=driver.findElement(By.cssSelector("input#Email"));
        email.sendKeys(mail);
        WebElement password=driver.findElement(By.cssSelector("input[name='Password']"));
        password.sendKeys("qwerty@123");
        WebElement cpassword=driver.findElement(By.cssSelector("div>input#ConfirmPassword"));
        cpassword.sendKeys("qwerty@123");
        WebElement registerbutton=driver.findElement(By.cssSelector("input[name='register-button']"));
        registerbutton.click();
    }
    public void selectGender(String gender){
        List<WebElement> radio=driver.findElements(By.xpath("//label[@class='forcheckbox']"));
        for(int i=0;i< radio.size();i++){
            if(radio.get(i).getText().equals("Female")) {
                radio.get(i).click();
            }
        }
    }
}


package org.obs.seleniumcommands;

import obs.selenium.Utility;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.swing.*;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;

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
       // Assert.assertEquals(actualloginbuttontext,expectedloginbuttontext,"incorrect text in login button");
        String tagname=submit.getTagName();
        System.out.println("Tagname= "+tagname);
        String cssproperty=submit.getCssValue("color");
        System.out.println("cssproperty= "+cssproperty);
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
        //a[text()='suryasomaraj13@gmail.com']
    }
    public void selectGender(String gender){
        List<WebElement> radio=driver.findElements(By.xpath("//label[@class='forcheckbox']"));
        for(int i=0;i< radio.size();i++){
            if(radio.get(i).getText().equals(gender)) {
                radio.get(i).click();
            }else{
                System.out.println("Invalid");
            }
        }
    }
    @Test
    public void verifyElementPresent(){
        driver.get("http://demowebshop.tricentis.com");
        WebElement login=driver.findElement(By.cssSelector("li>a.ico-login"));
        login.click();
        WebElement submit=driver.findElement(By.cssSelector("input[value='Log in']"));
        boolean result=submit.isDisplayed();
        System.out.println("Result="+result);
        Assert.assertTrue(result,"Submit button not displayed");
    }
    @Test
    public void verifyElementEnabled(){
        driver.get("http://demowebshop.tricentis.com");
        WebElement login=driver.findElement(By.cssSelector("li>a.ico-login"));
        login.click();
        WebElement submit=driver.findElement(By.cssSelector("input[value='Log in']"));
        boolean enabledStatus=submit.isEnabled();
        System.out.println("enabledStatus= "+enabledStatus);
        Assert.assertTrue(enabledStatus,"Submit button is not enabled");
    }
    @Test
    public void verifyCheckBoxSelectionStatus(){
        driver.get("http://demowebshop.tricentis.com");
        WebElement login=driver.findElement(By.cssSelector("li>a.ico-login"));
        login.click();
        WebElement checkbox=driver.findElement(By.cssSelector("input[type='checkbox']"));
        boolean selectionStatusBeforeClick=checkbox.isSelected();
        System.out.println(selectionStatusBeforeClick);
        Assert.assertFalse(selectionStatusBeforeClick,"Checkbox selection is not expected");
        checkbox.click();
        boolean selectionStatus=checkbox.isSelected();
        System.out.println("SelectionStatus= "+selectionStatus);
        Assert.assertTrue(selectionStatus,"Checkbox is not selected");
    }
    @Test
    public void verifyPromptAlert(){
        driver.get("https://demoqa.com/alerts");
        WebElement prompt=driver.findElement(By.cssSelector("button#promtButton"));
        prompt.click();
        Alert alert=driver.switchTo().alert();
        String alertString=alert.getText();
        System.out.println(alertString);
        alert.sendKeys("surya");
        alert.accept();
        //alert.dismiss();
    }
    @Test
    public void verifyDropDown(){
        driver.get("https://demo.guru99.com/test/newtours/");
        List<WebElement> registerLink=driver.findElements(By.xpath("//td[@class='mouseOut']/child::a"));
        for(int i=0;i< registerLink.size();i++) {
            if (registerLink.get(i).getText().equals("REGISTER")) {
                registerLink.get(i).click();
                break;
            }
        }
        WebElement dropdown=driver.findElement(By.xpath("//select[@name='country']"));
        Select select=new Select(dropdown);
        //select.selectByVisibleText("INDIA");
        select.selectByValue("INDIA");
        select.selectByIndex(20);
       List<WebElement> dropdownValues=select.getOptions();
        System.out.println(dropdownValues.size());
        for (int i=0;i<dropdownValues.size();i++) {
            System.out.println(dropdownValues.get(i).getText());
        }
    }
    @Test
    public void verifySimpleAlert(){
        driver.get("https://demoqa.com/alerts");
        WebElement simpleAlert=driver.findElement(By.cssSelector("button#alertButton"));
        simpleAlert.click();
        Alert alert=driver.switchTo().alert();
        alert.accept();
    }
    @Test
    public void verifyConfirmationAlert(){
        driver.get("https://demoqa.com/alerts");
        WebElement confirmationAlert=driver.findElement(By.cssSelector("button#confirmButton"));
        confirmationAlert.click();
        Alert alert=driver.switchTo().alert();
        //alert.accept();
        alert.dismiss();
    }
    @Test
    public void verifyDeleteCustomer(){
        driver.get("https://demo.guru99.com/test/delete_customer.php");
        WebElement customerTextBox=driver.findElement(By.cssSelector("td>input[name='cusid']"));
        customerTextBox.sendKeys("Surya");
        WebElement submitButton=driver.findElement(By.xpath("//input[@name='submit']"));
        submitButton.click();
        Alert alert=driver.switchTo().alert();
        alert.accept();
        alert.accept();
    }
    @Test
    public void verifyMultipleWindows() {
        driver.get("https://demo.guru99.com/popup.php");
        String parentWindow = driver.getWindowHandle();
        System.out.println("parentWindow= " + parentWindow);
        WebElement clickHere = driver.findElement(By.cssSelector("p>a[target='_blank']"));
        clickHere.click();
        Set<String> windows = driver.getWindowHandles();
        System.out.println("number of windows =" + windows.size());
        Iterator<String> ite = windows.iterator();
        while (ite.hasNext()) {
            System.out.println(ite.next());
            String newWindow = ite.next();
            if (!newWindow.equals(parentWindow)) {
                driver.switchTo().window(newWindow);
                Utility utility = new Utility();
                String mail = Utility.random();
                WebElement emailId = driver.findElement(By.cssSelector("td>input[name='emailid']"));
                emailId.sendKeys(mail);
                WebElement submitButton = driver.findElement(By.cssSelector("input[name='btnLogin']"));
                submitButton.click();
                driver.close();
            }
        }
        driver.switchTo().window(parentWindow);
    }
}


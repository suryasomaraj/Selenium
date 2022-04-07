package org.obs.seleniumcommands;

import io.github.bonigarcia.wdm.WebDriverManager;
import obs.selenium.ExcelUtility;
import obs.selenium.Utility;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITest;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.*;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class SeleniumCommands {
    WebDriver driver;

    public void testInitialize(String browser) {
        if (browser.equals("chrome")) {
            WebDriverManager.chromedriver().setup();
            //System.setProperty("webdriver.chrome.driver", "C:\\Users\\Lenovo\\Downloads\\chromedriver_win32 (2)\\chromedriver.exe");
            driver = new ChromeDriver();
        } else if (browser.equals("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            //System.setProperty("webdriver.gecko.driver", "C:\\Selenium_files\\geckodriver-v0.30.0-win64\\geckodriver.exe");
            driver = new FirefoxDriver();
        } else if (browser.equals("edge")) {
            WebDriverManager.edgedriver().setup();
            //System.setProperty("webdriver.edge.driver", "C:\\Selenium_files\\edgedriver_win64\\msedgedriver.exe");
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
    public void setUp() {
        testInitialize("chrome");//driver.get("http://demowebshop.tricentis.com");
    }

    @AfterMethod
    public void tearDown(ITestResult result) throws IOException {
        if(ITestResult.FAILURE==result.getStatus()) {
            TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
            File screenshot = takesScreenshot.getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(screenshot, new File("./Screenshots/" + result.getName() + ".png"));
        }
        driver.close();
    }

    @Test
    public void verifyHomepageTitle() {
        driver.get("http://demowebshop.tricentis.com");
        String actualTitle = driver.getTitle();
        String expectedTitle = "Demo Web Shop";
        Assert.assertEquals(actualTitle, expectedTitle, "invalid Page Title");
    }
    @Test
    public void verifyNavigationCommands(){
        driver.navigate().to("http://demowebshop.tricentis.com/login");
        //driver.navigate().back();
        // driver.navigate().forward();
        driver.navigate().refresh();
    }
    @Test
    public void verifyPageSource(){
        String actualTitle=driver.getTitle();
        System.out.println(actualTitle);
        String currentUrl=driver.getCurrentUrl();
        System.out.println(currentUrl);
        String getPageSource=driver.getPageSource();
        System.out.println(getPageSource); //source code
    }
    @Test
    public void verifyLocatorCommands(){
        WebElement email=driver.findElement(By.id("Email123"));
        WebElement email1=driver.findElement(By.name("Email"));
        WebElement email2=driver.findElement(By.className("email"));
        WebElement email3=driver.findElement(By.xpath("//*[@id=\"Email\"]"));
        WebElement email4=driver.findElement(By.linkText("login"));
        WebElement email5=driver.findElement(By.partialLinkText("log"));
        WebElement email6=driver.findElement(By.cssSelector("#Email"));
        email6.sendKeys("suryasomaraj94@gmail.com");
        List<WebElement> tag =driver.findElements(By.tagName("a"));
        //List<WebElement> tag =driver.findElements(By.tagName("123"));
        int size=tag.size();
        System.out.println(size);
    }
    @Test
    public void verifyLogin() throws IOException {
        driver.get("http://demowebshop.tricentis.com");
       // driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20)); //pageLoadWait
        WebElement login = driver.findElement(By.cssSelector("li>a.ico-login"));
        login.click();
        ExcelUtility excel=new ExcelUtility();
        List<String> data=excel.readDataFromExcel("\\src\\main\\resources\\TestData.xlsx","Login");
        WebElement loginEmail = driver.findElement(By.cssSelector("input#Email"));
        System.out.println(data);
        loginEmail.sendKeys(data.get(2));
        WebElement password = driver.findElement(By.cssSelector("input.password"));
        password.sendKeys(data.get(3));
        WebElement checkbox = driver.findElement(By.cssSelector("input[type='checkbox']"));
        checkbox.click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));//implicit
        WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(20));//explicit
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[value='Log in']")));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        WebElement submit = driver.findElement(By.cssSelector("input[value='Log in']"));
        submit.click();
        WebElement account = driver.findElement(By.xpath("//div[@class='header-links']//a[@class='account']"));
        String actualemailID = account.getText();
        String expectedemailID = "suryasomaraj94@gmail.com";
        Assert.assertEquals(actualemailID, expectedemailID, "User login Failed");
    }

    @Test
    public void verifyClear() {
        driver.get("http://demowebshop.tricentis.com");
        WebElement login = driver.findElement(By.cssSelector("a.ico-login"));
        login.click();
        WebElement email = driver.findElement(By.cssSelector("input#Email"));
        email.sendKeys("suryasomaraj94@gmail.com");
        WebElement password = driver.findElement(By.cssSelector("input.password"));
        password.sendKeys("qwerty@123");
        email.clear();
        WebElement submit = driver.findElement(By.cssSelector("input[value='Log in']"));
        submit.click();
        WebElement clear = driver.findElement(By.xpath("//div[@class='validation-summary-errors']/child::span"));
        String actualemail = clear.getText();
        String expectedemail = "Login was unsuccessful. Please correct the errors and try again.";
        Assert.assertEquals(actualemail, expectedemail, "Invalid Error Message");
    }

    @Test
    public void verifyWebelementCommands() {
        driver.get("http://demowebshop.tricentis.com");
        WebElement login = driver.findElement(By.cssSelector("li>a.ico-login"));
        login.click();
        WebElement submit = driver.findElement(By.cssSelector("input[value='Log in']"));
        Dimension dimension = submit.getSize();
        int height = dimension.height;
        int width = dimension.width;
        System.out.println("height=" + height);
        System.out.println("width= " + width);
        Point point = submit.getLocation();
        int x = point.x;
        int y = point.y;
        System.out.println("X-coordinate= " + x);
        System.out.println("Y-coordinate= " + y);
        String actualloginbuttontext = submit.getAttribute("value");
        System.out.println("actual login button text= " + actualloginbuttontext);
        String expectedloginbuttontext = "Log in";
        // Assert.assertEquals(actualloginbuttontext,expectedloginbuttontext,"incorrect text in login button");
        String tagname = submit.getTagName();
        System.out.println("Tagname= " + tagname);
        String cssproperty = submit.getCssValue("color");
        System.out.println("cssproperty= " + cssproperty);
    }

    @Test
    public void searchButton() {
        driver.get("http://demowebshop.tricentis.com");
        WebElement search = driver.findElement(By.cssSelector("input[value='Search']"));
        Dimension dimension = search.getSize();
        int height = dimension.height;
        int width = dimension.width;
        System.out.println("height=" + height);
        System.out.println("width= " + width);
        Point point = search.getLocation();
        int x = point.x;
        int y = point.y;
        System.out.println("X-coordinate= " + x);
        System.out.println("Y-coordinate= " + y);
        String actualsearchbuttontext = search.getAttribute("value");
        System.out.println("actual Search button text= " + actualsearchbuttontext);
        String expectedsearchbuttontext = "Search";
        Assert.assertEquals(actualsearchbuttontext, expectedsearchbuttontext, "incorrect text in search button");
    }

    @Test
    public void verifyRegistration() throws InterruptedException {
        driver.get("http://demowebshop.tricentis.com");
        WebElement register = driver.findElement(By.cssSelector("a.ico-register"));
        register.click();
        selectGender("Female");
        Thread.sleep(5000);
        WebElement name = driver.findElement(By.cssSelector("input[name='FirstName']"));
        name.sendKeys("surya");
        WebElement namel = driver.findElement(By.cssSelector("input#LastName"));
        namel.sendKeys("somaraj");
        Utility utility = new Utility();
        String mail = Utility.random();
        WebElement email = driver.findElement(By.cssSelector("input#Email"));
        email.sendKeys(mail);
        WebElement password = driver.findElement(By.cssSelector("input[name='Password']"));
        password.sendKeys("qwerty@123");
        WebElement cpassword = driver.findElement(By.cssSelector("div>input#ConfirmPassword"));
        cpassword.sendKeys("qwerty@123");
        WebElement registerbutton = driver.findElement(By.cssSelector("input[name='register-button']"));
        registerbutton.click();
        WebElement loginLink = driver.findElement(By.cssSelector("div[class='header-links']>ul>li>a[class='account']"));
        String actualId = loginLink.getText();
        System.out.println(actualId);
        String expectedId = mail;
        Assert.assertEquals(actualId, expectedId, "Registration fails");
    }

    public void selectGender(String gender) {
        List<WebElement> radio = driver.findElements(By.xpath("//label[@class='forcheckbox']"));
        for (int i = 0; i < radio.size(); i++) {
            if (radio.get(i).getText().equals(gender)) {
                radio.get(i).click();
            } else {
                System.out.println("Invalid");
            }
        }
    }

    @Test
    public void verifyElementPresent() {
        driver.get("http://demowebshop.tricentis.com");
        WebElement login = driver.findElement(By.cssSelector("li>a.ico-login"));
        login.click();
        WebElement submit = driver.findElement(By.cssSelector("input[value='Log in']"));
        boolean result = submit.isDisplayed();
        System.out.println("Result=" + result);
        Assert.assertTrue(result, "Submit button not displayed");
    }

    @Test
    public void verifyElementEnabled() {
        driver.get("http://demowebshop.tricentis.com");
        WebElement login = driver.findElement(By.cssSelector("li>a.ico-login"));
        login.click();
        WebElement submit = driver.findElement(By.cssSelector("input[value='Log in']"));
        boolean enabledStatus = submit.isEnabled();
        System.out.println("enabledStatus= " + enabledStatus);
        Assert.assertTrue(enabledStatus, "Submit button is not enabled");
    }

    @Test
    public void verifyCheckBoxSelectionStatus() {
        driver.get("http://demowebshop.tricentis.com");
        WebElement login = driver.findElement(By.cssSelector("li>a.ico-login"));
        login.click();
        WebElement checkbox = driver.findElement(By.cssSelector("input[type='checkbox']"));
        boolean selectionStatusBeforeClick = checkbox.isSelected();
        System.out.println(selectionStatusBeforeClick);
        Assert.assertFalse(selectionStatusBeforeClick, "Checkbox selection is not expected");
        checkbox.click();
        boolean selectionStatus = checkbox.isSelected();
        System.out.println("SelectionStatus= " + selectionStatus);
        Assert.assertTrue(selectionStatus, "Checkbox is not selected");
    }

    @Test
    public void verifyPromptAlert() {
        driver.get("https://demoqa.com/alerts");
        WebElement prompt = driver.findElement(By.cssSelector("button#promtButton"));
        prompt.click();
        Alert alert = driver.switchTo().alert();
        String alertString = alert.getText();
        System.out.println(alertString);
        alert.sendKeys("surya");
        alert.accept();
        //alert.dismiss();
    }

    @Test
    public void verifyDropDown() {
        driver.get("https://demo.guru99.com/test/newtours/");
        List<WebElement> registerLink = driver.findElements(By.xpath("//td[@class='mouseOut']/child::a"));
        for (int i = 0; i < registerLink.size(); i++) {
            if (registerLink.get(i).getText().equals("REGISTER")) {
                registerLink.get(i).click();
                break;
            }
        }
        WebElement dropdown = driver.findElement(By.xpath("//select[@name='country']"));
        Select select = new Select(dropdown);
        //select.selectByVisibleText("INDIA");
        select.selectByValue("INDIA");
        select.selectByIndex(20);
        List<WebElement> dropdownValues = select.getOptions();
        System.out.println(dropdownValues.size());
        for (int i = 0; i < dropdownValues.size(); i++) {
            System.out.println(dropdownValues.get(i).getText());
        }
    }

    @Test
    public void verifySimpleAlert() {
        driver.get("https://demoqa.com/alerts");
        WebElement simpleAlert = driver.findElement(By.cssSelector("button#alertButton"));
        simpleAlert.click();
        Alert alert = driver.switchTo().alert();
        alert.accept();
    }

    @Test
    public void verifyConfirmationAlert() {
        driver.get("https://demoqa.com/alerts");
        WebElement confirmationAlert = driver.findElement(By.cssSelector("button#confirmButton"));
        confirmationAlert.click();
        Alert alert = driver.switchTo().alert();
        //alert.accept();
        alert.dismiss();
    }

    @Test
    public void verifyDeleteCustomer() {
        driver.get("https://demo.guru99.com/test/delete_customer.php");
        WebElement customerTextBox = driver.findElement(By.cssSelector("td>input[name='cusid']"));
        customerTextBox.sendKeys("Surya");
        WebElement submitButton = driver.findElement(By.xpath("//input[@name='submit']"));
        submitButton.click();
        Alert alert = driver.switchTo().alert();
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

    @Test
    public void verifyFramesInSelenium() {
        driver.get("https://demoqa.com/frames");
        //driver.switchTo().frame(3);
        //driver.switchTo().frame("frame1");
        WebElement frameElement = driver.findElement(By.id("frame1"));
        driver.switchTo().frame(frameElement);
        WebElement sample1 = driver.findElement(By.id("sampleHeading"));
        String sampleText = sample1.getText();
        System.out.println("Sample1 Text= " + sampleText);
    }

    @Test
    public void verifySelectedColor() {
        driver.get("https://selenium.obsqurazone.com/select-input.php");
        selectColor("Red");
        WebElement selectedColor = driver.findElement(By.xpath("//div[@id='message-one']"));
        String actualColorSelected = selectedColor.getText();
        String expectedColorSelected = "Selected Color : Red";
        Assert.assertEquals(actualColorSelected, expectedColorSelected, "Color is not Selected");
    }
    public void selectColor(String color) {
        WebElement options = driver.findElement(By.xpath("//select[@id='single-input-field']"));
        Select select = new Select(options);
        List<WebElement> dropDown = select.getOptions();
        System.out.println(dropDown.size());
        for (int i = 0; i < dropDown.size(); i++) {
            if (dropDown.get(i).getText().equalsIgnoreCase(color)) {
                dropDown.get(i).click();
            }
        }
    }
    @Test
    public void multiSelectedColors() {
        driver.get("https://selenium.obsqurazone.com/select-input.php");
        selectMultipleColor("Red", "Green");
        WebElement getAllSelected = driver.findElement(By.xpath("//button[@id='button-all']"));
        getAllSelected.click();
        WebElement allSelectedColor = driver.findElement(By.xpath("//div[@id='message-two']"));
        String actualAllSelectedColor = allSelectedColor.getText();
        String expectedAllSelectedColor = "All selected colors are : Red,Green";
        //Assert.assertEquals(actualAllSelectedColor,expectedAllSelectedColor,"Colors not selected");
    }
    public void selectMultipleColor(String color1, String color2) {
        WebElement selectColor = driver.findElement(By.xpath("//select[@id='multi-select-field']"));
        Select select = new Select(selectColor);
        List<WebElement> option = select.getOptions();
        for (int i = 0; i < option.size(); i++) {
            if (option.get(i).getText().equals(color1)) {
                select.selectByVisibleText(color1);
            } else if (option.get(i).getText().equals(color2)) {
                select.selectByVisibleText(color2);
            }
        }
       List<WebElement> allSelectedOptions = select.getAllSelectedOptions();
        for (int x = 0; x < allSelectedOptions.size(); x++) {
            System.out.println("all selected= " + allSelectedOptions.get(x).getText());
            WebElement getFirstSelected = select.getFirstSelectedOption();
            getFirstSelected.click();
            allSelectedOptions.get(x).click();
        }
    }
    @Test
    public void verifyFirstSelectedColor() {
        driver.get("https://selenium.obsqurazone.com/select-input.php");
        WebElement selectColor = driver.findElement(By.xpath("//select[@id='multi-select-field']"));
        Select select = new Select(selectColor);
        select.selectByIndex(0);
        select.selectByIndex(1);
        select.selectByIndex(2);
        WebElement getFirstSelected = select.getFirstSelectedOption();
        getFirstSelected.click();
        WebElement getFirstSelect = driver.findElement(By.xpath("//button[@id='button-first']"));
        getFirstSelect.click();
        WebElement firstSelectedColor = driver.findElement(By.xpath("//div[@id='message-two']"));
        System.out.println(firstSelectedColor.getText());
        String actualFirstSelected = firstSelectedColor.getText();
        String expectedFirstSelected = "First selected color is : Red";
        Assert.assertEquals(actualFirstSelected, expectedFirstSelected, "First Selection is not done");
    }

    @Test
    public void getDeselected() throws InterruptedException {
        driver.get("https://selenium.obsqurazone.com/select-input.php");
        WebElement selectColor = driver.findElement(By.xpath("//select[@id='multi-select-field']"));
        Select select = new Select(selectColor);
        select.selectByIndex(0);
        select.selectByIndex(1);
        select.selectByIndex(2);
        Thread.sleep(3000);
        select.deselectByIndex(0);
        select.deselectByValue("Yellow");
        //select.deselectByVisibleText("Green");
        //select.deselectAll();
        WebElement allSelector = driver.findElement(By.xpath("//button[@id='button-all']"));
        allSelector.click();
    }

    @Test
    public void verifyColorOptions() {
        driver.get("https://selenium.obsqurazone.com/select-input.php");
        colorOption("Yellow", "Red", "Green");
        WebElement color45 = driver.findElement(By.xpath("//div[@id='message-one']"));
        String actualColor = color45.getText();
        System.out.println(color45.getText());
        String expectedColor = "Selected Color : Green";
        Assert.assertEquals(actualColor, expectedColor, "Color not found");
    }

    public void colorOption(String color1, String color2, String color3) {
        WebElement colorOptions = driver.findElement(By.xpath("//select[@id='single-input-field']"));
        Select select = new Select(colorOptions);
        List<WebElement> colorList = select.getOptions();
        for (int i = 0; i < colorList.size(); i++) {
            if (colorList.get(i).getText().equals(color1) ||
                    (colorList.get(i).getText().equals(color2)) || colorList.get(i).getText().equals(color3)) {
                colorList.get(i).click();
            }
        }
    }

    @Test
    public void verifyRightClick() {
        driver.get("https://demo.guru99.com/test/simple_context_menu.html");
        WebElement rightClickMe = driver.findElement(By.xpath("//span[@class='context-menu-one btn btn-neutral']"));
        Actions action = new Actions(driver);
        action.contextClick(rightClickMe).build().perform();
    }

    @Test
    public void verifyDoubleClick() throws InterruptedException {
        driver.get("https://demo.guru99.com/test/simple_context_menu.html");
        WebElement doubleClickMe = driver.findElement(By.xpath(" //button[@ondblclick='myFunction()']"));
        Actions action = new Actions(driver);
        action.doubleClick(doubleClickMe).build().perform();
        Alert alert = driver.switchTo().alert();
        String alertText = alert.getText();
        System.out.println(alertText);
        Thread.sleep(3000);
        alert.accept();
    }

    @Test
    public void verifyMouseOver() {
        driver.get("https://demoqa.com/menu");
        List<WebElement> menuList1 = driver.findElements(By.xpath("//ul[@id='nav']//a"));
        selectMainMenu("Main Item 2", "SUB SUB LIST", menuList1);
    }
    public void selectMainMenu(String menuItem, String subMenu, List<WebElement> menuList) {
        //List<WebElement> menuList = driver.findElements(By.xpath("//ul[@id='nav']//a"));
        for (int i = 0; i < menuList.size(); i++) {
            if (menuList.get(i).getText().equals(menuItem)) {
                Actions action = new Actions(driver);
                action.moveByOffset(100, 120).build().perform();
                //action.moveToElement(menuList.get(i)).build().perform();
            }
        }
    }
    public void selectSubMenu(String subMenu, List<WebElement> menuList1) {
        for (int i = 0; i < menuList1.size(); i++) {
            String value = menuList1.get(i).getText();
            if (menuList1.get(i).getText().equals(subMenu)) {
                Actions action = new Actions(driver);
                action.moveToElement(menuList1.get(i)).build().perform();
            }
        }
    }
    @Test
    public void verifyDemoTourMouseOver() {
        driver.get("https://demo.guru99.com/test/newtours/");
        List<WebElement> menuListTour = driver.findElements(By.xpath("//tr[@class='mouseOut']//td//a"));
        selectList(menuListTour, "Flights");
    }
    public void selectList(List<WebElement> menuListTour, String menu) {
        for (int i = 0; i < menuListTour.size(); i++) {
            if (menuListTour.get(i).getText().equals(menu)) {
                Actions action = new Actions(driver);
                action.moveToElement(menuListTour.get(i)).build().perform();
                //action.moveToElement(menuListTour.get(i),100,100).build().perform();
            }
        }
    }
    @Test
    public void verifyDragAndDrop(){
        driver.get("https://demoqa.com/droppable");
        WebElement drag=driver.findElement(By.xpath("//div[@id='draggable']"));
        WebElement drop=driver.findElement(By.xpath("//div[@id='droppable']"));
        Actions action = new Actions(driver);
        action.dragAndDrop(drag,drop).build().perform();
    }
   @Test
   public void dragAndDropBy(){
        driver.get("https://demoqa.com/dragabble");
        WebElement dragMe=driver.findElement(By.xpath("//div[@id='dragBox']"));
        Actions action=new Actions(driver);
        action.dragAndDropBy(dragMe,200,200);
   }
   @Test
    public void verifyKeyBoardAction(){
        driver.get("https://demoqa.com/text-box");
        WebElement fullName=driver.findElement(By.xpath("//input[@id='userName']"));
        fullName.sendKeys("Surya Somaraj");
        Utility utility = new Utility();
        String mail = Utility.random();
        WebElement userEmail=driver.findElement(By.xpath("//input[@id='userEmail']"));
        userEmail.sendKeys(mail);
        WebElement currentAddress=driver.findElement(By.xpath("//textarea[@id='currentAddress']"));
        currentAddress.sendKeys("43,Aswathy,tvm");
        Actions action=new Actions(driver);
        /**select the current address**/
        action.keyDown(Keys.CONTROL).sendKeys("A").keyUp(Keys.CONTROL).build().perform();
        /**copy the current address**/
       action.keyDown(Keys.CONTROL).sendKeys("C").keyUp(Keys.CONTROL).build().perform();
       /**to press Tab key to switch the tab to permanent address**/
       action.sendKeys(Keys.TAB).build().perform();
       /**Pasting address**/
       action.keyDown(Keys.CONTROL).sendKeys("V").keyUp(Keys.CONTROL).build().perform();
    }
    @Test
    public void verifyFileUpload(){
        driver.get("https://demo.guru99.com/test/upload/");
        WebElement chooseFile=driver.findElement(By.xpath("//input[@id='uploadfile_0']"));
        WebElement terms=driver.findElement(By.xpath("//input[@id='terms']"));
        WebElement submitButton=driver.findElement(By.xpath("//button[@id='submitbutton']"));
        chooseFile.sendKeys("C:\\Selenium_files\\Sample.txt");
        terms.click();
        submitButton.click();
    }
    @Test
    public void fileUploadUsingRobotClass() throws AWTException {
        driver.get("https://www.monsterindia.com/seeker/registration");
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
        WebElement chooseFile=driver.findElement(By.xpath("//span[text()='Choose CV']"));
        chooseFile.click();
        StringSelection S=new StringSelection("C:\\Selenium_files\\Sample.txt");
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(S,null);
        Robot robot=new Robot();
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.keyRelease(KeyEvent.VK_V);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
    }
    @Test
    public void verifyJavaScriptExecutor(){
        driver.get("http://demowebshop.tricentis.com/");
        JavascriptExecutor js=(JavascriptExecutor)driver;
        js.executeScript("document.getElementById('newsletter-email').value='suryasoma@gmi.com'");
        js.executeScript("document.getElementById('newsletter-subscribe-button').click()");
    }
    @Test
    public void verifyScroll(){
        driver.get("https://demo.guru99.com/test/guru99home/");
        JavascriptExecutor jse=(JavascriptExecutor)driver;
        jse.executeScript("window.scrollBy(0,1000)");
    }

}








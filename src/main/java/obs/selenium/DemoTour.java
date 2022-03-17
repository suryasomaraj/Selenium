package obs.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class DemoTour extends Utility{
    public static void main(String[] args) {
        WebDriver driver;
        System.setProperty("webdriver.chrome.driver","C:\\ChromeDriver\\chromedriver.exe");
        driver=new ChromeDriver();
        driver.navigate().to("https://demo.guru99.com/test/newtours/");
        driver.manage().window().maximize();

        WebElement name=driver.findElement(By.name("firstName"));
        name.sendKeys("surya");
        WebElement namel=driver.findElement(By.name("lastName"));
        namel.sendKeys("somaraj");
        WebElement phone=driver.findElement(By.name("phone"));
        phone.sendKeys("1234567890");

        int n =10;
        String mail=(Utility.randomString(n));
        WebElement email=driver.findElement(By.id("userName"));
        //email.sendKeys("suryaqww@gmail.com");
        email.sendKeys(mail+"@gmail.com");

        WebElement address1=driver.findElement(By.name("address1"));
        address1.sendKeys("ssbhavan");
        WebElement city1=driver.findElement(By.name("city"));
        city1.sendKeys("kottayam");
        WebElement state1=driver.findElement(By.name("state"));
        state1.sendKeys("kerala");

        WebElement post1=driver.findElement(By.name("postalCode"));
        post1.sendKeys("689625");
         driver.findElement(By.name("country")).click();


        WebElement user1=driver.findElement(By.id("email"));
       user1.sendKeys("suryasomaraj");
        WebElement password1=driver.findElement(By.name("password"));
        password1.sendKeys("qwerty@123");
        WebElement password2=driver.findElement(By.name("confirmPassword"));
        password2.sendKeys("qwerty@123");
        driver.findElement(By.name("submit")).click();


        WebElement name1=driver.findElement(By.xpath("//input[@name='firstName']"));
        WebElement name2=driver.findElement(By.xpath("//input[@name='lastName']"));
        WebElement phone1=driver.findElement(By.xpath("//input[@name='phone']"));
        WebElement emailid=driver.findElement(By.xpath("//input[@id='userName']"));
        WebElement address=driver.findElement(By.xpath("//input[@name='address1']"));
        WebElement city=driver.findElement(By.xpath("//input[@name='city']"));
        WebElement state=driver.findElement(By.xpath("//input[@name='state']"));
        WebElement post=driver.findElement(By.xpath("//input[@name='postalCode']"));
        WebElement country=driver.findElement(By.xpath("//select[@name='country']"));
        WebElement username=driver.findElement(By.xpath("//input[@id='email']"));
        WebElement password=driver.findElement(By.xpath("//input[@name='password']"));
        WebElement cpassword=driver.findElement(By.xpath("//input[@ name='confirmPassword']"));













    }
}

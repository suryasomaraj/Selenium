package obs.selenium;

import io.opentelemetry.exporter.logging.SystemOutLogExporter;
import net.bytebuddy.implementation.bind.MethodDelegationBinder;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BrowserDriver {
    public static void main(String[] args) {
        WebDriver driver;
      System.setProperty("webdriver.chrome.driver","C:\\ChromeDriver\\chromedriver.exe");
        driver=new ChromeDriver();

       /* System.setProperty("webdriver.gecko.driver","C:\\Selenium_files\\geckodriver-v0.30.0-win64\\geckodriver.exe");
        driver=new FirefoxDriver();

        System.setProperty("webdriver.edge.driver","C:\\Selenium_files\\edgedriver_win64\\msedgedriver.exe");
        driver=new EdgeDriver();
        */

        //to delete cookies
        driver.manage().deleteAllCookies();


        //maximize window
        driver.manage().window().maximize();

        //load url
       // driver.get("http://demowebshop.tricentis.com/");


        //navigation commands
        driver.navigate().to("http://demowebshop.tricentis.com/");
        driver.navigate().back();
        driver.navigate().forward();
        driver.navigate().refresh();


        //how to get title
        String actualTitle=driver.getTitle();
        System.out.println(actualTitle);

        String currentUrl=driver.getCurrentUrl();
        System.out.println(currentUrl);

      /*  String getPageSource=driver.getPageSource();
        System.out.println(getPageSource); //source code  */









        //close browser
        driver.close();




    }
}

package org.obsq.seleniumdriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Browsers {

    public static void main(String[] args) {



        System.setProperty("webdrive.chrome.driver", "C:\\ChromeDriver\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();



        System.setProperty("webdriver.gecko.driver", "C:\\Selenium_files\\geckodriver-v0.30.0-win64\\geckodriver.exe");
        WebDriver ffd = new FirefoxDriver();


        System.setProperty("webdriver.edge.driver", "C:\\Selenium_files\\edgedriver_win64\\msedgedriver.exe");
        WebDriver msd = new EdgeDriver();


    }



}

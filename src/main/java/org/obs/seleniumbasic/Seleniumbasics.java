package org.obs.seleniumbasic;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Seleniumbasics {

    public static void main(String[] args) throws InterruptedException {
       /* System.setProperty("webdriver.chrome.driver", "C:\\ChromeDriver\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();*/

        /*System.setProperty("webdriver.gecko.driver", "C:\\Selenium_files\\geckodriver-v0.30.0-win64\\geckodriver.exe");
        WebDriver driver = new FirefoxDriver();*/


        System.setProperty("webdriver.edge.driver", "C:\\Selenium_files\\edgedriver_win64\\msegdedriver.exe");
        WebDriver driver = new EdgeDriver();





    }
}

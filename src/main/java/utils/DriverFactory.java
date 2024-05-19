package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class DriverFactory {
    private static ThreadLocal<WebDriver> webDriverThreadLocal = new ThreadLocal<>();

    public static WebDriver getDriver() {
        if (webDriverThreadLocal.get() == null) {
            webDriverThreadLocal.set(createDriver());
        }
        return webDriverThreadLocal.get();
    }

    private static WebDriver createDriver() {
        WebDriver driver = null;
        switch (getBrowserName()) {
            case ("chrome") -> {
                WebDriverManager.chromedriver().setup();
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);
                driver = new ChromeDriver(chromeOptions);
                break;
            }
            case ("firefox") -> {
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);
                driver = new FirefoxDriver(firefoxOptions);
                break;
            }
        }
        return driver;
    }

    private static String getBrowserName() {
        String browserName = null;
        try {
            Properties properties = new Properties();
            FileInputStream fileInputStream = new FileInputStream("C:\\Users\\Magda\\OneDrive\\Pulpit\\ProjektyJava\\RahulshettyClient_Testng\\src\\test\\java\\properties\\config.properties");
            properties.load(fileInputStream);
            browserName = properties.getProperty("browser").toLowerCase().trim();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return browserName;
    }

    public static void cleanUpDriver() {
        webDriverThreadLocal.get().quit();
        webDriverThreadLocal.remove();
    }
}

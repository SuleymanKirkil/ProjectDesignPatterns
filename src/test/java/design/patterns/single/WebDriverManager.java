package design.patterns.single;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import java.util.concurrent.TimeUnit;

public class WebDriverManager {
    private static WebDriverManager instance;
    private WebDriver driver;
    private final String BASE_URL = "http://test.rubywatir.com/";
    
    private WebDriverManager() {
        // Private constructor to prevent instantiation
    }
    
    public static WebDriverManager getInstance() {
        if (instance == null) {
            synchronized (WebDriverManager.class) {
                if (instance == null) {
                    instance = new WebDriverManager();
                }
            }
        }
        return instance;
    }
    
    public WebDriver getDriver() {
        if (driver == null) {
            // Chrome driver'ı başlat
            driver = new ChromeDriver();
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        }
        return driver;
    }
    
    public void navigateToBaseUrl() {
        getDriver().get(BASE_URL);
    }
    
    public void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
    
    // Thread-safe cleanup
    public void cleanup() {
        quitDriver();
        instance = null;
    }
} 
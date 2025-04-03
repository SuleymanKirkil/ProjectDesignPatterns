package design.patterns.factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.util.concurrent.TimeUnit;

// Chrome tarayıcısı için WebDriver oluşturan sınıf
public class ChromeDriverFactory implements WebDriverFactory {
    
    @Override
    public WebDriver createDriver() {
        // Chrome tarayıcısını başlat
        WebDriver driver = new ChromeDriver();
        
        // Tarayıcıyı tam ekran yap
        driver.manage().window().maximize();
        
        return driver;
    }
    
    @Override
    public void configureDriver(WebDriver driver) {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
    }
    
    @Override
    public void quitDriver(WebDriver driver) {
        if (driver != null) {
            driver.quit();
        }
    }
} 
package design.patterns.factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.time.Duration;


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

    public void configureDriver(WebDriver driver) {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
    }

    public void quitDriver(WebDriver driver) {
        if (driver != null) {
            driver.quit();
        }
    }
} 
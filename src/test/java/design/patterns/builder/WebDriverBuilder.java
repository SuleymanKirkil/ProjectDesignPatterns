package design.patterns.builder;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import java.time.Duration;

// WebDriver oluşturmak için Builder sınıfı
public class WebDriverBuilder {
    // Varsayılan değerler
    private String browserType = "chrome";
    private boolean maximizeWindow = true;
    private int implicitWait = 10;
    private int pageLoadTimeout = 30;
    
    // Tarayıcı tipini ayarla
    public WebDriverBuilder withBrowserType(String browserType) {
        this.browserType = browserType;
        return this;
    }
    
    // Pencereyi büyütme ayarını yap
    public WebDriverBuilder withMaximizeWindow(boolean maximize) {
        this.maximizeWindow = maximize;
        return this;
    }
    
    // Bekleme süresini ayarla
    public WebDriverBuilder withImplicitWait(int seconds) {
        this.implicitWait = seconds;
        return this;
    }
    
    // Sayfa yükleme süresini ayarla
    public WebDriverBuilder withPageLoadTimeout(int seconds) {
        this.pageLoadTimeout = seconds;
        return this;
    }
    
    // WebDriver'ı oluştur ve yapılandır
    public WebDriver build() {
        // WebDriver'ı oluştur
        WebDriver driver = createDriver();
        
        // WebDriver'ı yapılandır
        configureDriver(driver);
        
        return driver;
    }
    
    // Tarayıcı tipine göre WebDriver oluştur
    private WebDriver createDriver() {
        switch (browserType.toLowerCase()) {
            case "chrome":
                return new ChromeDriver();
            case "firefox":
                return new FirefoxDriver();
            default:
                throw new IllegalArgumentException("Desteklenmeyen tarayıcı: " + browserType);
        }
    }
    
    // WebDriver'ı yapılandır
    private void configureDriver(WebDriver driver) {
        // Pencereyi büyüt
        if (maximizeWindow) {
            driver.manage().window().maximize();
        }
        
        // Bekleme sürelerini ayarla
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(implicitWait));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(pageLoadTimeout));
    }
} 
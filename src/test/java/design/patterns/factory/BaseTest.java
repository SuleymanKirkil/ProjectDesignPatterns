package design.patterns.factory;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

// Tüm testler için temel sınıf
public class BaseTest {
    // WebDriver ve factory değişkenleri
    protected WebDriver driver;
    protected WebDriverFactory factory;
    
    // Test sayfasının URL'i
    protected final String BASE_URL = "http://test.rubywatir.com/";
    
    @BeforeMethod
    public void setUp() {
        // Tarayıcı tipini belirle (varsayılan: chrome)
        String browserType = "chrome";
        
        // Factory'yi al
        factory = WebDriverFactoryProvider.getFactory(browserType);
        
        // WebDriver'ı oluştur
        driver = factory.createDriver();
        
        // Test sayfasına git
        driver.get(BASE_URL);
    }
    
    @AfterMethod
    public void tearDown() {
        // Tarayıcıyı kapat
        if (driver != null) {
            driver.quit();
        }
    }
} 
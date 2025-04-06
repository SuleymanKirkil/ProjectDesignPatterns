package design.patterns.builder;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

// Tüm testler için temel sınıf
public class BaseTest {
    // WebDriver değişkeni
    protected WebDriver driver;
    
    // Test sayfasının URL'i
    protected final String BASE_URL = "http://test.rubywatir.com/";
    
    @BeforeMethod
    public void setUp() {
        // WebDriver'ı oluştur ve yapılandır
        driver = new WebDriverBuilder()
            .withBrowserType("chrome")  // Tarayıcı tipi
            .withMaximizeWindow(true)   // Pencereyi büyüt
            .withImplicitWait(10)       // Bekleme süresi
            .withPageLoadTimeout(30)    // Sayfa yükleme süresi
            .build();                   // WebDriver'ı oluştur
        
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
# Page Object Model (POM) Design Pattern

## 1. Giriş
Page Object Model (POM), Selenium WebDriver ile UI test otomasyonunda kullanılan bir tasarım desenidir. Bu desen, test kodunun daha düzenli, bakımı kolay ve yeniden kullanılabilir olmasını sağlar.

## 2. Page Object Model Nedir?
- Her web sayfası için ayrı bir sınıf oluşturulur
- Sayfa elementleri ve bu elementlerle yapılabilecek işlemler bu sınıflarda tanımlanır
- Test senaryoları bu sınıfları kullanarak yazılır
- Kod tekrarını önler ve bakımı kolaylaştırır

## 3. POM'un Avantajları
- Kod tekrarını azaltır
- Bakımı kolaylaştırır
- Test senaryolarını daha okunabilir yapar
- Değişikliklere karşı daha dayanıklıdır
- Test kodunun yeniden kullanılabilirliğini artırır

## 4. Temel Yapı
```java
public class LoginPage {
    // Web element tanımlamaları
    @FindBy(id = "username")
    private WebElement usernameField;
    
    @FindBy(id = "password")
    private WebElement passwordField;
    
    @FindBy(id = "loginButton")
    private WebElement loginButton;
    
    // Constructor
    public LoginPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }
    
    // Sayfa metodları
    public void enterUsername(String username) {
        usernameField.sendKeys(username);
    }
    
    public void enterPassword(String password) {
        passwordField.sendKeys(password);
    }
    
    public void clickLoginButton() {
        loginButton.click();
    }
}
```

## 5. Best Practices
1. Her sayfa için ayrı bir sınıf oluşturun
2. Web elementleri private olarak tanımlayın
3. Sayfa metodlarını public olarak tanımlayın
4. Sayfa sınıflarını mantıksal olarak gruplandırın
5. Ortak işlemler için base page sınıfı oluşturun
6. Locator'ları dışarıdan yönetilebilir hale getirin

## 6. Örnek Test Senaryosu
```java
public class LoginTest {
    private WebDriver driver;
    private LoginPage loginPage;
    
    @Before
    public void setup() {
        driver = new ChromeDriver();
        loginPage = new LoginPage(driver);
    }
    
    @Test
    public void testSuccessfulLogin() {
        loginPage.enterUsername("testuser");
        loginPage.enterPassword("password123");
        loginPage.clickLoginButton();
        
        // Assertions
    }
    
    @After
    public void tearDown() {
        driver.quit();
    }
}
```

## 7. Page Factory Kullanımı
- @FindBy anotasyonu ile elementleri tanımlayın
- PageFactory.initElements() ile elementleri initialize edin
- Lazy initialization kullanın

## 8. Common Pitfalls
1. Çok fazla sayfa sınıfı oluşturmak
2. Test mantığını page sınıflarına yazmak
3. Gereksiz abstraction katmanları eklemek
4. Locator'ları hardcode etmek
5. Sayfa sınıflarını çok büyük yapmak

## 9. Modern Page Object Model Yaklaşımları

### 9.1 Page Factory with Loadable Component Pattern (En Çok Tercih Edilen)
Bu yaklaşım, Page Factory ve Loadable Component pattern'lerinin en iyi özelliklerini birleştirir:

```java
public abstract class BasePage {
    protected WebDriver driver;
    
    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    
    public abstract boolean isLoaded();
    
    public void waitForPageLoad() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
            .until(d -> ((JavascriptExecutor) d)
                .executeScript("return document.readyState").equals("complete"));
    }
}

public class LoginPage extends BasePage {
    @FindBy(id = "username")
    private WebElement usernameField;
    
    @FindBy(id = "password")
    private WebElement passwordField;
    
    @FindBy(id = "loginButton")
    private WebElement loginButton;
    
    public LoginPage(WebDriver driver) {
        super(driver);
    }
    
    @Override
    public boolean isLoaded() {
        return usernameField.isDisplayed() && 
               passwordField.isDisplayed() && 
               loginButton.isDisplayed();
    }
    
    public DashboardPage login(String username, String password) {
        usernameField.sendKeys(username);
        passwordField.sendKeys(password);
        loginButton.click();
        return new DashboardPage(driver);
    }
}
```

#### Avantajları:
1. Sayfa yüklenme kontrolü otomatik
2. Elementlerin varlığını kontrol etme
3. Daha güvenilir test senaryoları
4. Kod tekrarını minimize etme
5. Bakımı kolay yapı

### 9.2 Diğer Yaklaşımlar
1. **Page Factory Pattern**: Temel POM yaklaşımı, basit projeler için uygun
2. **Loadable Component Pattern**: Sayfa yüklenme kontrolü odaklı
3. **Fluent Interface Pattern**: Zincirleme metod çağrıları için
4. **Component Object Pattern**: Tekrar eden UI bileşenleri için

### 9.3 Önerilen Kullanım
1. Küçük-orta ölçekli projeler: Page Factory Pattern
2. Orta-büyük ölçekli projeler: Page Factory with Loadable Component
3. Kompleks UI bileşenleri: Component Object Pattern
4. API testleri: Fluent Interface Pattern

## 10. Önerilen Kaynaklar
1. **Resmi Selenium WebDriver Dokümantasyonu**
   - [Selenium WebDriver Documentation](https://www.selenium.dev/documentation/webdriver/)
   - [Selenium Java API Docs](https://www.selenium.dev/selenium/docs/api/java/)

2. **Page Object Model Eğitim Kaynakları**
   - [SeleniumHQ Page Object Model Guide](https://www.selenium.dev/documentation/test_practices/encouraged/page_object_models/)
   - [Guru99 Page Object Model Tutorial](https://www.guru99.com/page-object-model-pom-page-factory-in-selenium-ultimate-guide.html)
   - [ToolsQA Page Object Model](https://www.toolsqa.com/selenium-webdriver/page-object-model/)

3. **Video Eğitimler**
   - [Selenium Page Object Model Tutorial - YouTube](https://www.youtube.com/watch?v=3GQZfBQYj2E)
   - [Page Object Model in Selenium - Udemy](https://www.udemy.com/course/selenium-webdriver-with-java-testng-and-log4j/)

4. **Pratik Örnekler**
   - [SeleniumHQ GitHub Examples](https://github.com/SeleniumHQ/selenium/tree/trunk/examples)
   - [Page Object Model Example Project](https://github.com/angiejones/selenium-page-object-model)

5. **Blog Yazıları ve Makaleler**
   - [Martin Fowler - Page Object](https://martinfowler.com/bliki/PageObject.html)
   - [Selenium Best Practices](https://www.selenium.dev/documentation/test_practices/encouraged/)

6. **Kitaplar**
   - "Selenium WebDriver 3 Practical Guide" - Unmesh Gundecha
   - "Selenium Design Patterns and Best Practices" - Dima Kovalenko
   - "Selenium Testing Tools Cookbook" - Unmesh Gundecha

7. **Topluluk Kaynakları**
   - [Selenium Users Google Group](https://groups.google.com/g/selenium-users)
   - [Stack Overflow Selenium Tag](https://stackoverflow.com/questions/tagged/selenium)
   - [Selenium Slack Channel](https://seleniumhq.slack.com/)

Bu kaynaklar, Page Object Model'i öğrenmek ve uygulamak için güvenilir ve güncel bilgiler sunmaktadır. Özellikle resmi Selenium dokümantasyonu ve GitHub örnekleri, pratik uygulama için çok faydalı olacaktır.

## 11. Web Element Kontrolü ve Amacı

### 11.1 Neden Web Elementleri Kontrol Edilir?
1. **Sayfa Yüklenme Doğrulaması**
   - Sayfanın tam olarak yüklendiğinden emin olmak
   - AJAX çağrılarının tamamlandığını kontrol etmek
   - Dinamik içeriğin hazır olduğunu doğrulamak

2. **Test Güvenilirliği**
   - Test senaryolarının daha stabil çalışması
   - Yanlış pozitif/negatif sonuçların önlenmesi
   - Test hatalarının azaltılması

3. **Hata Yakalama**
   - Sayfa yapısındaki değişiklikleri erken tespit etmek
   - UI değişikliklerinden kaynaklanan hataları önlemek
   - Test süreçlerinin daha güvenilir olmasını sağlamak

### 11.2 Ne Zaman Kontrol Edilmeli?
```java
public class LoginPage extends BasePage {
    // Kritik elementler
    @FindBy(id = "username")
    private WebElement usernameField;
    
    @FindBy(id = "password")
    private WebElement passwordField;
    
    @FindBy(id = "loginButton")
    private WebElement loginButton;
    
    // İsteğe bağlı elementler
    @FindBy(className = "remember-me")
    private WebElement rememberMeCheckbox;
    
    @FindBy(linkText = "Forgot Password?")
    private WebElement forgotPasswordLink;
    
    @Override
    public boolean isLoaded() {
        // Sadece kritik elementleri kontrol et
        return usernameField.isDisplayed() && 
               passwordField.isDisplayed() && 
               loginButton.isDisplayed();
    }
    
    public void verifyOptionalElements() {
        // İsteğe bağlı elementleri ayrı bir metodda kontrol et
        if (rememberMeCheckbox.isDisplayed()) {
            // İşlem yap
        }
    }
}
```

### 11.3 Best Practices
1. **Kritik vs İsteğe Bağlı Elementler**
   - Sadece kritik elementleri `isLoaded()` metodunda kontrol edin
   - İsteğe bağlı elementleri ayrı metodlarda kontrol edin
   - Her sayfa için kritik elementleri belirleyin

2. **Performans Optimizasyonu**
   - Gereksiz element kontrollerinden kaçının
   - Kontrolleri akıllıca gruplandırın
   - Timeout değerlerini optimize edin

3. **Hata Yönetimi**
   - Anlamlı hata mesajları kullanın
   - Element bulunamadığında uygun exception fırlatın
   - Test senaryolarını durdurmadan önce log kaydı tutun

### 11.4 Örnek Kullanım Senaryoları
```java
public class DashboardPage extends BasePage {
    @Override
    public boolean isLoaded() {
        // Sadece dashboard'un yüklendiğini gösteren kritik elementi kontrol et
        return dashboardHeader.isDisplayed();
    }
    
    public void verifyAllWidgets() {
        // Tüm widget'ları ayrı bir metodda kontrol et
        verifySalesWidget();
        verifyActivityWidget();
        verifyNotificationWidget();
    }
    
    private void verifySalesWidget() {
        if (salesWidget.isDisplayed()) {
            // Widget ile ilgili işlemler
        }
    }
}
```

### 11.5 Önerilen Yaklaşım
1. Her sayfa için kritik elementleri belirleyin
2. `isLoaded()` metodunda sadece kritik elementleri kontrol edin
3. İsteğe bağlı elementleri ayrı metodlarda kontrol edin
4. Performans için gereksiz kontrollerden kaçının
5. Hata durumlarını uygun şekilde yönetin

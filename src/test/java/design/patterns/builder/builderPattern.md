# Builder Design Pattern

## Tanım
Builder Design Pattern, karmaşık nesnelerin adım adım oluşturulmasını sağlayan bir creational (oluşturucu) tasarım desenidir. Aynı oluşturma sürecinin farklı temsillerini oluşturmamıza olanak tanır.

## Kullanım Amacı
- Karmaşık nesnelerin oluşturulmasını basitleştirmek
- Nesne oluşturma sürecini adımlara ayırmak
- İsteğe bağlı parametreleri kolayca yönetmek
- Kod okunabilirliğini artırmak

## Avantajları
1. **Esneklik**: Nesne oluşturma sürecini adım adım kontrol edebilme
2. **Okunabilirlik**: Fluent interface sayesinde daha anlaşılır kod
3. **Genişletilebilirlik**: Yeni özellikler eklemek kolay
4. **Parametre Yönetimi**: İsteğe bağlı parametreleri kolayca yönetme

## Dezavantajları
1. **Karmaşıklık**: Basit nesneler için gereksiz karmaşıklık
2. **Sınıf Sayısı**: Daha fazla sınıf gerektirir

## Kullanım Alanları
- WebDriver konfigürasyonu
- Test verisi oluşturma
- Rapor oluşturma
- Karmaşık nesne oluşturma

## Örnek Implementasyon

### 1. WebDriver Builder
```java
public class WebDriverBuilder {
    private String browserType;
    private boolean maximizeWindow;
    private int implicitWait;
    
    public WebDriverBuilder withBrowserType(String browserType) {
        this.browserType = browserType;
        return this;
    }
    
    public WebDriverBuilder withMaximizeWindow(boolean maximize) {
        this.maximizeWindow = maximize;
        return this;
    }
    
    public WebDriverBuilder withImplicitWait(int seconds) {
        this.implicitWait = seconds;
        return this;
    }
    
    public WebDriver build() {
        WebDriver driver = createDriver();
        configureDriver(driver);
        return driver;
    }
}
```

### 2. Test Data Builder
```java
public class TestDataBuilder {
    private String username;
    private String password;
    private String email;
    
    public TestDataBuilder withUsername(String username) {
        this.username = username;
        return this;
    }
    
    public TestDataBuilder withPassword(String password) {
        this.password = password;
        return this;
    }
    
    public TestDataBuilder withEmail(String email) {
        this.email = email;
        return this;
    }
    
    public TestData build() {
        return new TestData(username, password, email);
    }
}
```

## Projemizde Kullanım Örneği

### 1. WebDriver Builder Implementasyonu
```java
public class WebDriverBuilder {
    private String browserType = "chrome";
    private boolean maximizeWindow = true;
    private int implicitWait = 10;
    private int pageLoadTimeout = 30;
    
    public WebDriverBuilder withBrowserType(String browserType) {
        this.browserType = browserType;
        return this;
    }
    
    public WebDriverBuilder withMaximizeWindow(boolean maximize) {
        this.maximizeWindow = maximize;
        return this;
    }
    
    public WebDriverBuilder withImplicitWait(int seconds) {
        this.implicitWait = seconds;
        return this;
    }
    
    public WebDriverBuilder withPageLoadTimeout(int seconds) {
        this.pageLoadTimeout = seconds;
        return this;
    }
    
    public WebDriver build() {
        WebDriver driver = createDriver();
        configureDriver(driver);
        return driver;
    }
}
```

### 2. Test Sınıfında Kullanım
```java
public class BaseTest {
    protected WebDriver driver;
    
    @BeforeMethod
    public void setUp() {
        driver = new WebDriverBuilder()
            .withBrowserType("chrome")
            .withMaximizeWindow(true)
            .withImplicitWait(10)
            .withPageLoadTimeout(30)
            .build();
    }
}
```

## Builder Pattern vs Factory Pattern
- **Builder Pattern**: Karmaşık nesnelerin adım adım oluşturulması
- **Factory Pattern**: Basit nesnelerin oluşturulması
- **Birlikte Kullanım**: Factory pattern, builder pattern ile birlikte kullanılabilir

## Best Practices
1. **Fluent Interface**: Method chaining için return this kullanın
2. **Varsayılan Değerler**: Mantıklı varsayılan değerler belirleyin
3. **Validasyon**: Build aşamasında parametreleri kontrol edin
4. **Immutable Objects**: Mümkünse immutable nesneler oluşturun

## Dikkat Edilmesi Gerekenler
1. Builder pattern'i gereksiz yere kullanmayın
2. Basit nesneler için builder kullanmayın
3. Builder'ı aşırı karmaşık hale getirmeyin
4. Gereksiz parametre eklemekten kaçının

## Kaynaklar
- [Design Patterns: Elements of Reusable Object-Oriented Software](https://www.amazon.com/Design-Patterns-Elements-Reusable-Object-Oriented/dp/0201633612)
- [Head First Design Patterns](https://www.amazon.com/Head-First-Design-Patterns-Brain-Friendly/dp/149207800X)
- [Refactoring Guru - Builder](https://refactoring.guru/design-patterns/builder) 
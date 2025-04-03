# Factory Design Pattern

## Tanım
Factory Design Pattern, nesne oluşturma mantığını client kodundan ayıran ve nesne oluşturma işlemini merkezi bir yerde toplayan bir creational (oluşturucu) tasarım desenidir.

## Kullanım Amacı
- Nesne oluşturma mantığını merkezileştirmek
- Client kodunu nesne oluşturma detaylarından soyutlamak
- Kod tekrarını önlemek
- Esnek ve genişletilebilir bir yapı sağlamak

## Avantajları
1. **Loose Coupling**: Client kodu, oluşturulan nesnelerin detaylarından bağımsızdır
2. **Single Responsibility**: Nesne oluşturma mantığı tek bir yerde toplanır
3. **Open/Closed Principle**: Yeni nesne tipleri eklemek kolaydır
4. **Kod Tekrarını Önleme**: Ortak oluşturma mantığı tek bir yerde toplanır

## Dezavantajları
1. **Karmaşıklık**: Basit senaryolarda gereksiz karmaşıklık yaratabilir
2. **Sınıf Sayısı**: Daha fazla sınıf ve interface gerektirir

## Kullanım Alanları
- Farklı tarayıcılar için WebDriver oluşturma
- Farklı veritabanı bağlantıları oluşturma
- Farklı rapor formatları oluşturma
- Farklı test verisi oluşturma

## Örnek Implementasyon

### 1. WebDriver Factory
```java
public interface WebDriverFactory {
    WebDriver createDriver();
}

public class ChromeDriverFactory implements WebDriverFactory {
    @Override
    public WebDriver createDriver() {
        return new ChromeDriver();
    }
}

public class FirefoxDriverFactory implements WebDriverFactory {
    @Override
    public WebDriver createDriver() {
        return new FirefoxDriver();
    }
}

public class WebDriverFactoryProvider {
    public static WebDriverFactory getFactory(String browserType) {
        switch (browserType.toLowerCase()) {
            case "chrome":
                return new ChromeDriverFactory();
            case "firefox":
                return new FirefoxDriverFactory();
            default:
                throw new IllegalArgumentException("Desteklenmeyen tarayıcı: " + browserType);
        }
    }
}
```

### 2. Test Data Factory
```java
public interface TestDataFactory {
    TestData createTestData();
}

public class UserTestDataFactory implements TestDataFactory {
    @Override
    public TestData createTestData() {
        return new UserTestData();
    }
}

public class ProductTestDataFactory implements TestDataFactory {
    @Override
    public TestData createTestData() {
        return new ProductTestData();
    }
}
```

## Projemizde Kullanım Örneği

### 1. WebDriver Factory Implementasyonu
```java
// WebDriverFactory.java
public interface WebDriverFactory {
    WebDriver createDriver();
    void configureDriver(WebDriver driver);
}

// ChromeDriverFactory.java
public class ChromeDriverFactory implements WebDriverFactory {
    @Override
    public WebDriver createDriver() {
        return new ChromeDriver();
    }
    
    @Override
    public void configureDriver(WebDriver driver) {
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }
}

// WebDriverFactoryProvider.java
public class WebDriverFactoryProvider {
    public static WebDriverFactory getFactory(String browserType) {
        switch (browserType.toLowerCase()) {
            case "chrome":
                return new ChromeDriverFactory();
            case "firefox":
                return new FirefoxDriverFactory();
            default:
                throw new IllegalArgumentException("Desteklenmeyen tarayıcı: " + browserType);
        }
    }
}
```

### 2. Test Sınıfında Kullanım
```java
public class BaseTest {
    protected WebDriver driver;
    protected WebDriverFactory factory;
    
    @BeforeMethod
    public void setUp() {
        String browserType = System.getProperty("browser", "chrome");
        factory = WebDriverFactoryProvider.getFactory(browserType);
        driver = factory.createDriver();
        factory.configureDriver(driver);
    }
}
```

## Factory Pattern vs Singleton Pattern
- **Factory Pattern**: Nesne oluşturma mantığını yönetir
- **Singleton Pattern**: Tek bir instance'ın oluşturulmasını sağlar
- **Birlikte Kullanım**: Factory pattern, singleton instance'ları oluşturmak için kullanılabilir

## Best Practices
1. **Interface Kullanımı**: Factory'ler için interface kullanın
2. **Konfigürasyon**: Factory'leri konfigüre edilebilir yapın
3. **Hata Yönetimi**: Uygun hata yönetimi ekleyin
4. **Dokümantasyon**: Factory'lerin kullanımını iyi dokümante edin

## Dikkat Edilmesi Gerekenler
1. Factory pattern'i gereksiz yere kullanmayın
2. Basit nesne oluşturma için factory kullanmayın
3. Factory'leri aşırı karmaşık hale getirmeyin
4. Dependency Injection ile birlikte kullanmayı düşünün

## Kaynaklar
- [Design Patterns: Elements of Reusable Object-Oriented Software](https://www.amazon.com/Design-Patterns-Elements-Reusable-Object-Oriented/dp/0201633612)
- [Head First Design Patterns](https://www.amazon.com/Head-First-Design-Patterns-Brain-Friendly/dp/149207800X)
- [Refactoring Guru - Factory Method](https://refactoring.guru/design-patterns/factory-method) 
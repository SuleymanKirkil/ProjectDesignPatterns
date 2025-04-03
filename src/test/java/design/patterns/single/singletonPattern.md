# Singleton Design Pattern

## Tanım
Singleton Design Pattern, bir sınıfın sadece bir örneğinin (instance) oluşturulmasını ve bu örneğe global bir erişim noktası sağlanmasını garanti eden bir tasarım desenidir.

## Kullanım Amacı
- Bir sınıfın sadece bir örneğinin olmasını sağlamak
- Global erişim noktası oluşturmak
- Kaynak kullanımını optimize etmek
- Paylaşılan kaynaklara erişimi kontrol etmek

## Avantajları
1. **Tek Örnek Garantisi**: Sınıfın sadece bir örneğinin oluşturulmasını sağlar
2. **Global Erişim**: Tek bir noktadan erişim sağlar
3. **Kaynak Optimizasyonu**: Gereksiz nesne oluşturulmasını önler
4. **Thread Safety**: Doğru implementasyon ile thread-safe çalışır

## Dezavantajları
1. **Test Edilebilirlik**: Singleton sınıfların test edilmesi zor olabilir
2. **Global State**: Global state kullanımı kodun anlaşılmasını zorlaştırabilir
3. **Bağımlılık**: Singleton kullanımı sınıflar arası bağımlılığı artırabilir

## Kullanım Alanları
- Veritabanı bağlantıları
- Logging sistemleri
- Konfigürasyon yönetimi
- Cache mekanizmaları
- WebDriver yönetimi (Selenium testlerinde)

## Örnek Kullanım Senaryoları
1. **WebDriver Yönetimi**:
```java
public class WebDriverManager {
    private static WebDriverManager instance;
    private WebDriver driver;
    
    private WebDriverManager() {
        // Private constructor
    }
    
    public static WebDriverManager getInstance() {
        if (instance == null) {
            instance = new WebDriverManager();
        }
        return instance;
    }
    
    public WebDriver getDriver() {
        if (driver == null) {
            driver = new ChromeDriver();
        }
        return driver;
    }
}
```

2. **Konfigürasyon Yönetimi**:
```java
public class ConfigurationManager {
    private static ConfigurationManager instance;
    private Properties properties;
    
    private ConfigurationManager() {
        loadProperties();
    }
    
    public static ConfigurationManager getInstance() {
        if (instance == null) {
            instance = new ConfigurationManager();
        }
        return instance;
    }
    
    private void loadProperties() {
        // Properties yükleme işlemleri
    }
}
```

## Thread-Safe Implementasyon
```java
public class ThreadSafeSingleton {
    private static volatile ThreadSafeSingleton instance;
    
    private ThreadSafeSingleton() {
        // Private constructor
    }
    
    public static ThreadSafeSingleton getInstance() {
        if (instance == null) {
            synchronized (ThreadSafeSingleton.class) {
                if (instance == null) {
                    instance = new ThreadSafeSingleton();
                }
            }
        }
        return instance;
    }
}
```

## Best Practices
1. **Lazy Initialization**: İhtiyaç duyulduğunda instance oluşturma
2. **Thread Safety**: Çoklu thread ortamında güvenli çalışma
3. **Serialization**: Serileştirme desteği
4. **Cloning**: Clone işlemlerini engelleme
5. **Reflection**: Reflection ile instance oluşturulmasını engelleme

## Dikkat Edilmesi Gerekenler
1. Singleton kullanımı gerçekten gerekli mi?
2. Thread-safe implementasyon yapıldı mı?
3. Test edilebilirlik göz önünde bulunduruldu mu?
4. Bağımlılık enjeksiyonu alternatif olarak düşünüldü mü?

## Kaynaklar
- [Design Patterns: Elements of Reusable Object-Oriented Software](https://www.amazon.com/Design-Patterns-Elements-Reusable-Object-Oriented/dp/0201633612)
- [Head First Design Patterns](https://www.amazon.com/Head-First-Design-Patterns-Brain-Friendly/dp/149207800X)
- [Refactoring Guru - Singleton Pattern](https://refactoring.guru/design-patterns/singleton) 
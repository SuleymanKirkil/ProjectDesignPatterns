package design.patterns.factory;

// WebDriverFactory oluşturan sınıf
public class WebDriverFactoryProvider {
    
    // Tarayıcı tipine göre uygun factory'yi döndürür
    public static WebDriverFactory getFactory(String browserType) {
        // Tarayıcı tipine göre factory oluştur
        if (browserType.equalsIgnoreCase("chrome")) {
            return new ChromeDriverFactory();
        } else if (browserType.equalsIgnoreCase("firefox")) {
            return new FirefoxDriverFactory();
        } else {
            // Desteklenmeyen tarayıcı için hata fırlat
            throw new IllegalArgumentException("Desteklenmeyen tarayıcı: " + browserType);
        }
    }
    
    public static void resetFactory() {
        // Bu metodun içeriği kaldırıldı, çünkü bu sınıfın amacı
        // sadece WebDriverFactory'yi oluşturmaktır ve bu işlemi tekrarlamak için
        // WebDriverFactoryProvider'ın kullanılması gerekmektedir.
    }
} 
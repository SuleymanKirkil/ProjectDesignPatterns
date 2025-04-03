# Page Object Model'de Test Mantığı ve Page Sınıfları Dengesi

## 1. Giriş
Page Object Model'de en önemli prensiplerden biri, test mantığı ile sayfa işlemlerinin doğru şekilde ayrılmasıdır. Bu ayrım, kodun bakımını kolaylaştırır ve test otomasyonunun sürdürülebilirliğini artırır.

## 2. Yanlış ve Doğru Yaklaşımlar

### 2.1 Yanlış Yaklaşım
```java
public class LoginPage {
    // ❌ YANLIŞ: Test mantığı page sınıfında
    public void verifyLoginWithCorrectCredentials() {
        enterUsername("testuser");
        enterPassword("password123");
        clickLoginButton();
        Assert.assertTrue(dashboardPage.isDisplayed());
    }
    
    // ❌ YANLIŞ: Assertion'lar page sınıfında
    public void verifyLoginErrorMessage() {
        Assert.assertEquals(getErrorMessage(), "Invalid credentials");
    }
}
```

### 2.2 Doğru Yaklaşım
```java
public class LoginPage {
    // ✅ DOĞRU: Sadece sayfa işlemleri
    public void enterCredentials(String username, String password) {
        usernameField.sendKeys(username);
        passwordField.sendKeys(password);
    }
    
    public void clickLogin() {
        loginButton.click();
    }
    
    // ✅ DOĞRU: Sayfa durumu bilgisi
    public boolean isLoginButtonEnabled() {
        return loginButton.isEnabled();
    }
    
    public String getErrorMessage() {
        return errorMessage.getText();
    }
}

public class LoginTest {
    @Test
    public void testSuccessfulLogin() {
        // ✅ DOĞRU: Test mantığı test sınıfında
        loginPage.enterCredentials("testuser", "password123");
        loginPage.clickLogin();
        
        // Assertion'lar test sınıfında
        Assert.assertTrue(dashboardPage.isDisplayed());
    }
}
```

## 3. Denge Nasıl Olmalı?

### 3.1 Page Sınıflarında Olması Gerekenler
- Web element işlemleri (click, sendKeys, vs.)
- Sayfa durumu bilgileri (isDisplayed, isEnabled, getText)
- Sayfa navigasyon metodları
- Sayfa yükleme kontrolleri

### 3.2 Test Sınıflarında Olması Gerekenler
- Test senaryoları
- Assertion'lar
- Test mantığı
- Veri doğrulama

### 3.3 Örnek Denge
```java
// Page sınıfı - Sadece sayfa işlemleri
public class ProductPage {
    public void addToCart() {
        addToCartButton.click();
    }
    
    public String getProductName() {
        return productName.getText();
    }
    
    public boolean isAddToCartEnabled() {
        return addToCartButton.isEnabled();
    }
}

// Test sınıfı - Test mantığı ve doğrulamalar
public class ProductTest {
    @Test
    public void testAddToCart() {
        productPage.addToCart();
        Assert.assertTrue(cartPage.isProductAdded(productName));
    }
}
```

## 4. Bu Denge Neden Önemli?

### 4.1 Single Responsibility Principle
- Page sınıfları sadece sayfa işlemlerinden sorumlu olmalı
- Test sınıfları test mantığından sorumlu olmalı

### 4.2 Bakım Kolaylığı
- UI değişikliklerinde sadece page sınıfları güncellenir
- Test mantığı değişikliklerinde sadece test sınıfları güncellenir

### 4.3 Yeniden Kullanılabilirlik
- Page sınıfları farklı test senaryolarında kullanılabilir
- Test mantığı bağımsız olarak değiştirilebilir

### 4.4 Okunabilirlik
- Kod daha organize ve anlaşılır olur
- Test senaryoları daha net görünür

## 5. Best Practices

### 5.1 Page Sınıfları İçin
1. Sadece sayfa işlemlerini içer
2. Web element işlemlerini kapsülle
3. Sayfa durumu bilgilerini sağla
4. Karmaşık işlemleri basit metodlara böl

### 5.2 Test Sınıfları İçin
1. Test mantığını test sınıflarında tut
2. Assertion'ları test sınıflarında yap
3. Test verilerini test sınıflarında yönet
4. Test senaryolarını açık ve anlaşılır yaz

## 6. Örnek Senaryo

### 6.1 Page Sınıfı
```java
public class CheckoutPage {
    private final WebDriver driver;
    
    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
    }
    
    public void enterShippingAddress(String address) {
        shippingAddressField.sendKeys(address);
    }
    
    public void selectPaymentMethod(String method) {
        paymentMethodDropdown.selectByVisibleText(method);
    }
    
    public boolean isProceedButtonEnabled() {
        return proceedButton.isEnabled();
    }
    
    public void clickProceed() {
        proceedButton.click();
    }
}
```

### 6.2 Test Sınıfı
```java
public class CheckoutTest {
    @Test
    public void testCompleteCheckout() {
        // Test mantığı ve veri
        String testAddress = "123 Test Street";
        String paymentMethod = "Credit Card";
        
        // Sayfa işlemleri
        checkoutPage.enterShippingAddress(testAddress);
        checkoutPage.selectPaymentMethod(paymentMethod);
        
        // Doğrulamalar
        Assert.assertTrue(checkoutPage.isProceedButtonEnabled());
        checkoutPage.clickProceed();
        Assert.assertTrue(orderConfirmationPage.isDisplayed());
    }
}
```

Bu dengeyi korumak, test otomasyonunun sürdürülebilirliği ve bakımı için çok önemlidir. Doğru ayrım yapıldığında, kod daha organize, anlaşılır ve bakımı kolay hale gelir. 
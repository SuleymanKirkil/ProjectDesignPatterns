package design.patterns.factory;

import org.openqa.selenium.WebDriver;

// WebDriver oluşturmak için temel arayüz
public interface WebDriverFactory {
    // WebDriver oluşturma metodu
    WebDriver createDriver();
} 
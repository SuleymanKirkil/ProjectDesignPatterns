package design.patterns.pom.tests;

import design.patterns.pom.pages.RadioButtonPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class RadioButtonTest {
    private WebDriver driver;
    private RadioButtonPage radioButtonPage;
    protected final String BASE_URL = "http://test.rubywatir.com/radios.php";

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        radioButtonPage = new RadioButtonPage(driver);
        driver.get(BASE_URL);
    }

    @Test
    public void testInitialRadioButtonStates() {
        System.out.println("Başlangıç durumu kontrol ediliyor...");
        
        // Toplam radio button sayısını kontrol et
        assertEquals(radioButtonPage.getTotalRadioButtonCount(), 6, 
            "Radio button sayısı yanlış");
        
        // İlk radio button'un seçili olduğunu kontrol et
        assertTrue(radioButtonPage.isFirstRadioButtonSelected(), 
            "İlk radio button seçili değil");
        
        // Diğerlerinin seçili olmadığını kontrol et
        assertTrue(radioButtonPage.areOtherRadioButtonsUnselected(), 
            "Diğer radio buttonlar seçili olmamalı");
    }

    @Test
    public void testSelectEachRadioButton() {
        System.out.println("Her radio button sırayla seçiliyor...");
        
        int totalButtons = radioButtonPage.getTotalRadioButtonCount();
        
        for (int i = 0; i < totalButtons; i++) {
            // Radio button'u seç
            radioButtonPage.selectRadioButton(i);
            
            // Seçilen radio button'un seçili olduğunu doğrula
            assertTrue(radioButtonPage.isRadioButtonSelected(i), 
                "Radio button " + (i+1) + " seçilemedi");
            
            // Diğerlerinin seçili olmadığını kontrol et
            assertTrue(radioButtonPage.areOtherRadioButtonsUnselected(i), 
                "Diğer radio buttonlar hala seçili");
            
            // Kısa bekleme
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void testSpecificRadioButtons() {
        System.out.println("Özel radio buttonlar test ediliyor...");
        
        // Class ile radio button seçimi
        radioButtonPage.selectRadioWithClass();
        assertTrue(radioButtonPage.isRadioWithClassSelected(), 
            "Class'a sahip radio button seçilemedi");
        
        // ID ile radio button seçimi
        radioButtonPage.selectRadioWithId();
        assertTrue(radioButtonPage.isRadioWithIdSelected(), 
            "ID'ye sahip radio button seçilemedi");
        
        // Name ile radio button seçimi
        radioButtonPage.selectRadioWithName();
        assertTrue(radioButtonPage.isRadioWithNameSelected(), 
            "Name'e sahip radio button seçilemedi");
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            System.out.println("Test tamamlandı, browser kapatıldı.");
        }
    }
} 
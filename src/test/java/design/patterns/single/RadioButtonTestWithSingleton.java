package design.patterns.single;

import design.patterns.pom.pages.RadioButtonPage;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

public class RadioButtonTestWithSingleton {
    private RadioButtonPage radioButtonPage;
    private WebDriverManager driverManager;
    
    @BeforeMethod
    public void setUp() {
        driverManager = WebDriverManager.getInstance();
        driverManager.navigateToBaseUrl();
        radioButtonPage = new RadioButtonPage(driverManager.getDriver());
    }
    
    @Test
    public void testInitialRadioButtonStates() {
        System.out.println("Başlangıç durumu kontrol ediliyor...");
        
        assertEquals(radioButtonPage.getTotalRadioButtonCount(), 6, 
            "Radio button sayısı yanlış");
        
        assertTrue(radioButtonPage.isFirstRadioButtonSelected(), 
            "İlk radio button seçili değil");
        
        assertTrue(radioButtonPage.areOtherRadioButtonsUnselected(), 
            "Diğer radio buttonlar seçili olmamalı");
    }
    
    @Test
    public void testSelectEachRadioButton() {
        System.out.println("Her radio button sırayla seçiliyor...");
        
        int totalButtons = radioButtonPage.getTotalRadioButtonCount();
        
        for (int i = 0; i < totalButtons; i++) {
            radioButtonPage.selectRadioButton(i);
            
            assertTrue(radioButtonPage.isRadioButtonSelected(i), 
                "Radio button " + (i+1) + " seçilemedi");
            
            assertTrue(radioButtonPage.areOtherRadioButtonsUnselected(i), 
                "Diğer radio buttonlar hala seçili");
            
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
        
        radioButtonPage.selectRadioWithClass();
        assertTrue(radioButtonPage.isRadioWithClassSelected(), 
            "Class'a sahip radio button seçilemedi");
        
        radioButtonPage.selectRadioWithId();
        assertTrue(radioButtonPage.isRadioWithIdSelected(), 
            "ID'ye sahip radio button seçilemedi");
        
        radioButtonPage.selectRadioWithName();
        assertTrue(radioButtonPage.isRadioWithNameSelected(), 
            "Name'e sahip radio button seçilemedi");
    }
    
    @AfterMethod
    public void tearDown() {
        driverManager.cleanup();
        System.out.println("Test tamamlandı, browser kapatıldı.");
    }
} 
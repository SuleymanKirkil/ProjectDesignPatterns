package design.patterns.pom.tests;

import design.patterns.pom.pages.CheckboxPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class CheckboxTest {
    private WebDriver driver;
    private CheckboxPage checkboxPage;
    protected final String BASE_URL = "http://test.rubywatir.com/checkboxes.php";


    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        checkboxPage = new CheckboxPage(driver);
        driver.get(BASE_URL);
    }

    @Test
    public void testDefaultCheckboxStates() {
        System.out.println("Varsayılan checkbox durumları kontrol ediliyor...");

        // Varsayılan olarak işaretli olması gereken checkbox'lar
        assertTrue(checkboxPage.isSoccerSelected(), "Soccer checkbox'ı işaretli değil");
        assertTrue(checkboxPage.isBasketballSelected(), "Basketball checkbox'ı işaretli değil");
        assertTrue(checkboxPage.isGolfSelected(), "Golf checkbox'ı işaretli değil");
        assertTrue(checkboxPage.isNetballSelected(), "Netball checkbox'ı işaretli değil");

        // Varsayılan olarak işaretsiz olması gereken checkbox'lar
        assertFalse(checkboxPage.isFootballSelected(), "Football checkbox'ı işaretli");
        assertFalse(checkboxPage.isBaseballSelected(), "Baseball checkbox'ı işaretli");
        assertFalse(checkboxPage.isSnookerSelected(), "Snooker checkbox'ı işaretli");
        assertFalse(checkboxPage.isRugbySelected(), "Rugby checkbox'ı işaretli");
    }

    @Test
    public void testCheckboxOperations() {
        System.out.println("Checkbox işlemleri test ediliyor...");

        // İşaretli olanları kaldır
        checkboxPage.uncheckAllSelected();
        
        // Kısa bir bekleme
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // İşaretsiz olanları işaretle
        checkboxPage.checkAllUnselected();
    }

    @Test
    public void testCheckboxClickability() {
        System.out.println("Checkbox'ların tıklanabilirliği test ediliyor...");
        assertTrue(checkboxPage.areAllCheckboxesClickable(), "Tüm checkbox'lar tıklanabilir değil");
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            System.out.println("Test tamamlandı, browser kapatıldı.");
        }
    }
} 
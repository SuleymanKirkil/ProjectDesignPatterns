package design.patterns.pom.tests;

import design.patterns.pom.pages.LinksPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

public class LinksTest {
    private WebDriver driver;
    private LinksPage linksPage;
    protected final String BASE_URL = "http://test.rubywatir.com/links.php";

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        linksPage = new LinksPage(driver);
        driver.get(BASE_URL);
    }

    @Test
    public void testTextLinks() {
        System.out.println("Text Link Testleri Başlıyor...");

        // Radios page linki
        assertTrue("Radios linki görünür değil", linksPage.isRadiosTextLinkDisplayed());
        linksPage.clickRadiosTextLink();
        linksPage.navigateBack();

        // Checkbox page linki
        assertTrue("Checkbox linki görünür değil", linksPage.isCheckboxTextLinkDisplayed());
        linksPage.clickCheckboxTextLink();
        linksPage.navigateBack();

        // Maths page linki
        assertTrue("Maths linki görünür değil", linksPage.isMathsTextLinkDisplayed());
        linksPage.clickMathsTextLink();
        linksPage.navigateBack();

        // RubyWatir site linki
        assertTrue("Ruby linki görünür değil", linksPage.isRubyTextLinkDisplayed());
        linksPage.clickRubyTextLink();
        linksPage.navigateBack();

        System.out.println("Text Link Testleri Tamamlandı.");
    }

    @Test
    public void testImageLinks() {
        System.out.println("Görsel Link Testleri Başlıyor...");

        // Radios resmi
        assertTrue("Radios resmi görünür değil", linksPage.isRadiosImageDisplayed());
        assertEquals("Resim genişliği %10 değil", "67", linksPage.getRadiosImageWidth());
        linksPage.clickRadiosImage();
        linksPage.navigateBack();

        // Checkbox resmi
        assertTrue("Checkbox resmi görünür değil", linksPage.isCheckboxImageDisplayed());
        assertEquals("Resim genişliği %10 değil", "67", linksPage.getCheckboxImageWidth());
        linksPage.clickCheckboxImage();
        linksPage.navigateBack();

        // Maths resmi
        assertTrue("Maths resmi görünür değil", linksPage.isMathsImageDisplayed());
        assertEquals("Resim genişliği %10 değil", "67", linksPage.getMathsImageWidth());
        linksPage.clickMathsImage();
        linksPage.navigateBack();

        // Ruby resmi
        assertTrue("Ruby resmi görünür değil", linksPage.isRubyImageDisplayed());
        assertEquals("Resim genişliği %10 değil", "67", linksPage.getRubyImageWidth());
        linksPage.clickRubyImage();
        linksPage.navigateBack();

        System.out.println("Görsel Link Testleri Tamamlandı.");
    }

    @Test
    public void testImageDimensions() {
        System.out.println("Resim Boyutları Kontrolü Başlıyor...");

        // Radios resmi
        assertEquals("Resim genişliği 67 değil", "67", linksPage.getRadiosImageWidth());
        assertEquals("Resim yüksekliği 66 değil", "66", linksPage.getRadiosImageHeight());
        assertTrue("Resim yüklenemedi", linksPage.isRadiosImageDisplayed());

        // Checkbox resmi
        assertEquals("Resim genişliği 67 değil", "67", linksPage.getCheckboxImageWidth());
        assertEquals("Resim yüksekliği 66 değil", "66", linksPage.getCheckboxImageHeight());
        assertTrue("Resim yüklenemedi", linksPage.isCheckboxImageDisplayed());

        // Maths resmi
        assertEquals("Resim genişliği 67 değil", "67", linksPage.getMathsImageWidth());
        assertEquals("Resim yüksekliği 66 değil", "66", linksPage.getMathsImageHeight());
        assertTrue("Resim yüklenemedi", linksPage.isMathsImageDisplayed());

        // Ruby resmi
        assertEquals("Resim genişliği 67 değil", "67", linksPage.getRubyImageWidth());
        assertEquals("Resim yüksekliği 66 değil", "66", linksPage.getRubyImageHeight());
        assertTrue("Resim yüklenemedi", linksPage.isRubyImageDisplayed());

        System.out.println("Resim Boyutları Kontrolü Tamamlandı.");
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            System.out.println("Test tamamlandı, browser kapatıldı.");
        }
    }
} 
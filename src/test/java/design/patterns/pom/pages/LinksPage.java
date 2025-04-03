package design.patterns.pom.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LinksPage extends BasePage {
    
    // Text Links
    @FindBy(linkText = "radios page")
    private WebElement radiosTextLink;
    
    @FindBy(linkText = "Checkbox page")
    private WebElement checkboxTextLink;
    
    @FindBy(linkText = "maths page")
    private WebElement mathsTextLink;
    
    @FindBy(linkText = "RubyWatir site")
    private WebElement rubyTextLink;
    
    // Image Links
    @FindBy(xpath = "//img[@src='images/radios.jpg']")
    private WebElement radiosImage;
    
    @FindBy(xpath = "//img[@src='images/checkbox.jpg']")
    private WebElement checkboxImage;
    
    @FindBy(xpath = "//img[@src='images/maths.jpg']")
    private WebElement mathsImage;
    
    @FindBy(xpath = "//img[@src='images/ruby.jpg']")
    private WebElement rubyImage;
    
    public LinksPage(WebDriver driver) {
        super(driver);
    }
    
    // Text Link Methods
    public void clickRadiosTextLink() {
        click(radiosTextLink, "Radios Text Link");
    }
    
    public void clickCheckboxTextLink() {
        click(checkboxTextLink, "Checkbox Text Link");
    }
    
    public void clickMathsTextLink() {
        click(mathsTextLink, "Maths Text Link");
    }
    
    public void clickRubyTextLink() {
        click(rubyTextLink, "Ruby Text Link");
    }
    
    // Image Link Methods
    public void clickRadiosImage() {
        click(radiosImage, "Radios Image");
    }
    
    public void clickCheckboxImage() {
        click(checkboxImage, "Checkbox Image");
    }
    
    public void clickMathsImage() {
        click(mathsImage, "Maths Image");
    }
    
    public void clickRubyImage() {
        click(rubyImage, "Ruby Image");
    }
    
    // Verification Methods
    public boolean isRadiosTextLinkDisplayed() {
        return isElementDisplayed(radiosTextLink, "Radios Text Link");
    }
    
    public boolean isCheckboxTextLinkDisplayed() {
        return isElementDisplayed(checkboxTextLink, "Checkbox Text Link");
    }
    
    public boolean isMathsTextLinkDisplayed() {
        return isElementDisplayed(mathsTextLink, "Maths Text Link");
    }
    
    public boolean isRubyTextLinkDisplayed() {
        return isElementDisplayed(rubyTextLink, "Ruby Text Link");
    }
    
    public boolean isRadiosImageDisplayed() {
        return isElementDisplayed(radiosImage, "Radios Image");
    }
    
    public boolean isCheckboxImageDisplayed() {
        return isElementDisplayed(checkboxImage, "Checkbox Image");
    }
    
    public boolean isMathsImageDisplayed() {
        return isElementDisplayed(mathsImage, "Maths Image");
    }
    
    public boolean isRubyImageDisplayed() {
        return isElementDisplayed(rubyImage, "Ruby Image");
    }
    
    // Image Dimension Methods
    public String getRadiosImageWidth() {
        return getAttribute(radiosImage, "width", "Radios Image");
    }
    
    public String getRadiosImageHeight() {
        return getAttribute(radiosImage, "height", "Radios Image");
    }
    
    public String getCheckboxImageWidth() {
        return getAttribute(checkboxImage, "width", "Checkbox Image");
    }
    
    public String getCheckboxImageHeight() {
        return getAttribute(checkboxImage, "height", "Checkbox Image");
    }
    
    public String getMathsImageWidth() {
        return getAttribute(mathsImage, "width", "Maths Image");
    }
    
    public String getMathsImageHeight() {
        return getAttribute(mathsImage, "height", "Maths Image");
    }
    
    public String getRubyImageWidth() {
        return getAttribute(rubyImage, "width", "Ruby Image");
    }
    
    public String getRubyImageHeight() {
        return getAttribute(rubyImage, "height", "Ruby Image");
    }
} 
package design.patterns.pom.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import java.util.List;

public class CheckboxPage extends BasePage {
    
    // Individual Checkboxes
    @FindBy(css = "input[value='soccer']")
    private WebElement soccerCheckbox;
    
    @FindBy(css = "input[value='basketball']")
    private WebElement basketballCheckbox;
    
    @FindBy(css = "input[value='golf']")
    private WebElement golfCheckbox;
    
    @FindBy(css = "input[value='netball']")
    private WebElement netballCheckbox;
    
    @FindBy(css = "input[value='football']")
    private WebElement footballCheckbox;
    
    @FindBy(css = "input[value='baseball']")
    private WebElement baseballCheckbox;
    
    @FindBy(css = "input[value='snooker']")
    private WebElement snookerCheckbox;
    
    @FindBy(css = "input[value='rugby']")
    private WebElement rugbyCheckbox;
    
    // All Checkboxes
    @FindBys(@FindBy(name = "sports"))
    private List<WebElement> allCheckboxes;
    
    public CheckboxPage(WebDriver driver) {
        super(driver);
    }
    
    // Individual Checkbox Methods
    public boolean isSoccerSelected() {
        return isElementSelected(soccerCheckbox, "Soccer Checkbox");
    }
    
    public boolean isBasketballSelected() {
        return isElementSelected(basketballCheckbox, "Basketball Checkbox");
    }
    
    public boolean isGolfSelected() {
        return isElementSelected(golfCheckbox, "Golf Checkbox");
    }
    
    public boolean isNetballSelected() {
        return isElementSelected(netballCheckbox, "Netball Checkbox");
    }
    
    public boolean isFootballSelected() {
        return isElementSelected(footballCheckbox, "Football Checkbox");
    }
    
    public boolean isBaseballSelected() {
        return isElementSelected(baseballCheckbox, "Baseball Checkbox");
    }
    
    public boolean isSnookerSelected() {
        return isElementSelected(snookerCheckbox, "Snooker Checkbox");
    }
    
    public boolean isRugbySelected() {
        return isElementSelected(rugbyCheckbox, "Rugby Checkbox");
    }
    
    // Checkbox Operations
    public void toggleCheckbox(WebElement checkbox, String checkboxName) {
        click(checkbox, checkboxName);
    }
    
    public void uncheckAllSelected() {
        for (WebElement checkbox : allCheckboxes) {
            if (isElementSelected(checkbox, "Checkbox")) {
                toggleCheckbox(checkbox, "Checkbox");
            }
        }
    }
    
    public void checkAllUnselected() {
        for (WebElement checkbox : allCheckboxes) {
            if (!isElementSelected(checkbox, "Checkbox")) {
                toggleCheckbox(checkbox, "Checkbox");
            }
        }
    }
    
    // Verification Methods
    public boolean areAllCheckboxesClickable() {
        for (WebElement checkbox : allCheckboxes) {
            if (!isElementEnabled(checkbox, "Checkbox")) {
                return false;
            }
        }
        return true;
    }
    
    public List<WebElement> getAllCheckboxes() {
        return allCheckboxes;
    }
} 
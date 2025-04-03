package design.patterns.pom.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import java.util.List;

public class RadioButtonPage extends BasePage {
    
    // All Radio Buttons
    @FindBys(@FindBy(name = "likeit"))
    private List<WebElement> allRadioButtons;
    
    // Specific Radio Buttons
    @FindBy(className = "radioclass")
    private WebElement radioWithClass;
    
    @FindBy(id = "radioId")
    private WebElement radioWithId;
    
    @FindBy(xpath = "//input[@type='radio' and @value='No']")
    private WebElement radioWithName;
    
    public RadioButtonPage(WebDriver driver) {
        super(driver);
    }
    
    // Initial State Methods
    public int getTotalRadioButtonCount() {
        return allRadioButtons.size();
    }
    
    public boolean isFirstRadioButtonSelected() {
        return isElementSelected(allRadioButtons.get(0), "First Radio Button");
    }
    
    public boolean areOtherRadioButtonsUnselected() {
        for (int i = 1; i < allRadioButtons.size(); i++) {
            if (isElementSelected(allRadioButtons.get(i), "Radio Button " + (i + 1))) {
                return false;
            }
        }
        return true;
    }
    
    // Radio Button Operations
    public void selectRadioButton(int index) {
        if (index >= 0 && index < allRadioButtons.size()) {
            click(allRadioButtons.get(index), "Radio Button " + (index + 1));
        }
    }
    
    public boolean isRadioButtonSelected(int index) {
        if (index >= 0 && index < allRadioButtons.size()) {
            return isElementSelected(allRadioButtons.get(index), "Radio Button " + (index + 1));
        }
        return false;
    }
    
    public boolean areOtherRadioButtonsUnselected(int selectedIndex) {
        for (int i = 0; i < allRadioButtons.size(); i++) {
            if (i != selectedIndex && isElementSelected(allRadioButtons.get(i), "Radio Button " + (i + 1))) {
                return false;
            }
        }
        return true;
    }
    
    // Specific Radio Button Methods
    public void selectRadioWithClass() {
        click(radioWithClass, "Radio with Class");
    }
    
    public boolean isRadioWithClassSelected() {
        return isElementSelected(radioWithClass, "Radio with Class");
    }
    
    public void selectRadioWithId() {
        click(radioWithId, "Radio with ID");
    }
    
    public boolean isRadioWithIdSelected() {
        return isElementSelected(radioWithId, "Radio with ID");
    }
    
    public void selectRadioWithName() {
        click(radioWithName, "Radio with Name");
    }
    
    public boolean isRadioWithNameSelected() {
        return isElementSelected(radioWithName, "Radio with Name");
    }
    
    // Getter for all radio buttons
    public List<WebElement> getAllRadioButtons() {
        return allRadioButtons;
    }
} 
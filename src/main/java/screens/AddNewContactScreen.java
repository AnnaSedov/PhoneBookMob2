package screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import models.Contact;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class AddNewContactScreen extends BaseScreen{

    public AddNewContactScreen(AppiumDriver<MobileElement> driver) {
        super(driver);
    }

    @FindBy(xpath = "//*[@resource-id='com.sheygam.contactapp:id/inputPhone']")
    MobileElement phoneField;


    @FindBy(xpath = "//*[@resource-id='com.sheygam.contactapp:id/inputName']")
    MobileElement nameField;
    @FindBy(xpath = "//*[@resource-id='com.sheygam.contactapp:id/inputLastName']")
    MobileElement lastNameField;
    @FindBy(xpath = "//*[@resource-id='com.sheygam.contactapp:id/inputEmail']")
    MobileElement emailField;
    @FindBy(xpath = "//*[@resource-id='com.sheygam.contactapp:id/inputAddress']")
    MobileElement addressField;
    @FindBy(xpath = "//*[@resource-id='com.sheygam.contactapp:id/inputDesc']")
    MobileElement descriptionField;
    @FindBy(xpath = "//*[@resource-id='com.sheygam.contactapp:id/createBtn']")
    MobileElement createButton;
    public AddNewContactScreen fillTheForm(Contact contact) {
        nameField.sendKeys(contact.getName());
        lastNameField.sendKeys(contact.getLastName());
        if(contact.getPhone().length()>=10&&contact.getPhone().length()<=15) {
            phoneField.sendKeys(contact.getPhone());
        }else{
            throw new IllegalArgumentException("The phone number length is small or big");
        }
        emailField.sendKeys(contact.getEmail());
        addressField.sendKeys(contact.getAddress());
        descriptionField.sendKeys(contact.getDescriptions());
        return this;

    }

    public <T extends BaseScreen> T createContact(){
        waitForAnElement(createButton);
        createButton.click();
        List<MobileElement> list=driver.findElements(By.xpath("//*[@resource-id='android:id/alertTitle']"));
        if(list.size()>0){
            driver.findElement(By.xpath("//*[@resource-id='android:id/button1']")).click();
            return (T) new AddNewContactScreen(driver);
        }
        return (T) new ContactListScreen(driver);

    }

}

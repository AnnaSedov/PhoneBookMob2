package screens;

import helpers.PropertiesWriterXML;
import helpers.TestHelpers;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class EditContactScreen extends BaseScreen implements TestHelpers {

    public EditContactScreen(AppiumDriver<MobileElement> driver) {
        super(driver);
    }
//com.sheygam.contactapp:id/inputName
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
    @FindBy(xpath = "//*[@resource-id='com.sheygam.contactapp:id/updateBtn']")
    MobileElement updateButton;

public EditContactScreen editFormEmail(String newEmail){
    String oldMail=emailField.getText();
    emailField.sendKeys(newEmail);
    System.out.println("NewMail "+newEmail+" oldMail "+oldMail);
    PropertiesWriterXML propertiesWriterXML=new PropertiesWriterXML(FILE_PATH);
    propertiesWriterXML.setProperties(NEWMAIL,newEmail,false);
    propertiesWriterXML.setProperties(OLDMAIL,oldMail,false);
    return this;
}

    public <T extends BaseScreen> T updateContact(){
        waitForAnElement(updateButton);
        updateButton.click();
        List<MobileElement> list=driver.findElements(By.xpath("//*[@resource-id='android:id/alertTitle']"));
        if(list.size()>0){
            driver.findElement(By.xpath("//*[@resource-id='android:id/button1']")).click();
            return (T) new EditContactScreen(driver);
        }
        return (T) new ContactListScreen(driver);

    }


}

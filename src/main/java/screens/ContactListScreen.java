package screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import models.Contact;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.util.List;

public class ContactListScreen extends BaseScreen{

    public ContactListScreen(AppiumDriver<MobileElement> driver) {
        super(driver);

    }
    @FindBy(xpath = "//*[@resource-id='com.sheygam.contactapp:id/action_bar']/android.widget.TextView")
    MobileElement titleText;
    @FindBy(xpath  ="//*[@content-desc='More options']")
    MobileElement moreOptions;
    @FindBy(xpath = "//*[@resource-id='com.sheygam.contactapp:id/title']")
    MobileElement logoutButton;
    @FindBy(xpath = "//*[@resource-id='com.sheygam.contactapp:id/add_contact_btn']")
    MobileElement addButton;
//  

    public AuthenticationScreen logOut(){
        moreOptions.click();
        logoutButton.click();
        return new AuthenticationScreen(driver);

    }
    public boolean isContactListPresent(){
         return isElementPresent(titleText,"Contact list");
    }
    public AddNewContactScreen openNewContactForm(){
        waitForAnElement(addButton);
        addButton.click();
        return new AddNewContactScreen(driver);
    }


    public boolean isContactAdded(Contact contact) {

List<MobileElement> containers=driver.findElementsByClassName("android.widget.TextView");//android.widget.TextView
        if(containers.size()>0) {
            for (MobileElement container : containers) {
                System.out.println(container.getText()+"***"+contact.getName()+" "+contact.getLastName());
                if(container.getText().equals(contact.getName()+" "+contact.getLastName())){
                    return true;
                }
            }
        }



        //  List<MobileElement> containers=driver.findElements(By.xpath("//*[@resource-id='com.sheygam.contactapp:id/rowContainer']"));
//        if(containers.size()>0){
//for(MobileElement container:containers){
//
//
//       waitForAnElement(container);
//        container.click();
//        MobileElement elementName=driver.findElement(By.xpath("//*[@resource-id='com.sheygam.contactapp:id/nameTxt']"));
//        String elementNameVal=elementName.getAttribute("value");
//          MobileElement elementLastName=driver.findElement(By.xpath("//[*@resource-id='com.sheygam.contactapp:id/lastNameTxt']"));
//          String elementLastNameVal=elementLastName.getAttribute("value");
//          MobileElement elementPhone=driver.findElement(By.xpath("//*[@resource-id='com.sheygam.contactapp:id/phoneTxt']"));
//          String elementPhoneVal=elementPhone.getAttribute("value");
//          MobileElement elementAddress=driver.findElement(By.xpath("//*[@resource-id='com.sheygam.contactapp:id/addressTxt']"));
//          String elementAddressVal=elementName.getAttribute("value");
//          MobileElement elementEmail=driver.findElement(By.xpath("//*[@resource-id='com.sheygam.contactapp:id/emailTxt']"));
//          String elementEmailVal=elementName.getAttribute("value");
//          MobileElement elementDescription=driver.findElement(By.xpath("//*[@resource-id='com.sheygam.contactapp:id/descTxt']"));
//          String elementDescriptionVal=elementName.getAttribute("value");
//          Contact listContact= new Contact();
//          listContact.setName(elementNameVal);
//          listContact.setLastName(elementLastNameVal);
//          listContact.setAddress(elementAddressVal);
//          listContact.setPhone(elementPhoneVal);
//          listContact.setDescriptions(elementDescriptionVal);
//          listContact.setEmail(elementEmailVal);
//          return listContact.equals(contact);}}

        return false;

    }
}

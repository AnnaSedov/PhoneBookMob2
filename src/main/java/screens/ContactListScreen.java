package screens;

import helpers.PropertiesReaderXML;
import helpers.TestHelpers;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.offset.PointOption;
import models.Contact;
import org.openqa.selenium.By;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.util.List;

import static helpers.TestHelpers.OLDMAIL;

public class ContactListScreen extends BaseScreen implements TestHelpers {

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
    @FindBy(xpath = "//*[@resource-id='com.sheygam.contactapp:id/rowContainer']")
    List<MobileElement> contacts;
    @FindBy(xpath = "//*[@resource-id='android:id/button1']")
    MobileElement yesButton;
    @FindBy(xpath = "//*[@resource-id='com.sheygam.contactapp:id/rowPhone']")
            List<MobileElement> rowPhone;
    @FindBy(xpath = "//*[@resource-id='com.sheygam.contactapp:id/rowName']")
    List<MobileElement> rowName;

    @FindBy(xpath = "//*[@resource-id='com.sheygam.contactapp:id/emptyTxt']")
            MobileElement emptyTXT;

    @FindBy(xpath = "//*[@resource-id='com.sheygam.contactapp:id/updateTxt']")
    MobileElement updateTXT;

    String phoneNumber;
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
    public ContactListScreen removeAContact(){
        waitForAnElement(addButton);
        //delete first contact from  contacts list
        MobileElement contact=contacts.get(0);
        phoneNumber=rowPhone.get(0).getText();
        Rectangle rectangle=contact.getRect();
        int xStart=  rectangle.getX()+rectangle.getWidth()/8;
        int y=rectangle.getY()+rectangle.getHeight()/2;
        int xEnd= xStart+rectangle.getWidth()*6/8;
        new TouchAction<>(driver).longPress(PointOption.point(xStart,y)).moveTo(PointOption.point(xEnd,y)).release().perform();
        if(isElementPresent(yesButton,"YES")){
            yesButton.click();

        }
        return this;
    }

    public boolean isContactRemoved() {
       return !rowPhone.contains(phoneNumber);
    }
    public ContactListScreen removeAllContacts(){
        waitForAnElement(addButton);
        while (contacts.size()>0){
            removeAContact();
        }
        return this;
    }

    public boolean isNoContactMess() {
       return isElementPresent(emptyTXT,"No Contacts");
    }

    public EditContactScreen editFirstContact() {
        waitForAnElement(addButton);
        MobileElement contact=contacts.get(0);
        Rectangle rectangle=contact.getRect();
        int xStart=rectangle.getWidth()-10;
        int y=rectangle.getY()+rectangle.getHeight()/2;
        int xEnd=xStart-rectangle.getWidth()*6/8;
        new TouchAction<>(driver).longPress(PointOption.point(xStart,y)).moveTo(PointOption.point(xEnd,y)).release().perform();

        return new EditContactScreen(driver);
    }
    public boolean isContactEdit() {
        return !PropertiesReaderXML.getProperty(OLDMAIL).equals(PropertiesReaderXML.getProperty(NEWMAIL));
    }
}

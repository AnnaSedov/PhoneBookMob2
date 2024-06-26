package tests;

import config.AppiumConfig;
import helpers.*;
import models.Contact;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import screens.ContactListScreen;
import screens.SplashScreen;

public class RemoveContactTest extends AppiumConfig implements TestHelpers {

   @BeforeTest
   public void precondition(){
       ContactListScreen contactListScreen=new SplashScreen(driver).swithchToAuthScreen().fillEmailField(PropertiesReaderXML.getProperty(EMAIL)).fillPasswordField(PropertiesReaderXML.getProperty(PASSWORD)).clickByLoginButton();

   }
    @Test
    public void removeContact(){

        Contact contact=new Contact(NameAndLastNameGenerator.generateName(),
                NameAndLastNameGenerator.generateLastName(), EmailGenerator.generateEmail(2,3,2),
                AddressGenerator.generateAddress(),
                PhoneNumberGenerator.generatePhoneNumber(),"222");
        ContactListScreen contactListScreen=new  ContactListScreen(driver).openNewContactForm().fillTheForm(contact).createContact();//.isContactAdded(contact);

       Assert.assertTrue( contactListScreen.removeAContact().isContactRemoved());

    }
    @Test
    public void removeAllContacts(){
        ContactListScreen contactListScreen=new SplashScreen(driver).swithchToAuthScreen().fillEmailField(PropertiesReaderXML.getProperty(EMAIL)).fillPasswordField(PropertiesReaderXML.getProperty(PASSWORD)).clickByLoginButton();
      Assert.assertTrue( contactListScreen.removeAllContacts().isNoContactMess());
    }
}

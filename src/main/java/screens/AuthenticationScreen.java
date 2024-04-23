package screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class AuthenticationScreen extends BaseScreen{
    public  AuthenticationScreen(AppiumDriver<MobileElement> driver){
        super(driver);





    }
    //  @FindBy(xpath = ="//*@resource-id='com.sheygam.contactapp'")
    @FindBy(xpath = "//*[@resource-id='com.sheygam.contactapp:id/action_bar/android.widget.TextView']")
    MobileElement titleText;
    @FindBy(id="com.sheygam.contactapp:id/inputEmail")
    MobileElement inputEmailField;
    @FindBy(id="com.sheygam.contactapp:id/inputPassword")
    MobileElement inputPasswordField;
    @FindBy(id="com.sheygam.contactapp:id/registrationBtn")
    MobileElement regButton;
    @FindBy(id="com.sheygam.contactapp:id/loginBtn")
    MobileElement loginButton;
    @FindBy(id="android:id/message")
    MobileElement errorText;

    public AuthenticationScreen fillEmailField(String email){
        waitForAnElement(inputEmailField);//for first field usually
        inputEmailField.sendKeys(email);
        return this;
    }
    public AuthenticationScreen fillPasswordField(String password){
        inputPasswordField.sendKeys(password);
        return this;
    }
    public <T extends BaseScreen> T clickByLoginButton(){
        loginButton.click();
       // List<MobileElement> list= driver.findElement(By.xpath("//*[@resource-id='android:id/alertTitle']"));
        List<MobileElement> list = driver.findElements(By.xpath("//*[@resource-id='android:id/alertTitle']"));
        if(list.size()>0){
            driver.findElement(By.xpath("//*[resource-id='android:id/button1']")).click();
            return (T) new AuthenticationScreen(driver);
        }
        return (T) new ContactListScreen(driver);
    }
    public boolean isItAuthScreen(){
       return titleText.isDisplayed();
    }
    public <T extends BaseScreen> T clickByRegistrationButton(){
        regButton.click();
        List<MobileElement> list=driver.findElements(By.id("android:id/alertTitle"));
        if(list.size()>0){
            driver.findElement(By.id("android:id/button1")).click();
            return (T) new AuthenticationScreen(driver);
        }else return (T) new ContactListScreen(driver);
    }



}

package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ContactUsPage extends BasePage{
    @FindBy(css = "input[placeholder='Name']")
    private WebElement sendName;

    @FindBy(css = "input[placeholder='Email']")
    private WebElement sendEmail;

    @FindBy(css = "input[placeholder='Subject']")
    private WebElement sendSubject;

    @FindBy(css = "#message")
    private WebElement sendMessage;

    @FindBy(css = "input[name='upload_file']")
    private WebElement sendFile;

    @FindBy(css = "input[value='Submit']")
    private WebElement submit;

    public void setSendName(String name){
        sendName.sendKeys(name);
    }
    public void setSendEmail(String email){
        sendEmail.sendKeys(email);
    }
    public void setSendSubject(String subject){
        sendSubject.sendKeys(subject);
    }
    public void setSendMessage(String message){
        sendMessage.sendKeys(message);
    }
    public void setSendFile(String path){
        sendFile.sendKeys(path);
    }
    public void setSubmit(){
        submit.click();
    }

}

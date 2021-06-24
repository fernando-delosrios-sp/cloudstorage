package com.udacity.jwdnd.course1.cloudstorage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SignupPage {
    public SignupPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }
    
    @FindBy(id = "inputUsername")
    private WebElement username;

    @FindBy(id = "inputFirstname")
    private WebElement firstname;

    @FindBy(id = "inputLastname")
    private WebElement lastname;

    @FindBy(id = "inputPassword")
    private WebElement password;

    @FindBy(id = "submit-button")
    private WebElement submit;

    @FindBy(id = "error-msg")
    private WebElement error;

    @FindBy(id = "success-msg")
    private WebElement success;

    public void Signup(String username, String password, String firstname, String lastname) {
        this.username.sendKeys(username);
        this.password.sendKeys(password);
        this.firstname.sendKeys(firstname);
        this.lastname.sendKeys(lastname);
        this.submit.click();
    }
}

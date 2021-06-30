package com.udacity.jwdnd.course1.cloudstorage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {
    private WebDriver driver;
    private WebDriverWait wait;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 10);
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "credentialTable")
    private WebElement credentialTable;

    @FindBy(id = "credential-submit-button")
    private WebElement credentialSubmitButton;
    
    @FindBy(id = "credential-url")
    private WebElement credentialUrl;

    @FindBy(id = "credential-username")
    private WebElement credentialUsername;

    @FindBy(id = "credential-password")
    private WebElement credentialPassword;

    @FindBy(id = "note-description")
    private WebElement noteDescription;

    @FindBy(id = "note-title")
    private WebElement noteTitle;

    @FindBy(id = "noteTable")
    private WebElement noteTable;

    @FindBy(id = "note-submit-button")
    private WebElement noteSubmitButton;

    @FindBy(id = "new-credential-button")
    private WebElement newCredentialButton;

    @FindBy(id = "new-note-button")
    private WebElement newNoteButton;

    @FindBy(id = "nav-files-tab")
    private WebElement navFilesTab;

    @FindBy(id = "nav-notes-tab")
    private WebElement navNotesTab;

    @FindBy(id = "nav-credentials-tab")
    private WebElement navCredentialsTab;

    @FindBy(id = "logout-button")
    private WebElement logoutButton;

    public void logout() {
        this.logoutButton.click();
    }

    public void switchSection(String section) {
        switch (section) {
            case "files":
                navFilesTab.click();
                break;
            case "notes":
                navNotesTab.click();
                break;
            case "credentials":
                navCredentialsTab.click();
                break;
        }
    }

    private void click(WebElement element) {
        element = wait.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
    }

    public void createNote(String title, String description) {
        click(newNoteButton);
        noteTitle = wait.until(ExpectedConditions.elementToBeClickable(noteTitle));
        noteTitle.sendKeys(title);
        noteDescription.sendKeys(description);
        click(noteSubmitButton);
    }

    public String findNoteId(String title, String description) {
        try {
            WebElement note = wait.until(noteTable -> noteTable.findElement(By.className("note")));
            //WebElement note = noteTable.findElement(By.className("note"));
            if (title.equals(note.findElement(By.className("note-title")).getText()) &&
                description.equals(note.findElement(By.className("note-description")).getText())) {
                    return note.getAttribute("id");
                } else {
                    return null;
                }
        } catch (Exception e) {
            return null;
        }
    }

    public void editNote(String title, String description) {
        WebElement note = noteTable.findElement(By.className("note"));
        WebElement edit = note.findElement(By.className("btn-success"));
        click(edit);
        noteTitle = wait.until(ExpectedConditions.elementToBeClickable(noteTitle));
        noteTitle.clear();
        noteTitle.sendKeys(title);
        noteDescription.clear();
        noteDescription.sendKeys(description);
        click(noteSubmitButton);
    }

    public void deleteNote() {
        WebElement note = wait.until(noteTable -> noteTable.findElement(By.className("note")));
        WebElement delete = note.findElement(By.className("btn-danger"));
        click(delete);
    }

    public void createCredential(String url, String userName, String password) {
        click(newCredentialButton);
        credentialUrl = wait.until(ExpectedConditions.elementToBeClickable(credentialUrl));
        credentialUrl.sendKeys(url);
        credentialUsername.sendKeys(userName);
        credentialPassword.sendKeys(password);
        click(credentialSubmitButton);
    }

    public String findCredentialId(String url, String userName) {
        try {
            WebElement credential = credentialTable.findElement(By.className("credential"));
            if (url.equals(credential.findElement(By.className("credential-url")).getText()) &&
                userName.equals(credential.findElement(By.className("credential-username")).getText())) {
                    return credential.getAttribute("id");
                } else {
                    return null;
                }
        } catch (Exception e) {
            return null;
        }
    }

    public String geHashedPassword(String credentialId) {
        WebElement credential = credentialTable.findElement(By.className("credential"));
        return credential.findElement(By.className("credential-hashedpassword")).getText();
    }

    public void editCredential(String url, String userName, String password) {
        WebElement credential = credentialTable.findElement(By.className("credential"));
        WebElement edit = credential.findElement(By.className("btn-success"));
        click(edit);
        credentialUrl = wait.until(ExpectedConditions.elementToBeClickable(credentialUrl));
        credentialUrl.clear();
        credentialUrl.sendKeys(url);
        credentialUsername.clear();
        credentialUsername.sendKeys(userName);
        credentialPassword.clear();
        credentialPassword.sendKeys(password);
        click(credentialSubmitButton);
    }

    public void deleteCredential() {
        WebElement credential = wait.until(noteTable -> noteTable.findElement(By.className("credential")));
        WebElement delete = credential.findElement(By.className("btn-danger"));
        click(delete);
    }
}

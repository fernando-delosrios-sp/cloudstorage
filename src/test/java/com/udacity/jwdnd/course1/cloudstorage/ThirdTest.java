package com.udacity.jwdnd.course1.cloudstorage;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.annotation.Description;

@Description("Tests for Credential Creation, Viewing, Editing, and Deletion")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ThirdTest {	
	private LoginPage loginPage;
	private HomePage homePage;

	@LocalServerPort
	protected int port;

	protected WebDriver driver;

	protected void loadPage(String path) {
		String url = "http://localhost:" + port + path;
		driver.get(url);
	}

	@BeforeAll
	static void beforeAll() {
		WebDriverManager.chromedriver().setup();
	}

	@BeforeEach
	public void beforeEach() {
		this.driver = new ChromeDriver();
		loadPage("/login");
		loginPage = new LoginPage(driver);
		loginPage.login("fdelosrios", "pass");
	}

	@AfterEach
	public void afterEach() {
		if (this.driver != null) {
			driver.quit();
		}
	}

	@Test
	@Order(1)
	@DisplayName("Test that creates a set of credentials, verifies that they are displayed, and verifies that the displayed password is encrypted")
	public void one() {
		String url = "https://udacity.com";
		String userName = "fdelosrios";
		String password = "pass";

		homePage = new HomePage(driver);
		homePage.switchSection("credentials");
		homePage.createCredential(url, userName, password);
		String credentialId = homePage.findCredentialId(url, userName);
		Assertions.assertNotNull(credentialId);
		Assertions.assertFalse(password == homePage.geHashedPassword(credentialId));
	}

	@Test
	@Order(2)
	@DisplayName("Test that views an existing set of credentials, verifies that the viewable password is unencrypted, edits the credentials, and verifies that the changes are displayed")
	public void two() {
		String newUrl = "https://www.udacity.com";
		String newUserName = "fernando.delosrios";
		String newPassword = "newpass";

		homePage = new HomePage(driver);
		homePage.switchSection("credentials");
		homePage.editCredential(newUrl, newUserName, newPassword);
		Assertions.assertNotNull(homePage.findCredentialId(newUrl, newUserName));
	}

	@Test
	@Order(3)
	@DisplayName("Test that deletes an existing set of credentials and verifies that the credentials are no longer displayed")
	public void three() {
		String url = "https://www.udacity.com";
		String userName = "fernando.delosrios";

		homePage = new HomePage(driver);
		homePage.switchSection("credentials");
		homePage.deleteCredential();
		Assertions.assertNull(homePage.findCredentialId(url, userName));
	}
}

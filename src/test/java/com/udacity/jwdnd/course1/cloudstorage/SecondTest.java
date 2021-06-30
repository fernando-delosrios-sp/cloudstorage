package com.udacity.jwdnd.course1.cloudstorage;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.annotation.Description;

@Description("Tests for Note Creation, Viewing, Editing, and Deletion")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SecondTest {	
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
	@DisplayName("Creates a note, and verifies it is displayed")
	public void one() {
		String title = "Note title";
		String description = "Note description";

		homePage = new HomePage(driver);
		homePage.switchSection("notes");
		homePage.createNote(title, description);
		Assertions.assertNotNull(homePage.findNoteId(title, description));
	}

	@Test
	@Order(2)
	@DisplayName("Edits an existing note and verifies that the changes are displayed")
	public void two() {
		String title = "New note title";
		String description = "New note description";

		homePage = new HomePage(driver);
		homePage.switchSection("notes");
		homePage.editNote(title, description);
		Assertions.assertNotNull(homePage.findNoteId(title, description));
	}

	@Test
	@Order(3)
	@DisplayName("Deletes a note and verifies that the note is no longer displayed")
	public void three() {
		String title = "New note title";
		String description = "New note description";

		homePage = new HomePage(driver);
		homePage.switchSection("notes");
		homePage.deleteNote();
		Assertions.assertNull(homePage.findNoteId(title, description));
	}
}

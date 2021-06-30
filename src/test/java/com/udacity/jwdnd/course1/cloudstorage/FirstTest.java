package com.udacity.jwdnd.course1.cloudstorage;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class FirstTest {	
	private LoginPage loginPage;
	private SignupPage signupPage;
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
	}

	@AfterEach
	public void afterEach() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (this.driver != null) {
			driver.quit();
		}
	}

	@Test
	@DisplayName("Verifies that an unauthorized user can only access the login and signup pages")
	public void one() {
		loadPage("/signup");
		Assertions.assertEquals("Sign Up", driver.getTitle());

		loadPage("/login");
		Assertions.assertEquals("Login", driver.getTitle());

		loadPage("/home");
		Assertions.assertNotEquals("Home", driver.getTitle());
	}

	@Test
	@DisplayName("Signs up a new user, logs in, verifies that the home page is accessible, logs out, and verifies that the home page is no longer accessible")
	public void two() {
		loadPage("/signup");
		signupPage = new SignupPage(driver);
	 	signupPage.Signup("username", "password", "firstname", "lastname");
		Assertions.assertTrue(signupPage.isSuccess());

		loadPage("/login");
		loginPage = new LoginPage(driver);
		loginPage.login("username", "password");
		Assertions.assertEquals("Home", driver.getTitle());
		
		homePage = new HomePage(driver);
		homePage.logout();
		Assertions.assertEquals("Login", driver.getTitle());
	}

	// @Test
	// public void getSignupPage() {
	// 	loadPage("/signup");
	// 	Assertions.assertEquals("Sign Up", driver.getTitle());
	// }

	// @Test
	// public void signupOK() {
	// 	loadPage("/signup");
	// 	signupPage = new SignupPage(driver);
	// 	signupPage.Signup("username", "password", "firstname", "lastname");
	// 	Assertions.assertTrue(signupPage.isSuccess());
	// }

	// @Test
	// public void signupKO() {
	// 	loadPage("/signup");
	// 	signupPage = new SignupPage(driver);
	// 	signupPage.Signup("username", "", "firstname", "lastname");
	// 	Assertions.assertTrue(signupPage.isError());
	// }
}

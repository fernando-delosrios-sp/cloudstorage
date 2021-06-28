package com.udacity.jwdnd.course1.cloudstorage;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class LoginTest extends SignupTest {	
	private LoginPage loginPage;

	@Test
	public void getLoginPage() {
		loadPage("/login");
		Assertions.assertEquals("Login", driver.getTitle());
	}

	@Test
	public void loginOK() {
		loadPage("/login");
		loginPage = new LoginPage(driver);
		loginPage.login("username", "password");
		Assertions.assertEquals("Home", driver.getTitle());
	}

	@Test
	public void loginKO() {
		loadPage("/login");
		loginPage = new LoginPage(driver);
		loginPage.login("username", "passwd");
		Assertions.assertTrue(loginPage.isError());
	}
}

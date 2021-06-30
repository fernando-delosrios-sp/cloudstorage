package com.udacity.jwdnd.course1.cloudstorage;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@Disabled
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

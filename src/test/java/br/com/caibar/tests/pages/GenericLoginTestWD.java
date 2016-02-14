package br.com.caibar.tests.pages;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;

import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import br.com.caibar.DriverFactory;
import br.com.caibar.page_objects.LoginPage;

public class GenericLoginTestWD extends DriverFactory {

	@Test
	public void logInToTheWebsiteStep1() throws Exception {
		getDriver().get("http://ch5.caibar.com.br/index.html");

		WebElement username = getDriver().findElement(LoginPage.usernameLocator);
		WebElement password = getDriver().findElement(LoginPage.passwordLocator);
		WebElement submitButton = getDriver().findElement(LoginPage.loginButtonLocator);

		username.sendKeys("foo");
		password.sendKeys("bar");
		submitButton.click();

		assertThat(getDriver().getTitle(), is(equalTo("Logged in")));
	}

	@Test
	public void logInToTheWebsiteStep2() throws Exception {
		getDriver().get("http://ch5.caibar.com.br/index.html");
		LoginPage.logInWithUsernameAndPassword("foo", "bar", getDriver());

		assertThat(getDriver().getTitle(), is(equalTo("Logged in")));
	}
}
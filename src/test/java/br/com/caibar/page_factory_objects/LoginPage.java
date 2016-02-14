package br.com.caibar.page_factory_objects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import br.com.caibar.DriverFactory;

public class LoginPage {

	@FindBy(how = How.ID, using = "username")
	private WebElement usernameLocator;

	@FindBy(how = How.ID, using = "password")
	private WebElement passwordLocator;

	@FindBy(how = How.ID, using = "login")
	private WebElement loginButtonLocator;

	public LoginPage() throws Exception {
		PageFactory.initElements(DriverFactory.getDriver(), this);
	}

	public void logInWithUsernameAndPassword(String username, String password) {
		usernameLocator.sendKeys(username);
		passwordLocator.sendKeys(password);
		loginButtonLocator.click();
	}
}
package br.com.caibar.page_factory_objects;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import br.com.caibar.DriverFactory;

public class AboutPage extends PageFactory {

	@FindBy(how = How.CSS, using = "h1")
	private List<WebElement> heading;

	@FindBy(how = How.CSS, using = ".col-md-4 > p")
	private List<WebElement> aboutUsText;

	public AboutPage() throws Exception {
		PageFactory.initElements(DriverFactory.getDriver(), this);
	}

	public boolean aboutUsTextIsDisplayed() {
		return aboutUsText.size() == 1;
	}

}
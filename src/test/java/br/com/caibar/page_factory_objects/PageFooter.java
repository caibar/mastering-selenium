package br.com.caibar.page_factory_objects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import br.com.caibar.DriverFactory;

public class PageFooter extends PageFactory {

	@FindBy(how = How.CSS, using = ".left-footer > a")
	private WebElement aboutUsLink;

	public PageFooter() throws Exception {
		PageFactory.initElements(DriverFactory.getDriver(), this);
	}

	public AboutPage goToTheAboutUsPage() throws Exception {
		aboutUsLink.click();
		return new AboutPage();
	}
}
package br.com.caibar.tests.pages;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import br.com.caibar.DriverFactory;
import br.com.caibar.page_factory_objects.AboutPage;
import br.com.caibar.page_factory_objects.IndexPage;
import br.com.caibar.page_factory_objects.PageFooter;

public class CheckAboutPageTextWD extends DriverFactory {

	@Test
	public void checkThatAboutPageHasTextStep1() throws Exception {
		getDriver().get("http://ch5.caibar.com.br/index.html");
		getDriver().findElement(By.cssSelector("footer div:nth-child(1) > a")).click();
		String titleText = getDriver().findElement(By.cssSelector(".container > div h1")).getText();

		assertThat(titleText, is(equalTo("About us!")));
	}

	@Test
	public void checkThatAboutPageHasTextStep2() throws Exception {
		getDriver().get("http://ch5.caibar.com.br/index.html");
		IndexPage indexPage = new IndexPage();

		assertThat(indexPage.mainTextIsDisplayed(), is(equalTo(true)));
		assertThat(indexPage.mainPageButtonIsDisplayed(), is(equalTo(true)));

		PageFooter footer = new PageFooter();
		footer.goToTheAboutUsPage();
		AboutPage aboutPage = new AboutPage();

		assertThat(aboutPage.aboutUsTextIsDisplayed(), is(equalTo(true)));
	}

	@Test
	public void checkThatAboutPageHasTextStep3() throws Exception {
		getDriver().get("http://ch5.caibar.com.br/index.html");
		IndexPage indexPage = new IndexPage();

		assertThat(indexPage.mainTextIsDisplayed(), is(equalTo(true)));
		assertThat(indexPage.mainPageButtonIsDisplayed(), is(equalTo(true)));

		indexPage.footer.goToTheAboutUsPage();
		AboutPage aboutPage = new AboutPage();

		assertThat(aboutPage.aboutUsTextIsDisplayed(), is(equalTo(true)));
	}

	@Test
	public void checkThatAboutPageHasTextStep4() throws Exception {
		getDriver().get("http://ch5.caibar.com.br/index.html");
		IndexPage indexPage = new IndexPage();

		assertThat(indexPage.mainTextIsDisplayed(), is(equalTo(true)));
		assertThat(indexPage.mainPageButtonIsDisplayed(), is(equalTo(true)));

		AboutPage aboutPage = indexPage.footer.goToTheAboutUsPage();

		assertThat(aboutPage.aboutUsTextIsDisplayed(), is(equalTo(true)));
	}

	@Test
	public void checkThatAboutPageHasText() throws Exception {
		getDriver().get("http://ch5.caibar.com.br/index.html");
		indexPage = new IndexPage();

		assertThat(indexPage.mainTextIsDisplayed(), is(equalTo(true)));
		assertThat(indexPage.mainPageButtonIsDisplayed(), is(equalTo(true)));

		aboutPage = indexPage.footer.goToTheAboutUsPage();

		assertThat(aboutPage.aboutUsTextIsDisplayed(), is(equalTo(true)));
	}

}

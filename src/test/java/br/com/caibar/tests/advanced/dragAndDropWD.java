package br.com.caibar.tests.advanced;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import br.com.caibar.DriverFactory;

public class dragAndDropWD extends DriverFactory {

	/*Estamos indo para escrever um teste simples que
	verifica quantos existem caixas mais pequenas. Este teste irá então verificar se são
	destruída se arrastá-los sobre a caixa vermelha grande.*/
	@Test
	public void automateJavaScriptDragAndDrop() throws Exception {
		WebDriver driver = getDriver();

		driver.get("file:///C:/dev/automacao/bedbug/src/main/webapp/jsDragAndDrop.html");

		Actions advancedActions = new Actions(driver);
		final By destroyableBoxes = By.cssSelector("ul > li > div");
		WebElement obliterator = driver.findElement(By.id("obliterate"));
		WebElement firstBox = driver.findElement(By.id("one"));
		WebElement secondBox = driver.findElement(By.id("two"));

		assertThat(driver.findElements(destroyableBoxes).size(), is(equalTo(5)));

		advancedActions.clickAndHold(firstBox).moveToElement(obliterator).release().perform();

		assertThat(driver.findElements(destroyableBoxes).size(), is(equalTo(4)));

		advancedActions.dragAndDrop(secondBox, obliterator).perform();

		assertThat(driver.findElements(destroyableBoxes).size(), is(equalTo(3)));

	}

	@Test
	public void automateJavaScriptDragAndDropWithOffsetsStep1() throws Exception {
		WebDriver driver = getDriver();

		driver.get("file:///C:/dev/automacao/bedbug/src/main/webapp/jsDragAndDropWithHandle.html");

		Actions advancedActions = new Actions(driver);
		final By destroyableBoxes = By.cssSelector("ul > li > div");
		WebElement obliterator = driver.findElement(By.id("obliterate"));
		WebElement firstBox = driver.findElement(By.id("one"));
		WebElement secondBox = driver.findElement(By.id("two"));

		assertThat(driver.findElements(destroyableBoxes).size(), is(equalTo(5)));

		advancedActions.moveToElement(firstBox).moveByOffset(-40, 0).clickAndHold().moveToElement(obliterator).release()
				.perform();

		assertThat(driver.findElements(destroyableBoxes).size(), is(equalTo(4)));
	}

	// Intentionally failing
	@Test
	public void automateJavaScriptDragAndDropWithOffsetsStep2() throws Exception {
		WebDriver driver = getDriver();

		driver.get("file:///C:/dev/automacao/bedbug/src/main/webapp/jsDragAndDropWithHandle.html");

		Actions advancedActions = new Actions(driver);
		final By destroyableBoxes = By.cssSelector("ul > li > div");
		WebElement obliterator = driver.findElement(By.id("obliterate"));
		WebElement firstBox = driver.findElement(By.id("one"));
		WebElement secondBox = driver.findElement(By.id("two"));

		assertThat(driver.findElements(destroyableBoxes).size(), is(equalTo(5)));

		advancedActions.moveToElement(firstBox).moveByOffset(-40, 0).clickAndHold(firstBox).moveToElement(obliterator)
				.release().perform();

		assertThat(driver.findElements(destroyableBoxes).size(), is(equalTo(4)));
	}

	// Intentionally failing
	@Test
	public void automateJavaScriptDragAndDropWithOffsetsStep3() throws Exception {
		WebDriver driver = getDriver();

		driver.get("file:///C:/dev/automacao/bedbug/src/main/webapp/jsDragAndDropWithHandle.html");

		Actions advancedActions = new Actions(driver);
		final By destroyableBoxes = By.cssSelector("ul > li > div");
		WebElement obliterator = driver.findElement(By.id("obliterate"));
		WebElement firstBox = driver.findElement(By.id("one"));
		WebElement secondBox = driver.findElement(By.id("two"));

		assertThat(driver.findElements(destroyableBoxes).size(), is(equalTo(5)));

		advancedActions.moveToElement(firstBox, -40, 0).clickAndHold().moveToElement(obliterator).release().perform();

		assertThat(driver.findElements(destroyableBoxes).size(), is(equalTo(4)));
	}

	@Test
	public void automateJavaScriptDragAndDropWithOffsetsStep4() throws Exception {
		WebDriver driver = getDriver();

		driver.get("file:///C:/dev/automacao/bedbug/src/main/webapp/jsDragAndDropWithHandle.html");

		Actions advancedActions = new Actions(driver);
		final By destroyableBoxes = By.cssSelector("ul > li > div");
		WebElement obliterator = driver.findElement(By.id("obliterate"));
		WebElement firstBox = driver.findElement(By.id("one"));
		WebElement secondBox = driver.findElement(By.id("two"));

		assertThat(driver.findElements(destroyableBoxes).size(), is(equalTo(5)));

		advancedActions.moveToElement(firstBox, 1, 1).clickAndHold().moveToElement(obliterator).release().perform();

		assertThat(driver.findElements(destroyableBoxes).size(), is(equalTo(4)));
	}

	@Test
	public void automateJavaScriptDragAndDropWithOffsetsStep5() throws Exception {
		WebDriver driver = getDriver();

		driver.get("file:///C:/dev/automacao/bedbug/src/main/webapp/jsDragAndDropWithHandle.html");
		Actions advancedActions = new Actions(driver);
		final By destroyableBoxes = By.cssSelector("ul > li > div");
		WebElement obliterator = driver.findElement(By.id("obliterate"));
		WebElement firstBox = driver.findElement(By.id("one"));
		WebElement firstBoxText = driver.findElement(By.cssSelector("#one > span"));

		assertThat(driver.findElements(destroyableBoxes).size(), is(equalTo(5)));

		CalculateOffsetPosition op = new CalculateOffsetPosition(firstBox, firstBoxText,
				CalculateOffsetPosition.CursorPosition.CENTER);

		advancedActions.moveToElement(firstBox).moveByOffset(op.getXOffset(), op.getYOffset()).clickAndHold()
				.moveToElement(obliterator).release().perform();

		assertThat(driver.findElements(destroyableBoxes).size(), is(equalTo(4)));
	}
}

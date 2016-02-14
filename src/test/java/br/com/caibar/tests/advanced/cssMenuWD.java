package br.com.caibar.tests.advanced;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import br.com.caibar.DriverFactory;

public class cssMenuWD extends DriverFactory {

	/*1. Primeiro de tudo, nós estamos indo para mover o mouse e torná-la pairar sobre o
	opção Servicesmenu.
	2. Uma vez que passe o mouse sobre a opção Servicesmenu, precisamos esperar que o
	submenu a aparecer.
	3. Agora que o submenu apareceu, nós estamos indo para mover o mouse para baixo
	para a opção de Desenvolvimento Web submenu.
	4. Ao passar o mouse sobre a opção de Desenvolvimento Web submenu, vamos
	a clicar sobre ele.*/
	@Test
	public void automateCSSMenu() throws Exception {
		WebDriver driver = getDriver();

		driver.get("file:///C:/dev/automacao/bedbug/src/main/webapp/cssMenu.html");
		Actions advancedActions = new Actions(driver);
		WebDriverWait wait = new WebDriverWait(driver, 5, 100);

		WebElement servicesMenuOption = driver.findElement(By.id("services"));
		WebElement webDevelopmentSubMenuOption = driver.findElement(By.cssSelector("#services > ul > li:nth-child(2)"));

		advancedActions.moveToElement(servicesMenuOption).perform();

		wait.until(ExpectedConditions.visibilityOf(webDevelopmentSubMenuOption));

		advancedActions.moveToElement(webDevelopmentSubMenuOption).click().perform();

	}
}

package br.com.caibar;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.google.common.base.Function;

public class Functions {

	Function<WebDriver, WebElement> weFindElementFoo = new Function<WebDriver, WebElement>() {
		public WebElement apply(WebDriver driver) {
			return driver.findElement(By.id("foo"));
		}
	};

}
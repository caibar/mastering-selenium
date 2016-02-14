package br.com.caibar;

import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.internal.Locatable;

import com.google.common.base.Predicate;

public class Predicates {

    Predicate<WebDriver> didWeFindElementFoo = new Predicate<WebDriver>() {
        public boolean apply(WebDriver driver) {
            return driver.findElements(By.id("foo")).size() > 0;
        }
    };

    public static Predicate<WebDriver> listenerIsRegisteredOnElement(final String listenerType, final WebElement element) {
        return new Predicate<WebDriver>() {
            public boolean apply(WebDriver driver) {
                Map<String, Object> registeredListeners = (Map<String, Object>) ((JavascriptExecutor) driver).executeScript("return (window.jQuery != null) && (jQuery._data(jQuery(arguments[0]).get(0)), 'events')", element);
                for (Map.Entry<String, Object> listener : registeredListeners.entrySet()) {
                    if (listener.getKey().equals(listenerType)) {
                        return true;
                    }
                }
                return false;
            }
        };
    }

    public static Predicate<WebDriver> elementHasStoppedMoving(final WebElement element) {
        return new Predicate<WebDriver>() {
            public boolean apply(WebDriver driver) {
                Point initialLocation = ((Locatable) element).getCoordinates().inViewPort();
                try {
                    Thread.sleep(50);
                } catch (InterruptedException ignored) {
                    //ignored
                }
                Point finalLocation = ((Locatable) element).getCoordinates().inViewPort();
                return initialLocation.equals(finalLocation);
            }
		};
	}

}
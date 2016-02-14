package br.com.caibar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import br.com.caibar.fluent_page_objects.LoginPage;
import br.com.caibar.page_factory_objects.AboutPage;
import br.com.caibar.page_factory_objects.IndexPage;
import net.lightbody.bmp.BrowserMobProxy;

public class DriverFactory {

	private static List<WebDriverThread> webDriverThreadPool = Collections
			.synchronizedList(new ArrayList<WebDriverThread>());
	private static ThreadLocal<WebDriverThread> driverThread;

	protected IndexPage indexPage;
	protected AboutPage aboutPage;
	protected LoginPage loginPage;

	@BeforeSuite
	public static void instantiateDriverObject() {
		driverThread = new ThreadLocal<WebDriverThread>() {
			@Override
			protected WebDriverThread initialValue() {
				WebDriverThread webDriverThread = new WebDriverThread();
				webDriverThreadPool.add(webDriverThread);
				return webDriverThread;
			}
		};
	}

	public static WebDriver getDriver() throws Exception {
		return driverThread.get().getDriver();
	}

	public static WebDriver getBrowserMobProxyEnabledDriver() throws Exception {
		return driverThread.get().getBrowserMobProxyEnabledDriver();
	}

	public static BrowserMobProxy getBrowserMobProxy() {
		return driverThread.get().getBrowserMobProxy();
	}

	@AfterMethod
	public static void clearCookies() throws Exception {
		getDriver().manage().deleteAllCookies();
	}

	@AfterSuite
	public static void closeDriverObjects() {
		for (WebDriverThread webDriverThread : webDriverThreadPool) {
			webDriverThread.quitDriver();
		}
	}
}
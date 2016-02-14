package br.com.caibar.tests.download;

import static br.com.caibar.downloader.CheckFileHash.generateHashForFileOfType;
import static br.com.caibar.downloader.HashType.MD5;
import static br.com.caibar.downloader.HashType.SHA1;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;

import java.io.File;
import java.net.URI;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import br.com.caibar.DriverFactory;
import br.com.caibar.downloader.FileDownloader;
import br.com.caibar.downloader.RequestType;

public class FileDownloaderWD extends DriverFactory {

	private static JettyServer localWebServer;
	private static final String WEB_SERVER_URL = "http://localhost";
	private static final int WEB_SERVER_PORT = 9081;
	private static URI downloadURI200;

	@BeforeSuite
	public static void start() throws Exception {
		localWebServer = new JettyServer(WEB_SERVER_PORT);
		downloadURI200 = new URI(WEB_SERVER_URL + ":" + WEB_SERVER_PORT + "/downloadTest.html");
	}

	@AfterSuite
	public static void stop() throws Exception {
		localWebServer.stopJettyServer();
	}

	@Test
	public void statusCodeFromEmbeddedFile() throws Exception {
		WebDriver driver = new HtmlUnitDriver(true);
		FileDownloader downloadHandler = new FileDownloader(driver);
		driver.get("http://www.masteringselenium.com/downloadTest.html");
		WebElement fileThatShouldExist = driver.findElement(By.id("fileToDownload"));
		URI fileAsURI = new URI(fileThatShouldExist.getAttribute("href"));
		downloadHandler.setURI(fileAsURI);
		downloadHandler.setHTTPRequestMethod(RequestType.GET);
		assertThat(downloadHandler.getLinkHTTPStatus(), is(equalTo(200)));
	}

	@Test
	public void downloadAFile() throws Exception {
		WebDriver driver = getDriver();
		FileDownloader downloadHandler = new FileDownloader(driver);

		driver.get(downloadURI200.toString());
		WebElement fileThatShouldExist = driver.findElement(By.id("fileToDownload"));
		URI fileAsURI = new URI(fileThatShouldExist.getAttribute("href"));

		downloadHandler.setURI(fileAsURI);
		downloadHandler.setHTTPRequestMethod(RequestType.GET);

		File downloadedFile = downloadHandler.downloadFile();

		assertThat(downloadedFile.exists(), is(equalTo(true)));
		assertThat(downloadHandler.getLinkHTTPStatus(), is(equalTo(200)));
	}

	@Test
	public void downloadAnImage() throws Exception {
		WebDriver driver = getDriver();
		FileDownloader downloadHandler = new FileDownloader(driver);

		driver.get(downloadURI200.toString());
		WebElement fileThatShouldExist = driver.findElement(By.id("anImage"));
		URI fileAsURI = new URI(fileThatShouldExist.getAttribute("src"));

		downloadHandler.setURI(fileAsURI);
		downloadHandler.setHTTPRequestMethod(RequestType.GET);
		File downloadedFile = downloadHandler.downloadFile();

		assertThat(downloadedFile.exists(), is(equalTo(true)));
		assertThat(downloadHandler.getLinkHTTPStatus(), is(equalTo(200)));
	}

	@Test
	public void downloadAFileWhilstMimicingSeleniumCookiesAndCheckTheSHA1Hash() throws Exception {
		WebDriver driver = getDriver();
		FileDownloader downloadHandler = new FileDownloader(driver);

		driver.get(downloadURI200.toString());
		WebElement fileThatShouldExist = driver.findElement(By.id("fileToDownload"));
		URI fileAsURI = new URI(fileThatShouldExist.getAttribute("href"));

		downloadHandler.setURI(fileAsURI);
		downloadHandler.setHTTPRequestMethod(RequestType.GET);
		File downloadedFile = downloadHandler.downloadFile();

		assertThat(downloadedFile.exists(), is(equalTo(true)));
		assertThat(downloadHandler.getLinkHTTPStatus(), is(equalTo(200)));
		assertThat(generateHashForFileOfType(downloadedFile, SHA1),
				is(equalTo("8882e3d972be82e14a98c522745746a03b97997a")));
	}

	@Test
	public void downloadAFileWhilstMimicingSeleniumCookiesAndCheckTheMD5Hash() throws Exception {
		WebDriver driver = getDriver();
		FileDownloader downloadHandler = new FileDownloader(driver);

		driver.get(downloadURI200.toString());
		WebElement fileThatShouldExist = driver.findElement(By.id("fileToDownload"));
		URI fileAsURI = new URI(fileThatShouldExist.getAttribute("href"));

		downloadHandler.setURI(fileAsURI);
		downloadHandler.setHTTPRequestMethod(RequestType.GET);
		File downloadedFile = downloadHandler.downloadFile();

		assertThat(downloadedFile.exists(), is(equalTo(true)));
		assertThat(downloadHandler.getLinkHTTPStatus(), is(equalTo(200)));
		assertThat(generateHashForFileOfType(downloadedFile, MD5), is(equalTo("d1f296f523b74462b31b912a5675a814")));
	}
}
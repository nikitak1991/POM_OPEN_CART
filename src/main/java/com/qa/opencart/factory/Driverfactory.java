package com.qa.opencart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.runtime.SwitchBootstraps;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Driverfactory {
	public WebDriver driver;
	public Properties prop;
	public OptionsManager optionsManager;

	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();

	/**
	 * this method is used to initialize the driver on the basis of given
	 * browsername
	 * 
	 * @param browsername
	 * @return WebDriver
	 */
	public WebDriver initDriver(Properties prop) {
		String browsername = prop.getProperty("browser");

//		if(System.getenv("browserName")!=null) {
//			browserName = System.getenv("browserName");
//		}

		System.out.println("browser name is : " + browsername);
		optionsManager = new OptionsManager(prop);

		if (browsername.equalsIgnoreCase("chrome")) {
			// driver = new ChromeDriver(optionsManager.optionsManager());

			if (Boolean.parseBoolean(prop.getProperty("remote"))) {
				// remote execution on grid:
				init_remoteWebDriver("chrome");
			} else {
				// local execution:
				WebDriverManager.chromedriver().setup();
				tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));
			}
		} else if (browsername.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			tlDriver.set(new FirefoxDriver());
		} else if (browsername.equalsIgnoreCase("safari")) {
			WebDriverManager.safaridriver().setup();
			tlDriver.set(new SafariDriver());
		} else {
			System.out.println("Please pass correct browser name");
		}

		getDriver().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		getDriver().get(prop.getProperty("url"));
		return getDriver();
	}

	private void init_remoteWebDriver(String browserName) {
		System.out.println("===========Running tests on Selenium GRID - Remote Machine...." + browserName);

		if (browserName.equals("chrome")) {
			try {
				tlDriver.set(
						new RemoteWebDriver(new URL(prop.getProperty("huburl")), optionsManager.getChromeOptions()));
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
		}

		else if (browserName.equals("firefox")) {
			try {
				tlDriver.set(
						new RemoteWebDriver(new URL(prop.getProperty("huburl")), optionsManager.getFirefoxOptions()));
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
		}

		else if (browserName.equals("edge")) {
			try {
				tlDriver.set(new RemoteWebDriver(new URL(prop.getProperty("huburl")), optionsManager.getEdgeOptions()));
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
		}

	}

	/**
	 * 
	 * @return it will give local copy of driver
	 */
	public static synchronized WebDriver getDriver() {
		return tlDriver.get();
	}

	/**
	 * 
	 * @return properties reference with all config propertu
	 */
	public Properties initPropperty() {

		prop = new Properties();
		FileInputStream ip = null;
		
		// commnd line args --> maven
		// mvn clean install -Denv="stage" -Dbrowser="chrome"
		// mvn clean install

		String envName = System.getProperty("env");
		System.out.println("Running test cases on enviornment" + envName);

		if (envName == null) {
			
				System.out.println("No Env is given...hence running in default-qa enviormnet");
				try {
				ip = new FileInputStream("./src\\test\\resourcse\\config\\config.properties");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		} 
		else {
			try {
				switch (envName.toLowerCase()) {
				case "qa":
					ip = new FileInputStream("./src\\test\\resourcse\\config\\qa.config.properties");
					break;
				case "stage":
					ip = new FileInputStream("./src\\test\\resourcse\\config\\qa.config.properties");
					break;
				case "dev":
					ip = new FileInputStream("./src\\test\\resourcse\\config\\config.properties");
					break;
				default:
					System.out.println("Please pass correct Enviornment");
					break;
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
		try {
			prop.load(ip);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop;

	}

	/**
	 * 
	 * this method is taking screen shot
	 * 
	 * @return path of stored screen shot
	 */
	public static String getScreenshot(String methodName) {
		File srcFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
		// Users/naveenautomationlabs/Documents/workspace1/
		String path = System.getProperty("user.dir") + "/screenshot/" + methodName + ".png";
		File destination = new File(path);
		try {
			FileUtils.copyFile(srcFile, destination);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return path;
	}

}

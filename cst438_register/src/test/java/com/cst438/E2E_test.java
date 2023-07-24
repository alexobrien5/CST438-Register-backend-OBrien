package com.cst438;


import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.Random;

public class E2E_test {
	public static final String CHROME_DRIVER_FILE_LOCATION 
	= "C:/chromedriver_win32/chromedriver.exe";
	public static final String URL = "http://localhost:3000";
	public static final String STUDENT_NAME = "test";
	public static final int SLEEP_DURATION = 1000; // 1 second.
	public static Random random = new Random();
	public static String randInt = String.valueOf(random.nextInt(1000000));
	public static final String RAND_EMAIL = "testemail" + randInt;
	// this email entry is already in the db
	public static final String STUDENT_EMAIL = "testemail";



	@Test
	public void addStudent() throws Exception {


		// set the driver location and start driver
		//@formatter:off
		//
		// browser	property name 				Java Driver Class
		// -------  ------------------------    ----------------------
		// Edge 	webdriver.edge.driver 		EdgeDriver
		// FireFox 	webdriver.firefox.driver 	FirefoxDriver
		// IE 		webdriver.ie.driver 		InternetExplorerDriver
		// Chrome   webdriver.chrome.driver     ChromeDriver
		//
		//@formatter:on


		//TODO update the property name for your browser 
		System.setProperty("webdriver.chrome.driver",
				CHROME_DRIVER_FILE_LOCATION);
		//TODO update the class ChromeDriver()  for your browser
		// For chromedriver 111 need to specify the following options 
		ChromeOptions ops = new ChromeOptions();
		ops.addArguments("--remote-allow-origins=*");	


		WebDriver driver = new ChromeDriver(ops);

		try {
			WebElement we;

			driver.get(URL);
			// must have a short wait to allow time for the page to download 
			Thread.sleep(SLEEP_DURATION);

			// click the add student button
			we = driver.findElement(By.id("addStudent"));
			we.click();
			Thread.sleep(SLEEP_DURATION);

			// type in the test name and email
			we = driver.findElement(By.name("name"));
			we.sendKeys(STUDENT_NAME);
			we = driver.findElement(By.name("email"));
			we.sendKeys(RAND_EMAIL);


			// click the "add" button
			we = driver.findElement(By.id("add"));
			we.click();
			Thread.sleep(SLEEP_DURATION);

			// verify the correct message from toast
			String toastMessage = driver.findElement(By.className("Toastify__toast-body")).getText();
			System.out.println("The toast message is " + toastMessage);
			assertEquals(toastMessage, "Student successfully added");
			Thread.sleep(SLEEP_DURATION*2);

		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
			
		} finally {
			driver.close();
			driver.quit();
		}
	}
	
	@Test
	public void addExistingStudent() throws Exception {


		// set the driver location and start driver
		//@formatter:off
		//
		// browser	property name 				Java Driver Class
		// -------  ------------------------    ----------------------
		// Edge 	webdriver.edge.driver 		EdgeDriver
		// FireFox 	webdriver.firefox.driver 	FirefoxDriver
		// IE 		webdriver.ie.driver 		InternetExplorerDriver
		// Chrome   webdriver.chrome.driver     ChromeDriver
		//
		//@formatter:on


		//TODO update the property name for your browser 
		System.setProperty("webdriver.chrome.driver",
				CHROME_DRIVER_FILE_LOCATION);
		//TODO update the class ChromeDriver()  for your browser
		// For chromedriver 111 need to specify the following options 
		ChromeOptions ops = new ChromeOptions();
		ops.addArguments("--remote-allow-origins=*");	


		WebDriver driver = new ChromeDriver(ops);

		try {
			WebElement we;

			driver.get(URL);
			// must have a short wait to allow time for the page to download 
			Thread.sleep(SLEEP_DURATION);

			// click the add student button
			we = driver.findElement(By.id("addStudent"));
			we.click();
			Thread.sleep(SLEEP_DURATION);

			// type in the test name and email
			we = driver.findElement(By.name("name"));
			we.sendKeys(STUDENT_NAME);
			we = driver.findElement(By.name("email"));
			we.sendKeys(STUDENT_EMAIL);


			// click the "add" button
			we = driver.findElement(By.id("add"));
			we.click();
			Thread.sleep(SLEEP_DURATION);

			// verify the correct message from toast
			String toastMessage = driver.findElement(By.className("Toastify__toast-body")).getText();
			System.out.println("The toast message is " + toastMessage);
			assertEquals(toastMessage, "Email exists or name is invalid");
			Thread.sleep(SLEEP_DURATION*2);

		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
			
		} finally {
			driver.close();
			driver.quit();
		}
	}
	
	@Test
	public void addStudentNoName() throws Exception {


		// set the driver location and start driver
		//@formatter:off
		//
		// browser	property name 				Java Driver Class
		// -------  ------------------------    ----------------------
		// Edge 	webdriver.edge.driver 		EdgeDriver
		// FireFox 	webdriver.firefox.driver 	FirefoxDriver
		// IE 		webdriver.ie.driver 		InternetExplorerDriver
		// Chrome   webdriver.chrome.driver     ChromeDriver
		//
		//@formatter:on


		//TODO update the property name for your browser 
		System.setProperty("webdriver.chrome.driver",
				CHROME_DRIVER_FILE_LOCATION);
		//TODO update the class ChromeDriver()  for your browser
		// For chromedriver 111 need to specify the following options 
		ChromeOptions ops = new ChromeOptions();
		ops.addArguments("--remote-allow-origins=*");	


		WebDriver driver = new ChromeDriver(ops);

		try {
			WebElement we;

			driver.get(URL);
			// must have a short wait to allow time for the page to download 
			Thread.sleep(SLEEP_DURATION);

			// click the add student button
			we = driver.findElement(By.id("addStudent"));
			we.click();
			Thread.sleep(SLEEP_DURATION);

			// type in the test name and email
			we = driver.findElement(By.name("name"));
			we.sendKeys("");
			we = driver.findElement(By.name("email"));
			we.sendKeys(RAND_EMAIL);


			// click the "add" button
			we = driver.findElement(By.id("add"));
			we.click();
			Thread.sleep(SLEEP_DURATION);

			// verify the correct message from toast
			String toastMessage = driver.findElement(By.className("Toastify__toast-body")).getText();
			System.out.println("The toast message is " + toastMessage);
			assertEquals(toastMessage, "Email exists or name is invalid");
			Thread.sleep(SLEEP_DURATION*2);

		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
			
		} finally {
			driver.close();
			driver.quit();
		}
	}
}

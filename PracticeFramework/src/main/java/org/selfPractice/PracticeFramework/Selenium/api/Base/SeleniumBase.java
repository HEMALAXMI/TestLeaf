package org.selfPractice.PracticeFramework.Selenium.api.Base;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.selfPractice.PracticeFramework.Selenium.api.Design.Browser;
import org.selfPractice.PracticeFramework.Selenium.api.Design.Element;
import org.selfPractice.PracticeFramework.Utils.Reporter;

public class SeleniumBase extends Reporter implements Browser, Element{
	
	public static RemoteWebDriver driver;
	public static WebDriverWait wait;
	
	public void click(WebElement ele) {
		// TODO Auto-generated method stub
		String text = "";
		
		try {
			wait = new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions.elementToBeClickable(ele));
			
			text = ele.getText();
			ele.click();
			
			reportStep("the element " + text + " is clicked", "pass");
		}catch(StaleElementReferenceException e) {
			reportStep("the element " + text + " is not clicked", "fail");
			throw new RuntimeException();
		}
		
	}

	public void clearAndType(WebElement ele, String data) {
		// TODO Auto-generated method stub
		try {
			ele.clear();
			ele.sendKeys(data);
			reportStep("The data " + data + " is set in the element", "pass");
		}catch(ElementNotInteractableException e) {
			reportStep("The data " + data + " is not set in the element", "fail");
			throw new RuntimeException();
		}
		
	}
	
	public void clearAndTypeWithEnter(WebElement ele, String data) {
		// TODO Auto-generated method stub
		try {
			ele.clear();
			ele.sendKeys(data);
			ele.sendKeys(Keys.ENTER);
			reportStep("The data " + data + " is set in the element", "pass");
		}catch(ElementNotInteractableException e) {
			reportStep("The data " + data + " is not set in the element", "fail");
			throw new RuntimeException();
		}
		
	}
	
	public boolean verifyIsSelected(WebElement ele) {
		// TODO Auto-generated method stub
		try {
			if(ele.isSelected() == true) {
				reportStep("The element is selected", "info");
				return true;
			}else {
				reportStep("The element is not selected", "info");
				return false;
			}
		}catch(WebDriverException e) {
			
			reportStep("The element is not selected and the exception is: "+ e.getMessage(), "info");
			return false;
		}
		
		
	}
	
	public String getExactText(WebElement ele) {
		// TODO Auto-generated method stub
		String text = "";
		try {
			text = ele.getText();
			reportStep("The text " + text + " is extracted from element", "info");
			return text;
		}catch(WebDriverException e){
			reportStep("The text is not extracted from element and the exception is: " + e.getMessage(), "info");
			return null;
		}
		
	}
	
	public boolean verifyExactText(WebElement ele, String expectedText) {
		// TODO Auto-generated method stub
		String text = "";
		try {
			text = ele.getText();
			if(text.equals(expectedText)) {
				reportStep("The text " + text + " is verified successfully", "info");
				return true;
			}else {
				reportStep("The text " + text + " is not verified successfully", "info");
				return false;
			}
			
		}catch(WebDriverException e){
			reportStep("The text of the element is not the same as expected and the exception is: " + e.getMessage(), "info");
			return false;
		}
	}
	
	public boolean verifyPartialText(WebElement ele, String expectedText) {
		// TODO Auto-generated method stub
		String text = "";
		try {
			text = ele.getText();
			if(text.contains(expectedText)) {
				reportStep("The text " + text + " is verified successfully", "info");
				return true;
			}else {
				reportStep("The text " + text + " is not verified successfully", "info");
				return false;
			}
			
		}catch(WebDriverException e){
			reportStep("The text of the element does not contain expected text and the exception is: " + e.getMessage(), "info");
			return false;
		}
		
	}
	
	public void StartApp(String browser, String url) {
		// TODO Auto-generated method stub
		
		try {
			switch(browser.toUpperCase()) {
			
			case "CHROME":
				System.setProperty("webdriver.chrome.driver", "./Drivers/chromedriver.exe");
				driver = new ChromeDriver();
				break;
			
			case "FIREFOX":
				System.setProperty("webdriver.gecko.driver", "./Drivers/geckodriver_64 bit.exe");
				driver = new FirefoxDriver();
				break;
			
			default:
				reportStep("Enter a valid broser name", "fail");
				break;
			}
			
			driver.navigate().to(url);
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		}catch(Exception e) {
			reportStep("Browser not launched", "Fail");
			throw new RuntimeException();
		}finally {
			takeSnap();
		}
	}
	
	public void CloseAPP() {
		// TODO Auto-generated method stub
		driver.close();
	}
	
	public void VerifyTitle(String title) {
		// TODO Auto-generated method stub
		String actualTitle ="";
		try {
			actualTitle = driver.getTitle();
			if(actualTitle.equals(title)) {
				reportStep("Title " +title+ " is verified successfully", "Pass");
			}else {
				reportStep("Title " +title+ " is not verified successfully", "Fail");
			}
		}catch(WebDriverException e) {
			throw new RuntimeException();
		}
	}
	
	@Override
	public long takeSnap() {
		// TODO Auto-generated method stub
		File src = driver.getScreenshotAs(OutputType.FILE);
		String fileName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		long num = Long.parseLong(fileName);
		File dest = new File("./snapshots/" + num + ".jpg");
		try {
			FileUtils.copyFile(src, dest);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return num;
	}
	
	
}

package org.selfPractice.PracticeFramework.Testng;

import java.io.IOException;

import org.selfPractice.PracticeFramework.Selenium.api.Base.SeleniumBase;
import org.selfPractice.PracticeFramework.Utils.DataLibrary;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;

public class Annotations extends SeleniumBase{
	
	@Parameters({"Browser","URL"})
	@BeforeMethod
	public void beforeMethod(String Browser, String URL) {
		StartApp(Browser, URL);
	}
	
	@AfterMethod
	public void afterMethod() {
		CloseAPP();
	}
	
	@DataProvider(name = "fetchData")
	public Object[][] setData() throws IOException {
		return DataLibrary.getData(sheetName, testcaseName);
	}

}

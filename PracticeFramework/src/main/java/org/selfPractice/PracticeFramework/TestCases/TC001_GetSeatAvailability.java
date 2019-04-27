package org.selfPractice.PracticeFramework.TestCases;

import java.util.Map;

import org.selfPractice.PracticeFramework.Pages.ErailHomePage;
import org.selfPractice.PracticeFramework.Testng.Annotations;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TC001_GetSeatAvailability extends Annotations{

	@BeforeTest
	public void beforeTest() {
		testcaseName = "TC001_GetSeatAvailability";
		sheetName = "TestData";
		testcaseDec = "Get available seats in any given train";
		author = "Hema";
		category = "Regression";
	}
	
	//Working code for 2D array data provider
	/*@Test(dataProvider = "fetchData")
	public void GetSeatAvailability(String TestCaseName, String Title, String From, String To, String TrainName) {
		
		new ErailHomePage()
		.verifyPageTitle(Title)
		.enterFrom(From)
		.enterTo(To)
		.unSelectchkbox()
		.clickGetTrains();
		
	}*/
	
	@Test(dataProvider = "fetchData")
	public void GetSeatAvailability(Map<String, String> map) {
		
		new ErailHomePage()
		.verifyPageTitle(map.get("Title"))
		.enterFrom(map.get("From"))
		.enterTo(map.get("To"))
		.unSelectchkbox()
		.clickGetTrains();
		
	}
	
}

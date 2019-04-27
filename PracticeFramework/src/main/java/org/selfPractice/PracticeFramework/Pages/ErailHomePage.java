package org.selfPractice.PracticeFramework.Pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.selfPractice.PracticeFramework.Testng.Annotations;

public class ErailHomePage extends Annotations{

	public ErailHomePage() {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(how = How.ID , using="txtStationFrom") WebElement eleFrom;
	@FindBy(how = How.ID, using="txtStationTo") WebElement eleTo;
	@FindBy(how=How.ID, using="chkSelectDateOnly") WebElement eleDateChkBx;
	@FindBy(how=How.ID, using="buttonFromTo") WebElement eleGetTrains;
	
	public ErailHomePage verifyPageTitle(String title) {
		VerifyTitle(title);
		return this;
	}
	
	public ErailHomePage enterFrom(String fromLocation) {
		clearAndTypeWithEnter(eleFrom, fromLocation);
		return this;
	}
	
	public ErailHomePage enterTo(String toLocation) {
		clearAndTypeWithEnter(eleTo, toLocation);
		return this;
	}
	
	public ErailHomePage unSelectchkbox() {
		boolean selected = verifyIsSelected(eleDateChkBx);
		
		if (selected == true) {
			click(eleDateChkBx);
		}
		return this;
	}
	
	public void clickGetTrains() {
		click(eleGetTrains);
	}
	
	//table[@class='DataTable TrainList']//tr
	
	
}

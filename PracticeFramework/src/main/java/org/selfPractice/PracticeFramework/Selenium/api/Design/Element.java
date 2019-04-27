package org.selfPractice.PracticeFramework.Selenium.api.Design;

import org.openqa.selenium.WebElement;

public interface Element {

	void click(WebElement ele);
	void clearAndType(WebElement ele, String data);
	boolean verifyIsSelected(WebElement ele);
	String getExactText(WebElement ele);
	boolean verifyExactText(WebElement ele, String expectedText);
	boolean verifyPartialText(WebElement ele, String expectedText);
	
}

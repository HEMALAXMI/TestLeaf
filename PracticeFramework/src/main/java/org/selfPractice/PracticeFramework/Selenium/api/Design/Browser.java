package org.selfPractice.PracticeFramework.Selenium.api.Design;

public interface Browser {
		
	
		public void StartApp(String browser, String url);
		public void CloseAPP();
		void VerifyTitle(String title);
		
}

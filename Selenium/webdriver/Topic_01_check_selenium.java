package webdriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_01_check_selenium {
	//khai bao bien dai dien cho selenium webdriver
	WebDriver driver;
	
	 @BeforeClass
	  public void beforeClass() {
		  System.out.println("before");
		  //mo trinh dyuyent
		  driver = new FirefoxDriver();
		  //mo url can test
		  driver.get("https://www.facebook.com/");
	  }
  @Test
  public void TC_01_Check_Title() {
	  String homePageTitle = driver.getTitle();
	  
	  Assert.assertEquals(homePageTitle, "Facebook - Đăng nhập hoặc đăng ký");
	  
  }
  @Test
  public void TC_02() { 
String homePageURL = driver.getCurrentUrl();
	  
	  Assert.assertEquals(homePageURL, "https://www.facebook.com/");
	  
  }
  

  @AfterClass
  public void afterClass() {
	  //dong trinh duyent
	  driver.quit();
  }

}
 
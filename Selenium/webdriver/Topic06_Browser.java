package webdriver;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

@Test
public class Topic06_Browser {
	//khai bao bien dai dien cho selenium webdriver
	WebDriver driver;
  public void TC_01_Verify_URL() {
	  
	  //click My Account from the Footer
	  driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
	  
	  //verify login url
	  Assert.assertEquals(driver.getCurrentUrl(), "http://live.demoguru99.com/index.php/customer/account/login/");
	  
	  //click Create An Account button
	  driver.findElement(By.xpath("//form[@id='login-form']//a[@title='Create an Account']")).click();
	  
	  //verify register url
	  Assert.assertEquals(driver.getCurrentUrl(), "http://live.demoguru99.com/index.php/customer/account/create/");
	  
  }
  public void TC_02_Verify_Title() {
	  
	  //click My Account from the Footer
	  driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
	  
	  //verify login page title 
	  Assert.assertEquals(driver.getTitle(), "Customer Login");
	  
	  //click Create An Account button
	  driver.findElement(By.xpath("//form[@id='login-form']//a[@title='Create an Account']")).click();
	  
	  //verify register page title
	  Assert.assertEquals(driver.getTitle(), "Create New Customer Account");  
  }
  
public void TC_03_Navigation_function() {
	  
	  //click My Account from the Footer
	  driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
	  
	  //click Create An Account button
	  driver.findElement(By.xpath("//form[@id='login-form']//a[@title='Create an Account']")).click();
	  
	  //verify register url
	  Assert.assertEquals(driver.getCurrentUrl(), "http://live.demoguru99.com/index.php/customer/account/create/");
	  
	  //back to login page
	  driver.navigate().back();
	  
	  //verify login url
	  Assert.assertEquals(driver.getCurrentUrl(), "http://live.demoguru99.com/index.php/customer/account/login/");
	  
	  //foward to register page
	  driver.navigate().forward();
	  
	  //verify register page title
	  Assert.assertEquals(driver.getTitle(), "Create New Customer Account");
}
  
  @BeforeClass
  public void beforeClass() {
	  //mo trinh duyet firefox
	  //driver = new FirefoxDriver();
	 
	 //mo trinh duyet chrome
	 // System.setProperty("webdriver.chrome.driver", ".\\BrowserDrivers\\chromedriver.exe");
	  //driver = new ChromeDriver();
	  
	  //mo trinh duyet edge
	  System.setProperty("webdriver.edge.driver", ".//BrowserDrivers\\msedgedriver.exe");
	  driver = new EdgeDriver();
	  
	  //set timeout
	  driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	  
	  //Full screen browser
	  driver.manage().window().maximize();
	  
	  //mo url test
	  driver.get("http://live.demoguru99.com/");
  }
  
  @AfterClass
  public void afterClass() {
	  //dong trinh duyent
	  driver.quit();
	  
  }

}

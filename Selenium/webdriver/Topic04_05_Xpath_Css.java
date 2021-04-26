package webdriver;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

@Test
public class Topic04_05_Xpath_Css {
	//khai bao bien dai dien cho selenium webdriver
		WebDriver driver;
	    String randomEmail;
	    String password;
		
		 @BeforeClass
		  public void beforeClass() {
			 
			 //random email
			  Random randomGenerator = new Random();  
			  int randomInt = randomGenerator.nextInt(1000);  
			  randomEmail = "hoannx09"+ randomInt +"@gmail.com"; 
			  //pass
			  password = "123456";
			 
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
	  public void TC_01_Login_with_empty_email_and_password () {
		//click on My Account link from the Footer
		  driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
		  
		//Leave email and password empty
		  driver.findElement(By.id("email")).sendKeys("");
		  driver.findElement(By.id("pass")).sendKeys("");
		  
		//Click login btn
		  driver.findElement(By.xpath("//button[@id='send2']")).click();  
		  
		//verify error message
		  Assert.assertEquals(driver.findElement(By.id("advice-required-entry-email")).getText(),"This is a required field.");
		  Assert.assertEquals(driver.findElement(By.id("advice-required-entry-pass")).getText(),"This is a required field.");
	  }
	  public void TC_02_Login_with_invalid_email() { 
		//click on My Account link from the Footer
		  driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
	
		  //input invalid email
		  driver.findElement(By.id("email")).sendKeys("12341234@123.123");
		  
		  //input valid password
		  driver.findElement(By.id("pass")).sendKeys("123456");
		  
		  //click login btn
		  driver.findElement(By.id("send2")).click();
		  
		  //verify error message
		  Assert.assertEquals(driver.findElement(By.id("advice-validate-email-email")).getText(),"Please enter a valid email address. For example johndoe@domain.com.");
	  }
	  public void TC_03_Loign_with_password_bellow_6_characters() {
		//click on My Account link from the Footer
		  driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
		  
		  //input valid email
		  driver.findElement(By.id("email")).sendKeys("automation@gmail.com");
		  
		  //input password bellow 6 characters
		  driver.findElement(By.id("pass")).sendKeys("123");
		  
		  //click login btn
		  driver.findElement(By.id("send2")).click();
		  
		  //verify error message
		  Assert.assertEquals(driver.findElement(By.id("advice-validate-password-pass")).getText(), "Please enter 6 or more characters without leading or trailing spaces.");  
	  }
	  
	  public void TC_04_Loign_with_incorrect_email_and_password() {
		//click on My Account link from the Footer
		  driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
		  
		  //input valid email
		  driver.findElement(By.id("email")).sendKeys("automation14414@gmail.com");
		  
		  //input password bellow 6 characters
		  driver.findElement(By.id("pass")).sendKeys("123123123");
		  
		  //click login btn
		  driver.findElement(By.id("send2")).click();
		  
		  //verify error message
		  Assert.assertEquals(driver.findElement(By.xpath("//li[@class='error-msg']")).getText(), "Invalid login or password.");
	  }
	  
	  public void TC_05_Create_new_account() {
		//click on My Account link from the Footer
		  driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
		  
		  //click Create An Account btn
		  driver.findElement(By.xpath("//form[@id='login-form']//a[@title='Create an Account']")).click();
		  
		  //enter first name
		  driver.findElement(By.id("firstname")).sendKeys("Hoan");
		  
		  //enter middle name
		  driver.findElement(By.id("middlename")).sendKeys("");
		  
		  //enter last name
		  driver.findElement(By.id("lastname")).sendKeys("Nguyen");
		  
		  //enter random email address 
		  driver.findElement(By.id("email_address")).sendKeys(randomEmail);
		  
		  //enter password
		  driver.findElement(By.id("password")).sendKeys(password);
		  
		  //enter confirm password
		  driver.findElement(By.id("confirmation")).sendKeys(password);
		  
		  //click Register btn
		  driver.findElement(By.xpath("//button[@title='Register']")).click();
		  
		  //verify success message
		  Assert.assertEquals(driver.findElement(By.xpath("//li[@class='success-msg']")).getText(), "Thank you for registering with Main Website Store.");
		  
		  //verify first name, last name, email
		  String contactInformation = driver.findElement(By.xpath("//h3[text()='Contact Information']/parent::div/following-sibling::div/p")).getText();
		  Assert.assertTrue(contactInformation.contains("Hoan"));
		  Assert.assertTrue(contactInformation.contains("Nguyen"));
		  Assert.assertTrue(contactInformation.contains(randomEmail));		  
		  
		  //logout
		  driver.findElement(By.xpath(".//*[@id='header']//a[contains(.,'Account')]")).click();
		  driver.findElement(By.xpath(".//*[@id='header-account']//a[@title='Log Out']")).click();
		  
		  //wait
		  try {
			Thread.sleep(6000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		  
		  //check navigate Home
		  String homePageURL = driver.getCurrentUrl();
		  Assert.assertEquals(homePageURL, "http://live.demoguru99.com/index.php/");
		 
	  }
	  
	  public void TC_06_Login_with_valid_email_and_password() {
		//click on My Account link from the Footer
		  driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
		  
		//input valid email
		  driver.findElement(By.id("email")).sendKeys(randomEmail);
		  
		//input password bellow 6 characters
		  driver.findElement(By.id("pass")).sendKeys(password);
		  
	  }

	  @AfterClass
	  public void afterClass() {
		  //dong trinh duyent
		  driver.quit();
	  }

	}
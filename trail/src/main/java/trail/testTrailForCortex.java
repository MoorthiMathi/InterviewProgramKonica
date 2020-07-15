package trail;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

import junit.framework.Assert;

public class testTrailForCortex {
	
	WebDriver driver;
	
	
	membervariables object = new membervariables();
	
	
	@BeforeMethod()
	public void Setup() {
		
		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}
	
	@Test(priority =1)
	public void FlowOneValidScenario() throws InterruptedException {
		
		driver.get("https://www.google.co.in/");
		driver.findElement(By.xpath("//*[text()='Sign in']")).click();
		Thread.sleep(2000);	
		
		object.setUserName("cortexdemotest120990@gmail.com");
		driver.findElement(By.xpath("//input[@type='email']")).sendKeys((object.getUserName()));
		driver.findElement(By.xpath("(//span[text()='Next'])/../div[2]")).click();
		
		
		object.setPassword("Password@123");
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys(object.getPassword());
		Thread.sleep(2000);
		driver.findElement(By.xpath("(//span[text()='Next'])/../div[2]")).click();
		Thread.sleep(5000);
		
		WebElement element = driver.findElement(By.xpath("//a[text()='Gmail']/following::div[6]/a/img"));
		
		boolean LoginIcon = element.isDisplayed();	// Verifying the User Icon is present at top right cornor
		Assert.assertEquals(true, LoginIcon);
		
		element.click();
		
		driver.findElement(By.xpath("//a[text()='Sign out']")).click();
		
		
	}

	
	@Test(priority =2)
	public void FlowOnenNegativeScenario() throws InterruptedException {
		
  /* In this tesecase, verifying whether user account is access by providing WRONG password.Expected Result should be FAILED */
		  
		
		driver.get("https://www.google.co.in/");
		driver.findElement(By.xpath("//*[text()='Sign in']")).click();
		Thread.sleep(2000);
		
		object.setUserName("cortexdemotest120990@gmail.com");
		driver.findElement(By.xpath("//input[@type='email']")).sendKeys((object.getUserName()));
		driver.findElement(By.xpath("(//span[text()='Next'])/../div[2]")).click();
		
		
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys("WRONG"); // Provided Wrong Password
		Thread.sleep(2000);
		driver.findElement(By.xpath("(//span[text()='Next'])/../div[2]")).click();
		Thread.sleep(5000);
		
		WebElement WrongCredentialMessage = driver.findElement(By.xpath("//*[contains(text(), 'Wrong password.')]"));
		
		System.out.println(WrongCredentialMessage.getText());
		
	}
	
	@Test(priority=3)
	public void FlowTwo() throws InterruptedException {
		
	  driver.get("https://www.ebay.com/");	
	  driver.findElement(By.xpath("//*[text()='Enter your search keyword']/../input")).sendKeys("Electirc Guitar");
	  driver.findElement(By.xpath("//input[@type='submit']")).click();
	  Thread.sleep(2000);
	  
	  WebElement FirstProductPrice = driver.findElement(By.xpath("//div[@class='s-item__image']/following::div[@class='s-item__details clearfix']/div/span[1]"));	 
	  System.out.println( "First Electric Guitar Product Price is - " + FirstProductPrice.getText());
	  
	}
	
	@AfterMethod
	public void tearDown() {
		driver.close();
		driver.quit();
	}
}

package Sandbox;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import SandboxPageObject.LogIn;
import TextFile.ReadingFromFile;

public class LogInTest {
	WebDriver driver;
	
	public static final String LOGIN_URL = "https://sandbox.2checkout.com/sandbox";
	public static final String LOGINERROR = "login-error";
	

	@BeforeClass
	public void startDriver() {
		System.setProperty("webdriver.chrome.driver", "/Users/Ivana/eclipse-workspace/SandboxWebPage/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get(LOGIN_URL);
	}

	@Test (priority = 1)
	public void LoginUser() {
		LogIn login = new LogIn(driver, ReadingFromFile.readXPaths());
		login.typeUsername("jcogley14");
		login.typePassword("y4FyhwdA5");
		login.clickLogInBtn();

		Assert.assertEquals(driver.getCurrentUrl(), "https://sandbox.2checkout.com/sandbox/home/dashboard");
	}
	
	@Test(priority = 2)
	public void LogInWithoutReg() {
		LogIn login = new LogIn(driver, ReadingFromFile.readXPaths());
		login.logOut();
		login.typeUsername("Gogica");
		login.typePassword("Dorasmora");
		login.clickLogInBtn();
		
		WebElement errorMessage = driver.findElement(By.id(LOGINERROR));
	    Assert.assertTrue(errorMessage.isDisplayed());	
	}

	@AfterClass
	  public void closeDriver() {
			driver.quit();
	  }
	
}

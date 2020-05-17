package Sandbox;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import TextFile.ReadingFromFile;
import SandboxPageObject.Registration;
import SandboxPageObject.LogIn;

public class RegistrationTest {
	
	WebDriver driver;
	WebDriverWait wait;
	SoftAssert sa = new SoftAssert();

	public static final String LOGIN_URL = "https://sandbox.2checkout.com/sandbox";
	public static final String USERNAME_MSG = "//*[@id=\"create_username\"]/div[1]/div[1]/div/div[2]/span";
	public static final String EMAIL_MSG = "//*[@id=\"create_username\"]/div[1]/div[1]/div/div[1]/span";
	public static final String PASSWORD_MSG = "//*[@id=\"create_username\"]/div[1]/div[1]/div/div[3]/span";
	public static final String PASSWORD_CONF_MSG = "//*[@id=\"create_username\"]/div[1]/div[1]/div/div[4]/span";
	
	@BeforeClass
	public void createDriver() {
		System.setProperty("webdriver.chrome.driver", "/Users/Ivana/eclipse-workspace/SandboxWebPage/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	
	@BeforeMethod
	public void logIn() {
		driver.navigate().to(LOGIN_URL);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		Registration registration = new Registration(driver, ReadingFromFile.readXPaths());
		registration.typeUsernameLogIn("jcogley14");
		registration.typePasswordLogIn("y4FyhwdA5");
		registration.clickLogInBtn();
	}
	
	
	@Test(priority = 1)
	public void RegistartionPage() {
		driver.navigate().to(LOGIN_URL);
		
		Registration registration = new Registration(driver, ReadingFromFile.readXPaths());
		
		registration.goToRegPage(driver);

		Assert.assertEquals(driver.getCurrentUrl(), "https://sandbox.2checkout.com/sandbox/acct/create_username");
	}
	
	@Test(priority = 2)
	public void validUserRegistration() throws InterruptedException {
		Registration registration = new Registration(driver, ReadingFromFile.readXPaths());
		registration.goToRegPage(driver);
		registration.typeEmail("hfamuple@gmail.com");
		registration.typeUsername("Purplel161dk3");
		registration.typePassword("PHaze123");
		registration.confirmPassword("PHaze123");
		registration.selectAccess();
		registration.createUsernameBtn();
		
		Assert.assertEquals(driver.getTitle(), "Update User");
	}
	
	@Test(priority = 3)
	public void InvalidRegistrationWithoutUsername() throws InterruptedException {
		Registration registration = new Registration(driver, ReadingFromFile.readXPaths());
		registration.goToRegPage(driver);
		registration.typeEmail("example@gogo.ca");
		registration.typePassword("crnTrn18");
		registration.confirmPassword("crnTrn18");
		registration.selectAccess();
		registration.createUsernameBtn();

		WebElement usernameErrorMessage = driver.findElement(By.xpath(USERNAME_MSG));
		Assert.assertTrue(usernameErrorMessage.isDisplayed());
	}
	
	@Test(priority = 4)
	public void InvalidRegistrationWithoutEmail() throws InterruptedException {
		Registration registration = new Registration(driver, ReadingFromFile.readXPaths());
		registration.goToRegPage(driver);
		registration.typeUsername("Smokvica");
		registration.typePassword("crnTrn18");
		registration.confirmPassword("crnTrn18");
		registration.selectAccess();
		registration.createUsernameBtn();

		WebElement emailErrorMessage = driver.findElement(By.xpath(EMAIL_MSG));
		Assert.assertTrue(emailErrorMessage.isDisplayed());
	}

	@Test(priority = 5)
	public void InvalidRegistrationWithoutPassword() throws InterruptedException {
		Registration registration = new Registration(driver, ReadingFromFile.readXPaths());
		registration.goToRegPage(driver);
		registration.typeEmail("example@gogo.ca");
		registration.typeUsername("Smokvica");
		registration.confirmPassword("crnTrn18");
		registration.selectAccess();
		registration.createUsernameBtn();

		WebElement passwordErrorMessage = driver.findElement(By.xpath(PASSWORD_MSG));
		Assert.assertTrue(passwordErrorMessage.isDisplayed());
	}
	
	@Test(priority = 6)
	public void InvalidRegistrationWithoutPassConfirm() throws InterruptedException {
		Registration registration = new Registration(driver, ReadingFromFile.readXPaths());
		registration.goToRegPage(driver);
		registration.typeEmail("example@gogo.ca");
		registration.typeUsername("KSmokvica");
		registration.typePassword("crnTrn18");
		registration.selectAccess();
		registration.createUsernameBtn();
		
		WebElement confirmPasswordErrorMessage = driver.findElement(By.xpath(PASSWORD_CONF_MSG));
		Assert.assertTrue(confirmPasswordErrorMessage.isDisplayed());
	}
	
	@Test(priority = 7)
	public void Register30Users() throws InterruptedException {

		for (int i = 1; i <= 30; i++) {

			Registration registration = new Registration(driver, ReadingFromFile.readXPaths());
			registration.goToRegPage(driver);
			
			String actualTitle = registration.Reg30Users(driver, i);
			sa.assertEquals(actualTitle, "Update User");
			sa.assertAll();
			
			registration.logOut();
			
			LogIn login = new LogIn(driver, ReadingFromFile.readXPaths());
			login.typeUsername("jcogley14");
			login.typePassword("y4FyhwdA5");
			login.clickLogInBtn();
		}
	}
	
	@AfterMethod
	public void LogOut () {
		Registration registration = new Registration(driver, ReadingFromFile.readXPaths());
		registration.logOut();
	}
	
	@AfterClass
	public void closeDriver() {
		driver.quit();
	}
	

}
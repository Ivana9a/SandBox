package SandboxPageObject;

import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LogIn {

	WebDriver driver;
	Map<String, String> Paths;
	
	private static final String USERNAME_XPATH = "USERNAME_XPATH";
	private static final String PASSWORD_XPATH = "PASSWORD_XPATH";
	private static final String LOGINBTN_XPATH = "LOGINBTN_XPATH";
	private static final String ACCOUNT_AVATAR_XPATH = "ACCOUNT_AVATAR_XPATH";
	private static final String LOGOUT_BTN_XPATH = "LOGOUT_BTN_XPATH";

	// constructor
	public LogIn (WebDriver driver, Map<String, String> Paths) {
		this.driver = driver;
		this.Paths = Paths;
	}
	
	public void typeUsername (String data) {
		driver.findElement(By.xpath(Paths.get(USERNAME_XPATH))).sendKeys(data);
		 
	}
	
	public void typePassword (String data) {
		driver.findElement(By.xpath(Paths.get(PASSWORD_XPATH))).sendKeys(data);
		
	}
	
	public void clickLogInBtn () {
		driver.findElement(By.xpath(Paths.get(LOGINBTN_XPATH))).click();
	}
	
	public void logOut() {
		driver.findElement(By.xpath(Paths.get(ACCOUNT_AVATAR_XPATH))).click();
		driver.findElement(By.xpath(Paths.get(LOGOUT_BTN_XPATH))).click();
	}

}

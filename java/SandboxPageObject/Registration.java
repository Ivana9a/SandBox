package SandboxPageObject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Registration {
	
	WebDriver driver;
	Map<String, String> Paths;
	
	private static final String ACCOUNT = "ACCOUNT";
	private static final String EMAIL_XPATH = "EMAIL_XPATH";
	private static final String USERNAMEREG = "USERNAMEREG";
	private static final String PASSWORDREG = "PASSWORDREG";
	private static final String CONFIRMPASS = "CONFIRMPASS";
	private static final String CREATE_BTN = "CREATE_BTN";
	private static final String USERMANAGEMENT = "USERMANAGEMENT";
	private static final String CREATEUSERNAME = "CREATEUSERNAME";
	private static final String ACCOUNT_AVATAR_XPATH = "ACCOUNT_AVATAR_XPATH";
	private static final String LOGOUT_BTN_XPATH = "LOGOUT_BTN_XPATH";
	private static final String USERNAME_XPATH = "USERNAME_XPATH";
	private static final String PASSWORD_XPATH = "PASSWORD_XPATH";
	private static final String LOGINBTN_XPATH = "LOGINBTN_XPATH";
	
	public Registration (WebDriver driver, Map<String, String> Paths) {
		this.driver = driver;
		this.Paths = Paths;
	}
	
	public void typeUsernameLogIn (String data) {
		driver.findElement(By.xpath(Paths.get(USERNAME_XPATH))).sendKeys(data);
		 
	}
	
	public void typePasswordLogIn (String data) {
		driver.findElement(By.xpath(Paths.get(PASSWORD_XPATH))).sendKeys(data);
		
	}
	
	public void clickLogInBtn () {
		driver.findElement(By.xpath(Paths.get(LOGINBTN_XPATH))).click();
	}

	
	public void goToRegPage(WebDriver driver) {
		//driver.findElement(By.xpath(Paths.get(LOGINBTN_XPATH))).click();
		driver.findElement(By.xpath(Paths.get(ACCOUNT))).click();
		driver.findElement(By.xpath(Paths.get(USERMANAGEMENT))).click();
		driver.findElement(By.xpath(Paths.get(CREATEUSERNAME))).click();
		
	}
	
	public void typeEmail (String data) {
		driver.findElement(By.xpath(Paths.get(EMAIL_XPATH))).sendKeys(data);
	}
	
	public void typeUsername (String data) {
		driver.findElement(By.xpath(Paths.get(USERNAMEREG))).sendKeys(data);
	}
	
	public void typePassword (String data) {
		driver.findElement(By.xpath(Paths.get(PASSWORDREG))).sendKeys(data);
	}
	
	public void confirmPassword (String data) {
		driver.findElement(By.xpath(Paths.get(CONFIRMPASS))).sendKeys(data);
	}
	
	public void selectAccess () {
		driver.findElement(By.id("sa-access")).click();
		driver.findElement(By.id("va_user1")).click();
	}
	
	public void createUsernameBtn () {
		driver.findElement(By.xpath(Paths.get(CREATE_BTN))).click();
	}
	
	public void registerNew () {
		
		driver.findElement(By.xpath(Paths.get(ACCOUNT))).click();
		driver.findElement(By.xpath(Paths.get(USERMANAGEMENT))).click();
		driver.findElement(By.xpath(Paths.get(CREATEUSERNAME))).click();
	}
	
	public static String getUsersData(int i, int j) {
		FileInputStream fis;
		XSSFWorkbook wb;
		try {
			fis = new FileInputStream("src\\main\\java\\TextFile\\MOCK_DATA (1).xlsx");
			wb = new XSSFWorkbook(fis);
			return wb.getSheetAt(0).getRow(i).getCell(j).toString();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return "Failed";
		} catch (IOException e) {
			e.printStackTrace();
			return "Failed";
		}
	}
	
	public String Reg30Users(WebDriver driver, int i) throws InterruptedException {

		driver.findElement(By.xpath(Paths.get(ACCOUNT))).click();
		driver.findElement(By.xpath(Paths.get(USERMANAGEMENT))).click();
		driver.findElement(By.xpath(Paths.get(CREATEUSERNAME))).click();
		
		Thread.sleep(1000);

		driver.findElement(By.xpath(Paths.get(USERNAMEREG))).sendKeys(getUsersData(i, 1));
		driver.findElement(By.xpath(Paths.get(EMAIL_XPATH))).sendKeys(getUsersData(i, 2));
		driver.findElement(By.xpath(Paths.get(PASSWORDREG))).sendKeys(getUsersData(i, 3));
		driver.findElement(By.xpath(Paths.get(CONFIRMPASS))).sendKeys(getUsersData(i, 4));
		driver.findElement(By.id("sa-access")).click();
		driver.findElement(By.id("va_user1")).click();
		
		Thread.sleep(3000);
		
		driver.findElement(By.xpath(Paths.get(CREATE_BTN))).click();
		

		return driver.getTitle();

	}
	
	public void logOut() {
		driver.findElement(By.xpath(Paths.get(ACCOUNT_AVATAR_XPATH))).click();
		driver.findElement(By.xpath(Paths.get(LOGOUT_BTN_XPATH))).click();
	}
}
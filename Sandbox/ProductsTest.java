package Sandbox;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import TextFile.ReadingFromFile;
import SandboxPageObject.LogIn;
import SandboxPageObject.Products;
import SandboxPageObject.Registration;

public class ProductsTest {

		WebDriver driver;
		SoftAssert sa = new SoftAssert();
		
		public String LOGIN_URL = "https://sandbox.2checkout.com/sandbox";
		By successful = By.xpath("//span[@class='form_valid large']");
		private static final String LUST_PRICE_XPATH = "//*[@id=\"update_bulk\"]/table/tbody/tr[2]/td[6]/input";
		private static final String AGONY_PRICE_XPATH = "//*[@id=\"update_bulk\"]/table/tbody/tr[3]/td[6]/input";
		private static final String ORIGIN_PRICE_XPATH = "//*[@id=\"update_bulk\"]/table/tbody/tr[4]/td[6]/input";
		private static final String ETERNAL_PRICE_XPATH = "//*[@id=\"update_bulk\"]/table/tbody/tr[5]/td[6]/input";
		private static final String LADY_PRICE_XPATH = "//*[@id=\"update_bulk\"]/table/tbody/tr[6]/td[6]/input";
		private static final String PRODUCTS_XPATH = "PRODUCTS_XPATH";
		
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
		public void GoToCreateProductPage() {
			Products products = new Products(driver, ReadingFromFile.readXPaths());
			products.goToCreateProductPage();

			Assert.assertEquals(driver.getCurrentUrl(), "https://sandbox.2checkout.com/sandbox/products/create_product");
		}

		@Test(priority = 2)
		public void testCreateProduct() throws InterruptedException {

			for (int i = 1; i <= 5; i++) {
				
				Products products = new Products(driver, ReadingFromFile.readXPaths());
				products.goToCreateProductPage();
				products.createProduct(driver, i);
				
				WebElement successfulAdd = driver.findElement(successful);
				sa.assertTrue(successfulAdd.isDisplayed());
				sa.assertAll();
				
				products.addNewProduct();
			}
		}
		
		@Test(priority = 3)
		public void updateProductsPrices() {
			Products products = new Products(driver, ReadingFromFile.readXPaths());
			
			products.goToCreateProductPage();
			products.editProductPage();
			
			products.updateLustForLifePrice(driver);
			sa.assertEquals(driver.findElement(By.xpath(LUST_PRICE_XPATH)).getAttribute("value"), "1095.4");
			
			products.updateAgonyAndEcstasyPrice(driver);
			sa.assertEquals(driver.findElement(By.xpath(AGONY_PRICE_XPATH)).getAttribute("value"), "1120.4");
			
			products.updateTheOriginPrice(driver);
			sa.assertEquals(driver.findElement(By.xpath(ORIGIN_PRICE_XPATH)).getAttribute("value"), "845.6");
			
			products.updateLoveIsEternalPrice(driver);
			sa.assertEquals(driver.findElement(By.xpath(ETERNAL_PRICE_XPATH)).getAttribute("value"), "848.4");
			
			products.updateThePresidentsLadyPrice(driver);
			sa.assertEquals(driver.findElement(By.xpath(LADY_PRICE_XPATH)).getAttribute("value"), "979.6");
			
			sa.assertAll();
		}

		@AfterMethod
		public void LogOut () {
			LogIn login = new LogIn(driver, ReadingFromFile.readXPaths());
			login.logOut();
		}
		
		@AfterClass
		public void closeDriver() {
			driver.quit();
		}

}

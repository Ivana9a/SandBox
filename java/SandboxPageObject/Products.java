package SandboxPageObject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import TextFile.ReadingFromFile;

public class Products {

	WebDriver driver;
	Map<String, String> Paths;

	public String name, short_description, long_description, tangible, recurring, approved_url;
	public int id;
	public double price;
	private static final String USERNAME_XPATH = "USERNAME_XPATH";
	private static final String PASSWORD_XPATH = "PASSWORD_XPATH";
	private static final String LOGINBTN_XPATH = "LOGINBTN_XPATH";
	private static final String PRODUCTS_XPATH = "PRODUCTS_XPATH";
	private static final String ADD_NEW_PRODUCT_XPATH = "ADD_NEW_PRODUCT_XPATH";
	private static final String PRODUCT_NAME_XPATH = "PRODUCT_NAME_XPATH";
	private static final String PRODUCT_ID_XPATH = "PRODUCT_ID_XPATH";
	private static final String PRODUCT_SHORT_DESCR_XPATH = "PRODUCT_SHORT_DESCR_XPATH";
	private static final String PRODUCT_LONG_DESCR_XPATH = "PRODUCT_LONG_DESCR_XPATH";
	private static final String PRODUCT_PRICE_XPATH = "PRODUCT_PRICE_XPATH";
	private static final String PRODUCT_TANGIBLE_NO_RADIO_BTN = "PRODUCT_TANGIBLE_NO_RADIO_BTN";
	private static final String PRODUCT_RECURRING_RADIO_BTN_XPATH = "PRODUCT_RECURRING_RADIO_BTN_XPATH";
	private static final String PRODUCT_SAVE_BTN = "PRODUCT_SAVE_BTN";
	private static final String PRODUCT_VIEW_XPATH = "PRODUCT_VIEW_XPATH";
	private static final String PRODUCT_EDIT_XPATH = "PRODUCT_EDIT_XPATH";
	private static final String LUST_PRICE_XPATH = "LUST_PRICE_XPATH";
	private static final String AGONY_PRICE_XPATH = "AGONY_PRICE_XPATH";
	private static final String ORIGIN_PRICE_XPATH = "ORIGIN_PRICE_XPATH";
	private static final String ETERNAL_PRICE_XPATH = "ETERNAL_PRICE_XPATH";
	private static final String LADY_PRICE_XPATH = "LADY_PRICE_XPATH";
	private static final String SAVE_PRODUCT_CHANGES_BTN_XPATH = "SAVE_PRODUCT_CHANGES_BTN_XPATH";
	private static final String EDIT_PRODUCTS_XPATH = "EDIT_PRODUCTS_XPATH";
	//private static final String LUST_PRICE_UPDATED_XPATH = "LUST_PRICE_UPDATED_XPATH";

	public Products(String name, int id, String short_description, String long_description, double price,
			String tangible, String recurring, String approved_url) {

		this.name = name;
		this.id = id;
		this.short_description = short_description;
		this.long_description = long_description;
		this.price = price;
		this.tangible = tangible;
		this.recurring = recurring;
		this.approved_url = approved_url;
	}

	public Products(WebDriver driver, Map<String, String> Paths) {
		this.driver = driver;
		this.Paths = Paths;
	}
	
	/*public void typeUsernameLogIn (String data) {
		driver.findElement(By.xpath(Paths.get(USERNAME_XPATH))).sendKeys(data);
		 
	}
	
	public void typePasswordLogIn (String data) {
		driver.findElement(By.xpath(Paths.get(PASSWORD_XPATH))).sendKeys(data);
		
	}
	
	public void clickLogInBtn () {
		driver.findElement(By.xpath(Paths.get(LOGINBTN_XPATH))).click();
	}*/

	public void goToCreateProductPage() {
		driver.findElement(By.xpath(Paths.get(PRODUCTS_XPATH))).click();
		driver.findElement(By.xpath(Paths.get(ADD_NEW_PRODUCT_XPATH))).click();
	}
	
	public void editProductPage() {
		driver.findElement(By.xpath(Paths.get(PRODUCTS_XPATH))).click();
		driver.findElement(By.xpath(Paths.get(EDIT_PRODUCTS_XPATH))).click();
		
	}

	public static String getProductsData(int i, int j) {
		FileInputStream fis;
		XSSFWorkbook wb;
		try {
			fis = new FileInputStream("src\\main\\java\\TextFile\\Products.xlsx");
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

	public void createProduct(WebDriver driver, int i) throws InterruptedException {

		
		driver.findElement(By.xpath(Paths.get(PRODUCT_NAME_XPATH))).sendKeys(getProductsData(i, 0));
		driver.findElement(By.xpath(Paths.get(PRODUCT_ID_XPATH))).sendKeys(getProductsData(i, 1));
		driver.findElement(By.xpath(Paths.get(PRODUCT_SHORT_DESCR_XPATH))).sendKeys(getProductsData(i, 2));
		driver.findElement(By.xpath(Paths.get(PRODUCT_LONG_DESCR_XPATH))).sendKeys(getProductsData(i, 3));
		driver.findElement(By.xpath(Paths.get(PRODUCT_PRICE_XPATH))).sendKeys(getProductsData(i, 4));
		driver.findElement(By.xpath(Paths.get(PRODUCT_TANGIBLE_NO_RADIO_BTN))).click();
		driver.findElement(By.xpath(Paths.get(PRODUCT_RECURRING_RADIO_BTN_XPATH))).click();
		driver.findElement(By.xpath(Paths.get(PRODUCT_SAVE_BTN))).click();

		Thread.sleep(2000);

	}

	public void addNewProduct() {
		driver.findElement(By.xpath(Paths.get(PRODUCT_VIEW_XPATH))).click();
		driver.findElement(By.xpath(Paths.get(ADD_NEW_PRODUCT_XPATH))).click();
	}

	public void updateLustForLifePrice(WebDriver driver) {
		driver.findElement(By.xpath(Paths.get(PRODUCT_EDIT_XPATH))).click();

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,500)");

		String lust = driver.findElement(By.xpath(Paths.get(LUST_PRICE_XPATH))).getAttribute("value");
		double lustPrice = Double.parseDouble(lust);
		double lustNewPrice = lustPrice + 100;
		String lustUpdated = String.valueOf(lustNewPrice);

		System.out.println(Paths.get(LUST_PRICE_XPATH));
		WebElement lustUpdatedPrice = driver.findElement(By.xpath(Paths.get(LUST_PRICE_XPATH)));
		lustUpdatedPrice.clear();
		lustUpdatedPrice.sendKeys(lustUpdated);

		driver.findElement(By.xpath(Paths.get(SAVE_PRODUCT_CHANGES_BTN_XPATH))).click();
	}

		public void updateAgonyAndEcstasyPrice(WebDriver driver) {
		driver.findElement(By.xpath(Paths.get(PRODUCT_EDIT_XPATH))).click();

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,500)");

		String agony = driver.findElement(By.xpath(Paths.get(AGONY_PRICE_XPATH))).getAttribute("value");
		double agonyPrice = Double.parseDouble(agony);
		double agonyNewPrice = agonyPrice + 100;
		String agonyUpdated = String.valueOf(agonyNewPrice);

		WebElement agonyUpdatedPrice = driver.findElement(By.xpath(Paths.get(AGONY_PRICE_XPATH)));
		agonyUpdatedPrice.clear();
		agonyUpdatedPrice.sendKeys(agonyUpdated);
		
		driver.findElement(By.xpath(Paths.get(SAVE_PRODUCT_CHANGES_BTN_XPATH))).click();
	}

	public void updateTheOriginPrice(WebDriver driver) {
		driver.findElement(By.xpath(Paths.get(PRODUCT_EDIT_XPATH))).click();

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,500)");

		String origin = driver.findElement(By.xpath(Paths.get(ORIGIN_PRICE_XPATH))).getAttribute("value");
		double originPrice = Double.parseDouble(origin);
		double originNewPrice = originPrice + 100;
		String originUpdated = String.valueOf(originNewPrice);

		WebElement originUpdatedPrice = driver.findElement(By.xpath(Paths.get(ORIGIN_PRICE_XPATH)));
		originUpdatedPrice.clear();
		originUpdatedPrice.sendKeys(originUpdated);
		
		driver.findElement(By.xpath(Paths.get(SAVE_PRODUCT_CHANGES_BTN_XPATH))).click();
	}

	public void updateLoveIsEternalPrice(WebDriver driver) {
		driver.findElement(By.xpath(Paths.get(PRODUCT_EDIT_XPATH))).click();

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,500)");

		String eternal = driver.findElement(By.xpath(Paths.get(ETERNAL_PRICE_XPATH))).getAttribute("value");
		double eternalPrice = Double.parseDouble(eternal);
		double matNewPrice = eternalPrice + 100;
		String matUpdated = String.valueOf(matNewPrice);

		WebElement eternalUpdatedPrice = driver.findElement(By.xpath(Paths.get(ETERNAL_PRICE_XPATH)));
		eternalUpdatedPrice.clear();
		eternalUpdatedPrice.sendKeys(matUpdated);
		
		driver.findElement(By.xpath(Paths.get(SAVE_PRODUCT_CHANGES_BTN_XPATH))).click();
	}

	public void updateThePresidentsLadyPrice(WebDriver driver) {
		driver.findElement(By.xpath(Paths.get(PRODUCT_EDIT_XPATH))).click();

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,500)");

		String lady = driver.findElement(By.xpath(Paths.get(LADY_PRICE_XPATH))).getAttribute("value");
		double ladyPrice = Double.parseDouble(lady);
		double ladyNewPrice = ladyPrice + 100;
		String gUpdated = String.valueOf(ladyNewPrice);

		WebElement ladyUpdatedPrice = driver.findElement(By.xpath(Paths.get(LADY_PRICE_XPATH)));
		ladyUpdatedPrice.clear();
		ladyUpdatedPrice.sendKeys(gUpdated);
		
		driver.findElement(By.xpath(Paths.get(SAVE_PRODUCT_CHANGES_BTN_XPATH))).click();
	}

}

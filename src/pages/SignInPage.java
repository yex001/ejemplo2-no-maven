package pages;

import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import utils.ExcelUtils;

public class SignInPage {
	private WebDriver driver;
	private Properties locators;
	private WebDriverWait waiter;

	public SignInPage(WebDriver driver, Properties locators, WebDriverWait waiter) {
		this.driver = driver;
		this.locators = locators;
		this.waiter = waiter;
	}

	public WebElement getUsername() {
		return this.driver.findElement(By.xpath(this.locators.getProperty("usernameLog")));
	}

	public void setUsername(String userId) {
		this.getUsername().clear();
		this.getUsername().sendKeys(userId);
	}

	public WebElement getPassword() {
		return this.driver.findElement(By.xpath(this.locators.getProperty("passwordLog")));
	}

	public void setPassword(String password) {
		this.getPassword().clear();
		this.getPassword().sendKeys(password);
	}

	public WebElement getSignInBtn() {
		return this.driver.findElement(By.xpath(this.locators.getProperty("signInBtn")));
	}

	// Sign in method using Excel data
	public void signIn() {
		ExcelUtils.setExcell(this.locators.getProperty("dataSource"));
		ExcelUtils.setWorkSheet(1);

		for (int i = 1; i < ExcelUtils.getRowNumber() - 1; i++) {
			driver.navigate().to(this.locators.getProperty("signInUrl"));
			this.setUsername(ExcelUtils.getDataAt(i, 0));
			this.setPassword(ExcelUtils.getDataAt(i, 1));
			this.getSignInBtn().click();
		}
		ExcelUtils.closeExcell();
	}

	public WebElement getWelcomeMsg() {
		return this.driver.findElement(By.xpath(this.locators.getProperty("welcomeMsg")));
	}

	public boolean checkSignIn() {
		return this.getWelcomeMsg().getText().contains("Welcome");
	}
}

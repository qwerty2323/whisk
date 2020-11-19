package pages;

import org.openqa.selenium.*;

public final class MainPage {
	private WebDriver driver;
	private String baseUrl = "https://my.whisk-dev.com";
	private By signUpForm = By.xpath("//input[@placeholder='Email or phone number']");
	private By continueButton = By.xpath("//button[type='Submit' and contains(., 'Continue')]");
	private By shoppingCart = By.xpath("//div[contains(., 'Shopping']");
	
	public MainPage(WebDriver driver) {
		this.driver = driver;
		driver.get(baseUrl);
	}
	
	public WebElement signUpForm() {
		return driver.findElement(signUpForm);
	}
	
	public WebElement continueSignUpButton() {
		return driver.findElement(continueButton);
	}
	
	public WebElement shoppingCartPage() {
		return driver.findElement(shoppingCart);
	}
}

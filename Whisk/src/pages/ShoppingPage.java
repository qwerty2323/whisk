package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public final class ShoppingPage {
	private WebDriver driver;
		
	public ShoppingPage(WebDriver driver) {
		this.driver = driver;
	}
	
	public WebElement createListButton() {
		return driver.findElement(By.xpath("//a[@data-testid='create-new-shopping-list-button']"));
	}
	
	public WebElement createListInput() {
		return driver.findElement(By.xpath("//input[@type='placeholder' and contains(., 'Name your list')"));
	}
	
	public WebElement deleteListButton() {
		return driver.findElement(
				By.xpath("//button[@data-testid='shopping-list-delete-menu-button']"));
	}
	
	public WebElement editListButton(String name) {
		String editButtonId = "shopping-lists-list-name";
		By editList = By.xpath(String.format(
				"//div[@data-testid='%s' and contains(., %s)]/../following-sibling::node()", editButtonId, name));
		return driver.findElement(editList);
	}
	
	public WebElement searchBar() {
		return driver.findElement(By.xpath("//input[@placeholder = 'Add item']"));
	}
	
	public WebElement addItem(String name) {
		By item = By.xpath(String.format("//div[@data-testid='autocomplete-item']/span[contains(., %s]", name));
		return driver.findElement(item);
	}
	
	public List<WebElement> lists() {
		return driver.findElements(By.xpath("//div[@data-testid='shopping-lists-list-name']"));
	}
	
	public List<WebElement> listItems() {
		return driver.findElements(By.xpath("//span[@data-testid='shopping-list-item-name']"));
	}
	
	public WebElement getItemFromList(String name) {
		return driver.findElement(By.xpath(String.format("//span[@data-testid='shopping-list-item-name' and contains[., '%s']", name)));
	}
}

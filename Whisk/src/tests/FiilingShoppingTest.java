package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.UUID;

import org.junit.*;
import org.openqa.selenium.*;

import pages.MainPage;
import pages.ShoppingPage;


public class FiilingShoppingTest {
	private WebDriver driver;
	private MainPage mainPage;
	private ShoppingPage shoppingPage;
	private String shoppingListName;
	
	@Before
	public void setUp() throws Exception {
		driver = TestBase.getDriver();
				
		signUp();
		
		shoppingPage = new ShoppingPage(driver);
		shoppingPage.createListButton().click();
		shoppingListName = UUID.randomUUID().toString();
		shoppingPage.createListButton().sendKeys(shoppingListName + Keys.ENTER);
	}

	@After
	public void tearDown() throws Exception {
		driver.manage().deleteAllCookies();
		driver.quit();
	}

	@Test
	public void fillInShoppingListFromPopularTab() {
		assertEquals(2, shoppingPage.lists().size());
		assertEquals(0, shoppingPage.listItems().size());
		
		shoppingPage.searchBar().click();
		
		shoppingPage.addItem("Milk").click();
		shoppingPage.addItem("Bread").click();
		shoppingPage.addItem("Onions").click();
		shoppingPage.addItem("Butter").click();
		shoppingPage.addItem("Cheese").click();
		
		assertEquals(5, shoppingPage.listItems().size());
		
		assertNotNull(shoppingPage.getItemFromList("Milk"));
		assertNotNull(shoppingPage.getItemFromList("Bread"));
		assertNotNull(shoppingPage.getItemFromList("Onions"));
		assertNotNull(shoppingPage.getItemFromList("Butter"));
		assertNotNull(shoppingPage.getItemFromList("Cheese"));
	}
	
	@Test
	public void deleteExistingList() {
		assertEquals(2, shoppingPage.lists().size());
		
		shoppingPage.editListButton(shoppingListName).click();
		shoppingPage.deleteListButton().click();
		
		assertEquals(1, shoppingPage.lists().size());
	}
	
	private void signUp() {
		mainPage = new MainPage(driver);
		mainPage.signUpForm().click();
		mainPage.signUpForm().sendKeys(getRandomEmail());
		mainPage.continueSignUpButton().click();
	}
	
	private String getRandomEmail() {
		String username = UUID.randomUUID().toString();
		
		return String.format("%s@%gmail.com", username);
	}

}

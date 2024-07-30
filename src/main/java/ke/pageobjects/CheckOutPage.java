package ke.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import ke.AbstractComponent.AbstractComponent;

public class CheckOutPage extends AbstractComponent {

	WebDriver driver;

	public CheckOutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}
	
	@FindBy(css = "[placeholder='Select Country']")
	WebElement countryDropdown;
	
	@FindBy(xpath = "(//button[contains(@class,'ta-item')])[2]")
	WebElement Selectcountry;
	
	@FindBy(css = ".action__submit")
	WebElement PlaceOrder;
	
	By country = By.cssSelector(".ta-results");
	
	public void selectCountry(String Country) {
	Actions a = new Actions(driver);
	a.sendKeys(countryDropdown, Country).build().perform();
	WaitForElementToApper(country);
	Selectcountry.click();
	}
	
	public ConfromationPage submit() {
	
	PlaceOrder.click();
	return new ConfromationPage(driver);
	}
	
	

}

package ke.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import ke.AbstractComponent.AbstractComponent;

public class CartPage extends AbstractComponent {

	WebDriver driver;

	public CartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	@FindBy(xpath = "//*[@class='cartSection']/h3")
	List<WebElement> cartProducts;
	
	@FindBy(css = ".totalRow button")
	WebElement checkout;
	
	
	

	public Boolean verifyProductDisply(String productName) {

		Boolean match = cartProducts.stream().anyMatch(a -> a.getText().equalsIgnoreCase(productName));
		return match;

	}
	public CheckOutPage checkout() {
		checkout.click();
		return new CheckOutPage(driver);
	}

}
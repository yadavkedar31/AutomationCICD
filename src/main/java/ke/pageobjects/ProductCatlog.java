package ke.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import ke.AbstractComponent.AbstractComponent;

public class ProductCatlog extends AbstractComponent {

	WebDriver driver;

	public ProductCatlog(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}
	// normal style method
	// WebElement useremail = driver.findElement(By.id("userEmail"));

	// Using page factory
	//List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
	@FindBy(css = ".mb-3")
	List<WebElement> products;
	
	@FindBy(css = ".ng-animating")
	WebElement spinner;
	
	
	
	By productsBy = By.cssSelector(".mb-3");
	By addToCart = By.cssSelector(".card-body button:last-of-type");
	By tostmsg = By.cssSelector("#toast-container");

	public List<WebElement> GetProductList()
	{
		WaitForElementToApper(productsBy);
		return products;
	}
	
	public WebElement getProductByName(String productName)
	{
		WebElement prod = GetProductList().stream()
				.filter(product -> product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst()
				.orElse(null);
		return prod;
				
	}
	
	public void addProductToCart(String productName) throws InterruptedException
	{
		WebElement prod =  getProductByName(productName);
		prod.findElement(addToCart).click();
		WaitForElementToApper(tostmsg);
		WaitForElementToDisApper(spinner);
	}
}

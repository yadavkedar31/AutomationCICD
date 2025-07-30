package ke.AbstractComponent;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import ke.pageobjects.CartPage;
import ke.pageobjects.orderPage;

public class AbstractComponent {

	WebDriver driver;

	public AbstractComponent(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	
	@FindBy(css = "[routerlink*='cart']")
	WebElement AddCart;
	
	@FindBy(css = "[routerlink*='myorders']")
	WebElement orderHeader;
	

	public void WaitForElementToApper(By findBy) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}


	public void WaitForWebElementToApper(WebElement findBy) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(findBy));
	}
	
	
	public CartPage goToCartPage()
	{
		AddCart.click();
		CartPage CartPage = new CartPage(driver);
		return CartPage;
	}
	
	public orderPage goTOorderPage()
	{
		orderHeader.click();
		orderPage orderPage = new orderPage(driver);
		return orderPage;
	}
	
	
	
	public void WaitForElementToDisApper(WebElement ele) throws InterruptedException {
		
		Thread.sleep(1000);
//		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//
//		wait.until(ExpectedConditions.invisibilityOf(ele));
	}

}

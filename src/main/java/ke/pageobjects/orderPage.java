package ke.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import ke.AbstractComponent.AbstractComponent;

public class orderPage extends AbstractComponent {

	WebDriver driver;

	public orderPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	@FindBy(css = "tr td:nth-child(3)")
	List<WebElement> ProductNames;
	
	
	
	

	public Boolean verifyOrderDisply(String productName) {

		Boolean match = ProductNames.stream().anyMatch(a -> a.getText().equalsIgnoreCase(productName));
		return match;

	}
	

}
package ke.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import ke.AbstractComponent.AbstractComponent;

public class ConfromationPage extends AbstractComponent {

	WebDriver driver;

	public ConfromationPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}
	
	@FindBy(css = ".hero-primary")
	WebElement ConfirmMsg;
	
	
	public String getConfirmMsg() {
	
	return ConfirmMsg.getText();
	
	}
	
	
	
	
}

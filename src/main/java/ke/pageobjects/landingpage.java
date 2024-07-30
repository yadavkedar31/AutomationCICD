package ke.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import ke.AbstractComponent.AbstractComponent;

public class landingpage extends AbstractComponent {

	WebDriver driver;

	public landingpage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}
	// normal style method
	// WebElement useremail = driver.findElement(By.id("userEmail"));

	// Using page factory

	@FindBy(id = "userEmail")
	WebElement useremail;

	@FindBy(id = "userPassword")
	WebElement userPassword;

	@FindBy(id = "login")
	WebElement Submit;

	@FindBy(css ="[class*='flyInOut']")
	WebElement ErrorMsg;

	public ProductCatlog LoginApplication(String email, String password) {
		useremail.sendKeys(email);
		userPassword.sendKeys(password);
		Submit.click();
		ProductCatlog ProductCatlog = new ProductCatlog(driver);
		return ProductCatlog;

	}
	
	public String ErrorMessage()
	{
		WaitForWebElementToApper(ErrorMsg);
		return ErrorMsg.getText();
	} 
	
	

	public void Goto() {
		driver.get("https://rahulshettyacademy.com/client");
	}

}

package ke.tests;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import ke.TestComponent.BaseTest;
import ke.TestComponent.retry;
import ke.pageobjects.CartPage;
import ke.pageobjects.ProductCatlog;

public class ErrorValidations extends BaseTest {

	@Test(groups= {"ErrorHandling"})
	public void loginError() throws IOException, InterruptedException {

		//String productName = "ZARA COAT 3";

		landingpage.LoginApplication("31kedaryadav@gmail.com", "Ked123");

		Assert.assertEquals("Incorrect email or password.", landingpage.ErrorMessage());

	}
	
	
	@Test(retryAnalyzer=retry.class)
	public void productErrorValidation() throws InterruptedException {
		String productName = "ZARA COAT 3";

		ProductCatlog ProductCatlog = landingpage.LoginApplication("31-1kedaryadav@gmail.com", "Ked@r123");
		List<WebElement> products = ProductCatlog.GetProductList();
		ProductCatlog.addProductToCart(productName);
		CartPage CartPage = ProductCatlog.goToCartPage();
		Boolean match = CartPage.verifyProductDisply("ZARA COAT 33");
		Assert.assertFalse(match);
		System.out.println(match);
	}

}

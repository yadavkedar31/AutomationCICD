package ke.stepDefinations;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import ke.TestComponent.BaseTest;
import ke.pageobjects.CartPage;
import ke.pageobjects.CheckOutPage;
import ke.pageobjects.ConfromationPage;
import ke.pageobjects.ProductCatlog;
import ke.pageobjects.landingpage;

public class stepdefinationsimpl extends BaseTest {
	public landingpage landinPage;
	public ProductCatlog ProductCatlog;
	public ConfromationPage ConfromationPage;

	@Given("I landed on Ecommerce page")
	public void i_landed_on_Ecommerce_page() throws IOException {
		landinPage = launchApplication();
	}

	@Given("^Logged in with username (.+) and password (.+)$")
	public void Logged_in_with_username_and_password(String username, String password) {
		ProductCatlog = landingpage.LoginApplication(username, password);
	}

	@When("^I add product (.+) to cart$")
	public void add_product_to_cart(String productName) throws InterruptedException {
		List<WebElement> products = ProductCatlog.GetProductList();
		ProductCatlog.addProductToCart(productName);
	}

	@When("^checkout (.+) and submit the order$")
	public void checkout_and_submit_the_order(String productName) {
		CartPage CartPage = ProductCatlog.goToCartPage();
		Boolean match = CartPage.verifyProductDisply(productName);
		Assert.assertTrue(match);
		System.out.println(match);
		CheckOutPage CheckOutPage = CartPage.checkout();
		CheckOutPage.selectCountry("India");
		ConfromationPage = CheckOutPage.submit();
	}

	@Then("{string} message is displayed on ConfirmationPage")
	public void message_is_displayed_on_ConfirmationPage(String string) {
		String ConfirmMsg = ConfromationPage.getConfirmMsg();
		Assert.assertTrue(ConfirmMsg.equalsIgnoreCase(string));
		driver.close();
	}
	
	@Then("^\"([^\"]*)\" message is displayed$")
	public void somthing_message_is_displayed(String strArg1) throws Throwable
	{
		Assert.assertEquals(strArg1, landingpage.ErrorMessage());
		driver.close();
	}

}

package ke.tests;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.sun.net.httpserver.Authenticator.Retry;

import io.github.bonigarcia.wdm.WebDriverManager;
import ke.TestComponent.BaseTest;
import ke.TestComponent.retry;
import ke.pageobjects.CartPage;
import ke.pageobjects.CheckOutPage;
import ke.pageobjects.ConfromationPage;
import ke.pageobjects.ProductCatlog;
import ke.pageobjects.landingpage;
import ke.pageobjects.orderPage;

public class StandloneTest extends BaseTest {
	String productName = "ZARA COAT 3";

	@Test(dataProvider = "getData", groups = { "purchase" },retryAnalyzer=retry.class)
	public void submitOrder(HashMap<String, String> input) throws IOException, InterruptedException {

		ProductCatlog ProductCatlog = landingpage.LoginApplication(input.get("email"), input.get("pass"));
		List<WebElement> products = ProductCatlog.GetProductList();
		ProductCatlog.addProductToCart(input.get("product"));
		CartPage CartPage = ProductCatlog.goToCartPage();
		Boolean match = CartPage.verifyProductDisply(input.get("product"));
		Assert.assertTrue(match);
		System.out.println(match);
		CheckOutPage CheckOutPage = CartPage.checkout();
		CheckOutPage.selectCountry("India");
		ConfromationPage ConfromationPage = CheckOutPage.submit();
		String ConfirmMsg = ConfromationPage.getConfirmMsg();
		Assert.assertTrue(ConfirmMsg.equalsIgnoreCase("THANKYOU FOR THE ORDER."));

	}

	@Test(dependsOnMethods = { "submitOrder" }, retryAnalyzer=retry.class)
	public void OrderHistoryTest() {

		// zara-Coat3 present in oerder history
		ProductCatlog ProductCatlog = landingpage.LoginApplication("31kedaryadav@gmail.com", "Ked@r123");
		orderPage orderPage = ProductCatlog.goTOorderPage();
		Assert.assertTrue(orderPage.verifyOrderDisply(productName));
	}

	@DataProvider
	public Object[][] getData() throws IOException {

//		
		List<HashMap<String, String>> data = getJsonDataToHashMap(
				System.getProperty("user.dir") + "//src//test//java//ke//data//PurchaceOrder.json");

		return new Object[][] { { data.get(0) }, { data.get(1) } };

	}
//1st method
//	@DataProvider
//	public Object[][] getData() {
//		return new 	Object[][] { { "31kedaryadav@gmail.com", "Ked@r123", "ZARA COAT 3" },
//				{ "31-1kedaryadav@gmail.com", "Ked@r123", "ADIDAS ORIGINAL" } };

//	HashMap<String,String> map = new HashMap<String,String>();
//	map.put("email", "31kedaryadav@gmail.com");
//	map.put("pass", "Ked@r123");
//	map.put("product", "ZARA COAT 3");
//	
//	
//	HashMap<String,String> map1 = new HashMap<String,String>();
//	map1.put("email", "31-1kedaryadav@gmail.com");
//	map1.put("pass", "Ked@r123");
//	map1.put("product", "ADIDAS ORIGINAL");
	
	
}

package ke.TestComponent;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.bonigarcia.wdm.WebDriverManager;
import ke.pageobjects.landingpage;

public class BaseTest {

	public WebDriver driver;
	public landingpage landingpage;

	public WebDriver initializeDriver() throws IOException {

		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + "//src//main//java//ke//resources//GlobalData.properties");
		prop.load(fis);
		String browserName = System.getProperty("browser")!= null ? System.getProperty("browser"):prop.getProperty("browser");

		if (browserName.contains("chrome")) {
			ChromeOptions options = new ChromeOptions();
			WebDriverManager.chromedriver().setup();
			if (browserName.contains("headless"))
			{
				options.addArguments("headless");
			}
			driver = new ChromeDriver();
			driver.manage().window().setSize(new Dimension (1440,900));

		} else if (browserName.equalsIgnoreCase("firefox")) {
			// firefox
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();

			
		} else if (browserName.equalsIgnoreCase("Edge")) {
			// Edge
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		return driver;
	}

	@BeforeMethod(alwaysRun = true)
	public landingpage launchApplication() throws IOException {

		driver = initializeDriver();
		landingpage = new landingpage(driver);
		landingpage.Goto();
		return landingpage;
	}

	@AfterMethod(alwaysRun = true)
	public void quit() {
		driver.close();
	}

	public String getScreenShot(String testcase , WebDriver driver ) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File Src = ts.getScreenshotAs(OutputType.FILE);
		File File = new File(System.getProperty("user.dir")+ "//reports//"+testcase+".png");
		FileUtils.copyFile(Src, File);
		return System.getProperty("user.dir")+ "//reports//"+testcase+".png";
	}

	public List<HashMap<String, String>> getJsonDataToHashMap(String FilePath) throws IOException {
		// Reading Json to string
		String JsonContent = FileUtils.readFileToString(new File(FilePath), StandardCharsets.UTF_8);

		// COnvert String to HashMap Jackson DataBind
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(JsonContent,
				new TypeReference<List<HashMap<String, String>>>() {
				});
		return data;

	}

}

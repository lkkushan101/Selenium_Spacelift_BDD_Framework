package stepdefs;

import java.util.concurrent.TimeUnit;
import static org.assertj.core.api.Assertions.assertThat;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import ExtentReport.ExtentReport;
import PageObject.*;
import JSONRead.*;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;

import org.apache.log4j.Logger;


public class LoginScenario {
	     WebDriver driver;

	    Login objLogin;

	    HomePage objHomePage;
	
	    ExtentReport extRpt = new ExtentReport();
	      
	    JSONReader jsonRead = new JSONReader();
	    Logger log = Logger.getLogger("devpinoyLogger");
	@Given("^I have user name and password$")
	public void i_have_user_name_and_password() throws Throwable {
		 //Integrated WebDriver Manager 
		  WebDriverManager.chromedriver().setup();
		  //  System.setProperty("webdriver.chrome.driver",".\\Drivers\\chromedriver.exe");
	        driver = new ChromeDriver();

	        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

	        
	        driver.get(jsonRead.jsonRead(".\\Data\\sel.json", "URL"));
	        log.debug("Navigating to Login Page");
	        extRpt.setUpReport();
	  
	}
	@When("^I successfully login$")
	public void i_successfully_login() throws Throwable {
	  
		   extRpt.startTestCase("User Login");
	
		    objLogin = new Login(driver);

		    //Verify login page title

		   
		    
		    String loginPageTitle = objLogin.getLoginTitle();

		    Assert.assertTrue(loginPageTitle.toLowerCase().contains(jsonRead.jsonRead(".\\Data\\sel.json", "TITLE")));

		    //login to application

		    objLogin.loginToGuru99(jsonRead.jsonRead(".\\Data\\sel.json", "USER_NAME"), jsonRead.jsonRead(".\\Data\\sel.json", "PASSWORD"));
		    log.debug("Entered the user name and password");
		    extRpt.logEvents("Logged Successfully");

	   
	}
	
	@Then("^I should navigate to the home page$")
	public void i_should_navigate_to_the_home_page() throws Throwable {
		 objHomePage = new HomePage(driver);

		    //Verify home page

		 objHomePage = new HomePage(driver);

		    //Verify home page

		    assertThat(objHomePage.getHomePageDashboardUserName().toLowerCase()).isEqualTo("manger id : mgr123");
		   
		    driver.close();
		    extRpt.createFinalReport();
		    log.debug("Creating final report");
	
	}

}

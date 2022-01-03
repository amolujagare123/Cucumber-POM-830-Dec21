package stepdefinition;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import pages.Login;

import static stepdefinition.SharedSD.getDriver;

public class LoginSD {

    Login login = new Login();

    @Given("^I am on the darksky Login page$")
    public void i_am_on_the_darksky_login_page()  {

        login.clickDarkSkyAPI();
        login.clickLnkLogin();

    }

    @When("^I click on Login button$")
    public void i_click_on_login_button() {
      login.clickLoginBtn();
    }

    @Then("^I verify I am on Login page by asserting Login page title$")
    public void i_verify_i_am_on_login_page_by_asserting_login_page_title()
             {

                 String expected ="Dark Sky API: Log In";
                 String actual = getDriver().getTitle();
                 System.out.println("Expected="+expected);
                 System.out.println("Actual="+actual);
                 Assert.assertEquals(expected,actual);
             }

}

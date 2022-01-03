package stepdefinition;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import pages.SearchResult;

import java.util.ArrayList;

public class HotelsSearchResultSD {

    SearchResult searchResult = new SearchResult();

    @Given("^I am on default locations search result screen$")
    public void i_am_on_default_locations_search_result_screen() throws Throwable {
       // Assert
    }

    @When("^I select option for stars as (.+)$")
    public void i_select_option_for_stars_as(String stars) throws Throwable {

         // stars --> 5 stars

        searchResult.clickStarRating(stars.split(" ")[0]);
    }

    @Then("^I verify system displays only (.+) hotels on search result$")
    public void i_verify_system_displays_only_hotels_on_search_result(String stars) throws Throwable {

        int expected = Integer.parseInt(stars.split(" ")[0]);
        int actual = searchResult.countRealStarRatingOnSearchPage();

        System.out.println("expected="+expected);
        System.out.println("actual="+actual);

        Assert.assertEquals(expected,actual);
    }

    @Then("^I verify \"([^\"]*)\" is within search result$")
    public void i_verify_something_is_within_search_result(String hotelName)
             {

                 ArrayList<String> hotelList = searchResult.getHotelsList();

                 boolean result = hotelList.contains(hotelName);

                 System.out.println("Expected hotel="+hotelName);
                 for (String hotel:hotelList)
                     System.out.println(hotel);

                 Assert.assertTrue(hotelName+":This hotel is not in the search result",result);
    }

    @Then("^I verify system displays all hotels within \"([^\"]*)\" Km radius from center$")
    public void i_verify_system_displays_all_hotels_within_something_km_radius_from_center(String expectedDistance) throws Throwable {

        ArrayList<Double> distList = searchResult.getDistanceList();
        System.out.println(distList);

        int expectedDistInt = Integer.parseInt(expectedDistance);

        boolean flag = true;

        for (int i=0;i<distList.size();i++)
        {
            if (expectedDistInt<distList.get(i))
            {
                flag = false;
            }
        }

        Assert.assertTrue("Some of the distances are greater than "+expectedDistance,flag);
    }
}

package pages;

import org.openqa.selenium.By;

import java.util.ArrayList;

import static stepdefinition.SharedSD.getDriver;

public class SearchResult extends Base {

    By starBlocks = By.xpath("//div[@data-testid='rating-stars']");
    By allStarsOnPage = By.xpath("//div[@data-testid='rating-stars']/span");

    By hotelList = By.xpath("//div[@data-testid='title']");
    
    By distTextList = By.xpath("//section[@class='_2jyzVa']//li[1]");
    
    public ArrayList<Double> getDistanceList()
    {
        ArrayList<String> rawDistance = getElementTextList(distTextList);
        //1.3 km to city centre

        ArrayList<Double> distList = new ArrayList<>();
        for(int i=0;i<rawDistance.size();i++)
        {
            String distStr = rawDistance.get(i).split(" ")[0]; // 1.3

           distList.add( Double.parseDouble(distStr));
        }

        return distList;
    }


    public ArrayList<String> getHotelsList()
    {
        return getElementTextList(hotelList);
    }


    public void clickStarRating(String star) // 4,5, 3
    {
        By starRating = By.xpath("//div[@data-filters-group='class']//div[contains(text(),'"+star+" star')]");
        webAction(starRating).click();
    }

    public int countRealStarRatingOnSearchPage()
    {
        getDriver().navigate().refresh();

        int countStarBlocks = getDriver().findElements(starBlocks).size(); // 22 - star blocks
        System.out.println("countStarBlocks="+countStarBlocks);

        int countAllStars = getDriver().findElements(allStarsOnPage).size(); // 88 - individual stars

        System.out.println("countAllStars="+countAllStars);

        return (countAllStars / countStarBlocks); // ->  number of stars per block
    }
}

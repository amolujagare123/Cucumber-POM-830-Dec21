package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;

import java.util.ArrayList;

import static stepdefinition.SharedSD.getDriver;

public class Darksky extends Base {

    By currentTemp = By.xpath("//span[@class='summary swap']");
    By timeLineTemp = By.xpath("//span[@class='first']//span");
    By timeListRaw = By.xpath("//span[@class='hour']/span");
    By expander = By.xpath("//a[@data-day='0']//span[@class='toggle']");
    By minTempBar = By.xpath("//a[@class='day revealed']//span[@class='minTemp']");
    By maxTempBar = By.xpath("//a[@class='day revealed']//span[@class='maxTemp']");
    By minTempTimeline = By.xpath("//div[@class='dayDetails revealed']//span[@class='highTemp swip']//span[@class='temp']");
    By maxTempTimeline = By.xpath("//div[@class='dayDetails revealed']//span[@class='lowTemp swap']//span[@class='temp']");
    By lnkDarkSkyAPI = By.xpath("//a[normalize-space()='Dark Sky API']");

    public void clickDarkSkyAPI()
    {
        clickOn(lnkDarkSkyAPI);
    }

    public ArrayList<String> getBarTempList()
    {
        ArrayList<String> tempList = new ArrayList<>();

        tempList.add(getTextFromElement(minTempBar).split("˚")[0]); // 52˚
        tempList.add(getTextFromElement(maxTempBar).split("˚")[0]); // 52˚
        return tempList;
    }

    public ArrayList<String> getTimelineTempList()
    {
        ArrayList<String> tempList = new ArrayList<>();

        tempList.add(getTextFromElement(minTempTimeline).split("˚")[0]); // 52˚
        tempList.add(getTextFromElement(maxTempTimeline).split("˚")[0]); // 52˚
        return tempList;
    }

    public void clickExpander()
    {
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("window.scrollBy(0,800)");
        js.executeScript("arguments[0].click()",webAction(expander));
        //clickOn(expander);
    }


    public ArrayList<Integer> getTimeListInt()
    {
        ArrayList<Integer> timeList = new ArrayList<>();
        ArrayList<String> timeListStr=getElementTextList(timeListRaw);
        System.out.println(timeListStr);
        //[11pm, 1am, 3am, 5am, 7am, 9am, 11am, 1pm, 3pm, 5pm, 7pm]

        for (int i=0;i<timeListStr.size();i++)
        {
            String tempTime = timeListStr.get(i); // "11pm" or "3am"
            int l = tempTime.length();
            String timeStr = tempTime.substring(0,l-2); // "11" or "3"

            int time = Integer.parseInt(timeStr);
            timeList.add(time);
        }

        // [11, 1, 3, 5, 7, 9, 11, 1, 3, 5, 7]
        return timeList;
    }



    public String getCurrentTemp()
    {
        return getTextFromElement(currentTemp).split("˚")[0]; //60˚ Mostly Cloudy.
    }

    public String getTimeLineTemp()
    {
       return getTextFromElement(timeLineTemp).split("°")[0]; // 60°
    }
}

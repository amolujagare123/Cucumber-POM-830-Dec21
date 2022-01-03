package pages;

import org.openqa.selenium.By;

public class DarkSkyAPI extends Darksky{

    By lnkLogin = By.xpath("//a[@class='button']");

    public void clickLnkLogin()
    {
        clickOn(lnkLogin);
    }
}

package sovTechUI.pages;

import org.openqa.selenium.WebDriver;

public class Home extends AbstractPage{
    private String url = "https://www.sovtech.co.za/";
    public Home(WebDriver d){
        super(d);
    }

    public void launch_SovTech(){
        navigate_to_url(url);
    }
}

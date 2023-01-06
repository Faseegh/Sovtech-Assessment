package sovTechUI.ui;

import org.openqa.selenium.WebDriver;
import sovTechUI.pages.*;


public class SovTechUI extends AbstractPage {
    public Home homePage;
    public ContactUs contactUsPage;


    public SovTechUI(WebDriver driver){
        super(driver);
        homePage = new Home(driver);
        contactUsPage = new ContactUs(driver);
    }

}

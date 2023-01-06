
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import sovTechUI.ui.SovTechUI;

import static org.testng.Assert.*;

public class TestSovTechUI {

    WebDriver driver;
    SovTechUI app;

    @BeforeTest
    public void setup_browser_session(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        app = new SovTechUI(driver);
        app.homePage.launch_SovTech();
    }

    @DataProvider(name= "test_data")
    public Object[][] contactMethod(){
        return new Object[][] {
                //{name,email, phone, size, service, help}
                //{"name","dataclicka@gmail.com","08123456789","1 - 25","Mobile App","help text"},
                {null,null,null,null,null,null}, //submit empty form
                {null,null,null,null,null,"help text"}, //mandatory fields
                {null,"dataclicka@gmail.com","08123456789","1 - 25","Mobile App","help text"},//missing name field
                {"name",null,"08123456789","1 - 25","Web App","help text"}, //missing email field
                {"name","test@gmail.com","08123456789","1 - 25","Mobile App","help text"},//invalid email
                {"name","data.com","08123456789","1 - 25","Support","help text"},//incorrect email format
                {"name","dataclicka@gmail.com","12345","1 - 25","Mobile App","help text"}, //number out of range
                {"name","dataclicka@gmail.com","08123456789","1 - 25",null,"help text"}, //no service selection
                {"name","dataclicka@gmail.com","08123456789",null,"Hire a team","help text"}, //no size selection
                // {"name",null,"08123456789","1 - 25","Mobile App","help text"},

        };
    }

    @Test(priority = 1)
    public void test_contact_us_form_successful()  {
        app.contactUsPage.nav_to_contact_us();
        app.contactUsPage.switch_to_contact_form_frame();

        app.contactUsPage.fill_form(
                "name","dataclicka@gmail.com", "08123456789",
                "1 - 25","Web App","help text");
        app.contactUsPage.submit_form();
        assertFalse(app.contactUsPage.count_error_labels());
        assertEquals(app.contactUsPage.confirm_submit_msg(),"CONTACT US - SUBMITTED");
        assertEquals(app.contactUsPage.confirm_success_msg(),"Success!");

        app.clear_fields();
        //driver.switchTo().parentFrame();

    }

    @Test(priority = 2)
    public void test_mandatory_field_error_label(){
        app.contactUsPage.nav_to_contact_us();
        app.contactUsPage.switch_to_contact_form_frame();

        app.contactUsPage.fill_form(
                null,"dataclicka@gmail.com", "08123456789",
                "1 - 25","Web App","help text");
        app.contactUsPage.submit_form();
        if (app.contactUsPage.count_error_labels()){
            assertEquals(app.contactUsPage.confirm_error_label(),"Please complete this required field.");
            assertEquals(app.contactUsPage.confirm_final_error_label(),"Please complete all required fields.");
        }
        app.clear_fields();
    }

    @DataProvider(name= "email_test_data")
    public Object[][] email_test_data(){
        return new Object[][] {
                //{"dataclick@gmail.com"}, //valid email
                {"test@gmail.com","Please enter a valid email address."}, //invalid email
                {"some wrong email","Email must be formatted correctly."}//incorrect format
        };
    }

    @Test(dataProvider = "email_test_data" ,priority = 3)
    public void test_email_field_error_labels(String email, String label_text){
        app.contactUsPage.nav_to_contact_us();
        app.contactUsPage.switch_to_contact_form_frame();

        app.contactUsPage.fill_form(
                "name",email, "08123456789",
                "1 - 25","Web App","help text");
        //app.contactUsPage.submit_form();
        if (app.contactUsPage.count_error_labels()){
            assertEquals(app.contactUsPage.confirm_error_label(),label_text);
        }
        app.clear_fields();
        driver.switchTo().parentFrame();
    }

    @DataProvider(name= "number_test_data")
    public Object[][] number_test_data(){
        return new Object[][] {
                //{"dataclick@gmail.com"}, //valid email
                {"vkyffvm","Must contain only numbers, +()-. and x."}, //invalid email
                {"08123","Email must be formatted correctly."}//incorrect format
        };
    }

    @Test(priority = 4)
    public void test_number_field_error_label(){
        app.contactUsPage.nav_to_contact_us();
        app.contactUsPage.switch_to_contact_form_frame();

        app.contactUsPage.fill_form(
                "name","dataclicka@gmail.com", "invalid_number",
                "1 - 25","Web App","help text");
        //app.contactUsPage.submit_form();
        if (app.contactUsPage.count_error_labels()){
            assertEquals(app.contactUsPage.confirm_error_label(),"Must contain only numbers, +()-. and x.");
        }
        app.clear_fields();
        driver.switchTo().parentFrame();
    }

    @Test(priority = 5)
    public void test_services_dropdown_field_error_label(){
        app.contactUsPage.nav_to_contact_us();
        app.contactUsPage.switch_to_contact_form_frame();

        app.contactUsPage.fill_form(
                "name","dataclicka@gmail.com", "278123456789",
                "1 - 25",null,"help text");
        app.contactUsPage.submit_form();
        if (app.contactUsPage.count_error_labels()){
            assertEquals(app.contactUsPage.confirm_error_label(),"Please select an option from the dropdown menu.");
        }
        app.clear_fields();
        driver.switchTo().parentFrame();
    }

    @Test(dataProvider = "test_data", priority = 6)
    public void negative_field_tests(String f_name, String e_address, String number,
                                     String size, String ser, String help){
        app.contactUsPage.nav_to_contact_us();
        app.contactUsPage.switch_to_contact_form_frame();

        app.contactUsPage.fill_form(f_name,e_address,number,size,ser,help);
        app.contactUsPage.submit_form();
        assertTrue(app.contactUsPage.count_error_labels());

        app.clear_fields();
        driver.switchTo().parentFrame();
    }



    @AfterTest
    public void end_browser_session(){
        driver.close();
        driver.quit();
    }

}

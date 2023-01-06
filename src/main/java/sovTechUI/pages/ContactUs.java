package sovTechUI.pages;

import org.openqa.selenium.*;


public class ContactUs extends AbstractPage{
    //Unique id used to identify the form
    private final String form_id = "10215c68-0453-423d-bb7a-f628ce89fb09";
    public ContactUs(WebDriver d){
        super(d);
    }

    /**
     *Locators of elements found on the Contact Us Page.
     * Will add more the more tests are generated :)
     */

    public By full_name = By.xpath(String.format("//input[@id=\"firstname-%s\"]",form_id));//By.id(String.format("firstname-%s",form_id));//
    public By email = By.xpath(String.format("//input[@id=\"email-%s\"]",form_id));
    public By phone = By.xpath(String.format("//input[@id=\"phone-%s\"]",form_id));
    String company_size_xpath;
    public By company_size_dropdown = By.xpath(String.format("//select[@id=\"0-2/company_size-%s\"]",form_id));
    //values = ["1 - 25", "25 - 50", "100-500", "500-1000", "1000+" ]
    String services_xpath;
    public By services_dropdown = By.xpath(String.format("//select[@id=\"services-%s\"]",form_id));
    //values = ["Software Development","Mobile App","Web App","Support","Hire a team","Staff Augmentation"]
    public By help_field = By.xpath(String.format("//textarea[@id=\"message-%s\"]",form_id));
    public By send_msg_btn = By.xpath("//input[@type=\"submit\"]");
    public By legal_text = By.xpath("//div[@class=\"legal-consent-container\"]//p");
    public By contact_us_btn = By.xpath("//div[@class=\"navigation-right\"]//a[@href=\"/contact-us\"]");

    public By submitted_text = By.xpath("//h6[@class=\"subheading\"]");
    public By success_text = By.xpath("//h1[@class=\"contact-hero-heading\"]");
    public By error_labels = By.xpath("//label[@class=\"hs-error-msg\"]");
    public By final_error_label = By.xpath("//label[@class=\"hs-main-font-element\"]");


    //Click Contact us button in navbar
    public void nav_to_contact_us(){
        click_element(contact_us_btn);
    }

    public void switch_to_contact_form_frame(){
        find_one_element(By.id("hs-form-iframe-0"));
        driver.switchTo().frame("hs-form-iframe-0");
    }


    public void fill_form(String f_name, String e_address, String number, String size, String ser, String help)  {
        if (f_name!=null){send_keys_to_field(full_name,f_name);}
        if (e_address!=null){send_keys_to_field(email,e_address);}
        if (number!=null){send_keys_to_field(phone,number);}

        if (size!=null){
        company_size_xpath = String.format("//select[@id=\"0-2/company_size-%s\"]//option[@value=\"%s\"]",form_id,size);
        selectDropdownOption(company_size_dropdown,size,By.xpath(company_size_xpath));}

        if (ser!=null){
        services_xpath = String.format("//select[@id=\"services-%s\"]//option[@value=\"%s\"]",form_id,ser);
        selectDropdownOption(services_dropdown,ser,By.xpath(services_xpath));}

        if (help!=null){send_keys_to_field(help_field, help);}

    }


    public void submit_form(){
        click_element(send_msg_btn);
    }

    public String confirm_submit_msg(){
        return find_text(submitted_text);
    }

    public String confirm_success_msg(){
        return find_text(success_text);
    }

    //check if there are any error messages displayed on the form
    public boolean count_error_labels() {
       try {
           //error messages found on form
           find_many_elements(error_labels).size();
           return true;
       }
       catch (TimeoutException tOe){
           //meaning that there were no error messages found
            return false;
       }
    }

    public String confirm_error_label(){
        return find_text(error_labels);
    }

    public String confirm_final_error_label(){
        return  find_text(final_error_label);

    }
}

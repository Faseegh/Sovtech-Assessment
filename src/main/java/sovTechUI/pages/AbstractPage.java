package sovTechUI.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public abstract class AbstractPage {

    public WebDriver driver;


    public AbstractPage(WebDriver _d){

        this.driver = _d;
    }

    public void navigate_to_url(String url){

        driver.navigate().to(url);
    }


    public WebElement find_one_element(By by){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    public List<WebElement> find_many_elements(By by){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
        return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
    }

    public void dropdown_wait(By by){
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    public void selectDropdownOption(By by, String option, By by_wait) {
        dropdown_wait(by_wait);
        Select dropdown = new Select(find_one_element(by));
        dropdown.selectByValue(option);
    }

    public void clearDropdownOption(By by){
        Select dropdown = new Select(find_one_element(by));
        dropdown.selectByIndex(0);
    }

    public void click_element(By by){

        find_one_element(by).click();
    }

    public void send_keys_to_field(By by, String text){

        find_one_element(by).sendKeys(text);
    }

    public String find_text(By by){
        //System.out.println("*******"+find_one_element(by).getText());
        return find_one_element(by).getText();
    }

    public void clear_fields(){
        driver.manage().deleteAllCookies();
        driver.navigate().refresh();
    }
}

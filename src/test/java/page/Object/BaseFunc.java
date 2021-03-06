package page.Object;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class BaseFunc {
    private WebDriver driver;
    private WebDriverWait wait;
    private  final Logger LOGGER = LogManager.getLogger(this.getClass());

    public BaseFunc() {
        System.setProperty("webdriver.chrome.driver", "c://chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, 10);
    }
    public void openPage(String url) {
        LOGGER.info("Try to open page: " + url);

        if(!url.startsWith("http://") && !url.startsWith("https://")){
            url = "http://" + url;
        }

        driver.get(url);
    }

    public List<WebElement> findElements(By locator) {
        return driver.findElements(locator);
    }
    public WebElement findElement(By locator){
        return driver.findElement(locator);
    }
    public void click(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
    }
    public int parseCommentCount(String textToParse) {
        textToParse = textToParse.substring(1, textToParse.length() - 1);
        return Integer.parseInt(textToParse);
    }
    public WebElement waitUntil(By locator){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
    public void closeBrowser(){
        driver.quit();
    }
}

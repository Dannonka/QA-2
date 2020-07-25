import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;
import java.util.logging.Logger;

public class FirstHeadline {

    private WebDriver driver;

    private final String HEADLINE_LOCATOR = ".//h1[contains(@class, 'headline__title')]";
    private final String ARTICLE_LOCATOR = ".//article[contains(@class, 'headline')]";

    private static final Logger LOGGER = Logger.getLogger(FirstHeadline.class.getName());

    @BeforeEach
    public void preconditions() {
        System.setProperty("webdriver.chrome.driver", "c://chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        LOGGER.info("open https://rus.delfi.lv site");
        driver.get("https://rus.delfi.lv");
    }

    @Test
    public void firstTitle() {
        List<WebElement> textes = driver.findElements(By.xpath(HEADLINE_LOCATOR));
        WebElement text = driver.findElement(By.xpath(HEADLINE_LOCATOR));
        for (int i = 0; i < textes.size(); i++) {
            if (text.getText().equals(textes.get(i).getText())) {
//                LOGGER.info("Find first Title row in home webpage");
                System.out.println("Row: " + i);
                System.out.println("Title: " + textes.get(i).getText());
            }
        }

    }

    @Test
    public  void commentSection(){
        WebElement articles = driver.findElement(By.xpath(ARTICLE_LOCATOR));
        List<WebElement> list = articles.findElements(By.xpath(".//a[contains(@class, 'comment-count')]"));
        if (list.isEmpty()){
            int commentCoun = 0;
        }
        else {
            Integer result = Integer.valueOf(list.get(0).getText().replace("(", "").replace(")", ""));
            System.out.println("Comment amount:" + result);
        }
    }

    @AfterEach
    public void postconditions() {
        driver.quit();
    }
}

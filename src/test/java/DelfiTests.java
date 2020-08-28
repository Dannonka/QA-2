import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.List;

public class DelfiTests {

    private final By HEADLINE_LOCATOR = By.xpath(".//h1[contains(@class, 'headline__title')]");
    private final By HEADLINE_IN_ARTICLE = By.xpath(".//h1[contains(@class, 'd-inline')]");
    private final String ARTICLE_LOCATOR = ".//article[contains(@class, 'headline')]";
    private final By ARTICLE = By.tagName("article");

    private WebDriver driver; //private because we need it only in this class

    @BeforeEach
    public void preconditions() {
        System.setProperty("webdriver.chrome.driver", "c://chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://rus.delfi.lv");
    }

    @Test
    public void firstDelfiTest() {

        // WebDriver driver = initDriver();


        List<WebElement> textes = driver.findElements(HEADLINE_LOCATOR);
        WebElement text = driver.findElement(HEADLINE_LOCATOR);
        for (int i = 0; i < textes.size(); i++) {
            System.out.println(textes.get(i).getText());
            if( text.getText().equals(textes.get(i).getText()))
            {
                System.out.println("Row: " + i);
                System.out.println("Title: " + textes.get(i).getText());
            }

        }
        String comment = driver.findElement(By.xpath(".//a[contains(@class, 'comment-count')]")).getText();
        System.out.println(comment);
    }

    @Test
    public void workingWithLists() {
        List<String> studentNames = new ArrayList<String>();

        System.out.println(studentNames.isEmpty());

        studentNames.add("Zoja");
        studentNames.add("Petja");
        studentNames.add("Vovan");

        System.out.println(studentNames.isEmpty());
        System.out.println(studentNames.size()); // size for Cicle
        System.out.println(studentNames.get(1)); //counting starts with 0 so to get second element write 1

    }

    @Test
    public void titleTest(){
        final String TITLE_TO_FIND = "Северная Корея заявила о возможном первом случае случае заражения Covid-19";

        //get all articles
        List<WebElement> articles = driver.findElements(ARTICLE);

        //find given article by text
        boolean isFound = false;
        for (WebElement article : articles) { //for each
            if(article.findElement(HEADLINE_LOCATOR).getText().equals(TITLE_TO_FIND)) {   // in article find it's title
                article.findElement(HEADLINE_LOCATOR).click();                           // if it is equal, click on it
                isFound = true;
                break;                                                                  // quit for each
            }
        }
        Assertions.assertTrue(isFound, "Article is not found!");
        //find article title
        String articlePageTitle = driver.findElement(HEADLINE_IN_ARTICLE).getText();

        //check it with given
        Assertions.assertEquals(TITLE_TO_FIND, articlePageTitle,"Titles are not equal!");
                            // expected result, actual result, error message
    }


    @AfterEach
    public void postconditions() {
        driver.quit();
    }
}





//public WebDriver initDriver() {
//   System.setProperty("webdriver.chrome.driver", "c://chromedriver.exe");
//    WebDriver driver = new ChromeDriver();
//   return driver;
//}


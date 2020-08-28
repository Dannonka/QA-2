import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;
import java.util.StringTokenizer;

public class TVNet {

    private final By ARTICLE = By.tagName("article");
    private final By TITLE = By.xpath(".//span[@itemprop = 'headline name']");
    private final By COMMENTS_COUNTS = By.xpath(".//span[@itemprop = 'url']");
    private final By ARTICLE_PAGE_TITLE =By.xpath(".//h1[@itemprop = 'headline name']");
    private final By ARTICLE_PAGE_COMMENT_COUNTS = By.xpath(".//a[contains(@class, 'item--comments')]");
    private final By COMMENT_PAGE_COMMENT_COUNT = By.xpath(".//span[contains(@class,  'article-comments')]");

    private final Logger LOGGER = LogManager.getLogger(this.getClass()); //TVNet.class


    @Test
    public void articleTitleCommentsCheck(){
        LOGGER.info("This test is checking titles and comment count of third Article");
        System.setProperty("webdriver.chrome.driver", "c://chromedriver.exe");

        LOGGER.info("Opening Browser");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        LOGGER.info("Opening Home page");
        driver.get("https://tvnet.lv");

        LOGGER.info("Getting all articles");
        List<WebElement> articles = driver.findElements(ARTICLE);

        LOGGER.info("Find third article");
        WebElement thirdArticle = articles.get(2);

        LOGGER.info("Get Article title");
        String homePageTitle = thirdArticle.findElement(TITLE).getText();

        LOGGER.info("Get article comment amount");
        int homePageCommentCount = 0;
        if (!thirdArticle.findElements(COMMENTS_COUNTS).isEmpty()){
            homePageCommentCount = parseCommentCount(thirdArticle.findElement(COMMENTS_COUNTS).getText());
        }

        // click on it
        thirdArticle.findElement(TITLE).click();

        //find title
        String articlePageTitle = driver.findElement(ARTICLE_PAGE_TITLE).getText();

        //find comment count
        int articlePageCommentCount = Integer.parseInt(driver.findElement(ARTICLE_PAGE_COMMENT_COUNTS).getText());

        //check
        Assertions.assertTrue(homePageTitle.startsWith(articlePageTitle), "Wrong title");
        Assertions.assertEquals(homePageCommentCount,articlePageCommentCount,"Wrong comment count");

        //open comment page
        driver.findElement(ARTICLE_PAGE_COMMENT_COUNTS).click();

        //find title
        String commentPageTitle =  driver.findElement(ARTICLE_PAGE_TITLE).getText();

        //find comment count
        int comentPageCommentCount = Integer.parseInt(driver.findElement(COMMENT_PAGE_COMMENT_COUNT).getText());

        //check
        Assertions.assertTrue(homePageTitle.startsWith(commentPageTitle),"Wrong title");
        Assertions.assertEquals(homePageCommentCount,comentPageCommentCount,"Wrong comment count");

    }

    private int parseCommentCount(String textToParse){
       textToParse = textToParse.substring(1, textToParse.length() -1);
       return Integer.parseInt(textToParse);
    }
}

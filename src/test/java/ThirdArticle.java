import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ThirdArticle {

    private final By HEADLINE_LOCATOR = By.xpath(".//h1[contains(@class, 'headline__title')]");
    private final By HEADLINE_IN_ARTICLE = By.xpath(".//h1[contains(@class, 'd-inline')]");
    private final By ARTICLES = By.tagName("article");
    private final By COMMENT = By.xpath(".//a[contains(@class, 'comment-count')]");
    private final By COMMENT_IN_ARTICLE = By.xpath(".//a[contains(@class, 'text-red-ribbon')]");
    private final By COMMENT_PAGE = By.xpath(".//div[contains(@class,'chat-page')]");
    private final By COMMENT_PAGE_ARTICLE_TITLE = By.xpath(".//h1[contains(@class,'article-title')]");
    private final By COMMENT_PAGE_COMMENT = By.xpath(".//span[contains(@class, 'type-cnt')]");

    private Integer comentHome = null;
    private Integer comentArticle = null;
    private Integer sum = null;

    private WebDriver driver;

    private final Logger LOGGER = LogManager.getLogger(this.getClass());

    @BeforeEach
    public void preconditions() {
        System.setProperty("webdriver.chrome.driver", "c://chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://rus.delfi.lv");

    }

    @Test
    public void findThirdArticle() {
    LOGGER.info("This test is checking third article title and comment amount");
        WebDriverWait wait = new WebDriverWait(driver, 10);
    LOGGER.info("Find All articles at Home Page");
        List<WebElement> articles = driver.findElements(ARTICLES);
    LOGGER.info("Find third article at Home Page");
        WebElement thirdArticle = articles.get(2);
    LOGGER.info("Find third article title at Home Page");
        String articleTitle = thirdArticle.findElement(HEADLINE_LOCATOR).getText();
        System.out.println(articleTitle);
    LOGGER.info("Find third article comment amount at Home Page");
        comentHome = commentCountForHomePage(thirdArticle, COMMENT);
    LOGGER.info("Open Third Article");
        thirdArticle.findElement(HEADLINE_LOCATOR).click();

    LOGGER.info("Wait Article page to be loaded");
        wait.until(ExpectedConditions.visibilityOfElementLocated(HEADLINE_IN_ARTICLE));

    LOGGER.info("Find Article title and comment amount on Article Page");
        String titleInArticle = driver.findElement(HEADLINE_IN_ARTICLE).getText();
        System.out.println(titleInArticle);
    LOGGER.info("Find comment amount on Article Page");
        comentArticle = commentCountPage(driver, COMMENT_IN_ARTICLE);

    LOGGER.info("Check that title and comment amount on article page is the same as on Home page");
        compareArticles(articleTitle, titleInArticle);
        compareCommentCount(comentArticle, comentHome);

    LOGGER.info("Open Comment section in article page");
        driver.findElements(COMMENT_IN_ARTICLE).get(0).click();

    LOGGER.info("wait comment page to be loaded");
        wait.until(ExpectedConditions.visibilityOfElementLocated(COMMENT_PAGE));

    LOGGER.info("Find article title and comment amount on Comment Page");
        WebElement comentList = driver.findElement(COMMENT_PAGE);
        String titleInComent = comentList.findElement(COMMENT_PAGE_ARTICLE_TITLE).getText();
        System.out.println(titleInComent);
        sum = commentCountPage(driver, COMMENT_PAGE_COMMENT);

    LOGGER.info("Check that title and comment amount on comment page is the same as on Home page");
        compareArticles(articleTitle, titleInArticle);
        compareCommentCount(comentHome, sum);
    }


    /**
     * change comment amount from string to integer removing "(" and ")"
     *
     * @param textToParse
     * @return Integer
     */
    private int parseCommentCount(String textToParse) {
        textToParse = textToParse.substring(1, textToParse.length() - 1);
        return Integer.parseInt(textToParse);
    }

    private int commentCountForHomePage(WebElement element, By locator) {
        List<WebElement> comment = element.findElements(locator);
        if (comment.isEmpty()) {
            int commentCount = 0;
            return commentCount;
        } else {
            comentHome = parseCommentCount(comment.get(0).getText());
            System.out.println("Comment amount:" + comentHome);
            return comentHome;
        }
    }

    private int sumComment(List<WebElement> commentAmount) {
        int comentInkognito = parseCommentCount(commentAmount.get(0).getText());
        System.out.println("Anonimus comments:" + comentInkognito);
        int comentRegist = parseCommentCount(commentAmount.get(1).getText());
        System.out.println("Registred comments:" + comentRegist);
        sum = comentInkognito + comentRegist;
        System.out.println("Total comment amount: " + sum);
        return sum;
    }

    private int commentCountPage(WebDriver driver, By locator) {
        List<WebElement> commentInArticle = driver.findElements(locator);
        if (commentInArticle.isEmpty()) {
            int commentCount = 0;
            return commentCount;
        } else {
            if (locator.equals(COMMENT_PAGE_COMMENT)) {
                return sumComment(commentInArticle);
            } else {
                comentArticle = parseCommentCount(commentInArticle.get(0).getText());
                System.out.println("Comment amount:" + comentArticle);
                return comentArticle;
            }
        }
    }

    private void compareArticles(String article1, String article2) {
        assertEquals(article1.trim(), article2.trim(), "Titles are not equals");
    }

    private void compareCommentCount(int comment1, int comment2) {
        assertEquals(comment1, comment2, "Comment amount is not equal");
    }

    @AfterEach
    public void postconditions() {
        driver.quit();
    }
}

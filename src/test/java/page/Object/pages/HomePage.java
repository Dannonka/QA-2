package page.Object.pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import page.Object.BaseFunc;

import java.util.List;

public class HomePage {
    private final By HEADLINE_LOCATOR = By.xpath(".//h1[contains(@class, 'headline__title')]");
    private final By ARTICLES = By.tagName("article");
    private final By COMMENT = By.xpath(".//a[contains(@class, 'comment-count')]");

    private BaseFunc baseFunc;

    public HomePage(BaseFunc baseFunc) {
        this.baseFunc = baseFunc;
    }

    public String getTitleById(int id){
       return getArticleById(id).findElement(HEADLINE_LOCATOR).getText();
    }

    public ArticlePage openArticle(int id) {
       baseFunc.click(getArticleById(id));
        return new ArticlePage(baseFunc);
    }

    public WebElement getArticleById(int id) {
        List<WebElement> articles = baseFunc.findElements(ARTICLES);
        Assertions.assertFalse(articles.isEmpty(), "There is no articles");
        return articles.get(id);
    }
    public int commentCountForHomePage(int id) {
        List<WebElement> comment = getArticleById(id).findElements(COMMENT);
        if (comment.isEmpty()) {
            int commentCount = 0;
            return commentCount;
        } else {
            return baseFunc.parseCommentCount(comment.get(0).getText());
        }
    }
}


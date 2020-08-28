package page.Object.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import page.Object.BaseFunc;

import java.util.List;

public class ArticlePage {

    private final By HEADLINE_IN_ARTICLE = By.xpath(".//h1[contains(@class, 'd-inline')]");
    private final By COMMENT_IN_ARTICLE = By.xpath(".//a[contains(@class, 'text-red-ribbon')]");
    private final By COMMENT_BUTTON = By.xpath(".//a[contains(@class, 'btn-comments')]");
    private BaseFunc baseFunc;


    public ArticlePage(BaseFunc baseFunc) {
        this.baseFunc = baseFunc;
    }

    public String articlePageTitleByLocator() {
        return baseFunc.findElement(HEADLINE_IN_ARTICLE).getText();
    }

    public int articlePageCommentByLocator() {
        List<WebElement> comment = baseFunc.findElements(COMMENT_IN_ARTICLE);
        if (comment.isEmpty()) {
            int commentCount = 0;
            return commentCount;
        } else {
            return baseFunc.parseCommentCount(comment.get(0).getText());
        }
    }

    public void loadPage() {
        baseFunc.waitUntil(HEADLINE_IN_ARTICLE);
    }

    public CommentPage openCommentPage() {
        if (articlePageCommentByLocator() == 0) {
            baseFunc.findElement(COMMENT_BUTTON).click();
            return new CommentPage(baseFunc);
        } else {
            baseFunc.findElement(COMMENT_IN_ARTICLE).click();
            return new CommentPage(baseFunc);
        }
    }
}

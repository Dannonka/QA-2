package page.Object.pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import page.Object.BaseFunc;

import java.util.List;

public class CommentPage {

    private final By COMMENT_PAGE = By.xpath(".//div[contains(@class,'chat-page')]");
    private final By COMMENT_PAGE_ARTICLE_TITLE = By.xpath(".//h1[contains(@class,'article-title')]");
    private final By COMMENT_PAGE_COMMENT = By.xpath(".//span[contains(@class, 'type-cnt')]");

    private BaseFunc baseFunc;

    public CommentPage(BaseFunc baseFunc) {
        this.baseFunc = baseFunc;
    }
    public void loadCommentPage(){
        baseFunc.waitUntil(COMMENT_PAGE);
    }
    public String commentPageArticleTitle(){
        WebElement comentList = baseFunc.findElement(COMMENT_PAGE);
        return comentList.findElement(COMMENT_PAGE_ARTICLE_TITLE).getText();
    }
    public int commentPageComment(){
        List<WebElement> comment = baseFunc.findElements(COMMENT_PAGE_COMMENT);
        if (comment.isEmpty()) {
            int commentCount = 0;
            return commentCount;
        } else {
            return sumComment(comment);
        }
    }
    public int sumComment(List<WebElement> commentAmount) {
        int comentInkognito = baseFunc.parseCommentCount(commentAmount.get(0).getText());
        int comentRegist = baseFunc.parseCommentCount(commentAmount.get(1).getText());
        int sum = comentInkognito + comentRegist;
        return sum;
    }
}

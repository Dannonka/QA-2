package page.Object.tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import page.Object.BaseFunc;
import page.Object.pages.ArticlePage;
import page.Object.pages.CommentPage;
import page.Object.pages.HomePage;

public class PageObjectTestes {

    private  final Logger LOGGER = LogManager.getLogger(this.getClass());

    @Test
    public void pageObjectTest() {
        BaseFunc baseFunc = new BaseFunc();
        baseFunc.openPage("delfi.lv");

        HomePage homePage = new HomePage(baseFunc);
        String homePageTitle = homePage.getTitleById(2);
        LOGGER.info("Home page Article Title: "+ homePageTitle);
        int homePageComment = homePage.commentCountForHomePage(2);
        LOGGER.info("Home page Article Comment count: "+ homePageComment);
        ArticlePage articlePage = homePage.openArticle(2);
        articlePage.loadPage();

        String articlePageTitle = articlePage.articlePageTitleByLocator();
        LOGGER.info("Article page Title: "+ articlePageTitle);
        int articlePageComment = articlePage.articlePageCommentByLocator();
        LOGGER.info("Article page Comment count: "+ articlePageComment);

        Assertions.assertEquals(homePageComment,articlePageComment,"Home page and Article page comment amount is not equals");
        Assertions.assertEquals(homePageTitle.trim(),articlePageTitle.trim(),"Home page and article page Titles is not equals");

        CommentPage commentPage = articlePage.openCommentPage();
        commentPage.loadCommentPage();

        String commentPageTitle = commentPage.commentPageArticleTitle();
        LOGGER.info("Comment page Article Title: "+ commentPageTitle);
        int commentPageComment = commentPage.commentPageComment();
        LOGGER.info("Comment page Comment count: "+ commentPageComment);

        Assertions.assertEquals(homePageComment,commentPageComment,"Home page and Comment page comment amount is not equals");
        Assertions.assertEquals(homePageTitle.trim(),commentPageTitle.trim(),"Home page and Comment page Titles is not equals");

        baseFunc.closeBrowser();
    }
}

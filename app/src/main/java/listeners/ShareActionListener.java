package listeners;

import models.Article;

/**
 * This class provides callback events for article icons on Article list
 */
public interface ShareActionListener {

    void emailArticle(Article artcle);

    void shareArticle(Article article);

}

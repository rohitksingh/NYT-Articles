package listeners;

import models.Article;

public interface ShareActionListener {

    void emailArticle(Article artcle);
    void shareArticle(Article article);

}

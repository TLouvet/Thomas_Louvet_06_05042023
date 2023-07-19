package com.openclassrooms.mddapi.interfaces;

import com.openclassrooms.mddapi.models.Article;

public interface ArticleService {
    Iterable<Article> findFeedArticles();
    Article findOne(long id);
    Article create(Article article);
}

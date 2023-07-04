package com.openclassrooms.mddapi.interfaces;

import com.openclassrooms.mddapi.models.Article;

public interface ArticleService {
    Iterable<Article> findAll();
    Article findOne(long id);
    Article create(Article article);
}

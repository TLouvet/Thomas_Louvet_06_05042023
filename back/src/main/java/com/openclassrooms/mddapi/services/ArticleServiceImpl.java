package com.openclassrooms.mddapi.services;

import com.openclassrooms.mddapi.exception.NotFoundException;
import com.openclassrooms.mddapi.interfaces.ArticleService;
import com.openclassrooms.mddapi.models.Article;
import com.openclassrooms.mddapi.models.User;
import com.openclassrooms.mddapi.repositories.ArticleRepository;
import com.openclassrooms.mddapi.security.UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private ThemeServiceImpl themeService;

    @Autowired
    private UserDetailsService userDetailsService;

    public Iterable<Article> findAll(){
        return this.articleRepository.findAllByOrderByIdDesc();
    }

    public Iterable<Article> findFeedArticles(){
        User user = this.userDetailsService.getUser();
        return this.articleRepository.findUserFeed(user.getId());
    }

    public Article findOne(final long id){
        return this.articleRepository.findById((id)).orElseThrow(NotFoundException::new);
    }

    public Article create(Article article){
        User user = this.userDetailsService.getUser();
        article.setAuthor(user);

        return this.articleRepository.save(article);
    }
}

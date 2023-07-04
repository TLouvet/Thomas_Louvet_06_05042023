package com.openclassrooms.mddapi.services;

import com.openclassrooms.mddapi.exception.NotFoundException;
import com.openclassrooms.mddapi.models.Article;
import com.openclassrooms.mddapi.models.Comment;
import com.openclassrooms.mddapi.models.Theme;
import com.openclassrooms.mddapi.models.User;
import com.openclassrooms.mddapi.repositories.ArticleRepository;
import org.assertj.core.api.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ArticleServiceTest {

    @Mock
    private ArticleRepository articleRepository;

    @InjectMocks
    private ArticleServiceImpl articleService;

    private final List<Article> articles = new ArrayList<Article>();
    private final Long id = 1L;

    @BeforeAll
    public void setup(){
        User user = new User(id, "test@test.com", "testuser", "123456789");
        Theme theme = new Theme(id, "Java", "Description", new ArrayList<User>());
        theme.getUsers().add(user);
        List<Comment> comments = new ArrayList<Comment>();
        Article article = new Article(id, "Article 1", "Article Content", user, theme);
        comments.add(new Comment(id, "Comment content", user, article));
        this.articles.add(article);
    }

    @Test
    public void itShouldGetTheArticleList(){
        given(this.articleRepository.findAll()).willReturn(this.articles);
        assertThat(this.articleService.findAll()).isEqualTo(this.articles);
    }

    @Test
    public void itShouldGetOneArticle(){
        given(this.articleRepository.findById(this.id)).willReturn(Optional.ofNullable(this.articles.get(0)));
        assertThat(this.articleService.findOne(this.id)).isEqualTo(this.articles.get(0));
    }

    @Test
    public void itShouldThrowAnErrorIfTheArticleDoesNotExist(){
        given(this.articleRepository.findById(this.id)).willReturn(Optional.empty());
        Assertions.assertThrows(NotFoundException.class,() -> this.articleService.findOne(this.id));
    }
}

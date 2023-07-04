package com.openclassrooms.mddapi.controllers;

import com.openclassrooms.mddapi.dto.ArticleDto;
import com.openclassrooms.mddapi.dto.CommentDto;
import com.openclassrooms.mddapi.mapper.ArticleMapper;
import com.openclassrooms.mddapi.mapper.CommentMapper;
import com.openclassrooms.mddapi.models.Article;
import com.openclassrooms.mddapi.models.Comment;
import com.openclassrooms.mddapi.services.ArticleServiceImpl;
import com.openclassrooms.mddapi.services.CommentServiceImpl;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    private ArticleServiceImpl articleService;

    @Autowired
    private CommentServiceImpl commentService;

    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping()
    public ResponseEntity<?> findAll(){
        Iterable<Article> articles = this.articleService.findAll();
        Iterable<ArticleDto> articlesResponse = this.articleMapper.mapToDtoList(articles);
        return ResponseEntity.ok().body(articlesResponse);
    }

    /**
     * Get Articles depending on the user subscriptions, which will be his feed on the homepage
     * @return
     */
    @GetMapping("/feed")
    public ResponseEntity<?> findFeedArticles(){
        Iterable<Article> articles = this.articleService.findFeedArticles();
        Iterable<ArticleDto> articlesResponse = this.articleMapper.mapToDtoList(articles);
        return ResponseEntity.ok(articlesResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findOne(@PathVariable("id") final long id){
        try {
            Article article = articleService.findOne((id));
            if (article == null){
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(this.articleMapper.map(article, ArticleDto.class));
        } catch (NumberFormatException e){
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{id}/comments")
    public ResponseEntity<?> findArticleComment(@PathVariable("id") Long id) {
        Iterable<Comment> comments = this.commentService.findArticleComments(id);
        System.out.println("ICI BAS");
        System.out.println(comments.toString());
        System.out.println("NOUS PARTONS");
        Iterable<CommentDto> formattedComments = this.commentMapper.mapToDtoList(comments);
        return ResponseEntity.ok(formattedComments);
    }

    @PostMapping()
    public ResponseEntity<?> create(@Valid @RequestBody ArticleDto articleDto){
        Article article = this.articleService.create(this.articleMapper.map(articleDto, Article.class));
        return ResponseEntity.ok(this.articleMapper.map(article, ArticleDto.class));
    }
}

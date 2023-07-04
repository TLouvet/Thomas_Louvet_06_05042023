package com.openclassrooms.mddapi.mapper;


import com.openclassrooms.mddapi.dto.ArticleDto;
import com.openclassrooms.mddapi.models.Article;
import com.openclassrooms.mddapi.repositories.ThemeRepository;
import com.openclassrooms.mddapi.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ArticleMapper extends ModelMapper {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ThemeRepository themeRepository;

    public ArticleMapper() {
        this.toEntityConfiguration();
        this.toDtoConfiguration();
    }

    private void toEntityConfiguration() {
        this.typeMap(ArticleDto.class, Article.class)
                .addMappings(src -> src.using(context -> {
                    Long themeId = (Long) context.getSource();
                    return this.themeRepository.findById(themeId).orElseThrow();
                }).map(ArticleDto::getTheme, Article::setTheme));
    }

    private void toDtoConfiguration() {
        this.typeMap(Article.class, ArticleDto.class)
                .addMapping(src -> src.getAuthor().getUsername(), ArticleDto::setAuthor)
                .addMapping(src -> src.getTheme().getId(), ArticleDto::setTheme);
    }


    public Iterable<ArticleDto> mapToDtoList(Iterable<Article> articles) {
        List<ArticleDto> articleDtoList = new ArrayList<>();

        for (Article article : articles) {
            ArticleDto articleDto = this.map(article, ArticleDto.class);
            articleDto.setAuthor(article.getAuthor().getUsername());
            articleDto.setTheme(article.getTheme().getId());
            articleDtoList.add(articleDto);
        }

        return articleDtoList;
    }


}

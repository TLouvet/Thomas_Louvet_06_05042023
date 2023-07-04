package com.openclassrooms.mddapi.mapper;

import com.openclassrooms.mddapi.dto.CommentDto;
import com.openclassrooms.mddapi.models.Comment;
import com.openclassrooms.mddapi.repositories.ArticleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CommentMapper extends ModelMapper {
    @Autowired
    private ArticleRepository articleRepository;

    public CommentMapper() {
        this.toEntityConfiguration();
        this.toDtoConfiguration();
    }

    private void toEntityConfiguration() {
        this.typeMap(CommentDto.class, Comment.class)
                .addMappings(src -> src.using(context -> {
                    Long articleID = (Long) context.getSource();
                    return this.articleRepository.findById(articleID).orElseThrow();
                }).map(CommentDto::getArticle, Comment::setArticle));
    }

    private void toDtoConfiguration() {
        this.typeMap(Comment.class, CommentDto.class)
                .addMapping(src -> src.getAuthor().getUsername(), CommentDto::setAuthor)
                .addMapping(src -> src.getArticle().getId(), CommentDto::setArticle);
    }


    public Iterable<CommentDto> mapToDtoList(Iterable<Comment> comments) {
        List<CommentDto> articleDtoList = new ArrayList<>();
        for (Comment comment : comments) {
            CommentDto commentDto = this.map(comment, CommentDto.class);
            commentDto.setAuthor(comment.getAuthor().getUsername());
            articleDtoList.add(commentDto);
        }

        return articleDtoList;
    }

}

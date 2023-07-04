package com.openclassrooms.mddapi.repositories;

import com.openclassrooms.mddapi.models.Article;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleRepository extends CrudRepository<Article, Long > {
    Iterable<Article> findAllByOrderByIdDesc();

    @Query(value = "SELECT a FROM Article a JOIN a.theme t JOIN t.users u WHERE u.id = :userId")
    Iterable<Article> findUserFeed(@Param("userId") Long userId);
}

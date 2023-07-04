package com.openclassrooms.mddapi.repositories;

import com.openclassrooms.mddapi.models.Theme;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface ThemeRepository extends CrudRepository<Theme, Long> {
    Iterable<Theme> findAllByOrderByNameAsc();

    @Query("SELECT t FROM Theme t JOIN UserThemeSubscription uts ON t.id = uts.theme_id WHERE uts.user_id = :userId ORDER BY t.name")
    Iterable<Theme> findSubscribedThemesByUserId(@Param("userId") Long userId);
}

package com.openclassrooms.mddapi.interfaces;

import com.openclassrooms.mddapi.models.Theme;

public interface ThemeService {
    Iterable<Theme> findAll();
    Theme findOne(Long id);
    Iterable<Theme> findUserSubscribedThemes();
    void subscribe(long themeId);
    void unsubscribe(long themeId);
}

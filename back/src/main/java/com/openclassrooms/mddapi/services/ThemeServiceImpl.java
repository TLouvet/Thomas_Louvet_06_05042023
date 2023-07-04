package com.openclassrooms.mddapi.services;

import com.openclassrooms.mddapi.exception.NotFoundException;
import com.openclassrooms.mddapi.interfaces.ThemeService;
import com.openclassrooms.mddapi.models.Theme;
import com.openclassrooms.mddapi.models.User;
import com.openclassrooms.mddapi.repositories.ThemeRepository;
import com.openclassrooms.mddapi.security.UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ThemeServiceImpl implements ThemeService {

    @Autowired
    private ThemeRepository themeRepository;

    @Autowired
    private UserDetailsService userDetailsService;

    public Iterable<Theme> findAll(){
        return this.themeRepository.findAllByOrderByNameAsc();
    }

    public Iterable<Theme> findUserSubscribedThemes() {
        User user = this.userDetailsService.getUser();
        return this.themeRepository.findSubscribedThemesByUserId(user.getId());
    }

    public void subscribe(long themeId) {
        Theme theme = this.themeRepository.findById(themeId).orElseThrow(NotFoundException::new);
        if (this.isUserSubscribed(theme)) {
            return;
        }

        theme.getUsers().add(this.userDetailsService.getUser());
        this.themeRepository.save(theme);
    }

    public void unsubscribe(long themeId) {
        Theme theme = this.themeRepository.findById(themeId).orElseThrow(NotFoundException::new);

        if (this.isUserSubscribed(theme)) {
            theme.setUsers(this.removeUserFromSubscriptionList(theme));
            this.themeRepository.save(theme);
        }
    }

    private boolean isUserSubscribed(Theme theme){
        User user = this.userDetailsService.getUser();
        return theme.getUsers().stream().anyMatch(u -> Objects.equals(u.getId(), user.getId()));
    }

    private List<User> removeUserFromSubscriptionList(Theme theme) {
        User authenticatedUser = this.userDetailsService.getUser();
        List<User> users = theme.getUsers();
        return users.stream().filter(user -> !Objects.equals(user.getId(), authenticatedUser.getId())).collect(Collectors.toList());
    }
}

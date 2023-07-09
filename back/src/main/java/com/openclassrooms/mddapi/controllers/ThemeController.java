package com.openclassrooms.mddapi.controllers;

import com.openclassrooms.mddapi.dto.ThemeDto;
import com.openclassrooms.mddapi.models.Theme;
import com.openclassrooms.mddapi.services.ThemeServiceImpl;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/theme")
public class ThemeController {

    @Autowired
    private ThemeServiceImpl themeService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping()
    public ResponseEntity<?> findAll() {
        Iterable<Theme> themes = this.themeService.findAll();
        Iterable<ThemeDto> themesDto = this.modelMapper.map(themes, new TypeToken<Iterable<ThemeDto>>() {}.getType());
        return ResponseEntity.ok(themesDto);
    }

    // TODO Mapper in service
    @GetMapping("/{id}")
    public ResponseEntity<?> findOne(@PathVariable("id") Long id) {
        Theme theme = this.themeService.findOne(id);
        return ResponseEntity.ok(theme);
    }

    @GetMapping("/mySubscriptions")
    public ResponseEntity<?> findUserSubscribedThemes(){
        Iterable<Theme> themes = this.themeService.findUserSubscribedThemes();
        Iterable<ThemeDto> themesDto = this.modelMapper.map(themes, new TypeToken<Iterable<ThemeDto>>() {}.getType());
        return ResponseEntity.ok(themesDto);
    }

    @PostMapping("/{theme_id}/subscribe")
    public ResponseEntity<?> subscribe(@PathVariable("theme_id") Long themeId) {
        try {
            this.themeService.subscribe(themeId);
            return ResponseEntity.ok().build();
        } catch (NumberFormatException exception){
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{theme_id}/unsubscribe")
    public ResponseEntity<?> unsubscribe(@PathVariable("theme_id") Long themeId) {
       try {
           this.themeService.unsubscribe(themeId);
           return ResponseEntity.ok().build();
       } catch (NumberFormatException e) {
           return ResponseEntity.badRequest().build();
       }
    }
}

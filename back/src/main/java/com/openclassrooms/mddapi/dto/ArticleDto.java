package com.openclassrooms.mddapi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleDto {

    private long id;

    @NotBlank
    private String title;

    @NotBlank
    private String content;

    @NotNull
    private Long theme;

    private String author;

    private Instant created_at;

    private Instant updated_at;
}

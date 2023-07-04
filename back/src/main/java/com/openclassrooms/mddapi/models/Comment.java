package com.openclassrooms.mddapi.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="comment")
public class Comment extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NonNull
    private String content;

    @NonNull
    @OneToOne(
            fetch = FetchType.EAGER
    )
    @JoinColumn(nullable = false, name = "author_id", referencedColumnName = "id")
    private User author;

    @NonNull
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "article_id", referencedColumnName = "id")
    private Article article;
}


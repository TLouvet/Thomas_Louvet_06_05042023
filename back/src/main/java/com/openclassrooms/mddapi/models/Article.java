package com.openclassrooms.mddapi.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="article")
public class Article extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String title;

    @Column(length = 2000)
    private String content;

    @OneToOne(
      cascade = CascadeType.ALL,
      fetch = FetchType.EAGER,
      orphanRemoval = true
    )
    @JoinColumn(nullable = false, name = "author_id", referencedColumnName = "id")
    private User author;

    @OneToOne(
      cascade = CascadeType.ALL,
      fetch = FetchType.EAGER,
      orphanRemoval = true
    )
    @JoinColumn(nullable = false, name = "theme_id", referencedColumnName = "id")
    private Theme theme;

}

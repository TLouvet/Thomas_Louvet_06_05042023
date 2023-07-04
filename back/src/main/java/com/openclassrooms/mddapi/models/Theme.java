package com.openclassrooms.mddapi.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Table(name = "theme")
public class Theme extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(length = 50)
    private String name;

    private String description;

    @JsonIgnore
    @ManyToMany()
    @JoinTable(
            name = "user_theme_subscription",
            joinColumns = @JoinColumn( name = "theme_id" ),
            inverseJoinColumns = @JoinColumn( name = "user_id" )
    )
    private List<User> users;
}

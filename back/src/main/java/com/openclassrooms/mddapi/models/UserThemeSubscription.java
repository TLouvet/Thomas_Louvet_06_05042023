package com.openclassrooms.mddapi.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class UserThemeSubscription {
    @Id
    Long user_id;

    @Id
    Long theme_id;
}

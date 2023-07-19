package com.openclassrooms.mddapi.interfaces;

import com.openclassrooms.mddapi.models.User;

public interface UserService {
    User getUserProfile();
    User updateUserProfile(User user);
}

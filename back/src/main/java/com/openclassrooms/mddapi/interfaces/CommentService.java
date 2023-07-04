package com.openclassrooms.mddapi.interfaces;

import com.openclassrooms.mddapi.models.Comment;

public interface CommentService {
    Comment create(Comment comment);
}

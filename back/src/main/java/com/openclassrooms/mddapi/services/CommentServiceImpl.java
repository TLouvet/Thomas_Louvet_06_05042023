package com.openclassrooms.mddapi.services;

import com.openclassrooms.mddapi.interfaces.CommentService;
import com.openclassrooms.mddapi.models.Comment;
import com.openclassrooms.mddapi.models.User;
import com.openclassrooms.mddapi.repositories.CommentRepository;
import com.openclassrooms.mddapi.security.UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private UserDetailsService userDetailsService;

    public Iterable<Comment> findArticleComments(Long id) {
        return this.commentRepository.findByArticleId(id);
    }

    public Comment create(Comment comment) {
        User user = this.userDetailsService.getUser();
        comment.setAuthor(user);

        return this.commentRepository.save(comment);
    }
}

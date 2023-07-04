package com.openclassrooms.mddapi.controllers;

import com.openclassrooms.mddapi.dto.CommentDto;
import com.openclassrooms.mddapi.mapper.CommentMapper;
import com.openclassrooms.mddapi.models.Comment;
import com.openclassrooms.mddapi.services.CommentServiceImpl;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentServiceImpl commentService;

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/article/{id}/comments")
    public ResponseEntity<?> findArticleComment(@PathVariable("id") String id) {
        return ResponseEntity.ok().build();
    }

    @PostMapping()
    public ResponseEntity<?> create(@Valid @RequestBody CommentDto commentDto) {
        Comment comment = this.commentService.create(this.commentMapper.map(commentDto, Comment.class));

       return ResponseEntity.ok(this.commentMapper.map(comment, CommentDto.class));
    }
}

package com.backend.services;

import com.backend.entities.Comment;
import com.backend.repositories.comment.CommentRepository;

import javax.inject.Inject;
import java.util.List;

public class CommentService {

    @Inject
    CommentRepository commentRepository;

    public List<Comment> allComments() {
        return this.commentRepository.allComments();
    }

    public Comment addComment(Comment comment) {
        return this.commentRepository.addComment(comment);
    }

    public Comment findComment(Integer id) {
        return this.commentRepository.findComment(id);
    }

    public Comment findComment(String name) {
        return this.commentRepository.findComment(name);
    }

    public void deleteComment(Integer id) {
        this.commentRepository.deleteComment(id);
    }

    public Comment updateComment(Integer id, Comment comment) {
        return this.commentRepository.updateComment(id, comment);
    }

    public List<Comment> commentsByArticleId(Integer id) {
        return this.commentRepository.commentsByArticleId(id);
    }
}

package com.backend.repositories.comment;

import com.backend.entities.Comment;

import java.util.List;

public interface CommentRepository {

    List<Comment> allComments();

    Comment addComment(Comment comment);

    Comment findComment(Integer id);

    Comment findComment(String name);

    void deleteComment(Integer id);

    Comment updateComment(Integer id, Comment comment);

    List<Comment> commentsByArticleId(Integer id);
}
